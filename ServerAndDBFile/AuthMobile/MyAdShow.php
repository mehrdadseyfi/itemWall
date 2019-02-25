<?php
/**
 * Created by PhpStorm.
 * User: root
 * Date: 2/25/2019
 * Time: 9:57 AM
 */
require_once('../db.php');
if (isset($_POST['token'])) {
    $db = Db::getInstance();

    $userToken = $_POST['token'];
    $member = $db->query("SELECT * FROM tbl_itemwall_users WHERE token=:token", array(

        'token' => $userToken,

    ));
    if (count($member) == 1) {

        $allItem = $db->query("SELECT * FROM tbl_itemwall_item WHERE user_id=:user_id", array(

            'user_id' => $member[0]['user_id']
        ));
        $respone = array("status" => 'ok', 'Item' => $allItem);
        $respone = json_encode($respone, true);
        echo $respone;

    } else {
        $respone = array("status" => 'token_expire');
        $respone = json_encode($respone, true);
        echo $respone;
    }


} else {
    $respone = array("status" => 'field_emp');
    $respone = json_encode($respone, true);
    echo $respone;

}