package com.abhijeet.travel_saathi.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.abhijeet.travel_saathi.R;
import com.abhijeet.travel_saathi.activities.Home_page;
import com.abhijeet.travel_saathi.activities.Select_Interest;
import com.abhijeet.travel_saathi.activities.SomeoneProfile;
import com.abhijeet.travel_saathi.fragments.ChatFragment;
import com.abhijeet.travel_saathi.models.UserModel;
import com.abhijeet.travel_saathi.utils.AndroidUtil;

import java.util.List;

public class FromYourLocationAdapter extends RecyclerView.Adapter<FromYourLocationAdapter.ViewHolder> {

    List<UserModel> userList;

    UserModel otherUserModel;
    Context context;

    public FromYourLocationAdapter(List<UserModel> userList, Context context) {
        this.userList = userList;
        this.context = context;
    }


    @NonNull
    @Override
    public FromYourLocationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.from_your_location_item_design,parent,false);
        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull FromYourLocationAdapter.ViewHolder holder, int position) {
//        int image = userList.get(position).get();
        String name = userList.get(position).getUsername();

        holder.setData(R.drawable.avatar_uncle1,name);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SomeoneProfile.class);
                UserModel a = userList.get(position);
                AndroidUtil.passUserModelAsIntent(intent, a);

                context.startActivity(intent);
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
            imageView = itemView.findViewById(R.id.personImage);
            textView = itemView.findViewById(R.id.personName);
        }

        public void setData(int d, String name){
            imageView.setImageResource(d);
            textView.setText(name);
        }
    }
}
