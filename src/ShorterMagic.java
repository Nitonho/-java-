import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ShorterMagic extends Magic{

	public ShorterMagic(String path, int x, int y) throws IOException {
		super(path, x, y);
	}

	@Override
	public void magicDo(Stick stick) throws IOException {
		if(stick.subtractWidth()){
			if(stick.getWidth()==1){
				stick.setImage(ImageIO.read(new File("img/shortStick.jpg")));
			}
			else{
				stick.setImage(ImageIO.read(new File("img/normalStick.jpg")));
			}
		}
	}

}
