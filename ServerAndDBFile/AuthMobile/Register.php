<?php
/**
 * Created by PhpStorm.
 * User: root
 * Date: 2/20/2019
 * Time: 1:28 PM
 */
require_once('../db.php');

if (isset($_POST['mobile'])&&isset($_POST['password'])&&isset($_POST['full_name'])&&isset($_POST['email'])){
    $username = $_POST['mobile'];
    $name = $_POST['full_name'];
    $email = $_POST['email'];
    $password = md5($_POST['password']);
    $code = md5($_POST['password']);
    $db = Db::getInstance();
    //checkUserExist
    $member = $db->query("SELECT * FROM tbl_itemwall_users WHERE username=:username AND code=:code", array(

        'username' => $username,
        'code' => $code,

    ));

    if(count($member)== 1){

        $db->modify("UPDATE sabtenam SET email=:email,name=:name,password=:password, WHERE username=:username", array(

            'username' => $username,
            'email' => $email,
            'name' => $name,
            'password' => $password,

        ));
        $respone=array("status"=>'user_register');
        $respone=json_encode($respone,true);
        echo $respone;



    }

    else{
        $respone=array("status"=>'user_exist');
        $respone=json_encode($respone,true);
        echo $respone;

    }

}else {
    $respone = array("status" => 'field_emp');
    $respone = json_encode($respone, true);
    echo $respone;
}