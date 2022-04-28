package com.example.bookkeepingking;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import LocalDatabase.DataBaseHelper;
import LocalDatabase.Login;

public class SignUpFragment extends Fragment {
    private RadioGroup radioGroup;
    private RadioButton radioButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v  = inflater.inflate(R.layout.fragment_sign_up, container, false);
        return v;
    }
    @Override
    public void onViewCreated(@NonNull View view, Bundle  savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //RadioGroup radioGroup = (RadioGroup) findViewById(R.id.signUpRadio);

        view.findViewById(R.id.signUpButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editTT = (EditText) view.findViewById(R.id.firstNameInput);
                String strFirstName = editTT.getText().toString();
                editTT = (EditText) view.findViewById(R.id.lastNameInput);
                String strLastName = editTT.getText().toString();
                editTT = (EditText) view.findViewById(R.id.emailInput);
                String strEmail = editTT.getText().toString();
                editTT = (EditText) view.findViewById(R.id.passwordInput);
                String strPassword = editTT.getText().toString();
                editTT = (EditText) view.findViewById(R.id.editTextTextPassword);
                String strRePassword = editTT.getText().toString();
                editTT = (EditText) view.findViewById(R.id.editTextPhone);
                String strPhone = editTT.getText().toString();
                Byte isAcc;
                radioGroup = (RadioGroup) view.findViewById(R.id.signUpRadio);
                int selectedID = radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton = view.findViewById(selectedID);
                Login login;
                if(TextUtils.isEmpty(strFirstName)||TextUtils.isEmpty(strLastName)||
                    TextUtils.isEmpty(strEmail)||TextUtils.isEmpty(strPassword)||
                    TextUtils.isEmpty(strRePassword)||TextUtils.isEmpty(strPhone)) {
                    Toast toast;
                    toast = Toast.makeText(getContext(),"Completely fill out form", Toast.LENGTH_LONG);
                    toast.show();
                }else{
                    if(strPassword.equals(strRePassword)){
                        try {
                            if (radioButton.getText() == getString(R.string.accountant)) {
                                isAcc = 1;
                            } else {
                                isAcc = 0;
                            }
                            login = new Login(
                                    getString(getView().findViewById(R.id.emailInput)),
                                    getString(getView().findViewById(R.id.passwordInput)),
                                    (byte) isAcc
                            );
                        } catch (Exception e) {
                            login = new Login("Admin", "Password", (byte) 0);
                        }
                        DataBaseHelper dataBaseHelper = new DataBaseHelper(getContext());
                        Boolean isAvailable = dataBaseHelper.isAvailable(strEmail);
                        boolean success = false;
                        Toast toast;
                        if (isAvailable) {
                            success = dataBaseHelper.addLogin(login);
                            if (success) {
                                toast = Toast.makeText(getContext(), "User Added", Toast.LENGTH_LONG);
                            } else {
                                toast = Toast.makeText(getContext(), "Error", Toast.LENGTH_LONG);
                            }
                            toast.show();
                        } else {
                            success = false;
                            toast = Toast.makeText(getContext(), "Username Taken", Toast.LENGTH_LONG);
                            toast.show();
                        }
                        if (success) {
                            NavHostFragment.findNavController(SignUpFragment.this).
                                    navigate(R.id.action_signUpFragment_to_logInFragment);
                        }
                    }else {
                        Toast toast;
                        toast = Toast.makeText(getContext(),"Passwords must match", Toast.LENGTH_LONG);
                        toast.show();
                    }
                }
            }
        });

        view.findViewById(R.id.cancelSUButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SignUpFragment.this).
                        navigate(R.id.action_signUpFragment_to_startUpFragment);
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