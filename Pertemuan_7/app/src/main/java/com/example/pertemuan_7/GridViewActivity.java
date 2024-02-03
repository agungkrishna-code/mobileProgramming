package com.example.pertemuan_7;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class GridViewActivity extends AppCompatActivity {
    GridView myGridView;
    ArrayList<Mahasiswa> arrmhs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);

        myGridView = findViewById(R.id.myGridView);

        if (SaveValue.getMhs().size() > 0) {
            arrmhs = SaveValue.getMhs();
            setAdapterView();
        }
    }

    private void setAdapterView() {
        ArrayAdapter<Mahasiswa> adapter = new MyGridAdapter();
        myGridView.setAdapter(adapter);
    }

    private class MyGridAdapter extends ArrayAdapter<Mahasiswa> {
        public MyGridAdapter() {
            super(GridViewActivity.this, R.layout.data_item_gridview, arrmhs);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.data_item_gridview, parent, false);
            }
            Mahasiswa mhs = arrmhs.get(position);

            TextView tvNim = (TextView) convertView.findViewById(R.id.textViewNimItemGridView);
            ImageView imgV = (ImageView) convertView.findViewById(R.id.imageViewItemGridView);

            tvNim.setText(mhs.getNim() + "\n" + mhs.getNama() + "\n" + String.valueOf(mhs.getUmur()));
            imgV.setImageResource(mhs.getFoto());
            return convertView;
        }
    }
}