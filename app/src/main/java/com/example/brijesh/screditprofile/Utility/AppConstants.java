package com.example.brijesh.screditprofile.Utility;

public class AppConstants {
    public static final int SPLASH_DURATION = 3000;
    public static final int REQUEST_EXTERNAL_STORAGE = 1;
    public class APIURL {
        public static final String BASE_API = "http://mahatmainfoware.com/ShareChatAPI/";
        public static final String URL_REGISTER = BASE_API + "registeruser.php";
        public static final String URL_LOGIN = BASE_API + "login.php";
        public static final String URL_LANGUAGE = BASE_API + "getlanguages.php";


    }

    public class APICode {
        public static final int REGISTER = 1;
        public static final int Login = 2;
        public static final int Language= 3;



    }

   /* public class SharedPreferenceKeys {
        public static final String IS_LOGGED_IN = "login_in";
        public static final String SELLER_ID = "seller_id";
        public static final String Name = "name";
        public static final String PROFILEPICTURE = "profile_picture";
        public static final String Seller_banner = "banner_image";

    }*/

    public class RequestDataKey {
        //------- Login ------------------------
        public static final String FIRSTNAME = "first_name";
        public static final String LASTNAME = "last_name";
        public static final String EMAIL = "emailid";
        public static final String PASSWORD = "password";
        public static final String LANGUAGE = "language";
        public static final String MOBILE = "mobile";
        public static final String BIRTHDATE = "BirthDate";
        public static final String GENDER = "gender";
        public static final String id = "id";
        public static final String LONGITUDE = "longitude";
        public static final String LATTITUDE = "lattitude";
        public static final String IMAGE = "image";
        public static final String DEVICETYPE = "type";
        public static final String cid = "cid";
        public static final String DynamicField = "DynamicField";
        public static final String Introduction = "introduction";
        public static final String Fname = "FirstName";
        public static final String Lname = "LastName";
        public static final String OTP = "OTP";
    }

    public class DialogMessage {
        public static final String PLEASE_WAIT = "Please wait...";
        public static final String HIDE_DIALOG = "";
    }
}
