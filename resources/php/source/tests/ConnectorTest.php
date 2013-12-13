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

    /**
     * @dataProvider constructorValidProvider
     */
    public function testConstructor($baseApiPath, $baseOauthPath, $version, $configuration) {
        $stub = $this->getMockForAbstractClass("IsaaCloud\Connector", array($baseApiPath, $baseOauthPath, $version, $configuration));
        $this->assertEquals($stub->getClientId(), $configuration["clientId"]);
        $this->assertEquals($stub->getSecret(), $configuration["secret"]);
        $this->assertEquals($stub->getBaseOuathUrl(), $baseOauthPath);
        $this->assertEquals($stub->getBaseApiUrl(), $baseApiPath);
        $this->assertEquals($stub->getVersion(), $version);
    }

}
