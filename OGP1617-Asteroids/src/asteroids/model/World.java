package asteroids.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import asteroids.helper.Entity;
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
 * 		4. Methods that handle Collision Detection 
 * 		5. Methods that handle evolving the world
 * #4# Query Methods
 * 		6. Methods that handle queries of the world
 */

/**
 * A class of worlds. //TODO Do we need to keep all these invariants? + Any more?
 * 
 * @invar  	The size of each world must be a valid size for any
 *         	world.
 *       	| isValidSize(getWidth(), getHeight())
 *       
 * @author	Mathijs Hubrechtsen
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
		return (entity != null) && (!containsEntity(entity)) && (entity.canHaveAsWorld(this));
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
	public void addEntity(Entity entity) throws IllegalArgumentException, NullPointerException {
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
	public void removeEntity(Entity entity) throws IllegalArgumentException, NullPointerException {
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
	 * 			of this world. If a boundary is involved the entities returned
	 * 			will be the entity involved in the boundary collision and itself.
	 * 			// TODO Declarative Documentation.
	 */
	@Raw
	public Entity[] getFirstCollisionEntities() {
		double collisionTimeMin = -1;
		Entity[] collisionEntitiesMin = new Entity[2];
		
		for (Entity entity1: entities.values()) {
			for (Entity entity2: entities.values()) {
				if (entity1 != entity2) {
					double collisionTimeTemp = entity1.getTimeToCollision(entity2);
					if ( (collisionTimeTemp < collisionTimeMin) || (collisionTimeMin == -1 ) ) {
						collisionTimeMin = collisionTimeTemp;
						collisionEntitiesMin[0] = entity1;
						collisionEntitiesMin[1] = entity2;
					}
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
	 * 			// TODO Boundaries
	 */
	@Raw
	public double[] getFirstCollisionPosition() {
		Entity[] collisionEntities = getFirstCollisionEntities();
		if (collisionEntities[0] == collisionEntities[1])
			return collisionEntities[0].getCollisionPosition(this);
		else
			return getFirstCollisionEntities()[0].getCollisionPosition(getFirstCollisionEntities()[1]);
	}
	
	/**
	 * Gets the time of the first collision in the current world.
	 * 
	 * @return	Returns the time of the first collision
	 * 			of this world.
	 * 			// TODO Declarative Documentation.
	 */
	@Raw
	public double getTimeToFirstCollision() {
		double collisionTimeMin = -1;
		for (Entity entity1: entities.values()) {
			for (Entity entity2: entities.values()) {
				if (entity1 != entity2) {
					double collisionTimeTemp = entity1.getTimeToCollision(entity2);
					if ( (collisionTimeTemp < collisionTimeMin) || (collisionTimeMin == -1 ) ) collisionTimeMin = collisionTimeTemp;
				}
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
	public void evolve(double time) throws IllegalArgumentException {
		if (time < 0) throw new IllegalArgumentException();
		Entity[] collisionEntitiesMin = getFirstCollisionEntities();
		if (collisionEntitiesMin == null) resolveEvolve(-1, collisionEntitiesMin, time);	// There are no entities in this World.
		
		double collisionTimeMin;
		if ( collisionEntitiesMin[0] == collisionEntitiesMin[1] )	// If the collision is with the boundary.
			collisionTimeMin = collisionEntitiesMin[0].getTimeToCollision(this);
		else	// If the collision is between 2 entities.
			collisionTimeMin = collisionEntitiesMin[0].getTimeToCollision(collisionEntitiesMin[1]);
		
		try {	// This is the only possible exception that is thrown in this method (outside of the one for the parameter);
			resolveEvolve(collisionTimeMin, collisionEntitiesMin, time); // All other exceptions will never occur in the execution of this method
		}																 // "All other" = the ones in the method calls of this method.
		catch (IllegalArgumentException exc) {// This exception is throw if the new position of an entity is invalid
			throw new IllegalArgumentException();	// or if time is < 0.
		}
	}
	
	
	/**
	 * Resolves the evolution method.
	 * Helper method of evolve().
	 * 
	 * @param	collisionTimeMin
	 * 			The time to the first collision.
	 * @param	collisionEntitiesMin
	 * 			The entities involved in the first collision.
	 * @param	time
	 * 			the time over which the world has to be evolved.
	 * 
	 */
	private void resolveEvolve(double collisionTimeMin, Entity[] collisionEntitiesMin, double time) 
			throws IllegalArgumentException {
		if ( (0 < collisionTimeMin) && (collisionTimeMin < time) ) {	// Collision time needs to be positive.-1 is also used to
			try {														// shortcut immediately from this comparison to the end.
				update(collisionTimeMin);
			}
			catch (IllegalArgumentException exc) {// This exception is throw if the new position of an entity is invalid
				throw new IllegalArgumentException();	// or if time is < 0.
			}
			
			if ( collisionEntitiesMin[0] == collisionEntitiesMin[1] )	// If the collision is with the boundary
				collisionEntitiesMin[0].resolveCollision(this);
			else	// If the collision is between 2 entities.
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
	private void update(double time) throws IllegalArgumentException {
		List<Entity> entitiesList = new ArrayList<Entity>();
		for (Entity entity: entities.values()) entitiesList.add(entity);	// Creates an iterator that the next
		for (Entity entity: entitiesList) {									// for loop can iterate over, otherwise the map might
			this.removeEntity(entity);										// be changed during execution.
			entity.setWorld(null);
			try {	// The exceptions in removeEntity are never thrown in this method.
				entity.move(time);
			}
			catch (IllegalArgumentException exc) {	// This exception is throw if the new position of this entity is invalid
				throw new IllegalArgumentException();	// or if time is < 0.
			}
			this.addEntity(entity);	// These exceptions are also never thrown (because nothing moves out of bounds in this method).
			entity.setWorld(this);
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
	 * @return	Returns the entity at the given position. 
	 * 			Returns null if there is no entity at that position.
	 * 			// TODO Declarative Documentation.
	 */
	@Raw
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
	 * 
	 * @return	Returns all entities registered in this world.
	 * 			// TODO Declarative Documentation.
	 */
	@Raw
	public Set<Entity> getAllEntities() {
		Set<Entity> entitiesResult = new HashSet<Entity>();
		for (Entity entity: entities.values()) {
			entitiesResult.add(entity);
		}
		return entitiesResult;
	}
	
	/**
	 * Returns all ships registered in this world.
	 * 
	 * @return	Returns all ships registered in this world.
	 * 			// TODO Declarative Documentation.
	 */
	@Raw
	public Set<Ship> getAllShips() {
		Set<Entity> entitiesResult = getAllEntities();
		Set<Ship> shipsResult = new HashSet<Ship>();
		for (Entity entity: entitiesResult)
			if (entity.getType() == "Ship") shipsResult.add((Ship)entity);
		
		return shipsResult;
	}
	
	/**
	 * Returns all bullets registered in this world.
	 * 
	 * @return 	Returns all bullets registered in this world.
	 * 			// TODO Declarative Documentation.
	 */
	@Raw
	public Set<Bullet> getAllBullets() {
		Set<Entity> entitiesResult = getAllEntities();
		Set<Bullet> bulletsResult = new HashSet<Bullet>();
		for (Entity entity: entitiesResult)
			if (entity.getType() == "Bullet") bulletsResult.add((Bullet)entity);
		
		return bulletsResult;
	}
	
}
