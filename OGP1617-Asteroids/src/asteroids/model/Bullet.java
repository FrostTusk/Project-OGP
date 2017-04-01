package asteroids.model;

import asteroids.helper.Position;
import be.kuleuven.cs.som.annotate.*;

/* NOTES:
*	1. Constants:
*		1.	constantMaxSpeed = the maximum speed this bullet can achieve.
*		2.	minRadius =  the minimum radius for each bullet.
*/

/*
 * Methods Index:
 * 1. Methods that handle the Initialization of the bullet
 * 2. Methods that handle the Position of the bullet
 * 3. Methods that handle the Speed of the bullet
 * 4. Methods that handle the Radius of the bullet
 * 5. Methods that handle the Mass of the bullet
 * 6. Methods that handle Moving, Turning and Accelerating
 * 7. Methods that handle Calculating Distance, Overlap, and Collision Detection
 * 8. Helper Methods
 */

/**
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
public class Bullet {
	
			/*
			 * |----------------------------------------------------------------|
			 * | 1. The next method handles the Initialization of the bullet.	|
			 * |----------------------------------------------------------------| 
			 */

	
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
	
	

			/*
			 * |--------------------------------------------------------|
			 * | 2. The next methods handle the Position of the bullet.	|
			 * |--------------------------------------------------------| 
			 */
	
	
	
	/**
	 * Variable registering the position of this bullet.
	 */
	private Position position;
	
	
	/**
	 * Return the position of this bullet.
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
	@Basic @Raw
	public double getVelocityX() {
		return this.velocityX;
	}
	
	/**
	 * Return the Y velocity of this bullet.
	 */
	@Basic @Raw
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
	@Basic @Raw
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
	public double mass;
	
	
	/**
	 * Return the current mass of the bullet.
	 * @return	Returns the mass of the current bullet.
	 */
	@Basic @Raw
	public double getMass() {
		return this.mass;
	}
	
	
	public boolean isValidMass(double mass) {
		return mass > ( 4/3 * Math.PI * Math.pow(getRadius(), 3) * (1.42 * Math.pow(10, 12)) );
	}
	
	
	public void setMass(double mass) {
		if (isValidMass(mass)) {
			this.mass = mass;
		}
		else {
			this.mass = (4/3 * Math.PI * Math.pow(getRadius(), 3) * 1.42 * Math.pow(10, 12));
		}
	}
	
	
	/**
	 * Return the current mass of the bullet and its cargo.
	 * @return	Returns the mass of the current bullet + the mass of the objects on the bullet.
	 */
	public double getTotalMass() {
		return getMass(); //TODO + mass of cargo
	}
	
}
