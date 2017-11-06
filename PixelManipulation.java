package test;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class PixelManipulation {
	static BufferedImage img = null;
	static Picture pic;
	static ARGBPixel pixels [][];
    static File f = null;
	public static void main(String[] args) throws PixelValueException {
		try{
		      f = new File("C:\\Users\\841111795\\Desktop\\HappyFace.png");
		      img = ImageIO.read(f);
		    }catch(IOException e){
		      System.out.println(e);
		    }
		System.out.println(img.toString());
		System.out.println(img.getMinTileX());
		System.out.println(img.getMinTileY());
		System.out.println(img.getMinX());
		System.out.println(img.getMinY());
		System.out.println(img.getRGB(1, 0));
		pixels = new ARGBPixel[img.getWidth()][img.getHeight()];
		for(int i = 0; i < img.getWidth(); i++){
			for(int j = 0; j < img.getHeight(); j++){
				pixels[i][j] = new ARGBPixel(img.getRGB(i, j));
			}
		}
		
		pic = new Picture(pixels);
		
		for(int i = 0; i < img.getWidth(); i++){
			for(int j = 0; j < img.getHeight(); j++){
				int color = new Random().nextInt();
				System.out.println("color " + color);
				img.setRGB(i, j, color);
			}
		}
		
	    try{
		      f = new File("C:\\Users\\841111795\\Desktop\\Output.png");
		      ImageIO.write(img, "jpg", f);
		    }catch(IOException e){
		      System.out.println(e);
		    }
	    
	}
}
