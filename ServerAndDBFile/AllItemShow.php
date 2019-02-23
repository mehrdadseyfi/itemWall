<?php
/**
 * Created by PhpStorm.
 * User: root
 * Date: 2/23/2019
 * Time: 12:56 PM
 */
require_once('db.php');
if (isset($_POST['token'])) {
    $db = Db::getInstance();

    $userToken = $_POST['token'];
    $member = $db->query("SELECT * FROM tbl_itemwall_users WHERE token=:token", array(

        'token' => $userToken,

    ));
    if (count($member) == 1) {
        if (isset($_POST['search'])) {
            $db = Db::getInstance();

            $search = json_decode($_POST['search'], true);

            if (isset($search['item_topic'])) {
                $item_topic = $search['item_topic'];
            } else {
                $item_topic = '';
            }
            if (isset($search['item_cate_id'])) {
                $item_cat_id = $search['item_cat_id'];
            } else {
                $item_cat_id = '';
            }
            if (isset($search['item_location'])) {
                $item_location = $search['item_location'];
            } else {
                $item_location = '';
            }
            if (isset($search['item_price'])) {
                $item_price = $search['item_price'];
            } else {
                $item_price = '';
            }
            if (isset($search['item_peresent'])) {
                $item_peresent = $search['item_peresent'];
            } else {
                $item_peresent = '';
            }
            $allItemSearch = $db->query("SELECT * FROM tbl_itemwall_item WHERE item_topic LIKE '%" . $item_topic . "%' AND item_cat_id LIKE '%" . $item_cat_id . "%'AND item_location LIKE '%" . $item_location . "%' AND item_price LIKE '%" . $item_price . "%' AND item_peresent LIKE '%" . $item_peresent . "%' ");
            $respone = array("status" => 'ok', 'Item' => $allItemSearch);
            $respone = json_encode($respone, true);
            echo $respone;


        } else {
            $allItem = $db->query("SELECT * FROM tbl_itemwall_item");
            $respone = array("status" => 'ok', 'Item' => $allItem);
            $respone = json_encode($respone, true);
            echo $respone."hi";
        }


    } else {
        $respone = array("status" => 'token_expire');
        $respone = json_encode($respone, true);
        echo $respone;
    }


} else {
    $respone = array("status" => 'field_emp');
    $respone = json_encode($respone, true);
    echo $respone;
}