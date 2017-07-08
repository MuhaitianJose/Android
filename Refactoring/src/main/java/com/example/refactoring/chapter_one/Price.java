package com.example.refactoring.chapter_one;

/**
 * Created by Muhaitian on 27/06/2017.
 */

public abstract class Price {
    abstract int getPriceCode();

    abstract double getCharge(int dayRent);

    public int getFrequentRenterPoints(int dayrent){
        return 1;
    }

}
