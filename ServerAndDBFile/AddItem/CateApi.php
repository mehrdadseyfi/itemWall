<?php
/**
 * Created by PhpStorm.
 * User: User
 * Date: 22/02/2019
 * Time: 06:54 PM
 */
require_once('../db.php');
if(isset($_POST['token'])){
    $userToken=$_POST['token'];
    $member = $db->query("SELECT * FROM tbl_itemwall_users WHERE token=:token", array(

        'token' => $userToken,

    ));
    if(count($member)== 1){
        $catApi = $db->query("SELECT * FROM tbl_itemwall_cat" );
        echo $catApi;

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