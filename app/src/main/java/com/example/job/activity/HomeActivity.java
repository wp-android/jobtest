package com.example.job.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.example.job.R;
import com.example.job.bean.Content;
import com.example.job.dao.ContentDao;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    List<Content> list;
    private Button dianji;
    private ListView listview;
    public String data[];
    ArrayAdapter<String> adapter;
    TimerTask timerTask;
    Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home1);
        initView();
//        new Thread(runnable).start();
        startTimer();
    }

    private void initView() {
        dianji = (Button) findViewById(R.id.dianji);

        dianji.setOnClickListener(this);
        listview = (ListView) findViewById(R.id.listview);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dianji:

                adapter = new ArrayAdapter<>(HomeActivity.this,android.R.layout.simple_list_item_1,data);
                listview.setAdapter(adapter);
                break;
        }
    }


    Runnable runnable=new Runnable() {
        @Override
        public void run() {
            ContentDao contentDao = new ContentDao("moonlighting");
//            list = contentDao.showAllContent();
            Message message = new Message();
            message.what=0;
            mHandler.sendMessage(message);

        }
    };
    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 0:
                    data = new String[list.size()];
                    for (int i=0;i<list.size();i++){
                        data[i] = list.get(i).getUsername()+": \n"+list.get(i).getContent();
                    }
                    startTimer();
                    break;
            }
        }
    };
    /*每运行一次 自减一*/
    void startTimer(){
        if(timer == null){
            timer = new Timer();
        }
        timerTask = new TimerTask() {
            @Override
            public void run() {
                ContentDao contentDao = new ContentDao("moonlighting");
//                list = contentDao.showAllContent();
                Message message = new Message();
                message.what=0;
                mHandler.sendMessage(message);
            }
        };
        timer.schedule(timerTask,1000);//1000ms执行一次
    }
    /*关闭计时器 并且 跳转到下个页面*/
    private void stopTime() {
        if(timer!=null){
            timer.cancel();
        }

    }

}
