package com.adropofliquid.fourfoursevenfinal;

import java.util.Random;

public class Captcha {

    private int first;
    private int second;
    private int answer;

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public Captcha newCaptcha() {
        Captcha captcha = new Captcha();
        captcha.setFirst(newRandomNumber());
        captcha.setSecond(newRandomNumber());
        captcha.calculateAnswer();
        return captcha;
    }

    private void calculateAnswer() {
        answer = first + second;
    }

    private int newRandomNumber() {
        Random random = new Random();
        return random.nextInt(20);
    }

    public String toString(){
        return first + " + " + second;
    }
}
