/**This is an Enum class which has 3 option values that determine the strength of the cargo: FRAGILE, MODERATE, STURDY
 * 
 * @author Pooja Ginjupalli
 */

enum CargoStrength {
	FRAGILE, MODERATE, STURDY;
	
	/**Returns the first letter of CargoStrength
	 * 
	 * @return
	 * 	Returns the first letter of the CargoStrength
	 * 	For example, if enum was 'FRAGILE', 'F' would be returned
	 */
	public String getFirstLetter() {
		return this.toString().substring(0, 1);
	}
}
