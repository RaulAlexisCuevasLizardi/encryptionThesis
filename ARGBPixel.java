package photoEncryption;


/**
 * 
 * @author 841111795
 *
 */
public class ARGBPixel implements Comparable<ARGBPixel>{
	private int p; //Value that represents this pixel's color.
	private byte alpha; //Value that represents the opacity of this pixel.
	private byte red; //Value that represents the red component of this pixel.
	private byte green; //Value that represents the green component of this pixel.
	private byte blue; //Value that represents the blue component of this pixel.

	/**
	 * Constructor to the ARGBPixel class. Sets this pixel's P value to the desired number.
	 * @param p Value that represents this pixel's color.
	 * @throws PixelValueException if the P value is more than Integer.MAX_VALUE or is less than 0.
	 */
	public ARGBPixel(int p) throws PixelValueException{
		this.setP(p);
	}

	/**
	 * Constructor to the ARGBPixel class. Sets this pixel's individual component value to their desired number.
	 * @param alpha represents the opacity of this pixel.
	 * @param red represents the red component of this pixel.
	 * @param green represents the green component of this pixel.
	 * @param blue represents the blue component of this pixel.
	 * @throws PixelValueException if any of the pixel component values are more than 255 or less than 0.
	 */
	public ARGBPixel(byte alpha, byte red, byte green, byte blue) throws PixelValueException{
		if(alpha > 128 || alpha < -128 || red > 127 || red < -128 || blue > 127 || blue < -128 || green > 127 || green < -128 ){
			throw new PixelValueException();
		}
		else{			
			this.setP((alpha<<24) | (red<<16) | (green<<8) | blue);
		}
	}

	/**
	 * Returns this pixel's P value.
	 * @return this pixel's P value.
	 */
	public int getP() {
		return this.p;
	}

	/**
	 * Returns this pixel's alpha component value.
	 * @return this pixel's alpha component value
	 */
	public byte getAlpha() {
		return this.alpha;
	}

	/**
	 * Returns this pixel's red component value.
	 * @return this pixel's red component value
	 */
	public byte getRed() {
		return this.red;
	}

	/**
	 * Returns this pixel's green component value.
	 * @return this pixel's green component value
	 */
	public byte getGreen() {
		return this.green;
	}

	/**
	 * Returns this pixel's blue component value.
	 * @return this pixel's blue component value
	 */
	public byte getBlue() {
		return this.blue;
	}

	/**
	 * Sets the P value of this pixel to the desired amount.
	 * @param p represents this pixel's color.
	 * @throws PixelValueException if the P value is more than Integer.MAX_VALUE or less than 0.
	 */
	public void setP(int p) throws PixelValueException {
		if(p > Integer.MAX_VALUE || p < Integer.MIN_VALUE){
			throw new PixelValueException();
		}
		else{
			this.p = p;
			this.alpha = (byte) ((p>>24) & 0xFF);
			this.red = (byte) ((p>>16) & 0xFF);
			this.green = (byte) ((p>>8) & 0xFF);
			this.blue = (byte) (p & 0xFF);
		}
	}

	/**
	 * Sets this pixel's individual component values to the desired amounts.
	 * @param alpha represents the opacity of this pixel.
	 * @param red represents the red component of this pixel.
	 * @param green represents the green component of this pixel. 
	 * @param blue represents the blue component of this pixel.
	 * @throws PixelValueException if any of the pixel component values are more than 255 or less than 0.
	 */
	public void setARGB(byte alpha, byte red, byte green, byte blue) throws PixelValueException{
		if(alpha > 127 || alpha < -128 || red > 127 || red < -128 || blue > 127 || blue < -128 || green > 127 || green < -128 ){
			throw new PixelValueException();
		}
		else{			
			this.setP((alpha<<24) | (red<<16) | (green<<8) | blue);
		}
	}

	/**
	 * Sets this pixel's alpha component to the desired amount.
	 * @param alpha represents the alpha component of this pixel.
	 * @throws PixelValueException if the alpha component value is higher than 255 or less than 0.
	 */
	public void setAlpha(byte alpha) throws PixelValueException {
		if(alpha > 127 || alpha < -128){
			throw new PixelValueException();
		}else{			
			this.setP((alpha<<24) | (this.red<<16) | (this.green<<8) | this.blue);
		}
	}

/**
 * Sets this pixel's red component to the desired amount.
 * @param red represents the red component of this pixel.
 * @throws PixelValueException if the red component value is more than 255 or less than 0. 
 */
	public void setRed(byte red) throws PixelValueException {
		if(red > 127 || red < -128){
			throw new PixelValueException();
		}else{			
			this.setP((this.alpha<<24) | (red<<16) | (this.green<<8) | this.blue);
		}
	}

/**
 * Sets this pixel's green component to the desired amount.
 * @param green represents the green component of this pixel.
 * @throws PixelValueException if the green component value is more than 255 or less than 0.
 */
	public void setGreen(byte green) throws PixelValueException {
		if(green > 127 || green < -128){
			throw new PixelValueException();
		}else{			
			this.setP((this.alpha<<24) | (this.red<<16) | (green<<8) | this.blue);
		}
	}

/**
 * Sets this pixel's blue component to the desired amount.
 * @param blue represents the blue component of this pixel.
 * @throws PixelValueException if the blue component value is more than 255 or less than 0.
 */
	public void setBlue(byte blue) throws PixelValueException {
		if(blue > 127 || blue < -128){
			throw new PixelValueException();
		}else{			
			this.setP((this.alpha<<24) | (this.red<<16) | (this.green<<8) | blue);
		}
	}

	/**
	 * Returns a string representing this pixel's data.
	 */
	@Override
	public String toString() {
		return "ARGBPixel [p=" + p + ", alpha=" + this.alpha + ", red=" + this.red
				+ ", green=" + this.green + ", blue=" + this.blue + "]";
	}
	/**
	 * Returns this ARGBPixel's color components in a byte array.
	 * @return this ARGBPixel's color components in a byte array.
	 */
	public byte[] toByteArray() {
		byte[] byteArray = {this.alpha, this.red, this.green, this.blue};
		return byteArray;
	}

	/**
	 * Compares this pixel with another ARGBPixel and returns the value 0 if this pixel's P value is equal to the argument P value;
	 *  a value less than 0 if this Pixel's P value is numerically less than the argument P value; and a value greater than 0 
	 *  if this Pixel's P value is numerically greater than the argument P value.
	 *  @return a value greater than, less than or equal to zero depending on how this pixel's P value compares to the argument's
	 *  P value.
	 */
	@Override
	public int compareTo(ARGBPixel anotherPixel) {
		return Integer.compare(this.p, anotherPixel.getP());
	}
}
