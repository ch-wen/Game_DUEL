package dual;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Graphics2D;

public class Guide {
	Rectangle playButton = new Rectangle(GamePanel.GAME_WIDTH*5/6,GamePanel.GAME_HEIGHT*3/4,150,50);
	Rectangle quitButton = new Rectangle(GamePanel.GAME_WIDTH*5/6,GamePanel.GAME_HEIGHT*3/4+70,150,50);
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		Font font1 = new Font("Courier New",Font.PLAIN,40);
		g.setFont(font1);
		g.setColor(Color.CYAN);
		g.fillRect(GamePanel.GAME_WIDTH/3,GamePanel.GAME_HEIGHT/6,50,50);
		g.drawString("W: UP",GamePanel.GAME_WIDTH/3,GamePanel.GAME_HEIGHT/6+100);
		g.drawString("S: DOWN",GamePanel.GAME_WIDTH/3,GamePanel.GAME_HEIGHT/6+130);
		g.drawString("A: LEFT",GamePanel.GAME_WIDTH/3,GamePanel.GAME_HEIGHT/6+160);
		g.drawString("D: RIGHT",GamePanel.GAME_WIDTH/3,GamePanel.GAME_HEIGHT/6+190);
		g.drawString("F: SHOT",GamePanel.GAME_WIDTH/3,GamePanel.GAME_HEIGHT/6+220);
		g.setColor(Color.MAGENTA);
		g.fillRect(GamePanel.GAME_WIDTH*2/3-100,GamePanel.GAME_HEIGHT/6,50,50);
		g.drawString("¡ô :UP",GamePanel.GAME_WIDTH*2/3-100,GamePanel.GAME_HEIGHT/6+100);
		g.drawString("¡õ :DOWN",GamePanel.GAME_WIDTH*2/3-100,GamePanel.GAME_HEIGHT/6+130);
		g.drawString("¡ö :LEFT",GamePanel.GAME_WIDTH*2/3-100,GamePanel.GAME_HEIGHT/6+160);
		g.drawString("¡÷ :RIGHT",GamePanel.GAME_WIDTH*2/3-100,GamePanel.GAME_HEIGHT/6+190);
		g.drawString("1 :SHOT",GamePanel.GAME_WIDTH*2/3-100,GamePanel.GAME_HEIGHT/6+220);
		g2d.setColor(Color.WHITE);
		g2d.draw(playButton);
		g2d.draw(quitButton);
		Font font2 = new Font("Courier New",Font.BOLD,50);
		g.setFont(font2);
		g.setColor(Color.WHITE);
		g.drawString("PLAY",playButton.x+15,playButton.y+40);
		g.drawString("QUIT",quitButton.x+15,quitButton.y+40);
		Font font3 = new Font("Courier New",Font.PLAIN,20);
		g.setFont(font3);
		g.drawString("1. Number of bullets remaining will be displayed around the player.",GamePanel.GAME_WIDTH/3-150,GamePanel.GAME_HEIGHT/6+280);
		g.drawString("2. A bullet will be reloaded per second, the maximum number is six.",GamePanel.GAME_WIDTH/3-150,GamePanel.GAME_HEIGHT/6+310);
		g.drawString("3. Player loses after being shot six times.",GamePanel.GAME_WIDTH/3-150,GamePanel.GAME_HEIGHT/6+340);
		g.drawString("4. Only the front side of player can be shot.",GamePanel.GAME_WIDTH/3-150,GamePanel.GAME_HEIGHT/6+370);
		g.drawString("5. Can not cross the center and the border.",GamePanel.GAME_WIDTH/3-150,GamePanel.GAME_HEIGHT/6+400);
		

	}
}
