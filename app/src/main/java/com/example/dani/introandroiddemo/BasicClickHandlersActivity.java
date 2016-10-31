package com.example.dani.introandroiddemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BasicClickHandlersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_click_handlers);

        final Button secondButton = (Button) findViewById(R.id.btnClick2);
        secondButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                secondButtonClicked(view);
            }
        });
    }

    public void firstButtonClicked(View view) {
        SimpleAlertDialog.displayWithOk(this, "first button clicked via xml handler");
    }

    public void secondButtonClicked(View view) {
        SimpleAlertDialog.displayWithOk(this, "second button clicked via java handler onCreate");
    }
}
