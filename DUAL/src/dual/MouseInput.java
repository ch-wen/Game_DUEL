package dual;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener{

	public void mouseClicked(MouseEvent e) {
		
	}
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		//menu
		if(GamePanel.state==GamePanel.STATE.MENU) {
			if(mx>=GamePanel.GAME_WIDTH/3+110&&mx<=GamePanel.GAME_WIDTH/3+260) {
				if(my>=GamePanel.GAME_HEIGHT/2&&my<=GamePanel.GAME_HEIGHT/2+50)//play
					GamePanel.state = GamePanel.STATE.GAME;
			}
			if(mx>=GamePanel.GAME_WIDTH/3+110&&mx<=GamePanel.GAME_WIDTH/3+260) {
				if(my>=GamePanel.GAME_HEIGHT/2+60&&my<=GamePanel.GAME_HEIGHT/2+110)//help
					GamePanel.state = GamePanel.STATE.HELP;
			}
			if(mx>=GamePanel.GAME_WIDTH/3+110&&mx<=GamePanel.GAME_WIDTH/3+260) {
				if(my>=GamePanel.GAME_HEIGHT/2+120&&my<=GamePanel.GAME_HEIGHT/2+170)//quit
					System.exit(1);
			}
		}
		//guide
		if(GamePanel.state==GamePanel.STATE.HELP) {
			if(mx>=GamePanel.GAME_WIDTH*5/6&&mx<=GamePanel.GAME_WIDTH*5/6+150) {
				if(my>=GamePanel.GAME_HEIGHT*3/4&&my<=GamePanel.GAME_HEIGHT*3/4+50)//play
					GamePanel.state = GamePanel.STATE.GAME;
			}
			if(mx>=GamePanel.GAME_WIDTH*5/6&&mx<=GamePanel.GAME_WIDTH*5/6+150) {
				if(my>=GamePanel.GAME_HEIGHT*3/4+70&&my<=GamePanel.GAME_HEIGHT*3/4+120)//quit
					System.exit(1);
			}
		}
		/*
	Rectangle playButton = new Rectangle(GamePanel.GAME_WIDTH*5/6,GamePanel.GAME_HEIGHT*3/4,150,50);
	Rectangle quitButton = new Rectangle(GamePanel.GAME_WIDTH*5/6,GamePanel.GAME_HEIGHT*3/4+70,150,50);
        */	
		}

	public void mouseReleased(MouseEvent e) {
		
	}

	public void mouseEntered(MouseEvent e) {
		
	}
	public void mouseExited(MouseEvent e) {
		
	}

}
