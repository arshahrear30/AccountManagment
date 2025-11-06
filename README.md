# AccountManagment
3070
App create করবো ফার্স্ট এ তাহলে Activitymain.xml and java file open হবে আমাদের আরো empty view activity create kortay hoibo . activity_login.xml  and java /// activity_signup.xml  and java .....

Step 1 : Manage Databases >> Create Database>>New Database>>"name">>Create .
Step 2 : Create Database User(s)>>New User>>Password (auto generate)>>!sqADvIInA >> Create 
Step 3 : Add User To Database>>User(Select)>>Database(Select)>>Add
Step 4 : Privileges For Database >> All >> Submit 

Go : phpMyAdmin

Table name: my_table
Number of columns : 5  -(id,photo,email,password,name)

Name :  Type :  Length/Values : 	Default :  Attributes :  Null	Index :

id       INT                                                primary   ✔️ click
email    TEXT     2000              Null        ✔️ 
name    VARCHAR    100              Null        ✔️
mobile  VARCHAR     20              Null        ✔️
image   VARCHAR    200              Null        ✔️

image bitmap কিন্তু store করবো না । সেটার একটা image file এ রেখে ওইখান থেকে url দিয়ে load করাবো 

 click Save .



  <uses-permission android:name="android.permission.INTERNET"/>
    <uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>

    build.gradle(app)
    implementation 'com.android.volley:volley:1.2.1'

    
    buildTypes {
        release {
            minifyEnabled true// false default thakey .. এটা true করে দেবো 
            shrinkResources true //ei line add korbo ....তাহলে কেউ app hack করলে আমার code পাবে না, code উল্টাপাল্টা হয়ে যাবে
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }



PHP এর link এ ঢুকলে তো যে কেউ আমার file গুলো দেখে যেতেই পারে । এটা বন্ধ করার জন্য আমরা index.php file create করবো । তাহলে যে কেউ link এ গেলে আমার index file open হবে ।


Mouse Right clcik(pacage name) >> Java class >>Class >>MyMethods(name) >>enter>>

3077 --- more safe by using encription in email and password section 

|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
API call, HTTP request, data fetching :

implementation 'com.android.volley:volley:1.2.1'

|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||



=====================================================
=====================================================
Image loading and caching library ::

   implementation 'com.github.bumptech.glide:glide:5.0.5'

=====================================================
=====================================================

--------------------------------------------------------------
--------------------------------------------------------------
image picker android library    https://github.com/Dhaval2404/ImagePicker  

implementation 'com.github.dhaval2404:imagepicker:2.1' 


  <uses-feature
        android:name="android.hardware.camera"
        android:required="false" />

    <uses-permission android:name="android.permission.INTERNET"/>
    <uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
    <uses-permission android:name="android.permission.CAMERA"/>

    

settings.gradle    :::::::::

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url "https://jitpack.io" }
    }
}

--------------------------------------------------------------
--------------------------------------------------------------





