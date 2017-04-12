package asteroids.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import asteroids.model.World;

/*
 * Methods Index:
 * 	1. Methods that handle the Initialization of the Helper
 * 	2. Generic tool-box methods
 * 	3. Approximation Methods
 */

/**
 * A helper class that includes tool-box methods such as convert a list to an array,
 * calculate a position, evaluate the cross (scalar) product. This class also
 * includes the requested approximation methods.
 */
public class Helper {
	
			/*
		     * |----------------------------------------------------------------|
		     * | 1. The next method handles the Initialization of the Helper.	|
		     * |----------------------------------------------------------------| 
		     */
	
	
	
	/**
	 * Initializes a new helper.
	 */
	public Helper() {};
	
	
	
			/*
		     * |----------------------------------------------------|
		     * | 2. The next methods are generic tool-box methods.	|
		     * |----------------------------------------------------| 
		     */

	
	
	/**
	 * Calculates the position of an entity after a given time.
	 * The result is not returned as a Position Object because this
	 * method should not throw an exception if the position is not valid.
	 * 
	 * @param 	entity
	 * 		  	The entity to be used.
	 * @param 	time
	 * 			The time to be used.
	 * 
	 * @return	the position after the given time.
	 */
	public double[] calculatePosition(Entity entity, double time) {
		double[] position = {entity.getPosition().getPositionX() + entity.getVelocityX() * time,
						   	 entity.getPosition().getPositionY() + entity.getVelocityY() * time};
		return position;
	}
	
	
	/**
	 * Converts a given List to an Array.
	 * 
	 * @param 	list
	 * 			The list to be converted
	 * 
	 * @return	Returns an array made up by the elements in list.
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
	 * Converts a given Set to a List.
	 * 
	 * @param 	list
	 * 			The list to be converted
	 * 
	 * @return	Returns an array made up by the elements in list.
	 */
	public <T> List<T> convertSetToList(Set<T> set) {
		List<T> list = new ArrayList<T>();
		for (T item: set) list.add(item);
		return list;
	}
	
	/**
	 * Calculates the cross product with a vector and itself.
	 * 
	 * @param 	vector
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
	
	
	
			/*
			 * |------------------------------------------------|
		     * | 3. The next methods are approximation methods.	|
		     * |------------------------------------------------| 
		     */
	
	
	
	/**
	 * Calculates if two entities overlap significantly.
	 * 
	 * @param 	entity1
	 * 		  	The first entity to be used.
	 * @param 	entity2
	 * 		  	The second entity to be used.
	 * @param	distance
	 * 			The distance between the centers of the entities.
	 * 
	 * @return	true if they overlap significantly, false if they do not.
	 */
	public boolean significantOverlap(Entity entity1, Entity entity2, double distance) throws IllegalArgumentException {
		if ( (entity1 == null) || (entity2 == null) ) throw new IllegalArgumentException();
		return distance <= (0.99)*(entity1.getRadius() + entity2.getRadius());
	}
	
	
	/**
	 * Calculates if an entity is apparently within the boundaries of a world.
	 * 
	 * @param 	entity
	 * 		  	The entity to be used.
	 * @param 	world
	 * 		  	The world to be used.
	 * 
	 * @return	true if the entity is apparently in the boundaries, false if it is not.
	 */
	public boolean apparentlyWithinBoundaries(Entity entity, World world) throws IllegalArgumentException {
		if ( (entity == null) || (world == null) ) throw new IllegalArgumentException();
		return ( (entity.getPosition().getPositionX() - entity.getRadius() * 0.99 >= 0) &&
				 (entity.getPosition().getPositionX() + entity.getRadius() * 0.99 <= world.getWidth()) &&
				 (entity.getPosition().getPositionY() - entity.getRadius() * 0.99 >= 0) &&
				 (entity.getPosition().getPositionY() + entity.getRadius() * 0.99 <= world.getHeight()) );
	}
	
	
	/**
	 * Calculates if an entity is apparently within the boundaries of a world.
	 * 
	 * @param 	entityContained
	 * 		  	The entity to be checked if it lies in the boundaries of the other entity.
	 * @param 	entityContainer
	 * 		  	The entity to be checked if it lies in the boundaries of the other entity.
	 * 
	 * @return	true if the entity is apparently in the boundaries, false if it is not.
	 */
	public boolean apparentlyWithinBoundaries(Entity entityContained, Entity entityContainer) throws IllegalArgumentException {
		if ( (entityContained == null) || (entityContainer == null) ) throw new IllegalArgumentException();
		return ( (entityContained.getPosition().getPositionX() - entityContained.getRadius() * 0.99 >= 
				entityContainer.getPosition().getPositionX() - entityContainer.getRadius()) &&
				 (entityContained.getPosition().getPositionX() + entityContained.getRadius() * 0.99 <= 
				entityContainer.getPosition().getPositionX() + entityContainer.getRadius()) &&
				 (entityContained.getPosition().getPositionY() - entityContained.getRadius() * 0.99 >= 
				entityContainer.getPosition().getPositionY() - entityContainer.getRadius()) &&
				 (entityContained.getPosition().getPositionY() + entityContained.getRadius() * 0.99 <= 
				entityContainer.getPosition().getPositionY() + entityContainer.getRadius()) );
	}
	
	
	/**
	 * Calculates if two entities apparently collide.
	 * 
	 * @param 	entity1
	 * 		  	The first entity to be used.
	 * @param 	entity2
	 * 		  	The second entity to be used.
	 * @param	distance
	 * 			The distance between the centers of the entities.
	 * 
	 * @return	true if they apparently collide, false if they do not.
	 */
	public boolean apparentlyCollide(Entity entity1, Entity entity2, double distance) throws IllegalArgumentException {
		if ( (entity1 == null) || (entity2 == null) ) throw new IllegalArgumentException();
		return ( distance >= (0.99) * (entity1.getRadius() + entity2.getRadius()) ) && 
				( distance <= (1.01) * (entity1.getRadius() + entity2.getRadius()) ); 
	}
	
}
