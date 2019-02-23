<?php
/**
 * Created by PhpStorm.
 * User: root
 * Date: 2/23/2019
 * Time: 12:56 PM
 */
require_once('db.php');
if(isset($_POST['token'])){
    $db = Db::getInstance();

    $userToken=$_POST['token'];
    $member = $db->query("SELECT * FROM tbl_itemwall_users WHERE token=:token", array(

        'token' => $userToken,

    ));
    if(count($member)== 1){
        $allItem = $db->query("SELECT * FROM tbl_itemwall_item" );
        $respone = array("status" => 'ok','Item'=>$allItem);
        $respone = json_encode($respone, true);
        echo $respone;

    }else{
        $respone = array("status" => 'token_expire');
        $respone = json_encode($respone, true);
        echo $respone;
    }





}else{
    $respone = array("status" => 'field_emp');
    $respone = json_encode($respone, true);
    echo $respone;
}