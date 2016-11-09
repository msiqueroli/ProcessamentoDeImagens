package principal;

public class RGB {
	
	private int red;
	private int green;
	private int blue;
	
	public RGB( int RGB ){
		red = ( RGB >> 16 ) & 0x000000FF;
        green = ( RGB >> 8 ) & 0x000000FF;
        blue = ( RGB ) & 0x000000FF;
	}
	
	public void setRed(int red){
		this.red = red;
	}
	
	public void setGreen(int green){
		this.green = green;
	}
	
	public void setBlue(int blue){
		this.blue = blue;
	}

	public int getRed(){
		return red;
	}
	
	public int getGreen(){
		return green;
	}
	
	public int getBlue(){
		return blue;
	}
}
