package com.example.job.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;


import com.example.job.Adapter.TabPagerAdapter;
import com.example.job.Fragment.BoutiqueFrament;
import com.example.job.Fragment.CentreFrament;
import com.example.job.Fragment.HomeFrament;
import com.example.job.R;
import com.example.job.bean.User;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewPager viewPager;
    private ImageView shouY;
    private LinearLayout bjZhuye;
    private ImageView tuiJ;
    private LinearLayout bjShezhi;
    private ImageView geR;
    private LinearLayout bjGeren;
    List<Fragment> fragments = new ArrayList<>();
    private User user=new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user = (User) getIntent().getSerializableExtra("user");
        initView();

        TabPagerAdapter mFragmentAdapter = new TabPagerAdapter(this.getSupportFragmentManager(), fragments);
        viewPager.setOffscreenPageLimit(2);//ViewPager的缓存为ji页
        viewPager.setAdapter(mFragmentAdapter);
        viewPager.setCurrentItem(0); //初始设置ViewPager选中第一页
        shouY.setImageResource(R.drawable.shou_ye2);
        tuiJ.setImageResource(R.drawable.zhong_xin);
        geR.setImageResource(R.drawable.ge_ren);
        /*选中的颜色*/
        /*一开始选中的那一项*/
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                /*此方法在页面被选中时调用*/
                changeTextColor(i);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }


        });
        //隐藏ActionBar
        /*if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }*/

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bj_zhuye:
                changeTextColor(0);
            break;
            case R.id.bj_shezhi:
                changeTextColor(1);
                break;
            case R.id.bj_geren:
                changeTextColor(2);
                break;
        }

    }

    private void initView() {
        viewPager =  findViewById(R.id.frament);
        shouY = (ImageView) findViewById(R.id.shouY);
        bjZhuye = (LinearLayout) findViewById(R.id.bj_zhuye);
        bjZhuye.setOnClickListener(this);
        tuiJ = (ImageView) findViewById(R.id.tuiJ);
        bjShezhi = (LinearLayout) findViewById(R.id.bj_shezhi);
        bjShezhi.setOnClickListener(this);
        geR = (ImageView) findViewById(R.id.geR);
        bjGeren = (LinearLayout) findViewById(R.id.bj_geren);
        bjGeren.setOnClickListener(this);

        HomeFrament homeFrament = new HomeFrament();
        fragments.add(homeFrament);

        BoutiqueFrament boutiqueFrament = new BoutiqueFrament();
        fragments.add(boutiqueFrament);

        CentreFrament centreFrament = new CentreFrament();
        Bundle bundle=new Bundle();
        bundle.putSerializable("msg",user);
        centreFrament.setArguments(bundle);
        fragments.add(centreFrament);


    }
    void changeTextColor(int i) {
        switch (i) {
            case 0:
                shouY.setImageResource(R.drawable.shou_ye2);
                tuiJ.setImageResource(R.drawable.zhong_xin);
                geR.setImageResource(R.drawable.ge_ren);
                viewPager.setCurrentItem(0);
                break;
            case 1:
                shouY.setImageResource(R.drawable.shou_ye);
                tuiJ.setImageResource(R.drawable.zhong_xin2);
                geR.setImageResource(R.drawable.ge_ren);
                viewPager.setCurrentItem(1);
                break;
            case 2:
                shouY.setImageResource(R.drawable.shou_ye);
                tuiJ.setImageResource(R.drawable.zhong_xin);
                geR.setImageResource(R.drawable.ge_ren2);
                viewPager.setCurrentItem(2);
                break;

        }
    }
}
