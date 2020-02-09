package com.example.job.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.job.DB.DBhelper;
import com.example.job.R;
import com.example.job.bean.User;

import de.hdodenhof.circleimageview.CircleImageView;

public class InfoActivity extends AppCompatActivity implements View.OnClickListener {


    private CircleImageView circle1;
    private TextView textview1;
    private TextView textview2;
    private TextView textview3;
    private TextView textview4;
    private TextView textview5;
    private LinearLayout linear1;
    private CircleImageView circle2;
    private EditText edittext1;
    private EditText edittext2;
    private EditText edittext3;
    private EditText edittext4;
    private EditText edittext5;
    private LinearLayout linear2;
    private Button btn1;
    User user = new User();
    private Button btn2;
    String message=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        initView();
        user = (User) getIntent().getSerializableExtra("user");
        textview1.setText(user.getUsername());
        String sex = String.valueOf(user.getSex());
        textview2.setText(sex);
        String age = String.valueOf(user.getAge());
        textview3.setText(age);
        textview4.setText(user.getRealname());
    }

    private void initView() {
        circle1 = (CircleImageView) findViewById(R.id.circle1);
        textview1 = (TextView) findViewById(R.id.textview1);
        textview2 = (TextView) findViewById(R.id.textview2);
        textview3 = (TextView) findViewById(R.id.textview3);
        textview4 = (TextView) findViewById(R.id.textview4);
        textview5 = (TextView) findViewById(R.id.textview5);
        linear1 = (LinearLayout) findViewById(R.id.linear1);
        circle2 = (CircleImageView) findViewById(R.id.circle2);
        edittext1 = (EditText) findViewById(R.id.edittext1);
        edittext2 = (EditText) findViewById(R.id.edittext2);
        edittext3 = (EditText) findViewById(R.id.edittext3);
        edittext4 = (EditText) findViewById(R.id.edittext4);
        edittext5 = (EditText) findViewById(R.id.edittext5);
        linear2 = (LinearLayout) findViewById(R.id.linear2);
        btn1 = (Button) findViewById(R.id.btn1);
        btn1.setOnClickListener(this);
        btn2 = (Button) findViewById(R.id.btn2);
        btn2.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                linear1.setVisibility(View.GONE);
                linear2.setVisibility(View.VISIBLE);
                edittext1.setText(user.getUsername());
                String sex = String.valueOf(user.getSex());
                edittext2.setText(sex);
                String age = String.valueOf(user.getAge());
                edittext3.setText(age);
                edittext4.setText(user.getRealname());
                break;
            case R.id.btn2:
                final String username = edittext1.getText().toString();
                final String sex1 = edittext2.getText().toString();
                final String age1 = edittext3.getText().toString();
                final String realname = edittext4.getText().toString();
                Log.d("sssdsdsd",user.getUsername()+"sds");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        message = new DBhelper().updateInfo(sex1,age1,realname,user.getUsername());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if ("true".equals(message)) {
                                    textview1.setText(username);
                                    textview2.setText(sex1);
                                    textview3.setText(age1);
                                    textview4.setText(realname);
                                    linear2.setVisibility(View.GONE);
                                    linear1.setVisibility(View.VISIBLE);

                                }
                            }
                        });
                    }
                }).start();

                break;
            default:
                break;
        }
    }
}
