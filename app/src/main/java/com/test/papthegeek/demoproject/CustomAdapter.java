package com.test.papthegeek.demoproject;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.test.papthegeek.demoproject.senproCloud.ItemClickListener;

import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * Created by PapTheGeek on 11/15/17.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder>{

    private Context context;
    private List<MyData> my_data;


    public CustomAdapter(Context context, List<MyData> my_data) {
        this.context = context;
        this.my_data = my_data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card,parent,false);

        return new ViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final String eventTitle = my_data.get(position).getEventTitle();
        final String eventImage = my_data.get(position).getImage_link();
        /*holder.description.setText(my_data.get(position).getDescription());*/
        holder.title.setText(eventTitle);
        holder.price.setText("Price: " + my_data.get(position).getTicketPrice());
        holder.eventDate.setText(my_data.get(position).getEventDate());
        /*holder.eventEndTime.setText(my_data.get(position).getEventEndTime());*/
        Glide.with(context).load(eventImage).into(holder.imageView);


        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                openDetailActivity(eventTitle,eventImage);
                Toast.makeText(context,eventTitle,Toast.LENGTH_SHORT).show();
            }
        });


    }



    @Override
    public int getItemCount() {
        return my_data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView description;
        public TextView title;
        public TextView eventDate;
        public TextView price;
        public TextView eventStartTime;
        public TextView eventEndTime;
        public ImageView imageView;
        ItemClickListener itemClickListener;



        public ViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.title);
            eventDate = (TextView) itemView.findViewById(R.id.eventDate);
            price = (TextView) itemView.findViewById(R.id.price);
            imageView = (ImageView) itemView.findViewById(R.id.image);

            itemView.setOnClickListener(this);
        }


        public void setItemClickListener(ItemClickListener itemClickListener){
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View view) {
            this.itemClickListener.onItemClick(this.getLayoutPosition());
        }

    }
    private void openDetailActivity(String title, String image){
        Intent intent = new Intent(context, detailActivity.class);

        //Pack Data to send
        intent.putExtra("TITLE_KEY", title);
        intent.putExtra("IMAGE_KEY", image);

        //open activity
        context.startActivity(intent);
    }
}
