package com.example.pertemuan10_readjson;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ActivitySearch extends AppCompatActivity {

    ImageView imv;
    EditText etNim;

    Button btsearch;

    Mahasiswa mhs;

    TextView tvNama, tvUmur;

    RequestQueue mRequestQueue;
    RequestQueue mRequestQueueImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        imv = findViewById(R.id.imageViewGambar2);
        etNim = findViewById(R.id.editTextNim);
        btsearch = findViewById(R.id.buttonCari);
        tvUmur = findViewById(R.id.textViewUmur1);
        tvNama = findViewById(R.id.textViewNama1);

        btsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchData(etNim.getText().toString());
            }
        });
    }

    private void viewData() {
        tvNama.setText("Nama\t:" + mhs.getNama());
        tvUmur.setText("Umur\t:" + mhs.getUmur());

        ImageRequest requestImage = new ImageRequest(URLs.URL_LOADIMAGE + mhs.getMyFoto(),
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        imv.setImageBitmap(response);
                    }
                }, 0, 0, ImageView.ScaleType.FIT_XY, Bitmap.Config.RGB_565,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ActivitySearch.this, "Error Load Image",
                                Toast.LENGTH_SHORT).show();
                    }
                });
        mRequestQueueImage.add(requestImage);
    }

    private void searchData(String Nim) {
        mRequestQueue = Volley.newRequestQueue(this);
        mRequestQueueImage = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_LOAD_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            if (!obj.getBoolean("error")) {
                                JSONArray dataMhsArray = obj.getJSONArray("data");
                                for (int i = 0; i < dataMhsArray.length(); i++) {
                                    JSONObject dataMhsObj = dataMhsArray.getJSONObject(i);
                                    mhs = new Mahasiswa(
                                            dataMhsObj.getString("Nim"),
                                            dataMhsObj.getString("Nama"),
                                            dataMhsObj.getInt("Umur"),
                                            dataMhsObj.getString("MyFoto")
                                    );
                                }
                                viewData();
                            } else {
                                Toast.makeText(ActivitySearch.this, obj.getString("message"),
                                        Toast.LENGTH_SHORT).show();
                            }

                        } catch (Exception e) {
                            Toast.makeText(ActivitySearch.this, e.getMessage(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ActivitySearch.this, error.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("Nim", Nim);
                return params;
            }
        };

        mRequestQueue.add(stringRequest);
    }
}
