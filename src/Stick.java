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
		if(this.width < 3){//�����Ϊ3
			this.width++;
			return true;
		}
		return false;
	}
	
	public boolean subtractWidth() {
		if(this.width > 1){//��̳���Ϊ1
			this.width--;
			return true;
		}
		return false;
	}
}
