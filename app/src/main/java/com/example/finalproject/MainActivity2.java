package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import org.w3c.dom.Text;


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
            gtext[0].setText("Hi frustrated student");
            sendText.getText().clear();

        }
    }
}
