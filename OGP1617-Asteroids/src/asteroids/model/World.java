package asteroids.model;

import java.util.*;
import java.util.stream.Collectors;

import asteroids.helper.*;
import asteroids.helper.entity.Entity;
import asteroids.helper.entity.Position;
import asteroids.helper.entity.Terminateable;
import asteroids.part2.CollisionListener;
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
 * A class of worlds.
 * 
 * @invar   | this.isValidSize(getWidth(), getHeight())
 * @invar	| for each entity in getAllEntities(): this.canHaveAsEntity(entity)
 *       
 * @author	Mathijs Hubrechtsen
 */

public class World implements Terminateable {
	
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
	 * @see	implementation
	 */
	public World(double width, double height) {
		double[] temp = {width, height};
		if (!isValidSize(width, height)) 
			temp = calculateDefaultSize(width, height);
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
		for (Entity entity: getAllEntitiesList()) {
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
	 * @see implementation
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
		if ( (width < 0) || (java.lang.Double.isNaN(width)) ) 
			result[0] = 0;
		else if (width > getUpperBound()) 
			result[0] = getUpperBound();
		if ( (height < 0) || (java.lang.Double.isNaN(width)) ) 
			result[1] = 0;
		else if (height > getUpperBound()) 
			result[1] = getUpperBound();
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
	private Map<String, Entity> entities = new HashMap<String, Entity>();
	
	/**
	 * Check whether a given in entity can be in this world.
	 * 
	 * @param	entity
	 * 			The entity to be checked.
	 * 
	 * @see implementation
	 */
	@Raw
	public boolean canHaveAsEntity(Entity entity) throws NullPointerException {
		if (entity == null) 
			return false;
		if (helper.operlapsWithOtherEntities(entity, getAllEntitiesList())) 
			return false;
		return (entity != null) && (!entity.isTerminated()) && (!containsEntity(entity)) && (entity.isInWorld(this));
	}	
	
	/**
	 * Check whether a given in entity is currently in this world.
	 * 
	 * @param	entity
	 * 			The entity to be checked.
	 * 
	 * @see implementation
	 */
	@Raw
	public boolean containsEntity(Entity entity) throws NullPointerException {
        try {
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
		if (entity == null) 
			throw new NullPointerException();
		if (!canHaveAsEntity(entity)) 
			throw new IllegalArgumentException();
		entities.put(helper.convertPositionToString(entity.getPosition()), entity);
		entity.setWorld(this);
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
		if (entity == null) 
			throw new NullPointerException();
		if (!containsEntity(entity)) 
			throw new IllegalArgumentException();
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
	private void updateMap() throws NullPointerException {
		List<Entity> entitiesList = new ArrayList<Entity>();	// A buffer list that will hold all entities that were changed.
		List<String> iterator = helper.convertCollectionToList(entities.keySet());	// Creates a position iterator.
			
		for (String position: iterator) {	// Iterate over all the positions
			if (position == null) 
				entities.remove(position);	// If a position is null, remove it.
			else if (!helper.convertPositionToString(entities.get(position).getPosition()).equals(position)) {
				Entity entity = entities.get(position);	// If the position was changed, remove the position
		  		entities.remove(position);	// from the map and add it to the buffer list.
		  		entitiesList.add(entity);
			}
		}
		
		for (Entity entity: entitiesList)	// Add all changed entities to the map.
			entities.put(helper.convertPositionToString(entity.getPosition()), entity);
	}	// Currently this method doesn't work with the add and removeEntity methods, 
		// because if it does, there are issues if the collection is invalid.
	
	
	
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
	 * For the throws, see iterateEntities/iterateBoundaries.
	 * 
	 * @return	Returns the entities involved in the first collision
	 * 			of this world. If a boundary is involved the entities returned
	 * 			will be the entity involved in the boundary collision and itself.
	 * 			| for each entity1: entity: getAllEntitiesList():
	 * 			| for each entity2: entity: getAllEntitiesList():
	 * 			| this.getTimeToCollisionEntities(result) <= this.getTimeToCollisionEntities({entity1, entity2})
	 */
	@Raw 
	public Entity[] getFirstCollisionEntities() {
		return getFirstCollisionEntities(-1);
	}	
	
	
	/**
	 * Gets the entities involved in the first collision in the current world starting from a certain value.
	 * For the throws, see iterateEntities/iterateBoundaries.
	 * 
	 * @return	Returns the entities involved in the first collision
	 * 			of this world. If a boundary is involved the entities returned
	 * 			will be the entity involved in the boundary collision and itself.
	 * 			| for each entity1: getAllEntitiesList():
	 * 			| for each entity2: getAllEntitiesList():
	 * 			| if (this.getTimeToCollisionEntities({entity1, entity2}) > time)
	 * 			| this.getTimeToCollisionEntities(result) <= this.getTimeToCollisionEntities({entity1, entity2})
	 */
	@Raw
	public Entity[] getFirstCollisionEntities(double time) throws IllegalArgumentException, NullPointerException { 
		double collisionTimeMin = -1;
		Entity[] collisionEntitiesMin = new Entity[2];
		
		for (Entity entity1: getAllEntitiesList()) {
			collisionTimeMin = iterateEntities(collisionTimeMin, collisionEntitiesMin, entity1, time);
			collisionTimeMin = iterateBoundaries(collisionTimeMin, collisionEntitiesMin, entity1, time);
		}
		
		return (collisionTimeMin == -1) ? null: collisionEntitiesMin;
	}	
	
	
	/**
	 * Gets the time of the first collision in the current world.
	 * 
	 * @return	Returns the time of the first collision
	 * 			of this world.
	 * 			| for each entity1: getAllEntitiesList():
	 * 			| for each entity2: getAllEntitiesList():
	 * 			| result <= this.getTimeToCollisionEntities({entity1, entity2})
	 */
	@Raw
	public double getTimeToFirstCollision() throws IllegalArgumentException, NullPointerException {
		double collisionTimeTemp, collisionTimeMin = -1;
		
		for (Entity entity1: getAllEntitiesList()) {
			for (Entity entity2: getAllEntitiesList()) {
				if (entity1 == entity2) continue;
				
				collisionTimeTemp = entity1.getTimeToCollision(entity2);
				if ( (collisionTimeTemp < collisionTimeMin) || (collisionTimeMin == -1 ) ) 
					collisionTimeMin = collisionTimeTemp;
			}
			
			collisionTimeTemp = entity1.getTimeToCollision(this);
			if ( (collisionTimeTemp < collisionTimeMin) || (collisionTimeMin == -1 ) ) 
				collisionTimeMin = collisionTimeTemp;
		}
		return collisionTimeMin;
	}
	
	
	/**
	 * Gets the position of the first collision in the current world.
	 * 
	 * @return	Returns the position of the first collision
	 * 			of this world.
	 * 			| for some entity1: getAllEntitiesList():
	 * 			| for some entity2: getAllEntitiesList():
	 * 			| if (getTimeToCollisionEntities({entity1, entity2) == getTimeToFirstCollision())
	 * 			| 	if (entity1 == entity2)
	 * 			|		result == entit1.getCollisionPosition(this)
	 * 			|	else
	 * 			|		result == entity1.getCollisionPosition(entity2)
	 */
	@Raw
	public double[] getFirstCollisionPosition() throws IllegalArgumentException, NullPointerException {
		Entity[] collisionEntities = getFirstCollisionEntities();
		
		if (collisionEntities[0] == collisionEntities[1])	// If the collision is with the boundary.
			return collisionEntities[0].getCollisionPosition(this);
		else	// If the collision is between 2 entities.
			return getFirstCollisionEntities()[0].getCollisionPosition(getFirstCollisionEntities()[1]);
	}
	
	
	
			/*
			 * |--------------------------------------------------------------------|
			 * | 5. The next methods handle helper methods for Collision Detection.	|
			 * |--------------------------------------------------------------------| 
			 */
	
	
	
	/**
	 * Returns the time to collision between the entities in an array.
	 * The array must be correctly built.
	 * Handler method used in the evolve method to negate code bloat.
	 * 
	 * @param	collisionEntitiesMin
	 * 			The entity array involved in the collision.
	 * 
	 * @see implementation
	 */
	@Raw
	public double getTimeToCollisionEntitities(Entity[] collisionEntitiesMin) 
			throws IllegalArgumentException, NullPointerException {
		if (collisionEntitiesMin.length != 2) 
			return -1;
		
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
			throws IllegalArgumentException, NullPointerException {
		double collisionTimeTemp;
		
		for (Entity entity2: getAllEntitiesList()) {
			if (entity1 == entity2) continue;	// Only check if the entities are different.
			
			collisionTimeTemp = entity1.getTimeToCollision(entity2);
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
	private double iterateBoundaries(double collisionTimeMin, Entity[] collisionEntitiesMin, Entity entity1, double time) {
		double collisionTimeTemp = entity1.getTimeToCollision(this);
		
		if ( !((collisionTimeTemp < collisionTimeMin) || (collisionTimeMin == -1 )) || (collisionTimeTemp <= time) ) 
			return collisionTimeMin;
		
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
	 * Variable holding the collisionPosition.
	 */
	private double[] collisionPosition;
	/**
	 * Variable holding the collisionListener.
	 */
	private CollisionListener collisionListener;

	
	/**
	 * Set the collisionPosition, this is used by the collisionListener.
	 * 
	 * @param 	collisionEntitiesMin
	 * 			The entities that will collide first of all.
	 */
	private void setCollisionPosition(Entity[] collisionEntitiesMin) throws IllegalArgumentException, NullPointerException {
		if (collisionEntitiesMin[0] == collisionEntitiesMin[1])
			this.collisionPosition = collisionEntitiesMin[0].getCollisionPosition(this);
		else
			this.collisionPosition = collisionEntitiesMin[0].getCollisionPosition(collisionEntitiesMin[1]);
	}
	
	
	/**
	 * Handles the collisionListener visual effect.
	 * 
	 * @param 	collisionEntitiesMin
	 * 			The entities that will collide first of all.
	 */
	private void HandleCollisionListener(Entity[] collisionEntitiesMin) {
		if (this.collisionListener == null) return;
		
		if ( (collisionEntitiesMin[0].getClass().isAssignableFrom(Bullet.class)) &&
				(collisionEntitiesMin[1].getClass().isAssignableFrom(Ship.class)) &&
				(((Bullet) collisionEntitiesMin[0]).getSource() == ((Ship) collisionEntitiesMin[1])) )
			return;

		if ( (collisionEntitiesMin[1].getClass().isAssignableFrom(Bullet.class)) &&
				(collisionEntitiesMin[0].getClass().isAssignableFrom(Ship.class)) &&
				(((Bullet) collisionEntitiesMin[1]).getSource() == ((Ship) collisionEntitiesMin[0])) )
				return;
		
		if (collisionEntitiesMin[0] == collisionEntitiesMin[1])
			this.collisionListener.boundaryCollision(collisionEntitiesMin[0], 
					this.collisionPosition[0], this.collisionPosition[1]);
		else
			this.collisionListener.objectCollision(collisionEntitiesMin[0], collisionEntitiesMin[1], 
					this.collisionPosition[0], this.collisionPosition[1]);
	}
	
	
	/**
	 * Evolve the current world.
	 * 
	 * @param	time
	 * 			The time over which the world will evolve.
	 * @param 	collisionListener
	 * 			The visual effect handler of collisions.
	 */
	public void evolve(double time, CollisionListener collisionListener) throws IllegalArgumentException, NullPointerException { 
		this.collisionListener = collisionListener;
		if ( (time < 0) || (Double.isNaN(time)) )
			throw new IllegalArgumentException();
		destroyOverlaps();	// Destroy all the entities that are currently invalid (overlap something).
		updateMap();	// We need to update the map because it is possible that the positions of the entities have been changed.
		
		Entity[] collisionEntitiesMin = getFirstCollisionEntities();	// First get the first collision entities.
		if (collisionEntitiesMin == null) {	// There are no entities in this World.
			resolveEvolve(collisionEntitiesMin, -1, time);	// Shortcut evolve to update by using -1.
			return;		
		}

		setCollisionPosition(collisionEntitiesMin); // Set the collision position.
		double collisionTimeMin = getTimeToCollisionEntitities(collisionEntitiesMin); 
		if ( (collisionTimeMin == 0) && (helper.isInList(collisionEntitiesMin, collisionTracker) // If there is a collision at zero,
				 || (collidesWithTrackedBoundary(collisionEntitiesMin))) ) // that has already occurred, 
			handleAbNormalCase(collisionEntitiesMin, time);	
		else 
			handleNormalCase(collisionEntitiesMin, collisionTimeMin, time);
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
	private void handleNormalCase (Entity[] collisionEntitiesMin, double collisionTimeMin, double time)
			throws IllegalArgumentException, NullPointerException {
			resolveEvolve(collisionEntitiesMin, collisionTimeMin, time);
	}
	
	
	/**
	 * Handles the abnormal cases of evolve. Such as the Salamander bug.
	 * 
	 * @param	time
	 * 			The time over which the world will evolve.
	 */
	private void handleAbNormalCase(Entity[] collisionEntitiesMin, double time) 
			throws IllegalArgumentException, NullPointerException {
		collisionEntitiesMin = getUntrackedEntititiesCollideAtZero();
		if (collisionEntitiesMin == null) 
			collisionEntitiesMin = getFirstCollisionEntities(0);	// There is no collision at zero. Exclude zero.
		if (collisionEntitiesMin == null) {	// If there is no other collision in the world (=Salamander bug).
			handleSalamander(time);
			return;	// Shortcut back to evolve.
		}
		
		double collisionTimeMin =  getTimeToCollisionEntitities(collisionEntitiesMin);
		resolveEvolve(collisionEntitiesMin, collisionTimeMin, time);
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
			throws IllegalArgumentException, NullPointerException {
		if ( (0 < collisionTimeMin) && (collisionTimeMin <= time) )	// Collision time needs to be positive to update to collisionTimeMin.
				update(collisionTimeMin);

		// We don't include zero in the above procedure, because update clears our "trackers" (List/Map).
		if ( (0 <= collisionTimeMin) && (collisionTimeMin <= time) ) {
				if ( collisionEntitiesMin[0] == collisionEntitiesMin[1] ) {	// If the collision is with the boundary.
					HandleCollisionListener(collisionEntitiesMin);
					collisionEntitiesMin[0].resolveCollision(this);	// This entity is added to the boundary tracker.
					boundaryTracker.put(collisionEntitiesMin[0], collisionEntitiesMin[0].getCollisionBoundaries(this));
				}
				else {	// If the collision is between 2 entities.
					HandleCollisionListener(collisionEntitiesMin);
					collisionEntitiesMin[0].resolveCollision(collisionEntitiesMin[1]);	
				}
				collisionTracker.add(collisionEntitiesMin);	// This entity is added to the collision tracker.
				evolve(time - collisionTimeMin, this.collisionListener);	// Continue with evolve.
		 }
		
		else  // CollisionTimeMin is too large/doesn't occur. Update the world freely.
			update(time);
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
	 *       	| ! for some entity in getAllEntitiesList(): new Position(0,0).isValidPosition(entity.move(time).getPosition.getPositionX(),
	 *       		 entity.move(time).getPosition.getPositionY())
	 */
	private void update(double time) throws NullPointerException {
		collisionTracker.clear();	// Clears the trackers because we are moving positions and won't be stuck in a loop anymore.
		boundaryTracker.clear();
		getAllEntitiesList().forEach(x -> x.morph(time));
//		for (Entity entity: getAllEntitiesList())// getAllEntitiesList(), returns a new list, iterating is possible.
//			entity.morph(time);
		updateMap();	// Positions were changed, the map needs to be updated.
	}
	
	
	/**
	 * Handler method for the Salamander bug.
	 * The Salamander bug is a bug that happens when sometimes a ship
	 * reaches a boundary and for some reason, it's collision isn't handled.
	 * This has as consequence that it's destroyed at the first next evolve call.
	 * This method handles the case when there just was a collision with the boundary, and there is 
	 * another one at time 0, this method will then find the next collision that's not at time zero.
	 * Helper Method for evolve().
	 * 
	 * @param	time
	 * 			The time over which the world will evolve.
	 */
	private void handleSalamander(double time) throws IllegalArgumentException, NullPointerException {
		double collisionTimeMin = -1;
		Entity[] collisionEntitiesMin = new Entity[2];
		for (Entity entity: getAllEntities()) {	// Find the second non-zero collision with a boundary.
			double collisionTimeTemp =  entity.getTimeToCollision(this);
			if (collisionTimeTemp == 0)	// If the collision is at time zero, find the non zero collision with the boundary.
				collisionTimeTemp =  entity.getTimeToSecondCollisionNonZero(this);
			if ((collisionTimeTemp < collisionTimeMin) || (collisionTimeMin == -1 )) {
				collisionTimeMin = collisionTimeTemp;
				collisionEntitiesMin[0] = entity;
				collisionEntitiesMin[1] = entity;
			}
		}
		resolveEvolve(collisionEntitiesMin, collisionTimeMin, time);	// Resolve the evolve.
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
	 * 			| result == for all boundary in entity[0].getCollisionBoundaries():
	 * 			| 			!boundaryTracker.get(Entity[0]).contains(boundary)
	 */
	public boolean collidesWithTrackedBoundary(Entity[] entity) throws NullPointerException {
		if (entity[0] != entity[1]) return false;	// Collision is with entities.
		
		double[] lastBoundaries  = boundaryTracker.get(entity[0]); 
		if (lastBoundaries == null) return false;
		
		double[] currentBoundaries = entity[0].getCollisionBoundaries(this);
		return (lastBoundaries[0] == currentBoundaries[0]) || (lastBoundaries[0] == currentBoundaries[1]) ||
			   (lastBoundaries[1] == currentBoundaries[0]) || (lastBoundaries[1] == currentBoundaries[1]);
	}
	
	
	/**
	 * Returns entities that collide at time zero and are currently not
	 * present in the collision tracker. If the first entity is equal to the second,
	 * the collision is with the boundary. Returns null if there isn't one.
	 * 
	 * @return	Returns entities that collide at time zero or null if there aren't any.
	 * 			| this.getTimeToCollisionEntities(getUntrackedEntititiesCollideAtZero()) == 0;
	 */
	public Entity[] getUntrackedEntititiesCollideAtZero() throws IllegalArgumentException, NullPointerException { 
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
	 * 			| ( (result.getPosition().getPositionX() == position.getPositionX()) &&
	 * 			|   (result.getPosition().getPositionY() == position.getPositionY()) )
	 */
	@Raw
	public Entity getEntityAtPosition(Position position) throws NullPointerException {
		if ( (position.getPositionX() > getWidth()) || (position.getPositionY() > getHeight()) )
			return null;
		return entities.get(helper.convertPositionToString(position));
	}
	
	
	/**
	 * Returns all entities registered in this world.
	 * It is returned as a list.
	 * 
	 * @return	Returns all entities registered in this world.
	 * 			| for each entity: result:
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
	 * 			| for each entity: result:
	 * 			| entity.getWorld() == this
	 */
	@Raw
	public Set<Entity> getAllEntities() {
		Set<Entity> entitiesResult = new HashSet<Entity>();
		entities.values().forEach(x -> entitiesResult.add(x));
//		for (Entity entity: entities.values()) 
//			entitiesResult.add(entity);
		return entitiesResult;
	}
		
	
	/**
	 * Returns all entities in this world of the given class.
	 * 
	 * @param entityClass
	 * 
	 * @return	Returns all entities registered in this world.
	 * 			| for each entity: result:
	 * 			| (entity.getWorld() == this) && (entityClass.isAssignableFrom(entity.getClass()))
	 */
	@SuppressWarnings("unchecked")
	public <T extends Entity> Set<T> getAllEntitiesSpecific(Class<T> entityClass) {
		return helper.deepCopySet(((Set<T>) getAllEntities().stream().
				filter(x -> entityClass.isAssignableFrom(x.getClass())).
				collect(Collectors.toSet())));
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
	 * @see implementation
	 */
	public void destroyOverlaps() throws NullPointerException {
		for (Entity entity1: getAllEntitiesList()) {	// getAllEntitiesList(), returns a new list, iterating is possible.
			if (entity1.isTerminated()) continue;	// If it has been terminated, stop checking for it.
			if (!entity1.isInWorld(this))
				entity1.terminate();	// If the entity "overlaps" the world, terminate it.
			
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
