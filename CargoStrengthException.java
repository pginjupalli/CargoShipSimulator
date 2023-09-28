/**This is an Exception class that throws an error when a cargo is 
 * put onto another cargo and breaks the CargoStrength rules
 * 
 * @author Pooja Ginjupalli
 */

public class CargoStrengthException extends Exception{
	/**
	 * This is a default Exception constructor that throws an error
	 */
	public CargoStrengthException() {
		super();
	}

	/**
	 * This is a Exception constructor that throws an error with a message
	 * 
	 * @param message The error message to be thrown with the error
	 */
	public CargoStrengthException(String message) {
		super(message);
	}
}
