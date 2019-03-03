<?php
/**
 * Created by PhpStorm.
 * User: root
 * Date: 3/3/2019
 * Time: 12:46 PM
 */
require_once('../db.php');
$db = Db::getInstance();

if(isset($_POST['userid'])){


    $member = $db->query("SELECT * FROM tbl_userweb WHERE token=:token", array(

        'token' => $_COOKIE['token'],

    ));

    if (count($member) == 1) {

        $db->query("DELETE FROM `tbl_itemwall_users`  WHERE user_id=:user_id", array(

            'user_id' => $_POST['userid'],

        ));

    } else {
        $respone = array("status" => 'token_expire');
        $respone = json_encode($respone, true);
        echo '<script>window.location.href = "index.php";</script>';
        exit();
    }

}