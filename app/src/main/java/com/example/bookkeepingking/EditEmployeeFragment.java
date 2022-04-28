package com.example.bookkeepingking;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.fragment.NavHostFragment;

import LocalDatabase.DataBaseHelper;
import LocalDatabase.Employee;
import LocalDatabase.Invoice;
import LocalDatabase.TempVal;

public class EditEmployeeFragment extends Fragment {
    public int id = 2;
    public String[] isMarried = {" ", " "};
    public String[] pRotation ={" ", " "};
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.fragment_edit_employee, container, false);

        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DataBaseHelper dataBaseHelper = new DataBaseHelper(getContext());
        TempVal tempVal = dataBaseHelper.getTempVal();
        id = tempVal.getEmployee_id();
        Employee employee = dataBaseHelper.getEmployee(id);

        EditText textBox = (EditText) getView().findViewById(R.id.editTextFirstName);
        textBox.setText(employee.getF_name());
        textBox = (EditText) getView().findViewById(R.id.editTextMiddleName);
        textBox.setText(employee.getM_name());
        textBox = (EditText) getView().findViewById(R.id.editTextLastName);
        textBox.setText(employee.getL_name());
        textBox = (EditText) getView().findViewById(R.id.editTextSsn);
        textBox.setText(employee.getSsn());
        textBox = (EditText) getView().findViewById(R.id.editTextPhnnumber);
        textBox.setText(employee.getPhone());
        textBox = (EditText) getView().findViewById(R.id.editTextAddress);
        textBox.setText(employee.getAddress());
        textBox = (EditText) getView().findViewById(R.id.editTextCity);
        textBox.setText(employee.getCity());
        textBox = (EditText) getView().findViewById(R.id.editTextState);
        textBox.setText(employee.getState());
        textBox = (EditText) getView().findViewById(R.id.editTextZip);
        textBox.setText(employee.getZip());
        textBox = (EditText) getView().findViewById(R.id.editTextAllow);
        textBox.setText(String.valueOf(employee.getAllowances()));
        if(employee.isIs_married() == true) {
            isMarried[0] = "Married";
            isMarried[1] = "Single";
        }else{
            isMarried[0] = "Single";
            isMarried[1] = "Married";
        }
        if(employee.getP_rotation().equals("weekly")){
            pRotation[0] = "weekly";
            pRotation[1] = "Bi-Monthly";
        }else{
            pRotation[0] = "Bi-Monthly";
            pRotation[1] = "weekly";
        }
        Spinner spinner = (Spinner) view.findViewById(R.id.spinnerIsMarriedEdit);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, isMarried);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);
        spinner = (Spinner) view.findViewById(R.id.spinnerFOPEdit);
        adapter = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_spinner_item, pRotation);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);

        view.findViewById(R.id.buttonSavePayroll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dataBaseHelper.editEmployee(id,
                        getString(getView().findViewById(R.id.editTextFirstName)),
                        getString(getView().findViewById(R.id.editTextMiddleName)),
                        getString(getView().findViewById(R.id.editTextLastName)),
                        getString(getView().findViewById(R.id.editTextAddress)),
                        getString(getView().findViewById(R.id.editTextState)),
                        getString(getView().findViewById(R.id.editTextCity)),
                        getString(getView().findViewById(R.id.editTextZip)),
                        getString(getView().findViewById(R.id.editTextPhnnumber)),
                        getString(getView().findViewById(R.id.editTextSsn)),
                        Integer.parseInt(getString(getView().findViewById(R.id.editTextAllow))),
                        ((Spinner) view.findViewById(R.id.spinnerFOPEdit)).getSelectedItem().toString(),
                        getIsMarried());
                Toast toast = Toast.makeText(getContext(), "Employee Edited", Toast.LENGTH_LONG);
                toast.show();
                NavHostFragment.findNavController(EditEmployeeFragment.this).
                        navigate(R.id.action_editEmployeeFragment_to_HomeFragment);
            }
        });
        view.findViewById(R.id.buttonCancelEmpEdit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(EditEmployeeFragment.this).
                        navigate(R.id.action_editEmployeeFragment_to_HomeFragment);
            }
        });
    }
    public String getString(EditText e){
        String k = e.getText().toString();
        return k;
    }
    public Boolean getIsMarried(){
        if((((Spinner) getView().findViewById(R.id.spinnerIsMarriedEdit)).getSelectedItem().toString()).equals("Married")){
            return true;
        }else{ return false;}
    }
}
