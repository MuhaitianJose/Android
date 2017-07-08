package com.example.refactoring.chapter_one;

/**
 * Created by Muhaitian on 27/06/2017.
 */

public class ChildrensPrice extends Price {
    @Override
    int getPriceCode() {
        return Movie.CHILDRENS;
    }

    @Override
    double getCharge(int dayRent) {
        double resualt = 0;
        resualt += dayRent * 3;
        return resualt;
    }
}
