package com.example.refactoring.chapter_one;

/**
 * Created by Muhaitian on 26/06/2017.
 */

public class Rental {

    private Movie _Movie;
    private int _dayRented;

    public Rental(Movie _Movie, int _dayRented) {
        this._dayRented = _dayRented;
        this._Movie = _Movie;
    }

    public Movie get_Movie() {
        return _Movie;
    }

    public void set_Movie(Movie _Movie) {
        this._Movie = _Movie;
    }

    public int get_dayRented() {
        return _dayRented;
    }

    public void set_dayRented(int _dayRented) {
        this._dayRented = _dayRented;
    }

    public double getChatge() {
        return get_Movie().getCharge(_dayRented);
    }

    public int getfFequentRenterPoints() {
        return get_Movie().getFrequentRenterPoints(_dayRented);
    }

}
