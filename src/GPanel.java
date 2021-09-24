import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class GPanel extends JPanel implements ActionListener{

	static final int SCREEN_WIDTH = 800;
	static final int SCREEN_HEIGHT = 900;
	static final int UNIT_SIZE = 25;
	static final int GAME_UNITS = (SCREEN_WIDTH*(SCREEN_HEIGHT-100))/UNIT_SIZE;
	static final int DELAY = 75;
	final int x[] = new int[GAME_UNITS];
	final int y[] = new int[GAME_UNITS];
	int bodyParts = 6;
	int applesEaten;
	int appleX;
	int appleY;
	char direction = 'R';
	boolean running = false;
	Timer timer;
	Random random;

	
	GPanel(){
		random = new Random();
		this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
		this.setBackground(Color.black);
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
		startGame();
	}
	public void startGame() {
		newApple();
		running = true;
		timer = new Timer(DELAY,this);
		timer.start();
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);			
	}
	public void draw(Graphics g) {
		if(running) {
			g.setColor(GameIntro.greenC);
				g.fillRect(0, SCREEN_HEIGHT-100, SCREEN_WIDTH, 100);
			//End
			g.setColor(Color.red);
			g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);
			
			for(int i=0;i<bodyParts;i++) {
				if(i == 0) {
					g.setColor(Color.green);
					g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
				}
				else {	
					g.setColor(new Color(45,180,0));
//					g.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
					g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
				}
			}
			g.setColor(GameIntro.whiteC);
			g.setFont(new Font("Ink Free",Font.BOLD,75));
			FontMetrics fmetrics = getFontMetrics(g.getFont());
			g.drawString("SCORE: "+applesEaten,(SCREEN_WIDTH -fmetrics.stringWidth("SCORE: "+applesEaten))/2,SCREEN_HEIGHT*97/100);
		}
		else
			gameOver(g);
	}
	public void newApple() {
		appleX = random.nextInt((int)SCREEN_WIDTH/UNIT_SIZE)*UNIT_SIZE;
		appleY = random.nextInt((int)(SCREEN_HEIGHT-100)/UNIT_SIZE)*UNIT_SIZE;	
	}
	public void move() {
		for(int i=bodyParts;i>0;i--) {
			x[i]=x[i-1];
			y[i]=y[i-1];
		}
		switch(direction) {
		case 'U': 
			y[0] = y[0] - UNIT_SIZE;
			break;
		
		case 'D': 
			y[0] = y[0] + UNIT_SIZE;
			break;

		case 'R': 
			x[0] = x[0] + UNIT_SIZE;
			break;

		case 'L': 
			x[0] = x[0] - UNIT_SIZE;
			break;
		}
	}
	public void checkApple(){
		if((x[0] == appleX) && (y[0] == appleY)) {
			bodyParts++;
			applesEaten++;
			newApple();
		}
	}
	public void checkCollisions() {
		for(int i=bodyParts;i>0;i--) {
			if((x[0]==x[i])&&(y[0]==y[i])) {
				running = false;
			}
			if(x[0]<0)
				running = false;
			if(x[0]>SCREEN_WIDTH)
				running = false;
			if(y[0]<0)
				running = false;
			if(y[0]>(SCREEN_HEIGHT-125))
				running = false;
		}
		if(! running)
			timer.stop();
	}
	public void gameOver(Graphics g) {
		g.setColor(Color.red);
		g.setFont(new Font("Ink Free",Font.BOLD,100));
		FontMetrics fmetrics = getFontMetrics(g.getFont());
		g.drawString("Game Over",(SCREEN_WIDTH -fmetrics.stringWidth("Game Over"))/2,SCREEN_HEIGHT*1/5);
		
		g.setColor(Color.blue);
		FontMetrics fmetrics2 = getFontMetrics(g.getFont());
		g.drawString("SCORE: "+applesEaten,(SCREEN_WIDTH -fmetrics2.stringWidth("SCORE: "+applesEaten))/2,SCREEN_HEIGHT*2/5);
		
		//Score Save
		try {
			FileWriter SavedScore = new FileWriter("Snake_Scores.csv",true);
			SavedScore.write(applesEaten+",");
			SavedScore.write(GameIntro.user_name.getText()+System.lineSeparator());
			SavedScore.close();
			} catch (NumberFormatException e) {} 
		catch (IOException e) {}
			
		//NewGame btn
		JButton newGame = new JButton("NEW GAME");
		newGame.setFont(new Font("Ink Free",Font.BOLD,50));
		newGame.setForeground(Color.white);
		newGame.setBackground(null);
		newGame.setFocusable(false);
		newGame.setBounds(200,SCREEN_HEIGHT*4/5, 400, 100);
		newGame.addActionListener((event) -> {
			new GameIntro();
			this.setVisible(false);
			});
		
		//Score btn
		JButton scores = new JButton("SCORES");
		scores.setFont(new Font("Ink Free",Font.BOLD,50));
		scores.setForeground(Color.white);
		scores.setBackground(null);
		scores.setFocusable(false);
		scores.setBounds(200,SCREEN_HEIGHT*3/5, 400, 100);
		scores.addActionListener((event) -> {
			new ScoreF();
			this.setVisible(false);
			});
		this.add(scores);
		this.add(newGame);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(running) {
			move();
			checkApple();
			checkCollisions();
		}
		repaint();	
	}
	public class MyKeyAdapter extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				if(direction != 'R')
					direction = 'L';
				break;
			case KeyEvent.VK_RIGHT:
				if(direction != 'L')
					direction = 'R';
				break;
			case KeyEvent.VK_UP:
				if(direction != 'D')
					direction = 'U';
				break;
			case KeyEvent.VK_DOWN:
				if(direction != 'U')
					direction = 'D';
				break;
				}
			}
		}

}