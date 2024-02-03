package com.example.pertemuan10_readjson;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ActivityViewAll extends AppCompatActivity {
    ImageView imv;
    TextView tvNama, tvNim, tvUmur;

    Button btprev, btnext;

    private ArrayList<Mahasiswa> LMhs = new ArrayList<>();
    int currpos;

    private RequestQueue mRequestQueue;
    private RequestQueue getmRequestQueueImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);

        imv = findViewById(R.id.imageViewGambar1);
        tvNim = findViewById(R.id.textViewNim);
        tvNama = findViewById(R.id.textViewNama);
        tvUmur = findViewById(R.id.textViewUmur);
        btnext = findViewById(R.id.buttonNext);
        btprev = findViewById(R.id.buttonPrev);
        currpos = 0;
        inserDataJSONtoList();

        btprev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!LMhs.isEmpty()){
                    currpos--;
                    if (currpos > -1){
                        viewData();
                    }else {
                        currpos = 0;
                        Toast.makeText(ActivityViewAll.this, "First Data", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!LMhs.isEmpty()){
                    currpos++;
                    if (currpos < LMhs.size()){
                        viewData();
                    }else {
                        currpos = LMhs.size() - 1;
                        Toast.makeText(ActivityViewAll.this, "Last Data", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void viewData() {
        tvNim.setText("Nim\t: " + LMhs.get(currpos).getNim());
        tvNama.setText("Nama\t: " + LMhs.get(currpos).getNama());
        tvUmur.setText("Umur\t: " + LMhs.get(currpos).getUmur());

        ImageRequest requestImage = new ImageRequest(URLs.URL_LOADIMAGE + LMhs.get(currpos).getMyFoto(),
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        imv.setImageBitmap(response);
                    }
                }, 0, 0, ImageView.ScaleType.FIT_XY, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ActivityViewAll.this, "Error Load Image", Toast.LENGTH_SHORT).show();
            }
        });
        getmRequestQueueImage.add(requestImage);
    }

    private void inserDataJSONtoList() {
        mRequestQueue = Volley.newRequestQueue(this);
        getmRequestQueueImage = Volley.newRequestQueue(this);
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
                                    LMhs.add(new Mahasiswa(
                                            dataMhsObj.getString("nim"),
                                            dataMhsObj.getString("nama"),
                                            dataMhsObj.getInt("umur"),
                                            dataMhsObj.getString("myFoto")
                                    ));
                                }
                                viewData();
                            } else {
                                Toast.makeText(ActivityViewAll.this, obj.getString("message"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(ActivityViewAll.this, "JSON Exception: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ActivityViewAll.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("IDMhs", "Kosong");
                return params;
            }
        };
        mRequestQueue.add(stringRequest);
    }
}
