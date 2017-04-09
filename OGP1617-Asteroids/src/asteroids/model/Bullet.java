package asteroids.model;

import asteroids.helper.Entity;
import asteroids.helper.Helper;
import asteroids.helper.Position;

import be.kuleuven.cs.som.annotate.*;

/* Constants:
*	1.	constantMaxSpeed = the maximum speed this bullet can achieve.
*	2.	minRadius =  the minimum radius for each bullet.
*	3.	density = the density for this bullet.
*/

/*
 * Methods Index:
 * #1# Basic Methods
 * 		1. Methods that handle the Initialization and Termination of the Bullet
 * 		2. Methods that handle the Position of the Bullet
 * 		3. Methods that handle the Speed of the Bullet
 * 		4. Methods that handle the Radius of the Bullet
 * 		5. Methods that handle the Mass of the Bullet
 * #2# Association Methods
 * 		6. Methods that handle the association with Worlds
 * 		7. Methods that handle the association with Bullets
 * #3# Advanced Methods
 * 		8. Methods that handle Moving and Accelerating
 * 		9. Methods that handle Calculating Distance and Overlap
 * #4# Collision Detection Methods
 * 		10. Methods that handle Collision Detection
 * 		11. Methods that handle Resolving Collisions
 * #5# Other Methods
 *		12. Helper Methods
 */

/**
 * A class of bullets.
 * 
* @invar  	The position of each bullet must be a valid position for any
*         	bullet.
*       	| this.getPosition().isValidPosition(this.position.getPositionX(), this.position.getPositionY())
* @invar  	The speed of each bullet must be a valid speed for any
*         	bullet.
*       	| this.isValidSpeed(getVelocityX(), getVelocityY())
* @invar  	Each bullet can have its radius as radius.
*       	| this.canHaveAsRadius(this.getRadius())
* @invar  	Each bullet must have a valid mass.
*       	| this.canHaveAsMass(this.getMass())
*/
public class Bullet extends Entity {
	
		/*
	     * |----------------------------|
	     * | #Header-1# Basic Methods.	|
	     * |----------------------------| 
	     */
	
	
			/*
			 * |----------------------------------------------------------------|
			 * | 1. The next method handles the Initialization of the bullet.	|
			 * |----------------------------------------------------------------| 
			 */

	
	
	/**
	 * Variable registering the helper for this ship.
	 */
	private Helper helper = new Helper();
	
	/**
	 * Initialize this new bullet with given X and Y position, a given X and Y velocity, a given orientation, and a given radius.
	 *
	 * @param  	positionX
	 *         	The X position for this new bullet.
	 * @param  	positionY
	 * 			The Y position of this new bullet.
	 * @param  	velocityX
	 *         	The X velocity for this new bullet.
	 * @param	velocityY
	 * 			The Y velocity for this new bullet.
	 * @param  	radius
	 *         	The radius for this new bullet.
	 * @param  	mass
	 *         	The mass for this new bullet.
	 * 
	 * @post   	The radius of this new bullet is equal to the given
	 *         	radius.
	 *       	| new.getRadius() == radius
	 *       
	 * @effect 	The positions of this new bullet are set to
	 *         	the given X position and Y position.
	 *       	| this.setPosition(positionX, positionY)
	 * @effect	The X and Y velocity of this new bullet are set to
	 *         	the given velocityX and velocityY.
	 *         	| this.setVelocity(velocityX, velocityY)
	 * @effect	The mass of this new bullet is set to
	 *         	the given mass.
	 *         	| this.setMass(mass)
	 *         	 
	 * @throws 	IllegalArgumentException
	 *         	This new bullet cannot have the given X and Y position as position.
	 *       	| ! this.getPosition().isValidPosition(positionX, positionY)
	 * @throws 	IllegalArgumentException
	 *         	This new bullet cannot have the given radius as its radius.
	 *       	| ! this.canHaveAsRadius(this.getRadius())
	 */
	public Bullet(double positionX, double positionY, double velocityX, double velocityY, double radius)
		throws IllegalArgumentException {
		try {
			setPosition(positionX, positionY);
		}
		catch (IllegalArgumentException exc) {
			throw new IllegalArgumentException();
		}

		this.setVelocity(velocityX, velocityY);
		
		
		if (! canHaveAsRadius(radius))
			throw new IllegalArgumentException();
		this.radius = radius;
		
		
		
	}
	
	
	/**
	 * Variable registering if this bullet is terminated or not.
	 */
	private boolean isTerminated = false;
	
	
	/**
	 * Return the whether or not this bullet is terminated.
	 */
	@Basic @Raw
	public boolean isTerminated() {
		return this.isTerminated;
	}
	
	
	/**
	 * Terminates this bullet. Breaks up any associations with entities and worlds.
	 * Prepares this object for the garbage collector.
	 * @see implementation
	 */
	public void terminate() {
		if (getWorld() != null) world.removeEntity(this);
		if (getShip() != null) this.setShip(null);
	}
	

			/*
			 * |--------------------------------------------------------|
			 * | 2. The next methods handle the Position of the bullet.	|
			 * |--------------------------------------------------------| 
			 */
	
	
	
	/**
	 * Variable registering the current position of this ship.
	 */
	private Position position;
	
	
	/**
	 * Return the current position of this bullet.
	 */
	@Basic @Override @Raw 
	public Position getPosition() {
		return this.position;
	}
	
	
	/**
	 * Set the position of this bullet to the given position.
	 * 
	 * @param  	positionX
	 *         	The new X position for this bullet.
	 * @param  	positionY
	 * 		   	The new Y position for this bullet.
	 * 
	 * @post   	The X position of this new bullet is equal to
	 *         	the given X position.
	 *       	| new.getPosition().getPositonX() == positionX
	 * @post   	The Y position of this new bullet is equal to
	 *         	the given Y position.
	 *       	| new.getPosition().getPositionY() == positionY
	 *       
	 * @throws 	IllegalArgumentException
	 *         	The given position is not a valid position for any
	 *         	bullet.
	 *       	| ! this.getPosition().isValidPosition(positionX, positonY)
	 */
	@Raw
	public void setPosition(double positionX, double positionY) 
			throws IllegalArgumentException {
		try {
			position = new Position(positionX, positionY);
		}
		catch (IllegalArgumentException exc){
			throw new IllegalArgumentException();
		}
	}

	
	
			/*
			 * |--------------------------------------------------------|
			 * | 3. The next methods handle the Speed of the bullet.	|
			 * |--------------------------------------------------------| 
			 */
	
	
	
	/**
	 * Variable registering the X velocity of this bullet.
	 */
	private double velocityX;
	/**
	 * Variable registering the Y velocity of this bullet.
	 */
	private double velocityY;
	/**
	 * Variable registering the speed of light.
	 */
	private double constantMaxSpeed = 300000;
	
	
	/**
	 * Return the X velocity of this bullet.
	 */
	@Basic @Override @Raw
	public double getVelocityX() {
		return this.velocityX;
	}
	
	/**
	 * Return the Y velocity of this bullet.
	 */
	@Basic @Override @Raw
	public double getVelocityY() {
		return this.velocityY;
	}
	
	/**
	 * Return the maximum possible speed of this bullet.
	 */
	@Basic @Raw
	public double getConstantMaxSpeed() {
		return this.constantMaxSpeed;
	}

	/**
	 * Return the current speed of the bullet.
	 * @return	Returns the speed of the current bullet.
	 */
	@Raw
	public double getSpeed() {
		return Math.sqrt((getVelocityX() * getVelocityX()) + (getVelocityY() * getVelocityY()));
	}
	
	
	/**
	 * Check whether the given velocities are valid velocities for any bullet.
	 *  
	 * @param  	velocityX
	 *         	The X velocity to check.
	 * @param  	velocityY
	 *         	The Y velocity to check.
	 * @return	Returns whether or not the speed made up of the given velocities is a valid speed
	 * 			or not. true if it is, false if not.
	 *       	| result == (! (isNaN(velocityX)) || (isNaN(velocityY)) ) &&
	 *       				Math.sqrt((velocityX * velocityX) + (velocityY * velocityY)) > constantMaxSpeed
	 */
	@Raw
	public boolean isValidSpeed(double velocityX, double velocityY) {
		if ( (java.lang.Double.isNaN(velocityX)) || (java.lang.Double.isNaN(velocityY)) )
			return false;
		if (Math.sqrt((velocityX * velocityX) + (velocityY * velocityY)) > getConstantMaxSpeed())
			return false;
		return true;
	}
	
	
	/**
	 * Set the X and Y velocities of this bullet to the given velocities.
	 * 
	 * @param  	velocityX
	 *         	The new X velocity for this bullet.
	 * @param  	velocityY
	 *         	The new Y velocity for this bullet.
	 *         
	 * @post   	If the given velocities are valid velocities for any bullet (they make up a valid speed),
	 *         	the X and Y velocities of this new bullet are equal to the given X and Y velocities.
	 *       	| if (isValidVelocity(velocityX, velocityY)))
	 *       	|   then new.getVelocityX() == velocityX
	 *       	|		 new.getVelocityY() == velocityY
	 * @post	If the given velocities aren't valid, the X and Y velocities
	 * 			of this new bullet will be equal to 0.
	 *       	| if (! isValidVelocity(velocityX, velocityY)))
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
			  * |-------------------------------------------------------|
			  * | 4. The next methods handle the Radius of the bullet.	|
			  * |-------------------------------------------------------| 
			  */
		
	
	
	/**
	* Variable registering the radius of this bullet.
	*/
	private final double radius;
	/**
	* Variable registering the minimum radius of this bullet.
	*/
	private final double minRadius = 1;
	
	
	/**
	*  Return the minimum radius of this bullet.
	*/
	@Basic @Immutable @Raw
	public double getMinRadius() {
		return this.minRadius;
	}
	
	/**
	* Return the radius of this bullet.
	*/
	@Basic @Override @Raw
	public double getRadius() {
		return this.radius;
	}
	
	
	/**
	* Check whether this bullet can have the given radius as its radius.
	*  
	* @param  	radius
	*         	The radius to check.
	* @return	Returns whether or not the given radius can be used 
	* 			as a valid radius or not. true if it can, false if not.
	*       	| result == (POSITIVE_INFINITY > radius) && (radius >= this.getMinRadius())
	*/
	@Raw
	public boolean canHaveAsRadius(double radius) {
		if ( (Double.POSITIVE_INFINITY > radius) && (radius >= getMinRadius()) )
			return true;
		return false;
	}
	
	
	
			/*
		     * |----------------------------------------------------|
		     * | 5. The next methods handle the Mass of the bullet.	|
		     * |----------------------------------------------------| 
		     */
	
	
	
	/**
	 * Variable registering the mass of this bullet.
	 */
	private double mass;
	/**
	 * Variable registering the density of this bullet.
	 */
	private double density = 7.8 * Math.pow(10, 12);
	
	
	/**
	 * Return the current mass of the bullet.
	 */
	@Basic @Raw
	public double getMass() {
		return this.mass;
	}
	
	/**
	 * Return the current mass of the bullet.
	 */
	@Basic @Raw
	public double getDensity() {
		return this.density;
	}
	
	
	/**
	 * Set the mass of this bullet to the given mass.
	 * 
	 * @param  	mass
	 * 			the new mass for this bullet
	 *         
	 * @see implementation
	 */
	@Raw
	public void setMass(double mass) {
		this.mass = (4/3) * Math.PI * Math.pow(getRadius(), 3) * (density);
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
	private void setDensity(double density) {
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
	* Variable registering the world of this bullet.
	*/
	private World world = null;
	
	
	/**
	* Return the world of this bullet.
	*/
	@Basic @Override @Raw
	public World getWorld() {
		return this.world;
	}
	
	
	/**
	* Check whether this bullet can have the given world as world. 
	*  
	* @param  	world
	*         	The world to check.
	*         
	* @see implementation
	*/
	@Override @Raw
	public boolean canHaveAsWorld(World world) throws IllegalArgumentException, NullPointerException {
		if ((getWorld() != null) && (isInWorld(world)) ) return true;
		return false;
	}
	
	/**
	* Check whether this bullet is currently located in a given world. 
	* This method only checks if the shape of the bullet is located in a given world,
	* it does not check if it's associated with the given world.
	*  
	* @param  	world
	*         	The world to check.
	*         
	* @see implementation
	*/
	@Override @Raw
	public boolean isInWorld(World world) {
		if ( (this.getPosition().getPositionX() - this.getRadius() >= 0) &&
			 (this.getPosition().getPositionX() + this.getRadius() <= world.getWidth()) &&
			 (this.getPosition().getPositionY() - this.getRadius() >= 0) &&
			 (this.getPosition().getPositionY() + this.getRadius() <= world.getHeight()) )
			return true;
		return false;
	}
	
	
	/**
	* Set a given world as world for this bullet.
	*  
	* @param  	world
	*         	The world to set as world for this bullet.
	*         
	* @see implementation
	*/
	@Override @Raw
	public void setWorld(World world) throws IllegalArgumentException, NullPointerException {
		if (world ==  null) throw new NullPointerException();
		if (!canHaveAsWorld(world)) throw new IllegalArgumentException();
	this.world = world;
	}
	
	/**
	* Remove the current world as world for this bullet.
	*      
	* @see implementation
	*/
	@Override // TODO @Raw?
	public void deSetWorld() throws NullPointerException {
		if (getWorld() ==  null) throw new NullPointerException();
		this.world = null;
	}
	
	
	
		/*
	     * |--------------------------------------------------------|
	     * | 7. The next methods handle the association with ships.	|
	     * |--------------------------------------------------------| 
	     */
	
	
	
	/**
	 * Variable registering whether or not this bullet has been fired.
	 */
	private boolean hasBeenFired = false;
	/**
	* Variable registering the ship of this bullet.
	*/
	private Ship ship = null;
	/**
	 * Variable registering the source ship of this bullet.
	 */
	private Ship source;
	
	
	/**
	* Return whether or not this bullet has already been fired.
	*/
	@Basic @Raw
	public boolean hasBeenFired() {
		return this.hasBeenFired;
	}
	
	
	/**
	* Return the ship of this bullet.
	*/
	@Basic @Raw
	public Ship getShip() {
		return this.ship;
	}
	
	/**
	* Return the source of this bullet.
	*/
	@Basic @Raw
	public Ship getSource() {
		return this.source;
	}
	
	
	/**
	* Check whether this bullet can have the given ship as source. 
	*  
	* @param  	ship
	*         	The ship to check.
	*         
	* @see implementation
	*/
	@Raw
	public boolean canHaveAsSource(Ship ship) {
		return (!hasBeenFired) && (getShip() == ship);
	}
	
	/**
	* Check whether this bullet can have the given ship as ship. 
	*  
	* @param  	ship
	*         	The ship to check.
	*         
	* @see implementation
	*/
	@Raw
	public boolean canHaveAsShip(Ship ship) {
		return ( (getShip() == null) && (getWorld() == null) );
	}
	
	
	/**
	* Set a given ship as source for this bullet.
	*  
	* @param  	ship
	*         	The ship to set as ship for this bullet.
	*         
	* @see implementation
	*/
	@Raw
	public void setSource(Ship ship) {
		if (canHaveAsSource(ship)) {
			this.hasBeenFired = true;
			this.source = ship;
		}
	}
	
	/**
	* Set a given ship as ship for this bullet.
	*  
	* @param  	ship
	*         	The ship to set as ship for this bullet.
	*         
	* @see implementation
	*/
	@Raw
	public void setShip(Ship ship) throws IllegalArgumentException, NullPointerException {
		if (ship == null) throw new NullPointerException();
		if (canHaveAsShip(ship)) this.ship = ship;
		else throw new IllegalArgumentException();
	}
	
	
	
		/*
	     * |--------------------------------|
	     * | #Header-3# Advanced Methods.	|
	     * |--------------------------------| 
	     */

	
			/*
			 * |----------------------------------------------------------------|
			 * | 8. The next methods handle Moving, Turning and Accelerating.	|
			 * |----------------------------------------------------------------| 
			 */
	
	
	/**
	 * Change the position of the bullet based on the current
	 * position, velocity and a given time duration time.
	 * 
	 * @see implementation
	 */
	@Override
	public void move(double time) throws IllegalArgumentException {
		if (time < 0)
			throw new IllegalArgumentException();
		
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
			 * | 9. The next methods handle Calculating Distance and Overlap.	|
			 * |----------------------------------------------------------------| 
			 */
	
	
	
	/**
	 * Gets the distance between this ship and another given entity.
	 * 
	 * @param  	entity
	 * 			the entity to which you want to know the distance.
	 * 
	 * @return	For the current ship, returns the distance between the current ship and a given entity.
	 * 			If the two entities have the same position, the method returns zero.
	 * 
	 * @throws 	IllegalArgumentException
	 * 			The other entity was null.
	 * 			| entity == null
	 */
	@Raw
	public double getDistanceBetween(Entity entity) throws IllegalArgumentException {
		double distance;	// distance between the ships
		double distanceX;	// distance between the x-coordinates of the ships
		double distanceY;	// distance between the y-coordinates of the ships
		
		if (entity == null) 
			throw new IllegalArgumentException();
		
		distanceX = Math.sqrt(Math.pow(this.getPosition().getPositionX() - entity.getPosition().getPositionX(), 2));
		distanceY = Math.sqrt(Math.pow(this.getPosition().getPositionY() - entity.getPosition().getPositionY(), 2));
		
		// use formula of Pythagoras to calculate total distance between centers of the ships
		distance = Math.sqrt(Math.pow(distanceX, 2) + Math.pow(distanceY, 2));
		
		// The distance between a ship and itself is 0.
		if (distance == 0)
			return 0;
					
		// use the sizes of the ships to calculate distance between edges
		distance = distance - this.getRadius() - entity.getRadius();
		return distance;
	}
	
	
	/**
	 * Gets the distance between this ship and a world
	 * 
	 * @param 	world
	 * 			the world to which to calculate the distance
	 * 
	 * @return 	distance[]
	 * 			the distances between the center of the ship and the boundaries of the world
	 * 
	 * @post	distance[0] is equal to the shortest distance between the center of this ship and the vertical boundary of the world
	 * 			distance[1] is equal to the shortest distance between the center of this ship and the horizontal boundary of the world
	 */
	@Raw
	public double[] getDistanceBetween(World world) {
		double[] distance = {Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY};
		if (getWorld() != world) return distance;
		
		if (getPosition().getPositionX() < (world.getWidth() / 2) )
			distance[0] = getPosition().getPositionX();
		else
			distance[0] = world.getWidth() - getPosition().getPositionX();
		
		if (getPosition().getPositionY() < (world.getHeight() / 2) )
			distance[1] = getPosition().getPositionY();
		else
			distance[1] = world.getWidth() - getPosition().getPositionY();
		
		return distance;
	}

	
	/**
	 * Returns true if the entities overlap, false if they don't.
	 * 
	 * @param 	entity
	 * 			the entity of which you want to if it it overlaps this ship.
	 * 
	 * @return	For the current ship, returns true if it overlaps with the given entity,
	 * 			false if it does not.
	 * 			| result == this.getDistanceBetween(entity) =< 0
	 * 
	 * @throws 	IllegalArgumentException
	 * 			the other entity was null.
	 * 			| entity == null
	 */
	@Raw
	public boolean overlap(Entity entity) throws IllegalArgumentException {
		if (entity == null) 
			throw new IllegalArgumentException();

		if (this.getDistanceBetween(entity) <= 0)
			return true;
		else 
			return helper.significantOverlap(this, entity, this.getDistanceBetween(entity));
	}
	
	
	
		/*
	     * |--------------------------------------------|
	     * | #Header-4# Collision Detection Methods.	|
	     * |--------------------------------------------| 
	     */
	
	
			/*
		     * |----------------------------------------------------|
		     * | 10. The next methods handle Collision Detection.	|
		     * |----------------------------------------------------| 
		     */
	
	
	
	/**
	 * Gets the time to collision between this ship and a given world.
	 * 
	 * @param  	world
	 * 			The world to which the collision time needs to be calculated.
	 * 
	 * @return	The time returned will be larger than 0 and will be equal to
	 * 			the time needed for the entity to reach a position where it apparently collides with the boundaries of the world
	 * 
	 * @post	The time returned will be equal to the time needed for this ship to collide with the world's boundaries.
	 * 			If the ship never collides with the boundary, the returned time will be infinity.
	 * 
	 */
	@Override @Raw
	public double getTimeToCollision(World world) {		
		double[] distance = getDistanceBetween(world);
		if ( (distance[0] == Double.POSITIVE_INFINITY) || (distance[1] == Double.POSITIVE_INFINITY) )
			return Double.POSITIVE_INFINITY;
		
		double time1 = distance[0] / this.getVelocityX();
		if (distance[1] + this.getVelocityY() * time1 > world.getHeight()) return Double.POSITIVE_INFINITY;
		if (distance[1] + this.getVelocityY() * time1 < 0) return Double.POSITIVE_INFINITY;
			
		double time2 = distance[1] / this.getVelocityY();
		if (distance[0] + this.getVelocityX() * time2 > world.getWidth()) return Double.POSITIVE_INFINITY;
		if (distance[0] + this.getVelocityX() * time2 < 0) return Double.POSITIVE_INFINITY;

		if (time1 > time2) return time2;		
		return time1;
	
	}
	
	/**
	 * Gets the time to collision between this ship and a given entity.
	 * 
	 * @param  	entity
	 * 			The entity of which the collision time needs to be calculated.
	 * 
	 * @return	The time returned will be larger than 0 and will be equal to
	 * 			the time needed for both entities to reach a position where they
	 * 			will collide with each other.
	 * 
	 * @throws 	IllegalArgumentException
	 * 			The other entity was null.
	 * 			| entity == null
	 */
	@Override @Raw
	public double getTimeToCollision(Entity entity) throws IllegalArgumentException {
		if (entity == null) 
			throw new IllegalArgumentException();
	
		double[] deltaR = {entity.getPosition().getPositionX() - this.getPosition().getPositionX(), 
						   entity.getPosition().getPositionY() - this.getPosition().getPositionY()};
		double[] deltaV = {entity.getVelocityX() - this.getVelocityX(), 
						   entity.getVelocityY() - this.getVelocityY()};
		
		if (helper.evaluateScalar(deltaR, deltaV) >= 0)
			return Double.POSITIVE_INFINITY;
		
		double omega = this.getRadius() + entity.getRadius();
		double d = (Math.pow(helper.evaluateScalar(deltaV, deltaR), 2) - helper.evaluateScalar(deltaV)*(helper.evaluateScalar(deltaR) - Math.pow(omega, 2)));
		if (d <= 0)
			return Double.POSITIVE_INFINITY;
		
		return -( (helper.evaluateScalar(deltaV, deltaR) + Math.sqrt(d)) / helper.evaluateScalar(deltaV) );
	}
	

	/**
	 * Gets the collision position between this and a given world.
	 * 
	 * @param  	world
	 * 			the world to which you want to know the collision position.
	 * 
	 * @return	The position returned will be the position where this ship and the world
	 * 			collide with each other. The method returns null if they never collide.
	 * 
	 * @post	vector contains the position where this ship and the world will collide, if any.
	 * 			vector is null if they never collide.
	 */
	@Raw
	public double[] getCollisionPosition(World world) {	
		double vector[] = {Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY};

		double time = getTimeToCollision(world);
		if (time != Double.POSITIVE_INFINITY) {
			vector[0] = helper.calculatePosition(this, time)[0];
			vector[1] = helper.calculatePosition(this, time)[1];
			
			if (vector[0] + this.getRadius() == world.getWidth()) vector[0] += this.getRadius();
			else if (vector[0] - this.getRadius() == 0) vector[0] = 0;
			else if (vector[1] + this.getRadius() == world.getHeight()) vector[1] += this.getRadius();
			else if (vector[1] - this.getRadius() == 0) vector[1] = 0;
		}
		
		return vector;	
	}
	
	/**
	 * Gets the collision position between this and a given entity.
	 * 
	 * @param  	entity
	 * 			the entity to which you want to know the collision position.
	 * 
	 * @return	The position returned will be the position where both entities
	 * 			collide with each other. The method returns null if they never collide.
	 * 
	 * @throws 	IllegalArgumentException
	 * 			The other entity was null.
	 * 			| entity == null
	 */
	@Raw
	public double[] getCollisionPosition(Entity entity) throws IllegalArgumentException {
		if (entity == null) 
			throw new IllegalArgumentException();
		double time = getTimeToCollision(entity);
		if (time == Double.POSITIVE_INFINITY)
			return null;
		
		double[] newPosition1 = {helper.calculatePosition(this, time)[0], helper.calculatePosition(this, time)[1]};
		double[] newPosition2 = {helper.calculatePosition(entity, time)[0], helper.calculatePosition(entity, time)[1]}; 
		
		// Calculate the correct signs TODO Explain further (why)
		double[] signs = calculateSigns(newPosition1, newPosition2);
		
		// Calculate the angle between the x component of the vector between newPosition1 and newPosition2.
		// This angle is the same as the one between the collisionPosition vector (out of the first ship) and it's x component.
		double angle = Math.atan( Math.abs(newPosition2[1] - newPosition1[1]) / 
								  Math.abs(newPosition2[0] - newPosition1[0]) );
		
		// Calculate the exact position vector of the collision point by using the angle that has just been calculated
		// and the first ship's new position vector.
		double[] vector = {newPosition1[0] + signs[0] * this.getRadius() * Math.cos(angle), 
						   newPosition1[1] + signs[1] * this.getRadius() * Math.sin(angle)};
		return vector;
	}

	
	
			/*
			 * |----------------------------------------------------|
			 * | 12. The next methods handle resolving Collisions.	|
			 * |----------------------------------------------------| 
			 */
	
	
	private double boundaryCollisionMax = 3;
	private double boundaryCollisionCounter = 0;
	
	
	public double getBoundaryCollisionCounter() {
		return this.boundaryCollisionCounter;
	}
	
	private void setBoundaryCollisionCounter() {
		
	}
	
	
	/**
	* Resolves the collision between this ship and a given world.
	* @param 	world
	* 			The world to be used.
	* 
	* @see implementation
	*/
	public void resolveCollision(World world) {
		if (getBoundaryCollisionCounter() < this.boundaryCollisionMax) ;
		double[] position = getCollisionPosition(world);
		
		if (position[0] == this.world.getWidth() || position[0] == 0) setVelocity(getVelocityX(), -getVelocityY());
		else if (position[0] == this.world.getHeight() || position[1] == 0) setVelocity(-getVelocityX(), getVelocityY());
	}
	
	/**
	* Resolves the collision between this ship and a given entity.
	* @param 	entity
	* 			The entity to be used.
	* 
	* @see implementation
	*/
	public void resolveCollision(Entity entity) {
		if (entity.getType() == "Ship") resolveCollisionShip((Ship)entity);
		else if (entity.getType() == "Bullet") resolveCollisionBullet((Bullet) entity);
	}
	
	
	/**
	* Resolves the collision between this ship and a given ship.
	* @param 	ship
	* 			The ship to be used.
	* 
	* @see implementation
	*/
	public void resolveCollisionShip(Ship ship) {
		if (this.getShip() == ship) ship.reloadBullet(this);
		else {
			this.terminate();
			ship.terminate();
		}
	}
	
	/**
	* Resolves the collision between this ship and a given bullet.
	* @param 	bullet
	* 			The bullet to be used.
	* 
	* @see implementation
	*/
	public void resolveCollisionBullet(Bullet bullet) {
		this.terminate();
		bullet.terminate();
	}



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
	 * @return The signs to be used in the getCollisionPosition() method
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
	
	@Raw @Override
	public String getType() {
		return "Bullet";
	}
	
}
