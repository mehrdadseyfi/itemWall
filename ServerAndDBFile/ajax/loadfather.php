<?php
/**
 * Created by PhpStorm.
 * User: root
 * Date: 3/3/2019
 * Time: 10:01 AM
 */
require_once('../db.php');
$db = Db::getInstance();

$allItem = $db->query("SELECT * FROM tbl_itemwall_cat");
$allItem=json_encode($allItem,true);
echo $allItem;
