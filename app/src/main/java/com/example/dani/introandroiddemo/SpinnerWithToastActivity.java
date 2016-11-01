package com.example.dani.introandroiddemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class SpinnerWithToastActivity extends AppCompatActivity {
    Spinner spinner;
    Button btnSpinnerVal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner_with_toast);

        spinner = (Spinner) findViewById(R.id.spnOptions);
        btnSpinnerVal = (Button) findViewById(R.id.btnSpinnerValue);

        loadSpinner();
    }

    private void loadSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
            this,
            R.array.spinner_options,
            android.R.layout.simple_spinner_item
        );

        // set layout style during dropdown
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // load data from adapter
        spinner.setAdapter(adapter);
    }

    public void displayVal(View view) {
        Toast.makeText(this, spinner.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
    }
}
