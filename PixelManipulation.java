package version_1;
import java.io.File;
import java.util.Scanner;
import java.awt.image.BufferedImage;


public class PixelManipulation {
	static BufferedImage img = null;
	static Picture pic;
	static ARGBPixel pixels [][];
	static File f = null;
	static Scanner kb;
	public static void main(String[] args) throws PixelValueException {
		kb = new Scanner(System.in);
		pic = new Picture("C:\\Users\\841111795\\Desktop\\images.png");
		System.out.println(pic.P_ValueToString());
		PictureEncryptor c = new PictureEncryptor(pic);
		
		System.out.println("Enter the file name of the picture that will be encrypted.");
		String outputFilename = kb.nextLine();
		c.Encrypt();
		c.ExportPicture(outputFilename);
		
//		System.out.println(pic);
//		System.out.println("Enter the file name of the copy of the picture to be exported.");
//		String outputFilename = kb.nextLine();
//		pic.export(outputFilename); 
//		System.out.println("Exported. Press enter...");
//		kb.nextLine();
//		
//		System.out.println("Sorting...");
//		pic.pixelSort();
//		kb.nextLine();
//		System.out.println("Enter a different file name for the sorted picture to be exported.");
//		outputFilename = kb.nextLine();
//		pic.export(outputFilename);
//		System.out.println("Exported. Press enter...");
//		kb.nextLine();
//		
//		System.out.println("Randomizing...");
//		pic.randomizePixels();
//		System.out.println("Enter a different file name for the randomized picture to be exported.");
//		outputFilename = kb.nextLine();
//		pic.export(outputFilename);
//		System.out.println("Exported.");
		
	}
}
