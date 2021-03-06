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
            $db = Db::getInstance();

//            $search = json_decode($_POST['search'], true);

            if (isset($_POST['item_topic'])) {
                $item_topic = $_POST['item_topic'];
            } else {
                $item_topic = '';
            }
            if (isset($_POST['item_cate_id'])) {
                $item_cat_id = $_POST['item_cat_id'];
            } else {
                $item_cat_id = '';
            }
            if (isset($_POST['item_location'])) {
                $item_location = $_POST['item_location'];
            } else {
                $item_location = '';
            }
            if (isset($_POST['item_price'])) {
                $item_price = $_POST['item_price'];
            } else {
                $item_price = '';
            }
            if (isset($_POST['item_peresent'])) {
                $item_peresent = $_POST['item_peresent'];
            } else {
                $item_peresent = '';
            }
            $allItemSearch = $db->query("SELECT * FROM tbl_itemwall_item WHERE item_topic LIKE '%" . $item_topic . "%' AND item_cat_id LIKE '%" . $item_cat_id . "%'AND item_location LIKE '%" . $item_location . "%' AND item_price LIKE '%" . $item_price . "%' AND item_peresent LIKE '%" . $item_peresent . "%' ");

            for($x=0;$x<count($allItemSearch);$x++){
                $allItemSearch[$x]['item_cat_id']=cateName($allItemSearch[$x]['item_cat_id']);
            }



            $respone = array("status" => 'ok', 'Item' => $allItemSearch);
            $respone = json_encode($respone, true);
            echo $respone;


//        } else {
//            $allItem = $db->query("SELECT * FROM tbl_itemwall_item");
//            for($x=0;$x<count($allItem);$x++){
//                $allItem[$x]['item_cat_id']=cateName($allItem[$x]['item_cat_id']);
//            }
//            $respone = array("status" => 'ok', 'Item' => $allItem);
//            $respone = json_encode($respone, true);
//            echo $respone;
//        }


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
function cateName($cateId){
    $db = Db::getInstance();

    $cat = $db->query("SELECT * FROM tbl_itemwall_cat WHERE cat_id=:cat_id", array(

        'cat_id' => $cateId,

    ));
    return $cat[0]['cat_name'];
}