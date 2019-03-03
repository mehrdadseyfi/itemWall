<?php
/**
 * Created by PhpStorm.
 * User: root
 * Date: 3/3/2019
 * Time: 3:58 PM
 */
require_once('../db.php');
$db = Db::getInstance();

if(isset($_POST['itemid'])&&isset($_POST['reason'])){


    $member = $db->query("SELECT * FROM tbl_userweb WHERE token=:token", array(

        'token' => $_COOKIE['token'],

    ));

    if (count($member) == 1) {

        //echo $_POST['itemid'];

        $db->modify("UPDATE `tbl_itemwall_item` SET reason=:reason WHERE item_id=:item_id", array(

            'item_id' => $_POST['itemid'],
            'reason' => $_POST['reason'],
        ));

    } else {
        $respone = array("status" => 'token_expire');
        $respone = json_encode($respone, true);
        echo '<script>window.location.href = "index.php";</script>';
        exit();
    }

}