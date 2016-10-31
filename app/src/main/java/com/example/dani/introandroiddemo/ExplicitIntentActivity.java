package com.example.dani.introandroiddemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class ExplicitIntentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explicit_intent);

        Button btnLaunchSecond = (Button) findViewById(R.id.btnLaunchSecond);
        btnLaunchSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("DEBUG", "Call second activity");
                Intent intent = new Intent(ExplicitIntentActivity.this, SimpleBundleDemoActivity.class);
                intent.putExtra("text", "Passed String Extra");
                startActivity(intent);
            }
        });
    }
}
