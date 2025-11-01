<?php
$json = file_get_contents('php://input');
$data = json_decode($json);

$key = $data['key'];
$email = $data['email'];
$decryptedKey = decryptData($key);

if ($decryptedKey == 'Juba112233' && strlen($email) > 0) {
    $temp = array();

    $con = mysqli_connect('localhost', 'zftsszne_rahman', 'rahman722@gmail.com', 'zftsszne_my_database');
    $sql = "SELECT * FROM my_table WHERE email LIKE '$email' ";
    $output = mysqli_query($con, $sql);

    while ($row = mysqli_fetch_assoc($output)) {
        $name = $row['name'];
        $image = $row['image'];
        $email = $row['email'];

        $result = $name . ' - ' . $email;

        $temp['result'] = $result;
        $temp['image'] = 'https://nubsoft.xyz/apps/' . $image;
    }

    echo json_encode($temp);

}

function decryptData($text){
    $decode = base64_decode($text);
    $decrypted = openssl_decrypt($decode, 'AES-128-ECB', 'Juba#12345678901', OPENSSL_RAW_DATA);
    return $decrypted;
}

?>
