package asteroids.model;

import asteroids.helper.entity.*;
import be.kuleuven.cs.som.annotate.*;

/* Constants:
*	1. 	boundaryCollisionMax = the maximum amount of collision with a boundary for this bullet
*/

/*
 * Methods Index:
 * #1# Basic Methods
 * 		1. Methods that handle the Initialization and Termination of the Bullet
 * 		2. Methods that handle the Mass of the Bullet
 * #2# Association Methods
 * 		3. Methods that handle the association with Worlds
 * 		4. Methods that handle the association with Ships
 * #3# Advanced Methods
 * 		5. Methods that handle Moving
 * #4# Collision Detection Methods
 * 		6. Methods that handle Resolving Collisions
 * #5# Other Methods
 *		7. Helper Methods
 */

/**
 * A class of bullets.
 * 
 * @invar	| this.getPosition().isValidPosition(this.position.getPositionX(), this.position.getPositionY())
 * @invar   | this.isValidSpeed(getVelocityX(), getVelocityY())
 * @invar   | this.canHaveAsRadius(this.getRadius())
 * @invar   | this.canHaveAsMass(this.getMass())
 * @invar	| this.canHaveAsWorld(this.getWorld())
 * @invar	| this.canHaveAsShip(this.getShip())
 *       
 * @author	Mathijs Hubrechtsen, Ruben Dhuyvetter
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
	 * 
	 * @see implementation
	 */
	public Bullet(double positionX, double positionY, double velocityX, double velocityY, double radius)
		throws IllegalArgumentException {
		setMinRadius(1);
		setDensity(7.8 * Math.pow(10, 12));
		setBoundaryCollisionMax(3);
		setBoundaryCollisionCounter(0);
		
		try {
			setPosition(positionX, positionY);
		}
		catch (IllegalArgumentException exc) {
			throw new IllegalArgumentException();
		}

		setVelocity(velocityX, velocityY);
		setRadius(radius);
		setMass();
	}
	
	
	/**
	 * Terminates this bullet. Breaks up any associations with entities and worlds.
	 * Prepares this object for the garbage collector.
	 * @see implementation
	 */
	@Override @Raw
	public void terminate() {
		if (isTerminated()) return;
		if (getWorld() != null) { 
			try {
				getWorld().removeEntity(this);
			}
			catch (IllegalArgumentException exc) {}	// Empty catch because if an IllegalArgument Exception
		}								// is thrown, it means that the association wasn't set to begin with.
		if (getShip() != null) {		// This means that the association already doesn't exist. We don't have to do anything.
			try {
				getShip().removeBullet(this);
				setShip(null);
			}
			catch (IllegalArgumentException exc) {}	// See former explanation.
		}
		this.isTerminated = true;
	}
	

		
			/*
		     * |----------------------------------------------------|
		     * | 2. The next methods handle the Mass of the bullet.	|
		     * |----------------------------------------------------| 
		     */
	
	
	
	/**
	 * Set the mass of this bullet to the given mass.
	 * 
	 * @param  	mass
	 * 			the new mass for this bullet
	 *         
	 * @see implementation
	 */
	@Raw
	public void setMass() {
		this.mass = ((double)4/(double)3) * Math.PI * Math.pow(getRadius(), 3) * getDensity();
	}
	

	
		/*
	     * |------------------------------------|
	     * | #Header-2# Association Methods.	|
	     * |------------------------------------| 
	     */
	
	
			/*
		     * |------------------------------------------------------------|
		     * | 3. The next methods handle the association with worlds.	|
		     * |------------------------------------------------------------| 
		     */
	
	
		
	/**
	 * Check whether this bullet can have the given world as world. 
	 *  
	 * @param  	world
	 *         	The world to check.
	 *         
	 * @see implementation
	 */
	@Override @Raw
	public boolean canHaveAsWorld(World world) {
		if (world == null) 
			return true;
		return (!world.isTerminated()) && (getShip() == null) 
			   && ( (getWorld() == null) || (getWorld() == world) ) && (isInWorld(world));
	}
	
	
	
			/*
		     * |--------------------------------------------------------|
		     * | 4. The next methods handle the association with ships.	|
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
		if (ship == null) 
			return true;
		return (!ship.isTerminated()) && (getWorld() == null) && ((getShip() == null) || (getShip() == ship)) 
				/*&& (helper.apparentlyWithinBoundaries(this, ship))*/;
	}
	
	
	/**
	 * Set a given ship as source for this bullet.
	 *  
	 * @param  	ship
	 *         	The ship to set as source ship for this bullet.
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
	 * Reset a given ship as source for this bullet. This action has
	 * as consequence that the source of this ship will be reset to null.
	 *  
	 * @param  	ship
	 *         	The ship to reset as source ship for this bullet.
	 *         
	 * @see implementation
	 */
	@Raw
	public void resetSource(Ship ship) throws IllegalArgumentException, NullPointerException {
		if (ship == null) 
			throw new NullPointerException();
		if ( (getDistanceBetween(ship) > 0) || (getSource() != ship) ) 
			throw new IllegalArgumentException();	// Since this 
		this.hasBeenFired = false;			// method may only be called during reload, these checks are necessary.
		this.source = null;
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
	public void setShip(Ship ship) throws IllegalArgumentException {
		if (!canHaveAsShip(ship)) 
			throw new IllegalArgumentException();
		this.ship = ship;
	}
	
	
	
		/*
	     * |--------------------------------------------|
	     * | #Header-4# Collision Detection Methods.	|
	     * |--------------------------------------------| 
	     */
	
		
			/*
			 * |------------------------------------------------------------|
			 * | 5. The next methods handle the Boundary Collision Counter.	|
			 * |------------------------------------------------------------| 
			 */
	

	
	/**
	 * Variable registering the maximum amount of boundary collisions allowed for this bullet.
	 */
	private double boundaryCollisionMax;	// #Constant-1#
	/**
	 * Variable registering the amount of times this bullet has hit a boundary.
	 */
	private double boundaryCollisionCounter;

		
	/**
	 * Return the maximum amount of times this bullet can collide with a boundary.
	 */
	@Basic @Raw
	public double getBoundaryCollisionMax() {
		return this.boundaryCollisionMax;
	}
	
	/**
	 * Return the amount of times this bullet has collided with a boundary.
	 */
	@Basic @Raw
	public double getBoundaryCollisionCounter() {
		return this.boundaryCollisionCounter;
	}
	
	
	/**
	 * Set a given maximum as maximum amount collisions for this bullet.
	 *  
	 * @param  	maximum
	 *         	The amount to which the maximum needs to be set
	 *         
	 * @see implementation
	 */
	@Raw
	private void setBoundaryCollisionMax(double maximum) {
		this.boundaryCollisionMax = maximum;
	}
	
	/**
	 * Set the boundary collision counter to a certain amount.
	 *  
	 * @param  	amount
	 *         	The amount to which the boundary collision counter needs to be set
	 *         
	 * @see implementation
	 */
	@Raw
	private void setBoundaryCollisionCounter(double amount) {
		this.boundaryCollisionCounter = amount;
	}

	
	
			/*
			 * |----------------------------------------------------|
			 * | 6. The next methods handle resolving Collisions.	|
			 * |----------------------------------------------------| 
			 */
	

	
	/**
	 * Resolves the collision between this bullet and a given world.
	 * 
	 * @param 	world
	 * 			The world to be used.
	 * 
	 * @see implementation
	 */
	@Override
	public void resolveCollision(World world) throws NullPointerException {
		if (world == null) 
			throw new NullPointerException();
		setBoundaryCollisionCounter(getBoundaryCollisionCounter() + 1);
		if (getBoundaryCollisionCounter() < getBoundaryCollisionMax()) {
			double[] position = getCollisionPosition(world);
			if (position[0] == Double.POSITIVE_INFINITY && position[1] == Double.POSITIVE_INFINITY) return;	
			// There is no collision so the collision does not need to be resolved.
			if (position[0] == getWorld().getWidth() || position[0] == 0) // Check collision with the x boundary.
				setVelocity(-getVelocityX(), getVelocityY());
			if ( (position[1] == getWorld().getHeight() || position[1] == 0) ||	// Check collision with y boundary
			     (position[3] == getWorld().getHeight() || position[3] == 0) ) 	// or with corner.
				setVelocity(getVelocityX(), -getVelocityY());
		}
		else this.terminate();	// Terminate the bullet if it has exceeded the max amount of bounces.
	}
	
	
	/**
	 * Resolves the collision between this bullet and a given ship.
	 * 
	 * @param 	ship
	 * 			The ship to be collided with.
	 * 
	 * @see implementation
	 */
	@Override
	public void resolveCollisionShip(Ship ship) throws IllegalArgumentException, NullPointerException {
		if (ship == null) 
			throw new NullPointerException();
		if (this.getSource() == ship) {	// Reload if this bullet hits it's source.
			try {
				ship.reloadBullet(this);
			}
			catch (IllegalArgumentException exc) {
				throw new IllegalArgumentException(exc);
			}
		}
		else {	// Terminate otherwise.
			this.terminate();
			ship.terminate();
		}
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
	public void resolveCollisionBullet(Bullet bullet) throws NullPointerException {
		if (bullet == null) 
			throw new NullPointerException();
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
		     * | 7. The next methods are Helper Methods.	|
		     * |--------------------------------------------| 
		     */
	
	
	
	/**
	 * Returns the type of this Bullet Class in enumeration format.
	 */
	@Basic @Override @Raw
	public EntityType getType() {
		return EntityType.BULLET;
	}
	
}
