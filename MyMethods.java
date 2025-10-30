package com.arshahrear.accountmanagment;

import android.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class MyMethods {

    public static String MY_KEY ="";



//===================================
    public static String encryptData(String text, String pass) throws Exception {
        String plainText = text;
        byte[] plaiTextBytes = plainText.getBytes( "UTF-8");

        String password = pass; //"aB3$dEfGh1JkLmNo"; //16 bit
        byte[] passwordBytes = password.getBytes("UTF-8");
        //Encrypt
        SecretKeySpec secretKey = new SecretKeySpec(passwordBytes,  "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] securedBytes = cipher.doFinal(plaiTextBytes);

        //Encode
        String encodedString = Base64.encodeToString(securedBytes, Base64.DEFAULT);
        return encodedString;
    }
//===================================



}
 
