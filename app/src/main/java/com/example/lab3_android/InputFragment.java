package com.example.lab3_android;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.FileOutputStream;
import java.io.IOException;

public class InputFragment extends Fragment {

    private Spinner spinner;
    private static final String FILE_NAME = "saved_data.txt";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_input, container, false);

        spinner = view.findViewById(R.id.spinner_languages);
        Button btnOk = view.findViewById(R.id.btn_ok);
        Button btnOpen = view.findViewById(R.id.btn_open);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                requireContext(),
                R.array.programming_languages,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(0);

        btnOk.setOnClickListener(v -> {
            if (spinner.getSelectedItemPosition() == 0) {
                Toast.makeText(requireContext(), "Завершіть введення даних!", Toast.LENGTH_SHORT).show();
                return;
            }

            String selectedLanguage = spinner.getSelectedItem().toString();

            saveToFile(selectedLanguage);

            if (getActivity() instanceof MainActivity) {
                ((MainActivity) getActivity()).showResult(selectedLanguage);
            }
        });

        btnOpen.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), StorageActivity.class);
            startActivity(intent);
        });

        return view;
    }

    private void saveToFile(String data) {
        try (FileOutputStream fos = requireContext().openFileOutput(FILE_NAME, Context.MODE_APPEND)) {
            String textToSave = data + "\n";
            fos.write(textToSave.getBytes());
            Toast.makeText(requireContext(), "Дані успішно збережено у файл!", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(requireContext(), "Помилка збереження!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (spinner != null) {
            spinner.setSelection(0);
        }
    }
}