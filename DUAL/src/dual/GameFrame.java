package dual;

import java.awt.Color;

import javax.swing.JFrame;

public class GameFrame extends JFrame{
	GamePanel panel;
	GameFrame(){
		panel = new GamePanel();
		this.add(panel); //frame <- panel
		this.setTitle("DUEL!");
		this.setResizable(false);
		this.setBackground(Color.BLACK);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null); //show in middle
	}
}
