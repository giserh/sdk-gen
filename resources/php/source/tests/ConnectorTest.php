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
        $stub = $this->getMockForAbstractClass("IsaaCloud\Connector", array("https://api.isaacloud.com/", "https://oauth.isaacloud.com/", "1.0.0", array(
                "clientId" => 123,
                "secret" => 123
        )));
        
        $stub->expects($this->any())
                ->method('curlIt')
                ->will($this->returnValue(2));
        $stub->callService($uri, $httpMethod, $parameters, $body);
    }

}
