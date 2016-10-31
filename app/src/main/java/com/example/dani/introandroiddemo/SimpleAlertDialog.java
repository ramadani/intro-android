package com.example.dani.introandroiddemo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class SimpleAlertDialog {
    public static void displayWithOk(Context context, String message) {
        displayWithOk(context, message, "Belajar Android");
    }

    public static void displayWithOk(Context context, String message, String title) {
        final AlertDialog alertDialog = new AlertDialog.Builder(context).create();

        alertDialog.setTitle(title);
        alertDialog.setMessage(message);

        // setting ok button
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                alertDialog.hide();
            }
        });

        // show alert
        alertDialog.show();
    }
}
