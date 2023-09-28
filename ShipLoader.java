/**This is an CargoShip class which holds the information 
 * 	of multiple stacks of Cargo
 * 
 * @author Pooja Ginjupalli
 */
import java.util.*;

public class ShipLoader {
	
	private static Scanner kb = new Scanner(System.in); //Scanner used for input
	private static CargoShip ship; //The ship which holds stacks of Cargo
	private static CargoStack dock = new CargoStack(); //Dock which holds cargo not on the ship
	
	public static void main(String args[]) throws CargoStrengthException, FullStackException, ShipOverweightException, EmptyStackException {
		Cargo newCargo;
		String menuOption;
		String cargoName;
		double cargoWeight;
		String cargoStrength;
		int stackIndex;
		int stackIndex2;
		CargoStrength newStrength = CargoStrength.FRAGILE;
		
		System.out.println("Welcome to ShipLoader!\n");
		System.out.println("Cargo Ship Parameters");
		System.out.println("--------------------------------------------------");
		
		getParameters();
		
		while (true) {
			System.out.print("Please select an option:\n"
					+ "C) Create new cargo\n"
					+ "L) Load cargo from ship\n"
					+ "U) Unload cargo from ship\n"
					+ "M) Move cargo between stacks\n"
					+ "K) Clear dock\n"
					+ "P) Print ship stacks\n"
					+ "S) Search for cargo\n"
					+ "Q) Quit\n"
					+ "\nSelect a menu option: ");
			menuOption = kb.nextLine().toUpperCase();
			
			switch (menuOption) {
			case "C": //User wishes to create and add Cargo to the dock
				System.out.print("\nEnter the name of the cargo: ");
				cargoName = kb.nextLine();
				
				System.out.print("Enter the weight of the cargo: ");
				try {
					cargoWeight = Double.parseDouble(kb.nextLine());
				} catch (NumberFormatException e){
					System.out.println("\nInvalid input.\n");
					break;
				}
				
				System.out.print("Enter the container strength (F/M/S): ");
				cargoStrength = kb.nextLine();
				
				if (!cargoStrength.equalsIgnoreCase("F") && !cargoStrength.equalsIgnoreCase("M") 
						&& !cargoStrength.equalsIgnoreCase("S")) {
					System.out.println("Invalid input.\n");
					break;
				} else if (cargoStrength.equalsIgnoreCase("F")) {
					newStrength = CargoStrength.FRAGILE;
				} else if (cargoStrength.equalsIgnoreCase("M")){
					newStrength = CargoStrength.MODERATE;
				} else if (cargoStrength.equalsIgnoreCase("S")) {
					newStrength = CargoStrength.STURDY;
				}
				
				try {
					newCargo = new Cargo(cargoName, cargoWeight, newStrength);
					dock.push(newCargo);
					System.out.println("\nCargo '" + cargoName + "' pushed onto the dock.\n");
					printShip();
				} catch (IllegalArgumentException e) {
					System.out.println("\nOperation Failed! Illegal weight for cargo.\n");
					break;
				} catch (CargoStrengthException e) {
					System.out.println("\nOperation Failed! Cargo at top of stack cannot support weight.\n");
					break;
				}
				
				break;
				
			case "L": //User wishes to load a Cargo from the dock onto desired stack
				System.out.print("\nSelect the load destination stack index: ");
				try {
					stackIndex = Integer.parseInt(kb.nextLine());
					newCargo = dock.peek();
					ship.pushCargo(newCargo, stackIndex);
					dock.pop();
					System.out.println("\nCargo '" + newCargo.getName() + "' moved from dock to stack " + stackIndex + ".\n");
					printShip();
				} catch (NumberFormatException e) {
					System.out.println("\nInvalid input.\n");
					break;
				} catch (IllegalArgumentException e) {
					System.out.println("\nInvalid input.\n");
					break;
				} catch (FullStackException e) {
					System.out.println("\nOperation failed! Cargo stack is at maximum height.\n");
					break;
				} catch (ShipOverweightException e) {
					System.out.println("\nOperation failed! Cargo would put ship overweight.\n");
					break;
				} catch (CargoStrengthException e) {
					System.out.println("\nOperation failed! Cargo at top of stack cannot support weight.\n");
					break;
				} catch (EmptyStackException e) {
					System.out.println("\nOperation failed! No cargo at dock to load.\n");
					break;
				}
				
				break;
				
			case "U": //The User wishes to unload a Cargo from a ship stack onto the dock
				System.out.print("\nSelect the unload source stack index: ");
				try {
					stackIndex = Integer.parseInt(kb.nextLine());
					newCargo = ship.getCargoStack(stackIndex).peek();
					dock.push(newCargo);
					ship.popCargo(stackIndex);
					System.out.println("Cargo '" + newCargo.getName() + "' moved from stack " + stackIndex + " to dock.\n");
					printShip();
				} catch (NumberFormatException e) {
					System.out.println("\nInvalid input.\n");
					break;
				} catch (IllegalArgumentException e) {
					System.out.println("\nInvalid input.\n");
					break;
				} catch (EmptyStackException e) {
					System.out.println("\nOperation failed! No cargo at stack to unload.\n");
					break;
				} catch (CargoStrengthException e) {
					System.out.println("\nOperation failed! Cargo at top of stack cannot support weight.\n");
					break;
				}
				
				break;
				
			case "M": //The User wishes to move a Cargo from one stack to another on the ship
				System.out.print("\nSource stack index: ");
				try {
					stackIndex = Integer.parseInt(kb.nextLine());
					System.out.print("Destination stack index: ");
					stackIndex2 = Integer.parseInt(kb.nextLine());
					newCargo = ship.getCargoStack(stackIndex).peek();
					ship.pushCargo(newCargo, stackIndex2);
					ship.popCargo(stackIndex);
					System.out.println("\nCargo '" + newCargo.getName() + "' moved "
							+ "from stack " + stackIndex + " to stack " + stackIndex2 + ".\n");
					printShip();
					
				} catch (NumberFormatException e) {
					System.out.println("\nInvalid input.\n");
					break;
				} catch (IllegalArgumentException e) {
					System.out.println("\nInvalid input.\n");
					break;
				} catch (EmptyStackException e) {
					System.out.println("\nOperation failed! No cargo at stack to unload.\n");
					break;
				} catch (CargoStrengthException e) {
					System.out.println("\nOperation failed! Cargo at top of stack cannot support weight.\n");
					break;
				} catch (FullStackException e) {
					System.out.println("\nOperation failed! Cargo stack is at maximum height.\n");
					break;
				}
				
				break;
				
			case "K": //The User wishes to empty the dock of all Cargos
				clearDock();
				printShip();
				
				break;
				
			case "P": //The User wishes to print all the Cargos on the ship
				System.out.println();
				ship.printAllCargoStacks();
				System.out.println();
				break;
				
			case "S": //The User wishes to find and print Cargo on the ship
				System.out.print("\nEnter the name of the cargo: ");
				cargoName = kb.nextLine();
				
				System.out.println();
				ship.findAndPrint(cargoName);
				
				break;
				
			case "Q": //User wishes to terminate the program
				System.out.println("\nProgram terminating normally...");
				System.exit(0);
				break;
				
			default: //User enters an invalid menu option
				System.out.println("\nInvalid option");
			}
		}
	}
	
	/**Creates a CargoShip object based on the inputs of the User
	 * 
	 * @Preconditions:
	 * 	Scanner has been previously declared and initizalied
	 * 	CargoShip has been previously declated but not initizalized
	 * 
	 * @Postconditions:
	 * 	CargoShip has been initialized to an empty ship
	 */
	public static void getParameters() {
		boolean validInput = false;
		int stacks, height;
		double weight;
		while (validInput == false) {
			try {
				System.out.print("Number of stacks: ");
				stacks = Integer.parseInt(kb.nextLine());
				
				System.out.print("Maximum height of stacks: ");
				height = Integer.parseInt(kb.nextLine());;
				
				System.out.print("Maximum total cargo weight: ");
				weight = Double.parseDouble(kb.nextLine());
				
				ship = new CargoShip(stacks, height, weight);
				
				System.out.println("\nCargo ship created."
						+ "\nPulling ship in to dock..."
						+ "\nCargo ship ready to be loaded.\n");

				validInput = true;
			} catch (NumberFormatException e){
				System.out.println("Sorry, that's not a valid input.\n");
			} catch (IllegalArgumentException e){
				System.out.println("Sorry, that's not a valid input.\n");
			}
		}
	}

	/**Prints a representation of the ship's stacks 
	 * 	and the dock with the strengths of each Cargo listed
	 * 
	 */
	public static void printShip() {
		for (int stack = 1; stack <= ship.getNumStacks(); stack++) {
			System.out.print("Stack " + stack + ": ");
			for (int x = 0; x < ship.getCargoStack(stack).size(); x++) {
				System.out.print(ship.getCargoStack(stack).getStack().get(x).getStrength().getFirstLetter());
				if (x != ship.getCargoStack(stack).getStack().size() - 1) {
					System.out.print(", ");
				}
			}
			System.out.println();
		}
		
		System.out.print("Dock: ");
		for (int x = 0; x < dock.getStack().size(); x++) {
			System.out.print(dock.getStack().get(x).getStrength().getFirstLetter());
			if (x != dock.getStack().size() - 1) {
				System.out.print(", ");
			}
		}
		System.out.println("\n\nTotal Weight = " + (int)ship.getTotalWeight() + " / " + (int)ship.getMaxWeight() + "\n\n");
	}
	
	public static void clearDock() throws EmptyStackException {
		int numCargo = dock.size();
		for (int x = 0; x < numCargo; x++) 
			dock.pop();
		System.out.println("\nDock cleared.\n");
	}
}
