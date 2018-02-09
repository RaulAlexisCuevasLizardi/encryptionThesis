
/**
 * @author 841111795
 *
 */
public class PictureEncryptor {
	private Picture pic; //The picture to be encrypted by this picture encryptor.
	
	/**
	 * Constructor of this picture encryptor. Sets the received picture as the picture to be encrypted. 
	 * @param pic the picture to be encrypted.
	 */
	public PictureEncryptor(Picture pic) {
		this.pic = pic;
	}
	/**
	 * This method encrypts this picture so that it may be secure.
	 * HERE GOES A DETAILED EXPLANATION OF HOW THIS ENCRYPTOR ENCRYPTS STUFF.
	 * I STILL DON'T KNOW HOW THIS WILL WORK.
	 * @throws PixelValueException if the P value is more than Integer.MAX_VALUE or is less than 0.
	 */
	public void Encrypt() throws PixelValueException{
		ARGBPixel[][] pix = pic.getPixels();
		for(int i = 0; i < pic.width; i++){
			for(int j = 0; j < pic.height; j++){
				pix[i][j].setP(pix[i][j].getP()+1);
			}
		}
	}
	
	/**
	 * This method decrypts this picture so that it may be go back to its original version.
	 * HERE GOES A DETAILED EXPLANATION OF HOW THIS ENCRYPTOR ENCRYPTS STUFF.
	 * I STILL DON'T KNOW HOW THIS WILL WORK.
	 * @throws PixelValueException if the P value is more than Integer.MAX_VALUE or is less than 0.
	 */
	public void Decrypt() throws PixelValueException{
		ARGBPixel[][] pix = pic.getPixels();
		for(int i = 0; i < pic.width; i++){
			for(int j = 0; j < pic.height; j++){
				pix[i][j].setP(pix[i][j].getP()-1);
			}
		}
	}
	
	/**
	 * Exports this picture as a JPG file to the desktop with the indicated filename.
	 * @param outputFilename the desired filename for the exported picture.
	 */
	public void ExportPicture(String outputFilename){
		pic.export(outputFilename);
	}
}
