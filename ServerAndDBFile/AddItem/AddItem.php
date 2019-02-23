<?php
/**
 * Created by PhpStorm.
 * User: User
 * Date: 22/02/2019
 * Time: 07:15 PM
 */
require_once('../db.php');


if ( isset($_POST['token'])&&isset($_FILES['image1']) && isset($_FILES['image2']) && isset($_POST['topic']) && isset($_POST['cateid']) && isset($_POST['location']) && isset($_POST['price']) && isset($_POST['peresent']) ) {
    $userToken=$_POST['token'];
    $db = Db::getInstance();

    $member = $db->query("SELECT * FROM tbl_itemwall_users WHERE token=:token", array(

        'token' => $userToken,

    ));
    if (count($member) == 1) {
        $user_id = $member[0]['user_id'];

        $db->insert("INSERT INTO tbl_itemwall_item (user_id,item_topic,item_cat_id,item_location,item_price,item_peresent,item_status) VALUES (:user_id,:topic,:cateid,:location,:price,:peresent,:status)", array(

            'user_id' => $user_id,
            'topic' => $_POST['topic'],
            'cateid' => $_POST['cateid'],
            'location' => $_POST['location'],
            'price' => $_POST['price'],
            'peresent' => $_POST['peresent'],
            'status' => 1
        ));

        $item = $db->query("SELECT * FROM tbl_itemwall_item WHERE user_id=:user_id ORDER BY item_id DESC "
            , array(
                'user_id' => $user_id,
            ));


        //<editor-fold desc="file save">
        $errors = array();
        $file_name = $_FILES['image1']['name'];
        $file_size = $_FILES['image1']['size'];
        $file_tmp = $_FILES['image1']['tmp_name'];
        $file_type = $_FILES['image1']['type'];
        $file_ext = strtolower(end(explode('.', $_FILES['image1']['name'])));


        $file_name2 = $_FILES['image2']['name'];
        $file_size2 = $_FILES['image2']['size'];
        $file_tmp2 = $_FILES['image2']['tmp_name'];
        $file_type2 = $_FILES['image2']['type'];
        $file_ext2 = strtolower(end(explode('.', $_FILES['image2']['name'])));


        $extensions = array("jpeg", "jpg", "png");

        if (in_array($file_ext, $extensions) === false && in_array($file_ext2, $extensions) === false) {
            $errors[] = "extension not allowed, please choose a JPEG or PNG file.";
        }

        if ($file_size > 2097152 && $file_size2 > 2097152) {
            $errors[] = 'File size must be excately 2 MB';
        }

        if (empty($errors) == true) {
            move_uploaded_file($file_tmp, "images/" . $item[0]['item_id'] . "1" . $file_name);
            move_uploaded_file($file_tmp2, "images/" . $item[0]['item_id'] . "2" . $file_name2);


            $db->modify("UPDATE tbl_itemwall_item SET image_url1=:image_url1,image_url2=:image_url2 WHERE item_id=:item_id", array(

                'image_url1' => "images/" . $item[0]['item_id'] . "1" . $file_name,
                'image_url2' => "images/" . $item[0]['item_id'] . "2" . $file_name2,

                'item_id' => $item[0]['item_id'],

            ));


        } else {


            var_dump($errors);
        }
        //</editor-fold>


        $respone = array("status" => 'ok', 'cate' => $catApi);

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
