package com.example.bookkeepingking;

import android.content.Intent;
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
import androidx.navigation.fragment.NavHostFragment;

import LocalDatabase.DataBaseHelper;
import LocalDatabase.Invoice;

public class AddInvoiceFragment extends Fragment {
    byte invoiceIsNew;
    byte invoiceIsTaxDeductible;

    View fragmentFirstLayout;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        fragmentFirstLayout = inflater.inflate(R.layout.fragment_add_invoice, container, false);

        // Inflate the layout for this fragment
        return fragmentFirstLayout;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String[] vendorId = { "Frito-Lay", "Coca Cola", "Donations", "Repairs", "Costco", "Edison", "ADT Security", "Sam's Club", "Anheuser-Bush INC",
                                "Southern Wine & Spirits", "Stone", "LADWP", "Other Expenses" };
        String[] item = {"Chips","Beer","Milk", "Expense", "Soda", "Water", "Juice", "Wine", "Liquor", "Cigarettes", "Cigars", "Vapes", "Groceries"};
        String[] gl = {"Cost of Goods Sold","Donation Expense","Utilities Expense", "Bank Charges", "Credit Card Fees", "Legal Fees", "Vehicle Expenses", "Alarm Expense",
                            "Accounting Expense", "Office Supplies", "Advertisement Expense", "License Renewals", "Insurance Expense", "Legal Expense"};
        String[] paymentMethod = {"Cash","Check","Credit"};
        Spinner spinner = (Spinner) view.findViewById(R.id.spinnerVendorIdEdit);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, vendorId);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);
        spinner = (Spinner) view.findViewById(R.id.spinnerItemEdit);
        adapter = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_spinner_item, item);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);
        spinner = (Spinner) view.findViewById(R.id.spinnerGlEdit);
        adapter = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_spinner_item, gl);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);
        spinner = (Spinner) view.findViewById(R.id.spinnerPaymentMethodEdit);
        adapter = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_spinner_item, paymentMethod);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);

        view.findViewById(R.id.buttonSaveInvoiceAdd).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (((Switch) view.findViewById(R.id.switchTaxDeductibleAdd)).isChecked()) {
                    invoiceIsTaxDeductible = 0;
                } else {
                    invoiceIsTaxDeductible = 1;
                }
                Invoice invoice;
                try{
                    invoice = new Invoice(-1,1,
                            ((Spinner) view.findViewById(R.id.spinnerGlEdit)).getSelectedItem().toString(),
                            ((Spinner) view.findViewById(R.id.spinnerVendorIdEdit)).getSelectedItem().toString(),
                            invoiceIsTaxDeductible,
                            getString(getView().findViewById(R.id.editTextInvoiceNum)),
                            ((Spinner) view.findViewById(R.id.spinnerItemEdit)).getSelectedItem().toString(),
                            getString(getView().findViewById(R.id.editTextInvoiceTotal)),
                            getString(getView().findViewById(R.id.editTextInvoiceDate)),
                            ((Spinner) view.findViewById(R.id.spinnerPaymentMethodEdit)).getSelectedItem().toString()
                    );
                }catch(Exception e){
                    invoice = new Invoice(-1,1,"1", "1", (byte) 1,
                            "error","error","error","error", "error");
                }
                DataBaseHelper dataBaseHelper = new DataBaseHelper(getContext());
                //This will send it back to the main menu and add to database
                boolean success = dataBaseHelper.addInvoice(invoice);
                Toast toast;
                if(success){
                    toast = Toast.makeText(getContext(), "Invoice Added", Toast.LENGTH_LONG);
                }else{
                    toast = Toast.makeText(getContext(), "Error", Toast.LENGTH_LONG);
                }
                toast.show();
                NavHostFragment.findNavController(AddInvoiceFragment.this).
                        navigate(R.id.action_addInvoiceFragment_to_HomeFragment);
            }
        });



        view.findViewById(R.id.buttonCancelInvoiceAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(AddInvoiceFragment.this).
                        navigate(R.id.action_addInvoiceFragment_to_HomeFragment);
            }
        });
        view.findViewById(R.id.buttonUploadInvoice).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent in= new Intent(getActivity(), AddInvoice.class);
                startActivity(in);
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