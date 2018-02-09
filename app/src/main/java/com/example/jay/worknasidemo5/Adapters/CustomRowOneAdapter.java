package com.example.jay.worknasidemo5.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.jay.worknasidemo5.Activities.PropertyDetailActivity;
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
    private OnItemClickListener mListener;

    public CustomRowOneAdapter(Context context, List<OfficeRoom> list){
        this.context = context;
        this.office_list = list;

    }

   /* public void add(OfficeRoom room){
        office_list.add(room);
    }
    */

     public interface OnItemClickListener{
          void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.mListener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view =  inflater.from(context).inflate(R.layout.custom_row_one, parent, false);

        MyViewHolder holder = new MyViewHolder(view);



        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        // get the data from the data array
        final OfficeRoom current = office_list.get(position);

        holder.roomTitle.setText(current.getRoomTitle());
        holder.roomType.setText(current.getRoomType());
        holder.price.setText(String.valueOf(current.getPrice()));
        holder.duration.setText(current.getDuration());

        Glide.with(context)
                .load("http://app.worknasi.com/app-content/uploads/2018/01/" + current.getRoomImage())
                .into(holder.roomImage);

       /* holder.topLinear.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Toast.makeText(context, "Item " + position, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(context, PropertyDetailActivity.class)
                               .putExtra("position", position);
                        context.startActivity(intent);
                    }
                }
        );
        */
    }


    @Override
    public int getItemCount() {

        if( office_list != null){
            return office_list.size();
        }else{
            return 0;
        }

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public LinearLayout topLinear;
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
            topLinear = itemView.findViewById(R.id.topLinear);

            itemView.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                             if(mListener != null){
                                  int position = getAdapterPosition();
                                  if(position != RecyclerView.NO_POSITION){
                                      mListener.onItemClick(position);
                                  }
                             }
                        }
                    }
            );
        }
    }
}




