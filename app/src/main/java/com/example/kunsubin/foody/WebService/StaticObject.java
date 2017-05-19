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
    //get binhluan nhà hàng
    public static String METHOD_GETBINHLUAN="getBinhLuanNhaHang";
    public static String SOAP_ACTION_GETBINHLUAN=NAME_SPACE+METHOD_GETBINHLUAN;
    //get ImageMore
    public static String METHOD_GETBIMAGEMORE="getImageMore";
    public static String SOAP_ACTION_GETIMAGEMORE=NAME_SPACE+METHOD_GETBIMAGEMORE;
    //getNhaHang
    public static String METHOD_GETNHAHANG="getNhaHang";
    public static String SOAP_ACTION_GETNHAHANG=NAME_SPACE+METHOD_GETNHAHANG;
    //get All danhh mục
    public static String METHOD_GETDANHMUC="getAllDanhMuc";
    public static String SOAP_ACTION_GETDANHMUC=NAME_SPACE+METHOD_GETDANHMUC;

    //getMonAn
    public static String METHOD_GETMONAN="getAllMonAn";
    public static String SOAP_ACTION_GETMONAN=NAME_SPACE+METHOD_GETMONAN;
    //getInfo
    public static String METHOD_GETINFO="getThongTin";
    public static String SOAP_ACTION_GETINFO=NAME_SPACE+METHOD_GETINFO;
    //get nhaHangAngi
    public static String METHOD_GETNHAHANGANGI="getNhaHangAnGi";
    public static String SOAP_ACTION_GETNHAHANGANGI=NAME_SPACE+METHOD_GETNHAHANGANGI;
    //upload hinh
    public static String METHOD_UPLOADIMAGE="UploadImageAvartar";
    public static String SOAP_ACTION_UPLOADIMAGE=NAME_SPACE+METHOD_UPLOADIMAGE;
    //insert nhà hàng
    public static String METHOD_INSERTNHAHANG="insertNhaHang";
    public static String SOAP_ACTION_INSERTNHAHANG=NAME_SPACE+METHOD_INSERTNHAHANG;

}
