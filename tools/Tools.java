/** This class provides some handy static methods for String formatting. Nothing that affects the game outcome in anyway, however ensures that words start with capital letter when they need to
 * and lower-case letters when they also need to.
 * 
 * Author: Chris Wing (cjmw2)
 * */

package tools;

public class Tools {

	/** Takes an input word and makes its first letter capital.
	 * */
	public static String firstLetterToCapital(String word) {
		
		return word.substring(0, 1).toUpperCase() + word.substring(1);
	}
	
	/** Takes an input word and makes its first letter lower-case.
	 * */
	public static String firstLetterToLower(String word) {
		
		return word.substring(0, 1).toLowerCase() + word.substring(1);
	}
}