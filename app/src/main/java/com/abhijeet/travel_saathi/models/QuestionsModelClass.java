package com.abhijeet.travel_saathi.models;

public class QuestionsModelClass {
    private String questionText;
    private String[] options;
    private int selectedOption = -1;

    public QuestionsModelClass(String questionText, String[] options) {
        this.questionText = questionText;
        this.options = options;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String[] getOptions() {
        return options;
    }

    public int getSelectedOption() {
        return selectedOption;
    }

    public void setSelectedOption(int selectedOption) {
        this.selectedOption = selectedOption;
    }
}
