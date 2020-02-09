package com.example.job.bean;

public class Apply {
    private String allApply;
    private String agree;
    private String unreviewed;

    public Apply(String agree,String unreviewed){
        this.agree=agree;
        this.unreviewed=unreviewed;
    }
    public Apply(String agree){
        this.agree=agree;

    }
    public Apply(){}

    public String getAllApply() {
        return allApply;
    }

    public void setAllApply(String allApply) {
        this.allApply = allApply;
    }

    public String getAgree() {
        return agree;
    }

    public void setAgree(String agree) {
        this.agree = agree;
    }

    public String getUnreviewed() {
        return unreviewed;
    }

    public void setUnreviewed(String unreviewed) {
        this.unreviewed = unreviewed;
    }
}
