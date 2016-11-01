package com.example.dani.introandroiddemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ToastFormInputsActivity extends AppCompatActivity {

    EditText etVal;
    CheckBox chkVal;
    RadioGroup rdgVal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast_form_inputs);

        etVal = (EditText) findViewById(R.id.etVal);
        chkVal = (CheckBox) findViewById(R.id.chkVal);
        rdgVal = (RadioGroup) findViewById(R.id.rdgVal);
    }

    public void toastInput(View view) {
        int selected = rdgVal.getCheckedRadioButtonId();
        RadioButton radioButton = (RadioButton) findViewById(selected);

        String text = etVal.getText() + " | " + chkVal.isChecked() + " | " + radioButton.getText();

        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}
