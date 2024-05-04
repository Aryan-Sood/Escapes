package com.abhijeet.travel_saathi.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.abhijeet.travel_saathi.R;
import com.abhijeet.travel_saathi.models.SimilarInterestModelClass;
import com.google.android.material.divider.MaterialDivider;

import java.util.List;

public class SimilarInterestAdapter extends RecyclerView.Adapter<SimilarInterestAdapter.ViewHolder> {

    List<SimilarInterestModelClass> userList;

    public SimilarInterestAdapter(List<SimilarInterestModelClass> userList) {
        this.userList = userList;
    }

    @NonNull
    @Override
    public SimilarInterestAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.similar_interest_item_design,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SimilarInterestAdapter.ViewHolder holder, int position) {
        String name = userList.get(position).getName();
        String location = userList.get(position).getLocation();

        holder.setData(name,location);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView nameView;
        private TextView locationView;
        private MaterialDivider divider;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nameView = itemView.findViewById(R.id.userName);
            locationView = itemView.findViewById(R.id.userLocation);
        }

        public void setData(String name, String location){
            nameView.setText(name);
            locationView.setText(location);
        }
    }
}
