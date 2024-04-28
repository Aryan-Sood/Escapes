package com.abhijeet.travel_saathi.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.abhijeet.travel_saathi.R;
import com.abhijeet.travel_saathi.models.QuestionsModelClass;

import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder> {
    private List<QuestionsModelClass> questions;

    public QuestionAdapter(List<QuestionsModelClass> questions) {
        this.questions = questions;
    }

    @Override
    public QuestionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_item, parent, false);
        return new QuestionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(QuestionViewHolder holder, int position) {
        QuestionsModelClass question = questions.get(position);
        holder.questionText.setText(question.getQuestionText());
        ArrayAdapter<String> adapter = new ArrayAdapter<>(holder.itemView.getContext(), android.R.layout.simple_list_item_multiple_choice, question.getOptions());
        holder.optionsList.setAdapter(adapter);
        holder.itemView.setOnClickListener(v -> {
            if (holder.optionsList.getVisibility() == View.GONE) {
                holder.optionsList.setVisibility(View.VISIBLE);
            } else {
                holder.optionsList.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    static class QuestionViewHolder extends RecyclerView.ViewHolder {
        TextView questionText;
        ListView optionsList;

        public QuestionViewHolder(View itemView) {
            super(itemView);
            questionText = itemView.findViewById(R.id.question_text);
            optionsList = itemView.findViewById(R.id.options_list);
        }
    }
}
