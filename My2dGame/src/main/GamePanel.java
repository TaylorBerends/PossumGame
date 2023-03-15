package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{ //works as game screen

	//screen settings
	final int originalTileSize = 16; //16x16 tile, default size for each sprite
	final int scale = 3; //scales up pixels for modern screen resolutions
	
	public final int tileSize = originalTileSize * scale; //48x48 tile size
	public final int maxScreenCol = 16; //how many tiles vertically
	public final int maxScreenRow = 12; //how many tiles vertically
	final int screenWidth = tileSize * maxScreenCol; //width of screen in pixels: 768px
	final int screenHeight = tileSize * maxScreenRow; //height of screen in pixels: 576px
	
	//FPS
	int FPS = 60;
	
	TileManager tileM = new TileManager(this);
	KeyHandler keyH = new KeyHandler();
	Thread gameThread;
	Player player = new Player(this, keyH);
	
	//constructor
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight)); //set the size of this class(JPanel)
		this.setBackground(Color.black); //set background color of panel
		this.setDoubleBuffered(true); //all drawing from this component will be done in an offscreen painting buffer
		this.addKeyListener(keyH); //listens for key presses
		this.setFocusable(true); //game panel can be focused to recieve key input
	}

	public void startGameThread() { // function to start the 'game loop'
		
		gameThread = new Thread(this); //creates new thread
		gameThread.start(); //starts the game thread (which auto runs the run method)
	}
	
	
	//GAMELOOP
	@Override
	public void run() {
		
		double drawInterval = 1000000000/FPS; //1000000000 nanoseconds = 1 second; 0.01666 seconds
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		int drawCount = 0;
		
		while(gameThread != null) {
			
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawInterval;
			timer += (currentTime - lastTime);
			lastTime = currentTime;
			
			if(delta >= 1) {
				
				//Update: update information such as character positions
				update();
				//draw the screen with he update information
				repaint();
				delta--;
				drawCount++;
			}
			
			if(timer >= 1000000000) {
				//System.out.println("FPS:" + drawCount);
				drawCount = 0;
				timer = 0;
			}
		}
	}
	
	public void update() {
		player.update();

	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g; //change to 2D graphics
		
		tileM.draw(g2);
		player.draw(g2);
		
		g2.dispose();
	}
	
	
	
}
