package com.example.kunsubin.foody.WebService;

import com.example.kunsubin.foody.Object.ObjectInfoUser;
import com.example.kunsubin.foody.Object.QuanHuyen;
import com.example.kunsubin.foody.Object.TinhThanh;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.MarshalFloat;
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
        ObjectInfoUser user = new ObjectInfoUser();
        user = null;
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
            SoapObject data = (SoapObject) envelope.getResponse();

            user.setUsername(data.getProperty("Username").toString());
            user.setUsername(data.getProperty("HoTen").toString());
            user.setUsername(data.getProperty("DiaChi").toString());
            user.setUsername(data.getProperty("SDT").toString());
            user.setUsername(data.getProperty("Avatar").toString());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        return user;
    }
}
