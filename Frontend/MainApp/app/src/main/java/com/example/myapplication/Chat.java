package com.example.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.endpoints.Endpoints;

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
    private ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        sendButton = findViewById(R.id.sendMessageButton);
        message = findViewById(R.id.messageEditText);
        conversation = findViewById(R.id.conversationTV);
        conversation.setMovementMethod(new ScrollingMovementMethod());
        scrollView = findViewById(R.id.chatScrollView);


        receiverEmail = getIntent().getStringExtra("receiverEmail");
        connect();

        sendButton.setOnClickListener(v -> {
            try {
                Log.e("error", "sending message: " + "@" + receiverEmail + " " + message.getText().toString());
                cc.send("@" + receiverEmail + " " + message.getText().toString());
            } catch (Exception e) {
                Log.e("error:", e.getMessage());
            }
        });
    }

    private void connect(){

        Draft[] drafts = {new Draft_6455()};

        String url = Endpoints.ChatUrl + MainActivity.accountEmail + "%7D";

        try {
            Log.e("error:", "Trying socket");
            cc = new WebSocketClient(new URI(url), drafts[0]) {
                @Override
                public void onMessage(String message) {
                    Log.e("error", "run() returned: " + message);

                    new Handler(Looper.getMainLooper()).post(() -> {
                        scrollView.fullScroll(View.FOCUS_DOWN);
                        String s = conversation.getText().toString();
                        conversation.setText(s + "\n" + message);
                        new Handler(Looper.getMainLooper()).post(() -> scrollView.fullScroll(View.FOCUS_DOWN));
                    });
                }

                @Override
                public void onOpen(ServerHandshake handshake) { Log.e("error", "run() returned: " + "is connecting"); }

                @Override
                public void onClose(int code, String reason, boolean remote) { Log.e("error", "onClose() returned: " + reason); }

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
