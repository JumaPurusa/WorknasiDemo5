package com.example.jay.worknasidemo5.Adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.jay.worknasidemo5.Model.OfficeRoom;
import com.example.jay.worknasidemo5.R;

import java.util.List;

/**
 * Created by Jay on 11/21/2017.
 */


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {


    Context context;
    List<OfficeRoom> office_list = null;

    public CustomAdapter(Context context, List<OfficeRoom> list){

        this.context = context;
        this.office_list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =   LayoutInflater.from(context).inflate(R.layout.custom_row, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        // get the data from the data array
        OfficeRoom current = office_list.get(position);

        Glide.with(context).load("http://app.worknasi.com/app-content/uploads/2018/01/"
                + current.getRoomImage()).into(holder.roomImage);
        holder.roomTitle.setText(current.getRoomTitle());
        holder.roomType.setText(current.getRoomType());
        holder.price.setText(String.valueOf(current.getPrice()));
        holder.duration.setText(current.getDuration());

        holder.card.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, "Item " + position, Toast.LENGTH_SHORT).show();
                    }
                }
        ); 
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

        public CardView card;
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
            card = itemView.findViewById(R.id.card_view);
        }
    }

}
