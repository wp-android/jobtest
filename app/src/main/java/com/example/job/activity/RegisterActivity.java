package com.example.job.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.job.DB.DBhelper;
import com.example.job.R;
import com.mob.MobSDK;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edit1;
    private Button btn1;
    private EditText edit2;
    private Button btn2;
    EventHandler eh;
    private EditText edit3;
    private EditText edit4;
    private TextView text1;
    private RadioButton radioBtn1;
    private RadioButton radioBtn2;
    private RadioGroup radioGroup;
    private String radiotext;
    private String state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MobSDK.init(this);
        //MobSDK.init(this,"2d63c54ddd3ec","b99108183b86104f4af67f6102ed1864");
        setContentView(R.layout.activity_register);
        initView();

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton radioButton=findViewById(i);
                radiotext=radioButton.getText().toString();
            }
        });
        edit3.addTextChangedListener(new TextWatcher() {
            String num = "";

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                num = charSequence.toString();

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String text = charSequence.toString();
                String reg = "[a-zA-Z0-9]";
                Pattern p = Pattern.compile(reg);
                Matcher m = p.matcher(text);
                if (m.find() || ("").equals(charSequence.toString())) {

                } else {
                    edit3.setText(num);
                    edit3.setSelection(num.length() - 1);
                }
            }


            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        edit4.addTextChangedListener(new TextWatcher() {
            String num = "";

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                num = charSequence.toString();

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String text = charSequence.toString();
                String reg = "[a-zA-Z0-9]";
                Pattern p = Pattern.compile(reg);
                Matcher m = p.matcher(text);
                if (m.find() || ("").equals(charSequence.toString())) {

                } else {
                    edit3.setText(num);
                    edit3.setSelection(num.length() - 1);
                }
            }


            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        final Handler mHandler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                switch (msg.what) {
                    case 0:
                        Toast.makeText(RegisterActivity.this, "失败", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(RegisterActivity.this, "获取验证码成功", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        String st=(String)msg.obj;
                        if (st.equals("1")) {
                            Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                        }else if (st.equals("0")){
                            Toast.makeText(RegisterActivity.this, "未知错误", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(RegisterActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    default:
                        break;

                }
            }
        };
        eh = new EventHandler() {
            @Override
            public void afterEvent(int event, int result, Object data) {
                // TODO 此处不可直接处理UI线程，处理后续操作需传到主线程中操作
                Message msg = new Message();
                if (result == SMSSDK.RESULT_COMPLETE) {

                    if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        msg.what = 1;
                        mHandler.sendMessage(msg);
                    } else if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                        msg.what = 2;
                        final String number = edit1.getText().toString().trim();
                        final String pass = edit3.getText().toString().trim();
                        state= new DBhelper().register(number,pass,"1",radiotext,"1","1","1","1","1");
                        msg.obj=state;
                        mHandler.sendMessage(msg);
                    }

                } else {
                    msg.what = 0;
                    mHandler.sendMessage(msg);
                }
            }

        };
//注册一个事件回调监听，用于处理SMSSDK接口请求的结果
        SMSSDK.registerEventHandler(eh);

    }

    private void initView() {
        edit1 = (EditText) findViewById(R.id.edit1);
        btn1 = (Button) findViewById(R.id.btn1);
        edit2 = (EditText) findViewById(R.id.edit2);
        btn2 = (Button) findViewById(R.id.btn2);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        edit3 = (EditText) findViewById(R.id.edit3);
        edit3.setOnClickListener(this);
        edit4 = (EditText) findViewById(R.id.edit4);
        edit4.setOnClickListener(this);
        text1 = (TextView) findViewById(R.id.text1);
        text1.setOnClickListener(this);
        radioBtn1 = (RadioButton) findViewById(R.id.radioBtn1);
        radioBtn1.setOnClickListener(this);
        radioBtn2 = (RadioButton) findViewById(R.id.radioBtn2);
        radioBtn2.setOnClickListener(this);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String number = edit1.getText().toString().trim();
        switch (v.getId()) {
            case R.id.btn1:
                if (TextUtils.isEmpty(number)){
                    Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
                }else {
                    Log.d("sssssss", edit1.getText().toString().trim());
                    SMSSDK.getVerificationCode("86", number);
                }

                break;


            case R.id.btn2:

                String edit3String = edit3.getText().toString().trim();
                String edit4String = edit4.getText().toString().trim();
                if (TextUtils.isEmpty(edit3String)) {
                    Toast.makeText(this, "请设置登录密码", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(edit4String)) {
                    Toast.makeText(this, "请确认登录密码", Toast.LENGTH_SHORT).show();
                } else {
                    if (edit3String.equals(edit4String)) {
                        String code = edit2.getText().toString().trim();
                        SMSSDK.submitVerificationCode("86", number, code);
                    } else {
                        Toast.makeText(this, "两次输入不一致", Toast.LENGTH_SHORT).show();
                    }
                }


                break;
        }
    }


    protected void onDestroy() {
        super.onDestroy();
        // 注销回调接口registerEventHandler必须和unregisterEventHandler配套使用，否则可能造成内存泄漏。
        SMSSDK.unregisterEventHandler(eh);

    }


}
