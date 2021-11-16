package com.example.studentregistration.ui.feedback;

public class FeedbackDetail {

    private String name;
    private String Feedback;
    public FeedbackDetail(){};
    public FeedbackDetail(String name, String feedback)
    {
        this.name = name;
        this.Feedback = feedback;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFeedback() {
        return Feedback;
    }

    public void setFeedback(String feedback) {
        Feedback = feedback;
    }
}
