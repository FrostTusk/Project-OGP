package asteroids.helper;

import asteroids.model.Bullet;
import asteroids.model.Ship;
import asteroids.model.World;

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
 * A class of entities. //TODO Do we need to keep all these invariants? + Any more?
 * 
 * @invar  	The position of each entity must be a valid position for any
 *         	entity.
 *       	| isValidPosition(getPositionX(), getPositionY())
 * @invar  	The speed of each entity must be a valid speed for any
 *         	entity.
 *       	| isValidSpeed(getVelocityX(), getVelocityY())
 * @invar  	Each entity can have its radius as radius.
 *       	| canHaveAsRadius(this.getRadius())
 * @invar  	Each entity must have a valid mass.
 *       	| canHaveAsMass(this.getMass())
 *       
 * @author	Mathijs Hubrechtsen
 */
public abstract class Entity {
	
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
		setMaxpeed(300000);
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
	// TODO Does abstract require extra documentation?
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
	 *       	| ! this.getPosition().isValidPosition(positionX, positonY) TODO is this done right?
	 */
	@Raw
	public void setPosition(double positionX, double positionY) 
			throws IllegalArgumentException {
		try {
			this.position = new Position(positionX, positionY);
		}
		catch(IllegalArgumentException exc) {
			throw new IllegalArgumentException();
		}
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
		if ( (java.lang.Double.isNaN(velocityX)) || (java.lang.Double.isNaN(velocityY)) ) return false;
		if (Math.sqrt((velocityX * velocityX) + (velocityY * velocityY)) > getMaxSpeed()) return false;
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
	public void setMaxpeed(double speed) {
		if (isValidMaxSpeed(speed)) this.maxSpeed = speed;
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
	protected double radius;	
	/**
	* Variable registering the minimum radius of this entity.
	* This minimum radius is hard coded into the sub classes of Entity so that it
	* never changes during the program's execution.
	*/
	protected double minRadius;	// #Constant-2#
	
	
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
	
	
	
			/*
			 * |----------------------------------------------------|
			 * | 5. The next methods handle the mass of the Entity.	|
			 * |----------------------------------------------------| 
			 */
	
	
	
	/**
	 * Variable registering the density of this entity.
	 * The density is always set by the sub classes of Entity. 
	 */
	protected double density;	// #Constant-3#
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
	protected World world;
	

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
	public abstract boolean canHaveAsWorld(World world);
	
	
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
	public boolean isInWorld(World world) {
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
		if (!canHaveAsWorld(world)) throw new IllegalArgumentException();
		this.world = world;
	}
	
//	/**
//	* Remove the current world as world for this entity.
//	*      
//	* @see implementation
//	*/
//	 // TODO @Raw?
//	public void deSetWorld() throws NullPointerException {
//		if (getWorld() ==  null) throw new NullPointerException();
//		this.world = null;
//	}
	
	
	
	
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
	// TODO @Raw?
	public void move(double time) throws IllegalArgumentException {
		if (time < 0) throw new IllegalArgumentException();
			
		double newPositionX = helper.calculatePosition(this, time)[0];
		double newPositionY = helper.calculatePosition(this, time)[1];

		try {
			setPosition(newPositionX, newPositionY);
		}
		catch (IllegalArgumentException exc) {
			throw new IllegalArgumentException();
		}
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
	 * 			// TODO declarative documentation.
	 * 
	 * @throws 	NullPointerException
	 * 			The other entity was null.
	 * 			| entity == null
	 */
	@Raw
	public double getDistanceBetween(Entity entity) throws NullPointerException {
		if (entity == null) throw new NullPointerException();		
		
		double distanceX = Math.sqrt(Math.pow(this.getPosition().getPositionX() - entity.getPosition().getPositionX(), 2));
		double distanceY = Math.sqrt(Math.pow(this.getPosition().getPositionY() - entity.getPosition().getPositionY(), 2));
		
		// Use formula of Pythagoras to calculate total distance between centers of the entities.
		double distance = Math.sqrt(Math.pow(distanceX, 2) + Math.pow(distanceY, 2));
		
		// This clause is necessary because otherwise a negative value is returned when this method
		// is called by an entity on itself. However this value should be 0.
		if (this == entity) return 0;
			
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
	* 			// TODO declarative documentation.
	*/
	@Raw
	public double[] getDistanceBetween(World world) {
		double[] distance = {Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, 
							 Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY};
		if (getWorld() != world) return distance;
		
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
		if (entity == null) throw new NullPointerException();
		if (entity == this) return true;
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
	* Gets the time to collision between this entity and a given world.
	* 
	* @param  	world
	* 			The world to which the collision time needs to be calculated.
	* 
	* @return	The time returned will be larger than 0 and will be equal to
	* 			the time needed for the entity to reach a position where it collides with the boundaries of the world.
	* 			// TODO declarative documentation.
	* 			// TODO apparently collide?
	*/
	@Raw
	public double getTimeToCollision(World world) {		
		double[] distance = getDistanceBetween(world);
		if ( (distance[0] == Double.POSITIVE_INFINITY) || (distance[1] == Double.POSITIVE_INFINITY) ||
			 (distance[2] == Double.POSITIVE_INFINITY) || (distance[3] == Double.POSITIVE_INFINITY) ) return Double.POSITIVE_INFINITY;
		
		double time1; // The shortest time to collision between this entity and the x boundaries of the given world.
		if (getVelocityX() == 0) time1 = Double.POSITIVE_INFINITY;
		if (getVelocityX() < 0) time1 = distance[0] / -getVelocityX();
		else time1 = distance[1] / getVelocityX();
		
		double time2; // The shortest time to collision between this entity and the y boundaries of the given world.
		if (getVelocityY() == 0) time2 = Double.POSITIVE_INFINITY;
		if (getVelocityY() < 0) time2 = distance[2] / -getVelocityY();
		else time2 = distance[3] / getVelocityY();
		
		if (time1 < time2) return time1;		
		return time2;
	}
	
	/**
	* Gets the time to collision between this entity and a given entity.
	* 
	* @param  	entity
	* 			The entity of which the collision time needs to be calculated.
	* 
	* @return	The time returned will be larger than 0 and will be equal to
	* 			the time needed for both entities to reach a position where they will collide with each other.
	* 			// TODO declarative documentation.
	* 			// TODO apparently collide?
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
		if (entity == null) throw new NullPointerException(); 
		if (this.overlap(entity)) throw new IllegalArgumentException();
		if (this.getWorld() != entity.getWorld()) return Double.POSITIVE_INFINITY;	//TODO can they collide in the null world?
		
		double[] deltaR = {entity.getPosition().getPositionX() - this.getPosition().getPositionX(), 
					   	   entity.getPosition().getPositionY() - this.getPosition().getPositionY()};
		double[] deltaV = {entity.getVelocityX() - this.getVelocityX(), 
					   	   entity.getVelocityY() - this.getVelocityY()};
		
		if (helper.evaluateScalar(deltaR, deltaV) >= 0) return Double.POSITIVE_INFINITY;
		double omega = this.getRadius() + entity.getRadius();
		
		double d = ( Math.pow(helper.evaluateScalar(deltaV, deltaR), 2)
					- helper.evaluateScalar(deltaV) * (helper.evaluateScalar(deltaR) - Math.pow(omega, 2)) );
		if (d <= 0) return Double.POSITIVE_INFINITY;
		
		double result = -( (helper.evaluateScalar(deltaV, deltaR) + Math.sqrt(d)) / helper.evaluateScalar(deltaV) );
		if (result < 0 ) return 0;	// TODO Is this right?
		return result;
	}
	
	
	/**
	* Gets the collision position between this entity and a given world.
	* 
	* @param  	world
	* 			The world of which the collision position needs to be calculated.
	* 
	* @return	The position returned will be the position where this ship and the world
	* 			collide with each other. The method returns null if they never collide.
	* 			// TODO declarative documentation.
	* 			// TODO null or infinity if they don't collide?
	* 			// TODO problems with rounding?
	*/
	@Raw
	public double[] getCollisionPosition(World world) {	
		if (! world.containsEntity(this)) return null;
		
		double time = getTimeToCollision(world);
		double[] vector = {Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY};
		if (time == Double.POSITIVE_INFINITY) return vector; // TODO Is this good?
		
		vector = helper.calculatePosition(this, time);
		if (vector[0] + this.getRadius() == world.getWidth()) vector[0] += this.getRadius();
		else if (vector[0] - this.getRadius() == 0) vector[0] = 0;
		if (vector[1] + this.getRadius() == world.getHeight()) vector[1] += this.getRadius();
		else if (vector[1] - this.getRadius() == 0) vector[1] = 0;
		
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
	* 			// TODO declarative documentation.
	* 
	* @throws 	Null
	* 			The other entity was null.
	* 			| entity == null
	*/
	@Raw
	public double[] getCollisionPosition(Entity entity) throws IllegalArgumentException, NullPointerException {
		if (entity == null) throw new NullPointerException();
		if (this.getWorld() != entity.getWorld()) return null;
		double time;
		try {
			time = getTimeToCollision(entity);
		}
		catch (IllegalArgumentException exc) {
			throw new IllegalArgumentException();
		}
		if (time == Double.POSITIVE_INFINITY) return null;
		
		double[] newPosition1 = helper.calculatePosition(this, time);
		double[] newPosition2 = helper.calculatePosition(entity, time);
		
		// Calculate the correct signs to know whether to add/subtract the radius to/from the position calculated further
		double[] signs = calculateSigns(newPosition1, newPosition2);
		
		// Calculate the angle between the x component of the vector between newPosition1 and newPosition2.
		// This angle is the same as the one between the collisionPosition vector (out of the first ship) and it's x component.
		double angle = Math.atan( Math.abs(newPosition2[1] - newPosition1[1]) / 
								  Math.abs(newPosition2[0] - newPosition1[0]) );
		
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
	
	
	// TODO Documentation
	public abstract void resolveCollision(World world);
	public abstract void resolveCollision(Entity entity);
	public abstract void resolveCollisionShip(Ship ship);
	public abstract void resolveCollisionBullet(Bullet bullet);

	
	
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
	 * A method that returns the signs to be used in the getCollisionPosition() method.
	 * Helper method for getCollisionPosition().
	 * 
	 * @param 	position1
	 * 			The first position to be used in the formula.
	 * @param 	position2
	 * 			The second position to be used in the formula.
	 * 
	 * @return 	The signs to be used in the getCollisionPosition() method
	 */
	@Raw
	public double[] calculateSigns(double[] position1, double[] position2) {
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
	 * Returns the type of the class of this entity in string format.
	 * 
	 * @see implementation
	 */
	public abstract String getType();

}
