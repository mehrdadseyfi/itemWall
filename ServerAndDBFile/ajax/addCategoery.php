<?php
/**
 * Created by PhpStorm.
 * User: root
 * Date: 3/3/2019
 * Time: 11:53 AM
 */
require_once('../db.php');
$db = Db::getInstance();

if(isset($_POST['catename'])&&isset($_POST['catefatherid'])){


    $member = $db->query("SELECT * FROM tbl_userweb WHERE token=:token", array(

        'token' => $_COOKIE['token'],

    ));

    if (count($member) == 1) {


        $db->insert("INSERT INTO tbl_itemwall_cat (cat_name,cat_father) VALUES (:cat_name,:cat_father)", array(

            'cat_name' => $_POST['catename'],
            'cat_father' => $_POST['catefatherid'],

        ));

    } else {
        $respone = array("status" => 'token_expire');
        $respone = json_encode($respone, true);
        echo '<script>window.location.href = "index.php";</script>';
        exit();
    }

}