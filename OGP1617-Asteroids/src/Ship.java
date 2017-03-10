// This is the old Ship class
//import java.lang.Math.sqrt;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;
import be.kuleuven.cs.som.annotate.Immutable;

/* NOTES:
*	1. Constants:
*		1.	constantMaxSpeed = the maximum speed this ship can achieve.
*		2.	PI = the value of pi.
*		3.	minShape =  the minimum shape for each ship.
*		4. 	shape = the shape of this ship.
*	2. Vragen:
*		1. Hoe werkt iFacade exact
*		2. Moeten we ook maximum voorstelling coveren?
*		3. Github
*		4. Start
*		5. Documentatie collision
*		6. @Raw/@Basic 
*		7. Documentatie at start
*		8. JUnit
*/


/**
* @invar  	The position of each ship must be a valid position for any
*         	ship.
*       	| isValidPosition(getPositionX(), getPositionY())
* @invar  	The velocity of each ship must be a valid velocity for any
*         	ship.
*       	| isValidVelocity(getVelocityX()) && isValidVelocity(getVelocityY())
* @invar  	The orientation of each ship must be a valid orientation for any
*         	ship.
*       	| isValidOrientation(getOrientation())
* @invar  	Each ship can have its shape as shape.
*       	| canHaveAsShape(this.getShape())
*/

public class Ship {
	
		/*
		  * |---------------------------------------------------------------|
		  * | The next method handles the initialization of the ship.		|
		  * |---------------------------------------------------------------| 
		  */
	
	
	/**
	 * Initialize this new ship with given X and Y position.
	 *
	 * @param  	positionX
	 *         	The X position for this new ship.
	 * @param  	positionY
	 * 			The Y position of this new ship.
	 * @effect 	The position of this new ship is set to
	 *         	the given X position and Y position.
	 *       	| this.setPosition(positionX, positionY)
	 * @throws 	IndexOutOfBoundsException
	 *         	This new ship cannot have the given X and Y position as position.
	 *       	| ! canHaveAsPosition(positionX, positionY)
	 */
	/**
	 * Initialize this new ship with given velocity.
	 * 
	 * @param  	velocityX
	 *         	The X velocity for this new ship.
	 * @param	velocityY
	 * 			The Y velocity for this new ship.
	 * @effect	The X velocity of this new ship is set to
	 *         	the given velocityX.
	 *         	| this.setVelocity(velocityX)
	 * @effect	The Y velocity of this new ship is set to
	 *         	the given velocityY.
	 *         	| this.setVelocity(velocityY)
	 */
	/**
	 * Initialize this new ship with given orientation.
	 * 
	 * @param  	orientation
	 *         	The orientation for this new ship.
	 * @pre    	The given orientation must be a valid orientation for any ship.
	 *       	| isValidOrientation(orientation)
	 * @post   	The orientation of this new ship is equal to the given
	 *         	orientation.
	 *       	| new.getOrientation() == orientation
	 */
	/**
	 * Initialize this new ship with given shape.
	 * 
	 * @param  	shape
	 *         	The shape for this new ship.
	 * @post   	The shape of this new ship is equal to the given
	 *         	shape.
	 *       	| new.getShape() == shape
	 * @throws 	IllegalArgumentException
	 *         	This new ship cannot have the given shape as its shape.
	 *       	| ! canHaveAsShape(this.getShape())
	 */
	public Ship(double positionX, double positionY, double velocityX, double velocityY, double orientation, double shape)
		throws IndexOutOfBoundsException, IllegalArgumentException {

		try {
			this.setPosition(positionX, positionY);
		}
		catch (IndexOutOfBoundsException exc) {
			throw new IllegalArgumentException();
		}
		
		setVelocityX(velocityX);
		setVelocityY(velocityY);
		
		assert isValidOrientation(orientation);
		this.setOrientation(orientation);
		
		if (! canHaveAsShape(shape))
			throw new IllegalArgumentException();
		this.shape = shape;
		
	}
	
	
	
		 /*
		  * |-------------------------------------------------------|
		  * | The next methods handle the position of the ship.		|
		  * |-------------------------------------------------------| 
		  */
	
	
	
	/**
	 * Variable registering the X position of this ship.
	 */
	private double positionX;
	/**
	 * Variable registering the minimum X position of this ship.
	 */
	private double minPositionX = Double.NEGATIVE_INFINITY;
	/**
	 * Variable registering the maximum X position of this ship.
	 */
	private double maxPositionX = Double.POSITIVE_INFINITY;
	/**
	 * Variable registering the Y position of this ship.
	 */
	private double positionY;
	/**
	 * Variable registering the minimum Y position of this ship.
	 */
	private double minPositionY = Double.NEGATIVE_INFINITY;
	/**
	 * Variable registering the maximum Y position of this ship.
	 */
	private double maxPositionY = Double.POSITIVE_INFINITY;
	
	
	/**
	 * Return the X position of this ship.
	 */
	@Basic @Raw
	public double getPositionX() {
		return this.positionX;
	}
	
	/**
	 * Return the minimum X position of this ship.
	 */
	@Basic @Raw
	public double getMinPositionX() {
		return this.minPositionX;
	}
	
	/**
	 * Return the max X position of this ship.
	 */
	@Basic @Raw
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
	@Basic @Raw
	public double getMinPositionY() {
		return this.minPositionY;
	}
	
	/**
	 * Return the max Y position of this ship.
	 */
	@Basic @Raw
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
	 * @return 
	 *       	| result == (this.getMinPositionX() < positionX) && (this.getMaxPositionX() > positionX) &&
	 *						(this.getMinPositionY() < positionY) && (this.getMaxPositionY() > positionY)      
	 */
	public boolean isValidPosition(double positionX, double positionY) {
		if ( (positionX == Double.NaN) || (positionY == Double.NaN) )
			return false;
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
	 * @post   	The X position of this new ship is equal to
	 *         	the given X position.
	 *       	| new.getPositionX() == positionX
	 * @post   	The Y position of this new ship is equal to
	 *         	the given Y position.
	 *       	| new.getPositionY() == positionY
	 * @throws 	IndexOutOfBoundsException
	 *         	The given position is not a valid position for any
	 *         	ship.
	 *       	| ! isValidPosition(getPositionX(), getPositionY())
	 */
	@Raw
	public void setPosition(double positionX, double positionY) 
			throws IndexOutOfBoundsException {
		if (! isValidPosition(positionX, positionY))
			throw new IndexOutOfBoundsException();
		this.positionX = positionX;
		this.positionY = positionY;
	}
	
	
	
		 /*
		  * |-------------------------------------------------------|
		  * | The next methods handle the velocity of the ship.		|
		  * |-------------------------------------------------------| 
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
	@Basic @Raw
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
	 */
	public double getSpeed() {
		return Math.sqrt((getVelocityX() * getVelocityX()) + (getVelocityY() * getVelocityY()));
	}
	
	
	/**
	 * Check whether the given velocity is a valid velocity for
	 * any ship.
	 *  
	 * @param  	velocity
	 *         	The velocity to check.
	 * @return 
	 *       	| result == velocity * velocity > constantMaxSpeed * constantMaxSpeed
	*/
	public boolean isValidVelocity(double velocity) {
		if (velocity * velocity > constantMaxSpeed * constantMaxSpeed)
			return false;
		return true;
	}
	
	/**
	 * Set the X velocity of this ship to the given velocity.
	 * 
	 * @param  	velocityX
	 *         	The new X velocity for this ship.
	 * @post   	If the given X velocity is a valid velocity for any ship,
	 *         	the X velocity of this new ship is equal to the given
	 *         	X velocity.
	 *       	| if (isValidVelocity(velocityX))
	 *       	|   then new.getVelocityX() == velocityX
	 * @post	If the given velocity is larger thangetConstantMaxSpeed(),
	 * 			the X velocity of this new ship is equal to getConstantMaxSpeed()
	 * 			| if ( (isValidVelocity(velocityX)) && (velocityX < 0) )
	 *       	|   then new.getVelocityX() == getConstantMaxSpeed()
	 * @post	If the given velocity is smaller than negative getConstantMaxSpeed(),
	 * 			the X velocity of this new ship is equal to negative getConstantMaxSpeed()
	 * 			| if ( (isValidVelocity(velocityX)) && (velocityX < 0) )
	 *       	|   then new.getVelocityX() == -getConstantMaxSpeed()
	 */
	@Raw
	public void setVelocityX(double velocityX) {
		if (isValidVelocity(velocityX))
			this.velocityX = velocityX;
		else
			if (velocityX > 0)
				this.velocityX = getConstantMaxSpeed();
			else
				this.velocityX = -getConstantMaxSpeed();
	}

	/**
	 * Set the Y velocity of this ship to the given velocity.
	 * 
	 * @param  	velocityY
	 *         	The new Y velocity for this ship.
	 * @post   	If the given Y velocity is a valid velocity for any ship,
	 *         	the Y velocity of this new ship is equal to the given
	 *         	Y velocity.
	 *       	| if (isValidVelocity(velocityY))
	 *       	|   then new.getVelocityY() == velocityY
	 * @post	If the given velocity is larger thangetConstantMaxSpeed(),
	 * 			the Y velocity of this new ship is equal to getConstantMaxSpeed()
	 * 			| if ( (isValidVelocity(velocityY)) && (velocityY < 0) )
	 *       	|   then new.getVelocityY() == getConstantMaxSpeed()
	 * @post	If the given velocity is smaller than negative getConstantMaxSpeed(),
	 * 			the Y velocity of this new ship is equal to negative getConstantMaxSpeed()
	 * 			| if ( (isValidVelocity(velocityY)) && (velocityY < 0) )
	 *       	|   then new.getVelocityY() == -getConstantMaxSpeed()
	 */
	@Raw
	public void setVelocityY(double velocityY) {
		if (isValidVelocity(velocityY))
			this.velocityY = velocityY;
		else
			if (velocityX > 0)
				this.velocityY = getConstantMaxSpeed();
			else
				this.velocityY = -getConstantMaxSpeed();
	}
	
	
	
		 /*
		  * |-------------------------------------------------------|
		  * | The next methods handle the orientation of the ship.	|
		  * |-------------------------------------------------------| 
		  */
	
	
	
	/**
	 * Variable registering the orientation of this ship.
	 */
	private double orientation;
	/**
	 * Variable registering the value of pi.
	 */
	private final double PI = Math.PI;
	
	/**
	 * Return the orientation of this ship.
	 */
	@Basic @Raw
	public double getOrientation() {
		return this.orientation;
	}
	
	/**
	 * Return pi.
	 */
	@Basic @Raw
	public double getPI() {
		return this.PI;
	}
	
	/**
	 * Check whether the given orientation is a valid orientation for
	 * any ship.
	 *  
	 * @param  	orientation
	 *         	The orientation to check.
	 * @return 
	 *       	| result == (orientation <= 2*getPI()) && (orientation >= 0)
	*/
	public static boolean isValidOrientation(double orientation) {
		if ( (orientation <= 2*Math.PI) && (orientation >= 0) )
			return true;
		return false;
	}
	
	/**
	 * Set the orientation of this ship to the given orientation.
	 * 
	 * @param  	orientation
	 *         	The new orientation for this ship.
	 * @pre    	The given orientation must be a valid orientation for any
	 *         	ship.
	 *       	| isValidOrientation(orientation)
	 * @post   	The orientation of this ship is equal to the given
	 *         	orientation.
	 *       	| new.getOrientation() == orientation
	 */
	@Raw
	public void setOrientation(double orientation) {
		assert isValidOrientation(orientation);
		this.orientation = orientation;
	}
	
	
	
		 /*
		  * |-------------------------------------------------------|
		  * | The next methods handle the shape of the ship.		|
		  * |-------------------------------------------------------| 
		  */
	
	
	
	/**
	 * Variable registering the shape of this ship.
	 */
	private double shape;
	/**
	 * Variable registering the minimum shape of this ship.
	 */
	private double minShape = 0;
	
	
	/**
	 *  Return the minimum shape of this ship.
	 */
	@Basic @Raw
	public double getMinShape() {
		return this.minShape;
	}
	
	/**
	 * Return the shape of this ship.
	 */
	@Basic @Raw @Immutable
	public double getShape() {
		return this.shape;
	}
	
	
	/**
	 * Check whether this ship can have the given shape as its shape.
	 *  
	 * @param  	shape
	 *         	The shape to check.
	 * @return 
	 *       	| result == shape > this.getMinShape()
	*/
	@Raw
	public boolean canHaveAsShape(double shape) {
		if (shape > this.getMinShape())
			return true;
		return false;
	}
	
	
	
		 /*
		  * |-----------------------------------------------------------|
		  * | The next methods handle Moving, Turning and Accelerating	|
		  * |-----------------------------------------------------------| 
		  */
	
	
	
	/**
	 * Change the position of the spacecraft based on the current
	 * position, velocity and a given time duration time.
	 * 
	 * @param  	time
	 * 			the time duration in which the ship will move.
	 * @post   	The X position of this new ship is equal to
	 *         	the current X position times the X velocity times
	 *         	the given time.
	 *       	| new.getPositionX() == this.getpositionX() * this.getVelocityX() * time
	 * @post   	The Y position of this new ship is equal to
	 *         	the current X position times the Y velocity times
	 *         	the given time.
	 *       	| new.getPositionY() == this.positionY() * this.getVelocityY() * time
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
	 * @pre    	The absolute value of the given angle must be a valid orientation for any
	 *         	ship.
	 *       	| isValidOrientation(angle)
	 * @post   	The orientation of this ship is equal to the given
	 *         	orientation.
	 *       	| new.getOrientation() == orientation
	 */
	public void turn(double angle) {
		assert isValidOrientation(Math.abs(angle));
		if (! isValidOrientation(getOrientation() + angle))
			this.setOrientation(orientation + angle - 2*PI);
		else
			this.setOrientation(orientation + angle);
	}
	
	
	/**
	 * Thrusts a ship forwards (in the direction it's facing) with a given acceleration.
	 * 
	 * @param  	acceleration
	 *         	The acceleration with which the ship will thrust forwards.
	 * @post   	If the given acceleration is lower than 0, the velocity will change
	 * 			with an acceleration of 0.
	 *       	| if (acceleration < 0)
	 *       	|	then (new.getVelocityX() == this.getVelocityX()) &&
	 *       	|   	 (new.getVelocityY() == this.getVelocityY())
	 * @post	If the new X velocity’s magnitude would exceed the upper bound c, 
	 * 			then reduce the new X velocity such that it's speed becomes getConstantMaxSpeed()
	 * 			or -getConstantMaxSpeed if it's velocity is lower than 0.
	 * 			| if (!isValidVelocity(getVelocityX() + acceleration * Math.cos(getOrientation()))
	 * 			|	if (getVelocityX() + acceleration * Math.cos(getOrientation() > 0)
	 *       	|   	then new.getVelocityX() == this.getConstantMaxSpeed()
	 *       	|	if (getVelocityX() + acceleration * Math.cos(getOrientation() < 0)
	 *       	|   	then new.getVelocityX() == -this.getConstantMaxSpeed()
	 * @post	If the new Y velocity’s magnitude would exceed the upper bound getConstantMaxSpeed(), 
	 * 			then reduce the new Y velocity such that it's speed becomes getConstantMaxSpeed()
	 * 			or -getConstantMaxSpeed if it's velocity is lower than 0.
	 * 			| if (!isValidVelocity(getVelocityX() + acceleration * Math.cos(getOrientation()))
	 * 			|	if (getVelocityX() + acceleration * Math.cos(getOrientation() > 0)
	 *       	|   	then new.getVelocityX() == this.getConstantMaxSpeed()
	 *       	|	if (getVelocityX() + acceleration * Math.cos(getOrientation() < 0)
	 *       	|   	then new.getVelocityX() == -this.getConstantMaxSpeed()
	 */
	public void thrust(double acceleration) {
		if (acceleration < 0)
			acceleration = 0;
		
		double newVelocityX = getVelocityX() + acceleration * Math.cos(getOrientation());
		if (! isValidVelocity(newVelocityX))
			if (newVelocityX > 0)
				newVelocityX = getConstantMaxSpeed();
			else
				newVelocityX = -getConstantMaxSpeed();

		double newVelocityY = getVelocityY() + acceleration * Math.sin(getOrientation());
		if (! isValidVelocity(newVelocityY))
			if (newVelocityY > 0)
				newVelocityY = getConstantMaxSpeed();
			else
				newVelocityY = -getConstantMaxSpeed();
		
		setVelocityX(newVelocityX);
		setVelocityY(newVelocityY);
	}
	
	
	
		/*
		  * |-----------------------------------------------|
		  * | The next methods handle Collision Detection   |
		  * |-----------------------------------------------| 
		  */
	
	
	
	/**
	 * Gets the distance between this ship and another given ship.
	 * 
	 * @param  	ship
	 * 			the ship to which you want to know the distance.
	 * @throws 	IllegalArgumentException
	 * 			the given ship has an invalid position
	 * 			| (! this.isValidPosition(this.positionX, this.positionY))
	 * @throws 	IllegalArgumentException
	 * 			the other ship has an invalid position
	 * 			| (! ship.isValidPosition(ship.positionX, ship.positionY))
	 * @throws 	IllegalArgumentException
	 * 			the other ship was null
	 * 			| ship == null
	 */
	public double getDistanceBetween(Ship ship) throws IllegalArgumentException {
		double distance;	// distance between the ships
		double distanceX;	// distance between the x-coordinates of the ships
		double distanceY;	// distance between the y-coordinates of the ships
		
		if (ship == null) 
			throw new IllegalArgumentException();
		if ((! this.isValidPosition(this.positionX, this.positionY)) || (! ship.isValidPosition(ship.positionX, ship.positionY)))
			throw new IllegalArgumentException();
		
		distanceX = Math.sqrt((this.getPositionX() - ship.getPositionX()) * (this.getPositionX() - ship.getPositionX()));
		distanceY = Math.sqrt((this.getPositionY() - ship.getPositionY()) * (this.getPositionY() - ship.getPositionY()));
		
		// use formula of Pythagoras to calculate total distance between centers of the ships
		distance = Math.sqrt((distanceX + distanceY) * (distanceX + distanceY));
		
		// the distance between a ship and itself is 0
//		if ((distance == 0) && (this.getShape() == ship.getShape()))
//			return distance;
		
		// use the sizes of the ships to calculate distance between edges
		distance = distance - this.getShape() - ship.getShape();
		
		return distance;
	}
	
	
	/**
	 * Returns true if the ships overlap, false if they don't.
	 * 
	 * @param 	ship
	 * 			the ship of which you want to if it it overlaps this ship.
	 * @return 		true if ships overlap
	 * 				| (getDistanceBetween(ship) =< 0)
	 * @return 		false if ships don't overlap
	 * 				| (getDistanceBetween(ship) > 0)
	 * @throws 	IllegalArgumentException
	 * 			the given ship has an invalid position
	 * 			| (! this.isValidPosition(this.positionX, this.positionY))
	 * @throws 	IllegalArgumentException
	 * 			the other ship has an invalid position
	 * 			| (! ship.isValidPosition(ship.positionX, ship.positionY))
	 * @throws 	IllegalArgumentException
	 * 			the other ship was null
	 * 			| ship == null
	 */
	public boolean overlap(Ship ship) throws IllegalArgumentException {
		if (ship == null) 
			throw new IllegalArgumentException();
		if ((! this.isValidPosition(this.positionX, this.positionY)) || (! ship.isValidPosition(ship.positionX, ship.positionY)))
			throw new IllegalArgumentException();

		if (getDistanceBetween(ship) <= 0)
			return true;
		else 
			return false;
		
	}
	
	
	/**
	 * Gets the time to collision between this and a given ship.
	 * 
	 * @param  	ship
	 * 			the ship to which you want to know the collision time.
	 * @throws 	IllegalArgumentException
	 * 			the given ship has an invalid position
	 * 			| (! this.isValidPosition(this.positionX, this.positionY))
	 * @throws 	IllegalArgumentException
	 * 			the other ship has an invalid position
	 * 			| (! ship.isValidPosition(ship.positionX, ship.positionY))
	 * @throws 	IllegalArgumentException
	 * 			the other ship was null
	 * 			| ship == null
	 */
	public double getTimeToCollision(Ship ship) throws IllegalArgumentException {
		if (ship == null) 
			throw new IllegalArgumentException();
		if ((! this.isValidPosition(this.positionX, this.positionY)) || (! ship.isValidPosition(ship.positionX, ship.positionY)))
			throw new IllegalArgumentException();
		
		double[] deltaR = {ship.getPositionX() - this.getPositionX(), ship.getPositionY() - this.getPositionY()};
		double[] deltaV = {ship.getVelocityX() - this.getVelocityX(), ship.getVelocityY() - this.getVelocityY()};
		if (evaluateScalar(deltaR, deltaV) >= 0)
			return Double.POSITIVE_INFINITY;
		
		double omega = this.getShape() + ship.getShape();
		double d = (evaluateScalar(deltaR, deltaV)*evaluateScalar(deltaR, deltaV)) - evaluateScalar(deltaV, deltaV)*(evaluateScalar(deltaR, deltaR) - omega*omega);
		if (d <= 0)
			return Double.POSITIVE_INFINITY;
		

		double deltaT = -(evaluateScalar(deltaV, deltaR) + Math.sqrt(d)) / evaluateScalar(deltaV, deltaV);
		return deltaT;
	}
	
	
	/**
	 * Gets the collision position between this and a given ship.
	 * 
	 * @param  	ship
	 * 			the ship to which you want to know the collision position.
	 * @throws 	IllegalArgumentException
	 * 			the given ship has an invalid position
	 * 			| (! this.isValidPosition(this.positionX, this.positionY))
	 * @throws 	IllegalArgumentException
	 * 			the other ship has an invalid position
	 * 			| (! ship.isValidPosition(ship.positionX, ship.positionY))
	 * @throws 	IllegalArgumentException
	 * 			the other ship was null
	 * 			| ship == null
	 */
	public double[] getCollisionPosition(Ship ship) throws IllegalArgumentException {
		if (ship == null) 
			throw new IllegalArgumentException();
		double time = getTimeToCollision(ship);
		if (time == Double.POSITIVE_INFINITY)
			return null;
		
		double newPositionX = getPositionX() + getVelocityX() * time;
		double newPositionY = getPositionY() + getVelocityY() * time;
		double[] vector = {newPositionX, newPositionY};
		return vector;
	}
	
	
	
	/*
	  * |-----------------------------------------------|
	  * | The next methods are private helper methods   |
	  * |-----------------------------------------------| 
	  */
	
	
	
	private double evaluateScalar(double[] vector1, double[] vector2) {
		return vector1[0]*vector2[0] + vector1[1]*vector2[1];
	}
	
}
