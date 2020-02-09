package com.example.job.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import com.example.job.Adapter.Positiondapter;
import com.example.job.R;
import com.example.job.bean.Position;
import com.example.job.dao.JSONgetPos;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class BoutiqueFrament extends Fragment implements View.OnClickListener {
    //    private List<Position> list = new ArrayList<>();
    Timer timer;
    TimerTask timerTask;
    List<Position> positions;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    private Spinner leiX;
    private Spinner quY;
    private Spinner paiX;
    String[] lei = {"话务员","服务员","前台","演员","后勤","家教","其他"};
    String[] qu = {"区域"};
    String[] pai = {"综合排序","距离最近","薪资最高","时长最短"};
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_boutique, container, false);
        init(view);
        recyclerView = view.findViewById(R.id.recyclerView);
       /* //刷新操作
        swipeRefreshLayout = view.findViewById(R.id.descx);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                startTimer();
                swipeRefreshLayout.setRefreshing(false);//关闭刷新
            }
        });
*/
        return view;
    }

    private void init(View view) {
        /*下拉框分类*/
        leiX = view.findViewById(R.id.leiX);
        ArrayAdapter<String> adapter1=new ArrayAdapter<String>(getActivity(),R.layout.spinner_layout,lei);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<String> adapter2=new ArrayAdapter<String>(getActivity(),R.layout.spinner_layout,qu);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<String> adapter3=new ArrayAdapter<String>(getActivity(),R.layout.spinner_layout,pai);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        leiX.setAdapter(adapter1);
        quY = view.findViewById(R.id.quY);
        quY.setAdapter(adapter2);
        paiX = view.findViewById(R.id.paiX);
        paiX.setAdapter(adapter3);

        //数据查询
        startTimer();
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


    /*每一秒执行一次*/
    void startTimer() {
        if (timer == null) {
            timer = new Timer();
        }
        timerTask = new TimerTask() {
            @Override
            public void run() {
               /* ContentDao contentDao = new ContentDao("test");
                positions = contentDao.showAllContent("position");*/
                positions = JSONgetPos.chaXun();
                Message message = new Message();
                mHandler.sendMessage(message);
            }
        };
        timer.schedule(timerTask, 1000);//1s执行一次
    }

    /*关闭计时器 并且 跳转到下个页面*/
    private void stopTime() {
        if (timer != null) {
            timer.cancel();
        }

    }

    @Override
    public void onClick(View view) {
        //点击进入详情
    }
}
