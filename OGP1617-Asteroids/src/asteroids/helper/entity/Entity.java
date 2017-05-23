package asteroids.helper.entity;

import asteroids.helper.Evolvable;
import asteroids.helper.ExitOutException;
import asteroids.helper.Helper;
import asteroids.helper.Terminateable;
import asteroids.model.*;
import be.kuleuven.cs.som.annotate.*;

/* Constants:
*	1.	maxSpeed = the maximum speed this entity can achieve.
*	2.	minRadius = the minimum radius for each entity.
* 	3.	density = the density for this entity.
*/

/*
 * Methods Index:
 * #1# Basic Methods
 * 		1. Methods that handle the Initialization and Termination of the Entity
 * 		2.	Methods that handle the Position of the Entity
 * 		3. Methods that handle the Speed of the Entity
 * 		4. Methods that handle the Radius of the Entity
 * 		5. Methods that handle the Mass of the Entity
 * #2# Association Methods
 * 		6. Methods that handle the association with Worlds
 * #3# Advanced Methods
 * 		7. Methods that handle Moving and Accelerating
 * 		8. Methods that handle Calculating Distance and Overlap
 * #4# Collision Detection Methods
 * 		9. Methods that handle Collision Detection 
 * 		10. Methods that handle Resolving Collisions
 * #5# Other Methods
 *		11. Helper Methods
 */

/**
 * A class of entities.
 * 
 * @invar  	The position of each entity must be a valid position for any
 *         	entity.
 *       	| this.isValidPosition(getPositionX(), getPositionY())
 * @invar  	The speed of each entity must be a valid speed for any
 *         	entity.
 *       	| this.isValidSpeed(getVelocityX(), getVelocityY())
 * @invar  	Each entity can have its radius as radius.
 *       	| this.canHaveAsRadius(this.getRadius())
 * @invar  	Each entity must have a valid mass.
 *       	| this.canHaveAsMass(this.getMass())
 * @invar	Each entity can have its world as world.
 * 			| this.canHaveAsWorld(this.getWorld())
 * 
 *   
 * @author	Mathijs Hubrechtsen, Ruben Dhuyvetter
 */
public abstract class Entity implements Evolvable, Terminateable {
	
	/*
     * |----------------------------|
     * | #Header-1# Basic Methods.	|
     * |----------------------------| 
     */


		/*
		 * |--------------------------------------------------------------------------------|
		 * | 1. The next method handles the Initialization and Termination of the Entity.	|
		 * |--------------------------------------------------------------------------------| 
		 */
	
	
	
	/**
	 * Variable registering the helper for this ship.
	 */
	protected Helper helper = new Helper();
	
	/**
	 * Default constructor for Entity, should never be used.
	 * An entity should always be either a Ship or a Bullet.
	 * @see implementation
	 */
	public Entity() {
		setMaxspeed(300000);
		this.world = null;
	}
	

	/**
	 * Variable registering if this entity is terminated or not.
	 */
	protected boolean isTerminated = false;
	
	
	/**
	 * Return the whether or not this entity is terminated.
	 */
	@Basic @Raw
	public boolean isTerminated() {
		return this.isTerminated;
	}
	
	
	/**
	 * Terminates this entity.
	 * 
	 * @see implementation.
	 */
	@Raw
	public abstract void terminate();
	
	
	
			 /*
			  * |-----------------------------------------------------------|
			  * | 2. The next methods handle the Position of the Entity.	|
			  * |-----------------------------------------------------------| 
			  */
	
	
	
	/**
	 * Variable registering the current position of this entity.
	 */
	private Position position;
	
	
	/**
	 * Return the current position of this entity.
	 */
	@Basic @Raw 
	public Position getPosition() {
		return this.position;
	}
	
	
	/**
	 * Set the position of this entity to the given position.
	 * 
	 * @param  	positionX
	 *         	The new X position for this entity.
	 * @param  	positionY
	 * 		   	The new Y position for this entity.
	 * 
	 * @post   	The X position of this new entity is equal to
	 *         	the given X position.
	 *       	| new.getPosition().getPositonX() == positionX
	 * @post   	The Y position of this new entity is equal to
	 *         	the given Y position.
	 *       	| new.getPosition().getPositionY() == positionY
	 *       
	 * @throws 	IllegalArgumentException
	 *         	The given position is not a valid position for any
	 *         	bullet.
	 *       	| ! new Position(0,0).isValidPosition(positionX, positionY)
	 */
	@Raw
	public void setPosition(double positionX, double positionY) 
			throws IllegalArgumentException {
		this.position = new Position(positionX, positionY);
	}
	
	
	
			 /*
			  * |---------------------------------------------------|
			  * | 3. The next methods handle the Speed of the Ship.	|
			  * |---------------------------------------------------| 
			  */
	
		
	
	/**
	* Variable registering the max speed of this entity.
	*/
	private double maxSpeed;	// #Constant-1#
	/**
	* Variable registering the X velocity of this entity.
	*/
	private double velocityX;
	/**
	* Variable registering the Y velocity of this entity.
	*/
	private double velocityY;

	
	/**
	* Return the maximum possible speed of this entity.
	*/
	@Basic @Raw
	public double getMaxSpeed() {
		return this.maxSpeed;
	}
	
	/**
	* Return the velocity of this entity.
	*/
	@Basic @Raw
	public double getVelocityX() {
		return this.velocityX;
	}
	
	/**
	* Return the velocity of this entity.
	*/
	@Basic @Raw
	public double getVelocityY() {
		return this.velocityY;
	}
	
	/**
	* Return the current speed of the entity.
	* @return	Returns the speed of the current entity.
	* 			| result == Math.sqrt((this.getVelocityX() * this.getVelocityX()) + (this.getVelocityY() * this.getVelocityY()))
	*/
	@Raw
	public double getSpeed() {
		return Math.sqrt((getVelocityX() * getVelocityX()) + (getVelocityY() * getVelocityY()));
	}
	
	
	/**
	 * Check whether the given speed is a valid maximum speed. It must always
	 * be slower or equal to c (the speed of light).
	 * 
	 * @param	speed
	 * 			The speed to check
	 * 
	 * @return	Returns whether the given speed is a valid maximum speed or not.
	 * 			true if it is, false if not.
	 * 			| result == (speed <= 300000)
	 * 			
	 */
	@Raw
	public boolean isValidMaxSpeed(double speed) {
		return speed <= 300000;
	}
	
	/**
	* Check whether the given velocities are valid velocities for any entity.
	*  
	* @param  	velocityX
	*         	The X velocity to check.
	* @param  	velocityY
	*         	The Y velocity to check.
	*         
	* @return	Returns whether or not the speed made up of the given velocities is a valid speed
	* 			or not. true if it is, false if not.
	*       	| result == (! (isNaN(velocityX)) || (isNaN(velocityY)) ) &&
	*       				Math.sqrt((velocityX * velocityX) + (velocityY * velocityY)) > maxSpeed
	*/
	@Raw
	public boolean isValidSpeed(double velocityX, double velocityY) {
		if ( (java.lang.Double.isNaN(velocityX)) || (java.lang.Double.isNaN(velocityY)) ) 
			return false;
		if (Math.sqrt((velocityX * velocityX) + (velocityY * velocityY)) > getMaxSpeed()) 
			return false;
		return true;
	}
	

	/**
	 * Set the maximum speed of this entity to the given speed.
	 * 
	 * @param	speed
	 * 			The new maximum speed for this entity.
	 * 
	 * @post	If the given speed is a valid maximum speed for any entity,
	 * 			the maximum speed of this entity is set to the given speed.
	 * 			| if (isValidMaxSpeed(speed)
	 * 			|	then new.getMaxSpeed() == speed
	 * @post	If the given speed is not a valid maximum speed for any entity,
	 * 			the maximum speed of this entity is equal to it's last maximum speed.
	 * 			| if (! isValidMaxSpeed(speed))
	 * 			|	then new.getMaxSpeed() == this.getMaxSpeed()	
	 */
	@Raw
	public void setMaxspeed(double speed) {
		if (isValidMaxSpeed(speed)) 
			this.maxSpeed = speed;
	}
	
	/**
	* Set the X and Y velocities of this entity to the given velocities.
	* 
	* @param  	velocityX
	*         	The new X velocity for this entity.
	* @param  	velocityY
	*         	The new Y velocity for this entity.
	*         
	* @post   	If the given velocities are valid velocities for any entity (they make up a valid speed),
	*         	the X and Y velocities of this new entity are equal to the given X and Y velocities.
	*       	| if (isValidVelocity(velocityX, velocityY))
	*       	|   then new.getVelocityX() == velocityX
	*       	|		 new.getVelocityY() == velocityY
	* @post		If the given velocities aren't valid velocities for any entity (they do not make up a valid speed),
	* 			the X and Y velocities of this new ship will be equal to 0.
	* 			| if (! isValidVelocity(velocityX, velocityY))
	*       	|   then new.getVelocityX() == 0
	*       	|		 new.getVelocityY() == 0
	*/
	@Raw
	public void setVelocity(double velocityX, double velocityY) {
		if (isValidSpeed(velocityX, velocityY)) {
			this.velocityX = velocityX;
			this.velocityY = velocityY;
		}
		else {
			this.velocityX = 0;
			this.velocityY = 0;
		}
	}
	
	
	
			/*
			 * |--------------------------------------------------------|
			 * | 4. The next methods handle the Radius of the Entity.	|
			 * |--------------------------------------------------------| 
			 */



	/**
	* Variable registering the radius of this entity.
	*/
	private double radius;	
	/**
	* Variable registering the minimum radius of this entity.
	* This minimum radius is hard coded into the sub classes of Entity so that it
	* never changes during the program's execution.
	*/
	private double minRadius;	// #Constant-2#
	
	
	/**
	* Return the minimum radius of this entity.
	*/
	@Basic @Immutable @Raw
	public double getMinRadius() {
		return this.minRadius;
	}
	
	/**
	* Return the radius of this entity.
	*/
	@Basic @Raw
	public double getRadius() {
		return this.radius;
	}
	
	
	/**
	* Check whether this entity can have the given radius as its radius.
	*  
	* @param  	radius
	*         	The radius to check.
	*         
	* @return	Returns whether or not the given radius is a valid radius
	* 			true if it is, false if not.
	*       	| result == (POSITIVE_INFINITY > radius) && (radius >= this.getMinRadius())
	*/
	@Raw
	public boolean canHaveAsRadius(double radius) {
		return (Double.POSITIVE_INFINITY > radius) && (radius >= this.getMinRadius()); 
	}
	
	
	/**
	 * Set the minimum radius to the given minimum radius.
	 * 
	 * @param 	minRadius
	 * 			The new minimum radius.
	 * 
	 * @post	The minimum radius is equal to the given minimum radius.
	 * 			this.getMinRadius() == minRadius
	 */
	protected void setMinRadius(double minRadius) {
		this.minRadius = minRadius;
	}
	

	/**
	 * Set the radius to the given radius.
	 * 
	 * @param 	radius
	 * 			The new radius.
	 * 
	 * @post	The radius is equal to the given radius.
	 * 			| new.getRadius() == radius
	 * 
	 * @throws 	IllegalArgumentException
	 * 			The given radius is not a valid radius for any entity.
	 * 			! ! this.canHaveAsRadius(radius)
	 */
	protected void setRadius(double radius) throws IllegalArgumentException {
		if (!canHaveAsRadius(radius)) 
			throw new IllegalArgumentException();
		this.radius = radius;
	}

	

			/*
			 * |----------------------------------------------------|
			 * | 5. The next methods handle the mass of the Entity.	|
			 * |----------------------------------------------------| 
			 */
	
	
	
	/**
	 * Variable registering the density of this entity.
	 * The density is always set by the sub classes of Entity. 
	 */
	private double density;	// #Constant-3#
	/**
	* Variable registering the mass of this ship.
	*/
	protected double mass;

	
	/**
	 * Return the current mass of the bullet.
	 */
	@Basic @Raw
	public double getDensity() {
		return this.density;
	}
	
	/**
	* Return the current mass of the ship.
	*/
	@Basic @Raw
	public double getMass() {
		return this.mass;
	}
	
	
	/**
	 * Set the density of this bullet to the given density.
	 * 
	 * @param  	mass
	 * 			the new mass for this bullet
	 *         
	 * @see implementation
	 */
	@Raw
	protected void setDensity(double density) {
		this.density = density;
	}
	
	
	
		/*
	     * |------------------------------------|
	     * | #Header-2# Association Methods.	|
	     * |------------------------------------| 
	     */
		
		
				/*
			     * |------------------------------------------------------------|
			     * | 6. The next methods handle the association with worlds.	|
			     * |------------------------------------------------------------| 
			     */



	/**
	* Variable registering the world of this entity.
	*/
	private World world;
	

	/**
	* Return the world associated with this ship.
	*/
	@Basic @Raw
	public World getWorld() {
		return this.world;
	}

	
	/**
	* Check whether this entity can have the given world as world. 
	*  
	* @param  	world
	*         	The world to check.
	*         
	* @see implementation
	*/
	public boolean canHaveAsWorld(World world) {
		if (world == null) 
			return true;
		return ( (getWorld() == null) || (getWorld() == world) ) && (isInWorld(world)) && !(world.isTerminated());
	}
	
	
	/**
	* Check whether this ship is currently located in a given world. 
	* This method only checks if the shape of this entity is located in a given world,
	* it does not check if it's associated with the given world.
	*  
	* @param  	world
	*         	The world to check.
	*         
	* @see implementation
	*/
	@Raw
	public boolean isInWorld(World world) throws NullPointerException {
		return helper.apparentlyWithinBoundaries(this, world);
	}
	

	/**
	* Set a given world as world for this entity.
	*  
	* @param  	world
	*         	The world to set as world for this entity.
	*         
	* @see implementation
	*/
	@Raw
	public void setWorld(World world) throws IllegalArgumentException {
		if (!canHaveAsWorld(world)) 
			throw new IllegalArgumentException();
		this.world = world;
	}
	

	
		/*
	     * |--------------------------------|
	     * | #Header-3# Advanced Methods.	|
	     * |--------------------------------| 
	     */


			/*
			 * |------------------------------------|
			 * | 7. The next methods handle Moving.	|
			 * |------------------------------------| 
			 */
	
	
	
	/**
	 * Change the position of this entity based on the current
	 * position, velocity and a given time duration time.
	 * 
	 * @param  	time
	 * 			the time duration in which this entity will move.
	 * 
	 * @post   	The X position of this new entity is equal to
	 *         	the current X position times the X velocity times the given time.
	 *       	| new.getPositionX() == this.getpositionX() * this.getVelocityX() * time
	 * @post   	The Y position of this new entity is equal to
	 *         	the current X position times the Y velocity times the given time.   	
	 *       	| new.getPositionY() == this.positionY() * this.getVelocityY() * time
	 *       
	 * @throws 	IllegalArgumentException
	 *         	The time was less than 0.
	 *       	| time < 0     
	 * @throws 	IllegalArgumentException
	 *         	The new position for this entity is not a valid position for any entity.
	 *       	| ! isValidPosition(new.getPositionX(), new.getPositionY())
	 */
	@Raw
	public void move(double time) throws IllegalArgumentException {
		if (time < 0) 
			throw new IllegalArgumentException();
			
		double newPositionX = helper.calculatePosition(this, time)[0];
		double newPositionY = helper.calculatePosition(this, time)[1];

		setPosition(newPositionX, newPositionY);
	}
	
	
	
			/*
			 * |----------------------------------------------------------------|
			 * | 8. The next methods handle Calculating Distance and Overlap.	|
			 * |----------------------------------------------------------------| 
			 */
	
	
	
	/**
	 * Gets the distance between this entity and another given entity.
	 * This distance is the distance between the edges of both entities.
	 * 
	 * @param  	entity
	 * 			the entity to which you want to know the distance.
	 * 
	 * @return	For the current entity, returns the distance between the current entity and a given entity.
	 * 			If the two entities have the same position, the method returns zero.
	 * 
	 * @throws 	NullPointerException
	 * 			The other entity was null.
	 * 			| entity == null
	 */
	@Raw
	public double getDistanceBetween(Entity entity) throws NullPointerException {
		if (entity == null) 
			throw new NullPointerException();		
		
		double distanceX = Math.sqrt(Math.pow(this.getPosition().getPositionX() - entity.getPosition().getPositionX(), 2));
		double distanceY = Math.sqrt(Math.pow(this.getPosition().getPositionY() - entity.getPosition().getPositionY(), 2));
		
		// Use formula of Pythagoras to calculate total distance between centers of the entities.
		double distance = Math.sqrt(Math.pow(distanceX, 2) + Math.pow(distanceY, 2));
		
		// This clause is necessary because otherwise a negative value is returned when this method
		// is called by an entity on itself. However this value should be 0.
		if (this == entity) 
			return 0;
			
		// Use the radius' of the ships to calculate distance between the edges.
		return distance - this.getRadius() - entity.getRadius();
	}
	
	
	/**
	* Gets the distance between this ship and the boundaries of it's world.
	* This distance is the distance between the edge of this entity and the boundaries.
	* 
	* @param 	world
	* 			the world to which to calculate the distance
	* 
	* @return 	returns the distance between this entity and the boundaries of it's world.
	* 			position 0 contains the distance between this entity and boundary at x = 0.
	* 			position 1 contains the distance between this entity and boundary at x = world.getWidth().
	* 			position 2 contains the distance between this entity and boundary at y = 0.
	* 			position 3 contains the distance between this entity and boundary at y = world.getHeight().
	*/
	@Raw
	public double[] getDistanceBetween(World world) {
		double[] distance = {Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, 
							 Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY};
		if ( (getWorld() != world) || (getWorld() == null) ) 
			return distance;
		
		distance[0] = getPosition().getPositionX() - getRadius();
		distance[1] = world.getWidth() - (getPosition().getPositionX() + getRadius());
		distance[2] = getPosition().getPositionY() - getRadius();
		distance[3] = world.getHeight() - (getPosition().getPositionY() + getRadius());
		return distance;
	}
	
	
	/**
	* Returns true if the entities overlap, false if they don't.
	* This method uses the notation of significant overlap.
	* 
	* @param 	entity
	* 			the entity of which you want to if it it overlaps with this entity.
	* 
	* @return	For the current ship, returns true if it overlaps with the given entity,
	* 			false if it does not.
	* 			| result == helper.significantOverlap(this, entity, this.getDistanceBetween(entity))
	* 
	* @throws 	NullPointerException
	* 			the other entity was null.
	* 			| entity == null
	*/
	@Raw
	public boolean overlap(Entity entity) throws NullPointerException {
		if (entity == null) 
			throw new NullPointerException();
		if (entity == this) 
			return true;
		return helper.significantOverlap(this, entity, this.getDistanceBetween(entity) + this.getRadius() + entity.getRadius());
	}
	
	
	
		/*
	     * |--------------------------------------------|
	     * | #Header-4# Collision Detection Methods.	|
	     * |--------------------------------------------| 
	     */
	
	
				/*
				 * |----------------------------------------------------|
				 * | 9. The next methods handle Collision Detection.	|
				 * |----------------------------------------------------| 
				 */
	
	
	
	/**
	 * Gets the boundaries involved in the collision. So, if there is a collision, returns
	 * which boundaries are involved in it.
	 * 
	 * @param  	world
	 * 			The world to which the collision boundaries need to be calculated.
	 * 
	 * @return	If there is collision, returns which boundaries are involved in it. Else the closest boundaries
	 * 			are returned.
	 * 			| (this.setPosition(new Position(result[0], result[1])).getDistanceBetween(entity) == 0
	 */
	@Raw
	public double[] getCollisionBoundaries(World world) {
		double[] distanceToBoundaries = this.getDistanceBetween(world);
		int minimumBoundary = 0;
		double[] result = new double[2]; 
		for (int i = 1; i < 4; i++) {
			if (distanceToBoundaries[i] == distanceToBoundaries[minimumBoundary]) {
				minimumBoundary = i;
				result[0] = minimumBoundary;
				result[1] = 4;
			}
			else if (distanceToBoundaries[i] <= distanceToBoundaries[minimumBoundary])
				result[1] = i;
		}
		return result;
	}
	
	
	/**
	 * Gets the time until the second non zero collision with a boundary by this entity.
	 * Helper Method used to fix the Salamander bug in World.
	 * 
	 * @param  	world
	 * 			he world to which the collision time needs to be calculated.
	 * 
	 * @return	The time returned will be equal to the time to second non zero collision with a
	 * 			boundary and this entity.
	 * 			| (this.move(result)).getDistanceBetween(entity) == 0
	 */
	@Raw
	public double getTimeToSecondCollisionNonZero(World world) throws NullPointerException {
		double[] distance = getDistanceBetween(world);	
		double[] timeNew = new double[6];
		timeNew[0] = Double.POSITIVE_INFINITY;
		timeNew[1] = distance[0] / -getVelocityX();
		timeNew[2] = distance[1] / getVelocityX();
		timeNew[3] = Double.POSITIVE_INFINITY;
		timeNew[4] = distance[2] / -getVelocityY();
		timeNew[5] = distance[3] / getVelocityY();
		int smallest = helper.findSecondSmallestNotZero(timeNew);
		return timeNew[smallest];
	}

	
	/**
	 * Gets the time to collision between this entity and a given world. This method
	 * is not the one described in the assignment, it does not account for the thruster.
	 * 
	 * @param  	world
	 * 			The world to which the collision time needs to be calculated.
	 * 
	 * @return	The time returned will be larger than 0 and will be equal to
	 * 			the time needed for the entity to reach a position where it collides with the boundaries of the world.
	 * 			| (this.move(result)).getDistanceBetween(entity) == 0
	 */
	@Raw
	public double getTimeToCollision(World world) {	
		if (world == null)
			return Double.POSITIVE_INFINITY;
		double[] distance = getDistanceBetween(world);
		if ( (distance[0] == 0) || (distance[1] == world.getWidth()) || (distance[2] == 0) || (distance[3] == world.getHeight()) )
			return 0;
		
		double time1; // The shortest time to collision between this entity and the x boundaries of the given world.
		if (getVelocityX() == 0) 
			time1 = Double.POSITIVE_INFINITY;
		else if (getVelocityX() < 0) 
			time1 = distance[0] / -getVelocityX();
		else 
			time1 = distance[1] / getVelocityX();
		
		double time2; // The shortest time to collision between this entity and the y boundaries of the given world.
		if (getVelocityY() == 0) 
			time2 = Double.POSITIVE_INFINITY;
		else if (getVelocityY() < 0) 
			time2 = distance[2] / -getVelocityY();
		else 
			time2 = distance[3] / getVelocityY();
		
		return (time1 < time2) ? time1: time2;
	}
	
	/**
	* Gets the time to collision between this entity and a given entity.
	* 
	* @param  	entity
	* 			The entity of which the collision time needs to be calculated.
	* 
	* @return	The time returned will be larger than 0 and will be equal to
	* 			the time needed for both entities to reach a position where they will collide with each other.
	* 			| (this.move(result)).getDistanceBetween(entity) == 0
	* 
	* @throws 	IllegalArgumentException
	* 			The entity overlaps this entity.
	* 			| entity == this
	* @throws 	NullPointerException
	* 			The other entity was null.
	* 			| entity == null
	*/
	@Raw
	public double getTimeToCollision(Entity entity) throws IllegalArgumentException, NullPointerException {
		if (entity == null) 
			throw new NullPointerException(); 
		if (this.overlap(entity)) 
			throw new IllegalArgumentException();
		if ( (this.getWorld() != entity.getWorld()) || (this.getWorld() == null) ) 
			return Double.POSITIVE_INFINITY;
		
		double[] deltaR = {entity.getPosition().getPositionX() - this.getPosition().getPositionX(), 
					   	   entity.getPosition().getPositionY() - this.getPosition().getPositionY()};
		double[] deltaV = {entity.getVelocityX() - this.getVelocityX(), 
					   	   entity.getVelocityY() - this.getVelocityY()};
		
		if (helper.evaluateScalar(deltaR, deltaV) >= 0  || (Double.isNaN(helper.evaluateScalar(deltaR, deltaV))) ) 
			return Double.POSITIVE_INFINITY;
		double omega = this.getRadius() + entity.getRadius();
		
		double d = ( Math.pow(helper.evaluateScalar(deltaV, deltaR), 2)
					- helper.evaluateScalar(deltaV) * (helper.evaluateScalar(deltaR) - Math.pow(omega, 2)) );
		if (d <= 0) 
			return Double.POSITIVE_INFINITY;
		
		double result = -( (helper.evaluateScalar(deltaV, deltaR) + Math.sqrt(d)) / helper.evaluateScalar(deltaV) );
		return (result < 0) ? 0: result;
	}
	
	
	/**
	* Gets the collision position between this entity and a given world.
	* 
	* @param  	world
	* 			The world of which the collision position needs to be calculated.
	* 
	* @return	The position returned will be the position where this ship and the world
	* 			collide with each other. The method returns infinity if they never collide.
	* 			| (this.setPosition(new Position(result[0], result[1]))).getDistanceBetween(world) == 0
	*/
	@Raw
	public double[] getCollisionPosition(World world) {	
		double time = getTimeToCollision(world);
		if (time == Double.POSITIVE_INFINITY) 
			return null;
		
		double[] vector = createDefaultVector(time);	// First create a default vector.
		if (vector[0] + this.getRadius() == world.getWidth()) 
			vector[0] += this.getRadius();	// Get the first collision position.
		else if (vector[0] - this.getRadius() == 0) 
			vector[0] = 0;
		else if (vector[1] + this.getRadius() == world.getHeight()) 
			vector[1] += this.getRadius();
		else if (vector[1] - this.getRadius() == 0) 
			vector[1] = 0;
		
		if ( (vector[0] != vector[2]) && ( (vector[3] + this.getRadius() == world.getHeight()) || // To cover corners:
			 (vector[3] - this.getRadius() == 0)) ) { // If there is a collision with the x boundary, we still have to check 
			if (vector[3] + this.getRadius() == world.getHeight()) 
				vector[3] += this.getRadius();	// if there is a collision with the y boundary.
			else if (vector[3] - this.getRadius() == 0) 
				vector[3] = 0;								
		}
		else {
			vector[2] = Double.POSITIVE_INFINITY;
			vector[3] = Double.POSITIVE_INFINITY;
		}
		return vector;	
	}
	
	/**
	* Gets the collision position between this entity and a given entity.
	* 
	* @param  	entity
	* 			The entity of which the collision position needs to be calculated.
	* 
	* @return	The position returned will be the position where both entities
	* 			collide with each other. The method returns null if they never collide.
	* 			| (this.setPosition(new Position(result[0], result[1]))).getDistanceBetween(world) == 0
	* 
	* @throws 	IllegalArgumentException
	* 			The entity overlaps this entity.
	* 			| entity == this
	* @throws 	NullPointerException
	* 			The other entity was null.
	* 			| entity == null
	*/
	@Raw
	public double[] getCollisionPosition(Entity entity) throws IllegalArgumentException, NullPointerException {
		if (entity == null) 
			throw new NullPointerException();
		
		double time = getTimeToCollision(entity);
		if (time == Double.POSITIVE_INFINITY) return null;
		if (this.getWorld() != entity.getWorld()) return null;
		
		double[] newPosition1 = helper.calculatePosition(this, time);
		double[] newPosition2 = helper.calculatePosition(entity, time);
		
		// Calculate the correct signs to know whether to add/subtract the radius to/from the position calculated further
		double[] signs = calculateSigns(newPosition1, newPosition2);
		
		// Calculate the angle between the x component of the vector between newPosition1 and newPosition2.
		// This angle is the same as the one between the collisionPosition vector (out of the first ship) and it's x component.
		double angle = Math.atan( Math.abs(newPosition2[1] - newPosition1[1]) / Math.abs(newPosition2[0] - newPosition1[0]) );
								  
		// Calculate the exact position vector of the collision point by using the angle that has just been calculated
		// and the first ship's new position vector.
		double vector[] = {newPosition1[0] + signs[0] * this.getRadius() * Math.cos(angle), 
					   	   newPosition1[1] + signs[1] * this.getRadius() * Math.sin(angle)};
		return vector;
	}
	
	
	
			/*
			 * |----------------------------------------------------|
			 * | 10. The next methods handle resolving Collisions.	|
			 * |----------------------------------------------------| 
			 */
	
	
	
	/**
	 * Resolves the collision between this entity and a given world.
	 * 
	 * @param 	ship
	 * 			The ship to be used.
	 * 
	 * @see implementation
	 */
	public void resolveCollision(World world) throws NullPointerException {
		if (world == null) 
			throw new NullPointerException();
		
		double[] position = getCollisionPosition(world);
		if (position[0] == Double.POSITIVE_INFINITY && position[1] == Double.POSITIVE_INFINITY) return;	
		// There is no collision so the collision does not need to be resolved.
		if (position[0] == this.world.getWidth() || position[0] == 0) 
			setVelocity(-getVelocityX(), getVelocityY());
		if ( (position[1] == this.world.getHeight() || position[1] == 0) ||
			 (position[3] == this.world.getHeight() || position[3] == 0) )
			setVelocity(getVelocityX(), -getVelocityY()); 
	}
	
	
	/**
	 * Resolves the collision between this entity and a given entity.
	 * 
	 * @param 	ship
	 * 			The ship to be used.
	 * 
	 * @see implementation
	 */
	public void resolveCollision(Entity entity) throws NullPointerException {
		if (entity == null) 
			throw new NullPointerException();
		
		if (entity.getType() == EntityType.SHIP) 
			resolveCollisionShip((Ship) entity);
		else if (entity.getType() == EntityType.BULLET) 
			resolveCollisionBullet((Bullet) entity);
		else if (MinorPlanet.class.isAssignableFrom(entity.getClass()))
			resolveCollisionMinorPlanet((MinorPlanet) entity);
	}
	
	/**
	 * Resolves the collision between this entity and a given ship.
	 * 
	 * @param 	ship
	 * 			The ship to be used.
	 * 
	 * @see implementation
	 */
	public abstract void resolveCollisionShip(Ship ship);
	
	/**
	 * Resolves the collision between this entity and a given bullet.
	 * 
	 * @param 	ship
	 * 			The ship to be used.
	 * 
	 * @see implementation
	 */
	public void resolveCollisionBullet(Bullet bullet) {
		this.terminate();
		bullet.terminate();	
	}

	/**
	 * Resolves the collision between this entity and a given MinorPlanet.
	 * 
	 * @param 	minorPlanet
	 * 			The minorPlanet to be used.
	 * 
	 * @see implementation
	 */
	public void resolveCollisionMinorPlanet(MinorPlanet minorPlanet) throws NullPointerException {
		minorPlanet.resolveCollision(this); 
	}	// This method will only be called by another minor planet. In minor planet this method is overridden.
	
	
	
		/*
	     * |----------------------------|
	     * | #Header-5# Other Methods.	|
	     * |----------------------------| 
	     */
	
	
	
			/*
		     * |--------------------------------------------|
		     * | 11. The next methods are Helper Methods.	|
		     * |--------------------------------------------| 
		     */

	
	
	/**
	 * Helper method of evolve in World.
	 */
	@Override
	public void morph(double time) throws ExitOutException, IllegalArgumentException, NullPointerException {
		move(time);
	}
	
	
	/**
	 * A method that returns the default vector for the getCollisionPosition(World world) method.
	 * Helper method for getCollisionPosition(World world).
	 * 
	 * @param 	time
	 * 			The time until collision with the boundary.
	 * 
	 * @return 	Returns the default vector to be used in the getCollisionPosition(World world) method.
	 */
	@Raw
	public double[] createDefaultVector(double time) {
		double[] vector = new double[4];
		vector[0] = helper.calculatePosition(this, time)[0];
		vector[1] = helper.calculatePosition(this, time)[1];
		vector[2] = helper.calculatePosition(this, time)[0];
		vector[3] = helper.calculatePosition(this, time)[1];
		return vector;
	}
	
	
	/**
	 * A method that returns the signs to be used in the getCollisionPosition(Entity entity) method.
	 * Helper method for getCollisionPosition(Entity entity).
	 * 
	 * @param 	position1
	 * 			The first position to be used in the formula.
	 * @param 	position2
	 * 			The second position to be used in the formula.
	 * 
	 * @return 	Returns the signs to be used in the getCollisionPosition() method
	 */
	@Raw
	private double[] calculateSigns(double[] position1, double[] position2) {
		double signX1 = 1;
		if (position1[0] > position2[0])
			signX1 = -1;
		double signY1 = 1;
		if (position1[1] > position2[1])
			signY1 = -1;
		
		double[] result = {signX1, signY1};
		return result;
	}
	
	
	/**
	 * Returns the type of the class of this entity in enumeration format.
	 * 
	 * @see implementation
	 */
	@Raw
	public abstract EntityType getType();

}
