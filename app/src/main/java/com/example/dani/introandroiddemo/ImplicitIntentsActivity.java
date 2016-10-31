package com.example.dani.introandroiddemo;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class ImplicitIntentsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_implicit_intents);
    }

    public void visitUrlAddress(View view) {
        Uri url = getUriToVisit();
        Log.d("DEBUG", url.toString());

        if (url != null) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(url);
            startActivity(intent);
        }
    }

    public Uri getUriToVisit() {
        TextView txtUrlAddress = (TextView) findViewById(R.id.txtUrlAddress);
        String urlAddress = txtUrlAddress.getText().toString();

        if (urlAddress != null) {
            if (! urlAddress.startsWith("https://"))
                urlAddress = "http://" + urlAddress;

            return Uri.parse(urlAddress);
        } else {
            return null;
        }
    }
}
