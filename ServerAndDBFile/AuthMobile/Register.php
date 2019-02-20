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
    $db = Db::getInstance();
    //checkUserExist
    $member = $db->query("SELECT * FROM tbl_itemwall_users WHERE username=:username", array(

        'username' => $username,

    ));

    if(count($member)== 0){

        $db->insert("INSERT INTO tbl_itemwall_users (username,email,full_name,password) VALUES (:username,:email,:name,:password)", array(

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