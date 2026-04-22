package com.example.lab3_android;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class StorageActivity extends AppCompatActivity {

    private TextView tvStoredData;
    private static final String FILE_NAME = "saved_data.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);

        tvStoredData = findViewById(R.id.tv_stored_data);
        Button btnClear = findViewById(R.id.btn_clear);
        Button btnBack = findViewById(R.id.btn_back);

        loadData();

        btnClear.setOnClickListener(v -> {
            clearData();
        });

        btnBack.setOnClickListener(v -> {
            finish();
        });
    }

    private void loadData() {
        try (FileInputStream fis = openFileInput(FILE_NAME);
             InputStreamReader isr = new InputStreamReader(fis);
             BufferedReader br = new BufferedReader(isr)) {

            StringBuilder sb = new StringBuilder();
            String text;

            while ((text = br.readLine()) != null) {
                sb.append(text).append("\n");
            }

            if (sb.toString().trim().isEmpty()) {
                tvStoredData.setText("Сховище пусте");
            } else {
                tvStoredData.setText(sb.toString());
            }

        } catch (IOException e) {
            e.printStackTrace();
            tvStoredData.setText("Сховище пусте");
        }
    }

    private void clearData() {
        try (FileOutputStream fos = openFileOutput(FILE_NAME, Context.MODE_PRIVATE)) {
            fos.write("".getBytes());
            tvStoredData.setText("Сховище пусте");
            Toast.makeText(this, "Дані видалено зі сховища", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}