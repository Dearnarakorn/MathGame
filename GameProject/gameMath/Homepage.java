package gameMath;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Homepage {

    //Homepage
    private JPanel panel1;
    private JFrame frame1;
    private JLabel labelgo; 
    private JButton butEasy;
    private JButton butNormal;
    private JButton butHard;
    //private JButton butDonate;
    
    private Mathpage Mathp;

    public Homepage(){
        Mathp = new Mathpage();
        Mathp.setGameUI(this);
        Mathp.setVisGame(false);

        frame1 = new JFrame("Math Game v1");
        frame1.setSize(500,700);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        datailHomepage();
        frame1.setVisible(true);
        frame1.setResizable(true);//ปรับขนาด

    }

    private void  datailHomepage(){
        panel1 = new JPanel();
        labelgo = new JLabel("Math Game");
        
        //butEasy = new JButton(img1);
        butEasy = new JButton();
        butEasy.setFocusable(false);
        //butEasy.setIcon(img1);
        butEasy.setText("Easy");
        butNormal = new JButton("Normal");
        butHard = new JButton("Hard");
        //butDonate = new JButton("Donate");        

        labelgo.setFont(new Font("Magneto", Font.PLAIN, 30));
        //labelgo.setPreferredSize(new Dimension(100,100));
        butEasy.setPreferredSize(new Dimension(300,100));
        butNormal.setPreferredSize(new Dimension(300,100));
        butHard.setPreferredSize(new Dimension(300,100));
        panel1.setPreferredSize(new Dimension(300, 100));
        //panel1.setBounds(250,5,800,400);
        panel1.setBackground(Color.yellow);

        frame1.setLayout(new FlowLayout());
        panel1.add(labelgo);        
        frame1.add(panel1);
        //frame1.add(labelgo);
        frame1.add(butEasy);
        frame1.add(butNormal);
        frame1.add(butHard);

        AllButtonListener bl = new AllButtonListener();
        butEasy.addActionListener(bl);
        butNormal.addActionListener(bl);
        butHard.addActionListener(bl);

    }
    private class AllButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent ev){
            JButton source = (JButton)ev.getSource();
            if(source==butEasy){
                Mathp.setVisGame(true);
                setVisHome(false);
                Mathp.setLevel(0);
            }else if(source==butNormal){
                Mathp.setVisGame(true);
                setVisHome(false);
                Mathp.setLevel(1);
            }else if(source==butHard){
                Mathp.setVisGame(true);
                setVisHome(false);
                Mathp.setLevel(2);
            }
        }
    }

    //game 
    protected void setVisHome(boolean status){
        frame1.setVisible(status);
    }

    
    
}
