/**This is an CargoShip class which holds the information 
 * 	of multiple stacks of Cargo
 * 
 * @author Pooja Ginjupalli
 */

public class CargoShip {
	private CargoStack[] stacks; //An array of stacks, each composed of Cargo
	private int maxHeight; //The maximum amount of Cargo in each stack
	private double maxWeight; //The total weight of the Cargo on the ship
	
	/**Returns an instance of CargoShip with a limited height, weight, stacks
	 * 
	 * @param numStacks
	 * 	The maximum number of stacks allowed on the ship
	 * 
	 * @param initMaxHeight
	 * 	The maximum number of Cargos that can be placed on top of each other
	 * 
	 * @param initMaxWeight
	 * 	The weight limit of all the stacks combined
	 * 
	 * @Preconditions:
	 * 	numStacks, initMaxHeight, initMaxWeight are all greater than 0
	 * 
	 * @Postcondition:
	 * 	The object has been initialized to a CargoShip with 
	 * 	maxWeight, maxHeight and stacks
	 * 
	 * @exception IllegalArgumentException
	 * 	Indicates numStacks, initMaxHeight, or initMaxWeight 
	 * 	are less than or equal to 0
	 */
	public CargoShip(int numStacks, int initMaxHeight, double initMaxWeight) {
		if (numStacks <= 0 || initMaxHeight <= 0 || initMaxWeight <= 0)
			throw new IllegalArgumentException();
		
		stacks = new CargoStack[numStacks];
		for (int x = 0; x < numStacks; x++)
			stacks[x] = new CargoStack();
		
		maxHeight = initMaxHeight;
		maxWeight = initMaxWeight;
	}
	
	/**Pushes a Cargo onto the stack indicated
	 * 
	 * @param cargo
	 * 	The Cargo to be added to the ship
	 * 
	 * @param stack
	 * 	The CargoStack to place the Cargo on
	 * 
	 * @Preconditions:
	 * 	cargo cannot be null
	 * 	stack must be between 1 and stacks.length
	 * 	CargoShip is initialized and not null
	 * 	The size of the stack cannot equal maxHeight
	 * 	The sum total of cargo.getWeight() and the weight of the cargos 
	 * 	on the ship do not exceed maxWeight
	 * 
	 * @Postconditions:
	 * 	The Cargo has been added ot the right stack or an exception has 
	 * 	been thrown and the stack remain unchanged
	 * 
	 * @exception IllegalArgumentException
	 * 	Indicates cargo is null or stack is less than 1 or greater than stacks.length
	 * 
	 * @exception FullStackException
	 * 	Indicates the stack the cargo is being added to is at its maxHeight
	 * 
	 * @exception ShipOverweightException
	 * 	Indicates the cargo's weight combined with the total weight of stacks 
	 * 	exceeds maxWeight
	 * 
	 * @exception CargoStrengthException
	 * 	Indicates Cargo is trying to be placed on top of a Cargo with weaker strength
	 * @throws EmptyStackException 
	 */
	public void pushCargo(Cargo cargo, int stack) throws FullStackException, 
		ShipOverweightException, CargoStrengthException, EmptyStackException {
		if (cargo == null || stack < 1 || stack > stacks.length) 
			throw new IllegalArgumentException();
		else if (stacks[stack - 1].size() == maxHeight)
			throw new FullStackException();
		else if (getTotalWeight() + cargo.getWeight() > maxWeight)
			throw new ShipOverweightException();
		else if (!stacks[stack - 1].isEmpty())
			if (stacks[stack - 1].peek().getStrength().ordinal()
				< cargo.getStrength().ordinal())
				throw new CargoStrengthException();
			
		stacks[stack - 1].push(cargo);
	}
	
	/**Removes the top Cargo from the indicated stack and returns the value
	 * 
	 * @param stack
	 * 	The stack whose top Cargo is to be removed
	 * 
	 * @Precondition:
	 * 	CargoShip is initialized and not null
	 * 	stack is between 1 and stacks.length
	 * 
	 * @Postcondition:
	 * 	The Cargo has been removed and returned or an 
	 * 	exception had been thrown and no changes were made
	 * 
	 * @return
	 * 	The Cargo that was removed
	 * 
	 * @exception IllegalArgumentException
	 * 	Indicates stack is less than 1 or greater than stacks.length
	 * 
	 * @exception EmptyStackException
	 * 	Indicates the selected stack is empty
	 */
	public Cargo popCargo(int stack) throws EmptyStackException{
		if (stack < 1 || stack > stacks.length)
			throw new IllegalArgumentException();
		else if (stacks[stack - 1].isEmpty())
			throw new EmptyStackException();
		
		Cargo removedCargo = stacks[stack - 1].pop();
		return removedCargo;
	}
	
	/**Returns the total weight of Cargos currently on the ship
	 * 
	 * @return
	 * 	The sum of the weights of stacks on the ship
	 */
	public double getTotalWeight() {
		double totalWeight = 0;
		for (CargoStack stack : stacks) 
			totalWeight += stack.getWeight();
		return totalWeight;
	}
	
	/**Returns the max weight for the ship
	 * 
	 * @return
	 * 	Returns the maximum weight the ship can hold
	 */
	public double getMaxWeight() {
		return maxWeight;
	}
	/** Returns a specific CargoStack on board at index
	 * 
	 * @param index
	 * 	The position of the CargoStack in stacks to be returned
	 * 
	 * @Precondition:
	 * 	index must be between 1 and stacks.length inclusive
	 * 
	 * @return
	 * 	The CargoStack at the position index
	 * 
	 * @exception IllegalArgumentException
	 * 	Indicates index is less than 1 or greater than stacks.length
	 */
	public CargoStack getCargoStack(int index) {
		if (index < 1 || index > stacks.length)
			throw new IllegalArgumentException();
		
		return stacks[index - 1];
	}
	
	/**Returns number of stacks on the ship
	 * 
	 * @return
	 * 	The number of stacks onboard, including empty stacks
	 */
	public int getNumStacks() {
		return stacks.length;
	}
	
	public boolean isEmpty() {
		int numEmptyStacks = 0;
		
		for (CargoStack stack : stacks)
			if (!stack.isEmpty())
				numEmptyStacks++;
		
		return (numEmptyStacks == 0);
	}
	
	/**Returns whether or not the ship contains any Cargo with a given name
	 * 
	 * @param name
	 * 	The name of the Cargo(s) to be found
	 * 
	 * @return
	 * 	True is a Cargo exists with the name, false otherwise
	 */
	public boolean contains(String name) {
		if (isEmpty())
			return false;
		
		for (CargoStack stack : stacks) 
			for (Cargo cargo : stack.getStack()) 
				if (cargo.getName().equalsIgnoreCase(name)) 
					return true;
		
		return false;
	}
	
	/**Finds the Cargo with the given name and prints them in a table. 
	 * 	If no Cargo is found, the user is notified
	 * 
	 * @param name
	 * The name of the Cargo to be found
	 * 
	 * @Precondition:
	 * 	CargoShip is initialized and not null
	 * 
	 * @Precondition:
	 * 	The details of the Cargo found have been printed or the user 
	 * 	was notified that no Cargo were found
	 * 	The CargoShip remains unchanged
	 */
	public void findAndPrint(String name) {
		if (contains(name)) {
			double totalWeight = 0;
			int cargoCount = 0;
			
			System.out.println("Cargo '" + name + "' found at the following locations: \n");
			System.out.println(" Stack   Depth   Weight   Strength");
			System.out.println("=======+=======+========+==========");

			int depth = 0;
			for (int stack = 0; stack < stacks.length; stack++) {
				depth = 0;
				for (int cargo = stacks[stack].size() - 1; cargo >= 0; cargo--) {
					if (stacks[stack].getStack().get(cargo).getName().equalsIgnoreCase(name)) {
						if (stacks[stack].getStack().get(cargo).getWeight() < 1000)
							System.out.printf("   %.1s   |   %.1s   |  %.4s   |  %.8s  \n", stack + 1, depth, 
									(int)stacks[stack].getStack().get(cargo).getWeight(), 
									stacks[stack].getStack().get(cargo).getStrength());
						else 
							System.out.printf("   %.1s   |   %.1s   |  %.4s  |  %.8s  \n", stack + 1, depth, 
									(int)stacks[stack].getStack().get(cargo).getWeight(), 
									stacks[stack].getStack().get(cargo).getStrength());
						totalWeight += stacks[stack].getStack().get(cargo).getWeight();
						cargoCount++;
					}
					depth++;
				}
			}
			System.out.println("\nTotal Count: " + cargoCount);
			System.out.println("Total Weight: " + (int)totalWeight);
			System.out.println();
			
		} else {
			System.out.println("Cargo '" + name + "' could not be found on the ship.\n");
		}
	}
	
	/**Prints a table of the stacks with their 
	 * 	stack number, depth, weight, and strength
	 */
	public void printAllCargoStacks() {
		if (!isEmpty()) {
			int depth = 0;
			System.out.println(" Stack   Depth   Weight   Strength");
			System.out.println("=======+=======+========+==========");
			for (int stack = 0; stack < stacks.length; stack++) {
				depth = 0;
				for (int cargo = stacks[stack].size() - 1; cargo >= 0; cargo--) {
					if (stacks[stack].getStack().get(cargo).getWeight() < 1000)
						System.out.printf("   %.1s   |   %.1s   |  %.4s   |  %.8s  \n", stack + 1, depth, 
								(int)stacks[stack].getStack().get(cargo).getWeight(), 
								stacks[stack].getStack().get(cargo).getStrength());
					else 
						System.out.printf("   %.1s   |   %.1s   |  %.4s  |  %.8s  \n", stack + 1, depth, 
								(int)stacks[stack].getStack().get(cargo).getWeight(), 
								stacks[stack].getStack().get(cargo).getStrength());
					depth++;
				}
			}
		} else {
			System.out.println("No Cargo on ship to print.\n");
		}
	}
}
