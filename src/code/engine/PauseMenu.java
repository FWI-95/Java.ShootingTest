package code.engine;

import java.awt.Container;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JLabel;

import code.level.Difficulties;
import code.level.Difficulty;
import code.level.Difficulty_Easy;
import code.level.Difficulty_Hard;
import code.level.Difficulty_Medium;

public class PauseMenu implements ActionListener, KeyListener {
	
	Frame frame;
	Main main;
	
	Difficulty difficulty;
	
	JButton DifficultyEasy = new JButton("Easy");
	JButton DifficultyMedium = new JButton("Medium");
	JButton DifficultyHard = new JButton("Hard");
	
	JButton Continue = new JButton("Continue");
	JButton Exit = new JButton("Exit");
	
	JLabel Dead = new JLabel("Dead - Game Over");
	
	final int Mode_Playing = 0;
	final int Mode_Start = 1;
	final int Mode_Pause = 2;
	final int Mode_Dead = 3;
	
	public PauseMenu(Frame f) {
		frame = f;
		
		DifficultyEasy.addActionListener(this);
		DifficultyMedium.addActionListener(this);
		DifficultyHard.addActionListener(this);
		
		Continue.addActionListener(this);
		Exit.addActionListener(this);
		
//		LoadGFX();
		
		DifficultyEasy.setBounds(100,100,150,75);
		DifficultyMedium.setBounds(100,200,150,75);
		DifficultyHard.setBounds(100,300,150,75);
		
		Continue.setBounds(100,700,150,75);
		Exit.setBounds(100,900,150,75);
		
		Dead.setBounds(100,100,150,75);
	}
		
	public Container Show(int mode, Container panel) {
		panel.setLayout(null);
		
		switch(mode) {
		case Mode_Start:
			return drawStart(panel);
		case Mode_Pause:
			return drawPause(panel);
		case Mode_Dead:
			return drawDead(panel);
		default:
			return panel;
		}
	}

	private Container drawStart(Container panel) {
		panel.add(DifficultyEasy);
		panel.add(DifficultyMedium);
		panel.add(DifficultyHard);
		
		panel.add(Exit);
		
		return panel;
	}
	
	private Container drawPause(Container panel) {
		panel.add(Continue);
		panel.add(Exit);
		panel.addKeyListener(this);
		return panel;
	}
	
	private Container drawDead(Container panel) {
		panel.add(Dead);
		panel.add(Exit);
		
		return panel;
	}
	
	private void LoadGFX() {
		try {
			Font font = Font.createFont(Font.TRUETYPE_FONT, new File("gfx.menu\\Gold Box 8x8.ttf"));
			
			GraphicsEnvironment ge =
					GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(font);
			
			DifficultyEasy.setFont(font);
			DifficultyMedium.setFont(font);
			DifficultyHard.setFont(font);
			
			Continue.setFont(font);
			Exit.setFont(font);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == Exit) {
			frame.ExitGame();
		}else {
			if(ae.getSource() == DifficultyEasy) {
				frame.setDifficulty(Difficulties.EASY);
				frame.changeState(Modes.ALIVE);
			}
			if(ae.getSource() == DifficultyMedium) {
				frame.setDifficulty(Difficulties.MEDIUM);
				frame.changeState(Modes.ALIVE);
			}
			if(ae.getSource() == DifficultyHard) {
				frame.setDifficulty(Difficulties.HARD);
				frame.changeState(Modes.ALIVE);
			}
			
			if(ae.getSource() == Continue) frame.changeState(Modes.ALIVE);
			if(ae.getSource() == Exit) frame.ExitGame();
		}
			
	}
	
	public Difficulty getDifficulty() {
		return difficulty;
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) frame.changeState(Modes.ALIVE);
	}
}
