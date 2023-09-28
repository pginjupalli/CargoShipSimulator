/**This is an Exception class that throws an error when a stack is full
 * 
 * @author Pooja Ginjupalli
 */

public class FullStackException extends Exception{
	
	/**This is a default Exception constructor that throws an error
	 */
	public FullStackException() {
		super();
	}
	
	/**This is a Exception constructor that throws an error with a message
	 * 
	 * @param message
	 * 	The error message to be thrown with the error
	 */
	public FullStackException(String message) {
		super(message);
	}
}
