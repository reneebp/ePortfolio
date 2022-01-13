package ePortfolio;
import java.util.*;
import java.lang.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.BadLocationException;
/**
 * Search class where the search commmand gui is set up
 *
 * @author Renee Palmer
*/
public class Search extends JFrame{
    JSplitPane hSplit, vSplit;
    JLabel symbol, name_keywords, price_range;
    JTextField s, nk, pr;
    JPanel leftPanel, rightPanel;
    Container contentPane;
    JButton button1, button2;
    JTextArea ta;
    //PrintStream ps;
    Investment i;
    /**
     * Search constructor to set up search commmand frame
    **/
    public Search(){
        menu();
        ta = new JTextArea(4,30);
        ta.setEditable(false);
        leftPanel = new JPanel();
        symbol = new JLabel("Symbol");
        name_keywords = new JLabel("Name/Keywords");
        price_range = new JLabel("Price Range");
        s = new JTextField(10);
        nk = new JTextField(15);
        pr = new JTextField(7);
        leftPanel.add(symbol);
        leftPanel.add(s);
        leftPanel.add(name_keywords);
        leftPanel.add(nk);
        leftPanel.add(price_range);
        leftPanel.add(pr);
        button1 = new JButton("Search");
        button2 = new JButton("Reset");
        rightPanel = new JPanel();
        rightPanel.add(button1);
        rightPanel.add(button2);

        hSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,false,new JScrollPane(leftPanel),rightPanel);
        hSplit.setDividerSize(10);
        hSplit.setOneTouchExpandable(true);
        vSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT,hSplit,new JScrollPane(ta));
        vSplit.setDividerSize(10);
        vSplit.setDividerLocation(150);
        contentPane = getContentPane();
        contentPane.add(vSplit);
        
        buttonAction();
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    /**
     * menu method to set up menubar of commands 
    **/
    public void menu(){
        JMenuBar menubar = new JMenuBar();
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
                i = new Investment();
                i.Update();
            }
        }
        update.addActionListener(new updateaction());

        class gainsaction implements ActionListener{
            public void actionPerformed (ActionEvent e){
                dispose(); 
                i = new Investment();
                i.Gains();
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

    public void buttonAction(){
        ActionListener bl1 = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                String symbol  = s.getText();
                String nameK  = nk.getText();
                String priceR  = pr.getText();
                i = new Investment();
                i.Search(symbol, nameK, priceR);
                //dispose();
            }
        };
        ActionListener bl2 = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                s.setText("");
                nk.setText("");
                pr.setText("");
            }
        };
        button1.addActionListener(bl1);
        button2.addActionListener(bl2);
    }

}


