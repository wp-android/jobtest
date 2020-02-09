package com.example.job.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.job.R;
import com.example.job.bean.User;

public class MainActivity1 extends AppCompatActivity implements View.OnClickListener {

    private RelativeLayout info;
    private LinearLayout money;
    private LinearLayout apply;
    private LinearLayout opinion;
    private LinearLayout about;
    private LinearLayout service;
    private LinearLayout share;
    private TextView username;
    User user = new User();
    private LinearLayout apply1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();
        user = (User) getIntent().getSerializableExtra("user");
        Log.d("rrrr", user.getUsername() + "wei");
        username.setText(user.getUsername());
        info.setOnClickListener(this);
        money.setOnClickListener(this);
        about.setOnClickListener(this);
        apply.setOnClickListener(this);
        share.setOnClickListener(this);
        service.setOnClickListener(this);
        opinion.setOnClickListener(this);
    }

    private void initView() {
        info = (RelativeLayout) findViewById(R.id.info);
        money = (LinearLayout) findViewById(R.id.money);
        apply = (LinearLayout) findViewById(R.id.apply);
        opinion = (LinearLayout) findViewById(R.id.opinion);
        about = (LinearLayout) findViewById(R.id.about);
        service = (LinearLayout) findViewById(R.id.service);
        share = (LinearLayout) findViewById(R.id.share);
        username = (TextView) findViewById(R.id.username);
        username.setOnClickListener(this);
        apply1 = (LinearLayout) findViewById(R.id.apply1);
        apply1.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.apply:
                Intent intent = new Intent(MainActivity1.this, ApplyActivity.class);
                startActivity(intent);
                break;
            case R.id.apply1:
                Intent intent4 = new Intent(MainActivity1.this, IdenActivity.class);
                startActivity(intent4);
                break;
            case R.id.info:
                Intent intent1 = new Intent(MainActivity1.this, InfoActivity.class);
                intent1.putExtra("user", user);
                startActivity(intent1);
                break;
            case R.id.money:
                Intent intent2 = new Intent(MainActivity1.this, BalanceActivity.class);
                startActivity(intent2);
                break;
            case R.id.opinion:
                Intent intent3 = new Intent(MainActivity1.this, OpinionActivity.class);
                startActivity(intent3);
                break;
        }
    }
}
