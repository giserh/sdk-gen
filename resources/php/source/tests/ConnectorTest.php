<?php

class ConnectorTest extends PHPUnit_Framework_TestCase {

    public function constructorValidProvider() {
        $dataProvider = array(
            array(
                "https://api.isaacloud.com/",
                "https://oauth.isaacloud.com/",
                "1.0.1",
                array(
                    "clientId" => 123,
                    "secret" => 123
                ))
        );
        return $dataProvider;
    }

    public function callServiceValidProvider() {
        $dataProvider = array(
            array(
                "/resource/{userId}",
                "get",
                array("{userId}" => 123),
                array()
            )
        );
        return $dataProvider;
    }

    /**
     * @dataProvider constructorValidProvider
     */
    public function testValidConstructor($baseApiPath, $baseOauthPath, $version, $configuration) {
        $stub = $this->getMockForAbstractClass("IsaaCloud\Connector", array($baseApiPath, $baseOauthPath, $version, $configuration));
        $this->assertEquals($stub->getClientId(), $configuration["clientId"]);
        $this->assertEquals($stub->getSecret(), $configuration["secret"]);
        $this->assertEquals($stub->getBaseOuathUrl(), $baseOauthPath);
        $this->assertEquals($stub->getBaseApiUrl(), $baseApiPath);
        $this->assertEquals($stub->getVersion(), $version);
    }

    /**
     * @dataProvider callServiceValidProvider
     */
    public function testCallService($uri, $httpMethod, $parameters, $body) {

        $args = array(
            "https://api.isaacloud.com",
            "https://oauth.isaacloud.com",
            "1.0.0",
            array(
                "clientId" => 123,
                "secret" => 123
        ));
        /**
         * Build Mock
         */
        $stub = $this->getMockBuilder("IsaaCloud\Connector")
                ->setMethods(array("curlIt"))
                ->setConstructorArgs($args)
                ->getMockForAbstractClass();





        $responder = new IsaaCloud\Response(200, array(), array());
        
        /**
         * Set up callback function
         */
        $callback = function($header, $method, $url, $body = null) use($responder) {
            //Assert that has been defined keys
            $this->assertArrayHasKey("Authentication", $header);
            $this->assertArrayHasKey("Content-type", $header);
            
            //Assert that url is valid
            if((filter_var($url, FILTER_VALIDATE_URL) == false)){
                $this->fail("{$url} url is not valid!");
            }

            //Assert method is valid
            $this->assertTrue(in_array($method, array("GET","POST","PUT","PATH","OPTIONS","DELETE")));

            if(in_array($method, array("GET","DELETE"))){
                $this->assertNull($body);
            }
            
            return $responder;
        };
        /**
         * Set up excepts
         */
        $stub->expects($this->any())
                ->method('curlIt')
                ->will($this->returnCallback($callback));

        /**
         * Call into orginal method
         */
        $response = $stub->callService($uri, $httpMethod, $parameters, $body);

        $this->assertEquals($response, $responder);
    }

}
