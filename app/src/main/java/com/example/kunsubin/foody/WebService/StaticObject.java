package com.example.kunsubin.foody.WebService;

/**
 * Created by kunsubin on 4/21/2017.
 */

public class StaticObject {
    public static String NAME_SPACE="http://anhky.org/";
    public static String URL="http://10.0.3.2/WebServiceFoody/WebService.asmx?WSDL";
    //tinh thành
    public static String METHOD_GETALLTINHTHANH="getAllTinhThanh";
    public static String SOAP_ACTION_GETALLTINHTHANH=NAME_SPACE+METHOD_GETALLTINHTHANH;
    //quận huyện
    public static String METHOD_GETALLQUANHUYEN="getAllQuanHuyen";
    public static String SOAP_ACTION_GETALLQUANHUYEN=NAME_SPACE+METHOD_GETALLQUANHUYEN;
    //checkLogin
    public static String METHOD_CHECKLOGIN="checkLogin";
    public static String SOAP_ACTION_CHECKLOGIN=NAME_SPACE+METHOD_CHECKLOGIN;
    //getInfo user
    public static String METHOD_GETUSER="getUser";
    public static String SOAP_ACTION_GETUSER=NAME_SPACE+METHOD_GETUSER;
    //getImage
    public static String METHOD_GETIMAGE="getImage";
    public static String SOAP_ACTION_GETIMAGE=NAME_SPACE+METHOD_GETIMAGE;

    //changePass
    public static String METHOD_CHANGEPASS="changePassword";
    public static String SOAP_ACTION_CHANGEPASS=NAME_SPACE+METHOD_CHANGEPASS;
    //changeProfile
    public static String METHOD_CHANGEPROFILE="changeProfile";
    public static String SOAP_ACTION_CHANGEPROFILE=NAME_SPACE+METHOD_CHANGEPROFILE;
    //changeProfile
    public static String METHOD_CREATEUSER="createUser";
    public static String SOAP_ACTION_CREATEUSER=NAME_SPACE+METHOD_CREATEUSER;
    //get Đường
    public static String METHOD_GETDUONG="getDuongTheoHuyen";
    public static String SOAP_ACTION_GETDUONG=NAME_SPACE+METHOD_GETDUONG;
}
