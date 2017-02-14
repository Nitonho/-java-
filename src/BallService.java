import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class BallService {
	private final int stickSpeed = 10;
	private boolean isWon;
	private boolean isOver;
	private Stick stick;
	private Ball ball;
	private Brick[][] bricks;
	private ArrayList<Magic> magics;
	BallFrame frame;
	Timer timer;
	
	public BallService(BallFrame frame) throws IOException{
		this.frame = frame;
		init();
	}
	
	public void init() throws IOException{
		this.isWon = false;
		this.isOver = false;
		magics = new ArrayList<Magic>();
		stick = new Stick(BallFrame.WIDTH,BallFrame.HEIGHT,"img/normalStick.jpg");
		ball = new Ball(this,stick,"img/ball.gif");
		bricks = this.createBrickArr(17, 8);
	}

	public void run() throws IOException {
		ball.move();
		
		for(int i=bricks.length-1;i>=0;i--){
			for(int j=0;j<bricks[0].length;j++){
				if(bricks[i][j].isExist() && isHitBrick(bricks[i][j])){//砖块存在的条件下才判断小球是否与其相撞
					bricks[i][j].setExist(false);
					
					Magic magic = bricks[i][j].getMagic();
					if(magic!=null){//该砖块有道具
						magic.setVisable(true);
						magics.add(magic);
					}
				}
			}
		}
		
		for(int i=0;i<magics.size();i++){
			magics.get(i).move();
			if(isHitStick(magics.get(i))){
				magics.get(i).magicDo(stick);
			}
		}
		
		if(isHitStick(ball)){
			ball.setSpeedY(-1*ball.getSpeedY());
		}
	}
	
	public void restart() throws IOException{
		timer.stop();
		init();
	}

	public boolean isOver() {
		return isOver;
	}

	public void setOver(boolean isOver) {
		this.isOver = isOver;
	}

	public void setStickPos(KeyEvent e) {
		int value = e.getKeyCode();
		if(value==KeyEvent.VK_LEFT){
			if(stick.getX()>0){
				if(stick.getX()-this.stickSpeed<0){
					stick.setX(0);
				}
				else 
					stick.setX(stick.getX()-stickSpeed);
			}
			if(!ball.isStarted()){//游戏未开始球跟着挡板动
				int x = stick.getX() + stick.getImage().getWidth(null) / 2 - ball.getImage().getWidth(null) / 2;
				ball.setX(x); 
			}
		}
		else if(value==KeyEvent.VK_RIGHT){
			if(stick.getX()<BallFrame.WIDTH-stick.getImage().getWidth(null)){
				if(stick.getX()+stick.getImage().getWidth(null)+this.stickSpeed>BallFrame.WIDTH){
					stick.setX(stick.getX()+(BallFrame.WIDTH-stick.getX()-stick.getImage().getWidth(null)));
				}
				else 
					stick.setX(stick.getX()+stickSpeed);
			}
				
			if(!ball.isStarted()){//游戏未开始球跟着挡板动
				int x = stick.getX() + stick.getImage().getWidth(null) / 2 - ball.getImage().getWidth(null) / 2;
				ball.setX(x); 
			}
		}
		
	}
	
	public boolean isHitStick(BallComponent bc){//判断小球或者道具是否撞到挡板
		if(bc.getX()+bc.getImage().getWidth(null) > stick.getX() &&
			bc.getX() < stick.getX()+stick.getImage().getWidth(null) &&
				bc.getY()+bc.getImage().getHeight(null) >= stick.getY()){
			return true;
		}
		return false;
	}
	
	public boolean isHitBrick(Brick brick){//判断小球是否撞到砖块
		//小球的中心
		int centerX = ball.getX() + ball.getImage().getWidth(null)/2;
		int centerY = ball.getY() + ball.getImage().getWidth(null)/2;
		
		if(centerX>brick.getX()&&centerX<brick.getX()+brick.getImage().getWidth(null)){
			if(ball.getY()<brick.getY()+brick.getImage().getWidth(null) && ball.getY()+ball.getImage().getWidth(null)>brick.getY()){
				ball.setSpeedY(-1*ball.getSpeedY());
				return true;
			}
		}
		
		if(centerY>brick.getY() && centerY<brick.getY()+brick.getImage().getWidth(null)){
			if(ball.getX()+ball.getImage().getWidth(null)>brick.getX() && ball.getX()<brick.getX()+brick.getImage().getWidth(null)){
				ball.setSpeedX(-1*ball.getSpeedX());
				return true;
			}
		}
		return false;
	}
	
	public Brick[][] createBrickArr(int xSize, int ySize) throws IOException{
		Brick[][] bricks = new Brick[ySize][xSize];
		int x,y;
		int imageSize = 29;
		boolean existent = false;
		
		for(int i=0;i<ySize;i++){
			for(int j=0;j<xSize;j++){
				x = j * imageSize;
				y = i * imageSize;
				existent = Math.random()<0.8 ? true : false;//一定几率不存在砖块
				Brick brick;
				if(existent){
					brick = new Brick("img/brick.gif",true,x,y);
				}
				else{
					brick = new Brick("img/brick.gif",false,x,y);
				}
				bricks[i][j] = brick;
			}
		}
		
		return bricks;
	}
	
	public void draw(Graphics g) throws IOException{
		if(isWon){
			JOptionPane.showMessageDialog(null, "恭喜通关！","Congratulation!",JOptionPane.INFORMATION_MESSAGE);
		}
		else{
			for(int i=0;i<bricks.length;i++){
				for(int j=0;j<bricks[0].length;j++){
					if(bricks[i][j].isExist()){
						g.drawImage(bricks[i][j].getImage(), bricks[i][j].getX(), bricks[i][j].getY(), null);
					}
				}
			}
			
			for(int i=0;i<magics.size();i++){
				if(magics.get(i).isVisable())
					g.drawImage(magics.get(i).getImage(), magics.get(i).getX(), magics.get(i).getY(), null);
				else magics.remove(i);
			}
			
			g.drawImage(ball.getImage(), ball.getX(),ball.getY(), null);
			g.drawImage(stick.getImage(), stick.getX(), stick.getY(), null);
			
			if(isOver){
				timer.stop();
				g.drawImage(ImageIO.read(new File("img/over.gif")), 140,140, null);
			}
		}
	}

	public void keyPressed(KeyEvent e) throws IOException {
		int value = e.getKeyCode();
		if(!this.isOver){//游戏未结束按这些键才有意义
			if(value==KeyEvent.VK_SPACE){
				ball.setStarted(true);
				
				ActionListener task = new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						try {
							run();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						frame.ballpanel.repaint();
					}
				};
				
				if(timer==null){
					timer = new Timer(15 , task);
					timer.start();
				}
				else{
					timer.restart();
				}
			}
			else if(value==KeyEvent.VK_LEFT ||value==KeyEvent.VK_RIGHT){
				this.setStickPos(e);
				frame.ballpanel.repaint();
			}
			else if(ball.isStarted() && value==KeyEvent.VK_UP){
				timer.setDelay(3);
			}
		}
		if(value==KeyEvent.VK_F2){
			this.restart();
			frame.ballpanel.repaint();
		}
	}

	public void keyReleased(KeyEvent e) {
		int value = e.getKeyCode();
		
		if(ball.isStarted() && value==KeyEvent.VK_UP){
			timer.setDelay(15);
		}
	}

}
