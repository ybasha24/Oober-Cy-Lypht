package com.example.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.method.ScrollingMovementMethod;
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
    TextView conversation;
    private String receiverEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        sendButton = findViewById(R.id.sendMessageButton);
        message = findViewById(R.id.messageEditText);
        conversation = findViewById(R.id.conversationTV);
        conversation.setMovementMethod(new ScrollingMovementMethod());


        receiverEmail = getIntent().getStringExtra("receiverEmail");
        connect();


        sendButton.setOnClickListener(v -> {
            try {
                Log.e("error", "trying to send message: " + "@" + receiverEmail + " " + message.getText().toString());
                cc.send("@" + receiverEmail + " " + message.getText().toString());
            } catch (Exception e) {
                Log.e("error:", e.getMessage());
            }
        });
    }

    private void connect(){

        Draft[] drafts = {
                new Draft_6455()
        };

        String url = "ws://coms-309-030.class.las.iastate.edu:8080/chat/%7B" + MainActivity.accountEmail + "%7D";

        try {
            Log.e("error:", "Trying socket");
            cc = new WebSocketClient(new URI(url), (Draft) drafts[0]) {
                @Override
                public void onMessage(String message) {
                    Log.e("error", "run() returned: " + message);

                    new Handler(Looper.getMainLooper()).post(new Runnable(){
                        @Override
                        public void run() {
                            String s = conversation.getText().toString();
                            conversation.setText(s + "\n" + message);
                        }
                    });
//                    conversation.setText(s + "\n" + message);
                }

                @Override
                public void onOpen(ServerHandshake handshake) {
                    Log.e("error", "run() returned: " + "is connecting");
                }

                @Override
                public void onClose(int code, String reason, boolean remote) {
                    Log.e("error", "onClose() returned: " + reason);
                }

                @Override
                public void onError(Exception e) {
                    Log.e("error:", e.toString());
                }
            };
        } catch (URISyntaxException e) {
            Log.e("error", e.toString());
        }
        cc.connect();
    }




}
