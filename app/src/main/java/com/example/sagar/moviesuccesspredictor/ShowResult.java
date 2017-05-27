package com.example.sagar.moviesuccesspredictor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 *
 * Created by Sagar on 5/8/2017.
 */

public class ShowResult extends AppCompatActivity{
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_result);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        String name, image_url,release_date,prediciton;

        name=extras.getString("name");
        image_url=extras.getString("image_url");
        release_date=extras.getString("release_date");
        prediciton=extras.getString("prediciton");

        ImageView img=(ImageView) findViewById(R.id.imageViewshow);
        TextView tv1=(TextView)findViewById(R.id.textView3);
        TextView tv2=(TextView)findViewById(R.id.textView2);

        tv1.setText(name);
        tv2.setText(release_date);
        Picasso.with(this)
                .load(image_url).fit().
                placeholder(R.drawable.aa).error(R.drawable.ab)
                .into(img);



    }
}
