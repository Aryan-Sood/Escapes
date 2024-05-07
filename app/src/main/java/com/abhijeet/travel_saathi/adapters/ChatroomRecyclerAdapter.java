package com.abhijeet.travel_saathi.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.abhijeet.travel_saathi.R;
import com.abhijeet.travel_saathi.models.ChatMessageModel;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class ChatroomRecyclerAdapter extends FirestoreRecyclerAdapter<ChatMessageModel, ChatroomRecyclerAdapter.ChatModelViewHolder> {

    Context context;

    public ChatroomRecyclerAdapter(@NonNull FirestoreRecyclerOptions<ChatMessageModel> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull ChatModelViewHolder holder, int position, @NonNull ChatMessageModel model) {
        Log.i("haushd","asjd");
       if(model.getSenderId().equals(FirebaseUtil.currentUserId())){
          holder.leftChatLayout.setVisibility(View.GONE);
          holder.rightChatLayout.setVisibility(View.VISIBLE);
          holder.rightChatTextview.setText(model.getMessage());
       }else{
           holder.rightChatLayout.setVisibility(View.GONE);
           holder.leftChatLayout.setVisibility(View.VISIBLE);
           holder.leftChatTextview.setText(model.getMessage());
       }
    }

    @NonNull
    @Override
    public ChatModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.chat_message_recycler_row,parent,false);
        return new ChatModelViewHolder(view);
    }

    class ChatModelViewHolder extends RecyclerView.ViewHolder{

        LinearLayout leftChatLayout,rightChatLayout;
        TextView leftChatTextview,rightChatTextview;

        public ChatModelViewHolder(@NonNull View itemView) {
            super(itemView);

            leftChatLayout = itemView.findViewById(R.id.left_chat_layout);
            rightChatLayout = itemView.findViewById(R.id.right_chat_layout);
            leftChatTextview = itemView.findViewById(R.id.left_chat_textview);
            rightChatTextview = itemView.findViewById(R.id.right_chat_textview);
        }
    }
}
