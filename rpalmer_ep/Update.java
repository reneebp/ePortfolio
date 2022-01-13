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
 * Update class where the update commmand gui is set up
 *
 * @author Renee Palmer
*/
public class Update extends JFrame{
    JSplitPane hSplit, vSplit;
    JLabel type, symbol, name, price, quantity;
    JTextField t, s, n, p, q;
    JPanel leftPanel, rightPanel;
    Container contentPane;
    JButton prev, next, update, done;
    JTextArea ta;
    PrintStream ps;
    Investment inv;
    int i;
    Object click;

    class CustomOutputStream extends OutputStream {
        private JTextArea textArea;
         
        public CustomOutputStream(JTextArea textArea) {
            this.textArea = textArea;
        }
         
        @Override
        public void write(int b) throws IOException {
            textArea.append(String.valueOf((char)b));
            textArea.setCaretPosition(textArea.getDocument().getLength());
        }
    }
    /**
     * Update constructor to set up update commmand frame
    **/
    public Update(){
        menu();
        ta = new JTextArea(4,30);
        leftPanel = new JPanel();
        type = new JLabel("Type");
        symbol = new JLabel("Symbol");
        name = new JLabel("Name");
        quantity = new JLabel("Quantity");
        price = new JLabel("Price");
        t = new JTextField(10);
        s = new JTextField(10);
        n = new JTextField(15);
        p = new JTextField(7);
        q = new JTextField(7);
        leftPanel.add(type);
        leftPanel.add(t);
        t.setEditable(false);
        leftPanel.add(symbol);
        leftPanel.add(s);
        s.setEditable(false);
        leftPanel.add(name);
        leftPanel.add(n);
        n.setEditable(false);
        leftPanel.add(quantity);
        leftPanel.add(q);
        q.setEditable(false);
        leftPanel.add(price);
        leftPanel.add(p);
        prev = new JButton("Previous");
        next = new JButton("Next");
        update = new JButton("Update");
        done = new JButton("Done");
        rightPanel = new JPanel();
        rightPanel.add(prev);
        rightPanel.add(next);
        rightPanel.add(update);
        rightPanel.add(done);

        hSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,false,new JScrollPane(leftPanel),rightPanel);
        hSplit.setDividerSize(10);
        hSplit.setOneTouchExpandable(true);
        vSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT,hSplit,new JScrollPane(ta));
        vSplit.setDividerSize(10);
        vSplit.setDividerLocation(150);
        contentPane = getContentPane();
        contentPane.add(vSplit);

        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    public ArrayList<Investment> buttonAction(ArrayList<Investment>investments){
        for(i = 0; i < investments.size(); i++){
            if(i == 0){
                inv = investments.get(i);
                prev.setVisible(false);
                t.setText(inv.getInvestmentType());
                s.setText(inv.getSymbol());
                n.setText(inv.getName());
                q.setText(String.valueOf(inv.getQuantity()));
                p.setText(String.valueOf(inv.getPrice()));
            }
            ActionListener bl1 = new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent ae){ 
                    i--;
                    inv = investments.get(i);
                    prev.setVisible(true);
                    t.setText(inv.getInvestmentType());
                    s.setText(inv.getSymbol());
                    n.setText(inv.getName());
                    q.setText(String.valueOf(inv.getQuantity()));
                    p.setText(String.valueOf(inv.getPrice()));
                }
            };
            ActionListener bl2 = new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent ae){ 
                    if(i == (investments.size() - 1)){
                        inv = investments.get(i);
                        prev.setVisible(true);
                        next.setVisible(false);
                        t.setText(inv.getInvestmentType());
                        s.setText(inv.getSymbol());
                        n.setText(inv.getName());
                        q.setText(String.valueOf(inv.getQuantity()));
                        p.setText(String.valueOf(inv.getPrice()));
                    }
                    if(i < (investments.size() - 1)){
                        i++;
                        if(i == (investments.size() - 1)){
                            next.setVisible(false);
                        }
                        inv = investments.get(i);
                        prev.setVisible(true);
                        t.setText(inv.getInvestmentType());
                        s.setText(inv.getSymbol());
                        n.setText(inv.getName());
                        q.setText(String.valueOf(inv.getQuantity()));
                        p.setText(String.valueOf(inv.getPrice()));
                    }
                }
            };
            ActionListener bl3 = new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent ae){ 
                    double price  = Double.parseDouble(p.getText());
                    inv = investments.get(i);
                    inv.setPrice(price);
                    ta.setText(inv.getSymbol()+" has been updated to a price of $"+price);
                }
            };
            ActionListener bl0 = new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent ae){ 
                    dispose();
                    Investment n = new Investment();
                }
            };
            done.addActionListener(bl0);
            prev.addActionListener(bl1);
            next.addActionListener(bl2);
            update.addActionListener(bl3);
        }
        return investments;
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
                inv = new Investment();
                inv.Update();  
            }
        }
        update.addActionListener(new updateaction());

        class gainsaction implements ActionListener{
            public void actionPerformed (ActionEvent e){
                dispose(); 
                inv = new Investment();
                inv.Gains();   
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


