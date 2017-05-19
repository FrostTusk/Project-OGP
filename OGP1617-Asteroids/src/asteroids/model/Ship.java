package asteroids.model;

import java.util.*;
import asteroids.helper.entity.*;
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
 * @invar	Each ship can have all its bullets as bullet.
 * 			| for each bullet in bullets: canHaveAsBullet(bullet)
 *       
 * @author	Mathijs Hubrechtsen, Ruben Dhuyvetter
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
	 *       	| ! new Position(0,0).isValidPosition(positionX, positionY)
	 * @throws 	IllegalArgumentException
	 *         	This new bullet cannot have the given radius as its radius.
	 *       	| ! this.canHaveAsRadius(this.getRadius())
	 */
	public Ship(double positionX, double positionY, double velocityX, double velocityY, double orientation, double radius, double mass)
		throws IllegalArgumentException {
		setMinRadius(10);	// First the constants are set.
		setDensity(1.42 * Math.pow(10, 12));
		setForce(1.1 * Math.pow(10, 18));
		
		try {	// Check if the position can be set.
			setPosition(positionX, positionY);
		}
		catch (IllegalArgumentException exc) {
			throw new IllegalArgumentException();
		}

		setVelocity(velocityX, velocityY);	// Set the velocity.
		assert isValidOrientation(orientation);	// Check if the orientation can be set.
		setOrientation(orientation);
		setRadius(radius);	// Set the radius.
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
		}											// is thrown, it means that the association wasn't set to begin with.	
		List<Bullet> iterator = helper.convertSetToList(getAllBullets());	// This means that the association already doesn't exist 
		for (Bullet bullet: iterator) {							// we don't have to do anything.
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
		return (orientation <= 2*Math.PI) && (orientation >= 0);
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
		for (Bullet bullet : this.bullets) 
			totalMass += bullet.getMass();
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
		if (Double.isNaN(mass)) 
			return false;
		return mass > ((double)4/(double)3) * Math.PI * Math.pow(getRadius(), 3) * getDensity();
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
		if (isValidMass(mass)) 
			this.mass = mass;
		else 
			this.mass = ((double)4/(double)3) * Math.PI * Math.pow(getRadius(), 3) * getDensity();
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
	

	
//	/** // TODO Is this unnecessary
//	* Check whether this ship can have the given world as world. 
//	*  
//	* @param  	world
//	*         	The world to check.
//	*         
//	* @return	Returns whether or not this ship can have the given world as world.
//	* 			true if it can, false if not.
//	*/
//	@Override @Raw
//	public boolean canHaveAsWorld(World world) {
//		if (world == null) return true;
//		return ( (getWorld() == null) || (getWorld() == world) ) && (isInWorld(world)) && !(world.isTerminated());
//	}
	

	
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
	 * @return	Returns whether or not this ship can have the given bullet as bullet.
	 * 			true if it can, false if not.
	 */
	@Raw
	public boolean canHaveAsBullet(Bullet bullet) {
		return (bullet != null) && (!bullet.isTerminated()) && 
			   (!this.bullets.contains(bullet)) && (helper.apparentlyWithinBoundaries(bullet, this));
	}
	
	
	/**
	 * Add a given bullet to this ship's collection of bullets. 
	 * This method works immediately with the collection.
	 * It does not load a bullet!
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
	@Raw
	public void removeBullet(Bullet bullet) throws IllegalArgumentException {
		if (!this.bullets.contains(bullet)) 
			throw new IllegalArgumentException();
		this.bullets.remove(bullet);
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
	public void loadBullet(Bullet bullet) throws IllegalArgumentException, NullPointerException {
		if (bullet == null)	
			throw new NullPointerException();
		if ( (!bullet.canHaveAsShip(this)) || (!this.canHaveAsBullet(bullet)) ) 
			throw new IllegalArgumentException();
		
		addBullet(bullet);
		try {
			bullet.setShip(this);
		}
		catch (IllegalArgumentException exc) {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * Load a given bullet collection onto this ship. Loading a bullet will add
	 * the given bullet to the ship's collection and set it's ship as this ship.
	 *  
	 * @param  	bullet
	 *         	The bullet to be loaded.
	 *         
	 * @see implementation
	 */
	public void loadBullets(Collection<Bullet> bullets) throws IllegalArgumentException, NullPointerException {
		try {
			for (Bullet bullet: bullets) 
				loadBullet(bullet);
		}
		catch (IllegalArgumentException exc) {
			throw new IllegalArgumentException();
		}
		catch (NullPointerException exc) {
			throw new NullPointerException();
		}
	}
	
	/**
	 * Reload a given bullet into this ship.
	 *  
	 * @param  	bullet
	 *         	The bullet to be reloaded.
	 *         
	 * @see implementation
	 */
	public void reloadBullet(Bullet bullet) throws IllegalArgumentException, NullPointerException {
		try {
			if (bullet.getWorld() != null) 
				bullet.getWorld().removeEntity(bullet);
			bullet.setPosition(getPosition().getPositionX(), getPosition().getPositionY());
			bullet.resetSource(this);
			bullet.setWorld(null);
			loadBullet(bullet);
		}
		catch (IllegalArgumentException exc) {
			throw new IllegalArgumentException(exc);
		}
		catch (NullPointerException exc) {
			throw new NullPointerException();
		}
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
	 * @param	bullet
	 * 			The bullet requested to be fired.
	 * 
	 * @see implementation
	 */
	public void fireBullet(Bullet bullet) {
		if ( (this.getWorld() == null) || (bullet == null) ) return;
		if (!bullets.contains(bullet)) 
			fireBullet();
		else 
			fireBulletProcedure(bullet);
	}

	/**
	 * The procedure a fired bullet has to run through to actually be fired.
	 * Helper Method for fireBullet().
	 *     
	 * @param	bullet
	 * 			The bullet requested to be fired.
	 * 
	 * @see implementation
	 */
	private void fireBulletProcedure(Bullet bullet) {
		bullet.setSource(this);	// First set the source to this ship.
		removeBullet(bullet);	// Remove the association with the ship.
		bullet.setShip(null);
		bullet.setVelocity(250 * Math.cos(getOrientation()), 250 * Math.sin(getOrientation()));	// Next the bullet is given 
		bullet.setPosition( getPosition().getPositionX() + Math.cos(getOrientation()) *			// the current position/speed.
								(getRadius() + bullet.getRadius() + 1),
						    getPosition().getPositionY() +  Math.sin(getOrientation()) * 
						    	(getRadius() + bullet.getRadius() + 1) );	// +1 otherwise bullet is reloaded immediately.

		// Next all the collisions with entities are resolved.
		for (Entity entity : getWorld().getAllEntities()) 
			//if (bullet.getDistanceBetween(entity) <= 0)
			if (bullet.overlap(entity))
				bullet.resolveCollision(entity);
			
		try {
			getWorld().addEntity(bullet);	// Add the bullet to the collection of the world.
			bullet.setWorld(this.getWorld());	// set the world of the bullet to the world of this ship.
		}
		catch (IllegalArgumentException exc) {	// If the bullet cannot exist in this world, an exception is thrown.
			bullet.terminate();					// And the bullet needs to be terminated
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
	@Raw
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
	@Raw
	public void thrust(double acceleration) {
		if (acceleration < 0) 
			acceleration = 0;
		double newVelocityX = getVelocityX() + acceleration * Math.cos(getOrientation());
		double newVelocityY = getVelocityY() + acceleration * Math.sin(getOrientation());
		setVelocity(newVelocityX, newVelocityY);
	}
	
	/**
	 * Thrusts a ship forwards (in the direction it's facing) with a given acceleration
	 * in a given time interval.
	 * 
	 * @param  	acceleration
	 *         	The acceleration with which the ship will thrust forwards.
	 * @param	time
	 * 			The time during which the ship will thrust forwards.
	 *         
	 * @post   	If the given acceleration is lower than 0, the velocity will change
	 * 			with an acceleration of 0.
	 *       	| if (acceleration < 0)
	 *       	|	then (new.getVelocityX() == this.getVelocityX()) &&
	 *       	|   	 (new.getVelocityY() == this.getVelocityY())
	 *       
	 * @effect	The X and Y velocity of this new ship is set to
	 *         	the the current velocity plus the given acceleration in the current direction.
	 * 			| this.setVelocity(this.getVelocityX() + acceleration * Math.cos(this.getOrientation()) * time,
	 * 			|				   this.getVelocityY() + acceleration * Math.sin(this.getOrientation()) * time)
	 *       
	 */
	@Raw
	public void thrust(double acceleration, double time) {
		if (acceleration < 0) 
			acceleration = 0;
		if (time < 0) 
			time = 0;
		double newVelocityX = getVelocityX() + acceleration * Math.cos(getOrientation()) * time;
		double newVelocityY = getVelocityY() + acceleration * Math.sin(getOrientation()) * time;
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
	 * true => On, false => Off
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
		if (!getThrustStatus()) 
			return 0;
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
//		thrust(getAcceleration());
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


	
	
//	/** // TODO Is this ok?
//	 * Resolves the collision between this ship and a given world.
//	 * 
//	 * @param 	world
//	 * 			The world to be used.
//	 * 
//	 * @see implementation
//	 * // TODO Problems with rounding? 
//	 */
//	@Override
//	public void resolveCollision(World world) throws NullPointerException {
//		if (world == null) throw new NullPointerException();
//		double[] position = getCollisionPosition(world);
//		if (position[0] == Double.POSITIVE_INFINITY && position[1] == Double.POSITIVE_INFINITY) return;	
//		// There is no collision so the collision does not need to be resolved.
//		if (position[0] == this.world.getWidth() || position[0] == 0) 
//			setVelocity(-getVelocityX(), getVelocityY());
//		if ( (position[1] == this.world.getHeight() || position[1] == 0) ||
//			 (position[3] == this.world.getHeight() || position[3] == 0) )
//			setVelocity(getVelocityX(), -getVelocityY()); 
//	}
	
//	/** //TODO is this ok?
//	 * Resolves the collision between this ship and a given entity.
//	 * 
//	 * @param 	entity
//	 * 			The entity to be used.
//	 * 
//	 * @see implementation
//	 */
//	@Override
//	public void resolveCollision(Entity entity) throws IllegalArgumentException, NullPointerException {
//		if (entity == null) throw new NullPointerException();
//		try {
//			if (entity.getType() == EntityType.SHIP) resolveCollisionShip((Ship)entity);
//			else if (entity.getType() == EntityType.BULLET) resolveCollisionBullet((Bullet)entity);
//		}
//		catch (IllegalArgumentException exc) {
//			throw new IllegalArgumentException(exc);
//		}
//	}


	/**
	 * Resolves the collision between this ship and a given ship.
	 * 
	 * @param 	ship
	 * 			The ship to be used.
	 * 
	 * @see implementation
	 */
	@Override
	public void resolveCollisionShip(Ship ship) throws NullPointerException {
		if (ship == null) 
			throw new NullPointerException();	// This method is pretty much a copy of the formula in the assignment.
		double[] deltaV = {ship.getVelocityX() - getVelocityX(), ship.getVelocityY() - getVelocityY()};
		double[] deltaR = {ship.getPosition().getPositionX() - getPosition().getPositionX(), 
						   ship.getPosition().getPositionY() - getPosition().getPositionY()};
		
		double J = ( 2 * getMass() * ship.getMass() * helper.evaluateScalar(deltaV, deltaR) 
				/ ( (getRadius() + ship.getRadius()) * (getMass() + ship.getMass()) ) );
		
		double Jx = J * (ship.getPosition().getPositionX() - getPosition().getPositionX()) / (getRadius() + ship.getRadius());
		double Jy = J * (ship.getPosition().getPositionY() - getPosition().getPositionY()) / (getRadius() + ship.getRadius());
		
		this.setVelocity(getVelocityX() + Jx/getMass(), getVelocityY() + Jy/getMass());
		ship.setVelocity(ship.getVelocityX() - Jx/ship.getMass(), ship.getVelocityY() - Jy/ship.getMass());
	}
	
	
	/**
	 * Resolves the collision between this ship and a given bullet.
	 * 
	 * @param 	bullet
	 * 			The bullet to be used.
	 * 
	 * @see implementation
	 */
	@Override
	public void resolveCollisionBullet(Bullet bullet) throws IllegalArgumentException, NullPointerException {
		if (bullet == null) 
			throw new NullPointerException();
		if (bullet.getSource() == this) {	// Reload if this bullet hits it's source.
			try {
				reloadBullet(bullet);
			}
			catch (IllegalArgumentException exc) {
				throw new IllegalArgumentException(exc);
			}
		}
		else {	// Terminate otherwise.
			this.terminate();
			bullet.terminate();
		}
	}
	

	
		/*
	     * |----------------------------|
	     * | #Header-5# Other Methods.	|
	     * |----------------------------| 
	     */
	
	
	
	private Program program;
	
	
	public Program getProgram() {
		return this.program;
	}
	
	
	public void setProgram(Program program) {
		this.program = program;
		program.setOwner(this);
	}
	
	public List<Object> executeProgram(double time) {
		try {
			program.execute(time);
		}
		catch (IllegalStateException exc) {
			; // TimeOut.
		}
		return program.getPrintTracker();
	}
	
	
	
			/*
		     * |--------------------------------------------|
		     * | 9. The next methods are Helper Methods.	|
		     * |--------------------------------------------| 
		     */
	

	
	/**
	 * Returns the type of this Ship Class in string format.
	 */
	@Basic @Override @Raw
	public EntityType getType() {
		return EntityType.SHIP;
	}
	
}
 