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


        $allItem = $db->query("SELECT * FROM tbl_itemwall_cat ORDER BY cat_father");


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
function cat($cat)
{
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
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
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
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item ">
                    <a class="nav-link" href="home.php">همه آگهی </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="usernobile.php">کاربران موبایل</a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="Category.php"> <span class="sr-only">(current)</span>دسته بندی</a>
                </li>

            </ul>
        </div>
    </nav>


    <div class="container">
        <div class="row">
            <div class="col-sm">
                <input type="text" class="form-control" placeholder="نام دسته بندی" id="catename"
                       aria-label="Recipient's username" aria-describedby="basic-addon2">
                <p>نام پدر</p>
                <select class="custom-select" id="fatherlist">

                </select>
                <br>
                <br>
                <button onclick="addCategoery()">افزودن</button>
            </div>
            <div class="col-sm">
                <select class="custom-select" id="cateid">
                    <?php
                    for($x=0;$x<count($allItem);$x++) {
                     echo   '<option value=' ;
                     echo ($allItem[$x]['cat_id']) ;
                     echo '>' ;
                     echo $allItem[$x]['cat_name'] ;
                     echo '</option>';
                    }
                    ?>
                </select>
<br>
                <br>
                <input type="text" class="form-control" placeholder="نام جدید" id="catenamenew"
                       aria-label="Recipient's username" aria-describedby="basic-addon2">
                <p>نام پدر جدید</p>
                <select class="custom-select" id="fatherlistEdit">

                </select>
                <br>
                <br>
                <button onclick="editCate()">افزودن</button>

            </div>

        </div>
    </div>


    <table class="table">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">نام دسته</th>
            <th scope="col">نام پدر</th>

        </thead>
        <tbody>

        <?php

        for ($x = 0; $x < count($allItem); $x++) {
            echo '              <tr>
      <th scope="row">' . ($x + 1) . '</th>

 <td>' . $allItem[$x]['cat_name'] . '</td>';
            echo '<td>';
            cat($allItem[$x]['cat_father']);
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

<script>
    function loadfather() {
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                //   document.getElementById("demo").innerHTML = this.responseText;
                console.log(this.response);

                var obj = JSON.parse(this.responseText);

                var vardlist = '<option selected></option>';

                var fatherlist = document.getElementById('fatherlist');
                var fatherlistEdit = document.getElementById('fatherlistEdit');

                for (var i = 0; i < obj.length; i++) {

                    vardlist = vardlist + '<option value=' + (obj[i]['cat_id']) + '>' + obj[i]['cat_name'] + '</option>';

                }
                fatherlist.innerHTML = vardlist;
                fatherlistEdit.innerHTML = vardlist;

            }


        };
        xhttp.open("POST", "ajax/loadfather.php", true);

        xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xhttp.send("req=carlist");
    }

    function addCategoery() {
        var xhttp = new XMLHttpRequest();
        var catename = document.getElementById('catename').value;
        var catefatherid = document.getElementById('fatherlist').value;
        xhttp.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                //   document.getElementById("demo").innerHTML = this.responseText;
                location.reload();
            }


        };
        xhttp.open("POST", "ajax/addCategoery.php", true);

        xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xhttp.send("catename=" + catename + "&catefatherid=" + catefatherid);


    }
    function editCate() {
        var xhttp = new XMLHttpRequest();
        var catename = document.getElementById('catenamenew').value;
        var catefatherid = document.getElementById('fatherlistEdit').value;
        var cateid = document.getElementById('cateid').value;
        xhttp.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                //   document.getElementById("demo").innerHTML = this.responseText;
           // alert(this.response);
              location.reload();
            }


        };
        xhttp.open("POST", "ajax/editcate.php", true);

        xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xhttp.send("catenamenew=" + catename + "&catefatherid=" + catefatherid+"&oldcateId="+cateid);

    }

    window.onload = loadfather();
</script>

</body>
</html>

