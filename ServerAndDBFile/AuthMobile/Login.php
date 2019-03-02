<?php
/**
 * Created by PhpStorm.
 * User: root
 * Date: 2/20/2019
 * Time: 9:56 AM
 */
require_once('../db.php');


if(isset($_POST['mobile'])&&isset($_POST['password'])){
    $username=$_POST['mobile'];
    $password=md5($_POST['password']);
    $db = Db::getInstance();

    //checkUserExist
    $member = $db->query("SELECT * FROM tbl_itemwall_users WHERE username=:username", array(

        'username' => $username,

    ));

    if(count($member)== 1){

        //checkPass
        $memberPass = $db->query("SELECT * FROM tbl_itemwall_users WHERE username=:username AND password=:password", array(

            'username' => $username,
            'password'=> $password

        ));

        if(count($memberPass)== 1){

            //insertToken
            $characters = '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ';
            $charactersLength = strlen($characters);
            $randomString = '';
            $length=32;
            for ($i = 0; $i < $length; $i++) {
                $randomString .= $characters[rand(0, $charactersLength - 1)];
            }
            $token=$randomString;

            $db->modify("UPDATE tbl_itemwall_users SET token=:token WHERE username=:username", array(
                'token' => $token,
                'username' => $username,

            ));


            $respone=array("status"=>'user_log_in','token'=>$token);
            $respone=json_encode($respone,true);
            echo $respone;
        } else{

            $respone=array("status"=>'password_wrong');
            $respone=json_encode($respone,true);
            echo $respone;


        }



    }

    else{
        $respone=array("status"=>'user_not_exist');
        $respone=json_encode($respone,true);
        echo $respone;

    }


}else{

    $respone=array("status"=>'field_emp');
    $respone=json_encode($respone,true);
    echo $respone;
}
