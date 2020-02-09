package com.example.job.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.job.Adapter.ListviewAdapter;
import com.example.job.Adapter.ViewPagerAdapter;
import com.example.job.bean.Apply;
import com.example.job.R;

import java.util.ArrayList;
import java.util.List;

public class ApplyActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView text1;
    private RelativeLayout relative1;
    private TextView text2;
    private RelativeLayout relative2;
    private TextView text3;
    private RelativeLayout relative3;
    private ViewPager viewPager;
    private List<View> viewList = new ArrayList<>();
    private View view1, view2, view3;
    private ListView listview1;
    private ListView listview2;
    private ListView listview3;
    private List<Apply> applyList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply);
        LayoutInflater inflater = getLayoutInflater();
        view1 = inflater.inflate(R.layout.layout1, null);
        view2 = inflater.inflate(R.layout.layout2, null);
        view3 = inflater.inflate(R.layout.layout3, null);
        initView();
        relative1.setOnClickListener(this);
        relative2.setOnClickListener(this);
        relative3.setOnClickListener(this);




        ListviewAdapter listviewAdapter=new ListviewAdapter(this,R.layout.apply_listview,applyList);
        listview1.setAdapter(listviewAdapter);




        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(viewList);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                change(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    private void initView() {
        text1 = (TextView) findViewById(R.id.text1);
        relative1 = (RelativeLayout) findViewById(R.id.relative1);
        text2 = (TextView) findViewById(R.id.text2);
        relative2 = (RelativeLayout) findViewById(R.id.relative2);
        text3 = (TextView) findViewById(R.id.text3);
        relative3 = (RelativeLayout) findViewById(R.id.relative3);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        listview1 = (ListView) view1.findViewById(R.id.listview1);

        listview2 = (ListView) view2.findViewById(R.id.listview2);

        listview3 = (ListView) view3.findViewById(R.id.listview3);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.relative1:
                viewPager.setCurrentItem(0);
                break;
            case R.id.relative2:
                viewPager.setCurrentItem(1);
                break;
            case R.id.relative3:
                viewPager.setCurrentItem(2);
                break;
            default:
                break;
        }
    }

    private void change(int position) {
        if (position == 0) {
            text1.setTextSize(20);
            text2.setTextSize(16);
            text3.setTextSize(16);
        } else if (position == 1) {
            text1.setTextSize(16);
            text2.setTextSize(20);
            text3.setTextSize(16);
        } else if (position == 2) {
            text1.setTextSize(16);
            text2.setTextSize(16);
            text3.setTextSize(20);
        }
    }
    public void initApplyList(){
        Apply apply=new Apply();
    }
}
