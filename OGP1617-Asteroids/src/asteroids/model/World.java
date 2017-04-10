package asteroids.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import asteroids.helper.Entity;
import asteroids.helper.Helper;
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
	
	
	
	private Helper helper = new Helper();
	
	/**
	 * Initialize this new world with given width and height.
	 * 
	 * @param  	width
	 *         	The width for this new world.
	 * @param	height
	 * 			The height for this new world.
	 * 
	 * @post	TODO see implementation ?
	 *       	| if (isValidSize(width, height))
	 *       	|   	new.getWidth() == width
	 *       	|		new.getHeight() == height
	 *       	| else  new.getWidth() == calculateDefaultSiz(width, height)[0]
	 *       	|		new.getHeight() == calculateDefaultSiz(width, height)[1]
	 */
	public World(double width, double height) {
		double[] temp = {width, height};
		if (! isValidSize(width, height)) temp = calculateDefaultSize(width, height);
		this.width = temp[0];
		this.height = temp[1];
	}
	
	
	/**
	 * Variable registering if this world is terminated or not.
	 */
	private boolean isTerminated = false;
	
	
	/**
	 * Return the whether or not this world is terminated.
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
	public void terminate() {
		for (Entity entity: entities.values()) 
			removeEntity(entity);
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
	 * @see implementation
	*/
	@Raw
	public static boolean isValidSize(double width, double height) {
		return ( ( (width >= 0) && (width <= getUpperBound()) ) && ( (height >= 0) && (height <= getUpperBound()) ));
	}
	
	
	/**
	 * 
	 * @param 	newUpperBound
	 * 			The new upper bound for the width and height for every world.
	 * 
	 * @see implementation
	 */
	// TODO Should this method be public?
	// TODO Is this raw
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
	 * @see implementation
	 */
	@Raw
	public boolean canHaveAsEntity(Entity entity) {
		if ( (!containsEntity(entity)) && (entity.canHaveAsWorld(this)) ) return true;
		return false;
	}	
	
	/**
	 * Check whether a given in entity is currently in this world.
	 * 
	 *@param	entity
	 * 			The entity to be checked.
	 * 
	 * @see implementation
	 */
	@Raw
	public boolean containsEntity(Entity entity) {
        try {
           return entities.get(entity.getPosition()) == entity;
        }
        catch (NullPointerException exc) {
//             //Because the given purchase is raw, it is possible that
//             //it does not yet reference an effective share.
//          assert (purchase == null) || (purchase.getShare() == null);
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
		// The world will always have final say as to what entities are in it.
		// The entity can only set and de-set worlds, but not recognize itself as actually being in a world.
		// First we check if this world can have a given entity as entity, this will also check if the
		// entity can have this world as world.
		if (!canHaveAsEntity(entity)) {
			// At this point we're sure that the entity can possibly be added to this world,
			// the only thing that can go wrong now is if the entity is already in a world.
			try {
				// We set the current world of the entity to this.
				entity.setWorld(this);
			}
			catch (IllegalArgumentException exc) {
				throw new IllegalArgumentException();
			}
			// Finally we can add the entity to the entities list.
			entities.put(entity.getPosition(), entity);
		}
		else throw new IllegalArgumentException();
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
		// The world will always have final say as to what entities are in it.
		// The entity can only set and de-set worlds, but not recognize itself as actually being in a world.
		// Removing an entity is simpler than adding an entity. We only have to check if
		// the entity is actually in the world and the world is  the current world of the entity.
		if ( (containsEntity(entity)) && (entity.getWorld() == this) ) {
			// Now we know that we're in a valid situation and can break the association.
			entity.deSetWorld();
			entities.remove(entity.getPosition());
		}
		else throw new IllegalArgumentException();
	}
	
	
	
		/*
	     * |--------------------------------|
	     * | #Header-3# Advanced Methods.	|
	     * |--------------------------------| 
	     */



			/*
			 * |------------------------------------------------|
			 * | 4. The next methods handle evolving the world.	|
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
		Entity[] collisionEntitiesMin = calculateFirstCollision();
		if ( collisionEntitiesMin[0] == collisionEntitiesMin[1] )
			collisionTimeMin = collisionEntitiesMin[0].getTimeToCollision(this);
		else
			collisionTimeMin = collisionEntitiesMin[0].getTimeToCollision(collisionEntitiesMin[1]);
	
		if ( collisionTimeMin < time) {
			update(collisionTimeMin);
			// Resolve Collision
			evolve(time-collisionTimeMin);
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
		for (Entity entity: entities.values()) {
			this.removeEntity(entity);
			entity.move(time);
			this.addEntity(entity);
		}
	}
	
	
	/**
	 * Calculate the first collision in the current world.
	 */
	public Entity[] calculateFirstCollision() {
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
		}
		
		for (Entity entity: entities.values()) {
			double collisionTimeTemp = entity.getTimeToCollision(this);
			if (collisionTimeTemp < collisionTimeMin) {
				collisionTimeMin = collisionTimeTemp;
				collisionEntitiesMin[0] = entity;
				collisionEntitiesMin[1] = entity;
			}
		}
		
		return collisionEntitiesMin;
	}
	
	
		
		/*
	     * |----------------------------|
	     * | #Header-4# Query Methods.	|
	     * |----------------------------| 
	     */
	
	
	
			/*
			 * |----------------------------------------------------|
			 * | 5. The next methods handle queries of the world.	|
			 * |----------------------------------------------------| 
			 */

	
	
	/**
	 * Returns the entity that is registered with the given position.
	 * 
	 * @param	position
	 * 			The position of the entity to be returned.
	 * 
	 * @see implementation
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
	 * 
	 * @see implementation
	 */
	public Entity[] getAllEntities() {
		Entity[] entitiesResult = new Entity[entities.size()];
		int current = 0;
		for (Entity entity: entities.values()) {
			entitiesResult[current] = entity;
			current += 1;
		}
		return entitiesResult;
	}
	
	/**
	 * Returns all ships registered in this world.
	 * 
	 * @see implementation
	 */
	public Ship[] getAllShips() {
		// First get all the entities registered in this world
		Entity[] entitiesResult = getAllEntities();
		
		// Create a temporary List that will include all the bullet entities.
		// We have to create this list because we have no idea how many bullets are
		// in the total amount of entities.
		List<Ship> shipsResult = new ArrayList<Ship>();
		for (int i = 0; i < entitiesResult.length; i++) 
			if (entitiesResult[i].getType() == "Ship") shipsResult.add((Ship)entitiesResult[i]);
		
		// Convert the List to an array using the helper class.
		return (Ship[])helper.convertListToArray(shipsResult);
	}
	
	/**
	 * Returns all bullets registered in this world.
	 * 
	 * @see implementation
	 */
	public Bullet[] getAllBullets() {
		// First get all the entities registered in this world
		Entity[] entitiesResult = getAllEntities();
		
		// Create a temporary List that will include all the bullet entities.
		// We have to create this list because we have no idea how many bullets are
		// in the total amount of entities.
		List<Bullet> bulletsResult = new ArrayList<Bullet>();
		for (int i = 0; i < entitiesResult.length; i++) 
			if (entitiesResult[i].getType() == "Bullet") bulletsResult.add((Bullet)entitiesResult[i]);
		
		// Convert the List to an array using the helper class.
		return (Bullet[])helper.convertListToArray(bulletsResult);
	}
	
}
