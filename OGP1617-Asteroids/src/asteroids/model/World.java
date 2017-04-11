package asteroids.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import asteroids.helper.Entity;
//import asteroids.helper.Helper;
import asteroids.helper.Position;

import be.kuleuven.cs.som.annotate.*;

/* Constants:
*	1.	upperBound = the maximum height/width of a world.
*/

/*
 * Methods Index:
 * #1# Basic Methods
 * 		1. Methods that handle the Initialization of the World
 * 		2. Methods that handle the Size of the World
 * #2# Association Methods
 * 		3. Methods that handle the relation with Entities
 * #3# Advanced Methods
 * 		4. Methods that handle evolving the world
 * #4# Query Methods
 * 		5. Methods that handle queries of the world
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
	     * |----------------------------|
	     * | #Header-1# Basic Methods.	|
	     * |----------------------------| 
	     */
	
	
			/*
			 * |--------------------------------------------------------------------------------|
			 * | 1. The next method handles the Initialization and Termination of the World.	|
			 * |--------------------------------------------------------------------------------| 
			 */
	
	
	
	/**
	 * Initialize this new world with given width and height.
	 * 
	 * @param  	width
	 *         	The width for this new world.
	 * @param	height
	 * 			The height for this new world.
	 * 
	 * @see	implementation
	 * 
	 * @post    | if (isValidSize(width, height))
	 *       	|   	new.getWidth() == width
	 *       	|		new.getHeight() == height
	 *       	| else  new.getWidth() == this.calculateDefaultSiz(width, height)[0]
	 *       	|		new.getHeight() == this.calculateDefaultSiz(width, height)[1]
	 * 
	 * // TODO See implementation?
	 */
	public World(double width, double height) {
		double[] temp = {width, height};
		if (! isValidSize(width, height)) temp = calculateDefaultSize(width, height);
		this.width = temp[0];	// The size is hard coded because the width and height
		this.height = temp[1];	// cannot change after construction.
	}
	
	
	/**
	 * Variable registering if this world is terminated or not.
	 */
	private boolean isTerminated = false;
	
	
	/**
	 * Return whether or not this world is terminated.
	 */
	@Basic @Raw
	public boolean isTerminated() {
		return this.isTerminated;
	}
	
	
	/**
	 * Terminates this world. Breaks up any associations with entities.
	 * Prepares this object for the garbage collector.
	 * @see implementation
	 */
	@Raw
	public void terminate() {
		for (Entity entity: entities.values()) {
			try {
				this.removeEntity(entity);
				entity.setWorld(null);
			}
			catch (IllegalArgumentException exc) {}	// Empty catch because if an IllegalArgumentException
		}											// is thrown, it means that the association wasn't set to start with.		
		this.isTerminated = true;					// This means that the association already doesn't exist. We don't have to do anything.		
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
	private final double height;
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
	 * Check whether the given size is a valid size for
	 * any world.
	 *  
	 * @param	width
	 *       	The width to check.
	 * @param	height
	 * 			The height to check.
	 *         
	 * @return	Returns whether the given width and height make up a valid size or not.
	 * 			true if it is, false if not.
	 */
	@Raw
	public static boolean isValidSize(double width, double height) {
		return ( ( (width >= 0) && (width <= getUpperBound()) ) && ( (height >= 0) && (height <= getUpperBound()) ) );
	}
	
	
	/**
	 * Set the upper bound for the width and height of every entity to the given upper bound.
	 * 
	 * @param 	newUpperBound
	 * 			The new upper bound for the width and height for every world.
	 * 
	 * @see implementation
	 */
	// TODO Should this method be public?
	@Raw
	public void setUpperBound(double newUpperBound) {
		upperBound = newUpperBound;
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
		double[] result = {width, height};
		
		if ( (width < 0) || (java.lang.Double.isNaN(width)) ) result[0] = 0;
		else if (width > getUpperBound()) result[0] = getUpperBound();
		if ( (height < 0) || (java.lang.Double.isNaN(width)) ) result[1] = 0;
		else if (height > getUpperBound()) result[1] = getUpperBound();

		return result;
	}
	
	
	
		/*
	     * |------------------------------------|
	     * | #Header-2# Association Methods.	|
	     * |------------------------------------| 
	     */
	
	
			/*
			 * |------------------------------------------------------------|
			 * | 3. The next methods handle the relationship with Entities.	|
			 * |------------------------------------------------------------| 
			 */


	
	/**
	 * Map holding all entities of this world.
	 */
	private Map<Position, Entity> entities = new HashMap<Position, Entity>();

	
	/**
	 * Check whether a given in entity can be in this world.
	 * 
	 * @param	entity
	 * 			The entity to be checked.
	 * 
	 * @return 	Returns whether this world can have the given entity as entity.
	 * 			true if it can, false if not.
	 */
	@Raw
	public boolean canHaveAsEntity(Entity entity) {
		return (entity != null) && (!containsEntity(entity));
	}	
	
	/**
	 * Check whether a given in entity is currently in this world.
	 * 
	 * @param	entity
	 * 			The entity to be checked.
	 * 
	 * @return 	Returns whether this world already has the given entity in 
	 * 			it's collection of entities. true if it can, false if not.
	 */
	@Raw
	public boolean containsEntity(Entity entity) {
        try {
           return entities.get(entity.getPosition()) == entity;
        }
        catch (NullPointerException exc) {
            return false;
        }
	}
	

	/**
	 * Add a given entity to this world.
	 * 
	 * @param	entity
	 * 			The entity to be added.
	 * 
	 * @see implementation
	 */
	@Raw
	public void addEntity(Entity entity) throws NullPointerException, IllegalArgumentException {
		if (entity == null) throw new NullPointerException();
		if (! canHaveAsEntity(entity)) throw new IllegalArgumentException();
		entities.put(entity.getPosition(), entity);
	}
	
	/**
	 * Remove a given entity from this world.
	 * 
	 * @param	entity
	 * 			The entity to be removed.
	 * 
	 * @see implementation
	 */
	@Raw
	public void removeEntity(Entity entity) throws NullPointerException, IllegalArgumentException {
		if (entity == null) throw new NullPointerException();
		if (!containsEntity(entity)) throw new IllegalArgumentException();
		entities.remove(entity.getPosition());
	}
	
	
	
		/*
	     * |--------------------------------|
	     * | #Header-3# Advanced Methods.	|
	     * |--------------------------------| 
	     */


			/*
			 * |----------------------------------------------------|
			 * | 4. The next methods handle Collision Detection.	|
			 * |----------------------------------------------------| 
			 */



	/**
	* Gets the entities involved first collision in the current world.
	* 
	* @return	Returns the entities involved in the first collision
	* 			of this world.
	* 			// TODO Declarative Documentation.
	*/
	public Entity[] getFirstCollisionEntities() {
		double collisionTimeMin = -1;
		Entity[] collisionEntitiesMin = new Entity[2];
		
		for (Entity entity1: entities.values()) {
			for (Entity entity2: entities.values()) {
				double collisionTimeTemp = entity1.getTimeToCollision(entity2);
				if ( (collisionTimeTemp < collisionTimeMin) || (collisionTimeMin == -1 ) ) {
					collisionTimeMin = collisionTimeTemp;
					collisionEntitiesMin[0] = entity1;
					collisionEntitiesMin[1] = entity2;
				}
			}
			double collisionTimeTemp = entity1.getTimeToCollision(this);
			if (collisionTimeTemp < collisionTimeMin) {
				collisionTimeMin = collisionTimeTemp;
				collisionEntitiesMin[0] = entity1;
				collisionEntitiesMin[1] = entity1;
			}
		}
		
		return collisionEntitiesMin;
	}
	
	
	/**
	* Gets the position of the first collision in the current world.
	* 
	* @return	Returns the position of the first collision
	* 			of this world.
	* 			// TODO Declarative Documentation.
	*/
	public double[] getFirstCollisionPosition() {
		double[] vector = getFirstCollisionEntities()[0].getCollisionPosition(getFirstCollisionEntities()[1]);
		return vector;
	}
	
	/**
	* Gets the time of the first collision in the current world.
	* 
	* @return	Returns the time of the first collision
	* 			of this world.
	* 			// TODO Declarative Documentation.
	*/
	public double getTimeToFirstCollision() {
		double collisionTimeMin = -1;
		for (Entity entity1: entities.values()) {
			for (Entity entity2: entities.values()) {
				double collisionTimeTemp = entity1.getTimeToCollision(entity2);
				if ( (collisionTimeTemp < collisionTimeMin) || (collisionTimeMin == -1 ) ) collisionTimeMin = collisionTimeTemp;
			}
			double collisionTimeTemp = entity1.getTimeToCollision(this);
			if (collisionTimeTemp < collisionTimeMin) collisionTimeMin = collisionTimeTemp;
		}
		
		return collisionTimeMin;
	}
	
	

			/*
			 * |------------------------------------------------|
			 * | 5. The next methods handle evolving the world.	|
			 * |------------------------------------------------| 
			 */
	
	
	
	/**
	 * Evolve the current world.
	 * 
	 * @param	time
	 * 			the time over which the world will evolve.
	 */
	public void evolve(double time) {
		double collisionTimeMin;
		Entity[] collisionEntitiesMin = getFirstCollisionEntities();
		if ( collisionEntitiesMin[0] == collisionEntitiesMin[1] )
			collisionTimeMin = collisionEntitiesMin[0].getTimeToCollision(this);
		else
			collisionTimeMin = collisionEntitiesMin[0].getTimeToCollision(collisionEntitiesMin[1]);
	
		if ( collisionTimeMin < time) {
			update(collisionTimeMin);
			if ( collisionEntitiesMin[0] == collisionEntitiesMin[1] )
				collisionEntitiesMin[0].resolveCollision(this);
			else
				collisionEntitiesMin[0].resolveCollision(collisionEntitiesMin[1]);
			evolve(time - collisionTimeMin);
		}
		update(time);
	}
	
	
	/**
	 * Update the position of all elements in the current world.
	 * Helper method of evolve().
	 * 
	 * @param	time
	 * 			the time over which the world has to be updated.
	 */
	private void update(double time) {
		List<Entity> entitiesList = new ArrayList<Entity>();
		for (Entity entity: entities.values()) entitiesList.add(entity);
		for (Entity entity: entitiesList) {
			this.removeEntity(entity);
			entity.move(time);
			this.addEntity(entity);
		}
	}
	

	
		/*
	     * |----------------------------|
	     * | #Header-4# Query Methods.	|
	     * |----------------------------| 
	     */
	
	
	
			/*
			 * |----------------------------------------------------|
			 * | 6. The next methods handle queries of the world.	|
			 * |----------------------------------------------------| 
			 */

	
	
	/**
	 * Returns the entity that is registered with the given position.
	 * 
	 * @param	position
	 * 			The position of the entity to be returned.
	 * 
	 * @return	Returns the entitity at the given position.
	 */
	// TODO Is this @Basic? @Raw?
	public Entity getEntityAtPosition(Position position) {
		try {
			return entities.get(position);
		}
		catch (NullPointerException exc) {
			return null;
		}
	}
	
	/**
	 * Returns all entities registered in this world. 
	 * Return type is Entity[].
	 * @see implementation
	 */
	public Entity[] getAllEntitiesTemp1() {
		Entity[] entitiesResult = new Entity[entities.size()];
		int current = 0;
		for (Entity entity: entities.values()) {
			entitiesResult[current] = entity;
			current += 1;
		}
		return entitiesResult;
	}
	
	/**
	 * Returns all entities registered in this world.
	 * Return type is List<Entity>.
	 * @see implementation
	 */
	public List<Entity> getAllEntitiesTemp2() {
		List<Entity> entitiesResult = new ArrayList<Entity>();
		for (Entity entity: entities.values()) {
			entitiesResult.add(entity);
		}
		return entitiesResult;
	}
	
	/**
	 * Returns all entities registered in this world.
	 * Return type is Set<Entity>.
	 * @see implementation
	 */
	public Set<Entity> getAllEntities() {
		Set<Entity> entitiesResult = new HashSet<Entity>();
		for (Entity entity: entities.values()) {
			entitiesResult.add(entity);
		}
		return entitiesResult;
	}
	
	/**
	 * Returns all ships registered in this world.
	 * @see implementation
	 */
	public Set<Ship> getAllShips() {
		// First get all the entities registered in this world
//		Entity[] entitiesResult = getAllEntitiesTemp1();
//		List<Entity> entitiesResult = getAllEntitiesTemp2();
		Set<Entity> entitiesResult = getAllEntities();
		
		// Create a temporary List that will include all the bullet entities.
		// We have to create this list because we have no idea how many bullets are
		// in the total amount of entities.
//		List<Ship> shipsResult = new ArrayList<Ship>();
		Set<Ship> shipsResult = new HashSet<Ship>();
//		for (int i = 0; i < entitiesResult.length; i++) 
//			if (entitiesResult[i].getType() == "Ship") shipsResult.add((Ship)entitiesResult[i]);
		for (Entity entity: entitiesResult)
			if (entity.getType() == "Ship") shipsResult.add((Ship)entity);
		
		return shipsResult;
//		// Convert the List to an array using the helper class.
//		return (Ship[])helper.convertListToArray(shipsResult);
	}
	
	/**
	 * Returns all bullets registered in this world.
	 * @see implementation
	 */
	public Set<Bullet> getAllBullets() {
		// First get all the entities registered in this world
//		Entity[] entitiesResult = getAllEntitiesTemp();
//		List<Entity> entitiesResult = getAllEntitiesTemp2();
		Set<Entity> entitiesResult = getAllEntities();
		
		// Create a temporary List that will include all the bullet entities.
		// We have to create this list because we have no idea how many bullets are
		// in the total amount of entities.
//		List<Bullet> bulletsResult = new ArrayList<Bullet>();
		Set<Bullet> bulletsResult = new HashSet<Bullet>();
//		for (int i = 0; i < entitiesResult.length; i++) 
//			if (entitiesResult[i].getType() == "Bullet") bulletsResult.add((Bullet)entitiesResult[i]);
		for (Entity entity: entitiesResult)
			if (entity.getType() == "Bullet") bulletsResult.add((Bullet)entity);
		
		return bulletsResult;
//		// Convert the List to an array using the helper class.
//		return (Bullet[])helper.convertListToArray(bulletsResult);
	}
	
}
