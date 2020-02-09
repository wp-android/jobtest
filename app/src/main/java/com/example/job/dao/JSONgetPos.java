package com.example.job.dao;


import com.example.job.bean.Position;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class JSONgetPos {

    //查询系统
    public static List<Position>  chaXun() {
        String responseData;
        List<Position> list = new ArrayList<>();
        try {
            JSONObject json = new JSONObject();
            OkHttpClient client = new OkHttpClient();
            RequestBody requestBody = RequestBody.create(null, String.valueOf(json));
            Request request = new Request.Builder()
                    .url("http://192.168.0.110:8080/userForAndroid/UserServlet?action=getPos")
                    .post(requestBody)
                    .build();
            Response response = client.newCall(request).execute();
            responseData = response.body().string();
            JSONArray jsonArray = new JSONArray(responseData);
            for (int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Position position = new Position();
                position.setClas((String) jsonObject.opt("clas"));//分类
                position.setCompany((String) jsonObject.opt("company"));//工作
//                position.setDaily((Boolean) jsonObject.opt("daily"));//日结
                position.setDescx((String) jsonObject.opt("descx"));//描述
                position.setEditdate((String) jsonObject.opt("editDate"));//修改日期
                position.setId((int) jsonObject.opt("id"));//

                position.setLocation((String) jsonObject.opt("location"));//位置
                position.setPeopleNum((int) jsonObject.opt("peopleNum"));//人数
                position.setPhone((String) jsonObject.opt("phone"));//dianhua
                position.setStartTime((String) jsonObject.opt("startTime"));//开始时间

                position.setTitle((String) jsonObject.opt("title"));//职位标题
                position.setTreatment((String) jsonObject.opt("treatmrnt"));//待遇
                position.setUsername((String) jsonObject.opt("username"));//
                list.add(position);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }




}
