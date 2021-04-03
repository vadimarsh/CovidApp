package com.arsh.covidrf;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import com.google.gson.Gson;

//TODO 01 добавить gradle-зависимость :implementation 'com.android.volley:volley:1.1.1'
//TODO 02 создать объект класса RequestQueue ("очередь запросов")
//TODO 03 создать объект класса Request ("запрос")
//TODO 05 реализовать листенеры для обрабтки успешного ответа и ошибки
//TODO 06 вынести создание очереди запросов RequestQueue в синглетон (создать класс-наследник Application)
//TODO 07 добавить вью для выбора страны из доступных и реализовать отправку запросов

public class MainActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private TextView tvCured;
    private TextView tvDeath;
    private TextView tvTotal;
    private Loader<String> stringLoader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        stringLoader = LoaderManager.getInstance(this).initLoader(1, null, new MyLoaderCallBack());
        tvCured = findViewById(R.id.tvCured);
        tvDeath = findViewById(R.id.tvDeaths);
        tvTotal = findViewById(R.id.tvCases);
        progressBar = findViewById(R.id.progressBar);
        progressBar.animate();
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.VISIBLE);
        stringLoader.forceLoad();
    }

    public void fillTextView(int recovered, int deaths, int cases){
        tvCured.setText("" + recovered);
        tvDeath.setText("" + deaths);
        tvTotal.setText("" + cases);
    }

    class MyLoaderCallBack implements LoaderManager.LoaderCallbacks<String> {
        @NonNull
        @Override
        public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
            return new MyAsyncTaskLoader(MainActivity.this);
        }

        @Override
        public void onLoadFinished(@NonNull Loader<String> loader, String data) {
            Gson gson = new Gson();
            try {
                CountryStatus obj = gson.fromJson(data, CountryStatus.class);
                fillTextView(obj.getRecovered(),obj.getDeaths(),obj.getCases());
            } catch (RuntimeException ex) {
                Toast.makeText(MainActivity.this,data,Toast.LENGTH_LONG).show();
            }
            progressBar.setVisibility(View.INVISIBLE);
        }

        @Override
        public void onLoaderReset(@NonNull Loader<String> loader) {
            loader = null;
        }
    }
}