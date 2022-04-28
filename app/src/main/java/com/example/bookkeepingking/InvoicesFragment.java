package com.example.bookkeepingking;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import LocalDatabase.DataBaseHelper;
import LocalDatabase.Invoice;

public class InvoicesFragment extends Fragment {
    public List<Invoice> list = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_invoices, container, false);
        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DataBaseHelper db = new DataBaseHelper(getContext());
        list = db.getAllInvoice();
        List<String> invoiceNum = new ArrayList<>();
        for(int i = 0; i < list.size() ; i++){
            invoiceNum.add(list.get(i).getInvoice_num());
        }
        Spinner spinner = (Spinner) view.findViewById(R.id.spinnerInvoiceList);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, invoiceNum);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);

        view.findViewById(R.id.buttonEditInvoice).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                int invoiceId = 10;
                for(int i = 0; i < list.size() ; i++){
                    //invoiceNum.add(list.get(i).getInvoice_num());
                    if(list.get(i).getInvoice_num() == spinner.getSelectedItem().toString()){
                        invoiceId = list.get(i).getInvoice_id();
                    }
                }
                //int selectInvoice =Integer.parseInt(spinner.getSelectedItem().toString());
                db.editTempValI("invoice_id", invoiceId);

                NavHostFragment.findNavController(InvoicesFragment.this).
                        navigate(R.id.action_invoicesFragment_to_fragmentInvoiceEditor);
            }
        });
        view.findViewById(R.id.buttonAddInvoice).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                NavHostFragment.findNavController(InvoicesFragment.this).
                        navigate(R.id.action_invoicesFragment_to_addInvoiceFragment);
            }
        });
    }

}