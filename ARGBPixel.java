package test;

public class ARGBPixel {
	private int p;
	private int alpha;
	private int red;
	private int green;
	private int blue;

	public ARGBPixel(int p) throws PixelValueException{
		this.setP(p);
	}

	public ARGBPixel(int alpha, int red, int green, int blue) throws PixelValueException{
		if(alpha > 255 || alpha < 0 || red > 255 || red < 0 || blue > 255 || blue < 0 || green > 255 || green < 0 ){
			throw new PixelValueException();
		}
		else{			
			this.setP((alpha<<24) | (red<<16) | (green<<8) | blue);
		}
	}

	public int getP() {
		return p;
	}

	public int getAlpha() {
		return alpha;
	}

	public int getRed() {
		return red;
	}

	public int getGreen() {
		return green;
	}

	public int getBlue() {
		return blue;
	}

	public void setP(int p) throws PixelValueException {
		if(p > Integer.MAX_VALUE || p < Integer.MIN_VALUE){
			throw new PixelValueException();
		}
		else{
			this.p = p;
			this.alpha = (p>>24) & 0xFF;
			this.red = (p>>16) & 0xFF;
			this.green = (p>>8) & 0xFF;
			this.blue = p & 0xFF;
		}
	}

	public void setARGB(int alpha, int red, int green, int blue) throws PixelValueException{
		if(alpha > 255 || alpha < 0 || red > 255 || red < 0 || blue > 255 || blue < 0 || green > 255 || green < 0 ){
			throw new PixelValueException();
		}
		else{			
			this.setP((alpha<<24) | (red<<16) | (green<<8) | blue);
		}
	}

	public void setAlpha(int alpha) throws PixelValueException {
		if(alpha > 255 || alpha < 0){
			throw new PixelValueException();
		}else{			
			this.setP((alpha<<24) | (this.red<<16) | (this.green<<8) | this.blue);
		}
	}


	public void setRed(int red) throws PixelValueException {
		if(red > 255 || red < 0){
			throw new PixelValueException();
		}else{			
			this.setP((this.alpha<<24) | (red<<16) | (this.green<<8) | this.blue);
		}
	}


	public void setGreen(int green) throws PixelValueException {
		if(green > 255 || green < 0){
			throw new PixelValueException();
		}else{			
			this.setP((this.alpha<<24) | (this.red<<16) | (green<<8) | this.blue);
		}
	}


	public void setBlue(int blue) throws PixelValueException {
		if(blue > 255 || blue < 0){
			throw new PixelValueException();
		}else{			
			this.setP((this.alpha<<24) | (this.red<<16) | (this.green<<8) | blue);
		}
	}

	@Override
	public String toString() {
		return "ARGBPixel [p=" + p + ", alpha=" + alpha + ", red=" + red
				+ ", green=" + green + ", blue=" + blue + "]";
	}
}
