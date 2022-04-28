package com.example.bookkeepingking;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class StartUpFragment extends Fragment {

    public StartUpFragment() {
        super(R.layout.fragment_start_up);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentStartUp  = inflater.inflate(R.layout.fragment_start_up, container, false);
        return fragmentStartUp;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.button_sign_up_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //System.out.print("Hello");
                NavHostFragment.findNavController(StartUpFragment.this).
                        navigate(R.id.action_startUpFragment_to_signUpFragment);
            }
                });
        view.findViewById(R.id.button_log_in_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(StartUpFragment.this).
                        navigate(R.id.action_startUpFragment_to_logInFragment);
            }
        });

    }
}