<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

class HttpMethods extends SplEnum {

    const GET = "GET";
    const POST = "POST";
    const PUT = "PUT";
    const PATH = "PATH";
    const OPTIONS = "OPTIONS";

}

/**
 * Description of SDK
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

class NotFoundException extends Exception {
    
}

class AccessDeniedException extends Exception {
    
}

/**
 * All error code
 */
abstract class Connector {

    private $baseURL;
    private $apiVersion;
    private $OAuth2;
    private $contentType = "application/json charset=utf-8";

    /**
     * This method provide low level REST-call mechnism
     * 
     * @param type $uri - the relative url addr
     * @param type $httpMethod - http method in the filed of GET, POST, PUT, OPTIONS, or PATH
     * @param type $parameters - array of request parameters
     * @param type $callback - optional anonymous function
     * 
     * @return Response Response value object
     */
    public function callService($uri, $httpMethod, $parameters, $callback = null) {
        /**
         * Get method type from string
         */
        $method = new HttpMethods(strtoupper($httpMethod));

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
        $body = array();
        
        /**
         * Curl it!
         */
        curlIt($header, $method, $url, $body);
        function curlIt($header, $method, $url, $body) {
            
        }

        /**
         * Responder data object
         */
        $response == new Response(200, array(), array());
        if ((is_string($callback) && function_exists($callback)) || (is_object($callback) && ($callback instanceof Closure))) {
           return $callback($response);
        }else{
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
