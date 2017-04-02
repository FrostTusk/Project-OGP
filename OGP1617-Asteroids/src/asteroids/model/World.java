package asteroids.model;

import java.util.ArrayList;
import java.util.List;

import asteroids.helper.Entity;

import be.kuleuven.cs.som.annotate.*;

/*
 * Methods Index:
 * 1. Methods that handle the Initialization of the World
 * 2. Methods that handle the Size of the World
 * 3. Methods that handle the relation with Entities
 * 4. Methods that handle evolving the world
 */

/**
 * A class of worlds.
 * 
 * @invar  	The size of each world must be a valid size for any
 *         	world.
 *       	| isValidSize(getWidth(), getHeight())
 */
public class World {

		/*
		 * |----------------------------------------------------------------|
		 * | 1. The next method handles the Initialization of the World.	|
		 * |----------------------------------------------------------------| 
		 */
	
	
	
	/**
	 * Initialize this new world with given width and height.
	 * 
	 * @param  	width
	 *         	The width for this new world.
	 * @param	height
	 * 			The height for this new world.
	 * 
	 * @post   	If the given s is a valid s for any world,
	 *         	the s of this new world is equal to the given
	 *        	s. Otherwise, the s of this new world is equal
	 *        	to default_value_Java.
	 *       	| if (isValidPropertyName_Java(propertyName_Java))
	 *       	|   then new.getPropertyName_Java() == propertyName_Java
	 *       	|   else new.getPropertyName_Java() == default_value_Java
	 */
	public World(double width, double height) {
		double[] temp = {width, height};
		if (! isValidSize(width, height)) temp = calculateDefaultSize(width, height);
		this.width = temp[0];
		this.height = temp[1];
	}
	
	
	
			/*
			 * |----------------------------------------------------|
			 * | 2. The next methods handle the size of the World.	|
			 * |----------------------------------------------------| 
			 */
	
	
	
	/**
	 * Variable registering the width of this world.
	 */
	private final double width;
	/**
	 * Variable registering the height of this world.
	 */
	private double height;
	/**
	 * Variable registering the upper bound for every world.
	 */
	private static double upperBound = Double.MAX_VALUE;
	
	
	/**
	 * Return the width of this world.
	 */
	@Basic @Raw
	public double getWidth() {
		return this.width;
	}
	
	/**
	 * Return the height of this world.
	 */
	@Basic @Raw
	public double getHeight() {
		return this.height;
	}
	
	/**
	 * Return the height of this world.
	 */
	@Basic @Raw
	public static double getUpperBound() {
		return upperBound;
	}
	
	
	/**
	 * 
	 * @param 	newUpperBound
	 * 			The new upper bound for the widt and height for every world.
	 * 
	 * @see implementation
	 */
	public void setUpperBound(double newUpperBound) {
		upperBound = newUpperBound;
		
	}
	
	/**
	 * Check whether the given s is a valid s for
	 * any world.
	 *  
	 * @param	width
	 *       	The width to check.
	 * @param	height
	 * 			The height to check.
	 *         
	 * @return 
	 *       | result == 
	*/
	public static boolean isValidSize(double width, double height) {
		return ( ( (width >= 0) && (width <= getUpperBound()) ) && ( (height >= 0) && (height <= getUpperBound()) ));
	}
	
	
	/**
	 * Calculates the default size for a specific width and height.
	 * 
	 * @param	width
	 *       	The width for this world.
	 * @param	height
	 * 			The height for this world.
	 * 
	 * @see implementation
	 */
	public double[] calculateDefaultSize(double width, double height) {
		double[] result = new double[2];
		
		if ( (width < 0) || (java.lang.Double.isNaN(width)) ) result[0] = 0;
		else result[0] = getUpperBound();
		if ( (height < 0) || (java.lang.Double.isNaN(width)) ) result[1] = 0;
		else result[1] = getUpperBound();

		return result;
	}
	
	
	
			/*
			 * |------------------------------------------------------------|
			 * | 3. The next methods handle the relationship with Entities	|
			 * |------------------------------------------------------------| 
			 */


	/**
	 * List holding all entities of this world.
	 */
	private List<Entity> entities = new ArrayList<Entity>();
	
	
	public boolean containsEntity(Entity entity) {
		return entities.contains(entity);
	}
	
	
	public boolean canHaveAsEntity(Entity entity) {
		if (!entities.contains(entity)) return true;
		return false;
	}
	
	
	public void addEntity(Entity entity) throws NullPointerException, IllegalArgumentException {
		if (entity == null) throw new NullPointerException();
		if (!canHaveAsEntity(entity)) {
			try {
				entity.setWorld(this);
			}
			catch (IllegalArgumentException exc) {
				throw new IllegalArgumentException();
			}
			entities.add(entity);
		}
		else throw new IllegalArgumentException();
	}
	
	
	public void removeEntity(Entity entity) throws NullPointerException, IllegalArgumentException {
		if (entity == null) throw new NullPointerException();
		if ( (containsEntity(entity)) && (entity.getWorld() == this) ) {
			entities.remove(entity);
			entity.deSetWorld();
		}
		else throw new IllegalArgumentException();
	}
	
	
	
			/*
			 * |------------------------------------------------|
			 * | 4. The next methods handle evolving the world.	|
			 * |------------------------------------------------| 
			 */
	
	
	
	public void evolve(double time) {
		Entity[] collisionEntitiesMin = calculateFirstCollision();
		double collisionTimeMin = collisionEntitiesMin[0].getTimeToCollision(collisionEntitiesMin[1]);
		if ( collisionTimeMin < time) {
			update(collisionTimeMin);
			// Resolve Collision
			evolve(time-collisionTimeMin);
		}
		update(time);
	}
	
	
	public void update(double time) {
		for (Entity entity: entities) {
			System.out.println(entity);
		}
	}
	
	
	public Entity[] calculateFirstCollision() {
		double collisionTimeMin = -1;
		Entity[] collisionEntitiesMin = new Entity[2];
		
		for (Entity entity1: entities) {
			for (Entity entity2: entities) {
				double collisionTimeTemp = entity1.getTimeToCollision(entity2);
				if ( (collisionTimeTemp < collisionTimeMin) || (collisionTimeMin == -1 ) ) {
					collisionTimeMin = collisionTimeTemp;
					collisionEntitiesMin[0] = entity1;
					collisionEntitiesMin[1] = entity2;
				}
			}
		}
		//TODO check if entities collide with borders.
		return collisionEntitiesMin;
	}
	
}
