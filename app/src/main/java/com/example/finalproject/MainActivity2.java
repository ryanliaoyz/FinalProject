package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import static com.android.volley.Request.Method.POST;

public class MainActivity2 extends AppCompatActivity {

    private EditText sendText;
    private Button send;
    private Button setting;
    private TextView mytext1, mytext2, mytext3, mytext4, mytext5, mytext6, mytext7, mytext8;
    private TextView gtext1, gtext2, gtext3, gtext4, gtext5, gtext6, gtext7, gtext8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        setupUI();
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage(sendText);
            }
        });
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity2.this, Setting.class));
            }
        });
    }

    private void setupUI() {
        sendText = findViewById(R.id.sendText);
        send = findViewById(R.id.send);
        setting = findViewById(R.id.setting);
        mytext1 = findViewById(R.id.mytext1);
        mytext2 = findViewById(R.id.mytext2);
        mytext3 = findViewById(R.id.mytext3);
        mytext4 = findViewById(R.id.mytext4);
        mytext5 = findViewById(R.id.mytext5);
        mytext6 = findViewById(R.id.mytext6);
        mytext7 = findViewById(R.id.mytext7);
        mytext8 = findViewById(R.id.mytext8);
        gtext1 = findViewById(R.id.gtext1);
        gtext2 = findViewById(R.id.gtext2);
        gtext3 = findViewById(R.id.gtext3);
        gtext4 = findViewById(R.id.gtext4);
        gtext5 = findViewById(R.id.gtext5);
        gtext6 = findViewById(R.id.gtext6);
        gtext7 = findViewById(R.id.gtext7);
        gtext8 = findViewById(R.id.gtext8);



    }

    private void sendMessage(View view) {
        TextView[] mytext = {mytext1, mytext2, mytext3, mytext4, mytext5, mytext6, mytext7, mytext8};
        TextView[] gtext = {gtext1, gtext2, gtext3, gtext4, gtext5, gtext6, gtext7, gtext8};
        String Message = sendText.getText().toString();
        if (Message.length() > 0) {
            //send message
            for (int i = 7; i > 0; i--) {
                String temp = mytext[i-1].getText().toString();
                mytext[i].setText(temp);
            }
            mytext[0].setText(Message);
            for (int i = 7; i > 0; i--) {
                String temp = gtext[i-1].getText().toString();
                gtext[i].setText(temp);
            }
            //Some url endpoint that you may have
            String myUrl = "http://45.79.151.162:8080/";
            //String to place our result in
            String result = "I don't understand.";
            //Instantiate new instance of our class
            RetrieveFeedTask getRequest = new RetrieveFeedTask();
            sendText.getText().clear();
            try {
                result = getRequest.execute(Message).get();
            }
            catch (ExecutionException e) {

            }
            catch (InterruptedException e) {

            }
            gtext[0].setText(result);
        }
    }

    private String sendWorkPostRequest(String message) {
        try {
            URL urlToRequest = new URL("https://45.79.151.162:8080/");
            HttpURLConnection urlConnection =
                    (HttpURLConnection) urlToRequest.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");
            urlConnection.setFixedLengthStreamingMode(
                    message.getBytes().length);
            System.out.println(urlConnection.toString());
//            checkExternalStoragePermissions();
            String something = urlConnection.getResponseMessage();
            OutputStream i = urlConnection.getOutputStream();
            PrintWriter out = new PrintWriter(urlConnection.getOutputStream());
            out.print(message);
            out.close();
        }
        catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "1";
    }

    int REQUEST_STORAGE = 1;

    private void checkExternalStoragePermissions() {
        if (hasStoragePermissionGranted()) {
            //You can do what whatever you want to do as permission is granted
        } else {
            requestExternalStoragePermission();
        }
    }

    public boolean hasStoragePermissionGranted(){
        return  ContextCompat.checkSelfPermission(MainActivity2.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    }

    public void requestExternalStoragePermission() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            String[] itt = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
            ActivityCompat.requestPermissions(MainActivity2.this, itt,
                    REQUEST_STORAGE);
        }
    }
}
