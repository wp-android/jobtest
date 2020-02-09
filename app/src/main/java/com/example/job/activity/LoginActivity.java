package com.example.job.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.job.DB.DBhelper;
import com.example.job.R;
import com.example.job.bean.User;
import com.mob.MobSDK;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.wechat.friends.Wechat;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, PlatformActionListener {

    private EditText edit1;
    private EditText edit2;
    private Button btn1, btn2;
    private ImageView image2;
    String responseData=null;
    public User user= new User();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //MobSDK.init(this);
        MobSDK.init(this,"2d63c54ddd3ec","b99108183b86104f4af67f6102ed1864");
        //MobSDK.init(this,"wx92134f01556fb479","22d71e1cae39317936707a6880223f38");
        //MobSDK.init(this,"2d63c54ddd3ec","b99108183b86104f4af67f6102ed1864");
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        edit1 = (EditText) findViewById(R.id.edit1);
        edit2 = (EditText) findViewById(R.id.edit2);

        btn2 = (Button) findViewById(R.id.btn2);
        btn1 = (Button) findViewById(R.id.btn1);

        btn2.setOnClickListener(this);
        btn1.setOnClickListener(this);

        image2 = (ImageView) findViewById(R.id.image2);
        image2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        final String phone=edit1.getText().toString().trim();
        final String password=edit2.getText().toString().trim();
        switch (v.getId()) {
            case R.id.btn2:
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.btn1:
                Intent intent1 = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent1);
                if ("".equals(password)||"".equals(phone)){
                    Toast.makeText(LoginActivity.this,"用户名或密码不能为空",Toast.LENGTH_SHORT).show();
                }else {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            user = new DBhelper().login(phone, password);
                            Log.d("shibai",user.getUsername());
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (user.getUsername().equals("失败")){
                                        Toast.makeText(LoginActivity.this, "dududu", Toast.LENGTH_SHORT).show();
                                    }else {
                                        if (user.getPassword().equals("")||user.getUsername().equals("")) {
                                            Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Intent intent1 = new Intent(LoginActivity.this, MainActivity.class);
                                            intent1.putExtra("user", user);
                                            startActivity(intent1);
                                        }
                                    }

                                }
                            });
                        }
                    }).start();
                }

                break;
            case R.id.image2:
                Platform plat = ShareSDK.getPlatform(Wechat.NAME);
//移除授权状态和本地缓存，下次授权会重新授权

//SSO授权，传false默认是客户端授权
                plat.SSOSetting(false);
//授权回调监听，监听oncomplete，onerror，oncancel三种状态
                plat.setPlatformActionListener(LoginActivity.this);
//抖音登录适配安卓9.0

//要数据不要功能，主要体现在不会重复出现授权界面
                plat.showUser(null);
                break;
        }
    }

    private void submit() {
        // validate
        String edit1String = edit1.getText().toString().trim();
        if (TextUtils.isEmpty(edit1String)) {
            Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
            return;
        }

        String edit2String = edit2.getText().toString().trim();
        if (TextUtils.isEmpty(edit2String)) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }

    @Override
    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
        Log.d("ssssssss","cheng");
    }

    @Override
    public void onError(Platform platform, int i, Throwable throwable) {
        Log.d("ssssssss",throwable.getLocalizedMessage());
    }

    @Override
    public void onCancel(Platform platform, int i) {
        Log.d("ssssssss","cheng1111");
    }
}
