package com.abhijeet.travel_saathi.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.abhijeet.travel_saathi.R;
import com.abhijeet.travel_saathi.models.SuggestedPlacesModelClass;

import java.util.List;

public class SuggestedPlacesAdapter extends RecyclerView.Adapter<SuggestedPlacesAdapter.ViewHolder>{

    List<SuggestedPlacesModelClass> userList;

    public SuggestedPlacesAdapter(List<SuggestedPlacesModelClass> userList) {
        this.userList = userList;
    }

    @NonNull
    @Override
    public SuggestedPlacesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.suggested_places_item_design,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SuggestedPlacesAdapter.ViewHolder holder, int position) {
        int image = userList.get(position).getSceneImage();
        String nam = userList.get(position).getSceneName();
        holder.setData(image, nam);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView image;
        private TextView nameView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.sceneImage);
            nameView = itemView.findViewById(R.id.loction_name);
        }

        public void setData(int d, String name){
            image.setImageResource(d);
            nameView.setText(name);
        }
    }
}
