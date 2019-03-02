<?php
/**
 * Created by PhpStorm.
 * User: root
 * Date: 2/20/2019
 * Time: 10:37 AM
 *
 */
require_once('db.php');

if(isset($_POST['username'])&&isset($_POST['password'])){
    $username=$_POST['username'];
    $password=md5($_POST['password']);
    $db = Db::getInstance();

    //checkUserExist
    $member = $db->query("SELECT * FROM tbl_userweb WHERE username=:username", array(

        'username' => $username,

    ));

if(count($member)== 1){

    //checkPass
$memberPass = $db->query("SELECT * FROM tbl_userweb WHERE username=:username AND password=:password", array(

    'username' => $username,
    'password'=> $password

));

if(count($memberPass)== 1){

    //insertToken
    $characters = '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ';
    $charactersLength = strlen($characters);
    $randomString = '';
    $length=32;
    for ($i = 0; $i < $length; $i++) {
        $randomString .= $characters[rand(0, $charactersLength - 1)];
    }
    $token=$randomString;

    $db->modify("UPDATE tbl_userweb SET token=:token WHERE username=:username", array(
        'token' => $token,
        'username' => $username,

    ));


    $respone=array("status"=>'user_log_in','token'=>$token);
    $respone=json_encode($respone,true);
//    echo $respone;
    setcookie('token', $token, time() + (86400 * 30), "/");
    echo '<script>window.location.href = "home.php";</script>';
    exit();
} else{

    $respone=array("status"=>'password_wrong');
    $respone=json_encode($respone,true);
    echo 'پسورد اشتباه است';


}



}

else{
    $respone=array("status"=>'user_not_exist');
    $respone=json_encode($respone,true);
    echo "کاربر وجود ندارد";

}


}else{

    $respone=array("status"=>'field_emp');
    $respone=json_encode($respone,true);
    echo "فیلد ها خالی است";
}

?>

<!DOCTYPE html>
<html lang="en"><head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="http://getbootstrap.com/assets/ico/favicon.ico">

    <title>ورود</title>

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

    <form class="form-signin"  role="form" method="POST">
        <img src="bootstrap/images/app_logo_large.png" class="center-block"/>
        <h3 class="form-signin-heading">لطفا وارد شوید</h3>
        <input class="form-control" placeholder="Username" required="" autofocus="" type="text" name="username" required>
        <input class="form-control" placeholder="Password" required="" type="password" name="password" required>

        <button class="btn btn-lg btn-primary btn-block" type="submit" name="submit">ورود</button>
    </form>

</div> <!-- /container -->


<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="bootstrap/js/bootstrap.js"></script>

</body>
</html>
