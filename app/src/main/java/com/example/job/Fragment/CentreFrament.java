package com.example.job.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.job.R;
import com.example.job.activity.ApplyActivity;
import com.example.job.activity.BalanceActivity;
import com.example.job.activity.IdenActivity;
import com.example.job.activity.InfoActivity;
import com.example.job.activity.OpinionActivity;
import com.example.job.bean.User;


public class CentreFrament extends Fragment implements View.OnClickListener{

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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main1,container,false);
        initView(view);
        Bundle bundle= CentreFrament.this.getArguments();
        user=(User)bundle.getSerializable("msg");
        username.setText(user.getUsername());
        info.setOnClickListener(this);
        money.setOnClickListener(this);
        about.setOnClickListener(this);
        apply.setOnClickListener(this);
        share.setOnClickListener(this);
        service.setOnClickListener(this);
        opinion.setOnClickListener(this);
        return view;
    }



    private void initView(View view) {
        info = (RelativeLayout) view.findViewById(R.id.info);
        money = (LinearLayout) view.findViewById(R.id.money);
        apply = (LinearLayout) view.findViewById(R.id.apply);
        opinion = (LinearLayout) view.findViewById(R.id.opinion);
        about = (LinearLayout) view.findViewById(R.id.about);
        service = (LinearLayout) view.findViewById(R.id.service);
        share = (LinearLayout) view.findViewById(R.id.share);
        username = (TextView) view.findViewById(R.id.username);
        username.setOnClickListener(this);
        apply1 = (LinearLayout) view.findViewById(R.id.apply1);
        apply1.setOnClickListener(this);
    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.apply:
                Intent intent = new Intent(getContext(), ApplyActivity.class);
                startActivity(intent);
                break;
            case R.id.apply1:
                Intent intent4 = new Intent(getContext(), IdenActivity.class);
                startActivity(intent4);
                break;
            case R.id.info:
                Intent intent1 = new Intent(getContext(), InfoActivity.class);
                intent1.putExtra("user", user);
                startActivity(intent1);
                break;
            case R.id.money:
                Intent intent2 = new Intent(getContext(), BalanceActivity.class);
                startActivity(intent2);
                break;
            case R.id.opinion:
                Intent intent3 = new Intent(getContext(), OpinionActivity.class);
                startActivity(intent3);
                break;
        }
    }
}

