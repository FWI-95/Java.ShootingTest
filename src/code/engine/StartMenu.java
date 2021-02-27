package code.engine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import code.level.Difficulties;
import code.level.Difficulty;

public class StartMenu extends JFrame implements ActionListener {

    Difficulty ChosenDiff;

    public StartMenu() {
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setResizable(false);

        this.getContentPane().add(getContentPanel());

        this.setVisible(true);
    }

    public boolean IsOpen() {
        return (ChosenDiff == null);
    }

    public Difficulty getDifficulty() {
        return ChosenDiff;
    }

    private JPanel getContentPanel(){
        JPanel Content = new JPanel();
        
        JButton BtnEasy = new JButton("Easy");
        BtnEasy.setActionCommand("EASY");
        BtnEasy.addActionListener(this);
        BtnEasy.setSize(100, 50);
        Content.add(BtnEasy);

        JButton BtnMedium = new JButton("Medium");
        BtnMedium.setActionCommand("MEDIUM");
        BtnMedium.addActionListener(this);
        BtnMedium.setSize(100, 50);
        Content.add(BtnMedium);

        JButton BtnHard = new JButton("Hard");
        BtnHard.setActionCommand("HARD");
        BtnHard.addActionListener(this);
        BtnHard.setSize(100, 50);
        Content.add(BtnHard);

        JButton BtnNightmare = new JButton("Nightmare");
        BtnNightmare.setActionCommand("NIGHTMARE");
        BtnNightmare.addActionListener(this);
        BtnNightmare.setSize(100, 50);
        Content.add(BtnNightmare);

        JButton BtnClose = new JButton("Exit");
        BtnClose.setActionCommand("EXIT");
        BtnClose.addActionListener(this);
        BtnClose.setSize(100, 50);
        Content.add(BtnClose);

        return Content;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.setVisible(false);
        switch(e.getActionCommand()){
            case "EASY":
                System.out.println("EASY");
                ChosenDiff = new Difficulty(Difficulties.EASY);
                break;
            case "MEDIUM":
                System.out.println("MEDIUM");
                ChosenDiff = new Difficulty(Difficulties.MEDIUM);
                break;
            case "HARD":
                System.out.println("HARD");
                ChosenDiff = new Difficulty(Difficulties.HARD);
                break;
            case "NIGHTMARE":
                System.out.println("NIGHTMARE");
                ChosenDiff = new Difficulty(Difficulties.NIGHTMARE);
                break;
            case "EXIT":
                System.exit(0);
                break;
        }

    }
    
}
