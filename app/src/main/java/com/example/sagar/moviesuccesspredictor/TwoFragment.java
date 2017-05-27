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
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.IOException;


public class TwoFragment extends Fragment{

    //arrays oholding all the data
    private String[] movie_name=new String[5];
    private String[] image_url=new String[5];
    private String[] release_date=new String[5];
    private String[] prediction=new String[5];

    GridView grid;



    public TwoFragment() {
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
        View view = inflater.inflate(R.layout.fragment_two, container, false);

       ConnectToServer connect= new ConnectToServer();
        connect.execute();



        //ImageView imageView = (ImageView) view.findViewById(R.id.imageView1);

        grid=(GridView)view.findViewById(R.id.gridview);

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Intent intent = new Intent(getActivity(), ShowResult.class);
                Bundle extras = new Bundle();

                extras.putString("name",movie_name[position]);
                extras.putString("image_url",image_url[position]);
                extras.putString("release_date",release_date[position]);
                extras.putString("prediciton",prediction[position]);
                intent.putExtras(extras);

                startActivity(intent);
            }
        });

        //receiving the data from database and populating it to the adapter
            /*DataReceiver data= new DataReceiver(getContext());
            movie_name=data.getMovie_name();
        DataReceiver data1= new DataReceiver(getContext());
            image_url=data1.getImagelink();
        DataReceiver data2= new DataReceiver(getContext());

        release_date=data2.getRelease_date();
        DataReceiver data3= new DataReceiver(getContext());

        prediction=data3.getPrediction();
        */
        return view;
    }
    public class ConnectToServer extends AsyncTask<String,String,String> {

        String url= "http://192.168.0.103:8080/msp/services/WebServiceForMSP/sendCurrentMovies";
        String returnS;

        @Override
        protected void onPreExecute() {
            //has access to main thread(i.e UI thread)
        }

        @Override
        protected String doInBackground(String... params) {
            //all code here runs in background thread
            GetDataFromServer getdata=new GetDataFromServer();
            try {
                returnS=getdata.getData(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return returnS;
        }

        @Override
        protected void onPostExecute(String s) {
            //also has access to main theread
            seperateAndStore(s);

            CustomAdapter adapter = new CustomAdapter(getActivity(), movie_name, image_url,release_date);

            grid.setAdapter(adapter);
           // adapter.updateMovies(movie_name);
            adapter.notifyDataSetChanged();



        }

    }

    private void seperateAndStore(String concatinated){
        //this method sepereates the concatinated string obtained from the server and stores the data into respective arraylists

        //splitting the  names,release_date,prediciton and image_url
       //Regular Expressions are use to split
        String[] split= concatinated.split("\\^");

        //again splitting the parts into individual elements
        String[]names=split[0].split("\\*");
        String[]url=split[1].split("\\*");
        String[]release_date1=split[2].split("\\*");
        String[]prediciton_local=split[3].split("\\*");

        movie_name=names;
        image_url=url;
        release_date=release_date1;
        prediction=prediciton_local;

    }

}
