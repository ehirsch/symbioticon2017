package io.swagger.client.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cdemon on 13.09.17.
 */

public class SmartTransaction extends GiroTransaction {

    @SerializedName("questions")
    private List<String> questions = new ArrayList<>();

    @SerializedName("answers")
    private List<Boolean> answers = new ArrayList<>();

    public SmartTransaction(GiroTransaction t) {
        setId(t.getId());
        setType(t.getType());
        setTransactionPatternId(t.getTransactionPatternId());
        setAmount(t.getAmount());
        setBookingDate(t.getBookingDate());
        setBookingKey(t.getBookingKey());
        setCreditor(t.getCreditor());
        setPurpose(t.getPurpose());
    }

    public List<String> getQuestions() {
        return questions;
    }

    public void setQuestions(List<String> questions) {
        this.questions = questions;
    }

    public List<Boolean> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Boolean> answers) {
        this.answers = answers;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("with class SmartTransaction {\n");
        sb.append("    ").append(toIndentedString(super.toString())).append("\n");

        for(String q : getQuestions()) {
            sb.append("    questions entry: ").append(toIndentedString(q)).append("\n");
        }

        for(Boolean a : getAnswers()) {
            sb.append("    answers entry: ").append(toIndentedString(""+a)).append("\n");
        }

        sb.append("}");
        return sb.toString();
    }
}
