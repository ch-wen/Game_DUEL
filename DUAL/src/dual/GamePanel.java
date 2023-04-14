package dual;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{
	static final int GAME_WIDTH = 1200;
	static final int GAME_HEIGHT = 600;
	static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH,GAME_HEIGHT);
	static final int BULLET_DIAMETER = 10;
	static final int PLAYER_WIDTH = 50;
	static final int BULLET_SPEED = 10;
	public static enum STATE{
		GAME,
		MENU,
		HELP
	};
	public static STATE state = STATE.MENU;
	private Menu menu;
	private Guide guide;
	int bullet1X[] = new int[6];
	int bullet1Y[] = new int[6];
	int now1X[] = new int[6];
	boolean isShot1[] = new boolean[6];
	int bullet1No = 0;
	int bullet1Contain = 6;
	int bullet2X[] = new int[6];
	int bullet2Y[] = new int[6];
	int now2X[] = new int[6];
	boolean isShot2[] = new boolean[6];
	int bullet2No = 0;
	int bullet2Contain = 6;
	boolean play;
	boolean shot = true;
	
	Rectangle bullet1Hit;
	Rectangle bullet2Hit;
	Rectangle bullet1;
	Rectangle bullet2;
	Rectangle bullet3;
	Rectangle bullet4;
	Rectangle bullet5;
	Rectangle bullet6;
	Thread gameThread;
	Image image;
	Graphics graphics;
	Player player1;
	Player player2;
	Score score;
	
	Timer timer = new Timer();
    TimerTask  task = new TimerTask (){
        public void run() {
        	if(bullet1Contain<=5)
        		bullet1Contain++;
        	if(bullet2Contain<=5)
        		bullet2Contain++;
        }
    };
	
	GamePanel(){
		play = true;
		newPlayer();
		newBullet();
		score = new Score(GAME_WIDTH,GAME_HEIGHT);
		menu = new Menu();
		guide = new Guide();
		timer.schedule(task,1000L,1000L);
		this.setFocusable(true);
		this.addKeyListener(new AL());
		this.addMouseListener(new MouseInput());
		this.setPreferredSize(SCREEN_SIZE);
		gameThread = new Thread(this);
		gameThread.start();
	}
	public void newBullet() {
		for(int i=0;i<bullet1X.length;i++) {
			isShot1[i] = false;
			bullet1X[i] = player1.x;
			bullet1Y[i] = player1.y;
		}
		for(int i=0;i<bullet2X.length;i++) {
			isShot2[i] = false;
			bullet2X[i] = player2.x;
			bullet2Y[i] = player2.y;
		}
	}
	public void newPlayer() {
		player1 = new Player(0,(GAME_HEIGHT/2)-(PLAYER_WIDTH/2),PLAYER_WIDTH,PLAYER_WIDTH,1);
		player2 = new Player(GAME_WIDTH-PLAYER_WIDTH,(GAME_HEIGHT/2)-(PLAYER_WIDTH/2),PLAYER_WIDTH,PLAYER_WIDTH,2);
	}
	public void paint(Graphics g) {
		image = createImage(getWidth(),getHeight());
		graphics = image.getGraphics();
		draw(graphics); //draw component
		g.drawImage(image,0,0,this);
	}
	public void draw(Graphics g) {
		if(state==STATE.GAME) {
			if(play) {
				player1.draw(score.player1,g);
				player2.draw(score.player2,g);
				drawBullet(1,g);
				drawBullet(2,g);
				drawBulletAround(1,bullet1Contain,player1,g);
				drawBulletAround(2,bullet2Contain,player2,g);
				/*drawContain(1,g);
				drawContain(2,g);*/
				score.draw(g);
			}
			else {// win, end and restart
				if(score.player1<=0) {
					g.setColor(Color.MAGENTA);
					g.fillRect(GAME_WIDTH/3-PLAYER_WIDTH+40,GAME_HEIGHT/2-PLAYER_WIDTH-20,100,100);
				}
				else {
					g.setColor(Color.CYAN);
					g.fillRect(GAME_WIDTH/3-PLAYER_WIDTH+45,GAME_HEIGHT/2-PLAYER_WIDTH-20,100,100);
				}
				// show winner, restart hint
				g.setColor(Color.WHITE);
				g.setFont(new Font("Consolas",Font.PLAIN,60));
				g.drawString("IS WINNER",GAME_WIDTH/2-45,GAME_HEIGHT/2);
				g.setFont(new Font("Consolas",Font.PLAIN,30));
				g.drawString("PRESS ENTER TO RESTART",GAME_WIDTH/2-170,GAME_HEIGHT/2+100);
			}
		}
		else if(state==STATE.MENU) {
			menu.draw(g);
		}
		else if(state==STATE.HELP) {
			guide.draw(g);
		}
	}
	public void move() {
		player1.move();
		player2.move();
		bulletMove(1);
		bulletMove(2);
	}
	public void checkCollision() {
		//bullet player
		checkBulletCollosion(1);
		checkBulletCollosion(2);
		//stops paddle at window edges
		if(player1.y<=0)
			player1.y = 0;
		if(player1.y>=(GAME_HEIGHT-PLAYER_WIDTH))
			player1.y = GAME_HEIGHT-PLAYER_WIDTH;
		if(player2.y<=0)
			player2.y = 0;
		if(player2.y>= (GAME_HEIGHT-PLAYER_WIDTH))
			player2.y = GAME_HEIGHT-PLAYER_WIDTH;
		if(player1.x<=0)
			player1.x = 0;
		if(player1.x>=((GAME_WIDTH/2)-PLAYER_WIDTH))
			player1.x = (GAME_WIDTH/2)-PLAYER_WIDTH;
		if(player2.x<=(GAME_WIDTH/2))
			player2.x = GAME_WIDTH/2;
		if(player2.x>= (GAME_WIDTH-PLAYER_WIDTH))
			player2.x = GAME_WIDTH-PLAYER_WIDTH;
	}
	public void run() { //like main
		//game loop
		long lastTime = System.nanoTime();
		double amoutOfTicks = 60.0; //60fps
		double ns = 1000000000 / amoutOfTicks;
		double delta = 0;
		while(true) {
			long now = System.nanoTime();
			delta += (now-lastTime)/ns;
			lastTime = now;
			if(delta>=1) {
				move(); //smooth
				checkCollision();
				repaint();
				delta--;
			}
		}
	}
	public void bulletMove(int id) {
		switch(id) {
		case 1:
			for(int i=0;i<bullet1X.length;i++) {
				if(isShot1[i]) 
					bullet1X[i]+=BULLET_SPEED;
				if(bullet1X[i]-now1X[i]>=GAME_WIDTH*2/3) {
					isShot1[i] = false;
					bullet1X[i] = -100;
					//++bullet1Contain;
				}
			}
			break;
		case 2:
			for(int i=0;i<bullet2X.length;i++) {
				if(isShot2[i]) 
					bullet2X[i]-=BULLET_SPEED;
				if(now2X[i]-bullet2X[i]>=GAME_WIDTH*2/3) {
					isShot2[i] = false;
					now2X[i] = -10;
					bullet2X[i] = -100;
					//++bullet2Contain;
				} 
			}
			break;
		}
	}
	public void drawBullet(int id,Graphics g) {
		switch(id) {
		case 1:
			g.setColor(Color.CYAN);
			for(int i=0;i<bullet1X.length;i++) {
				if(isShot1[i])
					g.fillOval(bullet1X[i],bullet1Y[i]+(PLAYER_WIDTH/2)-BULLET_DIAMETER/2,BULLET_DIAMETER,BULLET_DIAMETER);
			}
			break;
		case 2:
			g.setColor(Color.MAGENTA);
			for(int i=0;i<bullet2X.length;i++) {
				if(isShot2[i])
					g.fillOval(bullet2X[i],bullet2Y[i]+(PLAYER_WIDTH/2)-BULLET_DIAMETER/2,BULLET_DIAMETER,BULLET_DIAMETER);
			}
			break;
		}
	}
	public void drawContain(int id,Graphics g) {
		switch(id) {
		case 1:
			g.setColor(Color.BLACK);
			g.setFont(new Font("Consolas",Font.PLAIN,50));
			g.drawString(String.valueOf(bullet1Contain),player1.x+PLAYER_WIDTH/2-13,player1.y+PLAYER_WIDTH-10);
			break;
		case 2:
			g.setColor(Color.BLACK);
			g.setFont(new Font("Consolas",Font.PLAIN,50));
			g.drawString(String.valueOf(bullet2Contain),player2.x+PLAYER_WIDTH/2-13,player2.y+PLAYER_WIDTH-10);
			break;
		}
	}
	public void drawBulletAround(int id,int bulletContain,Rectangle player,Graphics g) {
		switch(id) {
		case 1:
			g.setColor(Color.CYAN);
			bullet1 = new Rectangle(player.x+player.width-BULLET_DIAMETER,player.y-BULLET_DIAMETER-10,BULLET_DIAMETER,BULLET_DIAMETER);
			bullet2 = new Rectangle(player.x+player.width/2-BULLET_DIAMETER/2,player.y-BULLET_DIAMETER-10,BULLET_DIAMETER,BULLET_DIAMETER);
			bullet3 = new Rectangle(player.x,player.y-BULLET_DIAMETER-10,BULLET_DIAMETER,BULLET_DIAMETER);
			bullet4 = new Rectangle(player.x+player.width-BULLET_DIAMETER,player.y+player.height+10,BULLET_DIAMETER,BULLET_DIAMETER);
			bullet5 = new Rectangle(player.x+player.width/2-BULLET_DIAMETER/2,player.y+player.height+10,BULLET_DIAMETER,BULLET_DIAMETER);
			bullet6 = new Rectangle(player.x,player.y+player.height+10,BULLET_DIAMETER,BULLET_DIAMETER);
			break;
		case 2:
			g.setColor(Color.MAGENTA);
			bullet1 = new Rectangle(player.x,player.y-BULLET_DIAMETER-10,BULLET_DIAMETER,BULLET_DIAMETER);
			bullet2 = new Rectangle(player.x+player.width/2-BULLET_DIAMETER/2,player.y-BULLET_DIAMETER-10,BULLET_DIAMETER,BULLET_DIAMETER);
			bullet3 = new Rectangle(player.x+player.width-BULLET_DIAMETER,player.y-BULLET_DIAMETER-10,BULLET_DIAMETER,BULLET_DIAMETER);
			bullet4 = new Rectangle(player.x,player.y+player.height+10,BULLET_DIAMETER,BULLET_DIAMETER);
			bullet5 = new Rectangle(player.x+player.width/2-BULLET_DIAMETER/2,player.y+player.height+10,BULLET_DIAMETER,BULLET_DIAMETER);
			bullet6 = new Rectangle(player.x+player.width-BULLET_DIAMETER,player.y+player.height+10,BULLET_DIAMETER,BULLET_DIAMETER);
			break;
		}
			if(bulletContain==0) {
				return;
			}
			if(bulletContain==1) {
				g.fillOval(bullet6.x,bullet6.y,bullet6.width,bullet6.height);
			}
			if(bulletContain==2) {
				g.fillOval(bullet5.x,bullet5.y,bullet5.width,bullet5.height);
				g.fillOval(bullet6.x,bullet6.y,bullet6.width,bullet6.height);
			}
			if(bulletContain==3) {
				g.fillOval(bullet4.x,bullet4.y,bullet4.width,bullet4.height);
				g.fillOval(bullet5.x,bullet5.y,bullet5.width,bullet5.height);
				g.fillOval(bullet6.x,bullet6.y,bullet6.width,bullet6.height);
			}
			if(bulletContain==4) {
				g.fillOval(bullet3.x,bullet3.y,bullet3.width,bullet3.height);
				g.fillOval(bullet4.x,bullet4.y,bullet4.width,bullet4.height);
				g.fillOval(bullet5.x,bullet5.y,bullet5.width,bullet5.height);
				g.fillOval(bullet6.x,bullet6.y,bullet6.width,bullet6.height);
			}
			if(bulletContain==5) {
				g.fillOval(bullet2.x,bullet2.y,bullet2.width,bullet2.height);
				g.fillOval(bullet3.x,bullet3.y,bullet3.width,bullet3.height);
				g.fillOval(bullet4.x,bullet4.y,bullet4.width,bullet4.height);
				g.fillOval(bullet5.x,bullet5.y,bullet5.width,bullet5.height);
				g.fillOval(bullet6.x,bullet6.y,bullet6.width,bullet6.height);
			}
			if(bulletContain==6) {
				g.fillOval(bullet1.x,bullet1.y,bullet1.width,bullet1.height);
				g.fillOval(bullet2.x,bullet2.y,bullet2.width,bullet2.height);
				g.fillOval(bullet3.x,bullet3.y,bullet3.width,bullet3.height);
				g.fillOval(bullet4.x,bullet4.y,bullet4.width,bullet4.height);
				g.fillOval(bullet5.x,bullet5.y,bullet5.width,bullet5.height);
				g.fillOval(bullet6.x,bullet6.y,bullet6.width,bullet6.height);
			}	
	}
	public void checkBulletCollosion(int id) {
		switch(id) {
		case 1:
			for(int i=0;i<bullet1X.length;i++) {
				bullet1Hit = new Rectangle(bullet1X[i],bullet1Y[i]+(PLAYER_WIDTH/2),BULLET_DIAMETER,BULLET_DIAMETER);
				if(bullet1Hit.intersects(new Rectangle(player2.x,player2.y,1,PLAYER_WIDTH))&&play) {
					score.player2--;
					if(score.player2==0&&score.player1!=0)
						play = false;
				}
			}
			break;
		case 2:
			for(int i=0;i<bullet1X.length;i++) {
				bullet2Hit  = new Rectangle(bullet2X[i],bullet2Y[i]+(PLAYER_WIDTH/2),BULLET_DIAMETER,BULLET_DIAMETER);
				if(bullet2Hit .intersects(new Rectangle(player1.x,player1.y,1,PLAYER_WIDTH))&&play) {
					score.player1--;
					if(score.player1==0&&score.player2!=0) 
						play = false;
				}
			}
			break;
		}
	}
	public void fire(int id) {//keep 6 bullets
		switch(id) {
		case 1:
			isShot1[bullet1No] = true;
			bullet1X[bullet1No] = player1.x;
			bullet1Y[bullet1No] = player1.y;
			now1X[bullet1No] = player1.x;
			++bullet1No;
			--bullet1Contain;
			if(bullet1No>bullet1X.length-1)
				bullet1No = 0;
			if(bullet1Contain<0)
				bullet1Contain = 0;
			break;
		case 2:
			isShot2[bullet2No] = true;
			bullet2X[bullet2No] = player2.x;
			bullet2Y[bullet2No] = player2.y;
			now2X[bullet2No] = player2.x;
			++bullet2No;
			--bullet2Contain;
			if(bullet2No>bullet2X.length-1)
				bullet2No = 0;
			if(bullet2Contain<0)
				bullet2Contain = 0;
			break;
		}	
	}
	public class AL extends KeyAdapter{
		public void keyPressed(KeyEvent e) {
			if(state==STATE.GAME) {
				player1.keyPressed(e);
				player2.keyPressed(e);
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					if(!play) {
						//reset play
						play = true;
						//reset player position
						player1.x = 0;
						player1.y = (GAME_HEIGHT/2)-(PLAYER_WIDTH/2);
						player2.x = GAME_WIDTH-PLAYER_WIDTH;
						player2.y = (GAME_HEIGHT/2)-(PLAYER_WIDTH/2);
						//reset bullet
						for(int i=0;i<bullet1X.length;i++) {
							isShot1[i] = false;
							bullet1X[i] = player1.x;
							bullet1Y[i] = player1.y;
							isShot1[i] = false;
						}
						for(int i=0;i<bullet2X.length;i++) {
							isShot2[i] = false;
							bullet2X[i] = player2.x;
							bullet2Y[i] = player2.y;
							isShot2[i] = false;
						}
						bullet1No = 0;
						bullet1Contain = 6;
						bullet2No = 0;
						bullet2Contain = 6;
						score.player1 = 6;
						score.player2 = 6;
					}
				}
			}
		}
		public void keyReleased(KeyEvent e) {
			player1.keyReleased(e);
			player2.keyReleased(e);
			if(e.getKeyCode()==KeyEvent.VK_F&&isShot1[bullet1No]!=true&&bullet1Contain!=0) {
				fire(1);
			}
			if(e.getKeyCode()==KeyEvent.VK_NUMPAD1&&isShot2[bullet2No]!=true&&bullet2Contain!=0) {
				fire(2);
			}
		}
	}
}

