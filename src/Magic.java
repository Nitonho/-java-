import java.io.IOException;

public abstract class Magic extends BallComponent{
	private int speed = 2;
	private boolean visable = false;
	

	public Magic(String path, int x, int y) throws IOException {
		super(path, x, y);
	}

	public abstract void magicDo(Stick stick) throws IOException ;
	

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public boolean isVisable() {
		return visable;
	}

	public void setVisable(boolean visable) {
		this.visable = visable;
	}
	
	public void move(){
		if(this.getY()<BallFrame.HEIGHT){
			this.setY(getY()+this.speed);
		}
		else 
			this.setVisable(false);
	}
}
