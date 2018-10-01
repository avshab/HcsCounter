package com.example.ashab.hcs;

public class HcsCounter {

    double currentHcs = 0.0;

    public void SetHcs(double data) {
        currentHcs = data;
    }

    public double GetHcs() {
        return currentHcs;
    }
}
