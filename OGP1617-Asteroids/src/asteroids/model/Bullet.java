package asteroids.model;

import asteroids.helper.Entity;
import be.kuleuven.cs.som.annotate.*;

/* Constants:
*	1.	constantMaxSpeed = the maximum speed this bullet can achieve.
*	2.	minRadius = the minimum radius for each bullet.
*	3.	density = the density for this bullet.
*	4. 	boundaryCollisionMax = the maximum amount of collision with a boundary for this bullet
*/

/*
 * Methods Index:
 * #1# Basic Methods
 * 		1. Methods that handle the Initialization and Termination of the Bullet
 * 		2. Methods that handle the Position of the Bullet
 * 		3. Methods that handle the Speed of the Bullet
 * 		4. Methods that handle the Radius of the Bullet
 * 		5. Methods that handle the Mass of the Bullet
 * #2# Association Methods
 * 		6. Methods that handle the association with Worlds
 * 		7. Methods that handle the association with Bullets
 * #3# Advanced Methods
 * 		8. Methods that handle Moving and Accelerating
 * 		9. Methods that handle Calculating Distance and Overlap
 * #4# Collision Detection Methods
 * 		10. Methods that handle Collision Detection
 * 		11. Methods that handle Resolving Collisions
 * #5# Other Methods
 *		12. Helper Methods
 */

/**
 * A class of bullets.
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
		
		this.minRadius = 1;
		if (! canHaveAsRadius(radius))
			throw new IllegalArgumentException();
		this.radius = radius;
		
		this.setDensity(7.8 * Math.pow(10, 12));
		this.setMass();
		
		this.setBoundaryCollisionMax(3);
	}
	
	
	/**
	 * Terminates this bullet. Breaks up any associations with entities and worlds.
	 * Prepares this object for the garbage collector.
	 * @see implementation
	 */
	@Override
	public void terminate() {
		if (isTerminated()) return;
		if (getWorld() != null) world.removeEntity(this);
		if (getShip() != null) this.setShip(null);
		this.isTerminated = true;
	}
	

	
	
			/*
		     * |----------------------------------------------------|
		     * | 5. The next methods handle the Mass of the bullet.	|
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
		     * | 6. The next methods handle the association with worlds.	|
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
		if ( (getShip() == null) && (getWorld() == null) && (isInWorld(world)) ) return true;
		return false;
	}
	
	
	
		/*
	     * |--------------------------------------------------------|
	     * | 7. The next methods handle the association with ships.	|
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
		return ( (getShip() == null) && (getWorld() == null) );
	}
	
	
	/**
	* Set a given ship as source for this bullet.
	*  
	* @param  	ship
	*         	The ship to set as ship for this bullet.
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
	
	public void resetSource(Ship ship) {
		if (ship == null) throw new NullPointerException();
		if ( (!this.overlap(ship)) || (this.getSource() != ship) ) throw new IllegalArgumentException();
		this.hasBeenFired = false;
		this.setSource(null);
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
	public void setShip(Ship ship) throws IllegalArgumentException, NullPointerException {
		if (ship == null) throw new NullPointerException();
		if (canHaveAsShip(ship)) this.ship = ship;
		else throw new IllegalArgumentException();
	}
	
	
	
		/*
	     * |--------------------------------------------|
	     * | #Header-4# Collision Detection Methods.	|
	     * |--------------------------------------------| 
	     */
	
		
			/*
			 * |----------------------------------------------------|
			 * | 12. The next methods handle resolving Collisions.	|
			 * |----------------------------------------------------| 
			 */
	
	
	
	/**
	 * Variable registering the maximum amount of boundary collisions allowed for this bullet.
	 */
	private double boundaryCollisionMax;
	/**
	 * Variable registering the amount of times this bullet has hit a boundary.
	 */
	private double boundaryCollisionCounter = 0;

	
	
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

	
	/**
	* Resolves the collision between this bullet and a given world.
	* @param 	world
	* 			The world to be used.
	* 
	* @see implementation
	*/
	public void resolveCollision(World world) {
		setBoundaryCollisionCounter(getBoundaryCollisionCounter() + 1);
		if (getBoundaryCollisionCounter() < getBoundaryCollisionMax()) {
			double[] position = getCollisionPosition(world);
			
			if (position[0] == this.world.getWidth() || position[0] == 0) setVelocity(getVelocityX(), -getVelocityY());
			else if (position[0] == this.world.getHeight() || position[1] == 0) setVelocity(-getVelocityX(), getVelocityY());
		}
		else this.terminate();
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
		else if (entity.getType() == "Bullet") resolveCollisionBullet((Bullet) entity);
	}
	
	
	/**
	* Resolves the collision between this bullet and a given ship.
	* @param 	ship
	* 			The ship to be collided with.
	* 
	* @see implementation
	*/
	public void resolveCollisionShip(Ship ship) {
		if (this.getSource() == ship) ship.reloadBullet(this);
		else {
			this.terminate();
			ship.terminate();
		}
	}
	
	/**
	* Resolves the collision between this ship and a given bullet.
	* @param 	bullet
	* 			The bullet to be used.
	* 
	* @see implementation
	*/
	public void resolveCollisionBullet(Bullet bullet) {
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
		     * | 11. The next methods are Helper Methods.	|
		     * |--------------------------------------------| 
		     */
	
	
	
	@Raw @Override
	public String getType() {
		return "Bullet";
	}
	
}
