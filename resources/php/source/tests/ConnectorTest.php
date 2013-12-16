<?php

/**
 * @author asikorski
 */
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

        /**
         * Setup constructor parameters
         */
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

        /**
         * Expected responder
         */
        $responder = new IsaaCloud\Response(200, array(), array());

        /**
         * Set up callback function
         */
        $callback = function(array $header, $method, $url, array $body = null) use($responder) {
            //Assert that has been defined keys
            $this->assertArrayHasKey("Authentication", $header);
            $this->assertArrayHasKey("Content-type", $header);

            //Assert that url is valid
            if ((filter_var($url, FILTER_VALIDATE_URL) == false)) {
                $this->fail("{$url} url is not valid!");
            }

            //Assert method is valid
            $this->assertTrue(in_array($method, array("GET", "POST", "PUT", "PATH", "OPTIONS", "DELETE")));

            if (in_array($method, array("GET", "DELETE"))) {
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

    /**
     * Test get authentication string
     */
    public function testGetAuthentication($type = "Barer") {
        $args = array(
            "https://api.isaacloud.com",
            "https://oauth.isaacloud.com",
            "1.0.0",
            array(
                "clientId" => 123,
                "secret" => 123
        ));
        /**
         * Build mock object
         */
        $stub = $this->getMockBuilder("IsaaCloud\Connector")
                ->setConstructorArgs($args)
                ->getMockForAbstractClass();

        $authentcationString = $stub->getAuthentication();

        $this->assertNotNull($authentcationString);
        $this->assertNotEmpty($authentcationString);
    }

    public function mergeProvider() {
        $dataProvider = array(
            array(
                "/resource/{test1}",
                array(
                    "{test1}" => 1),
                "/resource/1"
            ),
            array(
                "/resource/{test1}/resource2/{test2}",
                array(
                    "{test1}" => 1,
                    "{test2}" => 2),
                "/resource/1/resource2/2"
            ),
            array(
                "/resource/{test1}/resource2/{test2}/resource3/{test3}",
                array(
                    "{test1}" => 1,
                    "{test2}" => 2,
                    "{test3}" => 3),
                "/resource/1/resource2/2/resource3/3"
            ),
            array(
                "/resource/1",
                array(),
                "/resource/1"
            )
            ,
            array(
                "/resource/{test1}/resource2/{test2}",
                array(
                    "{test1}" => "string",
                    "{test2}" => "string2"),
                "/resource/string/resource2/string2"
            )
        );
        return $dataProvider;
    }

    /**
     * @dataProvider mergeProvider
     */
    public function testMerge($string, $parameters, $expected) {
        $args = array(
            "https://api.isaacloud.com",
            "https://oauth.isaacloud.com",
            "1.0.0",
            array(
                "clientId" => 123,
                "secret" => 123
        ));
        /**
         * Build mock object
         */
        $stub = $this->getMockBuilder("IsaaCloud\Connector")
                ->setConstructorArgs($args)
                ->getMockForAbstractClass();

        /**
         * TestIt!
         */
        $mergedString = $stub->merge($string, $parameters);
        $this->assertNotNull($mergedString);
        $this->assertEquals($mergedString, $expected);
    }
    /**
     * Test get session mechanism
     */
    public function testGetSessionData(){
        
    }
    


}
