package com.abhijeet.travel_saathi.adapters;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.abhijeet.travel_saathi.R;
import com.abhijeet.travel_saathi.models.FromYourLocationModelClass;

import org.w3c.dom.Text;

import java.util.List;

public class FromYourLocationAdapter extends RecyclerView.Adapter<FromYourLocationAdapter.ViewHolder> {

    List<FromYourLocationModelClass> userList;

    public FromYourLocationAdapter(List<FromYourLocationModelClass> userList) {
        this.userList = userList;
    }


    @NonNull
    @Override
    public FromYourLocationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.from_your_location_item_design,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FromYourLocationAdapter.ViewHolder holder, int position) {
        int image = userList.get(position).getImage();
        String name = userList.get(position).getName();

        holder.setData(image,name);

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
