<?php
$email = $_POST['email'];
$password = $_POST['password'];
$key = $_POST['key'];

$decryptedKey = decryptData($key);
$dec_email = decryptData($email);

if ($decryptedKey == 'arText1122' && strlen($email) > 0) {

    $con = mysqli_connect('localhost', 'zftsszne_rahman', 'rahman722@gmail.com', 'zftsszne_my_database');
    $sql22 = "SELECT * FROM mytable WHERE email = '$dec_email' AND password = '$password' ";
    $result = mysqli_query($con, $sql22);
    $rows = mysqli_num_rows($result);

    if ($rows > 0) {
        echo 'VALID LOGIN';
    } else {
        echo 'Login Error';
    }

}

function decryptData($text) {
    $decode = base64_decode($text);
    $decrypted = openssl_decrypt($decode, 'AES-128-ECB', '@rText1234567890', OPENSSL_RAW_DATA);
    return $decrypted;
}


?>
