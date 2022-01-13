package ePortfolio;
import java.util.*;
import java.util.ArrayList;
import ePortfolio.Investment;

/**
 * Stock method where all stock dedicated methods are
 * Mostly holds arithmethic operations
 *
 * @author Renee Palmer
 */

public class Stocks extends Investment{
    private static final double COMMISSION = 9.99;
    private int quantity;
    private double price;
    private String symbol;
    
    /**
     * Buy stock method, arithmethic operations for the purchase of a stock
     * @q q = quantity of stock
     * @p p  =price of stock
     * @return total cost of stock
     * */
    public double buyStock(int q, double p){
        double cost = (q * p) + COMMISSION;

        return cost;
    }
    /**
     * Sell stock method, arithmethic operations for the total sale of a stock
     * @q q = quantity of stock
     * @p p  =price of stock
     * @return total money gained
     * */
    public double sellAllStock(int q, double p, double bv){
        double value = ((q * p) - COMMISSION) - bv;
        return value;
    }
    /**
     * Sell partial fund method, arithmethic operations for the partial sale of a stock
     * @q q = quantity of stock
     * @p p  =price of stock
     * @return total money gained
     * */
    public double sellPartialStock(int q, double p){
        double value = (q * p) - COMMISSION;
        return value;
    }
    /*
    private double update_Sell_BookValue(double bv, int q, double pq){
        double value  = bv * (q / pq);

        return value;
    }
    public String toString(){

        return null;
    }*/
}

