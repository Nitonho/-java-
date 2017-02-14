import java.io.IOException;

public class Stick extends BallComponent{
	private int width;
	
	public Stick(int panelWidth, int panelHeight, String path) throws IOException{
		super(panelWidth,panelHeight,path);
		this.width = 2;
	}
	
	public int getWidth() {
		return this.width;
	}

	public boolean addWidth() {
		if(this.width < 3){//最长长度为3
			this.width++;
			return true;
		}
		return false;
	}
	
	public boolean subtractWidth() {
		if(this.width > 1){//最短长度为1
			this.width--;
			return true;
		}
		return false;
	}
}
