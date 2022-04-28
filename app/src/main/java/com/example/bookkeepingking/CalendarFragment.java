package com.example.bookkeepingking;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import LocalDatabase.Calender;
import LocalDatabase.DataBaseHelper;


public class CalendarFragment extends Fragment {
    private Button button3;
    private PopupWindow popupWindow;




        @Override
        public View onCreateView (
                LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState
    ){
            View fragmentFirstLayout = inflater.inflate(R.layout.fragment_calendar, container, false);


            // Inflate the layout for this fragment
            return fragmentFirstLayout;
        }
        public void onViewCreated (@NonNull View view, Bundle savedInstanceState){
            super.onViewCreated(view, savedInstanceState);



            view.findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calender calender;


                    try {
                        calender = new Calender(

                                getString(getView().findViewById(R.id.editTextTextPersonName3)),
                                getString(getView().findViewById(R.id.editTextTextPersonName4))
                                );


                    }catch(Exception e){
                        calender = new Calender("error","error");
                    }

                    DataBaseHelper dataBaseHelper = new DataBaseHelper(getContext());
                    boolean success = dataBaseHelper.addCalender(calender);
                    Toast toast;
                    if (success) {
                        toast=Toast.makeText(getContext(), "Event Added", Toast.LENGTH_LONG);
                    } else {
                        toast=Toast.makeText(getContext(), "Your Event has been saved! Thank you :)", Toast.LENGTH_LONG);
                    }

                    toast.show();
                    NavHostFragment.findNavController(CalendarFragment.this).
                            navigate(R.id.action_calendarFragment_to_FirstFragment);


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




