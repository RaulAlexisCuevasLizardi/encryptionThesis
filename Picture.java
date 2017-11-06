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
}

