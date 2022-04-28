package com.example.bookkeepingking;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import LocalDatabase.Chat;
import io.kommunicate.Kommunicate;


public class ChatFragment extends Fragment {

    EditText mGetMessage;
    ImageButton msendMessageButton;

    CardView mSendMessageCardView;
    ImageView mImageViewOfUser;
    TextView mNameOfUser;

    private String enteredMessage;
    Intent intent;
    String mRecieverName, senderName, mRecieverUserId, mSenderUserId;
    String senderRoom, recieverRoom;

    RecyclerView mMessagerecyclerView;

    String currentTime;
    Calendar calendar;
    SimpleDateFormat simpleDateFormat;
    RecyclerView recyclerView;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ){
        //Kommunicate.init(getActivity(), "21f0b692e6c2f4e5f204b8378c9079f62");
        View fragmentLayout = inflater.inflate(R.layout.fragment_chat, container, false);
        return fragmentLayout;

    }
    public void onViewCreated (@NonNull View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        // Populate dummy messages in List, you can implement your code here
        ArrayList<Chat> messagesList = new ArrayList<>();
        for (int i=0;i<60;i++) {
            messagesList.add(new Chat(i,1,true,"hi","12:00" ));
        }
        messagesList.add(new Chat(61,1,false,"hi","12:00" ));

        CustomAdapter adapter = new CustomAdapter(getActivity(), messagesList);

        recyclerView = view.findViewById(R.id.recyclerViewOfChat);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        mGetMessage = view.findViewById(R.id.getMessage);
        mSendMessageCardView = view.findViewById(R.id.cardviewOfSendMessage);
        msendMessageButton = view.findViewById(R.id.imageViewSendMessage);
        //intent = getIntent();

        calendar = Calendar.getInstance();
        simpleDateFormat = new SimpleDateFormat("hh:mm a");
    }
}