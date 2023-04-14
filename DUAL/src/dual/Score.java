package dual;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Score {
	static int GAME_WIDTH;
	static int GAME_HEIGHT;
	int player1 = 6;
	int player2 = 6;
	Score(int GAME_WIDTH,int GAME_HEIGHT){
		Score.GAME_WIDTH = GAME_WIDTH;
		Score.GAME_HEIGHT = GAME_HEIGHT;
	}
	public void draw(Graphics g) {
		g.setColor(Color.WHITE);
		g.setFont(new Font("Consolas",Font.PLAIN,60));
		g.drawLine(GAME_WIDTH/2,0,GAME_WIDTH/2,GAME_HEIGHT);
		/*g.drawString(String.valueOf(player1),GAME_WIDTH/4,50);
		g.drawString(String.valueOf(player2),GAME_WIDTH*3/4,50);*/
	}
}
