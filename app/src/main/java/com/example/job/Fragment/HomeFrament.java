package com.example.job.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import com.example.job.Adapter.Positiondapter;
import com.example.job.R;
import com.example.job.activity.DetailsActivity;
import com.example.job.activity.MainActivity;
import com.example.job.bean.Position;
import com.example.job.dao.JSONgetPos;


import java.util.List;

public class HomeFrament extends Fragment implements View.OnClickListener {

    RecyclerView recyclerView;
    int bian = 0;
    List<Position> positions;//将查询出来的所有数据保存起来
    List<Position> positions_new;//将所有数据符合条件的保存起来
    private SwipeRefreshLayout swipeRefreshLayout;
    private ImageView diyi;
    private ImageView dier;
    private ImageView disan;
    private ImageView disi;
    private TextView biaoT;
    private TextView textView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        init(view);
        huo_q();
        //刷新操作
        swipeRefreshLayout = view.findViewById(R.id.refresh);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                huo_q();
                swipeRefreshLayout.setRefreshing(false);//关闭刷新
            }
        });
        return view;
    }

    void init(View view) {
        textView=view.findViewById(R.id.text1);
        diyi = view.findViewById(R.id.diyi);
        dier = view.findViewById(R.id.dier);
        disan = view.findViewById(R.id.disan);
        disi = view.findViewById(R.id.disi);
        diyi.setOnClickListener(this);
        dier.setOnClickListener(this);
        disan.setOnClickListener(this);
        disi.setOnClickListener(this);
        textView.setOnClickListener(this);
        biaoT = view.findViewById(R.id.biaoT);
        recyclerView = view.findViewById(R.id.tui_recyclerview);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.diyi:
                biaoT.setText("全部兼职");
                break;
            case R.id.dier:
                biaoT.setText("附近兼职");
                break;
            case R.id.disan:
                biaoT.setText("短期兼职");
                break;
            case R.id.disi:
                biaoT.setText("长期兼职");
                break;
        }
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                default:
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                    recyclerView.setLayoutManager(linearLayoutManager);
                    Positiondapter positiondapter = new Positiondapter(positions);
                    recyclerView.setAdapter(positiondapter);
                    break;
            }
        }
    };

    void huo_q() {
        new Thread(new Runnable() {
            @Override
            public void run() {
               /* ContentDao contentDao = new ContentDao("test");
                positions = contentDao.showAllContent("position");*//*本地数据库获取*/
                positions = JSONgetPos.chaXun();
                Message message = new Message();
                mHandler.sendMessage(message);
            }
        }).start();
    }

    void refresh() {//刷新任务

    }
}
