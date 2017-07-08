package com.example.refactoring.chapter_one;

/**
 * Created by Muhaitian on 26/06/2017.
 */

public class Movie {

    public static final int CHILDRENS = 2;
    public static final int REGULAR = 0;
    public static final int NEW_REGULAR = 1;

    private String _title;
    private Price _Price;

    public Movie(String _title, int price_Code) {
        this._title = _title;
        setPriceCode(price_Code);
    }

    private void setPriceCode(int priceCode){
        switch (priceCode){
            case CHILDRENS:
                _Price = new ChildrensPrice();
                break;
            case REGULAR:
                _Price = new RegularPrice();
                break;
            case NEW_REGULAR:
                _Price = new NewRegularPrice();
                break;
        }
    }

    public String get_title() {
        return _title;
    }

    public void set_title(String _title) {
        this._title = _title;
    }

    public int getPrice_Code() {
        return _Price.getPriceCode();}

    public double getCharge(int dayRent) {
        return _Price.getCharge(dayRent);
    }

    public int getFrequentRenterPoints(int dayrent) {
        return _Price.getFrequentRenterPoints(dayrent);
    }

}
