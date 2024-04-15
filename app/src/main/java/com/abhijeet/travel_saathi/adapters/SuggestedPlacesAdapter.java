package com.abhijeet.travel_saathi.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

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
        holder.setData(image);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.sceneImage);
        }

        public void setData(int d){
            image.setImageResource(d);
        }
    }
}
