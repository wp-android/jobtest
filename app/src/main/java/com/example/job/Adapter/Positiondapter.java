package com.example.job.Adapter;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.job.R;
import com.example.job.activity.DetailsActivity;
import com.example.job.bean.Position;

import java.util.List;

public class Positiondapter extends RecyclerView.Adapter<Positiondapter.ViewHolderzi> {
    private List<Position> list;
    private int[] data= {Color.YELLOW,Color.BLUE,Color.RED};
    public /*static*/ int fang_s ;


    public Positiondapter(List<Position> list/*,int a*/){
        this.list = list;
//        this.fang_s = a;
    }
    @NonNull
    @Override
    public ViewHolderzi onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ViewHolderzi viewHolderzi = null;
//        if(fang_s == 0){
//            //普通
            viewHolderzi = new Positiondapter.ViewHolderzi( LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.position2,viewGroup,false));
//        }else{
//            //瀑布
//            viewHolderzi = new Positiondapter.ViewHolderzi( LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.position1,viewGroup,false));
//        }
        final ViewHolderzi finalViewHolderzi = viewHolderzi;
        viewHolderzi.positionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i = finalViewHolderzi.getAdapterPosition();
                Position position = list.get(i);
                Intent intent = new Intent(view.getContext(), DetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("position_xx", position);
                intent.putExtras(bundle);
                view.getContext().startActivity(intent);
            }
        });

        return viewHolderzi;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderzi viewHolderzi, int i) {
//        viewHolderzi.clas.setText("职务："+list.get(i).getClas());
        viewHolderzi.title.setText("分类："+list.get(i).getTitle());
        viewHolderzi.treatment.setText("¥"+list.get(i).getTreatment()+"元/天");
        viewHolderzi.peopleNum.setText("需要"+list.get(i).getPeopleNum()+"人");
        viewHolderzi.start_end.setText("工作时间:"+list.get(i).getStartTime()+"-"+list.get(i).getEndTime());
//        if(fang_s == 0){
            viewHolderzi.editdate.setText("修改时间："+list.get(i).getEditdate());
            viewHolderzi.descx.setText("职位简介:"+list.get(i).getDescx());
            //viewHolderzi.imageView.setImageResource();
//        }

//        viewHolderzi.linearLayout.setBackgroundColor(data[i%3]);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolderzi extends RecyclerView.ViewHolder{//类
        TextView clas,title,treatment,peopleNum,editdate,start_end,descx;//职位分类 //职位标题//职位待遇//需求人数//修改日期*/
        LinearLayout linearLayout;
        ImageView imageView;
        public View positionView;
        public ViewHolderzi(@NonNull View itemView) {
            super(itemView);
                positionView  = itemView;
//                clas = itemView.findViewById(R.id.clas);
                title = itemView.findViewById(R.id.title);
                treatment = itemView.findViewById(R.id.treatment);
                peopleNum = itemView.findViewById(R.id.peopleNum);
                editdate = itemView.findViewById(R.id.editdate);
                linearLayout = itemView.findViewById(R.id.bj_image);
                imageView = itemView.findViewById(R.id.imageTP);
                start_end = itemView.findViewById(R.id.start_end);
                descx = itemView.findViewById(R.id.descx);
        }
    }
}
