package com.abhijeet.travel_saathi.adapters;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.abhijeet.travel_saathi.R;
import com.abhijeet.travel_saathi.fragments.ChatFragment;
import com.abhijeet.travel_saathi.models.FromYourLocationModelClass;
import com.abhijeet.travel_saathi.models.MessageModelClass;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {


    List<MessageModelClass> userList;
    Context context;

    public MessageAdapter(List<MessageModelClass> userList, Context context) {
        this.userList = userList;
        this.context = context;
    }

    @NonNull
    @Override
    public MessageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_item_design,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageAdapter.ViewHolder holder, int position) {
        int image = userList.get(position).getImage();
        String name = userList.get(position).getName();

        holder.setData(image,name);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChatFragment chatFragment = new ChatFragment();
                FragmentActivity fragmentActivity = (FragmentActivity) context;
                chatFragment.show(fragmentActivity.getSupportFragmentManager(), "ChatFragmentTag");
            }
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.profileImage);
            textView = itemView.findViewById(R.id.profileName);
        }

        public void setData(int d, String name){
            imageView.setImageResource(d);
            textView.setText(name);
        }
    }
}
