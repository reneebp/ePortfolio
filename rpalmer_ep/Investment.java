/**
 * Title: Assignment 3
 * Author: Renee Palmer
 * Student Number: 1043935
 * Email:rpalme05@uoguelph.ca
 * Instructor: F. Song 
 * Course: CIS 2430
*/
package ePortfolio;
import java.util.*;
import java.lang.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.BadLocationException;

public class Investment extends JFrame{
    private int quantity;
    private double price;
    private String name;
    private String symbol;
    private double bookValue;
    private String type;
    public static ArrayList<Investment>investments;
    public static HashMap<String, Integer>investmentHash;

    private JTextArea ta;
    private JMenuBar menubar;
    private PrintStream ps;
    private PrintStream so;
/**
 * Investment class where all buying, selling
 * and updating of investments are handled
 *
 * Bugs: Update and gain methods not full functionable
 *
 * @author Renee Palmer
 */
    public Investment(){
        quantity = 0;
        price = 0.0;
        name = "";
        symbol = "";
        bookValue = 0.0;

        setSize(700, 600);
        setTitle("ePortfolio");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ta = new JTextArea(30, 50);
        ta.setEditable(false);
        PrintStream io = System.out;
        PrintStream ps = new PrintStream(new OutputStream(){
        @Override
            public void write(int b) throws IOException {
                ta.append(String.valueOf((char)b));
                ta.setCaretPosition(ta.getDocument().getLength());
            }
        });
        System.setOut(ps);
        add(ta);
        menu();
        setVisible(true);
    }

    public void menu(){
        menubar = new JMenuBar();
        setJMenuBar(menubar);
        JMenu commands = new JMenu("Commands");
        menubar.add(commands);
        JMenuItem buy = new JMenuItem("Buy an investment");
        JMenuItem sell = new JMenuItem("Sell an investment");
        JMenuItem update = new JMenuItem("Update investments");
        JMenuItem gains = new JMenuItem("Show total gains");
        JMenuItem search = new JMenuItem("Search investments");
        JMenuItem exit = new JMenuItem("Exit ePortfolio");
        commands.add(buy);
        commands.add(sell);
        commands.add(update);
        commands.add(gains);
        commands.add(search);
        commands.add(exit);
        class exitaction implements ActionListener{
            public void actionPerformed (ActionEvent e){
                System.exit(0);  
            }
        }
        exit.addActionListener(new exitaction());

        class buyaction implements ActionListener{
            public void actionPerformed (ActionEvent e){
                dispose();
                Buy b = new Buy();
            }
        }
        buy.addActionListener(new buyaction());

        class sellaction implements ActionListener{
            public void actionPerformed (ActionEvent e){
                dispose();
                Sell s = new Sell();  
            }
        }
        sell.addActionListener(new sellaction());

        class updateaction implements ActionListener{
            public void actionPerformed (ActionEvent e){
                dispose();
                Update(); 
            }
        }
        update.addActionListener(new updateaction());

        class gainsaction implements ActionListener{
            public void actionPerformed (ActionEvent e){
                dispose();
                Gains(); 
            }
        }
        gains.addActionListener(new gainsaction());

        class searchaction implements ActionListener{
            public void actionPerformed (ActionEvent e){
                dispose();
                Search s = new Search();   
            }
        }
        search.addActionListener(new searchaction());
    }
    /**
     * Investnment constuctor to initialize variables of investments 
     * @quantity quantity = quantity of investnment 
     * @price price of investment 
     * @name name of investment 
     * @symbol symbol of investment 
    * */
    public Investment(int quantity, double price, String name, String symbol){
        this.quantity = quantity;
        this.price = price;
        this.name = name;
        this.symbol = symbol;
    }
    /**
     * Buy method where purchase of investments are handled 
     * ask for investment type and prompts user regarding 
     * investment information. If investment does not already exist, a new investment is created
    * */
    public void Buy(String type, String symbol, String name, int quantity, double price){
        int count = 0;
        if(type.isBlank() || symbol.isBlank() || name.isBlank()){
            System.out.println("Must fill all fields. Try Again");
            Buy b = new Buy();
            dispose();
        }
        for(Investment n : investments){
            if(symbol.equals(n.getSymbol())){
                if(quantity <= 0){
                    System.out.println("Insufficient quantity. Try Again");
                    Buy b = new Buy();
                    dispose();
                }
                int prevQuantity = n.getQuantity();
                if(type.equals("Stock")){
                    Stocks s = new Stocks();
                    bookValue = s.buyStock(quantity, price);
                }
                if(type.equals("Mutual Fund")){
                    mutualFunds m = new mutualFunds();
                    bookValue = m.buyFund(quantity, price);
                }
                quantity += prevQuantity;
                n.setQuantity(quantity);
                n.setPrice(price);
                n.setBookValue(bookValue);
                if(type.equals("Stock")){
                    System.out.println("You now own "+quantity+" shares of "+symbol+" at $"+price);
                    dispose();
                    return;
                }
                if(type.equals("Mutual Fund")){
                    System.out.println("You now own "+quantity+" units of "+symbol+" at $"+price);
                    dispose();
                    return;
                }
            }
            count++;
        }
        if((count == investments.size())){
            if(quantity <= 0){
                System.out.println("Insufficient quantity. Try Again");
                Buy b = new Buy();
                dispose();
            }
            Investment invest = new Investment(quantity, price, name, symbol);
            if(type.equals("Stock")){
                Stocks s = new Stocks();
                bookValue = s.buyStock(quantity, price);
            }
            if(type.equals("Mutual Fund")){
                mutualFunds m = new mutualFunds();
                bookValue = m.buyFund(quantity, price);
            }
            investments.add(invest);
            investmentHash.put(name, count);
            invest.setBookValue(bookValue);
            if(type.equals("Stock")){
                System.out.println("You now own "+quantity+" shares of "+symbol+" at $"+price);
                invest.setType("stock");
                dispose();
                return;
            }
            if(type.equals("Mutual Fund")){
                System.out.println("You now own "+quantity+" units of "+symbol+" at $"+price);
                invest.setType("mutualfund");
                dispose();
                return;
            }
        }
    }
    /**
     * Sell method where selling of investments are handled.
     * Method ask for investment type and prompts user regarding 
     * investment information. If user wishes to sell all invesment quantity, the investment is deleted from
     * the ArrayList
    * */
    public void Sell(String type, String symbol, String name, int quantity, double price){
        int count = 0;
        double gain = 0.0;
        if(type.isBlank() || symbol.isBlank() || name.isBlank()){
            System.out.println("Must fill all fields. Try Again");
            Sell s = new Sell();
            dispose();
        }
        for(Investment n : investments){
            if(symbol.equals(n.getSymbol())){
                int prevQuantity = n.getQuantity();
                if(quantity > prevQuantity){
                    System.out.println("Cannot sell more than you own. Try Again");
                    Sell s = new Sell();
                    dispose();
                }
                if(prevQuantity == quantity){
                    if(type.equals("Stock")){
                        Stocks s = new Stocks();
                        s.sellAllStock(quantity, price, gain);
                        System.out.println("You no longer own any shares of "+symbol);
                        dispose();
                        return;
                    }
                    if(type.equals("Mutual Fund")){
                        mutualFunds m = new mutualFunds();
                        m.sellAllFund(quantity, price, gain);
                        System.out.println("You no longer own any units of "+symbol);
                        dispose();
                        return;
                    }
                    investmentHash.remove(n.getName());
                    investments.remove(n);
                    dispose();
                    return;
                }
                else{
                    if(type.equals("Stock")){
                        Stocks s = new Stocks();
                        gain = s.sellPartialStock(quantity, price);
                    }
                    if(type.equals("Mutual Fund")){
                        mutualFunds m = new mutualFunds();
                        gain = m.sellPartialFund(quantity, price);
                    }
                    quantity = prevQuantity - quantity;
                    n.setPrice(price);
                    n.setQuantity(quantity);
                    if(type.equals("Stock")){
                        System.out.println("You now own "+quantity+" shares of "+symbol+" at $"+price);
                        dispose();
                        return;
                    }
                    if(type.equals("Mutual Fund")){
                        System.out.println("You now own "+quantity+" units of "+symbol+" at $"+price);
                        dispose();
                        return;
                    }
                }
            }
            count++;
        }
        if((count == investments.size())){
            System.out.println("investment does not exist in your portfolio");
            Sell s = new Sell();
            dispose();
        }
    }
    /**
     * Update method refresh the prices of all existing investments.
    * */

    public void Update(){ 
        Update u = new Update();
        investments = u.buttonAction(investments);
    }

    public void Gains(){ 
        double sum = 0.0;
        for(Investment total : investments){
            sum += total.getBookValue();
            System.out.println("Name : " + total.getName()+"\n");
            System.out.println("Symbol : " + total.getSymbol()+"\n");
            System.out.println("Quantity : " + total.getQuantity()+ "\n");
            System.out.println("Price : " + total.getPrice()+ "\n");
        }
        Gains g = new Gains();
        g.display(sum);
    }
    /**
     * Search method finds all investments that match a search request
     *  and display all attributes of these investments. Uses HashMap for keyword search
     * */
    public void Search(String symbol, String keyword, String range){
        double min = 0.0;
        double max = 0.0;
        double limit = 0.0;
        keyword = keyword.toLowerCase();
        String[] rangeNum = range.split("-");
        boolean done = false;

        if(range.charAt(0) == '-'){
            max = Integer.parseInt(range);
            max = Math.abs(min);
            done = true;
            for(String s : investmentHash.keySet()){
                if(s.contains(keyword)){
                    int index = investmentHash.get(s);
                    Investment inv = investments.get(index);
                    if((investments.get(index)).getPrice() <= max){
                        System.out.println("Name : " + inv.getName()+"\n");
                        System.out.println("Symbol : " + inv.getSymbol()+"\n");
                        System.out.println("Quantity : " + inv.getQuantity()+ "\n");
                        System.out.println("Price : " + inv.getPrice()+ "\n");
                    }
                }
            }
        }
        else if(range.charAt(range.length()-1) == '-'){
            min = Integer.parseInt(rangeNum[0]);
            done = true;
            for(String s : investmentHash.keySet()){
                if(s.contains(keyword)){
                    int index = investmentHash.get(s);
                    Investment inv = investments.get(index);;
                    if((investments.get(index)).getPrice() >= min){
                        System.out.println("Name : " + inv.getName()+"\n");
                        System.out.println("Symbol : " + inv.getSymbol()+"\n");
                        System.out.println("Quantity : " + inv.getQuantity()+ "\n");
                        System.out.println("Price : " + inv.getPrice()+ "\n");
                    }
                }
            }
        }
        
        else if(range.contains("-") == false){
            limit = Integer.parseInt(range);
            done = true;
            for(String s : investmentHash.keySet()){
                if(s.contains(keyword)){
                    int index = investmentHash.get(s);
                    Investment inv = investments.get(index);
                    if((investments.get(index)).getPrice() == limit){
                        System.out.println("Name : " + inv.getName()+"\n");
                        System.out.println("Symbol : " + inv.getSymbol()+"\n");
                        System.out.println("Quantity : " + inv.getQuantity()+ "\n");
                        System.out.println("Price : " + inv.getPrice()+ "\n");
                    }
                }
            }
        }
        else if(rangeNum.length == 2 && done == false){
            min = Integer.parseInt(rangeNum[0]);
            max = Integer.parseInt(rangeNum[1]);
            done = true;
            for(String s : investmentHash.keySet()){
                if(s.contains(keyword)){
                    int index = investmentHash.get(s);
                    Investment inv = investments.get(index);
                    if((investments.get(index)).getPrice() >= min && (investments.get(index)).getPrice() <= max){
                        System.out.println("Name : " + inv.getName()+"\n");
                        System.out.println("Symbol : " + inv.getSymbol()+"\n");
                        System.out.println("Quantity : " + inv.getQuantity()+ "\n");
                        System.out.println("Price : " + inv.getPrice()+ "\n");
                    }
                }
            }
        }
        else{
            System.out.println("No investments match. Sorry");
        }
    }
    /**
     * Sets up the bookvalue of the individual investment to be accumlated later
     * @bookValue value of individual investment 
    * */
    public void setBookValue(double bookValue){
        this.bookValue = bookValue;
    }
    /**
     * Sets up the investment type of the individual investment for file purposes
     * @t String stating type of investment
    * */
    public void setType(String t){
        this.type = t;
    }
    /**
     * Returns type of investment 
     * @return returns type of investment 
    * */
    public String getInvestmentType(){
        return this.type;
    }
    /**
     * Sets up the investment name of the individual investment
     * @name String stating name of investment
    * */
    public void setName(String name){
        this.name = name;
    }
    /**
     * Sets up the investment quantity of the individual investment
     * @tquantity int stating qunatity of investment
    * */
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
    /**
     * Sets up the investment price of the individual investment 
     * @price double stating price of investment
    * */
    public void setPrice(double price){
        this.price  = price;
    }
    /**
     * Returns bookvalue of individual investment 
     * @return returns bookvalue of investment 
    * */
    public double getBookValue(){
        return this.bookValue;
    }
    /**
     * Returns symbol of individual investment 
     * @return returns symbol of investment 
    **/
    public String getSymbol(){
        return this.symbol;
    }
    /**
     * Returns name of individual investment 
     * @return returns name of investment 
    **/
    public String getName(){
        return this.name;
    }
    /**
     * Returns quantity of individual investment 
     * @return returns quantity of investment 
    **/
    public int getQuantity(){
        return this.quantity;
    }
    /**
     * Returns price of individual investment 
     * @return returns rice of investment 
    **/
    public double getPrice(){
        return this.price;
    }
    public static void main(String[] args){
        investments = new ArrayList<Investment>();
        investmentHash = new HashMap<String, Integer>();
        Investment i = new Investment();
        int input = 0;
        boolean loop = true;
        String filename = "filename.txt";

        System.out.println("Welcome to Portfolio.");
        System.out.println("Choose a command from the \"Commands\"menu to buv or sell");
        System.out.println("an investment, update prices for all investments, get gain for the");
        System.out.println("portfolio, search for relevant investments, or quit the program.");
    }
}

