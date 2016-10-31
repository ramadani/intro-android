package com.example.dani.introandroiddemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class SimpleBundleDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_bundle_demo);

        String initialText = getIntent().getStringExtra("text");
        TextView tvDisplayText = (TextView) findViewById(R.id.tvDisplayText);

        if (initialText != null) {
            Log.d("DEBUG", initialText);
            tvDisplayText.setText(initialText);
        }
//        getActionBar().setHomeButtonEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_simple_bundle_demo, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_home:
                Intent intent = new Intent(this, ActionBarMenuActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }

        return true;
    }
}
