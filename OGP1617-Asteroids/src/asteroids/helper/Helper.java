package asteroids.helper;

import java.util.List;

/**
 * A helper class that includes tool box methods.
 */
public class Helper {
	
	/**
	 * Initialize a new Helper.
	 */
	public Helper() {};
	
	
	/**
	 * Converts a given List to an array.
	 * 
	 * @param 	list
	 * 			The list to be converted
	 * 
	 * @return	Returns the array made up by the elements in list.
	 */	
	public <T> Object[] convertListToArray(List<T> list) {
		Object[] result = new Object[list.size()];
		int count = 0;
		for (Object object: list) {
			result[count] = object;
			count += 1;
		}
		return result;
	}
	

	/**
	 * Calculates the cross product with a vector and itself.
	 * 
	 * @param 	vector1
	 * 		  	The vector to be used.
	 * 
	 * @return	Returns the cross product of the given vector with itself.
	 */
	public double evaluateScalar(double[] vector) {
		return Math.pow(vector[0], 2) + Math.pow(vector[1], 2);
	}
	
	/**
	 * Calculates the cross product between two vectors.
	 * 
	 * @param 	vector1
	 * 		  	The first vector to be used.
	 * @param 	vector2
	 * 		  	The second vector to be used.
	 * 
	 * @return	Returns the cross product of two given vectors.
	 */
	public double evaluateScalar(double[] vector1, double[] vector2) {
		return vector1[0]*vector2[0] + vector1[1]*vector2[1];
	}
	
}
