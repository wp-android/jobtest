package com.example.job.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.job.bean.Apply;

import java.util.List;

public class ListviewAdapter extends ArrayAdapter<Apply> {
    private int resourceId;

    public ListviewAdapter(Context context, int resource, List<Apply> objects) {
        super(context, resource, objects);
        resourceId=resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Apply apply=getItem(position);
        View view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        return view;
    }
}
