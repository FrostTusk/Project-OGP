package asteroids.model;

import be.kuleuven.cs.som.annotate.*;

/* NOTES:
*	1. Constants:
*		1. 	min/maxPosX/Y = the min/max X/Y position
*		2.	constantMaxSpeed = the maximum speed this ship can achieve.
*		3.	minRadius =  the minimum radius for each ship.
*/

/*
 * Methods Index:
 * 1. Methods that handle the Initialization of the Ship
 * 2. Methods that handle the Position of the Ship
 * 3. Methods that handle the Speed of the Ship
 * 4. Methods that handle the Orientation of the Ship
 * 5. Methods that handle the Radius of the Ship
 * 6. Methods that handle Moving, Turning and Accelerating
 * 7. Methods that handle Calculating Distance, Overlap, and Collision Detection
 * 8. Helper Methods
 * 9. Methods that handle the mass of the Ship
 */

/**
* @invar  	The position of each ship must be a valid position for any
*         	ship.
*       	| isValidPosition(getPositionX(), getPositionY())
* @invar  	The speed of each ship must be a valid speed for any
*         	ship.
*       	| isValidSpeed(getVelocityX(), getVelocityY())
* @invar  	The orientation of each ship must be a valid orientation for any
*         	ship.
*       	| isValidOrientation(getOrientation())
* @invar  	Each ship can have its radius as radius.
*       	| canHaveAsRadius(this.getRadius())
* @invar  	Each ship must have a valid mass.
*       	| canHaveAsMass(this.getMass())
*/

public class Ship {
	
		/*
		  * |---------------------------------------------------------------|
		  * | 1. The next method handles the Initialization of the Ship.	|
		  * |---------------------------------------------------------------| 
		  */
	
	
	
	/**
	 * Initialize this new ship with given X and Y position, a given X and Y velocity, a given orientation, and a given radius.
	 *
	 * @param  	positionX
	 *         	The X position for this new ship.
	 * @param  	positionY
	 * 			The Y position of this new ship.
	 * @param  	velocityX
	 *         	The X velocity for this new ship.
	 * @param	velocityY
	 * 			The Y velocity for this new ship.
	 * @param  	orientation
	 *         	The orientation for this new ship.
	 * @param  	radius
	 *         	The radius for this new ship.
	 * @param  	mass
	 *         	The mass for this new ship.
	 * 
	 * 
	 * @pre    	The given orientation must be a valid orientation for any ship.
	 *       	| isValidOrientation(orientation)
	 *       
	 * @post   	The orientation of this new ship is equal to the given
	 *         	orientation.
	 *       	| new.getOrientation() == orientation
	 * @post   	The radius of this new ship is equal to the given
	 *         	radius.
	 *       	| new.getRadius() == radius
	 *       
	 * @effect 	The positions of this new ship are set to
	 *         	the given X position and Y position.
	 *       	| this.setPosition(positionX, positionY)
	 * @effect	The X and Y velocity of this new ship are set to
	 *         	the given velocityX and velocityY.
	 *         	| this.setVelocity(velocityX, velocityY)
	 * @effect	The mass of this new ship is set to
	 *         	the given mass.
	 *         	| this.setMass(mass)
	 *         	 
	 * @throws 	IndexOutOfBoundsException
	 *         	This new ship cannot have the given X and Y position as position.
	 *       	| ! canHaveAsPosition(positionX, positionY)
	 * @throws 	IllegalArgumentException
	 *         	This new ship cannot have the given radius as its radius.
	 *       	| ! canHaveAsRadius(this.getRadius())
	 */
	public Ship(double positionX, double positionY, double velocityX, double velocityY, double orientation, double radius, double mass)
		throws IndexOutOfBoundsException, IllegalArgumentException {
		try {
			this.setPosition(positionX, positionY);
		}
		catch (IndexOutOfBoundsException exc) {
			throw new IndexOutOfBoundsException();
		}

		setVelocity(velocityX, velocityY);
		
		assert isValidOrientation(orientation);
		this.setOrientation(orientation);
		
		if (! canHaveAsRadius(radius))
			throw new IllegalArgumentException();
		this.radius = radius;
		
		setMass(mass);
	}
	
	
	
		 /*
		  * |-------------------------------------------------------|
		  * | 2. The next methods handle the Position of the Ship.	|
		  * |-------------------------------------------------------| 
		  */
	
	
	
	/**
	 * Variable registering the X position of this ship.
	 */
	private double positionX;
	/**
	 * Variable registering the minimum X position of this ship.
	 */
	private final double minPositionX = Double.NEGATIVE_INFINITY;
	/**
	 * Variable registering the maximum X position of this ship.
	 */
	private final double maxPositionX = Double.POSITIVE_INFINITY;
	/**
	 * Variable registering the Y position of this ship.
	 */
	private double positionY;
	/**
	 * Variable registering the minimum Y position of this ship.
	 */
	private final double minPositionY = Double.NEGATIVE_INFINITY;
	/**
	 * Variable registering the maximum Y position of this ship.
	 */
	private final double maxPositionY = Double.POSITIVE_INFINITY;
	
	
	/**
	 * Return the X position of this ship.
	 */
	@Basic 
	public double getPositionX() {
		return this.positionX;
	}
	
	/**
	 * Return the minimum X position of this ship.
	 */
	@Basic @Immutable @Raw
	public double getMinPositionX() {
		return this.minPositionX;
	}
	
	/**
	 * Return the max X position of this ship.
	 */
	@Basic @Immutable @Raw 
	public double getMaxPositionX() {
		return this.maxPositionX;
	}
	
	/**
	 * Return the Y position of this ship.
	 */
	@Basic @Raw
	public double getPositionY() {
		return this.positionY;
	}
	
	/**
	 * Return the minimum Y position of this ship.
	 */
	@Basic @Immutable @Raw
	public double getMinPositionY() {
		return this.minPositionY;
	}
	
	/**
	 * Return the max Y position of this ship.
	 */
	@Basic @Immutable @Raw 
	public double getMaxPositionY() {
		return this.maxPositionY;
	}
	
	
	/**
	 * Check whether the given position is a valid position for
	 * any ship.
	 *  
	 * @param  	positionX
	 *         	The X position to check.
	 * @param	positionY
	 * 			The Y position to check.
	 * @return	Returns whether or not the given position is a valid position.
	 * 			true if it is, false if not.
	 *       	| result == (! ( (java.lang.Double.isNaN(positionX)) || (java.lang.Double.isNaN(positionY)) ) &&
	 *       	|			(this.getMinPositionX() < positionX) && (this.getMaxPositionX() > positionX) &&
	 *			|			(this.getMinPositionY() < positionY) && (this.getMaxPositionY() > positionY)      
	 */
	public boolean isValidPosition(double positionX, double positionY) {
		// A position has to be a number.
		if ( (java.lang.Double.isNaN(positionX)) || (java.lang.Double.isNaN(positionY)) )
			return false;
		// A position cannot be smaller/bigger or equal to negative/positive infinity.
		if ( (getMinPositionX() < positionX) && (getMaxPositionX() > positionX) &&
				(getMinPositionY() < positionY) && (getMaxPositionY() > positionY) )
			return true;
		else
			return false;	
	}
	
	
	/**
	 * Set the position of this ship to the given position.
	 * 
	 * @param  	positionX
	 *         	The new X position for this ship.
	 * @param  	positionY
	 * 		   	The new Y position for this ship.
	 * 
	 * @post   	The X position of this new ship is equal to
	 *         	the given X position.
	 *       	| new.getPositionX() == positionX
	 * @post   	The Y position of this new ship is equal to
	 *         	the given Y position.
	 *       	| new.getPositionY() == positionY
	 *       
	 * @throws 	IndexOutOfBoundsException
	 *         	The given position is not a valid position for any
	 *         	ship.
	 *       	| ! isValidPosition(getPositionX(), getPositionY())
	 */
	public void setPosition(double positionX, double positionY) 
			throws IndexOutOfBoundsException {
		if (! isValidPosition(positionX, positionY))
			throw new IndexOutOfBoundsException();
		this.positionX = positionX;
		this.positionY = positionY;
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
	/**
	 * Variable registering the speed of light.
	 */
	private double constantMaxSpeed = 300000;

	
	/**
	 * Return the velocity of this ship.
	 */
	@Basic 
	public double getVelocityX() {
		return this.velocityX;
	}
	
	/**
	 * Return the velocity of this ship.
	 */
	@Basic 
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
	 */
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
	public boolean isValidSpeed(double velocityX, double velocityY) {
		if ( (java.lang.Double.isNaN(velocityX)) || (java.lang.Double.isNaN(velocityY)) )
			return false;
		if (Math.sqrt((velocityX * velocityX) + (velocityY * velocityY)) > constantMaxSpeed)
			return false;
		return true;
	}
	
	
	/**
	 * Set the X and Y velocity of this ship to the given velocities.
	 * 
	 * @param  	velocityX
	 *         	The new X velocity for this ship.
	 * @param  	velocityY
	 *         	The new Y velocity for this ship.
	 *         
	 * @post   	If the given velocities are valid velocities for any ship (they make up a valid speed),
	 *         	the X and Y velocity of this new ship is equal to the given	X and Y velocity.
	 *       	| if (isValidVelocity(velocityX, velocityY)))
	 *       	|   then new.getVelocityX() == velocityX
	 *       	|		 new.getVelocityY() == velocityY
	 * @post	If the given velocities aren't valid, the X and Y velocities
	 * 			of this new ship will be equal to 0.
	 *       	| if (! isValidVelocity(velocityX, velocityY)))
	 *       	|   then new.getVelocityX() == 0
	 *       	|		 new.getVelocityY() == 0
	 */
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
		  * |-----------------------------------------------------------|
		  * | 4. The next methods handle the Orientation of the Ship.	|
		  * |-----------------------------------------------------------| 
		  */
	
	
	
	/**
	 * Variable registering the orientation of this ship.
	 */
	private double orientation;
	
	
	/**
	 * Return the orientation of this ship.
	 */
	@Basic 
	public double getOrientation() {
		return this.orientation;
	}
	
	
	/**
	 * Check whether the given orientation is a valid orientation for any ship.
	 *  
	 * @param  	orientation
	 *         	The orientation to check.
	 * @return	Returns whether or not the given orientation is valid orientation
	 * 			or not. true if it is, false if not.
	 *       	| result == (orientation <= 2*Math.PI) && (orientation >= 0)
	*/
	public boolean isValidOrientation(double orientation) {
		if ( (orientation <= 2*Math.PI) && (orientation >= 0) )
			return true;
		return false;
	}
	
	
	/**
	 * Set the orientation of this ship to the given orientation.
	 * 
	 * @param  	orientation
	 *         	The new orientation for this ship.
	 *         
	 * @pre    	The given orientation must be a valid orientation for any
	 *         	ship.
	 *       	| isValidOrientation(orientation)
	 *       
	 * @post   	The orientation of this ship is equal to the given
	 *         	orientation.
	 *       	| new.getOrientation() == orientation
	 */
	public void setOrientation(double orientation) {
		assert isValidOrientation(orientation);
		this.orientation = orientation;
	}
	
	
	
		 /*
		  * |-------------------------------------------------------|
		  * | 5. The next methods handle the Radius of the Ship.	|
		  * |-------------------------------------------------------| 
		  */
	
	
	
	/**
	 * Variable registering the radius of this ship.
	 */
	private final double radius;
	/**
	 * Variable registering the minimum radius of this ship.
	 */
	private final double minRadius = 10;
	
	
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
	@Basic  
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
	public boolean canHaveAsRadius(double radius) {
		if ( (Double.POSITIVE_INFINITY > radius) && (radius >= this.getMinRadius()) )
			return true;
		return false;
	}
	
	
	
		 /*
		  * |---------------------------------------------------------------|
		  * | 6. The next methods handle Moving, Turning and Accelerating.	|
		  * |---------------------------------------------------------------| 
		  */
	
	
	
	/**
	 * Change the position of the spacecraft based on the current
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
		if (time < 0)
			throw new IllegalArgumentException();
		
		double newPositionX = getPositionX() + getVelocityX() * time;
		double newPositionY = getPositionY() + getVelocityY() * time;

		try {
			setPosition(newPositionX, newPositionY);
		}
		catch (IndexOutOfBoundsException exc) {
			throw new IndexOutOfBoundsException();
		}
	}
	
	
	/**
	 * Add a given angle to the current orientation of the ship.
	 * 
	 * @param  	angle
	 *         	The angle to be added to the current orientation of the sip.
	 *         
	 * @pre    	The absolute value of the given angle must be a valid orientation for any
	 *         	ship.
	 *       	| isValidOrientation(angle)
	 *       
	 * @post	The orientation of this ship is equal to the original orientation plus the given angle the
	 * 			orientation plus the given angle is a valid angle.
	 * 			| if (isValidOrientation(getOrientation() + angle))
	 * 			|	then new.getOrientation() == this.getOrientation() + angle
	 * @post   	The orientation of this ship is equal to the original orientation plus the given angle minus 2 times pi if the
	 *         	orientation plus the given angle is not a valid angle and the given angle was larger than zero.
	 *       	| if (! isValidOrientation(getOrientation() + angle))
	 *       	|	if (angle > 0)
	 *       	|		then new.getOrientation() == this.getOrientation() + angle - 2*Math.PI
	 * @post   	The orientation of this ship is equal to the original orientation plus the given angle plus 2 times pi if the
	 *         	orientation plus the given angle is not a valid angle and the given angle was smaller than zero.
	 *       	| if (! isValidOrientation(getOrientation() + angle))
	 *       	|	if (angle < 0)
	 *       	|		then new.getOrientation() == this.getOrientation() + angle - 2*Math.PI
	 */
	public void turn(double angle) {
		assert isValidOrientation(Math.abs(angle));
		
		if (! isValidOrientation(getOrientation() + angle))
			if (angle > 0)
				this.setOrientation(orientation + angle - 2*Math.PI);
			else
				this.setOrientation(orientation + angle + 2*Math.PI);
		else
			this.setOrientation(orientation + angle);
	}
	
	
	/**
	 * Thrusts a ship forwards (in the direction it's facing) with a given acceleration.
	 * 
	 * @param  	acceleration
	 *         	The acceleration with which the ship will thrust forwards.
	 *         
	 * @post   	If the given acceleration is lower than 0, the velocity will change
	 * 			with an acceleration of 0.
	 *       	| if (acceleration < 0)
	 *       	|	then (new.getVelocityX() == this.getVelocityX()) &&
	 *       	|   	 (new.getVelocityY() == this.getVelocityY())
	 *       
	 * @effect	The X and Y velocity of this new ship is set to
	 *         	the the current velocity plus the given acceleration in the current direction.
	 * 			| this.setVelocity(this.getVelocityX() + acceleration * Math.cos(this.getOrientation()),
	 * 			|				   this.getVelocityY() + acceleration * Math.sin(this.getOrientation()))
	 *       
	 */
	public void thrust(double acceleration) {
		if (! (acceleration > 0))
			acceleration = 0;
		
		double newVelocityX = getVelocityX() + acceleration * Math.cos(getOrientation());
		double newVelocityY = getVelocityY() + acceleration * Math.sin(getOrientation());
		setVelocity(newVelocityX, newVelocityY);
	}
	
	
	
		/*
		  * |---------------------------------------------------------------------------------------|
		  * | 7. The next methods handle Calculating Distance, Overlap, and Collision Detection.	|
		  * |---------------------------------------------------------------------------------------| 
		  */
	
	
	
	/**
	 * Gets the distance between this ship and another given ship.
	 * 
	 * @param  	ship
	 * 			the ship to which you want to know the distance.
	 * 
	 * @return	For the current ship, returns the distance between the current ship and a given ship.
	 * 			If the two ships have the same position, the method returns zero.
	 * 
	 * @throws 	IllegalArgumentException
	 * 			The other ship was null.
	 * 			| ship == null
	 */
	public double getDistanceBetween(Ship ship) throws IllegalArgumentException {
		double distance;	// distance between the ships
		double distanceX;	// distance between the x-coordinates of the ships
		double distanceY;	// distance between the y-coordinates of the ships
		
		if (ship == null) 
			throw new IllegalArgumentException();
		
		distanceX = Math.sqrt(Math.pow(this.getPositionX() - ship.getPositionX(), 2));
		distanceY = Math.sqrt(Math.pow(this.getPositionY() - ship.getPositionY(), 2));
		
		// use formula of Pythagoras to calculate total distance between centers of the ships
		distance = Math.sqrt(Math.pow(distanceX, 2) + Math.pow(distanceY, 2));
		
		// The distance between a ship and itself is 0.
		if (distance == 0)
			return 0;
					
		// use the sizes of the ships to calculate distance between edges
		distance = distance - this.getRadius() - ship.getRadius();
		return distance;
	}
	
	
	/**
	 * Returns true if the ships overlap, false if they don't.
	 * 
	 * @param 	ship
	 * 			the ship of which you want to if it it overlaps this ship.
	 * 
	 * @return	For the current ship, returns true if it overlaps with the given ship,
	 * 			false if it does not.
	 * 			| result == this.getDistanceBetween(ship) =< 0
	 * 
	 * @throws 	IllegalArgumentException
	 * 			the other ship was null.
	 * 			| ship == null
	 */
	public boolean overlap(Ship ship) throws IllegalArgumentException {
		if (ship == null) 
			throw new IllegalArgumentException();

		if (this.getDistanceBetween(ship) <= 0)
			return true;
		else 
			return false;
	}
	
	
	/**
	 * Gets the time to collision between this ship and a given ship.
	 * 
	 * @param  	ship
	 * 			The ship of which the collision time needs to be calculated.
	 * 
	 * @return	The time returned will be larger than 0 and will be equal to
	 * 			the time needed for both ships to reach a position where they
	 * 			will collide with each other.
	 * 
	 * @throws 	IllegalArgumentException
	 * 			The other ship was null.
	 * 			| ship == null
	 */
	public double getTimeToCollision(Ship ship) throws IllegalArgumentException {
		if (ship == null) 
			throw new IllegalArgumentException();
	
		double[] deltaR = {ship.getPositionX() - this.getPositionX(), ship.getPositionY() - this.getPositionY()};
		double[] deltaV = {ship.getVelocityX() - this.getVelocityX(), ship.getVelocityY() - this.getVelocityY()};
		if (evaluateScalar(deltaR, deltaV) >= 0)
			return Double.POSITIVE_INFINITY;
		
		double omega = this.getRadius() + ship.getRadius();
		double d = (Math.pow(evaluateScalar(deltaV, deltaR), 2) - evaluateScalar(deltaV)*(evaluateScalar(deltaR) - Math.pow(omega, 2)));
		if (d <= 0)
			return Double.POSITIVE_INFINITY;
		
		double deltaT = -( (evaluateScalar(deltaV, deltaR) + Math.sqrt(d)) / evaluateScalar(deltaV) );
		return deltaT;
	}
	
	
	/**
	 * Gets the collision position between this and a given ship.
	 * 
	 * @param  	ship
	 * 			the ship to which you want to know the collision position.
	 * 
	 * @return	The position returned will be the position where both ships
	 * 			collide with each other. The method returns null if they never collide.
	 * 
	 * @throws 	IllegalArgumentException
	 * 			The other ship was null.
	 * 			| ship == null
	 */
	public double[] getCollisionPosition(Ship ship) throws IllegalArgumentException {
		if (ship == null) 
			throw new IllegalArgumentException();
		
		double time = getTimeToCollision(ship);
		if (time == Double.POSITIVE_INFINITY)
			return null;
		
		double newPositionX1 = this.getPositionX() + this.getVelocityX() * time;
		double newPositionY1 = this.getPositionY() + this.getVelocityY() * time;
		double newPositionX2 = ship.getPositionX() + ship.getVelocityX() * time;
		double newPositionY2 = ship.getPositionY() + ship.getVelocityY() * time;
		
		double signX1 = 1;
		if (newPositionX1 > newPositionX2)
			signX1 = -1;
		double signY1 = 1;
		if (newPositionY1 > newPositionY2)
			signY1 = -1;
		
		// Calculate the angle between the x component of the vector between newPosition1 and newPosition2.
		// This angle is the same as the one between the collisionPosition vector (out of the first ship) and it's x component.
		double angle = Math.atan( Math.abs(newPositionY2 - newPositionY1) / Math.abs(newPositionX2 - newPositionX1) );
		// Calculate the exact position vector of the collision point by using the angle that has just been calculated
		// and the first ship's new position vector.
		double[] vector = {newPositionX1 + signX1 * this.getRadius() * Math.cos(angle), newPositionY1 + signY1 * this.getRadius() * Math.sin(angle)};
		return vector;
	}
	
	
	
		/*
		  * |-------------------------------------------|
		  * | 8. The next methods are helper methods.	|
		  * |-------------------------------------------| 
		  */
	
	
	
	/**
	 * Calculates the cross product with a vector and itself.
	 * 
	 * @param 	vector1
	 * 		  	The vector to be used.
	 * 
	 * @return	Returns the cross product of the given vector with itself.
	 */
	public double evaluateScalar(double[] vector) {
		return Math.pow(vector[0], 2) + Math.pow(vector[1], 2);
	}
	
	/**
	 * Calculates the cross product between two vectors.
	 * 
	 * @param 	vector1
	 * 		  	The first vector to be used.
	 * @param 	vector2
	 * 		  	The second vector to be used.
	 * 
	 * @return	Returns the cross product of two given vectors.
	 */
	public double evaluateScalar(double[] vector1, double[] vector2) {
		return vector1[0]*vector2[0] + vector1[1]*vector2[1];
	}
	
	
	/*
	  * |---------------------------------------------------|
	  * | 9. The next methods handle the mass of the ship.	|
	  * |---------------------------------------------------| 
	  */
	
	public double mass;
	
	public void setMass(double mass) {
		if (mass < (4/3 * Math.PI * Math.pow(this.getRadius(), 3) * 1.42 * Math.pow(10, 12))) {
			this.mass = (4/3 * Math.PI * Math.pow(this.getRadius(), 3) * 1.42 * Math.pow(10, 12));
		}
		else {
			this.mass = mass;
		}
	}
	
	/**
	 * Return the current mass of the ship.
	 * @return	Returns the mass of the current ship.
	 */
	@Basic @Raw
	public double getMass() {
		return this.mass;
	}
	
	/**
	 * Return the current mass of the ship and its cargo.
	 * @return	Returns the mass of the current ship + the mass of the objects on the ship.
	 */
	@Raw
	public double getTotalMass() {
		return this.getMass(); //TODO + mass of cargo
	}
}
