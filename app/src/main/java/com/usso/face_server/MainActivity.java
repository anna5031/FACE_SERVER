package com.usso.face_server;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.textclassifier.TextLinks;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private TextView pagenameTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url("http://192.168.0.144:5000/").build();
        pagenameTextView = findViewById(R.id.pagename);

        Intent intent = new Intent(MainActivity.this, SendImageActivity.class);
        startActivity(intent);
        finish();
        /*
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            // called if server is unreachable
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "server down", Toast.LENGTH_SHORT).show();
                        pagenameTextView.setText("error connecting to the server");
                    }
                });
            }

            @Override
            // called if we get a
            // response from the server
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                //pagenameTextView.setText(response.body().string());
                //Intent intent = new Intent(MainActivity.this, SendImageActivity.class);
                //startActivity(intent);
                //finish();
                //Log.w("mainactivity", "onResponse() 호출됨.");
            }
        });

        */
    }
}