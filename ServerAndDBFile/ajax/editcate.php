<?php
/**
 * Created by PhpStorm.
 * User: root
 * Date: 3/3/2019
 * Time: 12:14 PM
 */
require_once('../db.php');
$db = Db::getInstance();

if(isset($_POST['catenamenew'])&&isset($_POST['catefatherid'])){


    $member = $db->query("SELECT * FROM tbl_userweb WHERE token=:token", array(

        'token' => $_COOKIE['token'],

    ));

    if (count($member) == 1) {

        $db->modify("UPDATE `tbl_itemwall_cat` SET cat_name=:cat_name,cat_father=:cat_father WHERE cat_id=:cat_id", array(

            'cat_name' => $_POST['catenamenew'],
            'cat_father' => $_POST['catefatherid'],
            'cat_id'=>$_POST['oldcateId']
        ));

    } else {
        $respone = array("status" => 'token_expire');
        $respone = json_encode($respone, true);
        echo '<script>window.location.href = "index.php";</script>';
        exit();
    }

}