package asteroids.model;

import asteroids.helper.Entity;

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
 * A class of bullets. //TODO Do we need to keep all these invariants? + Any more?
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
	 *                  	 
	 * @throws 	IllegalArgumentException
	 *         	This new bullet cannot have the given X and Y position as position.
	 *       	| ! this.getPosition().isValidPosition(positionX, positionY)	// TODO is this done right?
	 * @throws 	IllegalArgumentException
	 *         	This new bullet cannot have the given radius as its radius.
	 *       	| ! this.canHaveAsRadius(this.getRadius())
	 */
	public Bullet(double positionX, double positionY, double velocityX, double velocityY, double radius)
		throws IllegalArgumentException {
		this.minRadius = 1;
		setDensity(7.8 * Math.pow(10, 12));
		this.boundaryCollisionCounter = 0;
		setBoundaryCollisionMax(3);
		
		try {
			setPosition(positionX, positionY);
		}
		catch (IllegalArgumentException exc) {
			throw new IllegalArgumentException();
		}

		setVelocity(velocityX, velocityY);
		
		if (! canHaveAsRadius(radius))
			throw new IllegalArgumentException();
		this.radius = radius;
		
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
				this.setWorld(null);
			}
			catch (IllegalArgumentException exc) {}	// Empty catch because if an IllegalArgument Exception
		}											// is thrown, it means that the association wasn't set to start with.
		if (getShip() != null) {					// This means that the association already doesn't exist. We don't have to do anything.
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
		this.mass = (4/3) * Math.PI * Math.pow(getRadius(), 3) * (density);
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
		if (world == null) return true;	// TODO Is this a good fix?
		return (getShip() == null) && (getWorld() == null) && (isInWorld(world));
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
		if (ship == null) return true;	// TODO Is this a good fix?
		return ( (getShip() == null) && (getWorld() == null) );
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
	 * 		// TODO More documentation?
	 */
	@Raw
	public void resetSource(Ship ship) throws IllegalArgumentException, NullPointerException {
		if (ship == null) throw new NullPointerException();
		if ( (!(this.getDistanceBetween(ship) <= 0)) || (this.getSource() != ship) ) throw new IllegalArgumentException(); // TODO Is this right?
		this.hasBeenFired = false;
		this.setSource(null);
		this.hasBeenFired = false;
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
		if (! canHaveAsShip(ship)) throw new IllegalArgumentException();
		this.ship = ship;
	}
	
	
	
		/*
	     * |--------------------------------------------|
	     * | #Header-4# Collision Detection Methods.	|
	     * |--------------------------------------------| 
	     */
	
		
			/*
			 * |----------------------------------------------------------------|
			 * | 5. The next methods handle the Boundary Collision Counter.	|
			 * |----------------------------------------------------------------| 
			 */
	
	// TODO rewrite as a Class.
	
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
	
	// Are these Methods Raw?
	
	/**
	 * Resolves the collision between this bullet and a given world.
	 * 
	 * @param 	world
	 * 			The world to be used.
	 * 
	 * @see implementation
	 * 		// TODO declarative documentation.
	 * 		// TODO apparently collide?
	 * 		// TODO what if they are not colliding?
	 */
	@Override
	public void resolveCollision(World world) throws NullPointerException {
		if (world == null) throw new NullPointerException();
		setBoundaryCollisionCounter(getBoundaryCollisionCounter() + 1);
		if (getBoundaryCollisionCounter() < getBoundaryCollisionMax()) {
			double[] position = getCollisionPosition(world);
			if (position == null) return;	// There is no collision so the collision does not need to be resolved.
			if (position[0] == getWorld().getWidth() || position[0] == 0) 
				setVelocity(-getVelocityX(), getVelocityY());
			if (position[1] == getWorld().getHeight() || position[1] == 0) 
				setVelocity(getVelocityX(), -getVelocityY());
		}
		else this.terminate();
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
	@Override
	public void resolveCollision(Entity entity) throws IllegalArgumentException, NullPointerException {
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
	 * Resolves the collision between this bullet and a given ship.
	 * 
	 * @param 	ship
	 * 			The ship to be collided with.
	 * 
	 * @see implementation
	 * 		// TODO declarative documentation.
	 */
	@Override
	public void resolveCollisionShip(Ship ship) throws IllegalArgumentException, NullPointerException {
		if (ship == null) throw new NullPointerException();
//		if (!this.overlap(ship)) throw new IllegalArgumentException();
		if (this.getSource() == ship) {
			try {
				ship.reloadBullet(this);
			}
			catch (IllegalArgumentException exc) {
				throw new IllegalArgumentException();
			}
		}
		else {
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
	 * 		// TODO declarative documentation.
	 */
	@Override
	public void resolveCollisionBullet(Bullet bullet) throws NullPointerException {
		if (bullet == null) throw new NullPointerException();
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
	 * Returns the type of this Ship Class in string format.
	 */
	@Basic @Override @Raw
	public String getType() {
		return "Bullet";
	}
	
}
