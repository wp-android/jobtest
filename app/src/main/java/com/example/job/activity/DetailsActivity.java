package com.example.job.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.job.R;
import com.example.job.bean.Position;


public class DetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView huanjing;
    private TextView shangjiangxinxi;
    private TextView shishixinxi;
    private TextView xian;
    private TextView gongzuoneirong;
    private TextView shihsineirong;
    private TextView tishi;
    private TextView shihsitishi;
    private TextView za;
    private TextView collect;
    private TextView apply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        initView();

        //Position position = (Position) this.getIntent().getSerializableExtra("position_xx");
//        shihsineirong.setText(position.getDescx());
//        shihsitishi.setText("有两年编程经验的java工程师，要穿黑色裤子，黑色鞋子，。。。。。。。。。。"+position.getDescx());
//        za.setText("职位："+position.getClas()+"\n招聘人数："+position.getPeopleNum()+"人\n工作时间："+position.getStartTime()+"--"+position.getEndTime()+"\n¥："+position.getTreatment()+"元/天");
//        shihsineirong.setText(position.getDescx());
        shihsitishi.setText("有两年编程经验的java工程师，要穿黑色裤子，黑色鞋子");
        za.setText("职位：\n招聘人数：人\n工作时间：\n¥：元/天");
        collect.setOnClickListener(this);
        apply.setOnClickListener(this);
    }

    private void initView() {
        huanjing = (ImageView) findViewById(R.id.huanjing);//图片
        shishixinxi = (TextView) findViewById(R.id.shishixinxi);//商家信息
        shihsineirong = (TextView) findViewById(R.id.shihsineirong);//工作内容
        shihsitishi = (TextView) findViewById(R.id.shihsitishi);//温馨提示
        za = (TextView) findViewById(R.id.za);//人数 时间 薪酬
        collect = (TextView) findViewById(R.id.collect);//收藏
        apply = (TextView) findViewById(R.id.apply);//报名
    }

    @SuppressLint("ResourceType")
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.collect:
//                collect.setBackgroundResource(YELLOW);
                collect.setText("已收藏");
                break;
            case R.id.apply:
                Toast.makeText(this, "报名成功", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
