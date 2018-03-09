package com.test.papthegeek.demoproject;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class detailActivity extends AppCompatActivity {

    TextView title;
    TextView date;
    TextView time;
    TextView price;
    TextView description;
    ImageView image;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        System.out.println("DetailActivity has been triggered!!!!");

        //INITIALIZE VIEW
        title = (TextView) findViewById(R.id.detailTitle);
        image = (ImageView) findViewById(R.id.detailImage);
        price = (TextView) findViewById(R.id.detailprice);
        date = (TextView) findViewById(R.id.detailDate);

        //RECEIVE DATA
        Intent intent = this.getIntent();
        String name = intent.getExtras().getString("TITLE_KEY");
        String img =intent.getExtras().getString("IMAGE_KEY");
        String prix = "Price: $20.00";
        String eventDate = "Date: 2017-12-12";


        //BIND DATA
        title.setText(name);
        price.setText(prix);
        date.setText(eventDate);
        Glide.with(this).load(img).into(image);

    }
}
