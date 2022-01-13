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
 * Buy class where the buy commmand gui is set up
 * @author Renee Palmer
*/
public class Buy extends JFrame{

    JSplitPane hSplit, vSplit;
    JLabel type, symbol, name, price, quantity;
    JTextField s, n, p, q;
    JPanel leftPanel, rightPanel;
    Container contentPane;
    JButton button1;
    JButton button2;
    JTextArea ta;
    PrintStream ps;
    JComboBox<String> list;
    Investment i;

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
     * Buy constructor to set up buy commmand frame
    */
    public Buy(){
        menu();
        ta = new JTextArea(4,30);
        ps = new PrintStream(new CustomOutputStream(ta));
        ta.setEditable(false);
        System.setOut(ps);
        leftPanel = new JPanel();
        list = new JComboBox<>(new String[] {"Stock", "Mutual Fund"});
        type = new JLabel("Type");
        symbol = new JLabel("Symbol");
        name = new JLabel("Name");
        quantity = new JLabel("Quantity");
        price = new JLabel("Price");
        s = new JTextField(10);
        n = new JTextField(15);
        p = new JTextField(7);
        q = new JTextField(7);
        leftPanel.add(type);
        leftPanel.add(list);
        leftPanel.add(symbol);
        leftPanel.add(s);
        leftPanel.add(name);
        leftPanel.add(n);
        leftPanel.add(quantity);
        leftPanel.add(q);
        leftPanel.add(price);
        leftPanel.add(p);
        button1 = new JButton("Buy");
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
    public void buttonAction(){
        ActionListener bl1 = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                try{
                    String type = String.valueOf(list.getSelectedItem());
                    String symbol  = s.getText();
                    String name  = n.getText();
                    int quantity  = Integer.parseInt(q.getText());
                    double price  = Double.parseDouble(p.getText());
                    i = new Investment();
                    i.Buy(type,symbol, name, quantity, price);
                    dispose();
                }catch(NumberFormatException e){
                    System.out.println("Quantity and Price must be numbers. Try Again");
                    Buy b = new Buy();
                    dispose();
                }
            }
        };
        ActionListener bl2 = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                s.setText("");
                n.setText("");
                q.setText("");
                p.setText("");
            }
        };
        button1.addActionListener(bl1);
        button2.addActionListener(bl2);
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

}


