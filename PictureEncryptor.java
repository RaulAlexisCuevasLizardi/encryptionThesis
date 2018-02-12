package photoEncryption;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author 841111795
 *
 */
public class PictureEncryptor{
	private Picture pic; //The picture to be encrypted by this picture encryptor.
	private byte[][] pictureInBytes;
	private byte[] encryptedPictureArray;
	private byte[] decryptedPictureArray;
	private String key;
	private String initVector;
	private IvParameterSpec iv;
	private SecretKeySpec skeySpec;
	private Cipher cipher;

	/**
	 * Constructor of this picture encryptor. Sets the received picture as the picture to be encrypted. 
	 * @param pic the picture to be encrypted.
	 */
	public PictureEncryptor(Picture pic) throws Exception{
		this.pic = pic;
		this.pictureInBytes = new byte[this.pic.getAmountOfPixels()][4];
		int counter = 0;
		for(int i = 0; i < this.pic.width; i++) {
			for(int j = 0; j < this.pic.height; j++) {
				this.pictureInBytes[counter] = this.pic.getPixelInByteArray(i, j);
				counter++;
			}
		}
		this.key = "Bar12345Bar12345";
		this.initVector = "RandomInitVector";
		this.encryptedPictureArray = null;
		this.decryptedPictureArray = null;
		this.iv = new IvParameterSpec(this.initVector.getBytes("UTF-8"));
		this.skeySpec = new SecretKeySpec(this.key.getBytes("UTF-8"), "AES");
		this.cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
	}
	/**
	 * This method encrypts this picture so that it may be secure.
	 * HERE GOES A DETAILED EXPLANATION OF HOW THIS ENCRYPTOR ENCRYPTS STUFF.
	 * I STILL DON'T KNOW HOW THIS WILL WORK.
	 * @throws PixelValueException if the P value is more than Integer.MAX_VALUE or is less than 0.
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchAlgorithmException 
	 */
	public void Encrypt() throws Exception{
		this.cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
		System.out.println(this.pictureInBytes[0].length);
		ArrayList<Byte> encryptedByteList = new ArrayList<Byte>();
		for(int i = 0; i < pictureInBytes.length; i++) {
			byte[] block = new byte[4];
			for(int j = 0; j < pictureInBytes[i].length; j++) {
				block[j] = pictureInBytes[i][j];
			}
			if(i < (pictureInBytes.length-1)) {
				byte[] encryptedBlock = cipher.update(block);
				for(int x = 0; x < encryptedBlock.length; x++) {
					encryptedByteList.add(encryptedBlock[x]);
				}
			}else if(i == (pictureInBytes.length-1)) {
				byte[] encryptedBlock = cipher.doFinal(block);
				for(int x = 0; x < encryptedBlock.length; x++) {
					encryptedByteList.add(encryptedBlock[x]);
				}
			}
		}
		Object[] encryptedPictureTemp = encryptedByteList.toArray();
		this.encryptedPictureArray = new byte[encryptedPictureTemp.length];
		for(int i = 0; i < encryptedPictureTemp.length; i++) {
			encryptedPictureArray[i] = (byte)(encryptedPictureTemp[i]);
		}
		Path file = Paths.get(pic.getName().replaceAll(".jpg", "") + ".txt");
		Files.write(file, encryptedPictureArray);
	}

	/**
	 * This method decrypts this picture so that it may be go back to its original version.
	 * HERE GOES A DETAILED EXPLANATION OF HOW THIS ENCRYPTOR ENCRYPTS STUFF.
	 * I STILL DON'T KNOW HOW THIS WILL WORK.
	 * @throws PixelValueException if the P value is more than Integer.MAX_VALUE or is less than 0.
	 */
	public void Decrypt() throws Exception{
		Path file = Paths.get("C:\\Users\\Alexi\\eclipse-workspace\\Encryption thesis\\You-Win.txt");
		byte[]encryptedPictureReadFromFile = Files.readAllBytes(file);
		cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
		byte[] decryptedPictureTemp = cipher.doFinal(encryptedPictureReadFromFile);
		byte[][] decryptedPicture = new byte[decryptedPictureTemp.length/4][4];
		int counter = 0;
		for(int i = 0; i < decryptedPicture.length; i++) {
			for(int j = 0; j < decryptedPicture[i].length; j++) {
				decryptedPicture[i][j] = decryptedPictureTemp[counter];
			}
		}
		System.out.println("Decrypted picture length: " + decryptedPicture.length + "\nDecrypted picture column length: " + decryptedPicture[0].length);
		System.out.println("Original picture length: " + pictureInBytes.length + "\nOriginal picture column length: " + pictureInBytes[0].length);
		boolean sameValues = true;;
		for(int i = 0; i < decryptedPicture.length; i++) {
			for(int j = 0; j < decryptedPicture[i].length; j++) {
				if(decryptedPicture[i][j] == pictureInBytes[i][j]);
			}
		}
		if(sameValues)
			System.out.println("They have the same values.");
		else 
			System.out.println("They don't have the same values.");
	}


	/**
	 * Exports this picture as a JPG file to the desktop with the indicated filename.
	 * @param outputFilename the desired filename for the exported picture.
	 */
	public void ExportPicture(String outputFilename){
		pic.export(outputFilename);
	}
	/**
	 * Exports this picture as a JPG file to the desktop with no name.
	 */
	public void ExportPicture(){
		pic.export();
	}
}
