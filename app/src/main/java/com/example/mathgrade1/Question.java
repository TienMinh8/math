package com.example.mathgrade1;

import java.util.List;

public class Question {
    private String question;
    private String A;
    private String B;
    private String C;
    private String D;
    private String result;

    public Question(String question, String a, String b, String c, String d, String result) {
        this.question = question;
        this.A = a;
        this.B = b;
        this.C = c;
        this.D = d;
        this.result = result;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getA() {
        return A;
    }

    public void setA(String a) {
        A = a;
    }

    public String getB() {
        return B;
    }

    public void setB(String b) {
        B = b;
    }

    public String getC() {
        return C;
    }

    public void setC(String c) {
        C = c;
    }

    public String getD() {
        return D;
    }

    public void setD(String d) {
        D = d;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getCorrectAnswer(){
        switch (result) {
            case "A":
                return A;
            case "B":
                return B;
            case "C":
                return C;
            case "D":
                return D;
            default:
                return "";
        }
    }
}
