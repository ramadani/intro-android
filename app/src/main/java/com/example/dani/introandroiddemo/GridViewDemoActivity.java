package com.example.dani.introandroiddemo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class GridViewDemoActivity extends AppCompatActivity {

    GridView gvImages;
    GridImageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view_demo);

        populateGridViewImages();
    }

    private void populateGridViewImages() {
        gvImages = (GridView) findViewById(R.id.gvImages);
        String[] numbers = new String[] { "ad", "ae", "af", "ag", "ai", "al"};
        adapter = new GridImageAdapter(this, android.R.layout.simple_list_item_1, numbers);
        gvImages.setAdapter(adapter);
    }

    class GridImageAdapter extends ArrayAdapter<String> {

        public GridImageAdapter(Context context, int id, String[] numbers) {
            super(context, id, numbers);
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView = new ImageView(GridViewDemoActivity.this);
            int resId = getResources().getIdentifier(getItem(position), "drawable", getPackageName());
            imageView.setImageDrawable(getResources().getDrawable(resId));

            return imageView;
        }

    }
}
