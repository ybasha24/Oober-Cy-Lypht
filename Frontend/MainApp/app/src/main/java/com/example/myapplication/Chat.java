package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;


public class Chat extends AppCompatActivity {

    private Button sendButton;
    private WebSocketClient cc;
    private EditText message;
    private TextView conversation;
    private String receiverEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        sendButton = findViewById(R.id.sendMessageButton);
        message = findViewById(R.id.messageEditText);
        conversation = findViewById(R.id.conversationTV);
        receiverEmail = getIntent().getStringExtra("receiverEmail");
        connect();

        sendButton.setOnClickListener(v -> {
            try {
                cc.send("@" + receiverEmail + " " + message.getText().toString());
            } catch (Exception e) {
                Log.d("ExceptionSendMessage:", e.getMessage().toString());
            }
        });
    }

    private void connect(){

        Draft[] drafts = {
                new Draft_6455()
        };

        String url = "ws://coms-309-030.class.las.iastate.edu:8080/chat/{" + MainActivity.accountEmail + "}";

        try {
            Log.d("Socket:", "Trying socket");
            cc = new WebSocketClient(new URI(url), (Draft) drafts[0]) {
                @Override
                public void onMessage(String message) {
                    Log.d("", "run() returned: " + message);
                    String s = conversation.getText().toString();
                    conversation.setText(s + "\nServer:" + message);
                }

                @Override
                public void onOpen(ServerHandshake handshake) {
                    Log.d("OPEN", "run() returned: " + "is connecting");
                }

                @Override
                public void onClose(int code, String reason, boolean remote) {
                    Log.d("CLOSE", "onClose() returned: " + reason);
                }

                @Override
                public void onError(Exception e) {
                    Log.d("Exception:", e.toString());
                }
            };
        } catch (URISyntaxException e) {
            Log.d("Exception:", e.getMessage().toString());
            e.printStackTrace();
        }
        cc.connect();
    }




}
