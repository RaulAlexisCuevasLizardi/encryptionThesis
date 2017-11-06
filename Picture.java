package test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Picture {
	private static File f;
	private static BufferedImage img;
	private final int width;
	private final int height;
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
		this.pixels = pixels;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public BufferedImage getImage(){
		return img;
	}
	
	public void export(String outputFilename){
		try{
			f = new File("C:\\Users\\841111795\\Desktop\\" + outputFilename + ".jpg");
			ImageIO.write(img, "jpg", f);
		}catch(IOException e){
			System.out.println(e);
		}
	}
}
