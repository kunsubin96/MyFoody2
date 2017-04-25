package com.example.kunsubin.foody.WebService;

import android.util.Log;

import com.example.kunsubin.foody.Object.Duong;
import com.example.kunsubin.foody.Object.ObjectInfoUser;
import com.example.kunsubin.foody.Object.QuanHuyen;
import com.example.kunsubin.foody.Object.TinhThanh;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.MarshalFloat;
import org.ksoap2.serialization.NullSoapObject;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by kunsubin on 4/21/2017.
 */

public class WebService {
    public WebService() {

    }

    //getAll tinh thanh
    public List<TinhThanh> getAllTinhThanh() {
        List<TinhThanh> listTinhThanh = new ArrayList<TinhThanh>();
        SoapObject request = new SoapObject(StaticObject.NAME_SPACE, StaticObject.METHOD_GETALLTINHTHANH);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        HttpTransportSE HttpsTransport = new HttpTransportSE(StaticObject.URL);
        try {
            HttpsTransport.call(StaticObject.SOAP_ACTION_GETALLTINHTHANH, envelope);
            //getData
            SoapObject arraySoapObject = (SoapObject) envelope.getResponse();
            for (int i = 0; i < arraySoapObject.getPropertyCount(); i++) {
                SoapObject item = (SoapObject) arraySoapObject.getProperty(i);

                TinhThanh tinhThanh = new TinhThanh();
                tinhThanh.setMaTinhThanh(item.getProperty("MaTinhThanh").toString());
                tinhThanh.setTenTinhThanh(item.getProperty("TenTinhThanh").toString());
                listTinhThanh.add(tinhThanh);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        return listTinhThanh;
    }

    //getAll tinh thanh
    public List<QuanHuyen> getAllQuanHuyen() {
        List<QuanHuyen> listQuanHuyen = new ArrayList<QuanHuyen>();
        SoapObject request = new SoapObject(StaticObject.NAME_SPACE, StaticObject.METHOD_GETALLQUANHUYEN);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        HttpTransportSE HttpsTransport = new HttpTransportSE(StaticObject.URL);
        try {
            HttpsTransport.call(StaticObject.SOAP_ACTION_GETALLQUANHUYEN, envelope);
            //getData
            SoapObject arraySoapObject = (SoapObject) envelope.getResponse();
            for (int i = 0; i < arraySoapObject.getPropertyCount(); i++) {
                SoapObject item = (SoapObject) arraySoapObject.getProperty(i);

                QuanHuyen quanHuyen = new QuanHuyen();
                quanHuyen.setMaQuanHuyen(item.getProperty("MaQuanHuyen").toString());
                quanHuyen.setTenQuanHuyen(item.getProperty("TenQuanHuyen").toString());
                quanHuyen.setMaTinhThanh(item.getProperty("MaTinhThanh").toString());
                listQuanHuyen.add(quanHuyen);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        return listQuanHuyen;
    }

    //check login
    public int CheckLogin(String TenDN, String MatKhau) {
        int kiemtra = -1;
        SoapObject request = new SoapObject(StaticObject.NAME_SPACE, StaticObject.METHOD_CHECKLOGIN);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        request.addProperty("username", TenDN);
        request.addProperty("password", MatKhau);
        envelope.setOutputSoapObject(request);

        MarshalFloat marshalFloat = new MarshalFloat();
        marshalFloat.register(envelope);

        HttpTransportSE HttpsTransport = new HttpTransportSE(StaticObject.URL);

        try {

            HttpsTransport.call(StaticObject.SOAP_ACTION_CHECKLOGIN, envelope);

            SoapPrimitive item = (SoapPrimitive) envelope.getResponse();

            kiemtra = Integer.parseInt(item.toString());


        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }

        return kiemtra;
    }

    //getInfo user
    public ObjectInfoUser getInfoUser(String username) {
        Log.d("username",username);
        ObjectInfoUser user=null;

        SoapObject request = new SoapObject(StaticObject.NAME_SPACE, StaticObject.METHOD_GETUSER);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        request.addProperty("username", username);
        envelope.setOutputSoapObject(request);

        MarshalFloat marshalFloat = new MarshalFloat();
        marshalFloat.register(envelope);

        HttpTransportSE HttpsTransport = new HttpTransportSE(StaticObject.URL);
        try {
            HttpsTransport.call(StaticObject.SOAP_ACTION_GETUSER, envelope);
            //getData
            SoapObject data = (SoapObject) envelope.bodyIn;

            SoapObject item=(SoapObject)data.getProperty(0);
            user= new ObjectInfoUser();
            user.setUsername(item.getProperty("Username").toString());
            user.setPassword(item.getProperty("Password").toString());
            user.setHoTen(item.getProperty("HoTen").toString());
            user.setDiaChi(item.getProperty("DiaChi").toString());
            user.setSDT(item.getProperty("SDT").toString());
            user.setAvatar(item.getProperty("Avatar").toString());
            user.setEmail(item.getProperty("Email").toString());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        catch (NullPointerException e){
            e.printStackTrace();
        }
        return user;
    }
    //getImage
    public String getImage(String filename) {
       String image=null;
        SoapObject request = new SoapObject(StaticObject.NAME_SPACE, StaticObject.METHOD_GETIMAGE);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        request.addProperty("filename", filename);
        envelope.setOutputSoapObject(request);

        MarshalFloat marshalFloat = new MarshalFloat();
        marshalFloat.register(envelope);

        HttpTransportSE HttpsTransport = new HttpTransportSE(StaticObject.URL);

        try {

            HttpsTransport.call(StaticObject.SOAP_ACTION_GETIMAGE, envelope);

            SoapPrimitive item = (SoapPrimitive) envelope.getResponse();

            image = item.toString();


        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }catch (NullPointerException e){
            e.printStackTrace();
        }

        return image;
    }
    //change password
    public boolean changePassword(String username,String newPass) {
        boolean resuft=false;
        SoapObject request = new SoapObject(StaticObject.NAME_SPACE, StaticObject.METHOD_CHANGEPASS);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        request.addProperty("username", username);
        request.addProperty("newPass", newPass);
        envelope.setOutputSoapObject(request);

        MarshalFloat marshalFloat = new MarshalFloat();
        marshalFloat.register(envelope);

        HttpTransportSE HttpsTransport = new HttpTransportSE(StaticObject.URL);

        try {

            HttpsTransport.call(StaticObject.SOAP_ACTION_CHANGEPASS, envelope);

            SoapPrimitive item = (SoapPrimitive) envelope.getResponse();
            String kq=item.toString();
            resuft = Boolean.parseBoolean(kq);


        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }catch (NullPointerException e){
            e.printStackTrace();
        }

        return resuft;
    }
    //changeProfile
    public boolean changeProfile(String username,String hoten,String DiaChi,String SDT) {
        boolean resuft=false;
        SoapObject request = new SoapObject(StaticObject.NAME_SPACE, StaticObject.METHOD_CHANGEPROFILE);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        request.addProperty("username", username);
        request.addProperty("hoten", hoten);
        request.addProperty("diachi", DiaChi);
        request.addProperty("SDT", SDT);
        envelope.setOutputSoapObject(request);

        MarshalFloat marshalFloat = new MarshalFloat();
        marshalFloat.register(envelope);

        HttpTransportSE HttpsTransport = new HttpTransportSE(StaticObject.URL);

        try {

            HttpsTransport.call(StaticObject.SOAP_ACTION_CHANGEPROFILE, envelope);

            SoapPrimitive item = (SoapPrimitive) envelope.getResponse();
            String kq=item.toString();
            resuft = Boolean.parseBoolean(kq);


        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        return resuft;
    }
    //createUser
    public boolean createUser(String username,String password,String hoten,String DiaChi,String SDT,String Email) {
        boolean resuft=false;
        SoapObject request = new SoapObject(StaticObject.NAME_SPACE, StaticObject.METHOD_CREATEUSER);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        request.addProperty("username", username);
        request.addProperty("password", password);
        request.addProperty("hoten", hoten);
        request.addProperty("diachi", DiaChi);
        request.addProperty("SDT", SDT);
        request.addProperty("Email", Email);
        envelope.setOutputSoapObject(request);

        MarshalFloat marshalFloat = new MarshalFloat();
        marshalFloat.register(envelope);

        HttpTransportSE HttpsTransport = new HttpTransportSE(StaticObject.URL);

        try {

            HttpsTransport.call(StaticObject.SOAP_ACTION_CREATEUSER, envelope);

            SoapPrimitive item = (SoapPrimitive) envelope.getResponse();
            String kq=item.toString();
            resuft = Boolean.parseBoolean(kq);


        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        return resuft;
    }
    //get Đường theo quận
    public List<Duong> getDuong(String idHuyen) {
        List<Duong> duongs=  new ArrayList<>();;

        SoapObject request = new SoapObject(StaticObject.NAME_SPACE, StaticObject.METHOD_GETDUONG);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        request.addProperty("maHuyen", idHuyen);
        envelope.setOutputSoapObject(request);

        MarshalFloat marshalFloat = new MarshalFloat();
        marshalFloat.register(envelope);

        HttpTransportSE HttpsTransport = new HttpTransportSE(StaticObject.URL);
        try {
            HttpsTransport.call(StaticObject.SOAP_ACTION_GETDUONG, envelope);
            //getData
            SoapObject arraySoapObject = (SoapObject) envelope.getResponse();
            for (int i = 0; i < arraySoapObject.getPropertyCount(); i++) {
                SoapObject item = (SoapObject) arraySoapObject.getProperty(i);

                Duong duong = new Duong();
                duong.setMaDuong(item.getProperty("MaDuong").toString().trim());
                duong.setTenDuong(item.getProperty("TenDuong").toString().trim());
                duong.setMaQuanHuyen(item.getProperty("MaQuanHuyen").toString().trim());
                duongs.add(duong);
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        catch (NullPointerException e){
            e.printStackTrace();
        }
        return duongs;
    }
}
