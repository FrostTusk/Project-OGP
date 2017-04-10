package asteroids.helper;

import asteroids.model.World;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw;

public abstract class Entity {
	
	/**
	 * Variable registering the helper for this ship.
	 */
	protected Helper helper = new Helper();
	
	
	public Entity() {
		this.radius = 10;
		this.minRadius = 10;
	}
	

	/**
	 * Variable registering if this ship is terminated or not.
	 */
	protected boolean isTerminated = false;
	
	
	/**
	 * Return the whether or not this ship is terminated.
	 */
	@Basic @Raw
	public boolean isTerminated() {
		return this.isTerminated;
	}
	
	
	public abstract void terminate();
	
	
			 /*
			  * |-------------------------------------------------------|
			  * | 2. The next methods handle the Position of the Ship.	|
			  * |-------------------------------------------------------| 
			  */
	
	
	
	/**
	 * Variable registering the current position of this ship.
	 */
	private Position position;
	
	
	/**
	 * Return the current position of this ship.
	 */
	@Basic @Raw 
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
	* Variable registering the X velocity of this ship.
	*/
	private double velocityX;
	/**
	* Variable registering the Y velocity of this ship.
	*/
	private double velocityY;
	// #Constant-1#
	/**
	* Variable registering the speed of light.
	*/
	private double constantMaxSpeed = 300000;
	
	
	/**
	* Return the velocity of this ship.
	*/
	@Basic@Raw
	public double getVelocityX() {
		return this.velocityX;
	}
	
	/**
	* Return the velocity of this ship.
	*/
	@Basic @Raw
	public double getVelocityY() {
		return this.velocityY;
	}
	
	/**
	* Return the maximum possible speed of this ship.
	*/
	@Basic @Raw
	public double getConstantMaxSpeed() {
		return this.constantMaxSpeed;
	}
	
	/**
	* Return the current speed of the ship.
	* @return	Returns the speed of the current ship.
	* 			| result == Math.sqrt((this.getVelocityX() * this.getVelocityX()) + (this.getVelocityY() * this.getVelocityY()))
	*/
	@Raw
	public double getSpeed() {
		return Math.sqrt((getVelocityX() * getVelocityX()) + (getVelocityY() * getVelocityY()));
	}
	
	
	/**
	* Check whether the given velocities are valid velocities for any ship.
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
	* Set the X and Y velocities of this ship to the given velocities.
	* 
	* @param  	velocityX
	*         	The new X velocity for this ship.
	* @param  	velocityY
	*         	The new Y velocity for this ship.
	*         
	* @post   	If the given velocities are valid velocities for any ship (they make up a valid speed),
	*         	the X and Y velocities of this new ship are equal to the given	X and Y velocities.
	*       	| if (isValidVelocity(velocityX, velocityY)))
	*       	|   then new.getVelocityX() == velocityX
	*       	|		 new.getVelocityY() == velocityY
	* @post	If the given velocities aren't valid, the X and Y velocities
	* 			of this new ship will be equal to 0.
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
			 * |----------------------------------------------------|
			 * | 5. The next methods handle the Radius of the Ship.	|
			 * |----------------------------------------------------| 
			 */



	/**
	* Variable registering the radius of this ship.
	*/
	protected double radius;
	// #Constant-2#
	/**
	* Variable registering the minimum radius of this ship.
	*/
	protected double minRadius;
	
	
	/**
	*  Return the minimum radius of this ship.
	*/
	@Basic @Immutable @Raw
	public double getMinRadius() {
		return this.minRadius;
	}
	
	/**
	* Return the radius of this ship.
	*/
	@Basic @Raw
	public double getRadius() {
		return this.radius;
	}
	
	
	/**
	* Check whether this ship can have the given radius as its radius.
	*  
	* @param  	radius
	*         	The radius to check.
	* @return	Returns whether or not the given radius can be used 
	* 			as a valid radius or not. true if it can, false if not.
	*       	| result == (POSITIVE_INFINITY > radius) && (radius >= this.getMinRadius())
	*/
	@Raw
	public boolean canHaveAsRadius(double radius) {
		if ( (Double.POSITIVE_INFINITY > radius) && (radius >= this.getMinRadius()) )
			return true;
		return false;
	}
	
	
	
			/*
			 * |----------------------------------------------------|
			 * | 6. The next methods handle the mass of the ship.	|
			 * |----------------------------------------------------| 
			 */
	
	
	
	/**
	 * Variable registering the density of this bullet.
	 */
	protected double density;
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
		     * |------------------------------------------------------------|
		     * | 7. The next methods handle the association with worlds.	|
		     * |------------------------------------------------------------| 
		     */



	/**
	* Variable registering the world of this ship.
	*/
	protected World world = null;
	
	
	/**
	* Return the world associated with this ship.
	*/
	@Basic @Raw
	public World getWorld() {
		return this.world;
	}

	
	public abstract boolean canHaveAsWorld(World world);
	
	
	/**
	* Check whether this ship is currently located in a given world. 
	* This method only checks if the shape of the ship is located in a given world,
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
	* Set a given world as world for this ship.
	*  
	* @param  	world
	*         	The world to set as world for this ship.
	*         
	* @see implementation
	*/
	@Raw
	public void setWorld(World world) throws IllegalArgumentException, NullPointerException {
		if (world ==  null) throw new NullPointerException();
		if (!canHaveAsWorld(world)) throw new IllegalArgumentException();
		this.world = world;
	}
	
	/**
	* Remove the current world as world for this ship.
	*      
	* @see implementation
	*/
	 // TODO @Raw?
	public void deSetWorld() throws NullPointerException {
		if (getWorld() ==  null) throw new NullPointerException();
		this.world = null;
	}
	
	
	
	
	/*
     * |--------------------------------|
     * | #Header-3# Advanced Methods.	|
     * |--------------------------------| 
     */


		/*
		 * |----------------------------------------------------------------|
		 * | 9. The next methods handle Moving, Turning and Accelerating.	|
		 * |----------------------------------------------------------------| 
		 */
	
	
	
	/**
	 * Change the position of the ship based on the current
	 * position, velocity and a given time duration time.
	 * 
	 * @param  	time
	 * 			the time duration in which the ship will move.
	 * 
	 * @post   	The X position of this new ship is equal to
	 *         	the current X position times the X velocity times the given time.
	 *       	| new.getPositionX() == this.getpositionX() * this.getVelocityX() * time
	 * @post   	The Y position of this new ship is equal to
	 *         	the current X position times the Y velocity times the given time.   	
	 *       	| new.getPositionY() == this.positionY() * this.getVelocityY() * time
	 *       
	 * @throws 	IndexOutOfBoundsException
	 *         	The ship moved out of bounds.
	 *       	| ! isValidPosition(new.getPositionX(), new.getPositionY())
	 * @throws 	IllegalArgumentException
	 *         	The time was less than 0.
	 *       	| time < 0
	 */
	public void move(double time) throws IllegalArgumentException, IndexOutOfBoundsException {
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
			 * | 11. The next methods handle Calculating Distance and Overlap.	|
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
		if (entity == null) throw new IllegalArgumentException();
		
		double distance;	// distance between the ship and entity
		double distanceX;	// distance between the x-coordinates of the ship and entity
		double distanceY;	// distance between the y-coordinates of the ship and entity
		
		distanceX = Math.sqrt(Math.pow(this.getPosition().getPositionX() - entity.getPosition().getPositionX(), 2));
		distanceY = Math.sqrt(Math.pow(this.getPosition().getPositionY() - entity.getPosition().getPositionY(), 2));
		
		// use formula of Pythagoras to calculate total distance between centers of the ships
		distance = Math.sqrt(Math.pow(distanceX, 2) + Math.pow(distanceY, 2));
		
		// The distance between a ship and itself is 0.
		if (distance == 0) return 0;
			
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
	* 			the distances between the ship and the boundaries of the world
	* 
	* @post	distance[0] is equal to the shortest distance between the center of this ship and the vertical boundary of the world
	* 			distance[1] is equal to the shortest distance between the center of this ship and the horizontal boundary of the world
	*/
	@Raw
	public double[] getDistanceBetween(World world) {
		double[] distance = {Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY};
		if (getWorld() != world) return distance;
		
		if (getPosition().getPositionX() < (world.getWidth() / 2) )
			distance[0] = getPosition().getPositionX() - getRadius();
		else
			distance[0] = world.getWidth() - getPosition().getPositionX()  + getRadius();
		
		if (getPosition().getPositionY() < (world.getHeight() / 2) )
			distance[1] = getPosition().getPositionY() - getRadius();
		else
			distance[1] = world.getWidth() - getPosition().getPositionY() + getRadius();
		
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
		if (entity == null) throw new IllegalArgumentException();
		return helper.significantOverlap(this, entity, this.getDistanceBetween(entity));
	}
	
	
	
				/*
				 * |----------------------------------------------------|
				 * | 12. The next methods handle Collision Detection.	|
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
	@Raw
	public double getTimeToCollision(World world) {		
		if (! world.containsEntity(this)) return Double.POSITIVE_INFINITY;
		
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
	@Raw
	public double getTimeToCollision(Entity entity) throws IllegalArgumentException {
		if (entity == null) 
		throw new IllegalArgumentException();
		if (this.getWorld() != entity.getWorld()) return Double.POSITIVE_INFINITY;
		
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
		if (! world.containsEntity(this)) return vector;
		
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
		if (entity == null) throw new IllegalArgumentException();
		if (! (this.getWorld() == entity.getWorld())) return null;
		double time = getTimeToCollision(entity);
		if (time == Double.POSITIVE_INFINITY) return null;
		
		double[] newPosition1 = {helper.calculatePosition(this, time)[0], helper.calculatePosition(this, time)[1]};
		double[] newPosition2 = {helper.calculatePosition(entity, time)[0], helper.calculatePosition(entity, time)[1]}; 
		
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
	
	public abstract String getType();

}
