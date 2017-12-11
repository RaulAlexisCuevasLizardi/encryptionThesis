package version_1;

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
	private static BufferedImage img; //An object that contains the data for this picture.
	public final int width; //Width of this picture.
	public final int height; //Height of this picture.
	private ARGBPixel pixels[][]; //Double array of ARGBPixels that represent this picture. 

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
		height = img.getHeight();
		width = img.getWidth();
		pixels = new ARGBPixel[width][height];

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
	 * Sets the pixels of this picture to the received double array of ARGBPixels. This can only work if the height and length
	 * of the received pixels are the same of the original height and width of this picture.
	 * @param Double array of ARGBPixels. Height and length of the received pixels must be equal to the original height and 
	 * width of this picture.
	 */
	public void setPixels(ARGBPixel[][] pixels) {
		if(pixels.length == this.pixels.length && pixels[0].length == this.pixels[0].length ){
			this.pixels = pixels;
		}
	}
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
			f = new File("C:\\Users\\841111795\\Desktop\\" + outputFilename + ".jpg");
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
	public void pixelSort() throws PixelValueException{
		//****Sorting algorithm taken from the Internet****
		int temp = 0;
		for(int x = 0; x < width; x++)
		{
			for(int y = 0; y < height; y++)
			{
				for(int i = 0; i < width; i++)
				{
					for(int j = 0; j < height; j++)
					{
						if(pixels[i][j].compareTo(pixels[x][y]) > 0)
						{
							temp = pixels[x][y].getP();
							pixels[x][y].setP(pixels[i][j].getP());
							pixels[i][j].setP(temp);
						}
					}
				}
			}
		}

		for(int i = 0; i < width; i++){
			for(int j = 0; j < height; j++){
				int color = pixels[i][j].getP();
				changeSpecificPixel(i, j, color);
			}
		}

	}

	/**
	 * Randomizes the pixels of this picture. Mostly used for testing.
	 */
	public void randomizePixels(){
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
	private void changeSpecificPixel(int i, int j, int color){
		img.setRGB(i, j, color);
		try {
			pixels[i][j].setP(color);
		} catch (PixelValueException e) {
			e.printStackTrace();
		}
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
				String p_value = String.valueOf(fmt.format(this.pixels[i][j].getP()));
				str.append(p_value);
			}
		}
		return str.toString();
		
	}
}
