package com.example.jay.worknasidemo5.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jay.worknasidemo5.Model.OfficeRoom;
import com.example.jay.worknasidemo5.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jay on 11/23/2017.
 */

public class CustomRowOneAdapter extends RecyclerView.Adapter<CustomRowOneAdapter.MyViewHolder> {

    Context context;
    List<OfficeRoom> office_list = null;

    public CustomRowOneAdapter(Context context, List<OfficeRoom> list){
        this.context = context;
        this.office_list = list;

    }

   /* public void add(OfficeRoom room){
        office_list.add(room);
    }
    */

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view =  inflater.from(context).inflate(R.layout.custom_row_one, parent, false);

        MyViewHolder holder = new MyViewHolder(view);



        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        // get the data from the data array
        OfficeRoom current = office_list.get(position);

        holder.roomTitle.setText(current.getRoomTitle());
        holder.roomType.setText(current.getRoomType());
        holder.price.setText(String.valueOf(current.getPrice()));
        holder.duration.setText(current.getDuration());

        Glide.with(context)
                .load("http://app.worknasi.com/app-content/uploads/2018/01/" + current.getRoomImage())
                .into(holder.roomImage);
    }


    @Override
    public int getItemCount() {

        if( office_list != null){
            return office_list.size();
        }else{
            return 0;
        }

    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        //public CardView card;
        public ImageView roomImage;
        public TextView roomTitle;
        public TextView roomType;
        public TextView price;
        public TextView duration;

        public MyViewHolder(View  itemView) {
            super(itemView);

            roomImage = itemView.findViewById(R.id.office_room_image);
            roomTitle = itemView.findViewById(R.id.roomTitle);
            roomType = itemView.findViewById(R.id.roomType);
            price = itemView.findViewById(R.id.price);
            duration = itemView.findViewById(R.id.duration);
            //card = itemView.findViewById(R.id.card_view);
        }
    }
}




