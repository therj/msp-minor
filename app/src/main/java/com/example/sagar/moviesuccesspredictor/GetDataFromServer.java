package com.example.sagar.moviesuccesspredictor;

import android.text.AndroidCharacter;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Sagar on 5/15/2017.
 */

public class GetDataFromServer {


    public String getData(String uri) throws IOException{
      //StringBuilder sb= new StringBuilder();
        String returned;
        String parsedxml;
        URL url = new URL(uri);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            urlConnection.setDoOutput(true);
            urlConnection.setChunkedStreamingMode(0);
            urlConnection.setConnectTimeout(5 * 1000);
            //OutputStream out = new BufferedOutputStream(urlConnection.getOutputStream());
            //writeStream(out,textTosend);


            int statusCode = urlConnection.getResponseCode();

            InputStream in ;

            if (statusCode >= 200 && statusCode < 400) {
                // Create an InputStream in order to extract the response object
             in = new BufferedInputStream(urlConnection.getInputStream()); }
            else {
                in = new BufferedInputStream(urlConnection.getErrorStream());}


            returned   = readStream(in);
            ParseXML pm=new ParseXML();

            parsedxml=pm.parse(returned);
        } finally {
            //regardkless of success or faliure we disconnect the connection
            urlConnection.disconnect();
        }



        return parsedxml ;

    }
    private void writeStream(OutputStream out,String textTosend) throws IOException{

        out.write(textTosend.getBytes());
        out.flush();
    }
    private String readStream(InputStream in) throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(in));
        StringBuilder result = new StringBuilder();
        String line;
        while((line = br.readLine()) != null) {
            result.append(line);
        }
        return (result.toString());
    }

}
