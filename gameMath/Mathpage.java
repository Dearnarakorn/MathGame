package gameMath;
import java.awt.*;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Mathpage {
    //Mathpage
    private JFrame frame2;
    JPanel contentPanel = new JPanel();

    private int numsign;
    private int num1;
    private int num2;
    private char sign;
    private int correctAnswer;
    private int Choice;

    private JPanel panel1;
    private JLabel labelHeart;
    private int Numheart = 3;
    private JLabel labelScore;
    private JLabel time;
    private Icon imgheart;

    private JPanel panel2;
    private JLabel labelpb;

    private JPanel panel3;
    private JButton butans1;
    private JButton butans2;
    private JButton butans3;
    private JButton butans4;

    private JPanel panel4;
    private JButton butnew;
    private JButton butback;

    private int score = 0;
    private int level;

    
    public Homepage Homep;
    //public Homepage Startgame = new Homepage();

    
    public Mathpage(){
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        frame2 = new JFrame("Math Game v2");
        frame2.setSize(500, 700);
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.getContentPane().add(contentPanel);

        datailMathpage();
        //frame2.pack();
        frame2.setVisible(true);

    }


    private void RandomProblem(){
        if(level==0){ //easy
            numsign = 0;
        }else if(level==1){ //Normal
            numsign = (int)Math.floor(Math.random() * 2);
        }
        else numsign = (int)(Math.random()*3); //Hard
        num1 = (int)(Math.random() * 20) + 1;
        num2 = (int)(Math.random() * 10) + 1;

        switch(numsign){
            case 0:
            sign = '+';
            correctAnswer = num1+num2;
            break;
            case 1:
            sign = '-';
            correctAnswer = num1-num2;
            break;
            case 2:
            sign = 'x';
            correctAnswer = num1*num2;
            break;
        }
    }

    private void RandomChoice(){
        int min = -5;
        int max = 20;
        int range = max - min + 1; //41
        //int randomInt = (int)(Math.random()*range)+min;
        int num1,num2,num3;
        do{
            num1 = ((int)(Math.random()*range)+min);
        }while(num1 == correctAnswer);
        do{
            num2 = ((int)(Math.random()*range)+min);
        }while(num2 == correctAnswer || num2 == num1);
        do{
            num3 = ((int)(Math.random()*range)+min);
        }while(num3 == correctAnswer || num3 == num2 || num3 == num1);
        
        Double random = Math.random();
        if(random<0.25){
            Choice = 1;
            butans1.setText(""+correctAnswer);
            butans2.setText(""+num1);
            butans3.setText(""+num2);
            butans4.setText(""+num3);
        }else if(random<0.5){
            Choice = 2;
            butans1.setText(""+num1);
            butans2.setText(""+correctAnswer);
            butans3.setText(""+num2);
            butans4.setText(""+num3);
        }else if(random<0.75){
            Choice = 3;
            butans1.setText(""+num1);
            butans2.setText(""+num2);
            butans3.setText(""+correctAnswer);
            butans4.setText(""+num3);
        }else{
            Choice = 4;
            butans1.setText(""+num1);
            butans2.setText(""+num2);   
            butans3.setText(""+num3);
            butans4.setText(""+correctAnswer);
        }
    }

    private void datailMathpage(){
        panel1 = new JPanel(new GridLayout(0,3));
        panel1.setMaximumSize(new Dimension(Integer.MAX_VALUE,5000));
        //panel1.setPreferredSize(new Dimension(400,5));
        labelHeart = new JLabel("Heart x"+Numheart,SwingConstants.LEFT);
        try {
            imgheart = new ImageIcon("game/heart.png");
        } catch (Exception e) {
            System.out.println(e);
        }
        labelHeart.setIcon(imgheart);
        labelScore = new JLabel("Score : "+score,SwingConstants.CENTER);
        time = new JLabel("00.00",SwingConstants.RIGHT);
        panel1.add(labelHeart);
        panel1.add(labelScore);
        panel1.add(time);
        panel1.setBackground(Color.YELLOW);
        contentPanel.add(panel1);


        //โจทย์
        RandomProblem();
        panel2 = new JPanel();
        labelpb = new JLabel(num1+" "+sign+" "+num2+" = ?");
        labelpb.setFont(new Font("Magneto", Font.PLAIN, 30));
        panel2.add(labelpb);
        //panel2.setBackground(Color.CYAN);
        contentPanel.add(Box.createVerticalGlue()); //Y
        //contentPanel.add(Box.createHorizontalGlue()); //X
        contentPanel.add(panel2);


        //ช้อย
        panel3 = new JPanel(new GridLayout(2,2,5,5));
        butans1 = new JButton();
        butans2 = new JButton();
        butans3 = new JButton();
        butans4 = new JButton();
        RandomChoice();
        panel3.add(butans1);
        panel3.add(butans2);
        panel3.add(butans3);
        panel3.add(butans4);
        panel3.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        contentPanel.add(panel3);

        panel4 = new JPanel(new GridLayout(0,2,20,0));
        butnew = new JButton("New");    panel4.add(butnew);
        butback = new JButton("Back");  panel4.add(butback);
        panel4.setMaximumSize(new Dimension(Integer.MAX_VALUE,5000));
        contentPanel.add(panel4);


        AllButtonListener bl = new AllButtonListener();
        butnew.addActionListener(bl);
        butback.addActionListener(bl);
        butans1.addActionListener(bl);
        butans2.addActionListener(bl);
        butans3.addActionListener(bl);
        butans4.addActionListener(bl);
    }

    //home
    protected void setVisGame(boolean status){
        frame2.setVisible(status);
    }
    protected void setGameUI(Homepage Home){
        Homep = Home;
    }
    protected void setLevel(int lv){
        level = lv;
    }

    private class AllButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent ev) {
            JButton source = (JButton)ev.getSource();
            if(source==butnew){
                System.out.println("New game");
                Numheart=3; score=0;
                labelHeart.setText("Heart x"+Numheart);
                labelScore.setText("Score : "+score);
                RandomProblem();    labelpb.setText(num1+" "+sign+" "+num2+" = ?");
                RandomChoice();
                butans1.setEnabled(true);  butans2.setEnabled(true);  butans3.setEnabled(true);  butans4.setEnabled(true);
            }else if(source==butback){
                Numheart=3; score=0;
                labelHeart.setText("Heart x"+Numheart);
                labelScore.setText("Score : "+score);
                RandomProblem();    labelpb.setText(num1+" "+sign+" "+num2+" = ?");
                RandomChoice();
                butans1.setEnabled(true);  butans2.setEnabled(true);  butans3.setEnabled(true);  butans4.setEnabled(true);
                Homep.setVisHome(true);
                setVisGame(false);
            }else if(source==butans1){
                if(Choice==1){
                    System.out.println("You are right!!");
                    score++;
                    labelScore.setText("Score : "+score);
                    RandomProblem();    labelpb.setText(num1+" "+sign+" "+num2+" = ?");
                    RandomChoice();
                }
                else{
                    Numheart--;
                    System.out.println("You are wrong. You have "+Numheart+" hearts left.");
                    labelHeart.setText("Heart x"+Numheart);
                    if(Numheart<=0){
                        System.out.println("GameEnd");
                        butans1.setEnabled(false);  butans2.setEnabled(false);  butans3.setEnabled(false);  butans4.setEnabled(false);

                    }
                }
            }else if(source==butans2){
                if(Choice==2){
                    System.out.println("You are right!!");
                    score++;
                    labelScore.setText("Score : "+score);
                    RandomProblem();    labelpb.setText(num1+" "+sign+" "+num2+" = ?");
                    RandomChoice();
                }
                else{
                    Numheart--;
                    System.out.println("You are wrong. You have "+Numheart+" hearts left.");
                    labelHeart.setText("Heart x"+Numheart);
                    if(Numheart<=0){
                        System.out.println("GameEnd");
                        butans1.setEnabled(false);  butans2.setEnabled(false);  butans3.setEnabled(false);  butans4.setEnabled(false);

                    }
                }
            }else if(source==butans3){
                if(Choice==3){
                    System.out.println("You are right!!");
                    score++;
                    labelScore.setText("Score : "+score);
                    RandomProblem();    labelpb.setText(num1+" "+sign+" "+num2+" = ?");
                    RandomChoice();
                }
                else{
                    Numheart--;
                    System.out.println("You are wrong. You have "+Numheart+" hearts left.");
                    labelHeart.setText("Heart x"+Numheart);
                    if(Numheart<=0){
                        System.out.println("GameEnd");
                        butans1.setEnabled(false);  butans2.setEnabled(false);  butans3.setEnabled(false);  butans4.setEnabled(false);

                    }
                }
            }else if(source==butans4){
                if(Choice==4){
                    System.out.println("You are right!!");
                    score++;
                    labelScore.setText("Score : "+score);
                    RandomProblem();    labelpb.setText(num1+" "+sign+" "+num2+" = ?");
                    RandomChoice();
                }
                else{
                    Numheart--;
                    System.out.println("You are wrong. You have "+Numheart+" hearts left.");
                    labelHeart.setText("Heart x"+Numheart);
                    if(Numheart<=0){
                        System.out.println("GameEnd");
                        butans1.setEnabled(false);  butans2.setEnabled(false);  butans3.setEnabled(false);  butans4.setEnabled(false);

                    }
                }
            }
            
        }

    }
    
}


