package com.example.a210030533_ba203;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class laporanKasusSiswa extends AppCompatActivity {

    ListView listViewKasusSiswa;
    TextView textViewTotalKasusSiswaBermasalah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laporan_kasus_siswa);

        listViewKasusSiswa = findViewById(R.id.ListViewKasusSiswa);
        textViewTotalKasusSiswaBermasalah = findViewById(R.id.textViewLaporanTotalKasusSiswa);

        ArrayList<Siswa> savedData = SaveValue.getMhs();

        Map<String, Integer> kasusPerJenis = new HashMap<>();

        for (Siswa siswa : savedData) {
            for (String jenisMasalah : siswa.getJenisMasalahList()) {
                kasusPerJenis.put(jenisMasalah, kasusPerJenis.getOrDefault(jenisMasalah, 0) + 1);
            }
        }

        ArrayList<String> listKasus = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : kasusPerJenis.entrySet()) {
            String jenisMasalah = entry.getKey();
            int jumlah = entry.getValue();
            listKasus.add(jenisMasalah + "  " + jumlah + " Kasus");
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.list_view, R.id.textViewKasus, listKasus);
        listViewKasusSiswa.setAdapter(adapter);

        int totalKasus = 0;
        for (int jumlah : kasusPerJenis.values()) {
            totalKasus += jumlah;
        }

        textViewTotalKasusSiswaBermasalah.setText("Total Kasus: " + totalKasus);

    }
}
