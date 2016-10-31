package com.example.dani.introandroiddemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class IntentWithResultActivity extends AppCompatActivity {

    final static int GET_RESULT_TEXT = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_with_result);
    }

    public void enterText(View view) {
        startActivityForResult(
            new Intent(IntentWithResultActivity.this, SimpleReturnResultActivity.class),
            GET_RESULT_TEXT
        );
    }

    // handle the result once the activity returns a result, display contact
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("DEBUG", "request code " + requestCode + " result code " + resultCode);

        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                TextView tvResult = (TextView) findViewById(R.id.txtDisplayResult);
                tvResult.setText(data.getStringExtra("result"));

                Toast.makeText(this, data.getStringExtra("result"), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
