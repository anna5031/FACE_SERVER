package com.usso.face_server;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class DummyActivity extends AppCompatActivity {

    private EditText editText;
    private Button button;
    private OkHttpClient okHttpClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dummy);
        editText = findViewById(R.id.dummy_text);
        button = findViewById(R.id.dummy_send);
        okHttpClient = new OkHttpClient();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 //  String Upload
                String dummyText = editText.getText().toString();

                // we add the information we want to send in
                // a form. each string we want to send should
                // have a name. in our case we sent the
                // dummyText with a name 'sample'
                RequestBody formbody = new FormBody.Builder().add("sample", dummyText).build();

/*
                //JPG upload
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                Bitmap bitmap = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.heart);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                RequestBody formbody = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addFormDataPart("image", "androidFlask.jpg", RequestBody.create(MultipartBody.FORM, byteArray))
                        .build();
*/

                // while building request
                // we give our form
                // as a parameter to post()
                Request request = new Request.Builder().url("http://192.168.0.144:5000/debug")
                        .post(formbody)
                        .build();
                okHttpClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        call.cancel();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(), "server down", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        if (response.body().string().equals("received")) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    editText.setText("clear");
                                    Toast.makeText(getApplicationContext(), "data received", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                });
            }
        });
    }
}