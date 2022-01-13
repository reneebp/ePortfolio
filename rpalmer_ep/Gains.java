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
 * Gains class where the getGains commmand gui is set up
 *
 * @author Renee Palmer
*/
public class Gains extends JFrame{
    Investment i;
    JSplitPane hSplit;
    JLabel gain;
    JTextField g;
    JPanel rightPanel;
    Container contentPane;
    JTextArea ta;
    /**
     * Gains constructor to set up getGains commmand frame
    **/
    public Gains(){
        menu();
        ta = new JTextArea(4,30);
        ta.setEditable(false);
        rightPanel = new JPanel();
        gain = new JLabel("Total Gains:");
        g = new JTextField(10);
        rightPanel.add(gain);
        g.setEditable(false);
        rightPanel.add(g);
        hSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,false,new JScrollPane(ta),rightPanel);
        hSplit.setDividerSize(10);
        hSplit.setOneTouchExpandable(true);
        contentPane = getContentPane();
        contentPane.add(hSplit);
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    public void display(double sum){
        g.setText(String.valueOf(sum));
    }

    /**
     * menu method to set up menubar of commands 
    * */
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
                i.Update();; 
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

}


