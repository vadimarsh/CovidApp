package com.arsh.covidrf;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import org.json.JSONObject;

import static com.arsh.covidrf.Consts.URL_GET_STATUS_RF;

//TODO 01 добавить на активити AutoCompleteTextView
//TODO 02 добавить выполнение запроса к апи "https://covid19-api.org/api/countries" для получения массива объектов с информацией о странах
//TODO 03 создать ArrayAdapter для отображаения выпадающего списка AutoCompleteTextView
//TODO 04 реализовать "сборку" и отправку запроса для получения ковид-ситуации по выбранной стране
//TODO 05 Profit!!!

public class MainActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private TextView tvCured;
    private TextView tvDeath;
    private TextView tvTotal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvCured = findViewById(R.id.tvCured);
        tvDeath = findViewById(R.id.tvDeaths);
        tvTotal = findViewById(R.id.tvCases);
        progressBar = findViewById(R.id.progressBar);
        progressBar.animate();
        doRequestToApi("RU");
    }

    private void doRequestToApi(String countryCode) {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL_GET_STATUS_RF+countryCode, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Gson gson = new Gson();
                CountryStatus obj = gson.fromJson(String.valueOf(response), CountryStatus.class);
                fillTextView(obj.getRecovered(),obj.getDeaths(),obj.getCases());
                progressBar.setVisibility(View.INVISIBLE);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "No internet", Toast.LENGTH_LONG).show();
            }
        });
        App.getApp().addToRequestQueue(request);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void fillTextView(int recovered, int deaths, int cases){
        tvCured.setText("" + recovered);
        tvDeath.setText("" + deaths);
        tvTotal.setText("" + cases);

    }

}