<?php

namespace IsaaCloud;

/**
 * 
 */

/**
 * Response object
 *
 */
class Response {

    public function __construct($code, $body, $header) {
        $this->code = $code;
        $this->header = $header;
        $this->body = $body;
    }

    private $code;
    private $body;
    private $header;
    private $paginator;

    public function getCode() {
        return $this->code;
    }

    public function getBody() {
        return $this->body;
    }

    public function getHeader() {
        return $this->header;
    }

}

class NotFoundException extends \Exception {
    
}

class AccessDeniedException extends \Exception {
    
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
    public function merge($string,$parameters){
        
        $mergedString = "";
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


        $url = "https://test.com/";

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

    public function getToken() {
        return "xxx";
    }

    /**
     * Build and call request with CURL
     * 
     * @param array $header 
     * @param string $method HTTP method
     * @param type $url
     * @param array $body
     */
    public function curlIt($header, $method, $url, $body = null) {
        return new Response(200, array(), array());
    }

    /**
     * 
     * Build authentication data
     * @return string Authentication Token
     */
    public function getAuthentication() {
        return "Barer {$this->getToken()}";
    }

}
