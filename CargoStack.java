/**This is an CargoStack class which holds the information of a stack of Cargo
 * 
 * @author Pooja Ginjupalli
 */
import java.util.*;

public class CargoStack{
	private ArrayList<Cargo> cargoStack; //List of Cargo representing a stack
	private double stackWeight; //The total weight of the stack
	
	/**Creates an instance of a stack of cargo with no cargo units
	 * 
	 * @Postconditions:
	 * 	Stack of Cargo is initialized and empty
	 * 	stackSize and stackWeight is 0
	 */
	public CargoStack() {
		cargoStack = new ArrayList<Cargo>();
		stackWeight = 0;
	}
	
	/**Determines if stack of cargo is empty
	 * 
	 * @return
	 * 	Returns true if stack contains no Cargo, false otherwise
	 */
	public boolean isEmpty() {
		return cargoStack.isEmpty();
	}
	
	/**Adds a Cargo to the stack
	 * 
	 * @param cargo
	 * 	The Cargo to be added
	 * 
	 * @Postcondition:
	 * 	New Cargo is on the top of the stack and 
	 * 	size increases by 1
	 * 
	 * @exception CargoStrengthException
	 * 	Indicates the Cargo currently on top has less strength than the 
	 * 	Cargo being put on
	 */
	public void push(Cargo cargo) throws CargoStrengthException {
		if (!cargoStack.isEmpty()) 
			if (cargoStack.get(cargoStack.size() - 1).getStrength().ordinal() < cargo.getStrength().ordinal()) {
				throw new CargoStrengthException();
			}
	
		cargoStack.add(cargo);
		stackWeight += cargo.getWeight();
	}

	/**Removes a Cargo to the stack
	 * 
	 * @return
	 * 	The Cargo that was removed
	 * 
	 * @Precondition:
	 * 	The stack cannot be empty
	 * 
	 * @exception EmptyStackException
	 * 	Indicates the stack is empty
	 */
	public Cargo pop() throws EmptyStackException {
		if (isEmpty()) {
			throw new EmptyStackException();
		} else {
			Cargo removedCargo = cargoStack.remove(size() - 1);
			stackWeight -= removedCargo.getWeight();
			return removedCargo;
		}
		
		
	}
	
	/**Returns the Cargo on the top of the stack
	 * 
	 * @return
	 * 	Returns the Cargo object located on top of the stack
	 * 
	 * @Preconditions:
	 * 	stack cannot be empty
	 * 
	 * @exception EmptyStackException 
	 * 	Indicates the stack is empty
	 */
	public Cargo peek() throws EmptyStackException {
		if (isEmpty()) 
			throw new EmptyStackException();
		
		return cargoStack.get(size() - 1);
	}
	
	public ArrayList<Cargo> getStack() {
		return cargoStack;
	}
	
	/**Returns the number of Cargos in this stack
	 * 
	 * @return
	 * 	The number of Cargos in the stack
	 */
	public int size() {
		return cargoStack.size();
	}
	
	/**Returns the total weight of the Cargos in the stack
	 * 
	 * @return
	 * 	The total weight of all the Cargos
	 */
	public double getWeight() {
		return stackWeight;
	}

}
