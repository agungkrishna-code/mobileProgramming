package com.example.pertemuan_7;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class ListViewActivity extends AppCompatActivity {
    ListView myListView_Activity;
    ArrayList<Mahasiswa> arrmhs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        myListView_Activity = findViewById(R.id.myListView);

        if (SaveValue.getMhs().size() > 0) {
            arrmhs = SaveValue.getMhs();
            setAdapterView();
        }
    }

    private void setAdapterView() {
        ArrayAdapter<Mahasiswa> adapter = new MyListAdapter();
        myListView_Activity.setAdapter(adapter);
    }

    private class MyListAdapter extends ArrayAdapter<Mahasiswa> {
        public MyListAdapter() {
            super(ListViewActivity.this, R.layout.item_datalist_view, arrmhs);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_datalist_view, parent, false);
            }
            Mahasiswa mhs = arrmhs.get(position);

            TextView tvNama = convertView.findViewById(R.id.textViewNamaItemListView);
            TextView tvUmur = convertView.findViewById(R.id.textViewUmurItemListView);
            TextView tvNim = convertView.findViewById(R.id.textViewNimItemListView);
            ImageView imv = convertView.findViewById(R.id.imageViewItemListView);

            tvNama.setText(mhs.getNama());
            tvUmur.setText(String.valueOf(mhs.getUmur()) + "thn");
            tvNim.setText(mhs.getNim());
            imv.setImageResource(mhs.getFoto());

            return convertView;
        }
    }
}
