package com.abhijeet.travel_saathi.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import androidx.recyclerview.widget.RecyclerView;

import com.abhijeet.travel_saathi.R;
import com.abhijeet.travel_saathi.models.QuestionsModelClass;

public class OptionAdapter extends RecyclerView.Adapter<OptionAdapter.OptionViewHolder> {
    private QuestionsModelClass question;

    public OptionAdapter(QuestionsModelClass question) {
        this.question = question;
    }

    @Override
    public OptionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.option_item, parent, false);
        return new OptionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OptionViewHolder holder, int position) {
        holder.optionText.setText(question.getOptions()[position]);
        holder.optionText.setChecked(question.getSelectedOptionIndex() == position);
        holder.optionText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                question.setSelectedOptionIndex(holder.getAdapterPosition());
                notifyDataSetChanged();
            }
        });
    }


    @Override
    public int getItemCount() {
        return question.getOptions().length;
    }

    static class OptionViewHolder extends RecyclerView.ViewHolder {
        RadioButton optionText;

        public OptionViewHolder(View itemView) {
            super(itemView);
            optionText = itemView.findViewById(R.id.option_text);
        }
    }
}




