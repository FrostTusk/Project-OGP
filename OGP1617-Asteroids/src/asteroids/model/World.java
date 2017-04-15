package asteroids.model;

import java.util.*;
import asteroids.helper.*;
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
 * 		5. Helper Methods for Collision Detection
 * #4# Evolve Methods 
 * 		6. Methods that handle evolving the world
 * 		7. Helper Methods for evolving the world
 * #5# Query Methods
 * 		8. Methods that handle queries of the world
 * #6# Other Methods
 *		9. Helper Methods
 */

/**
 * A class of worlds. //TODO Any more?
 * 
 * @invar   | isValidSize(getWidth(), getHeight())
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
	 * Variable registering the helper for this ship.
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
	 * @see	implementation	// TODO Is this ok for cconstructors?
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
//		for (Entity entity: entities.values()) {
			try {
				this.removeEntity(entity);
				entity.setWorld(null);
			}
			catch (IllegalArgumentException exc) {}	// Empty catch because if an IllegalArgumentException
		}											// is thrown, it means that the association wasn't set to begin with.		
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
		return ( (width >= 0) && (width <= getUpperBound()) && (height >= 0) && (height <= getUpperBound()) );
	}
	
	
	/**
	 * Set the upper bound for the width and height of every entity to the given upper bound.
	 * 
	 * @param 	newUpperBound
	 * 			The new upper bound for the width and height for every world.
	 * 
	 * @see implementation
	 */
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
//	private Map<Position, Entity> entities = new HashMap<Position, Entity>();
	private Map<String, Entity> entities = new HashMap<String, Entity>();	// TODO Use this implementation!
	
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
		if (entity == null) return false;
//		if (helper.operlapsWithOtherEntities(entity, helper.convertCollectionToList(entities.values()))) 
		if (helper.operlapsWithOtherEntities(entity, helper.convertCollectionToList(entities.values()))) 
			return false;
		return (entity != null) && (!entity.isTerminated()) && (!containsEntity(entity)) && (entity.isInWorld(this));
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
//           return entities.get(entity.getPosition()) == entity;
            return entities.get(helper.convertPositionToString(entity.getPosition())) == entity;
        }
        catch (NullPointerException exc) {
            return false;
        }
	}
	

	/**
	 * Add a given entity to this world. This does set 
	 * the world of the entity to this world.
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
//		entities.put(entity.getPosition(), entity);
		entities.put(helper.convertPositionToString(entity.getPosition()), entity);
		try {
			entity.setWorld(this);
		}
		catch (IllegalArgumentException exc) {
			throw new IllegalArgumentException(exc);
		}
	}
	
	/**
	 * Remove a given entity from this world. This does set 
	 * the world of the entity to null.
	 * 
	 * @param	entity
	 * 			The entity to be removed.
	 * 
	 * @see implementation
	 */
	@Raw
	public void removeEntity(Entity entity) throws IllegalArgumentException, NullPointerException {
		updateMap();
		if (entity == null) throw new NullPointerException();
		if (!containsEntity(entity)) throw new IllegalArgumentException();
		entities.remove(helper.convertPositionToString(entity.getPosition()));
		entity.setWorld(null);
	}
	
	
	/**
	 * Updates the collection of entities. This method needs to be called whenever
	 * the position of an entity is changed.
	 *
	 * @see implementation
	 */
	@Raw
	private void updateMap() {
		List<Entity> entitiesList = new ArrayList<Entity>();
		List<String> iterator = helper.convertCollectionToList(entities.keySet());
			
		for (String position: iterator) {
			if (position == null) entities.remove(position);
			else if (!helper.convertPositionToString(entities.get(position).getPosition()).equals(position)) {
				Entity entity = entities.get(position);
		  		entities.remove(position);
		  		entitiesList.add(entity);
			}
		}
	  
		for (Entity entity: entitiesList)
			entities.put(helper.convertPositionToString(entity.getPosition()), entity);
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
	public Entity[] getFirstCollisionEntities() throws IllegalArgumentException {
		double collisionTimeMin = -1;
		Entity[] collisionEntitiesMin = new Entity[2];
		
//		for (Entity entity1: entities.values()) {
		for (Entity entity1: entities.values()) {
			collisionTimeMin = iterateEntities(collisionTimeMin, collisionEntitiesMin, entity1, -1);
			collisionTimeMin = iterateBoundaries(collisionTimeMin, collisionEntitiesMin, entity1, -1);
		}
		
		if (collisionTimeMin == -1) return null;
		return collisionEntitiesMin;
	}	
	
	
	/**
	 * Gets the entities involved in the first collision in the current world starting from a certain value.
	 * 
	 * @return	Returns the entities involved in the first collision
	 * 			of this world. If a boundary is involved the entities returned
	 * 			will be the entity involved in the boundary collision and itself.
	 * 			// TODO Declarative Documentation.
	 */
	@Raw
	public Entity[] getFirstCollisionEntities(double time) throws IllegalArgumentException {
		double collisionTimeMin = -1;
		Entity[] collisionEntitiesMin = new Entity[2];
		
//		for (Entity entity1: entities.values()) {
		for (Entity entity1: entities.values()) {
			collisionTimeMin = iterateEntities(collisionTimeMin, collisionEntitiesMin, entity1, time);
			collisionTimeMin = iterateBoundaries(collisionTimeMin, collisionEntitiesMin, entity1, time);
		}
		
		if (collisionTimeMin == -1) return null;
		return collisionEntitiesMin;
	}	
	
	
	/**
	 * Gets the time of the first collision in the current world.
	 * 
	 * @return	Returns the time of the first collision
	 * 			of this world.
	 * 			// TODO Declarative Documentation.
	 * 
	 * @throws	IllegalArgumentException
	 * 			| (this.getFirstCollisionEntities()[0] != this.getFirstCollisionEntities()[1]) &&
	 * 			| (this.getFirstCollisionEntities()[0].overlap(this.getFirstCollisionEntities()[1]))
	 */
	@Raw
	public double getTimeToFirstCollision() throws IllegalArgumentException {
		double collisionTimeTemp;
		double collisionTimeMin = -1;
//		for (Entity entity1: entities.values()) {
		for (Entity entity1: entities.values()) {
//			for (Entity entity2: entities.values()) {
			for (Entity entity2: entities.values()) {
				if (entity1 == entity2) continue;
				try {
					collisionTimeTemp = entity1.getTimeToCollision(entity2);
				}
				catch (IllegalArgumentException exc) {	// The First collision is already happening,
					throw new IllegalArgumentException(exc);	// there is an overlap. Overlap is not legal for this method.
				}
				if ( (collisionTimeTemp < collisionTimeMin) || (collisionTimeMin == -1 ) ) collisionTimeMin = collisionTimeTemp;
			}
			collisionTimeTemp = entity1.getTimeToCollision(this);
			if ( (collisionTimeTemp < collisionTimeMin) || (collisionTimeMin == -1 ) ) collisionTimeMin = collisionTimeTemp;
		}
		return collisionTimeMin;
	}
	
	
	/**
	 * Gets the position of the first collision in the current world.
	 * 
	 * @return	Returns the position of the first collision
	 * 			of this world.
	 * 			// TODO Declarative Documentation.
	 * 
	 * @throws	IllegalArgumentException
	 * 			| (this.getFirstCollisionEntities()[0] != this.getFirstCollisionEntities()[1]) &&
	 * 			| (this.getFirstCollisionEntities()[0].overlap(this.getFirstCollisionEntities()[1]))
	 */
	@Raw
	public double[] getFirstCollisionPosition() throws IllegalArgumentException {
		Entity[] collisionEntities = getFirstCollisionEntities();
		if (collisionEntities[0] == collisionEntities[1])
				return collisionEntities[0].getCollisionPosition(this);
		else
			try {
				return getFirstCollisionEntities()[0].getCollisionPosition(getFirstCollisionEntities()[1]);
			}
			catch (IllegalArgumentException exc) {
				throw new IllegalArgumentException(exc);
			}
	}
	
	
	
			/*
			 * |--------------------------------------------------------------------|
			 * | 5. The next methods handle helper methods for Collision Detection.	|
			 * |--------------------------------------------------------------------| 
			 */
	
	
	
	public double getTimeToCollisionEntitities(Entity[] collisionEntitiesMin) {
		if ( collisionEntitiesMin[0] == collisionEntitiesMin[1] )	// If the collision is with the boundary.
			return collisionEntitiesMin[0].getTimeToCollision(this);
		else	// If the collision is between 2 entities.
			return collisionEntitiesMin[0].getTimeToCollision(collisionEntitiesMin[1]);
	}
	
	
	/**
	 * Iterates an entity over all other entities and finds the shortest time.
	 * Helper Method for getFirstCollisionEntities().
	 * 
	 * @see implementation
	 */
	@Raw
	private double iterateEntities(double collisionTimeMin, Entity[] collisionEntitiesMin, Entity entity1, double time) 
			throws IllegalArgumentException {
		double collisionTimeTemp;
//		for (Entity entity2: entities.values()) {
		for (Entity entity2: getAllEntitiesList()) {
			if (entity1 == entity2) continue;	// Only check if the entities are different.
			try {
				collisionTimeTemp = entity1.getTimeToCollision(entity2);
			}
			catch (IllegalArgumentException exc) {	// The First collision is already happening,
				throw new IllegalArgumentException(exc);	// there is an overlap. Overlap is not legal for this method.
			}
			
			if ( !((collisionTimeTemp < collisionTimeMin) || (collisionTimeMin == -1 )) || (collisionTimeTemp <= time) ) continue;
			collisionTimeMin = collisionTimeTemp;	// This code is only reached if the new time is smaller
			collisionEntitiesMin[0] = entity1;	// Than the old time.
			collisionEntitiesMin[1] = entity2;
		}
		return collisionTimeMin;
	}
	
	/**
	 * Iterates an entity over the boundary with this world and finds the shortest time.
	 * Helper Method for getFirstCollisionEntities().
	 * 
	 * @see implementation
	 */
	@Raw
	private double iterateBoundaries(double collisionTimeMin, Entity[] collisionEntitiesMin, Entity entity1, double time) 
			throws IllegalArgumentException {
		double collisionTimeTemp;
		try {
			collisionTimeTemp = entity1.getTimeToCollision(this);
		}
		catch (IllegalArgumentException exc) { // The First collision is already happening,
			throw new IllegalArgumentException(exc);	// there is an overlap. Overlap is not legal for this method.
		}
		
		if ( !((collisionTimeTemp < collisionTimeMin) || (collisionTimeMin == -1 )) || (collisionTimeTemp <= time) ) return collisionTimeMin;
		collisionTimeMin = collisionTimeTemp;
		collisionEntitiesMin[0] = entity1;
		collisionEntitiesMin[1] = entity1;
		return collisionTimeMin;
	}
	
	
	
		/*
	     * |----------------------------|
	     * | #Header-4# Evolve Methods.	|
	     * |----------------------------| 
	     */
	
	
			/*
			 * |------------------------------------------------|
			 * | 6. The next methods handle evolving the world.	|
			 * |------------------------------------------------| 
			 */
	
	
	
	/**
	 * Map holding all entities that "just" had a collision in the evolve method.
	 * Cleared in the update method.
	 */
	private List<Entity[]> collisionTracker = new ArrayList<Entity[]>();
	/**
	 * Map holding all entities that "just" had a collision with a boundary.
	 * Cleared in the update method.
	 */
	private Map<Entity, double[]> boundaryTracker = new HashMap<Entity, double[]>();
	
	
	
	/**
	 * Evolve the current world.
	 * 
	 * @param	time
	 * 			The time over which the world will evolve.
	 */
	public void evolve(double time) throws IllegalArgumentException {
		if (time < 0) throw new IllegalArgumentException();
		destroyOverlaps();	// Destroy all the entities that are currently invalid (overlap something).
		updateMap();	// We need to update the map because it is possible that the positions of the entities have been changed.
		
		Entity[] collisionEntitiesMin = getFirstCollisionEntities();	// First get the first collision entities.
		if (collisionEntitiesMin == null) {	// There are no entities in this World.
			resolveEvolve(collisionEntitiesMin, -1, time);	// Shortcut evolve to update by using -1.
			return;		
		}

		double collisionTimeMin = getTimeToCollisionEntitities(collisionEntitiesMin); 
		if ( (collisionTimeMin == 0) && helper.isInList(collisionEntitiesMin, collisionTracker) // If there is a collision at zero,
				 && (collidesWithTrackedBoundary(collisionEntitiesMin))) // that has already occurred, 
			handleAbNormalCase(collisionEntitiesMin, time);	
		else handleNormalCase(collisionEntitiesMin, collisionTimeMin, time);
		
	}
	
	
	/**
	 * Handles the normal expected case of evolve.
	 * 
	 * @param	collisionEntitiesMin
	 * 			The entities that will collide first of all.
	 * @param 	collisionTimeMin
	 * 			The time at which the first collision occurs.
	 * @param	time
	 * 			The time over which the world will evolve.
	 */
	private void handleNormalCase (Entity[] collisionEntitiesMin, double collisionTimeMin, double time) {
		try {	// This is the only possible exception that is thrown in this method (outside of the one for the parameter);
			resolveEvolve(collisionEntitiesMin, collisionTimeMin, time); // All other exceptions will never occur in the execution of this method
		}																 // "All other" = the ones in the method calls of this method.
		catch (IllegalArgumentException exc) {// This exception is throw if the new position of an entity is invalid
			throw new IllegalArgumentException(exc);	// or if time is < 0.
		}
	}
	
	
	/**
	 * Handles the abnormal cases of evolve. Such as the Salamander bug.
	 * 
	 * @param	time
	 * 			The time over which the world will evolve.
	 */
	private void handleAbNormalCase(Entity[] collisionEntitiesMin, double time) {
		collisionEntitiesMin = getUntrackedEntititiesCollideAtZero();
		if (collisionEntitiesMin == null) collisionEntitiesMin = getFirstCollisionEntities(0);	// There is no collision at zero. Exclude zero.
		if (collisionEntitiesMin == null) {	// If there is no other collision in the world (=Salamander bug).
			handleSalamander(time);
			return;	// Shortcut back to evolve.
		}
		
		double collisionTimeMin =  getTimeToCollisionEntitities(collisionEntitiesMin);
		try {	// This is the only possible exception that is thrown in this method (outside of the one for the parameter);
			resolveEvolve(collisionEntitiesMin, collisionTimeMin, time); // All other exceptions will never occur in the execution of this method
		}										 // "All other" = the ones in the method calls of this method.
		catch (IllegalArgumentException exc) {	// This exception is throw if the new position of an entity is invalid
			throw new IllegalArgumentException(exc);	// or if time is < 0.
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
	 * 			The time over which the world has to be evolved.
	 */
	private void resolveEvolve(Entity[] collisionEntitiesMin, double collisionTimeMin, double time) 
			throws IllegalArgumentException {
		if ( (0 < collisionTimeMin) && (collisionTimeMin <= time) ) {	// Collision time needs to be positive to update to collisionTimeMin.
			try {
				update(collisionTimeMin);
			}
			catch (IllegalArgumentException exc) {	// This exception is throw if the new position of an entity is invalid
				throw new IllegalArgumentException(exc);	
			}
		}	// We don't include zero in the above procedure, because update clears our "trackers" (List/Map).
		if ( (0 <= collisionTimeMin) && (collisionTimeMin <= time) ) {
				if ( collisionEntitiesMin[0] == collisionEntitiesMin[1] ) {	// If the collision is with the boundary.
					collisionEntitiesMin[0].resolveCollision(this);	// This entity is added to the boundary tracker.
					boundaryTracker.put(collisionEntitiesMin[0], collisionEntitiesMin[0].getBoundaryCollision(this));
				}
				else	// If the collision is between 2 entities.
					collisionEntitiesMin[0].resolveCollision(collisionEntitiesMin[1]);
				
				collisionTracker.add(collisionEntitiesMin);	// This entity is added to the collision tracker.
				evolve(time - collisionTimeMin);	// Continue with evolve.
		 }
		else { 	// CollisionTimeMin is too large/doesn't occur. Update the world freely.
			try {
				update(time);
			}
			catch (IllegalArgumentException exc) {	// This exception is throw if the new position of an entity is invalid
				throw new IllegalArgumentException(exc);	
			}
		}
	}
	

	
			/*
			 * |--------------------------------------------------------------------|
			 * | 7. The next methods handle helper methods for evolving the world.	|
			 * |--------------------------------------------------------------------| 
			 */
	
	
	
	/**
	 * Update the position of all elements in the current world.
	 * Helper method of evolve().
	 * 
	 * @param	time
	 * 			the time over which the world has to be updated.
	 * 
	 * @throws 	IllegalArgumentException
	 *         	The new position of an entity is invalid.
	 *       	| ! new Position(0,0).isValidPosition(positionX, positionY)	// TODO There exists.
	 */
	private void update(double time) throws IllegalArgumentException {
		collisionTracker.clear();	// Clears the trackers because we are moving positions and won't be stuck in a loop anymore.
		boundaryTracker.clear();
		for (Entity entity: getAllEntitiesList()) {	// getAllEntitiesList(), returns a new list, iterating is possible.
			if ( (entity.getType() == "Ship") && ((Ship)entity).getThrustStatus()) ((Ship)entity).thrust(((Ship)entity).getAcceleration(), time);
			try {
				entity.move(time);
			}
			catch (IllegalArgumentException exc) {	// This exception is throw if the new position of this entity is invalid
				throw new IllegalArgumentException(exc);	
			}
		}
		updateMap();	// Positions were changed, the map needs to be updated.
	}
	
	
	/**
	 * Handler method for the Salamander bug.
	 * TODO Explain the Salamander bug.
	 * 
	 * @param	time
	 * 			The time over which the world will evolve.
	 */
	private void handleSalamander(double time) {
		double collisionTimeMin = -1;	// TODO This shit is weird.
		Entity[] collisionEntitiesMin = new Entity[2];
		for (Entity entity: getAllEntities()) {
			double collisionTimeTemp =  entity.getTimeToCollision(this);
			if (collisionTimeTemp == 0)
				collisionTimeTemp =  entity.getTimeToNextBoundaryCollision(this);
			if ((collisionTimeTemp < collisionTimeMin) || (collisionTimeMin == -1 )) {
				collisionTimeMin = collisionTimeTemp;
				collisionEntitiesMin[0] = entity;
				collisionEntitiesMin[1] = entity;
			}
		}
		resolveEvolve(collisionEntitiesMin, collisionTimeMin, time);
		return;
	}
	
	
	/**
	 * Checks if a given entity collides with a tracked boundary. The given entity should
	 * be entered correctly, else this method does not work or will return false.
	 * 
	 * @param	entity
	 * 			The entity to be checked. This should be entered as an array
	 * 			of entities with size 2. In which the first entity is also
	 * 			the second entity.
	 * 
	 * @return	Returns whether or not the given entity collides with a tracked boundary.
	 */
	public boolean collidesWithTrackedBoundary(Entity[] entity) {
		if (entity[0] != entity[1]) return false;
		double[] lastBoundaries;
		double[] currentBoundaries = entity[0].getBoundaryCollision(this);
		try {
			lastBoundaries = boundaryTracker.get(entity[0]);
		}
		catch (NullPointerException exc) {
			return false;
		}
		if ( (lastBoundaries[0] == currentBoundaries[0]) || (lastBoundaries[0] == currentBoundaries[1]) ||
				(lastBoundaries[1] == currentBoundaries[0]) || (lastBoundaries[1] == currentBoundaries[1]) )
			return true;
		return false;
	}
	
	
	/**
	 * Returns entities that collide at time zero and are currently not
	 * present in the collision tracker. If the first entity is equal to the second,
	 * the collision is with the boundary. Returns null if there isn't one.
	 * 
	 * @return	Returns entities that collide at time zero or null if there aren't any.
	 * 			| this.getTimeToCollisionEntities(getUntrackedEntititiesCollideAtZero()) == 0;
	 */
	public Entity[] getUntrackedEntititiesCollideAtZero() {
		Entity[] collisionEntitiesMin= new Entity[2];
		for (Entity entity1: getAllEntitiesList()) {
			collisionEntitiesMin[0] = entity1;
			for (Entity entity2: getAllEntitiesList()) {
				collisionEntitiesMin[1] = entity2;
				if (entity1 == entity2) continue;
				if ( (entity1.getTimeToCollision(entity2) == 0) && !helper.isInList(collisionEntitiesMin, collisionTracker) )
					return collisionEntitiesMin; // Check if two different entities collide at zero and are untracked.
			}
			
			collisionEntitiesMin[1] = entity1;
			if ( (entity1.getTimeToCollision(this) == 0) && !helper.isInList(collisionEntitiesMin, collisionTracker) )
				return collisionEntitiesMin;	// Checks if an entity collides with the boundaries of the world and is untracked.
		}
		return null;	// If there is no other collision at zero, return null.
	}
	
	
	
		/*
	     * |----------------------------|
	     * | #Header-5# Query Methods.	|
	     * |----------------------------| 
	     */
	
	
			/*
			 * |----------------------------------------------------|
			 * | 8. The next methods handle queries of the world.	|
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
	 * 			// TODO Declarative Documentation?
	 */
	@Raw
	public Entity getEntityAtPosition(Position position) {
		try {
			return entities.get(helper.convertPositionToString(position));
		}
		catch (NullPointerException exc) {
			return null;
		}
	}
	
	
	/**
	 * Returns all entities registered in this world.
	 * It is returned as a list.
	 * 
	 * @return	Returns all entities registered in this world.
	 * 			| entity.getWorld() == this
	 */
	@Raw
	public List<Entity> getAllEntitiesList() {
		return helper.convertSetToList(getAllEntities());
	}
	
	
	/**
	 * Returns all entities registered in this world.
	 * 
	 * @return	Returns all entities registered in this world.
	 * 			| entity.getWorld() == this
	 */
	@Raw
	public Set<Entity> getAllEntities() {
		Set<Entity> entitiesResult = new HashSet<Entity>();
		for (Entity entity: entities.values()) entitiesResult.add(entity);
		return entitiesResult;
	}
		
	/**
	 * Returns all ships registered in this world.
	 * 
	 * @return	Returns all ships registered in this world.
	 * 			| ship.getWorld() == this
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
	 * 			| bullet.getWorld() == this
	 */
	@Raw
	public Set<Bullet> getAllBullets() {
		Set<Entity> entitiesResult = getAllEntities();
		Set<Bullet> bulletsResult = new HashSet<Bullet>();
		for (Entity entity: entitiesResult)
			if (entity.getType() == "Bullet") bulletsResult.add((Bullet)entity);
		return bulletsResult;
	}

	
	
		/*
	     * |----------------------------|
	     * | #Header-6# Other Methods.	|
	     * |----------------------------| 
	     */
	
	
			/*
		 	 * |--------------------------------------------|
		     * | 9. The next methods are Helper Methods.	|
		     * |--------------------------------------------| 
		     */
	
	
	
	/**
	 * Destroys all overlapping entities in the current world.
	 * If an entity overlaps with 2 other entities, one of the entities will not be destroyed.
	 * The same holds true for boundaries and entity and boundary overlaps.
	 */
	public void destroyOverlaps() {
		for (Entity entity1: getAllEntitiesList()) {	// getAllEntitiesList(), returns a new list, iterating is possible.
			if (!entity1.isInWorld(this)) {
				;	// This is to be able to debug at terminate. #Bug-1-Salamander#
				entity1.terminate();	// If the entity "overlaps" the world, terminate it.
			}
			if (entity1.isTerminated()) continue;	// If it has been terminated, stop checking for it.
			for (Entity entity2: getAllEntitiesList()) {	// Otherwise find all overlaps with other entities.
				if (entity1 == entity2) continue;	// These continue statements are necessary so that overlap doesn't crash.
				if (entity2.isTerminated()) continue; 
				if (entity1.overlap(entity2) || entity2.overlap(entity1)) {	// If two entities overlap, destroy them.
					entity1.terminate();
					entity2.terminate();
				}
			}
		}
	}
	
}
