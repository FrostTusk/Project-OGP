package asteroids.model;

//import java.util.ArrayList;
import java.util.HashSet;
//import java.util.List;
import java.util.Set;

import asteroids.helper.Entity;
import asteroids.helper.Helper;
import asteroids.helper.Position;
import asteroids.helper.Thruster;
import be.kuleuven.cs.som.annotate.*;


/* Constants:
*	1.	constantMaxSpeed = the maximum speed this ship can achieve.
*	2.	minRadius = the minimum radius for each ship.
*	3. 	force = the force of this ship.
*/

/*
 * Methods Index:
 * #1# Basic Methods
 * 		1. Methods that handle the Initialization and Termination of the Ship
 * 		2. Methods that handle the Position of the Ship
 * 		3. Methods that handle the Speed of the Ship
 * 		4. Methods that handle the Orientation of the Ship
 * 		5. Methods that handle the Radius of the Ship
 * 		6. Methods that handle the Mass of the ship
 * #2# Association Methods
 * 		7. Methods that handle the association with Worlds
 * 		8. Methods that handle the association with Bullets
 * #3# Advanced Methods
 * 		9. Methods that handle Moving, Turning and Accelerating
 * 		10. Methods that handle the Thruster
 * 		11. Methods that handle Calculating Distance and Overlap
 * #4# Collision Detection Methods
 * 		12. Methods that handle Collision Detection
 * 		13. Methods that handle Resolving Collisions
 * #5# Other Methods
 *		14. Helper Methods
 */

/**
 * A class of ships.
 * 
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
public class Ship extends Entity {
	
		/*
	     * |----------------------------|
	     * | #Header-1# Basic Methods.	|
	     * |----------------------------| 
	     */
	
	
			/*
			 * |----------------------------------------------------------------------------|
			 * | 1. The next method handles the Initialization and Termination of the Ship.	|
			 * |----------------------------------------------------------------------------| 
			 */
	
	
	
	/**
	 * Variable registering the helper for this ship.
	 */
	private Helper helper = new Helper();
	
	
	/**
	 * Initialize this new ship with given X and Y position, a given X and Y velocity, a given orientation,  a given radius, and a given mass.
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
	 * @throws 	IllegalArgumentException
	 *         	This new bullet cannot have the given X and Y position as position.
	 *       	| ! this.getPosition().isValidPosition(positionX, positionY)
	 * @throws 	IllegalArgumentException
	 *         	This new bullet cannot have the given radius as its radius.
	 *       	| ! this.canHaveAsRadius(this.getRadius())
	 */
	public Ship(double positionX, double positionY, double velocityX, double velocityY, double orientation, double radius, double mass)
		throws IllegalArgumentException {
		try {
			this.setPosition(positionX, positionY);
		}
		catch (IllegalArgumentException exc) {
			throw new IllegalArgumentException();
		}

		setVelocity(velocityX, velocityY);
		
		assert isValidOrientation(orientation);
		this.setOrientation(orientation);
		
		if (! canHaveAsRadius(radius))
			throw new IllegalArgumentException();
		this.radius = radius;
		
		setMass(mass);
	}
	
	
	/**
	 * Variable registering if this ship is terminated or not.
	 */
	private boolean isTerminated = false;
	
	
	/**
	 * Return the whether or not this ship is terminated.
	 */
	@Basic @Raw
	public boolean isTerminated() {
		return this.isTerminated;
	}
	
	
	/**
	 * Terminates this ship. Breaks up any associations with entities and worlds.
	 * Prepares this object for the garbage collector.
	 * @see implementation
	 */
	public void terminate() {
		if (getWorld() != null) world.removeEntity(this);
		this.isTerminated = true;
	}
	
	
	
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
	@Basic @Override @Raw
	public double getVelocityX() {
		return this.velocityX;
	}
	
	/**
	 * Return the velocity of this ship.
	 */
	@Basic @Override @Raw
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
	@Basic @Raw
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
	@Raw
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
	@Raw
	public void setOrientation(double orientation) {
		assert isValidOrientation(orientation);
		this.orientation = orientation;
	}
	
	
	
			/*
			 * |----------------------------------------------------|
			 * | 5. The next methods handle the Radius of the Ship.	|
			 * |----------------------------------------------------| 
			 */
	
	
	
	/**
	 * Variable registering the radius of this ship.
	 */
	private final double radius;
	// #Constant-2#
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
	@Basic @Override @Raw
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
	 * Variable registering the mass of this ship.
	 */
	private double mass;
	
	
	/**
	 * Return the current mass of the ship.
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
		double totalMass = this.getMass();
		
		for (Bullet bullet : this.bullets) {
			totalMass += bullet.getMass();
		}
		return totalMass;
	}
	
	/**
	 * Check whether the given mass is a valid mass for this ship.
	 *  
	 * @param  	mass
	 * 			the mass to check
	 * @return	Returns whether or not the mass is a valid mass for this ship
	 * 			or not. true if it is, false if not.
	 *       	| result == (mass > (4/3) * Math.PI * Math.pow(this.getRadius(), 3) * (1.42 * Math.pow(10, 12)) )
	 */
	@Raw
	public boolean isValidMass(double mass) {
		return mass > (4/3) * Math.PI * Math.pow(this.getRadius(), 3) * (1.42 * Math.pow(10, 12));
	}
	
	
	/**
	 * Set the mass of this ship to the given mass.
	 * 
	 * @param  	mass
	 * 			the new mass for this ship
	 *         
	 * @post   	If the given mass is a valid mass for this ship,
	 *         	the mass of this ship is set to the given mass.
	 *       	| if (isValidMass(mass)))
	 *       	|   then new.getMass() == mass
	 * @post	If the given mass isn't valid, the mass
	 * 			of this new ship will be equal to a default value.
	 *       	| if (! isValidMass(mass))
	 *       	|   then new.getMass() == (4/3) * Math.PI * Math.pow(this.getRadius(), 3) * 1.42 * Math.pow(10, 12)
	 */
	@Raw
	public void setMass(double mass) {
		if (isValidMass(mass)) this.mass = mass;
		else this.mass = (4/3) * Math.PI * Math.pow(this.getRadius(), 3) * 1.42 * Math.pow(10, 12);
	}
	
	
	
		/*
	     * |------------------------------------|
	     * | #Header-2# Association Methods.	|
	     * |------------------------------------| 
	     */
	
	
			/*
		     * |------------------------------------------------------------|
		     * | 7. The next methods handle the association with worlds.	|
		     * |------------------------------------------------------------| 
		     */
	
	
	
	/**
	* Variable registering the world of this ship.
	*/
	public World world = null;
	
	
	/**
	* Return the world associated with this ship.
	*/
	@Basic @Override @Raw
	public World getWorld() {
		return this.world;
	}
	
	
	/**
	* Check whether this ship can have the given world as world. 
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
	* Check whether this ship is currently located in a given world. 
	* This method only checks if the shape of the ship is located in a given world,
	* it does not check if it's associated with the given world.
	*  
	* @param  	world
	*         	The world to check.
	*         
	* @see implementation
	*/
	@Override @Raw
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
	@Override @Raw
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
	@Override // TODO @Raw?
	public void deSetWorld() throws NullPointerException {
		if (getWorld() ==  null) throw new NullPointerException();
		this.world = null;
	}
	
	
	
		/*
	     * |------------------------------------------------------------|
	     * | 8. The next methods handle the association with bullets.	|
	     * |------------------------------------------------------------| 
	     */
	
	
	
	/**
	* Variable registering the bullets of this ship.
	*/
	private Set<Bullet> bullets = new HashSet<Bullet>();
	
	
	public Set<Bullet> getAllBullets() {
		return bullets;
	}
	
	public int getBulletsCount() {
		return bullets.size();
	}
	
	
	/**
	* Check whether this ship can have the given bullet as a bullet. 
	*
	* @param  	bullet
	*         	The bullet to check.
	*         
	* @see implementation
	*/
	@Raw
	public boolean canHaveAsBullet(Bullet bullet) {
		if (bullets.contains(bullet)) return false;
		if (bullet.canHaveAsShip(this)) return true;
		return false;
	}
	
	
	/**
	* Add a given bullet to this ship's collection of bullets. 
	*
	* @param  	bullet
	*         	The bullet to be added.
	*         
	* @see implementation
	*/
	public void addBullet(Bullet bullet) {
		bullets.add(bullet);
	}
	
	/**
	* Remove a given bullet from this ship's collection of bullets. 
	*
	* @param  	bullet
	*         	The bullet to be removed.
	*         
	* @see implementation
	*/
	public void removeBullet(Bullet bullet) throws IllegalArgumentException {
		if (!bullets.contains(bullet)) throw new IllegalArgumentException();
		else bullets.remove(bullet);
	}
	
	
	/**
	* Load a given bullet into this ship.
	*  
	* @param  	bullet
	*         	The bullet to be loaded.
	*         
	* @see implementation
	*/
	public void loadBullet(Bullet bullet) throws IllegalArgumentException {
		if (!canHaveAsBullet(bullet)) throw new IllegalArgumentException();
		addBullet(bullet);
		bullet.setShip(this);
	}
	
	
	/**
	* Reload a given bullet into this ship.
	*  
	* @param  	bullet
	*         	The bullet to be reloaded.
	*         
	* @see implementation
	*/
	public void reloadBullet(Bullet bullet) {
		loadBullet(bullet);
	}
	
	
	/**
	* Fires a bullet out of the collection of bullets.
	*     
	* @see implementation
	*/
	public void fireBullet() {
		if (bullets.size() > 0)
			for (Bullet bullet: bullets) {
				fireBullet(bullet);
				break;
			}
	}
	
	/**
	* Fires a requested bullet out of the collection of bullets.
	*     
	* @param	bulletRequest
	* 			The bullet requested to be fired.
	* 
	* @see implementation
	*/
	public void fireBullet(Bullet bulletRequest) {
		Bullet bullet = bulletRequest;
		if (getWorld() == null) return;
		
		if (!bullets.contains(bullet)) {
			fireBullet();
			return;
		}
		
		bullets.remove(bullet);
		bullet.setSource(this);
		bullet.setVelocity(250 * Math.cos(getOrientation()), 250 * Math.sin(getOrientation()));	
		bullet.setPosition( getPosition().getPositionX() + getRadius() * Math.cos(getOrientation()) 
								+ bullet.getRadius() * Math.cos(getOrientation()), 
						    getPosition().getPositionY() + getRadius() * Math.sin(getOrientation()) 
						    	+ bullet.getRadius() * Math.sin(getOrientation()) );

		for (Entity entity : world.getAllEntities()) if (bullet.overlap(entity)) bullet.resolveCollision(entity);
			
		bullet.setShip(null);
		try {
			bullet.setWorld(getWorld());
		}
		catch (IllegalArgumentException exc) {
			bullet.terminate();
		}
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
	@Override
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
		if (! (acceleration > 0)) acceleration = 0;
			
		double newVelocityX = getVelocityX() + acceleration * Math.cos(getOrientation());
		double newVelocityY = getVelocityY() + acceleration * Math.sin(getOrientation());
		setVelocity(newVelocityX, newVelocityY);
	}
	
	
	
			/*
			 * |--------------------------------------------|
			 * | 10. The next methods handle the Thruster.	|
			 * |--------------------------------------------| 
			 */
	
	
	// #Constant-3#
	/**
	* Variable registering the force of this ship.
	*/
	private double force = 1.1 * Math.pow(10, 12);
	/**
	* Variable registering the thruster of this ship.
	*/
	private Thruster thruster = new Thruster();
	
	
	/**
	* Return the force of this ship.
	*/
	@Basic @Raw
	public double getForce() {
		return this.force;
	}
	
	/**
	* Return the status of this ship's thruster.
	* True => On, False => Off
	*/
	@Basic @Raw
	public boolean getThrustStatus() {
		return this.thruster.getThrustStatus();
	}
	
	/**
	* Return the acceleration of this ship.
	*/
	@Basic @Raw
	public double getAcceleration() {
		return getForce() / getTotalMass();
	}
	
	
	/**
	 * Set the force of this ship to the given force.
	 * 
	 * @param  	force
	 *         	The new force for this ship.
	 * 
	 * @see implementation
	 */
	@Raw
	public void setForce(double force) {
		this.force = force;
	}
	
	
	/**
	 * Toggle the thruster of this ship on.
	 * @see implementation
	 */
	@Raw
	public void thrustOn() {
		this.thruster.thrustOn();
		thrust(getAcceleration());
	}
	
	/**
	 * Toggle the thruster of this ship off.
	 * @see implementation
	 */
	@Raw
	public void thrustOff() {
		this.thruster.thrustOff();
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
	     * |--------------------------------------------|
	     * | #Header-4# Collision Detection Methods.	|
	     * |--------------------------------------------| 
	     */
	
	
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
	@Override @Raw
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
	@Override @Raw
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
	
	
	
			/*
			 * |----------------------------------------------------|
			 * | 13. The next methods handle resolving Collisions.	|
			 * |----------------------------------------------------| 
			 */

	
	
	/**
	 * Resolves the collision between this ship and a given world.
	 * @param 	world
	 * 			The world to be used.
	 * 
	 * @see implementation
	 */
	public void resolveCollision(World world) {
		double[] position = getCollisionPosition(world);
		if (position == null) return;
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
		else if (entity.getType() == "Bullet") resolveCollisionBullet((Bullet)entity);
	}


	/**
	 * Resolves the collision between this ship and a given ship.
	 * @param 	ship
	 * 			The ship to be used.
	 * 
	 * @see implementation
	 */
	public void resolveCollisionShip(Ship ship) {
		double[] deltaV = {ship.getVelocityX() - getVelocityX(), ship.getVelocityY() - getVelocityY()};
		double[] deltaR = {ship.getPosition().getPositionX() - getPosition().getPositionX(), ship.getPosition().getPositionY() - getPosition().getPositionY()};
		double deltaVR = deltaV[0] * deltaR[0] + deltaV[1] * deltaR[1];
		
		double J = (2 * getMass() * ship.getMass() * deltaVR) 
				/ (this.getRadius() * (this.getMass() + ship.getMass()));
		
		double Jx1 = J * (this.getPosition().getPositionX() - ship.getPosition().getPositionX()) / (getRadius() + ship.getRadius());
		double Jy1 = J * (this.getPosition().getPositionY() - ship.getPosition().getPositionY()) / (getRadius() + ship.getRadius());
	
		this.setVelocity(this.getVelocityX() + Jx1/this.getMass(), this.getVelocityY() + Jy1/this.getMass());
		
		double Jx2 = J * (ship.getPosition().getPositionX() - this.getPosition().getPositionX()) / (getRadius() + ship.getRadius());
		double Jy2 = J * (ship.getPosition().getPositionY() - this.getPosition().getPositionY()) / (getRadius() + ship.getRadius());
		
		ship.setVelocity(ship.getVelocityX() + Jx2/ship.getMass(), ship.getVelocityY() + Jy2 / ship.getMass());
	}
	
	/**
	 * Resolves the collision between this ship and a given bullet.
	 * @param 	bullet
	 * 			The bullet to be used.
	 * 
	 * @see implementation
	 */
	public void resolveCollisionBullet(Bullet bullet) {
		if (bullet.getSource() == this) reloadBullet(bullet);
		else {
			this.terminate();
			bullet.terminate();
		}
		
	}
	

	
		/*
	     * |----------------------------|
	     * | #Header-5# Other Methods.	|
	     * |----------------------------| 
	     */
	
	
			/*
		     * |--------------------------------------------|
		     * | 14. The next methods are Helper Methods.	|
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
	
	
	/**
	 * Returns the type of this class in string format.
	 * 
	 * @see implementation
	 */
	@Override @Raw
	public String getType() {
		return "Ship";
	}
	
}
 