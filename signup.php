//প্রথমে apps folder create করে নিবা । ঐটার ভিতর signup.php code add করবা এবং images file create করবা যেইটায় image গুলো store হবে।


<?php
$email = $_POST['email'];
$password = $_POST['password'];
$name = $_POST['name'];
$image64 = $_POST['image'];
$key = $_POST['key'];

$security_key=decryptData($key); //1st a kake decode kortay cai ..$key
$dec_email=decryptData($email);
//$password=decryptData($password);  decrypt korlam na . jate kore aro safe hoi..

if($security_key=='arText' && strlen($dec_email)>0  && strlen($password)>0 ){ //user email password na dilay databse a data store hoibo na


//----------------------------

  $con = mysqli_connect('localhost', 'zftsszne_rahman', 'rahman722@gmail.com', 'zftsszne_my_database'); //user name user password database name 
  $sql22 ="SELECT * FROM my_table WHERE email LIKE '$dec_email' ";//
  $result = mysql_query($con, $sql22);
  
    if($rows<=0){ //row er modde oi email ta zero bar er beshi nai 
  //++++++++++++++++++++++++++
  $decodedImage = base64_decode($image64);
  $fileName = time().'_' . rand(1000, 100000) . '.jpeg';
  $filePath = 'images/' . $fileName;

if (file_put_contents($filePath,$decodedImage)) {

   
    $sql = "INSERT INTO mytable (email, name, password, image) VALUES ('$dec_email', '$name', '$password', '$filePath')";//table name check koiro 100% same kina 
    $result = mysqli_query($con, $sql);

    if ($result) echo 'Signup Success';
    else echo 'Signup Failed';

}
  
  //++++++++++++++++++++++++++
    }else{ 
      echo'Already user Available . Change email and try again..'; 
    }
  
  
  
//--------------------------------
  
  mysqli_close($con);


}

function decryptData($text) {
    $decode = base64_decode($text);
    $decrypted = openssl_decrypt($decode, 'AES-128-ECB', 'ar11password', OPENSSL_RAW_DATA);
    return $decrypted;
}
  
?>
