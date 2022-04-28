package com.example.bookkeepingking;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import LocalDatabase.DataBaseHelper;
import LocalDatabase.Employee;

public class AddEmployeeFragment extends Fragment {

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.fragment_add_employee, container, false);
        Button button = (Button) view.findViewById(R.id.buttonSaveEmpAdd);
        Context thisContext = container.getContext();
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Employee employee;
                try{
                    employee = new Employee(-1,1, getString(getView().findViewById(R.id.editTextFirstName)),
                            getString(getView().findViewById(R.id.editTextMiddleName)),
                            getString(getView().findViewById(R.id.editTextLastName)),
                            getString(getView().findViewById(R.id.editTextAddress)),
                            getString(getView().findViewById(R.id.editTextState)),
                            getString(getView().findViewById(R.id.editTextCity)),
                            getString(getView().findViewById(R.id.editTextZip)),
                            getString(getView().findViewById(R.id.editTextPhnnumber)),
                            getString(getView().findViewById(R.id.editTextSsn)),
                            Integer.parseInt(getString(getView().findViewById(R.id.editTextAllow))),
                            ((Spinner) view.findViewById(R.id.spinnerFOPAdd)).getSelectedItem().toString(),
                            getIsMarried(),
                            true);
                }catch(Exception e){
                employee = new Employee(-1,1,"error", "error", "error",
                        "error","error","error","error", "error","error",
                        0,"error", true, true);
                }
                DataBaseHelper dataBaseHelper = new DataBaseHelper(thisContext);
                boolean success = dataBaseHelper.addEmployee(employee);
                Toast toast;
                if(success){
                    toast = Toast.makeText(getContext(), "Employee Added", Toast.LENGTH_LONG);
                }else{
                    toast = Toast.makeText(getContext(), "Error", Toast.LENGTH_LONG);
                }
                toast.show();
                NavHostFragment.findNavController(AddEmployeeFragment.this).
                        navigate(R.id.action_addEmployeeFragment_to_HomeFragment);
            }
        });
        // Inflate the layout for this fragment
        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String[] isMarried = {"Single","Married"};
        String[] allowances = {"weekly", "bi-monthly"};
        Spinner spinner = (Spinner) view.findViewById(R.id.spinnerIsMarriedAdd);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, isMarried);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);
        spinner = (Spinner) view.findViewById(R.id.spinnerFOPAdd);
        adapter = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_spinner_item, allowances);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);

        view.findViewById(R.id.buttonCancelEmpAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(AddEmployeeFragment.this).
                        navigate(R.id.action_addEmployeeFragment_to_HomeFragment);
            }
        });
    }

    /*public String getString() {
        return getString();
    }*/

    public String getString(EditText e){
        String k = e.getText().toString();
        return k;
    }
    public Boolean getIsMarried(){
        if((((Spinner) getView().findViewById(R.id.spinnerIsMarriedAdd)).getSelectedItem().toString()).equals("Married")){
            return true;
        }else{ return false;}
    }

}
