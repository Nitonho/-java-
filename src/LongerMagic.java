import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class LongerMagic extends Magic{

	public LongerMagic(String path, int x, int y) throws IOException {
		super(path, x, y);
	}

	@Override
	public void magicDo(Stick stick) throws IOException {
		if(stick.addWidth()){
			if(stick.getWidth()==3){
				stick.setImage(ImageIO.read(new File("img/longStick.jpg")));
			}
			else{
				stick.setImage(ImageIO.read(new File("img/normalStick.jpg")));
			}
		}
	}

}
