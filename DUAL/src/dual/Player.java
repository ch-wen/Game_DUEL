package dual;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Player extends Rectangle{
	int id; //1,2
	int yVelocity;
	int xVelocity;
	int speed = 5;
	int bulletX[] = new int [5];
	int bulletY[] = new int [5];
	int bulletNo;
	boolean isShot[] = new boolean[5];
	static int BULLET_DIAMETER;
	int PLAYER_WIDTH;
	static int GAME_WIDTH;
	Player(int x,int y,int PLAYER_WIDTH,int PLAYER_HEIGHT,int id){
		super(x,y,PLAYER_WIDTH,PLAYER_HEIGHT); //Rectangle
		this.id = id;
		this.bulletNo = 0;
		this.PLAYER_WIDTH = PLAYER_WIDTH;
	}
	public void keyPressed(KeyEvent e) {
		switch(id) {
		case 1:
			if(e.getKeyCode()==KeyEvent.VK_W) {
				setYDirection(-speed);
				move();
			}
			if(e.getKeyCode()==KeyEvent.VK_S) {
				setYDirection(speed);
				move();
			}
			if(e.getKeyCode()==KeyEvent.VK_A) {
				setXDirection(-speed);
				move();
			}
			if(e.getKeyCode()==KeyEvent.VK_D) {
				setXDirection(speed);
				move();
			}
			break;
		case 2:
			if(e.getKeyCode()==KeyEvent.VK_UP) {
				setYDirection(-speed);
				move();
			}
			if(e.getKeyCode()==KeyEvent.VK_DOWN) {
				setYDirection(speed);
				move();
			}
			if(e.getKeyCode()==KeyEvent.VK_LEFT) {
				setXDirection(-speed);
				move();
			}
			if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
				setXDirection(speed);
				move();
			}
			break;
		}
	}
	public void keyReleased(KeyEvent e) {
		switch(id) {
		case 1:
			if(e.getKeyCode()==KeyEvent.VK_W) {
				setYDirection(0);
				move();
			}
			if(e.getKeyCode()==KeyEvent.VK_S) {
				setYDirection(0);
				move();
			}
			if(e.getKeyCode()==KeyEvent.VK_A) {
				setXDirection(0);
				move();
			}
			if(e.getKeyCode()==KeyEvent.VK_D) {
				setXDirection(0);
				move();
			}
			break;
		case 2:
			if(e.getKeyCode()==KeyEvent.VK_UP) {
				setYDirection(0);
				move();
			}
			if(e.getKeyCode()==KeyEvent.VK_DOWN) {
				setYDirection(0);
				move();
			}
			if(e.getKeyCode()==KeyEvent.VK_LEFT) {
				setXDirection(0);
				move();
			}
			if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
				setXDirection(0);
				move();
			}
			break;
		}
	}
	public void setXDirection(int xDirection) {
		xVelocity = xDirection;
	}
	public void setYDirection(int yDirection) {
		yVelocity = yDirection;
	}
	public void move() {
		x = x+xVelocity;
		y = y+yVelocity;
	}
	public void draw(int score,Graphics g) {
		if(id==1)
			g.setColor(Color.CYAN);
		else 
			g.setColor(Color.MAGENTA);
		if(score==6)
			g.fillRect(x,y,width,height);//super
		if(score==5)
			g.fillRect(x,y+PLAYER_WIDTH/6,width,height-PLAYER_WIDTH/6);
		if(score==4)
			g.fillRect(x,y+2*PLAYER_WIDTH/6,width,height-2*PLAYER_WIDTH/6);
		if(score==3)
			g.fillRect(x,y+3*PLAYER_WIDTH/6,width,height-3*PLAYER_WIDTH/6);
		if(score==2)
			g.fillRect(x,y+4*PLAYER_WIDTH/6,width,height-4*PLAYER_WIDTH/6);
		if(score==1)
			g.fillRect(x,y+5*PLAYER_WIDTH/6,width,height-5*PLAYER_WIDTH/6);
		g.drawRect(x,y,width,height);
	}
}
