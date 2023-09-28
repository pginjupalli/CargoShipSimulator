/**This is an Cargo class which holds the information of a cargo crate
 * 
 * @author Pooja Ginjupalli
 */

public class Cargo {
	private final String name; //The name of the Cargo
	private final double weight; //The weight of the Cargo
	private final CargoStrength strength; //The Strength of the Cargo
	
	/**Creates an instance of Cargo with a name, weight, and strength level
	 * 
	 * @param initName
	 * 	The name to be given to Cargo
	 * 
	 * @param initWeight
	 * 	The weight to be given to Cargo
	 * 
	 * @param initStrength
	 * The strength level to be given to Cargo
	 * 
	 * @Preconditions:
	 * 	initData is not null
	 * 	initWeight is greater than 0
	 * 
	 * @Postcondition:
	 * 	Cargo has been initialized to have an immutable name, weight, and strength
	 * 
	 * @exception IllegalArgumentException
	 * 	Indicated initData is null or initWeight is less than or equal to 0
	 * 
	 */
	public Cargo(String initName, double initWeight, CargoStrength initStrength) {
		if (initName == null || initWeight <= 0)
			throw new IllegalArgumentException();
		
		name = initName;
		weight = initWeight;
		strength = initStrength;
	}
	
	/**Returns name of Cargp
	 * 
	 * @return
	 * 	Returns the name variable
	 */
	public String getName() {
		return name;
	}
	
	/**Returns weight of Cargp
	 * 
	 * @return
	 * 	Returns the weight variable
	 */
	public double getWeight() {
		return weight;
	}
	
	/**Returns strength of Cargp
	 * 
	 * @return
	 * 	Returns the strength variable
	 */
	public CargoStrength getStrength() {
		return strength;
	}
	
	
}
