<?php
/**
 * Created by PhpStorm.
 * User: root
 * Date: 2/25/2019
 * Time: 11:36 AM
 */

class SendSms{

    public  function smsSend($mobile, $code){

        // Get cURL resource
        $curl = curl_init();
// Set some options - we are passing in a useragent too here
        curl_setopt_array($curl, array(
            CURLOPT_RETURNTRANSFER => 1,
            CURLOPT_URL => 'https://api.kavenegar.com/v1/436B4B59706E63696254324E31616A3338776F672F5A5043515563766E755835/sms/send.json',
            CURLOPT_USERAGENT => 'Codular Sample cURL Request',
            CURLOPT_POST => 1,
            CURLOPT_POSTFIELDS => array(
                'receptor' => $mobile,
                'sender' => '100065995',
                'message' => $code
            )
        ));
// Send the request & save response to $resp
        $resp = curl_exec($curl);
// Close request to clear up some resources
        curl_close($curl);
        echo $resp;
    }


}