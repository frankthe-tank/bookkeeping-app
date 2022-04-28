package com.example.bookkeepingking;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class HomeFragment extends Fragment {
    FloatingActionButton mAccountant, mCustomersupp;

    TextView addAccountantText, customerSupp;
    // to check whether sub FAB buttons are visible or not.
    Boolean isAllFabsVisible;



    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        View fragmentFirstLayout = inflater.inflate(R.layout.fragment_home, container, false);



        // Inflate the layout for this fragment
        return fragmentFirstLayout;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // FAB button
        mAccountant=view.findViewById(R.id.accountantButton);
        mCustomersupp=view.findViewById(R.id.customerSupport);
        // Also register the action name text, of all the FABs.
        addAccountantText = view.findViewById(R.id.accountText);
        customerSupp=view.findViewById(R.id.customerSupportText);

        mAccountant.setVisibility(View.GONE);
        addAccountantText.setVisibility(View.GONE);
        mCustomersupp.setVisibility(View.GONE);
        customerSupp.setVisibility(View.GONE);
        isAllFabsVisible = false;

        view.findViewById(R.id.buttonHomeCalender).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(HomeFragment.this).
                        navigate(R.id.action_HomeFragment_to_calendarFragment);
            }
        });
        view.findViewById(R.id.buttonHomeEmployees).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(HomeFragment.this).
                        navigate(R.id.action_HomeFragment_to_employeesFragment);
            }
        });
        view.findViewById(R.id.buttonHomeInvoices).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(HomeFragment.this).
                        navigate(R.id.action_HomeFragment_to_invoicesFragment);
            }
        });
        view.findViewById(R.id.buttonHomePayroll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(HomeFragment.this).
                        navigate(R.id.action_HomeFragment_to_payrollFragment);
            }
        });
        view.findViewById(R.id.buttonHomeSalestax).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(HomeFragment.this).
                        navigate(R.id.action_HomeFragment_to_salesTaxFragment);
            }
        });

        view.findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isAllFabsVisible) {

                    // when isAllFabsVisible becomes
                    // true make all the action name
                    // texts and FABs VISIBLE.

                    mAccountant.show();
                    addAccountantText.setVisibility(View.VISIBLE);
                    mCustomersupp.show();
                    customerSupp.setVisibility(View.VISIBLE);
                    // make the boolean variable true as
                    // we have set the sub FABs
                    // visibility to GONE
                    isAllFabsVisible = true;
                } else {

                    // when isAllFabsVisible becomes
                    // true make all the action name
                    // texts and FABs GONE.
                    mAccountant.hide();
                    addAccountantText.setVisibility(View.GONE);
                    mCustomersupp.hide();
                    customerSupp.setVisibility(View.GONE);

                    // make the boolean variable false
                    // as we have set the sub FABs
                    // visibility to GONE
                    isAllFabsVisible = false;
                }
            }
        });
        mAccountant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                NavHostFragment.findNavController(HomeFragment.this).
                        navigate(R.id.action_HomeFragment_to_chatFragment);
                //Toast.makeText(getActivity(), "getting support "+ ("\ud83d\ude14"),Toast.LENGTH_LONG).show();
            }
        });

        mCustomersupp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Toast.makeText(getActivity(), "Customer won't support "+ ("\ud83d\ude14"),Toast.LENGTH_LONG).show();
            }
        });
    }
}