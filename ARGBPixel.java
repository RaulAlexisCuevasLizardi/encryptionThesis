package photoEncryption;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Random;
import javax.imageio.ImageIO;

/**
 * 
 * @author 841111795
 *
 */
public class Picture{
	private static File f; //The file that contains the image for this picture.
	private String name;
	private static BufferedImage img; //An object that contains the data for this picture.
	public final int width; //Width of this picture.
	public final int height; //Height of this picture.
	private ARGBPixel pixels[][]; //Double array of ARGBPixels that represent this picture. 
	private int amountOfPixels; //Amount of pixels in this picture. This is width*height.
	private int amountOfBytes; //Amount of bytes in this picture. This will be useful for encrypting later on. This is amountOfPixels * 4
	private int amountOfBits; //Amount of bits in this picture. This is just amountOfBytes * 8

	/**
	 * Constructor of the Picture class that receives the filepath of the picture. Using the filepath it creates a file object and 
	 * creates a BufferedImage object with that file. This method also initializes height, width and creates a double array of 
	 * ARGBPixels using the data of that BufferedImage. 
	 * @param filepath Address of the image file for creating a Picture.
	 */
	public Picture(String filepath) {
		try{
			f = new File(filepath);
			img = ImageIO.read(f);
		}catch(IOException e){
			System.out.println(e);
		}
		name = f.getName();
		height = img.getHeight();
		width = img.getWidth();
		pixels = new ARGBPixel[width][height];
		amountOfPixels = width*height;
		amountOfBytes = amountOfPixels*4;
		amountOfBits = amountOfBytes*8;

		for(int i = 0; i < width; i++){
			for(int j = 0; j < height; j++){
				try {
					pixels[i][j] = new ARGBPixel(img.getRGB(i, j));
				} catch (PixelValueException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Constructor of the Picture class that receives a double array of ARGBPixels. The received double array's length and height
	 * should represent the width and height of the picture respectively. Also initializes the height and width of the Picture.
	 * @param pixels represents this picture's pixels.
	 */
	public Picture(ARGBPixel[][] pixels){
		this.pixels = pixels;
		this.width = this.pixels.length;
		this.height = this.pixels[0].length;
	}

	/**
	 * Returns a double array of ARGBPixels.
	 * @return a double array of ARGBPixels.
	 */
	public ARGBPixel[][] getPixels() {
		return pixels;
	}
	/**
	 * Return the amount of Pixels in this picture.
	 * @return the amount of Pixels in this picture.
	 */
	public int getAmountOfPixels() {
		return amountOfPixels;
	}
	/**
	 * Return the amount of bytes in this picture.
	 * @return the amount of bytes in this picture.
	 */
	public int getAmountOfBytes() {
		return amountOfBytes;
	}
	/**
	 * Return the amount of bits in this picture.
	 * @return the amount of bits in this picture.
	 */
	public int getAmountOfBits() {
		return amountOfBits;
	}

	/**
	 * I changed my mind. This method will not be used. If you want to change pixels in
	 */
//	/**
//	 * Sets the pixels of this picture to the received double array of ARGBPixels. This can only work if the height and length
//	 * of the received pixels are the same of the original height and width of this picture.
//	 * @param Double array of ARGBPixels. Height and length of the received pixels must be equal to the original height and 
//	 * width of this picture.
//	 */
//	public void setPixels(ARGBPixel[][] pixels) {
//		if(pixels.length == this.pixels.length && pixels[0].length == this.pixels[0].length ){
//			this.pixels = pixels;
//		}
//	}
	/**
	 * Returns this picture's BufferedImage.
	 * @return this picture's BufferedImage.
	 */
	public BufferedImage getImage(){
		return img;
	}
	/**
	 * Returns a string representing this picture's data.
	 */
	@Override
	public String toString() {
		String temp = "";
		for(ARGBPixel[] pix: pixels)
			temp += Arrays.toString(pix) + ", ";
		return "Picture [width=" + width + ", height=" + height + ", pixels="
		+ temp + ", image= " + img.toString() + ", file= " + f.toString() + "]";
	}
	/**
	 * Exports this picture as a JPG file to the desktop with the indicated filename.
	 * @param outputFilename the desired filename for the exported picture.
	 */
	public void export(String outputFilename){
		try{
			f = new File("C:\\Users\\Alexi\\Desktop\\" + outputFilename + ".jpg");
			ImageIO.write(img, "jpg", f);
		}catch(IOException e){
			System.out.println(e);
		}
	}
	/**
	 * 
	 */
	public void export(){
		try{
			f = new File("C:\\Users\\Alexi\\Desktop\\" + f.getName() + "export" + ".jpg");
			ImageIO.write(img, "jpg", f);
		}catch(IOException e){
			System.out.println(e);
		}
	}
	/**
	 * Sorts the double array of ARGBPixels so that the P value of the pixels are sorted from lowest to highest where index
	 * [0][0] is the starting point and [length-1][length-1] is the end point.
	 * @throws PixelValueException if a pixel's P value exceeds Integer.Maximum or is less than 0.
	 */
//	public void pixelSort() throws PixelValueException{
//		//THIS IS 100% USELESS. EXTREMELY BAD ALGORITHM WITH O(n^4)....
//		//****Sorting algorithm taken from the Internet****
//		int temp = 0;
//		for(int x = 0; x < width; x++)//n = 2560
//		{
//			for(int y = 0; y < height; y++)//n = 1440
//			{
//				for(int i = 0; i < width; i++)//n = 2560
//				{
//					for(int j = 0; j < height; j++)//n = 1440
//					{
//						if(pixels[i][j].compareTo(pixels[x][y]) > 0)
//						{
//							temp = pixels[x][y].getP();
//							pixels[x][y].setP(pixels[i][j].getP());
//							pixels[i][j].setP(temp);
//							System.out.println("Sorting... Pixel " + x + " " + y + " with " + i + " " + j);
//						}
//					}
//				}
//			}
//		}
//
//		for(int i = 0; i < width; i++){
//			for(int j = 0; j < height; j++){
//				int color = pixels[i][j].getP();
//				changeSpecificPixel(i, j, color);
//			}
//		}
//
//	}

	/**
	 * Randomizes the pixels of this picture. Mostly used for testing.
	 */
	public void r(){
		for(int i = 0; i < width; i++){
			for(int j = 0; j < height; j++){
				int color = new Random().nextInt();
				changeSpecificPixel(i, j, color);
			}
		}
	}
	/**
	 * This method changes the pixel at index [i][j] to the color indicated. The received value must represent the P value 
	 * of the desired color.
	 * @param i Indicates the column where the pixel resides.
	 * @param j Indicates the row where the pixel resides.
	 * @param color The P value of the desired color.
	 */
	public void changeSpecificPixel(int i, int j, int color){
		img.setRGB(i, j, color);
		try {
			pixels[i][j].setP(color);
		} catch (PixelValueException e) {
			e.printStackTrace();
		}
	}
	/**
	 * This method changes the pixel at index [i][j] to the color composed of the components given. 
	 * @param i Indicates the column where the pixel resides.
	 * @param j Indicates the row where the pixel resides.
	 * @param alpha 
	 * @param red
	 * @param green
	 * @param blue
	 */
	public void changeSpecificPixel(int i, int j, byte alpha, byte red, byte green, byte blue){
		img.setRGB(i, j, ((alpha<<24) | (red<<16) | (green<<8) | blue));
		try {
			pixels[i][j].setARGB(alpha, red, green, blue);
		} catch (PixelValueException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @param i
	 * @param j
	 * @return
	 */
	public byte[] getPixelInByteArray(int i, int j) {
		return pixels[i][j].toByteArray();
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * This method returns a string with all the P values of every pixel in this picture
	 * THIS NEEDS SOME CHANGING. I THINK IT MIGHT BE BETTER TO CONVERT THE INT VALUE INTO
	 * BYTES SO THAT THEY ARE EASIER TO MANAGE.
	 * @return
	 */
	public String P_ValueToString(){
		StringBuilder str = new StringBuilder();
		DecimalFormat fmt = new DecimalFormat("0000");
		for(int i = 0; i < this.width; i++){
			for(int j = 0; j < this.height; j++){
				String p_value = String.valueOf(fmt.format(this.pixels[i][j].getP()) + "\n");
				str.append(p_value);
			}
		}
		return str.toString();
	}
}
