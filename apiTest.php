<?php

include "isaacloud.php";
//echo "Started\n";
//phpinfo();

$config = [
    "clientId" => "2",
    "secret" => "98bb5d47c897b8aef0ce2b5af38f5af7",
];

$foo = new \IsaaCloud\Cache($config); 

$foo->getUsers();

?>
