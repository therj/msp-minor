package com.example.sagar.moviesuccesspredictor;

/**
 * Created by Sagar on 5/6/2017.
 */

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.squareup.picasso.Picasso;

import java.io.IOException;

import static com.example.sagar.moviesuccesspredictor.R.layout.fragment_one;


public class OneFragment extends Fragment{
    private ProgressBar pb;
    private String send;
    public OneFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment



        View view = inflater.inflate(fragment_one, container, false);
         pb=(ProgressBar) view.findViewById(R.id.progressBar);
        pb.setVisibility(View.INVISIBLE);
        Button button = (Button) view.findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String concat=readFromViewsAndConcat(v);
                ConnectToServer connect=new ConnectToServer();
                connect.execute(concat);

                // do something
            }
        });

        return view;
    }

    private String readFromViewsAndConcat(View v){


        Spinner mySpinner=(Spinner) getView().findViewById(R.id.spinner);
        String actor =mySpinner.getSelectedItem().toString();

        Spinner mySpinner1=(Spinner) getView().findViewById(R.id.spinner1);
        String actor1 = mySpinner1.getSelectedItem().toString();

        Spinner mySpinner2=(Spinner) getView().findViewById(R.id.spinner2);
        String actor2 = mySpinner2.getSelectedItem().toString();

        Spinner mySpinner3=(Spinner) getView().findViewById(R.id.spinner3);
        String actor3 = mySpinner3.getSelectedItem().toString();


        Spinner mySpinner4=(Spinner) getView().findViewById(R.id.spinner5);
        String actress = mySpinner4.getSelectedItem().toString();

        Spinner mySpinner5=(Spinner) getView().findViewById(R.id.spinner6);
        String actress1 = mySpinner5.getSelectedItem().toString();

        Spinner mySpinner6=(Spinner) getView().findViewById(R.id.spinner7);
        String actress2 = mySpinner6.getSelectedItem().toString();


        Spinner mySpinner7=(Spinner) getView().findViewById(R.id.spinner8);
        String director = mySpinner7.getSelectedItem().toString();

        Spinner mySpinner8=(Spinner) getView().findViewById(R.id.spinner9);
        String producer = mySpinner8.getSelectedItem().toString();

        EditText edittext=(EditText) getView().findViewById(R.id.et);
        String budget = edittext.getText().toString();

        Spinner mySpinner9=(Spinner) getView().findViewById(R.id.genera_spinner);
        String genera = mySpinner9.getSelectedItem().toString();

        String concat=actor+"*"+actor1+"*"+actor2+"*"+actor3+"*"+actress+"*"+actress1+"*"+actress2+"*"
                +director+"*"+producer+"*"+budget+"*"+genera;

        return concat;
        }

    public class ConnectToServer extends AsyncTask<String,String,String> {



        String url= "http://192.168.0.103:8080/msp/services/WebServiceForMSP/calculate?concatinated_input=";
        String returnS;

        @Override
        protected void onPreExecute() {
            //has access to main thread(i.e UI thread)
            pb.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... params) {
            //all code here runs in background thread
            GetDataFromServer getdata=new GetDataFromServer();
            try {
                returnS=getdata.getData(url+params[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return returnS;
        }

        @Override
        protected void onPostExecute(String s) {
            //also has access to main theread
            pb.setVisibility(View.INVISIBLE);
            send=s;
            Intent intent=new Intent(getContext(),ShowForFirstFragment.class);
            intent.putExtra("unsplitted", send);
            startActivity(intent);
        }

    }

}
