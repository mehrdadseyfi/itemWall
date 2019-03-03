<?php
/**
 * Created by PhpStorm.
 * User: root
 * Date: 3/3/2019
 * Time: 1:35 PM
 */
require_once('../db.php');
$db = Db::getInstance();

if(isset($_POST['itemid'])&&isset($_POST['status'])){


    $member = $db->query("SELECT * FROM tbl_userweb WHERE token=:token", array(

        'token' => $_COOKIE['token'],

    ));

    if (count($member) == 1) {



        $db->modify("UPDATE `tbl_itemwall_item` SET item_status=:item_status WHERE item_id=:item_id", array(

            'item_id' => $_POST['itemid'],
            'item_status' => $_POST['status'],
        ));

    } else {
        $respone = array("status" => 'token_expire');
        $respone = json_encode($respone, true);
        echo '<script>window.location.href = "index.php";</script>';
        exit();
    }

}