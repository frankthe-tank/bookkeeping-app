package com.example.bookkeepingking;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import LocalDatabase.DataBaseHelper;
import LocalDatabase.Employee;
import LocalDatabase.Payroll;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PayrollFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_payroll, container, false);
        Button button = (Button) view.findViewById(R.id.buttonSavePayroll);
        Context thisContext = container.getContext();
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Payroll payroll;
                try{
                    payroll = new Payroll(-1,1, getString(getView().findViewById(R.id.checkNumInput)),
                            getString(getView().findViewById(R.id.checkDateInput)),
                            getString(getView().findViewById(R.id.endingPeriodDateInput)),
                            getString(getView().findViewById(R.id.numberOfHoursWorkedInput)),
                            getString(getView().findViewById(R.id.grossAmtInput)),
                            getString(getView().findViewById(R.id.netPayInput))
                            );
                }catch(Exception e){
                    payroll = new Payroll(-1,1,"error", "error",
                            "error", "0","0", "0");
                }
                DataBaseHelper dataBaseHelper = new DataBaseHelper(thisContext);
                boolean success = dataBaseHelper.addPayroll(payroll);
            }
        });
        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //calculate button actions
        view.findViewById(R.id.buttonPayrollCalc).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText textBox = (EditText) getView().findViewById(R.id.grossAmtInput);
                textBox.setText(calcGross());
                textBox=(EditText) getView().findViewById(R.id.federalIncomeTaxInput);
                textBox.setText(calcFederalIncomeTax());
                textBox=(EditText) getView().findViewById(R.id.socialSecurityInput);
                textBox.setText(calcSocialSecurity());
                textBox=(EditText) getView().findViewById(R.id.medicareInput);
                textBox.setText(calcMedicare());
                textBox=(EditText) getView().findViewById(R.id.stateIncomeTaxInput);
                //getIncomeTax();
                textBox.setText(calcStateIncomeTax());
                textBox=(EditText) getView().findViewById(R.id.stateDisabilityInsuranceInput);
                textBox.setText(calcStateDisabilityInsurance());
                textBox=(EditText) getView().findViewById(R.id.netPayInput);
                textBox.setText(calcNetPay());
            }
        });
        view.findViewById(R.id.buttonCancelPayroll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                NavHostFragment.findNavController(PayrollFragment.this).
                        navigate(R.id.action_payrollFragment_to_HomeFragment);
            }
        });
        view.findViewById(R.id.buttonSavePayroll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                NavHostFragment.findNavController(PayrollFragment.this).
                        navigate(R.id.action_payrollFragment_to_HomeFragment);
            }
        });

        List<Employee> list = new ArrayList<>();
        DataBaseHelper db = new DataBaseHelper(getContext());
        list = db.getAllEmployee();
        List<String> names = new ArrayList<>();
        for(int i = 0; i < list.size() ; i++){
            names.add(list.get(i).getF_name() + " " + list.get(i).getL_name());
        }
        Spinner spinner = (Spinner) view.findViewById(R.id.employeeSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, names);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                EditText text = (EditText) getView().findViewById(R.id.empNameInput);
                text.setText(spinner.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                EditText text = (EditText) getView().findViewById(R.id.empNameInput);
                text.setText(" ");
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

    public String calcGross(){
        String hourlyPay = getString(getView().findViewById(R.id.hourlyPayInput));
        String hoursWorked = getString(getView().findViewById(R.id.numberOfHoursWorkedInput));
        return String.format("%.2f",Double.parseDouble(hoursWorked) * Double.parseDouble(hourlyPay));
    }
    public String calcFederalIncomeTax(){
        return String.format("%.2f", Double.parseDouble(calcGross())*.0887);
    }
    public String calcSocialSecurity(){
        return String.format("%.2f",Double.parseDouble(calcGross())*.062);
    }
    public String calcMedicare(){
        return String.format("%.2f",Double.parseDouble(calcGross())*.0145);
    }
    public String calcStateIncomeTax(){
        return String.format("%.2f",Double.parseDouble(calcGross())*.0061);
    }

    public String calcStateDisabilityInsurance(){
        return String.format("%.2f",Double.parseDouble(calcGross())*.01);
    }
    public String calcNetPay(){
        return String.format("%.2f",Double.parseDouble(calcGross())-Double.parseDouble(calcFederalIncomeTax())
                    -Double.parseDouble(calcSocialSecurity())-Double.parseDouble(calcMedicare())
                    -Double.parseDouble(calcStateIncomeTax())-Double.parseDouble(calcStateDisabilityInsurance()));
    }
}