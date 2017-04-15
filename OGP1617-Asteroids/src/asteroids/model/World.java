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
 * #4# Query Methods
 * 		8. Methods that handle queries of the world
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
		for (Entity entity: entitiesString.values()) {
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
	private Map<String, Entity> entitiesString = new HashMap<String, Entity>();	// TODO Use this implementation!
	
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
		if (helper.operlapsWithOtherEntities(entity, helper.convertCollectionToList(entitiesString.values()))) 
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
            return entitiesString.get(helper.convertPositionToString(entity.getPosition())) == entity;
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
		entitiesString.put(helper.convertPositionToString(entity.getPosition()), entity);
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
//		entities.remove(entity.getPosition());
		entitiesString.remove(helper.convertPositionToString(entity.getPosition()));
		entity.setWorld(null);
	}
	
	
	/**
	 * Updates the collection of entities. This method needs to be called whenever
	 * the position of an entity is changed.
	 *
	 * @see implementation
	 */
	@Raw
	public void updateMap() {
		List<Entity> entitiesList = new ArrayList<Entity>();
		List<String> iterator = helper.convertCollectionToList(entitiesString.keySet());
//		List<Position> iterator = helper.convertCollectionToList(entities.keySet());
//		for (Position position: iterator) {
//			if (position == null) entities.remove(position);
//			else if (entities.get(position).getPosition() != position) {
//					Entity entity = entities.get(position);
//					entities.remove(position);
//					entitiesList.add(entity);
//			}
//		}
//		for (Entity entity: entitiesList) /*this.addEntity(entity);*/{
//			entities.put(entity.getPosition(), entity);
//		}
		
	  for (String position: iterator) {
	  	if (position == null) entitiesString.remove(position);
	  	else if (!helper.convertPositionToString(entitiesString.get(position).getPosition()).equals(position)) {
	  		Entity entity = entitiesString.get(position);
	  		entitiesString.remove(position);
	  		entitiesList.add(entity);
	  	}
	  }
	  
	  for (Entity entity: entitiesList) /*this.addEntity;*/
	  	entitiesString.put(helper.convertPositionToString(entity.getPosition()), entity);
	 
	}
	
	
//	public String convertPositionToString(Position position) {
//		return Double.toString(position.getPositionX()) + "," + Double.toString(position.getPositionY());
//	}
	
	
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
		for (Entity entity1: entitiesString.values()) {
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
		for (Entity entity1: entitiesString.values()) {
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
		for (Entity entity1: entitiesString.values()) {
//			for (Entity entity2: entities.values()) {
			for (Entity entity2: entitiesString.values()) {
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
		for (Entity entity2: entitiesString.values()) {
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
	 * Map holding all entities that had a collision at time zero and have been updated
	 */
	private List<Entity[]> updatedEntities = new ArrayList<Entity[]>();
	
	
	/**
	 * Evolve the current world.
	 * 
	 * @param	time
	 * 			the time over which the world will evolve.
	 */
	// This method is longer than 20 lines, but I did not want to break it up in smaller methods because
	// it made debugging quite difficult.
	public void evolve(double time) throws IllegalArgumentException {
		if (time < 0) throw new IllegalArgumentException();
		destroyOverlaps();	// Destroy all the entities that are currently invalid (overlap something).
		updateMap();	// We need to update the map because it is possible that the positions of the entities have been changed.
		Entity[] collisionEntitiesMin = getFirstCollisionEntities();
		if (collisionEntitiesMin == null) resolveEvolve(-1, collisionEntitiesMin, time);	// There are no entities in this World.
		else {
			double collisionTimeMin = -1;
			for (int i = 0; i < 2; i ++) {	// This for loop is necessary to make sure that we can resolve collisions at time zero.
				if (collisionEntitiesMin == null) collisionEntitiesMin = getFirstCollisionEntities(0);	// If not break at end.
				if (collisionEntitiesMin == null) {	// These if statements ensure that the next collision is correct.
					resolveEvolve(-1, collisionEntitiesMin, time);
					return;
				}
				
				if ( collisionEntitiesMin[0] == collisionEntitiesMin[1] )	// If the collision is with the boundary.
					collisionTimeMin = collisionEntitiesMin[0].getTimeToCollision(this);
				else	// If the collision is between 2 entities.
					collisionTimeMin = collisionEntitiesMin[0].getTimeToCollision(collisionEntitiesMin[1]);
				
				if ( (collisionTimeMin == 0) && helper.isInList(collisionEntitiesMin, updatedEntities) ) // If this collision has already
					collisionEntitiesMin = findOtherCollisionsAtZero();	// been found and the time is zero, find another collision.
				else break;	// If in normal case, break.													
			}
			
			try {	// This is the only possible exception that is thrown in this method (outside of the one for the parameter);
				resolveEvolve(collisionTimeMin, collisionEntitiesMin, time); // All other exceptions will never occur in the execution of this method
			}																 // "All other" = the ones in the method calls of this method.
			catch (IllegalArgumentException exc) {// This exception is throw if the new position of an entity is invalid
				throw new IllegalArgumentException(exc);	// or if time is < 0.
			}
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
	 */
	private void resolveEvolve(double collisionTimeMin, Entity[] collisionEntitiesMin, double time) 
			throws IllegalArgumentException {
		if ( (0 < collisionTimeMin) && (collisionTimeMin <= time) ) {	// Collision time needs to be positive. -1 is also used to
			try {				// shortcut immediately from this comparison to the end.
				update(collisionTimeMin);
			}
			catch (IllegalArgumentException exc) {// This exception is throw if the new position of an entity is invalid
				throw new IllegalArgumentException(exc);	
			}
		}	// We don't include zero in the above procedure, because update clears updatedEntities.
		if ( (0 <= collisionTimeMin) && (collisionTimeMin <= time) ) {	// This is so we can also handle the zero case.
				if ( collisionEntitiesMin[0] == collisionEntitiesMin[1] )	// If the collision is with the boundary
					collisionEntitiesMin[0].resolveCollision(this);
				else	// If the collision is between 2 entities.
					collisionEntitiesMin[0].resolveCollision(collisionEntitiesMin[1]);
				updatedEntities.add(collisionEntitiesMin);
				evolve(time - collisionTimeMin);
		 }
		else update(time);
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
		updatedEntities.clear();	// Clear the updated entities because we are moving positions and won't be stuck in a loop anymore.
//		List<Entity> iterator = helper.convertCollectionToList(entities.values());	// Creates an iterator that the next
		List<Entity> iterator = helper.convertCollectionToList(entitiesString.values());	// Creates an iterator that the next
		for (Entity entity: iterator) {			// for loop can iterate over, otherwise the map might be changed during execution.
			if ( (entity.getType() == "Ship") && ((Ship)entity).getThrustStatus()) ((Ship)entity).thrust(((Ship)entity).getAcceleration(), time);
			try {	// The exceptions in removeEntity are never thrown in this method.
				entity.move(time);
			}
			catch (IllegalArgumentException exc) {	// This exception is throw if the new position of this entity is invalid
				throw new IllegalArgumentException(exc);	
			}
		}
		updateMap();	// Positions were changed, the map needs to be updated.
	}
	
	
	/**
	 * Destroys all overlapping entities in the current world.
	 * If an entity overlaps with 2 other entities, one of the entities will not be destroyed.
	 * The same holds true for boundaries and entity + boundary overlaps.
	 * Helper method of evolve().
	 */
	public void destroyOverlaps() {
//		List<Entity> iterator = helper.convertCollectionToList(entities.values());	// Creates an iterator
		List<Entity> iterator = helper.convertCollectionToList(entitiesString.values());	// Creates an iterator
		for (Entity entity1: iterator) {	// that iterates over all the entities of this world.
			if (!entity1.isInWorld(this)) {
				;	// This is to be able to debug at terminate. #Bug-1#
				entity1.terminate();	// If the entity "overlaps" the world, terminate it.
			}
			if (entity1.isTerminated()) continue;	// If it has been terminated, stop checking for it.
			for (Entity entity2: iterator) {	// Otherwise find all overlaps with other entities.
				if (entity1 == entity2) continue;	// These continue statements are necessary
				if (entity2.isTerminated()) continue;	// So that overlap doesn't crash.
				if (entity1.overlap(entity2) || entity2.overlap(entity1)) {	// If two entities overlap, destroy them.
					entity1.terminate();
					entity2.terminate();
				}
			}
		}
	}
	
	
	/**
	 * Finds all collisions that at zero that are not currently present
	 * in the updatedEntities List.
	 */
	public Entity[] findOtherCollisionsAtZero() {
//		List<Entity> iterator = helper.convertCollectionToList(entities.values());
		List<Entity> iterator = helper.convertCollectionToList(entitiesString.values());
		Entity[] collisionEntitiesMin= new Entity[2];
		for (Entity entity1: iterator) {
			collisionEntitiesMin[0] = entity1;
			for (Entity entity2: iterator) {
				collisionEntitiesMin[1] = entity2;
				if (entity1 == entity2) continue;
				if ( (entity1.getTimeToCollision(entity2) == 0) && !helper.isInList(collisionEntitiesMin, updatedEntities) )
					return collisionEntitiesMin;
			}
			collisionEntitiesMin[1] = entity1;
			if ( (entity1.getTimeToCollision(this) == 0) && !helper.isInList(collisionEntitiesMin, updatedEntities) )
				return collisionEntitiesMin;
		}
		return null;
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
//			return entities.get(position);
			return entitiesString.get(helper.convertPositionToString(position));
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
//		for (Entity entity: entities.values()) entitiesResult.add(entity);
		for (Entity entity: entitiesString.values()) entitiesResult.add(entity);
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
	
}
