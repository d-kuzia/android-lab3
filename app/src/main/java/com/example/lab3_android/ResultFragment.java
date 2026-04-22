package com.example.lab3_android;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ResultFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_result, container, false);

        TextView tvResult = view.findViewById(R.id.tv_result);
        Button btnCancel = view.findViewById(R.id.btn_cancel);

        if (getArguments() != null) {
            String result = getArguments().getString("selected_language");
            tvResult.setText(result);
        }

        btnCancel.setOnClickListener(v -> {
            if (getActivity() instanceof MainActivity) {
                ((MainActivity) getActivity()).clearAndShowInput();
            }
        });

        return view;
    }
}