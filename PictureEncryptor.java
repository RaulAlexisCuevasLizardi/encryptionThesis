/**
 * 
 */
package version_1;

/**
 * @author 841111795
 *
 */
public class PictureEncryptor {
	private static Picture pic;
	
	public PictureEncryptor(Picture pic) {
		this.pic = pic;
	}
	public void Encrypt() throws PixelValueException{
		ARGBPixel[][] pix = pic.getPixels();
		for(int i = 0; i < pic.width; i++){
			for(int j = 0; j < pic.height; j++){
				pix[i][j].setP(pix[i][j].getP()+1);
			}
		}
	}
	
	public void Decrypt() throws PixelValueException{
		ARGBPixel[][] pix = pic.getPixels();
		for(int i = 0; i < pic.width; i++){
			for(int j = 0; j < pic.height; j++){
				pix[i][j].setP(pix[i][j].getP()-1);
			}
		}
	}
	
	public void ExportPicture(String outputFilename){
		pic.export(outputFilename);
	}
}
