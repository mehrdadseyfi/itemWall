<?php
/**
 * Created by PhpStorm.
 * User: root
 * Date: 2/23/2019
 * Time: 12:56 PM
 */
require_once('db.php');
if (isset($_POST['token'])&&isset($_POST['item_id'])) {
    $db = Db::getInstance();

    $userToken = $_POST['token'];
    $item_id = $_POST['item_id'];
    $member = $db->query("SELECT * FROM tbl_itemwall_users WHERE token=:token", array(

        'token' => $userToken,

    ));
    if (count($member) == 1) {

        $db = Db::getInstance();


        $allItemSearch = $db->query("SELECT * FROM tbl_itemwall_item WHERE item_id=:item_id ",array(
            'item_id'=>$item_id
        ));
        for($x=0;$x<count($allItemSearch);$x++){
            $allItemSearch[$x]['item_cat_id']=cateName($allItemSearch[$x]['item_cat_id']);
        }

        $contactItem = $db->query("SELECT * FROM tbl_itemwall_users WHERE user_id=:user_id ",array(
            'user_id'=>$allItemSearch[0]['user_id']
        ));
        $respone = array("status" => 'ok', 'Item' => $allItemSearch,'item_contact'=>$contactItem[0]['username']);
        $respone = json_encode($respone, true);
        echo $respone;




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
