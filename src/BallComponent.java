import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class BallComponent {
	private Image image;
	private int x,y;
	
	public BallComponent(int panelWidth, int panelHeight, String path) throws IOException{
		this.image = ImageIO.read(new File(path));
		this.x = (panelWidth - image.getWidth(null))/2;
		this.y = panelHeight - image.getHeight(null);
	}
	
	public BallComponent(Stick stick, String path) throws IOException{
		this.image = ImageIO.read(new File(path));
		this.x = stick.getX() + stick.getImage().getWidth(null) / 2 - this.image.getWidth(null) / 2; 
		this.y = stick.getY() - this.image.getHeight(null);
	}
	
	public BallComponent(String path, int x, int y) throws IOException{
		this.image = ImageIO.read(new File(path));
		this.x = x;
		this.y = y;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}
