package com.example.sagar.moviesuccesspredictor;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

/**
 * Created by Sagar on 5/16/2017.
 */

public class ShowForFirstFragment extends AppCompatActivity {

    String unformatted;
    Toolbar toolbar;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_one_fragment);


        toolbar=(android.support.v7.widget.Toolbar) findViewById(R.id.this_toolbar);
        setSupportActionBar(toolbar);

        Bundle extras = getIntent().getExtras();
        unformatted = extras.getString("unsplitted");

        TextView tv=(TextView) findViewById(R.id.textView7);
        tv.setText(unformatted);

    }
}
