package asteroids.model;

import java.util.HashSet;
import java.util.Set;

import asteroids.helper.Entity;
import asteroids.helper.Thruster;

import be.kuleuven.cs.som.annotate.*;


/* Constants:
*	1. 	force = the force of this ship.
*/

/*
 * Methods Index:
 * #1# Basic Methods
 * 		1. Methods that handle the Initialization and Termination of the Ship
 * 		2. Methods that handle the Orientation of the Ship
 * 		3. Methods that handle the Mass of the ship
 * #2# Association Methods
 * 		4. Methods that handle the association with Worlds
 * 		5. Methods that handle the association with Bullets
 * #3# Advanced Methods
 * 		6. Methods that handle Turning and Accelerating
 * 		7. Methods that handle the Thruster
 * #4# Collision Detection Methods
 * 		8. Methods that handle Resolving Collisions
 * #5# Other Methods
 *		9. Helper Methods
 */

/**
 * A class of ships. //TODO Do we need to keep all these invariants?
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
	 *       	| ! this.getPosition().isValidPosition(positionX, positionY)	// TODO is this done right?
	 * @throws 	IllegalArgumentException
	 *         	This new bullet cannot have the given radius as its radius.
	 *       	| ! this.canHaveAsRadius(this.getRadius())
	 */
	public Ship(double positionX, double positionY, double velocityX, double velocityY, double orientation, double radius, double mass)
		throws IllegalArgumentException {
		this.minRadius = 10;	// First the constants are set.
		setDensity(1.42 * Math.pow(10, 12));
		setForce(1.1 * Math.pow(10, 12));
		
		try {	// Check if the position can be set.
			setPosition(positionX, positionY);
		}
		catch (IllegalArgumentException exc) {
			throw new IllegalArgumentException();
		}

		setVelocity(velocityX, velocityY);	// Set the velocity.
		
		assert isValidOrientation(orientation);	// Check if the orientation can be set.
		setOrientation(orientation);
		
		if (! canHaveAsRadius(radius)) throw new IllegalArgumentException();	// Check if the radius can be set.
		this.radius = radius;
		
		setMass(mass);	// Set the mass.
	}
	

	/**
	 * Terminates this ship. Breaks up any associations with entities and worlds.
	 * Prepares this object for the garbage collector.
	 * @see implementation
	 */
	@Override @Raw
	public void terminate() {
		if (isTerminated()) return;
		if (getWorld() != null) { 
			try {
				getWorld().removeEntity(this);
				this.setWorld(null);
			}
			catch (IllegalArgumentException exc) {}	// Empty catch because if an IllegalArgumentException
		}											// is thrown, it means that the association wasn't set to start with.		
		for (Bullet bullet: getAllBullets()) {		// This means that the association already doesn't exist. We don't have to do anything.
			try {
				this.removeBullet(bullet);
				bullet.setShip(null);
			}
			catch (IllegalArgumentException exc) {}	// See former explanation.
		}
		this.isTerminated = true;
	}
	
	
	
			/*
			 * |------------------------------------------------------------|
			 * | 2. The next methods handle the Orientation of the Ship.	|
			 * |------------------------------------------------------------| 
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
			 * | 3. The next methods handle the mass of the ship.	|
			 * |----------------------------------------------------| 
			 */
	

	
	/**
	 * Return the current mass of the ship and its cargo.
	 * @return	Returns the mass of the current ship plus the mass of the objects on the ship.
	 */
	@Raw
	public double getTotalMass() {
		double totalMass = getMass();
		for (Bullet bullet : this.bullets) totalMass += bullet.getMass();
		return totalMass;
	}
	
	
	/**
	 * Check whether the given mass is a valid mass for this ship.
	 *  
	 * @param  	mass
	 * 			the mass to check
	 * @return	Returns whether or not the mass is a valid mass for this ship
	 * 			or not. true if it is, false if not.
	 *       	| result == ( mass > (4/3) * Math.PI * Math.pow(this.getRadius(), 3) * this.getDensity )
	 */
	@Raw
	public boolean isValidMass(double mass) {
		return mass > (4/3) * Math.PI * Math.pow(getRadius(), 3) * getDensity();
	}
	
	
	/**
	 * Set the mass of this ship to the given mass.
	 * 
	 * @param  	mass
	 * 			the new mass for this ship
	 *         
	 * @post   	If the given mass is a valid mass for this ship,
	 *         	the mass of this new ship is set to the given mass.
	 *       	| if (isValidMass(mass))
	 *       	|   then new.getMass() == mass
	 * @post	If the given mass isn't valid, the mass
	 * 			of this new ship will be equal to a default value.
	 *       	| if (! isValidMass(mass))
	 *       	|   then new.getMass() == (4/3) * Math.PI * Math.pow(this.getRadius(), 3) * this.getDensity()
	 */
	@Raw
	public void setMass(double mass) {
		if (isValidMass(mass)) this.mass = mass;
		else this.mass = (4/3) * Math.PI * Math.pow(getRadius(), 3) * getDensity();
	}
	
	
	
		/*
	     * |------------------------------------|
	     * | #Header-2# Association Methods.	|
	     * |------------------------------------| 
	     */
	
	
			/*
		     * |------------------------------------------------------------|
		     * | 4. The next methods handle the association with worlds.	|
		     * |------------------------------------------------------------| 
		     */
	

	
	/**
	* Check whether this ship can have the given world as world. 
	*  
	* @param  	world
	*         	The world to check.
	*         
	* @see implementation
	*/
	@Override @Raw
	public boolean canHaveAsWorld(World world) {
		if (world == null) return true;	// TODO Is this a good fix?
		return (getWorld() == null) && (isInWorld(world));
	}
	

	
			/*
		     * |------------------------------------------------------------|
		     * | 5. The next methods handle the association with bullets.	|
		     * |------------------------------------------------------------| 
		     */
	
	
	
	/**
	* Variable registering the bullets of this ship.
	*/
	private Set<Bullet> bullets = new HashSet<Bullet>();
	
	/**
	 * Return the collection of bullets of this ship.
	 */
	@Basic @Raw
	public Set<Bullet> getAllBullets() {
		return this.bullets;
	}
	
	/**
	 * Return the number of bullets on this ship.
	 */
	@Basic @Raw
	public int getBulletsCount() {
		return this.bullets.size();
	}
	
	
	/**
	* Check whether this ship can have the given bullet as a bullet. 
	*
	* @param  	bullet
	*         	The bullet to check.
	*         
	* @see implementation
	* 		// TODO Bullet has to lie in the circle of Ship
	*/
	@Raw
	public boolean canHaveAsBullet(Bullet bullet) {
		if ( (this.bullets.contains(bullet)) || (bullet == null) ) return false;
		return true;
		//		if (bullet.canHaveAsShip(this)) return true; 
//		return false;
	}
	
	
	/**
	* Add a given bullet to this ship's collection of bullets. 
	* This method works immediately with the collection.
	* Load 
	* @param  	bullet
	*         	The bullet to be added.
	*         
	* @see implementation
	*/
	@Raw
	private void addBullet(Bullet bullet) {
		this.bullets.add(bullet);
		
	}
	
	/**
	* Remove a given bullet from this ship's collection of bullets. 
	*
	* @param  	bullet
	*         	The bullet to be removed.
	*         
	* @see implementation
	*/
	@Raw // TODO @Raw?
	public void removeBullet(Bullet bullet) throws IllegalArgumentException {
		if (!this.bullets.contains(bullet)) throw new IllegalArgumentException();
		this.bullets.remove(bullet);
		// TODO	bullet.setShip(null); ?
		// TODO bullet.setWorld(this.getWorld); ?
	}
	
	
	/**
	* Load a given bullet into this ship. Loading a bullet will add
	* the given bullet to the ship's collection and set it's ship as this ship.
	*  
	* @param  	bullet
	*         	The bullet to be loaded.
	*         
	* @see implementation
	*/
	public void loadBullet(Bullet bullet) throws IllegalArgumentException {
		if ( (!bullet.canHaveAsShip(this)) || (!this.canHaveAsBullet(bullet)) ) throw new IllegalArgumentException();
		addBullet(bullet);
		try {
			bullet.setShip(this);
		}
		catch (IllegalArgumentException exc) {
			throw new IllegalArgumentException();
		}
	}
	
	
	/**
	* Reload a given bullet into this ship.
	*  
	* @param  	bullet
	*         	The bullet to be reloaded.
	*         
	* @see implementation
	* 		// TODO declarative documentation.
	*/
	public void reloadBullet(Bullet bullet) {
		try {
			bullet.resetSource(this);
			bullet.setWorld(null);
			loadBullet(bullet);
		}
		catch (IllegalArgumentException exc) {
			return;
		}
	}
	
	
	/**
	* Fires a bullet out of the collection of bullets.
	*     
	* @see implementation
	* 		// TODO declarative documentation.
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
	* @param	bullet
	* 			The bullet requested to be fired.
	* 
	* @see implementation
	* 		// TODO declarative documentation.
	*/
	public void fireBullet(Bullet bullet) {
		if ( (this.getWorld() == null) || (bullet == null) ) return;
		
		if (!bullets.contains(bullet)) {
			fireBullet();
			return;
		}
		
		this.removeBullet(bullet);
		bullet.setSource(this);
		bullet.setShip(null);
		bullet.setVelocity(250 * Math.cos(getOrientation()), 250 * Math.sin(getOrientation()));	
		bullet.setPosition( getPosition().getPositionX() + getRadius() * Math.cos(getOrientation()) 
								+ bullet.getRadius() * Math.cos(getOrientation()), 
						    getPosition().getPositionY() + getRadius() * Math.sin(getOrientation()) 
						    	+ bullet.getRadius() * Math.sin(getOrientation()) );

		for (Entity entity : world.getAllEntities()) if (bullet.overlap(entity)) bullet.resolveCollision(entity);
			
		try {
			bullet.setWorld(this.getWorld());
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
			 * |--------------------------------------------------------|
			 * | 6. The next methods handle Turning and Accelerating.	|
			 * |--------------------------------------------------------| 
			 */
	
	
	
	/**
	 * Add a given angle to the current orientation of the ship.
	 * 
	 * @param  	angle
	 *         	The angle to be added to the current orientation of the sip.
	 *         
	 * @pre    	The absolute value of the given angle must be a valid orientation for any
	 *         	ship.
	 *       	| this.isValidOrientation(Math.abs(angle))
	 *       
	 * @post	The orientation of this ship is equal to the original orientation plus the given angle the
	 * 			orientation plus the given angle is a valid angle.
	 * 			| if (this.isValidOrientation(this.getOrientation() + angle))
	 * 			|	then new.getOrientation() == this.getOrientation() + angle
	 * @post   	The orientation of this ship is equal to the original orientation plus the given angle minus 2 times pi if the
	 *         	orientation plus the given angle is not a valid angle and the given angle was larger than zero.
	 *       	| if (! this.isValidOrientation(this.getOrientation() + angle))
	 *       	|	if (angle > 0)
	 *       	|		then new.getOrientation() == this.getOrientation() + angle - 2*Math.PI
	 * @post   	The orientation of this ship is equal to the original orientation plus the given angle plus 2 times pi if the
	 *         	orientation plus the given angle is not a valid angle and the given angle was smaller than zero.
	 *       	| if (! this.isValidOrientation(this.getOrientation() + angle))
	 *       	|	if (angle < 0)
	 *       	|		then new.getOrientation() == this.getOrientation() + angle - 2*Math.PI
	 */
	public void turn(double angle) {
		assert isValidOrientation(Math.abs(angle));
		
		if (! isValidOrientation(getOrientation() + angle))
			if (angle > 0) 
				setOrientation(orientation + angle - 2*Math.PI);
			else 
				setOrientation(orientation + angle + 2*Math.PI);	
		else
			setOrientation(orientation + angle);
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
			 * | 7. The next methods handle the Thruster.	|
			 * |--------------------------------------------| 
			 */
	
	
	
	/**
	* Variable registering the force of this ship.
	*/
	private double force;	// #Constant-1#
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
	     * |--------------------------------------------|
	     * | #Header-4# Collision Detection Methods.	|
	     * |--------------------------------------------| 
	     */
	

			/*
			 * |----------------------------------------------------|
			 * | 8. The next methods handle resolving Collisions.	|
			 * |----------------------------------------------------| 
			 */

	// TODO Are these Methods Raw?
	
	/**
	 * Resolves the collision between this ship and a given world.
	 * 
	 * @param 	world
	 * 			The world to be used.
	 * 
	 * @see implementation
	 * 		// TODO declarative documentation.
	 * 		// TODO apparently collide?
	 * 		// TODO what if they are not colliding?
	 */
	public void resolveCollision(World world) throws NullPointerException {
		if (world == null) throw new NullPointerException();
		double[] position = getCollisionPosition(world);
		if (position == null) return;	// There is no collision so the collision does not need to be resolved.
		if (position[0] == this.world.getWidth() || position[0] == 0) 
			setVelocity(-getVelocityX(), getVelocityY());
		if (position[0] == this.world.getHeight() || position[1] == 0) 
			setVelocity(getVelocityX(), -getVelocityY());
	}
	
	/**
	 * Resolves the collision between this ship and a given entity.
	 * 
	 * @param 	entity
	 * 			The entity to be used.
	 * 
	 * @see implementation
	 * 		// TODO declarative documentation.
	 */
	public void resolveCollision(Entity entity) throws NullPointerException, IllegalArgumentException {
		if (entity == null) throw new NullPointerException();
		try {
			if (entity.getType() == "Ship") resolveCollisionShip((Ship)entity);
			else if (entity.getType() == "Bullet") resolveCollisionBullet((Bullet)entity);
		}
		catch (IllegalArgumentException exc) {
			throw new IllegalArgumentException();
		}
	}


	/**
	 * Resolves the collision between this ship and a given ship.
	 * 
	 * @param 	ship
	 * 			The ship to be used.
	 * 
	 * @see implementation
	 * 		// TODO declarative documentation.
	 */
	public void resolveCollisionShip(Ship ship) throws NullPointerException, IllegalArgumentException {
		if (ship == null) throw new NullPointerException();
		if (!this.overlap(ship)) throw new IllegalArgumentException();
		double[] deltaV = {ship.getVelocityX() - getVelocityX(), ship.getVelocityY() - getVelocityY()};
		double[] deltaR = {ship.getPosition().getPositionX() - getPosition().getPositionX(), 
						   ship.getPosition().getPositionY() - getPosition().getPositionY()};
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
	 * 
	 * @param 	bullet
	 * 			The bullet to be used.
	 * 
	 * @see implementation
	 * 		// TODO declarative documentation.
	 */
	public void resolveCollisionBullet(Bullet bullet) throws NullPointerException, IllegalArgumentException {
		if (bullet == null) throw new NullPointerException();
		if (!this.overlap(bullet)) throw new IllegalArgumentException();
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
		     * | 9. The next methods are Helper Methods.	|
		     * |--------------------------------------------| 
		     */
	

	
	/**
	 * Returns the type of this Ship Class in string format.
	 */
	@Basic @Override @Raw
	public String getType() {
		return "Ship";
	}
	
}
 