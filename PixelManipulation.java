package test;
import java.io.File;
import java.util.Scanner;
import java.awt.image.BufferedImage;

/*
 * TODO
 * TIME TO START WORKING ON THE ENCRYPTION ALGORITHM
 */

public class PixelManipulation {
	static BufferedImage img = null;
	static Picture pic;
	static ARGBPixel pixels [][];
	static File f = null;
	static Scanner kb;
	public static void main(String[] args) throws PixelValueException {
		kb = new Scanner(System.in);
		pic = new Picture("C:\\Users\\841111795\\Desktop\\HappyFace.png");
		
//		System.out.println(img.toString());
//		System.out.println(img.getMinTileX());
//		System.out.println(img.getMinTileY());
//		System.out.println(img.getMinX());
//		System.out.println(img.getMinY());
//		System.out.println(img.getRGB(1, 0));
		
//		pixels = new ARGBPixel[img.getWidth()][img.getHeight()];
//		for(int i = 0; i < img.getWidth(); i++){
//			for(int j = 0; j < img.getHeight(); j++){
//				pixels[i][j] = new ARGBPixel(img.getRGB(i, j));
//			}
//		}
//
//
//		for(int i = 0; i < img.getWidth(); i++){
//			for(int j = 0; j < img.getHeight(); j++){
//				int color = new Random().nextInt();
//				System.out.println("color " + color);
//				img.setRGB(i, j, color);
//			}
//		}
		System.out.println(pic);
		System.out.println("Enter the file name of the picture to be exported.");
		String outputFilename = kb.nextLine();
		kb.nextLine();
		pic.export(outputFilename); 
		System.out.println("Exported. Press enter...");
		kb.nextLine();
		System.out.println("Randomizing...");
		pic.randomizePixels();
		System.out.println("Enter a different file name for the randomized picture to be exported.");
		outputFilename = kb.nextLine();
		pic.export(outputFilename);
		
	}
}
