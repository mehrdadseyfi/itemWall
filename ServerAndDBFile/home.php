<?php
/**
 * Created by PhpStorm.
 * User: root
 * Date: 3/2/2019
 * Time: 3:29 PM
 */
require_once('db.php');

$db = Db::getInstance();

if (isset($_COOKIE['token'])) {
$member = $db->query("SELECT * FROM tbl_userweb WHERE token=:token", array(

    'token' => $_COOKIE['token'],

));

if (count($member) == 1) {


        $allItem = $db->query("SELECT * FROM tbl_itemwall_item");



    } else {
        $respone = array("status" => 'token_expire');
        $respone = json_encode($respone, true);
    echo '<script>window.location.href = "index.php";</script>';
    exit();
    }
} else {
    $respone = array("status" => 'token_expire');
    $respone = json_encode($respone, true);
    echo '<script>window.location.href = "index.php";</script>';
    exit();


}
function cat($cat){
    $db = Db::getInstance();

    $cat = $db->query("SELECT * FROM tbl_itemwall_cat WHERE cat_id=:cat_id", array(

        'cat_id' => $cat,

    ));
    echo $cat[0]['cat_name'];
}

?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="http://getbootstrap.com/assets/ico/favicon.ico">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>همه آگهی ها</title>

    <!-- Bootstrap core CSS -->
    <link href="bootstrap/css/bootstrap.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="bootstrap/css/signin.css" rel="stylesheet">
    <link href="bootstrap/css/custom.css" rel="stylesheet">
    <style>
        @font-face {
            font-family: yekan;
            src: url('fonts/Vazir-Medium-FD-WOL.eot');
            src: url('fonts/Vazir-Medium-FD-WOL.eot?#iefix') format('Vazir-Medium-FD-WOL-opentype'),
            url('fonts/Vazir-Medium-FD-WOL.woff') format('woff'),
            url('fonts/Vazir-Medium-FD-WOL.ttf') format('truetype');
            font-weight: normal;
            font-style: normal;
        }
    </style>
</head>
<body style="font-family: yekan">

<div class="container">
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="#">صفحه ها</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item active">
                    <a class="nav-link" href="home.php">همه آگهی <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="usernobile.php">کاربران موبایل</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="Category.php">دسته بندی</a>
                </li>

            </ul>
        </div>
    </nav>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">موضوع</th>
            <th scope="col">محل</th>
            <th scope="col">قیمت</th>
            <th scope="col">توضیح</th>
            <th scope="col">عکس اول</th>
            <th scope="col">عکس دوم</th>
            <th scope="col">وضعیت</th>
            <th scope="col">دسته</th>
        </tr>
        </thead>
        <tbody>

        <?php

        for($x=0;$x<count($allItem);$x++) {
            echo '              <tr>
      <th scope="row">'.($x+1).'</th>

 <td>' . $allItem[$x]['item_topic'] . '</td>
            <td>' . $allItem[$x]['item_location'] . '</td>
            <td>' . $allItem[$x]['item_price'] . '</td>
            <td>' . $allItem[$x]['item_peresent'] . '</td>
            <td><a href="' . $allItem[$x]['image_url1'] . '">image 1</a></td>
            <td><a href="' . $allItem[$x]['image_url2'] . '">image 2</a></td>
            <td>' . $allItem[$x]['item_status'] . '</td>';
            echo '<td>';
            cat($allItem[$x]['item_cat_id']);
            echo '</td>        </tr>';


        }



        ?>




        </tbody>
    </table>

</div> <!-- /container -->


<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="bootstrap/js/bootstrap.js"></script>

</body>
</html>

