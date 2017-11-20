package version_1;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

import javax.imageio.ImageIO;

public class Picture{
	private static File f;
	private static BufferedImage img;
	public final int width;
	public final int height;
	private ARGBPixel pixels[][];
	
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
	
	public Picture(ARGBPixel[][] pixels){
		this.pixels = pixels;
		this.width = this.pixels.length;
		this.height = this.pixels[0].length;
	}

	public ARGBPixel[][] getPixels() {
		return pixels;
	}

	public void setPixels(ARGBPixel[][] pixels) {
		if(pixels.length == this.pixels.length && pixels[0].length == this.pixels[0].length ){
			this.pixels = pixels;
		}
	}
	
	public BufferedImage getImage(){
		return img;
	}
	
	@Override
	public String toString() {
		String temp = "";
		for(ARGBPixel[] pix: pixels)
			temp += Arrays.toString(pix) + ", ";
		return "Picture [width=" + width + ", height=" + height + ", pixels="
				+ temp + ", image= " + img.toString() + ", file= " + f.toString() + "]";
	}

	public void export(String outputFilename){
		try{
			f = new File("C:\\Users\\841111795\\Desktop\\" + outputFilename + ".jpg");
			ImageIO.write(img, "jpg", f);
		}catch(IOException e){
			System.out.println(e);
		}
	}
	
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
	
	public void randomizePixels(){
		for(int i = 0; i < width; i++){
			for(int j = 0; j < height; j++){
				int color = new Random().nextInt();
				changeSpecificPixel(i, j, color);
			}
		}
	}
	
	private void changeSpecificPixel(int i, int j, int color){
		img.setRGB(i, j, color);
		try {
			pixels[i][j].setP(color);
		} catch (PixelValueException e) {
			e.printStackTrace();
		}
	}
}
