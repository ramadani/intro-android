package com.example.dani.introandroiddemo;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class PersistSettingsActivity extends AppCompatActivity {

    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    TextView txtPersist;
    CheckBox chkPersist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persist_settings);

        txtPersist = (TextView) findViewById(R.id.etPersistText);
        chkPersist = (CheckBox) findViewById(R.id.chkPersist);

        prefs = getSharedPreferences("view", 0);
        editor = prefs.edit();

        populateValues();
    }

    public void populateValues() {
        String persistedText = prefs.getString("txtVal", "None Stored Yest");
        boolean isChecked = prefs.getBoolean("chkState", false);

        txtPersist.setText(persistedText);
        chkPersist.setChecked(isChecked);
    }

    public void persistValues(View view) {
        editor.putString("txtVal", txtPersist.getText().toString());
        editor.putBoolean("chkState", chkPersist.isChecked());
        editor.commit();

        Toast.makeText(this, "Persisted!", Toast.LENGTH_SHORT).show();
    }
}
