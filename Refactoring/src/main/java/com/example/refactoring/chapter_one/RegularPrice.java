package com.example.refactoring.chapter_one;

/**
 * Created by Muhaitian on 27/06/2017.
 */

public class RegularPrice extends Price {
    @Override
    int getPriceCode() {
        return Movie.REGULAR;
    }

    @Override
    double getCharge(int dayRent) {
        double resualt = 0;
        resualt += 1.5;
        if (dayRent > 3) {
            resualt += (dayRent - 3) * 1.5;
        }
        return resualt;
    }
}
