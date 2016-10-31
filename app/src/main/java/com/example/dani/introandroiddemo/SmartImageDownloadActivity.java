package com.example.dani.introandroiddemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.BinaryHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

public class SmartImageDownloadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("DEBUG", "Great mbah");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smart_image_download);
        downloadSmartImageFromUrl("https://myanimelist.cdn-dena.com/images/anime/9/77484l.jpg");
    }

    private void downloadSmartImageFromUrl(String address) {
        AsyncHttpClient client = new AsyncHttpClient();

        Log.d("DEBUG", "Great gan");

        client.get(address, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(responseBody, 0, responseBody.length);
                ImageView imageView = (ImageView) findViewById(R.id.ivSmartImage);
                imageView.setImageBitmap(bitmap);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }
}
