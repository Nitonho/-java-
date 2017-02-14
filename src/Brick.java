import java.io.IOException;

public class Brick extends BallComponent {
	public static int NO_MAGIC_TYPE = 0;
	public static int MAGIC_LONG_TYPE = 1;
	public static int MAGIC_SHORT_TYPE = 2;
	
	private boolean existent;
	private Magic magic;
	
	
	public Brick(String path,boolean existent,int x,int y) throws IOException{
		super(path,x,y);
		this.existent = existent;
		
		if(existent){
			double random = Math.random();
			if(random-0.1<0){//一定的几率有道具
				this.magic = new LongerMagic("img/longerMagic.jpg",x,y);
			}
			else if(random-0.9>0){
				this.magic = new ShorterMagic("img/shorterMagic.jpg",x,y);
			}
		}
		
	}
	
	public void setMagic(Magic magic){
		this.magic = magic;
	}
	
	public Magic getMagic(){
		return this.magic;
	}
	
	public boolean isExist(){
		return this.existent;
	}
	
	public void setExist(boolean existent){
		this.existent = existent;
	}
}
