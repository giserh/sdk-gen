<?php

/**
 * @author asikorski
 */
class ConnectorTest extends PHPUnit_Framework_TestCase {
	
	private $testBaseApiUrl = "http://api.dev.isaacloud.com";
	private $testBaseOAuthUrl = "http://oauth.dev.isaacloud.com";
	private $testVersion = "v1";
	private $testClientId = "123";
	private $testSecret = "123";
	

    public function constructorValidProvider() {
        $dataProvider = array(
            array(
                "{$this->testBaseApiUrl}",
                "{$this->testBaseOAuthUrl}",
                "{$this->testVersion}",
                array(
                    "clientId" => $this->testClientId,
                    "secret" => $this->testSecret,
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
        $this->assertEquals($stub->getBaseOAuthUrl(), $baseOauthPath);
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
            "{$this->testBaseApiUrl}",
            "{$this->testBaseOAuthUrl}",
            "{$this->testVersion}",
            array(
                "clientId" => $this->testClientId,
                "secret" => $this->testSecret,
        ));
        /**
         * Build Mock
         */
        $stub = $this->getMockBuilder("IsaaCloud\Connector")
                ->setMethods(array("curlIt", "getAuthentication"))
                ->setConstructorArgs($args)
                ->getMockForAbstractClass();
		
        /**
         * Expected responder
         */
        $responder = new IsaaCloud\Response(200, array(), array());

        /**
         * Set up callback function
         */
        $test = $this;
        $callback = function(array $header, $method, $url, array $body = null) use($responder, $test) {
            //Assert that has been defined keys
            
            $test->assertArrayHasKey("Authorization", $header);
            $test->assertArrayHasKey("Content-type", $header);

            //Assert that url is valid
            if ((filter_var($url, FILTER_VALIDATE_URL) == false)) {
                $test->fail("{$url} url is not valid!");
            }

            //Assert method is valid
            $test->assertTrue(in_array($method, array("GET", "POST", "PUT", "PATH", "OPTIONS", "DELETE")));

            if (in_array($method, array("GET", "DELETE"))) {
                $test->assertNull($body);
            }
			
            return $responder;
        };
        /**
         * Set up excepts
         */
        $stub->expects($this->any())
                ->method('curlIt')
                ->will($this->returnCallback($callback));
				
		$stub->expects($this->any())
				->method('getAuthentication')
				->will($this->returnValue("Bearer 6edcd1bd25e0b798ccc8523f7e3e9c5e"));

        /**
         * Call into orginal method
         */
        $response = $stub->callService($uri, $httpMethod, $parameters, $body);

        $this->assertEquals($response, $responder);
    }

	public function getAuthenticationProvider() {
		$dataProvider = array(
			array(
				array(
					"token_type" => "Bearer",
					"expires_in" => "3600",
					"access_token" => "965c8d4d29717ad3ad8e82447c55b1e"
				),
			),
			array(
				array(
					"token_type" => "Bearer",
					"expires_in" => "1800",
					"access_token" => "47c55b8d4d2965c3ad8e8249717ad1e"
				),
			),
			array(
				array(
					"token_type" => "Basic",
					"expires_in" => "3600",
					"access_token" => "717ad3ad899e824e65c8d4d47c55b12"
				),
			),
			array(
				array(
					"error" => "Invalid client",
				),
				false
			)
		);
		
		return $dataProvider;
	}

    /**
     * Test get authentication string
	 * @dataProvider getAuthenticationProvider
     */
    public function testGetAuthentication($token, $tokenOk = true) {
        $args = array(
            "{$this->testBaseApiUrl}",
            "{$this->testBaseOAuthUrl}",
            "{$this->testVersion}",
            array(
                "clientId" => $this->testClientId,
                "secret" => $this->testSecret,
        ));
        /**
         * Build mock object
         */
        $stub = $this->getMockBuilder("IsaaCloud\Connector")
				->setMethods(array("obtainOAuthToken","setCookieData"))
                ->setConstructorArgs($args)
                ->getMockForAbstractClass();
		
		$test = $this;
		$callback = function() use($token, $test, $tokenOk) {
			
			if( $tokenOk ) {
				$test->assertArrayHasKey("token_type", $token);
				$test->assertArrayHasKey("expires_in", $token);
				$test->assertArrayHasKey("access_token", $token);
				
				return $token;
			} else {
				$test->assertArrayHasKey("error", $token);
				return null;
			}
		};
        /**
         * Set up excepts
         */
        $stub->expects($this->any())
                ->method("obtainOAuthToken")
                ->will($this->returnCallback($callback));
		
		$callbackSetCookie = function() use($token) {
			$_COOKIE["test"] = json_encode($token);
			return $token;
		};
		
		$stub->expects($this->any())
				->method("setCookieData")
				->will($this->returnCallback($callbackSetCookie));

        $authentcationString = $stub->getAuthentication();
		
		if( $tokenOk ) {
			$this->assertNotNull($authentcationString);
        	$this->assertNotEmpty($authentcationString);
		}
		else {
			$this->assertNull($authentcationString);
		}
    }
	
	public function obtainOAuthTokenProvider() {
		$dataProvider = array(
			array(
				200,
				array(
					"token_type" => "Bearer",
					"expires_in" => "3600",
					"access_token" => "965c8d4d29717ad3ad8e82447c55b1e"
				),
				array(),
			),
			array(
				403,
				array(
					"error" => "Invalid client",
				),
				array(),
				new Exception("Error while obtaining OAuth Token: Invalid client", 403)
			)
		);
		
		return $dataProvider;
	}

	/**
     * Test obtain oauth token error
	 * @dataProvider obtainOAuthTokenProvider
     */
    public function testObtainOAuthToken($code, $body, $headers, $expectedException = null) {
        $args = array(
            "{$this->testBaseApiUrl}",
            "$this->testBaseOAuthUrl",
            "{$this->testVersion}",
            array(
                "clientId" => $this->testClientId,
                "secret" => $this->testSecret,
        ));
        
        $responder = new IsaaCloud\Response($code, $body, $headers);
				
        /**
         * Build mock object
         */
		$stub = $this->getMockBuilder("IsaaCloud\Connector")
				->setMethods(array("curlIt"))
                ->setConstructorArgs($args)
                ->getMockForAbstractClass();
			
		/**
         * Set up callback function
         */
        $callback = function(array $header, $method, $url, array $body = null) use($responder) {
            return $responder;
        };
        /**
         * Set up expects
         */
        $stub->expects($this->any())
                ->method("curlIt")
                ->will($this->returnCallback($callback));
		
		try {
        	$token = $stub->obtainOAuthToken();
	        $this->assertNotNull($token);
	        $this->assertNotEmpty($token);
			$this->assertArrayHasKey("token_type", $token);
			$this->assertArrayHasKey("expires_in", $token);
			$this->assertArrayHasKey("access_token", $token);
		} catch( Exception $e ) {
			$expectedCode = $expectedException->getCode();
			$expectedMsg = $expectedException->getMessage();
			
			if( $expectedCode ) $this->assertEquals($e->getCode(), $expectedCode);
			if( $expectedMsg ) $this->assertEquals($e->getMessage(), $expectedMsg);
		}
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
            "{$this->testBaseApiUrl}",
            "{$this->testBaseOAuthUrl}",
            "{$this->testVersion}",
            array(
                "clientId" => $this->testClientId,
                "secret" => $this->testSecret,
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
    public function testGetSessionData() {
        $args = array(
            "{$this->testBaseApiUrl}",
            "{$this->testBaseOAuthUrl}",
            "{$this->testVersion}",
            array(
                "clientId" => $this->testClientId,
                "secret" => $this->testSecret,
        ));
        /**
         * Prepare session and cookie
         */
        /**
         * Build mock object
         */
        $stub = $this->getMockBuilder("IsaaCloud\Connector")
                ->setConstructorArgs($args)
                ->getMockForAbstractClass();

        $cookieName = "test";
        $mockArray = array(
            "access_token" => "token_3432",
            "expires_in" => 3600,
            "token_type" => 45465334
        );
        /**
         * Setup cookie, only in global variable!
         */
        $_COOKIE[$cookieName] = json_encode($mockArray);


        $sessionData = $stub->getCookieData();

        $this->assertNotNull($sessionData);

        $this->assertArrayHasKey("access_token", $sessionData, "There is not defined access_token");
        $this->assertArrayHasKey("expires_in", $sessionData, "There is not defined expires_in");
        $this->assertArrayHasKey("token_type", $sessionData, "There is not defined token_type");

        $this->assertGreaterThan(0, $sessionData["expires_in"]);
    }

    public function testIsValidToken() {
        $args = array(
            "{$this->testBaseApiUrl}",
            "{$this->testBaseOAuthUrl}",
            "{$this->testVersion}",
            array(
                "clientId" => $this->testClientId,
                "secret" => $this->testSecret,
        ));
        /**
         * Prepare session and cookie
         */
        /**
         * Build mock object
         */
        $mock = $this->getMockBuilder("IsaaCloud\Connector")
                ->setConstructorArgs($args)
                ->getMockForAbstractClass();

        /**
         * Prepare cookie data
         */
        $validCookieData = array(
            "access_token" => "token_3432",
            "expires_in" => 3600,
            "token_type" => 45465334
        );
        $response = $mock->isValidCookieData($validCookieData);
        $this->assertTrue($response);
    }

    public function testBuildTokenByCookie() {
        $args = array(
            "{$this->testBaseApiUrl}",
            "{$this->testBaseOAuthUrl}",
            "{$this->testVersion}",
            array(
                "clientId" => $this->testClientId,
                "secret" => $this->testSecret,
        ));
        /**
         * Prepare session and cookie
         */
        /**
         * Build mock object
         */
        $mock = $this->getMockBuilder("IsaaCloud\Connector")
                ->setConstructorArgs($args)
                ->getMockForAbstractClass();

        $validCookieData = array(
            "access_token" => "token_3432",
            "expires_in" => 3600,
            "token_type" => 45465334
        );

        $result = $mock->buildTokenByCookie($validCookieData);

        $this->assertNotNull($result);
    }

    /**
     * 
     */
    public function testSetCookieData() {
        
    }

    public function credentialProvider() {
        $data = array(
            array(
                "123",
                "password",
                "MTIzOnBhc3N3b3Jk"
            ),
            array(
                "123456790",
                "password",
                "MTIzNDU2NzkwOnBhc3N3b3Jk"
            ),
            array(
                "123456790",
                "password",
                "MTIzNDU2NzkwOnBhc3N3b3Jk"
            ),
            array(
                "123456790",
                "!t6qggR%{yYTd",
                "MTIzNDU2NzkwOiF0NnFnZ1Ile3lZVGQ"
            )
        );
        return $data;
    }

    /**
     * @dataProvider credentialProvider
     */
    public function testEncodeCredential($clientId, $secret, $encoded) {
        $args = array(
            "{$this->testBaseApiUrl}",
            "{$this->testBaseOAuthUrl}",
            "{$this->testVersion}",
            array(
                "clientId" => $this->testClientId,
                "secret" => $this->testSecret,
        ));
        /**
         * Prepare session and cookie
         */
        $mock = $this->getMockBuilder("IsaaCloud\Connector")
                ->setConstructorArgs($args)
                ->getMockForAbstractClass();
        /**
         * Build mock object
         */
        $encodedString = $mock->encodeCredential($clientId, $secret);

        $this->assertEquals($encodedString, $encoded, "encoded string is invalid!");
    }

    public function testDecodePaginator() {
        $args = array(
            "{$this->testBaseApiUrl}",
            "{$this->testBaseOAuthUrl}",
            "{$this->testVersion}",
            array(
                "clientId" => $this->testClientId,
                "secret" => $this->testSecret,
        ));
        /**
         * Prepare session and cookie
         */
        $mock = $this->getMockBuilder("IsaaCloud\Connector")
                ->setConstructorArgs($args)
                ->getMockForAbstractClass();

        $pgr = array(
            "limit" => 10,
            "offset" => 20,
            "total" => 100,
            "page" => 10,
            "pages" => 10
        );

        /**
         * Build mock paginator
         */
        $paginator = $mock->decodePaginator(json_encode($pgr));

        /**
         * Array has key
         */
        $this->assertArrayHasKey("limit", $paginator);
        $this->assertArrayHasKey("total", $paginator);
        $this->assertArrayHasKey("page", $paginator);
        $this->assertArrayHasKey("pages", $paginator);
        $this->assertArrayHasKey("offset", $paginator);
    }

    public function responseProvider() {
        $provider = array(
            array(
                200,
                array(),
                array()
            ),
            array(
                200,
                array(),
                array()
            ),
            array(
                200,
                array(),
                array()
            )
        );

        return $provider;
    }

    /**
     * @dataProvider responseProvider
     */
    public function testResponse($code, array $body, array $header) {
        $mock = new \IsaaCloud\Response($code,$body,$header);
        
        $this->assertEquals($mock->getCode(), $code);
        $this->assertEquals($mock->getBody(), $body);
        $this->assertEquals($mock->getHeader(), $header);
    }

}
