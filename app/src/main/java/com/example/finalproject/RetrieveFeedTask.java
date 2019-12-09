package com.example.finalproject;

import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class RetrieveFeedTask extends AsyncTask<String, Void, String> {
    public static final String REQUEST_METHOD = "POST";
    public static final int READ_TIMEOUT = 15000;
    public static final int CONNECTION_TIMEOUT = 15000;
    public static final String URL = "http://45.79.151.162:8080/";
    @Override
    protected String doInBackground(String... params){
        String stringUrl = URL;
        String message = params[0];
        String result;
        String inputLine;
        try {
            //Create a URL object holding our url
            URL myUrl = new URL(stringUrl);
            //Create a connection
            HttpURLConnection connection =(HttpURLConnection)
                    myUrl.openConnection();
            //Set methods and timeouts
            connection.setRequestMethod(REQUEST_METHOD);
            connection.setReadTimeout(READ_TIMEOUT);
            connection.setConnectTimeout(CONNECTION_TIMEOUT);
            connection.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");
            //Connect to our url
            connection.connect();
            //Create a new InputStreamReader
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(message);
            wr.flush();
            wr.close();
            InputStream in = new BufferedInputStream(connection.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder test = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                test.append(line);
            }
            result = test.toString();
            System.out.println(result);
//            InputStreamReader streamReader = new
//                    InputStreamReader(connection.getInputStream());
//            //Create a new buffered reader and String Builder
//            BufferedReader reader = new BufferedReader(streamReader);
//            StringBuilder stringBuilder = new StringBuilder();
//            //Check if the line we are reading is not null
//            while((inputLine = reader.readLine()) != null){
//                stringBuilder.append(inputLine);
//            }
//            //Close our InputStream and Buffered reader
//            reader.close();
//            streamReader.close();
//            //Set our result equal to our stringBuilder
//            result = stringBuilder.toString();
        }
        catch(IOException e){
            e.printStackTrace();
            result = null;
        }
        return result;
    }
    protected void onPostExecute(String result){
        super.onPostExecute(result);
    }
}