package com.example.job.DB;


import android.util.Log;

import com.example.job.bean.User;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class DBhelper {

    User userL;
    String username;
    String password;
    String phone;
    String email;
    String realname;

    public String register(String username,String password,String realname,String sex,String age,String phone,String email,String regTime,String location) {
        String message=null;
        try {
            //MediaType JSON = MediaType.parse("application/json; charset=utf-8");
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(20, TimeUnit.SECONDS)
                    .build();

//            第一种传参数
//            JSONObject json=new JSONObject();
//            json.put("phone",phone);
//            json.put("password",password);
//            RequestBody requestBody=RequestBody.create(JSON,String.valueOf(json));
//
//            第二种传参数
            RequestBody requestBody=new FormBody.Builder()
                    .add("username",username)
                    .add("password",password)
                    .add("realname",realname)
                    .add("sex",sex)
                    .add("age",age)
                    .add("phone",phone)
                    .add("email",email)
                    .add("regTime",regTime)
                    .add("location",location)
                    .build();

            Request request = new Request.Builder()
                    .url("http://192.168.1.111:8080/userForAndroid/UserServlet?action=sign")
                    .post(requestBody)
                    .build();
            Response response = client.newCall(request).execute();
            String responseData = response.body().string();

//解析
                    JSONObject jsonObject = new JSONObject(responseData);
                   message =jsonObject.getString("signState");


        } catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }

    public User login(String phoneN,String pwd) {
        Log.d("loginwe1","shoibia");
        Response response = null;



            //MediaType JSON = MediaType.parse("application/json; charset=utf-8");
            final OkHttpClient client = new OkHttpClient().newBuilder()
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(20, TimeUnit.SECONDS)
                    .build();

//            第一种传参数
//            JSONObject json=new JSONObject();
//            json.put("phone",phoneN);
//            json.put("password",password);
//            RequestBody requestBody=RequestBody.create(null,String.valueOf(json));
//
//            第二种传参数
            RequestBody requestBody=new FormBody.Builder()
                            .add("username",phoneN)
                            .add("password",pwd)
                            .build();

//            //构建表单参数
//            FormBody.Builder requestBuild=new FormBody.Builder();
//            //添加请求体
//            RequestBody requestBody=requestBuild
//                    .add("username",phoneN)
//                    .add("password",pwd)
//                    .build();

            final Request request = new Request.Builder()
                    .url("http://192.168.1.112:8080/userForAndroid/UserServlet?action=login")
                    .post(requestBody)
                    .build();


        
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (response.isSuccessful()) {
                try {
                String res = response.body().string();
//            if (res.startsWith("\ufeff")) {
//                res.substring(1);
//            }
//解析
                JSONObject rs = null;

                rs = new JSONObject(res);
                username = rs.getString("username");
                password = rs.getString("password");
                realname = rs.getString("realname");
                int sex = rs.getInt("sex");
                int age = rs.getInt("age");
                phone = rs.getString("phone");
                email = rs.getString("email");
                userL = new User(username, password, realname, sex, age, phone, email);
            } catch(Exception e){
                e.printStackTrace();
            }

        }else{
            Log.d("loginwe111","sha");
        }
   
        return userL;

    }
    public String updateInfo(String sex,String age,String realname,String pp) {
       String message=null;
        try {
            //MediaType JSON = MediaType.parse("application/json; charset=utf-8");
            OkHttpClient client = new OkHttpClient();

//            第一种传参数
//            JSONObject json=new JSONObject();
//            json.put("realname",realname);
//            json.put("age",age);
//            json.put("username",pp);
//            json.put("sex",sex);
//            RequestBody requestBody=RequestBody.create(null,String.valueOf(json));

//            第二种传参数
            RequestBody requestBody=new FormBody.Builder()
                    .add("username1",pp)
                    .add("age",age)
                    .add("realname",realname)
                    .add("sex",sex)
                    .build();

            Request request = new Request.Builder()
                    .url("http://192.168.1.111:8080/userForAndroid/UserServlet?action=updateUser")
                    .post(requestBody)
                    .build();
            Response response = client.newCall(request).execute();
            String responseData = response.body().string();
//            if (responseData.startsWith("\ufeff")) {
//                responseData.substring(1);
//            }
//解析
            JSONObject jsonObject = new JSONObject(responseData);
            message =jsonObject.getString("updateUserState");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }
//    public User login(String phoneN,String pwd) {
//        Log.d("loginwe1","shoibia");
//
//
//
//        //MediaType JSON = MediaType.parse("application/json; charset=utf-8");
//        final OkHttpClient client = new OkHttpClient().newBuilder()
//                .connectTimeout(10, TimeUnit.SECONDS)
//                .readTimeout(20, TimeUnit.SECONDS)
//                .build();
//
////            第一种传参数
////            JSONObject json=new JSONObject();
////            json.put("phone",phoneN);
////            json.put("password",password);
////            RequestBody requestBody=RequestBody.create(null,String.valueOf(json));
////
////            第二种传参数
//        RequestBody requestBody=new FormBody.Builder()
//                .add("username",phoneN)
//                .add("password",pwd)
//                .build();
//
////            //构建表单参数
////            FormBody.Builder requestBuild=new FormBody.Builder();
////            //添加请求体
////            RequestBody requestBody=requestBuild
////                    .add("username",phoneN)
////                    .add("password",pwd)
////                    .build();
//
//        final Request request = new Request.Builder()
//                .url("http://192.168.1.111:8080/userForAndroid/UserServlet?action=login")
//                .post(requestBody)
//                .build();
//        Call call = client.newCall(request);
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(@NotNull Call call, @NotNull IOException e) {
//                Log.d("loginwe","shoibia");
//                if (e instanceof SocketTimeoutException) {
//                    //判断超时异常
//                    Log.d("loginwe","shoibia");
//                    userL=new User("失败","1","1",1,1,"1","1");
//                }
//                if (e instanceof ConnectException) {
//                    ////判断连接异常，
//                    Log.d("loginwe","shoibia");
//                    userL=new User("失败","1","1",1,1,"1","1");
//                }
//
//
//            }
//
//            @Override
//            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
//                Log.d("loginwe2","shoibia");
//                response = client.newCall(request).execute();
//                String res=response.body().string();
////            if (res.startsWith("\ufeff")) {
////                res.substring(1);
////            }
////解析
//                JSONObject rs= null;
//                try {
//                    rs = new JSONObject(res);
//                    username = rs.getString("username");
//                    password = rs.getString("password");
//                    realname = rs.getString("realname");
//                    int sex = rs.getInt("sex");
//                    int age = rs.getInt("age");
//                    phone = rs.getString("phone");
//                    email = rs.getString("email");
//                    userL = new User(username,password,realname,sex,age,phone,email);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//
//        return userL;
//
//    }
}
