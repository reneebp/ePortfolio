package ePortfolio;
import java.util.*;
import java.util.ArrayList;
import ePortfolio.Investment;

/**
 * Mutual Funds method where all mutual fund dedicated methods are
 * Mostly holds arithmethic operations
 *
 * @author Renee Palmer
 */

public class mutualFunds extends Investment{
    private static final double REDUMPTION_FEE = 45.00;
    private int quantity;
    private double price;
    private String symbol;
    /**
     * Buy fund method, arithmethic operations for the purchase of a mutual fund
     * @q q = quantity of fund
     * @p p  =price of fund
     * @return total cost of fund
     * */
    public double buyFund(int q, double p){
        double cost = q * p;

        return cost;
    }
    /**
     * Sell partial fund method, arithmethic operations for the partial sale of a mutual fund
     * @q q = quantity of fund
     * @p p  =price of fund
     * @return total money gained
     * */
    public double sellPartialFund(int q, double p){
        double payment = (q * p) - REDUMPTION_FEE;
        return payment;
    }
    /**
     * Sell fund method, arithmethic operations for the total sale of a mutual fund
     * @q q = quantity of fund
     * @p p  =price of fund
     * @return total money gained
     * */
    public double sellAllFund(int q, double p, double bv){
        double value = ((q * p) - REDUMPTION_FEE) - bv;
        return value;
    }
    /*
    private double partialSellFund(double bv, int q, double pq){
        double value  = bv * (q / pq);

        return value;
    } 
    public String toString(){

        return null;
    }*/
}

