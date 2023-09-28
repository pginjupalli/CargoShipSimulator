/**This is an Exception class that throws an error when a stack is empty
 * 
 * @author Pooja Ginjupalli
 */

public class EmptyStackException extends Exception{
	
	/**This is a default Exception constructor that throws an error
	 */
	public EmptyStackException() {
		super();
	}
	
	/**This is a Exception constructor that throws an error with a message
	 * 
	 * @param message
	 * 	The error message to be thrown with the error
	 */
	public EmptyStackException(String message) {
		super(message);
	}
}
