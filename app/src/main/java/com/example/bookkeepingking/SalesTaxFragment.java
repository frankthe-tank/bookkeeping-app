package com.example.bookkeepingking;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class SalesTaxFragment extends Fragment {

    String CountyChoice;
    String taxableRevenue;
    String total;

    public void calculate(){
        double taxRate=.095;
        if(CountyChoice.equals("Los Angeles"))
            taxRate=.0950;
        else if(CountyChoice.equals("San Francisco"))
            taxRate=.0850;
        else if(CountyChoice.equals("Riverside"))
            taxRate=.0875;
        else if(CountyChoice.equals("Fresno"))
            taxRate=.07975;
        else if(CountyChoice.equals("Alameda"))
            taxRate=.1025;
        else if(CountyChoice.equals("San Diego"))
            taxRate=.0775;
        else if(CountyChoice.equals("Nevada"))
            taxRate=.075;
        else if(CountyChoice.equals("Santa Barbara"))
            taxRate=.0775;
        else if(CountyChoice.equals("Orange"))
            taxRate=.0775;
        else if(CountyChoice.equals("Ventura"))
            taxRate=.0725;
        else if(CountyChoice.equals("Santa Clara"))
            taxRate=.09125;
        else if(CountyChoice.equals("San Luis Obispo"))
            taxRate=.0725;
        else if(CountyChoice.equals("San Bernardino"))
            taxRate=.0775;
        else
            return;
        total =String.format("%.2f",(Double.parseDouble(taxableRevenue)*taxRate));

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_sales_tax, container, false);;

        //choice set
        String [] values =
                {"Select one","Alameda","Fresno","Los Angeles","Nevada","Orange","Riverside","San Diego","San Francisco",
                        "San Luis Obispo","Santa Barbara","Santa Clara","Ventura","San Bernardino"};
        Spinner spinner = (Spinner) v.findViewById(R.id.spinner5);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, values);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);

        TextView totalTax=(TextView) v.findViewById(R.id.totalSalexTaxNumber);

        //taxable
        EditText taxable=(EditText) v.findViewById(R.id.editTexttotalMonthlyTaxableRevenue);

        //calculate
        Button calculate=(Button)v.findViewById(R.id.button);

        calculate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                taxableRevenue=taxable.getText().toString();
                CountyChoice = spinner.getSelectedItem().toString();
                calculate();
                totalTax.setText(total);
            }
        });
        // Inflate the layout for this fragment
        return v;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}