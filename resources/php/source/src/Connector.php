<?php

namespace IsaaCloud;

/**
 * Response object
 *
 */
class Response {

    public function __construct($code, array $body, array $header) {
        if (!is_numeric($code)) {
            throw new ConnectorException("Code shoul be valid numeric!");
        }

        $this->code = $code;
        $this->header = $header;
        $this->body = $body;
    }

    private $code;
    private $body;
    private $header;

    /**
     * 
     * @return numeric Response Code
     */
    public function getCode() {
        return $this->code;
    }

    /**
     * 
     * @return array Body Array
     */
    public function getBody() {
        return $this->body;
    }

    /**
     * 
     * @return array Header
     */
    public function getHeader() {
        return $this->header;
    }

}

class ConnectorException extends \RuntimeException {

    public function __construct($message = '', $code = 0) {
        return parent::__construct($message, $code);
    }

}

/**
 * Main connector class
 */
abstract class Connector {

    private $baseOuathUrl = null;
    private $baseApiUrl = null;
    private $version = null;
    private $contentType = "application/json charset=utf-8";
    private $methods = array("GET", "POST", "PUT", "PATH", "OPTIONS");

    /**
     * Credentials configuration
     */
    private $clientId = null;
    private $secret = null;

    /**
     * Hash
     */
    private $hash = "xcxsd";

    /**
     * Constructor of connector, set up all connection parameters
     * 
     * @param string $baseApiUrl Base url path to api server
     * @param string $baseOuathUrl Base url path to authenticate server
     * @param string $version Version compatible API (in pattern x.y.z)
     * @param array $config Array of configutation
     */
    public function __construct($baseApiUrl, $baseOuathUrl, $version, $config) {

        if ((filter_var($baseApiUrl, FILTER_VALIDATE_URL) == true)) {
            $this->baseApiUrl = $baseApiUrl;
        } else {
            throw new ConnectorException("{$baseApiUrl} is invalid url!");
        }

        if ((filter_var($baseOuathUrl, FILTER_VALIDATE_URL) == true)) {
            $this->baseOuathUrl = $baseOuathUrl;
        } else {
            throw new ConnectorException("{$baseOuathUrl} is invalid url!");
        }

        $this->version = $version;

        /**
         * Set up configuration
         */
        if (is_array($config)) {
            //Set up clientId
            if (!isset($config["clientId"])) {
                throw new ConnectorException("There are not defined client id");
            } else {
                $this->clientId = $config["clientId"];
            }

            if (!isset($config["secret"])) {
                throw new ConnectorException("There are not defined secret");
            } else {
                $this->secret = $config["secret"];
            }
        }
    }

    /**
     * Merge string with array
     * 
     * @param type $string
     * @param type $parameters
     * 
     * @return string Merged string
     */
    public function merge($string, array $parameters = array()) {
        $mergedString = $string;
        if ((null != $parameters) && (count($parameters) > 0)) {
            foreach ($parameters as $key => $value) {
                $mergedString = str_replace($key, $value, $mergedString);
            }
        }
        return $mergedString;
    }

    /**
     * This method provide low level REST-call access mechnism
     * 
     * @param type $uri - the relative url addr
     * @param type $httpMethod - http method in the filed of GET, POST, PUT, OPTIONS, or PATH
     * @param type $parameters - array of request parameters
     * @param type $callback - optional anonymous function
     * 
     * @return Response Response value object
     */
    public function callService($uri, $httpMethod, $parameters, $body = null, $callback = null) {
        /**
         * Get method type from string
         */
        $method = strtoupper($httpMethod);
        if (!in_array($method, $this->methods)) {
            throw new ConnectorException("{$httpMethod} is invalid http method!");
        }
        /**
         * Build request header
         */
        $header = array(
            "Content-type" => $this->contentType,
            "Authentication" => $this->getAuthentication()
        );

        if (in_array($method, array("GET", "DELETE"))) {
            $body = null;
        }

        /**
         * Build url address to call
         */
        $url = $this->merge($this->baseApiUrl . $uri, $parameters);

        /**
         * Responder data object
         */
        $response = $this->curlIt($header, $method, $url, $body);

        /**
         * Callback if was defined
         */
        if ((is_string($callback) && function_exists($callback)) || (is_object($callback) && ($callback instanceof Closure))) {
            return $callback($response);
        } else {
            return $response;
        }
    }

    /**
     * Setters and Getters
     */
    public function getClientId() {
        return $this->clientId;
    }

    public function getSecret() {
        return $this->secret;
    }

    public function setClientId($clientId) {
        $this->clientId = $clientId;
    }

    public function setSecret($secret) {
        $this->secret = $secret;
    }

    public function getBaseOuathUrl() {
        return $this->baseOuathUrl;
    }

    public function getBaseApiUrl() {
        return $this->baseApiUrl;
    }

    public function getVersion() {
        return $this->version;
    }

    /**
     * Build and call request with CURL
     * 
     * @param array $header 
     * @param string $method HTTP method
     * @param type $url
     * @param array $body
     */
    public function curlIt(array $header, $method, $url, array $body = null) {
        $curl = curl_init($url);

        /**
         * Check if this setup is valid for our api configuration
         */
        curl_setopt($curl, CURLOPT_SSL_VERIFYPEER, false);
        curl_setopt($curl, CURLOPT_RETURNTRANSFER, true);

        /**
         * For update method
         */
        if ("POST" === $method) {
            curl_setopt($curl, CURLOPT_POST, true); // ?? as like case?
        } else {
            curl_setopt($curl, CURLOPT_CUSTOMREQUEST, $method);
        }

        if (("POST" || "PATH" || "PUT") === $method) {
            curl_setopt($curl, CURLOPT_RETURNTRANSFER, true);
        }

        curl_setopt($curl, CURLOPT_HEADER, true);

        /**
         * Setup header
         */
        if ((null != $header) && (count($header) > 0)) {
            curl_setopt($curl, CURLOPT_HTTPHEADER, $header);
        }

        /**
         * Setup body
         */
        if ((null != $body) && (count($body) > 0)) {
            $bodyAsJson = json_encode($body);
            curl_setopt($curl, CURLOPT_POSTFIELDS, $bodyAsJson);
        }
        /**
         * Response IT!
         */
        $curlResponse = curl_exec($curl);
        if (curl_error($curl)) {
            throw new ConnectorException("There are error(s) while invoking remote resource: {$method} {$url}");
        }
        /**
         * Close connection
         */
        curl_close($curl);
        /**
         * Build response object
         */
        if ($curlResponse === null) {
            throw new ConnectorException("The response is empty, and cannot be parse!");
        } else {
            try {
                list($curlResponseHeader, $curlBody) = explode("\r\n\r\n", $curlResponse, 2);
                list($curlHttpCode) = explode("\r", $curlResponseHeader, 2);

                $jsonCurlBody = json_decode($curlBody);
                $response = new Response($curlHttpCode, $jsonCurlBody, $curlResponseHeader);
                return $response;
            } catch (Exception $exc) {
                throw new ConnectorException("Error while parsing remote response! ");
            }
        }
    }

    /**
     * 
     * Build authentication data
     * @return string Authentication Token
     */
    public function getAuthentication() {

        $cookieData = $this->getCookieData();
        $token = "Barer xxx";

        if (is_array($cookieData) && $this->isValidCookieData($cookieData)) {
            $token = $this->buildTokenByCookie($cookieData);
        } else {
            //Request into OAuth server and get
        }
        return $token;
    }

    /**
     * Encode credintial into valid base64 string
     * 
     * @param type $clientId
     * @param type $secret
     * @return type
     * @throws ConnectorException
     */
    public function encodeCredential($clientId, $secret) {
        if (is_numeric($clientId) && !is_null($secret)) {
            //Combine client id and secret
            $cobinedString = $clientId . ":" . $secret;

            //Encode into base64
            $result = $this->base64url_encode(trim($cobinedString));

            return $result;
        } else {
            throw new ConnectorException("Client Id or secret are invalid!");
        }
    }

    public function base64url_encode($data) {
        return rtrim(strtr(base64_encode($data), '+/', '-_'), '=');
    }

    public function decodePaginator($paginator) {
        if (is_string($paginator)) {
            $arrayPaginator = (array)json_decode($paginator);
            if (is_array($arrayPaginator) &&
                    (count($arrayPaginator) > 0) &&
                    isset($arrayPaginator["limit"]) &&
                    isset($arrayPaginator["total"]) &&
                    isset($arrayPaginator["page"]) &&
                    isset($arrayPaginator["pages"]) &&
                    isset($arrayPaginator["offset"])) {
                return $arrayPaginator;
            } else {
                throw new ConnectorException("Invalid data in paginator!");
            }
        } else {
            throw new ConnectorException("Paginator should be a string!");
        }
    }

    /**
     * Get sesssion data @todo Refactor this method with better secure protection!
     * 
     * @param type $session_name
     * @param type $session_save_handler
     */
    public function getCookieData($name = null) {

        $cookieData = array();
        if (array_key_exists($name, $_COOKIE)) {
            try {
                $cookieData = (array) json_decode($_COOKIE[$name]);
            } catch (Exception $exc) {
                throw new ConnectorException("Cannot read cookie state!");
            }
        }
        return $cookieData;
    }

    /**
     * 
     * @param type $name
     */
    public function setCookieData($name, $access_token, $expires_in, $token_type) {
        try {
            $data = array(
                "access_token" => $access_token,
                "expires_in" => $expires_in,
                "token_type" => $token_type
            );
            setcookie($name, json_encode($data), time() + $expires_in);
            return $data;
        } catch (Exception $exc) {
            throw new ConnectorException("Error occurred during set cookie");
        }
    }

    /**
     * 
     * @param array $cookieData array of cookie data
     * @return type authentication string
     */
    public function buildTokenByCookie(array $cookieData) {
        return trim(ucfirst($cookieData["token_type"]) . " " . $cookieData["access_token"]);
    }

    /**
     * 
     * @param array $cookieData
     * @return boolean
     */
    public function isValidCookieData(array $cookieData) {
        if ((count($cookieData) > 0) &&
                isset($cookieData["access_token"]) &&
                isset($cookieData["expires_in"]) &&
                isset($cookieData["token_type"])) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 
     */
}
