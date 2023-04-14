package dual;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Menu{
	public Rectangle playButton = new Rectangle(GamePanel.GAME_WIDTH/2-75,GamePanel.GAME_HEIGHT/2,150,50);
	public Rectangle helpButton = new Rectangle(GamePanel.GAME_WIDTH/2-75,GamePanel.GAME_HEIGHT/2+70,150,50);
	public Rectangle quitButton = new Rectangle(GamePanel.GAME_WIDTH/2-75,GamePanel.GAME_HEIGHT/2+140,150,50);
	public Rectangle leftBackground = new Rectangle(GamePanel.GAME_WIDTH/2-172,GamePanel.GAME_HEIGHT/2-195,172,150);
	public Rectangle rightBackground = new Rectangle(GamePanel.GAME_WIDTH/2,GamePanel.GAME_HEIGHT/2-195,165,150);
	public Rectangle left1 = new Rectangle(leftBackground.x-36,leftBackground.y,26,26);
	public Rectangle left2 = new Rectangle(leftBackground.x-36,leftBackground.y+leftBackground.height/2-13,26,26);
	public Rectangle left3 = new Rectangle(leftBackground.x-36,leftBackground.y+leftBackground.height-26,26,26);
	
	public Rectangle right1 = new Rectangle(rightBackground.x+rightBackground.width+10,rightBackground.y,26,26);
	public Rectangle right2 = new Rectangle(rightBackground.x+rightBackground.width+10,rightBackground.y+rightBackground.height/2-13,26,26);
	public Rectangle right3 = new Rectangle(rightBackground.x+rightBackground.width+10,rightBackground.y+rightBackground.height-26,26,26);
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.CYAN);
		g2d.fill(leftBackground);
		g2d.fillOval(left1.x,left1.y,26,26);
		g2d.fillOval(left2.x,left2.y,26,26);
		g2d.fillOval(left3.x,left3.y,26,26);
		g2d.setColor(Color.MAGENTA);
		g2d.fill(rightBackground);
		g2d.fillOval(right1.x,right1.y,26,26);
		g2d.fillOval(right2.x,right2.y,26,26);
		g2d.fillOval(right3.x,right3.y,26,26);
		Font font1 = new Font("Arial",Font.BOLD,120);
		g.setFont(font1);
		g.setColor(Color.WHITE);
		g.drawString("DUEL",GamePanel.GAME_WIDTH/2-172,GamePanel.GAME_HEIGHT/2-75);
		g2d.draw(playButton);
		g2d.draw(helpButton);
		g2d.draw(quitButton);
		Font font2 = new Font("Courier New",Font.BOLD,50);
		g.setFont(font2);
		g.drawString("PLAY",playButton.x+15,playButton.y+40);
		g.drawString("HELP",helpButton.x+15,helpButton.y+40);
		g.drawString("QUIT",quitButton.x+15,quitButton.y+40);
		Font font3 = new Font("Courier New",Font.BOLD,20);
		g.setFont(font3);
		g.drawString("Author: WEN CHIEH-HSUN",quitButton.x+400,quitButton.y+150);
		g.drawString("Refer2: DUAL!",quitButton.x+400,quitButton.y+130);
	}
}
