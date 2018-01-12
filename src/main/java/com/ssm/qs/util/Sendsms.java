package com.ssm.qs.util;

//接口类型：互亿无线触发短信接口，支持发送验证码短信、订单通知短信等。
// 账户注册：请通过该地址开通账户http://sms.ihuyi.com/register.html
// 注意事项：
//（1）调试期间，请用默认的模板进行测试，默认模板详见接口文档；
//（2）请使用APIID（查看APIID请登录用户中心->验证码短信->产品总览->APIID）及 APIkey来调用接口；
//（3）该代码仅供接入互亿无线短信接口参考使用，客户可根据实际需要自行编写；

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *  Author 田宇
 *  Date   2018/1/11 0011 20:22
 *  Description 短信验证工具类
 */
public class Sendsms {

    private static String Url = "http://106.ihuyi.cn/webservice/sms.php?method=Submit";
    private static Map<String,String> map = new HashMap<>();

    public static Map<String,String> send(String phone) {

        HttpClient client = new HttpClient();//发起客户端请求
        PostMethod method = new PostMethod(Url);//post请求

        client.getParams().setContentCharset("GBK");
        method.setRequestHeader("ContentType","application/x-www-form-urlencoded;charset=GBK");

        int mobile_code = (int)((Math.random()*9+1)*100000);//验证码

        String content = new String("您的验证码是：" + mobile_code + "。请不要把验证码泄露给其他人。");

        NameValuePair[] data = {//提交短信
                new NameValuePair("account", "C46124377"), //查看用户名是登录用户中心->验证码短信->产品总览->APIID
                new NameValuePair("password", "629779e8f6b4ef802395d152ef8acd80"),  //查看密码请登录用户中心->验证码短信->产品总览->APIKEY
                //new NameValuePair("password", util.StringUtil.MD5Encode("密码")),//加密
                new NameValuePair("mobile", phone),//手机号
                new NameValuePair("content", content),
                };
        method.setRequestBody(data);

        try {
            client.executeMethod(method);

            String SubmitResult =method.getResponseBodyAsString();

            //System.out.println(SubmitResult);

            Document doc = DocumentHelper.parseText(SubmitResult);
            Element root = doc.getRootElement();//响应

            String code = root.elementText("code");//返回2，代表提交成功
            String msg = root.elementText("msg");//短信发送结果描述:提交成功
            String smsid = root.elementText("smsid");//提交成功，返回流水号

            //响应状态码、验证码至Controller用于判断
            map.put("code",code);
            map.put("mobile_code",""+mobile_code);

        } catch (HttpException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }

}
