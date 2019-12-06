package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ScrollView;

public class MainActivity2 extends AppCompatActivity {

    private EditText sendText;
    private Button send;
    private Button setting;
//    private MessageAdapter messageAdapter;
//    private ListView messageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        setupUI();
//        messageAdapter = new MessageAdapter(this);
        sendText = (EditText) findViewById(R.id.sendText);
//        messageView = (ListView) findViewById(R.id.messageView);
//        messageView.setAdapter(messageAdapter);
        setting = findViewById(R.id.setting);
        send = findViewById(R.id.send);
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
    }

    private void sendMessage(View view) {
        String messsage = sendText.getText().toString();
        if (messsage.length() > 0) {
            //send message
            sendText.getText().clear();
        }
    }
}
