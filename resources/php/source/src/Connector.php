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
    private $options = array();
    
    private $token;
    private $contentType = "application/json charset=utf-8";
    private $methods = array("GET", "POST", "PUT", "PATH", "OPTIONS");

    /**
     * Constructor of connector, set up all connection parameters
     * 
     * @param string $baseApiUrl Base url path to api server
     * @param string $baseOuathUrl Base url path to authenticate server
     * @param string $version Version compatible API (in pattern x.y.z)
     * @param array $options Optional array of parameters
     */
    public function __construct($baseApiUrl, $baseOuathUrl, $version, $options = array()) {
        $this->baseApiUrl = $baseApiUrl;
        $this->baseOuathUrl = $baseOuathUrl;
        $this->version = $version;
        $this->options = $options;
        
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
    public function callService($uri, $httpMethod, $parameters, $body = array(), $callback = null) {
        /**
         * Get method type from string
         */
        if (!in_array($httpMethod, $this->methods)) {
            throw new \ConnectorException("Invalid http method");
        }

        /**
         * Build authentication data
         */
        $authentication = "Barer {$this->getToken()}";

        /**
         * Build request header
         */
        $header = array(
            "Content-type" => $this->contentType,
            "Authentication" => $authentication
        );

        $url = null;
        /**
         * Curl it!
         */
        curlIt($header, $method, $url, $body);

        function curlIt($header, $method, $url, $body) {
            
        }

        /**
         * Responder data object
         */
        $response = new Response(200, array(), array());

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
     * Get token
     */
    private function getToken() {
        return "xxx";
    }

    private function getScope() {
        
    }

}
