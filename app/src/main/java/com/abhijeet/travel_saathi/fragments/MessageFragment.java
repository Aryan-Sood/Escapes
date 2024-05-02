package com.abhijeet.travel_saathi.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.abhijeet.travel_saathi.R;
import com.abhijeet.travel_saathi.adapters.MessageAdapter;
import com.abhijeet.travel_saathi.models.MessageModelClass;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.List;

public class MessageFragment extends BottomSheetDialogFragment {

    List<MessageModelClass> messageList;
    MessageAdapter messageAdapter;
    LinearLayoutManager messageLayout;
    RecyclerView chatsRecyclerView;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        Dialog dialog = super.onCreateDialog(savedInstanceState);
        Window window = dialog.getWindow();
        if (window!=null){
            WindowManager.LayoutParams params = window.getAttributes();
            params.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
            params.y = getResources().getDimensionPixelSize(R.dimen.bottom_sheet_margin);
            window.setAttributes(params);
        }
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.message_fragment_layout, container, false);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

         chatsRecyclerView = view.findViewById(R.id.chatsRecyclerView);



        initChatsRecyclerViewData();
        initChatsRecyclerView();
    }



    public void initChatsRecyclerViewData(){
        messageList = new ArrayList<>();
        messageList.add(new MessageModelClass(R.drawable.avatar_lady2, "Rishiraj Jain"));
        messageList.add(new MessageModelClass(R.drawable.avatar_uncle1, "Siddharth Kumar"));
        messageList.add(new MessageModelClass(R.drawable.avatar_lady2, "Abhijeet Kumar"));
        messageList.add(new MessageModelClass(R.drawable.avatar_uncle1, "Vibhor Mathur"));
    }
    public void initChatsRecyclerView(){
        messageLayout = new LinearLayoutManager(getContext());
        messageLayout.setOrientation(RecyclerView.VERTICAL);
        chatsRecyclerView.setLayoutManager(messageLayout);
        messageAdapter = new MessageAdapter(messageList,getContext());
        chatsRecyclerView.setAdapter(messageAdapter);
        messageAdapter.notifyDataSetChanged();
    }
}
