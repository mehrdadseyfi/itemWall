<?php
/**
 * Created by PhpStorm.
 * User: root
 * Date: 2/25/2019
 * Time: 12:29 PM
 */
require_once('SmSSender/MehApi.php');
require_once('../db.php');

if (isset($_POST['mobile'])) {
    $db = Db::getInstance();

    $username = $_POST['mobile'];

    $member = $db->query("SELECT * FROM tbl_itemwall_users WHERE username=:username AND full_name IS NOT NULL ", array(

        'username' => $username,

    ));
    if (count($member )==0) {

        $characters = '123456789';
        $charactersLength = strlen($characters);
        $randomString = '';
        for ($i = 0; $i < 4; $i++) {
            $randomString .= $characters[rand(0, $charactersLength - 1)];
        }
        $code= $randomString;

        $memberMobile = $db->query("SELECT * FROM tbl_itemwall_users WHERE username=:username  ", array(

            'username' => $username,

        ));
        if (count($memberMobile )==0) {

            $db->insert("INSERT INTO tbl_itemwall_users (username,code) VALUES (:username,:code)", array(

                'code' => $code,
                'username' => $username,

            ));
        }else{

            $db->modify("UPDATE sabtenam SET code=:code WHERE username=:username", array(

                'code' => $code,
                'username' => $username,

            ));
        }
        $sms=new SendSms();
        $sms->smsSend($username,$code);

        $respone = array("status" => 'code_send');
        $respone = json_encode($respone, true);
        echo $respone;

    } else {

        $respone = array("status" => 'user_exist');
        $respone = json_encode($respone, true);
        echo $respone;
    }


} else {

    $respone = array("status" => 'field_emp');
    $respone = json_encode($respone, true);
    echo $respone;
}
