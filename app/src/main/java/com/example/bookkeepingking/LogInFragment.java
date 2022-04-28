package com.example.bookkeepingking;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import LocalDatabase.DataBaseHelper;
import LocalDatabase.Login;
import LocalDatabase.TempVal;

public class LogInFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_log_in, container, false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.logInButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast;
                DataBaseHelper dataBaseHelper = new DataBaseHelper(getContext());
                if(dataBaseHelper.isAvailable(getString(getView().findViewById(R.id.editTextTextPersonName3)))){
                    toast = Toast.makeText(getContext(),"Username incorrect", Toast.LENGTH_LONG);
                    toast.show();
                }else{
                    Login login;
                    login = dataBaseHelper.getLogin(getString(getView().findViewById(R.id.editTextTextPersonName3)));
                    if((login.getPassword()).equals(getString(getView().findViewById(R.id.editTextTextPassword2)))){
                        if(dataBaseHelper.isTempVal()){
                            dataBaseHelper.editTempValS(login.getUsername());
                        }else{
                            TempVal tempVal = new TempVal(login.getUsername(),login.getIs_acc(),0,0,0,0);
                            dataBaseHelper.addTempVal(tempVal);
                        }
                        NavHostFragment.findNavController(LogInFragment.this).
                                navigate(R.id.action_logInFragment_to_HomeFragment);
                    }else{
                        toast = Toast.makeText(getContext(), "Password incorrect", Toast.LENGTH_LONG);
                        toast.show();
                    }
                }
            }
        });
        view.findViewById(R.id.cancelLIButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(LogInFragment.this).
                        navigate(R.id.action_logInFragment_to_startUpFragment);
            }
        });
    }
    public String getString() {
        return getString();
    }

    public String getString(EditText e){
        String k = e.getText().toString();
        return k;
    }
}