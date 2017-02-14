import java.io.IOException;

public class Ball extends BallComponent{
	private int speedX = 3;
	private int speedY = -2;
	private boolean started = false;
	BallService service;
	
	public Ball(BallService service, Stick stick, String path) throws IOException {
		super(stick,path);
		this.service = service;
	}

	public int getSpeedX() {
		return speedX;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public int getSpeedY() {
		return speedY;
	}

	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}

	public boolean isStarted() {
		return started;
	}

	public void setStarted(boolean started){
		this.started = started;
	}
	
	
	
	public void move(){
		int tmpX = this.getX() + this.speedX;
		int tmpY = this.getY() + this.speedY;
		this.setX(tmpX);
		this.setY(tmpY);
		
		if(tmpX<=0 || tmpX>=BallFrame.WIDTH-this.getImage().getWidth(null)){
			this.speedX = -this.speedX;
		}
		if(tmpY<=0){
			this.speedY = -this.speedY;
		}
		else if(tmpY>=BallFrame.HEIGHT-this.getImage().getHeight(null)){
			System.out.println("Game Over!");
			service.setOver(true);
		}
		
	}
}
