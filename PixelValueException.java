package test;

public class PixelValueException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PixelValueException() {
		System.err.println("A pixel's ARBG individual color value must be between 255 and 0 inclusive.\n" +
			       "A pixel's total value must be equal to or less than a signed 32 bit integer maximum\n" +
			       "value or equal to or more than a signed 32 bit integer minimum value.");
	}

	public PixelValueException(String message) {
		System.err.println(message);
	}
}