import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.*;

public class BallFrame extends JFrame{
	public static final int WIDTH = 494;
	public static final int HEIGHT = 600;
	BallPanel ballpanel;
	KeyListener keyAdapter;
	Timer timer;
	BallService service = new BallService(this);
	
	public BallFrame() throws IOException{
		init();
		this.setTitle("µ¯Çò");
		this.setResizable(false);
		this.setLocation(500, 200);
		ballpanel = getBallPanel();
		this.add(ballpanel);
		this.addKeyListener(keyAdapter);
	}
	
	public void init(){
		KeyListener[] karr = this.getKeyListeners();
		
		if(karr.length==0){
			keyAdapter = new KeyAdapter(){
				public void keyPressed(KeyEvent e){
					try {
						service.keyPressed(e);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				
				public void keyReleased(KeyEvent e){
					service.keyReleased(e);
				}
			};
		}
	}

	//µ¥ÀýÄ£Ê½
	private BallPanel getBallPanel() {
		if(ballpanel==null){
			ballpanel = new BallPanel();
			ballpanel.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		}
		return ballpanel;
	}
	
	class BallPanel extends JPanel{
		public BallPanel(){
			this.setBackground(Color.BLACK);
		}
		
		public void paint(Graphics g){
			super.paint(g);
			try {
				service.draw(g);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
}
