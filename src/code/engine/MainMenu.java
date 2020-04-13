package code.engine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import code.level.Difficulty;
import code.level.Difficulty_Easy;
import code.level.Difficulty_Hard;
import code.level.Difficulty_Medium;

public class MainMenu extends JFrame implements ActionListener{
	
	Frame Frame;
	Difficulty Difficulty;
	
	JButton DifficultyEasy = new JButton("Easy");
	JButton DifficultyMedium = new JButton("Medium");
	JButton DifficultyHard = new JButton("Hard");
	
	JButton Exit = new JButton("Exit");
	
	public MainMenu(int width, int height) {
		DifficultyEasy.addActionListener(this);
		DifficultyMedium.addActionListener(this);
		DifficultyHard.addActionListener(this);
		
		Exit.addActionListener(this);
		
	}
	
	public Difficulty Show() {
		while(Difficulty == null) {
			try {
				Thread.sleep(100);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return Difficulty;
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == Exit) {
			Frame.ExitGame();
		}else {
			if(ae.getSource() == DifficultyEasy)Difficulty = new Difficulty_Easy();
			if(ae.getSource() == DifficultyMedium)Difficulty = new Difficulty_Medium();
			if(ae.getSource() == DifficultyHard)Difficulty = new Difficulty_Hard();
		}
			
	}

}
