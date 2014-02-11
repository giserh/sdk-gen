<?php

namespace IsaaCloud;

/**
 * Response object
 *
 */
class Response {

    public function __construct($code, $body, $header) {
        if (!is_numeric($code)) {
            throw new ConnectorException("Code should be valid numeric!");
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

    private $baseOAuthUrl = null;
    private $baseApiUrl = null;
    private $version = null;
    private $contentType = "application/json charset=utf-8";
    private $methods = array("GET", "POST", "PUT", "PATH", "OPTIONS");
	private $cookieName = "test";

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
     * @param string $baseOAuthUrl Base url path to authenticate server
     * @param string $version Version compatible API (in pattern x.y.z)
     * @param array $config Array of configutation
     */
    public function __construct($baseApiUrl, $baseOAuthUrl, $version, $config) {

        if ((filter_var($baseApiUrl, FILTER_VALIDATE_URL) == true)) {
            $this->baseApiUrl = $baseApiUrl;
        } else {
            throw new ConnectorException("{$baseApiUrl} is invalid url!");
        }

        if ((filter_var($baseOAuthUrl, FILTER_VALIDATE_URL) == true)) {
            $this->baseOAuthUrl = $baseOAuthUrl;
        } else {
            throw new ConnectorException("{$baseOAuthUrl} is invalid url!");
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
            "Authorization" => $this->getAuthentication()
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

    public function getBaseOAuthUrl() {
        return $this->baseOAuthUrl;
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
			$bodyquery = http_build_query($body,null,'&');
            curl_setopt($curl, CURLOPT_POSTFIELDS, $bodyquery);
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

                $jsonCurlBody = json_decode($curlBody, true);
				
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
	 * Firstly check cookie data, if cookie not exists or is invalid - make request to oauth server, to obtain token
	 * At the end build token string
     * @return string Authentication Token
     */
    public function getAuthentication() {

        $cookieData = $this->getCookieData();
        $token = "Bearer 6edcd1bd25e0b798ccc8523f7e3e9c5e";
		
		if( !is_array($cookieData) || !$this->isValidCookieData($cookieData)) {
			//Request into OAuth server and get authentication token
			try {
				$token = $cookieData = $this->obtainOAuthToken();
				if( !is_array($token) ) return null; // if invalid token data, return null
			} catch(Exception $e) {
				return null; // if some exception catched, return null
			}
		}
        
		// build token string
        $token = $this->buildTokenByCookie($cookieData);
        return $token;
    }
	
	/**
	 * Create request into OAuth server, to get authentication token
	 * @return string Authentication Token
	 * @throws ConnectorException
	 */
	public function obtainOAuthToken($authMethod = "Basic") {
			
		// Build http headers
		$header = array(
			$authMethod . " " . $this->encodeCredential($this->clientId, $this->secret),
		);
		
		// set http method
		$method = "POST";
		
		// prepare url to call
		$url = $this->baseOAuthUrl . "/token";
		
		// set http fields - grant type for obtaining oauth token	
		$body = array(
			"grant_type" => "client_credentials",
		);
		
		// curlIt to get response from oauth server
		$response = $this->curlIt($header, $method, $url, $body);
		
		// get body
		$body = $response->getBody();
		
		// if body contains good token data create token, and return it
		if( isset($body["token_type"]) && isset($body["expires_in"]) && isset($body["access_token"]) ) {
			$token = array(
				"token_type" => $body["token_type"],
				"expires_in" => $body["expires_in"],
				"access_token" => $body["access_token"]
			);
			
			return $token;
		} elseif( isset($body["error"]) ) {
			// Throw new exception with oauth error
			throw new ConnectorException("Error while obtaining OAuth Token: " . $body["error"], $response->getCode());
		} else {
			// TODO: Check http response headers and throw exception with error that occured
			throw new ConnectorException("Unknown error while obtaining OAuth Token", $response->getCode());
		}
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

    /**
     * Encode base64url
     * @param type $data
     * @return type
     */
    public function base64url_encode($data) {
        return rtrim(strtr(base64_encode($data), '+/', '-_'), '=');
    }

    public function decodePaginator($paginator) {
        if (is_string($paginator)) {
            $arrayPaginator = (array) json_decode($paginator);
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
     */
    public function getCookieData() {
		
		$name = $this->cookieName;
		
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
     * @param string $access_token
	 * @param integer $expires_in
	 * @param srting $token_type
     */
    public function setCookieData($access_token, $expires_in, $token_type) {
        $name = $this->cookieName;
		
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

}
