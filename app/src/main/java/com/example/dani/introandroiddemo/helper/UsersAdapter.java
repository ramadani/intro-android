package com.example.dani.introandroiddemo.helper;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.dani.introandroiddemo.R;

import java.util.ArrayList;

public class UsersAdapter extends ArrayAdapter<User> {
    public UsersAdapter(Context context, ArrayList<User> users) {
        super(context, 0, users);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        User user = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_user, parent, false);
        }

        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.tvUserName);
        TextView tvHometown = (TextView) convertView.findViewById(R.id.tvUserHometown);

        // Populate the data into the template view using the data object
        tvName.setText(user.name);
        tvHometown.setText(user.hometown);

        // Return the completed view to render on screen
        return convertView;
    }
}
