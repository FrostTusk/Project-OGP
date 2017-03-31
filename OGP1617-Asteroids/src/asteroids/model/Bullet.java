package asteroids.model;

import be.kuleuven.cs.som.annotate.*;

public class Bullet {

		
	/**
	 * Variable registering the position of this bullet.
	 */
	private Position position;
	
	/**
	 * Return the position of this bullet.
	 */
	@Basic 
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
	 *       	| ! Position(positionX, PositonY).isValidPosition(positionX, positonY)
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
	  * |-------------------------------------------------------|
	  * | 3. The next methods handle the Speed of the Bullet.	|
	  * |-------------------------------------------------------| 
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
	@Basic 
	public double getVelocityX() {
		return this.velocityX;
	}
	
	/**
	 * Return the Y velocity of this bullet.
	 */
	@Basic 
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
	 * Set the X and Y velocity of this bullet to the given velocities.
	 * 
	 * @param  	velocityX
	 *         	The new X velocity for this bullet.
	 * @param  	velocityY
	 *         	The new Y velocity for this bullet.
	 *         
	 * @post   	If the given velocities are valid velocities for any bullet (they make up a valid speed),
	 *         	the X and Y velocity of this new bullet is equal to the given	X and Y velocity.
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
		  * | 5. The next methods handle the Radius of the Ship.	|
		  * |-------------------------------------------------------| 
		  */
	
	
	
	/**
	* Variable registering the radius of this ship.
	*/
	private final double radius = 0;
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
		  * |-------------------------------------------------------|
		  * | 5. The next methods handle the Radius of the Ship.	|
		  * |-------------------------------------------------------| 
		  */
	
	
	
	/**
	 * Variable registering the mass of this ship.
	 */
	public double mass;
	
	
	/**
	 * Return the current mass of the ship.
	 * @return	Returns the mass of the current ship.
	 */
	@Basic
	public double getMass() {
		return this.mass;
	}
	
	
	public boolean isValidMass(double mass) {
		return mass > ( 4/3 * Math.PI * Math.pow(this.getRadius(), 3) * (1.42 * Math.pow(10, 12)) );
	}
	
	
	public void setMass(double mass) {
		if (isValidMass(mass)) {
			this.mass = mass;
		}
		else {
			this.mass = (4/3 * Math.PI * Math.pow(this.getRadius(), 3) * 1.42 * Math.pow(10, 12));
		}
	}
	
	
	/**
	 * Return the current mass of the ship and its cargo.
	 * @return	Returns the mass of the current ship + the mass of the objects on the ship.
	 */
	public double getTotalMass() {
		return this.getMass(); //TODO + mass of cargo
	}
}
