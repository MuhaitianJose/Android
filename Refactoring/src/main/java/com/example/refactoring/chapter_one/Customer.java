package com.example.refactoring.chapter_one;

import java.util.Enumeration;
import java.util.Vector;

/**
 * Created by Muhaitian on 26/06/2017.
 */

public class Customer {

    private String _name;
    private Vector _Rentals = new Vector();

    public Customer(String _name) {
        this._name = _name;
    }

    public void addRental(Rental _retal) {
        if (_Rentals != null) {
            _Rentals.addElement(_retal);
        }
    }

    public String getName() {
        return _name;
    }

    /**
     * 生成表单
     */
    public String statusMent() {
//        double totalCount = 0;
        int frequentRenterPoints = 0;
        Enumeration rentals = _Rentals.elements();

        String Resualts = "Rental record for " + getName() + "\n";

        while (rentals.hasMoreElements()) {
//            double thisAmount = 0;

            Rental rental = (Rental) rentals.nextElement();
//            thisAmount = amountfor(rental);

//            frequentRenterPoints += rental.getfFequentRenterPoints();
//            if (rental.get_Movie().getPrice_Code() == Movie.NEW_REGULAR && rental.get_dayRented() > 1) {
//                frequentRenterPoints++;
//            }
            Resualts += "\t" + rental.get_Movie().get_title() + "\t" + String.valueOf(rental.getChatge()) + "\n";
//            totalCount += rental.getChatge();
        }

        Resualts += "Amount owed is" + String.valueOf(getTotalCount()) + "\n";
        Resualts += "you earned is" + String.valueOf(getTotalfrequentRenterPoints()) + "frequent renter point";

        return Resualts;


    }

    public int getTotalfrequentRenterPoints(){

        int renterPoint = 0;
        Enumeration rentals = _Rentals.elements();
        while (rentals.hasMoreElements()){
            Rental rental = (Rental) rentals.nextElement();
            renterPoint+=rental.getfFequentRenterPoints();

        }
        return renterPoint;
    }
    public int getTotalCount(){

        int price = 0;
        Enumeration rentals = _Rentals.elements();
        while (rentals.hasMoreElements()){
            Rental rental = (Rental) rentals.nextElement();
            price+=rental.getfFequentRenterPoints();

        }
        return price;
    }



    public double amountfor(Rental aRental) {
        return aRental.getChatge();
    }

}
