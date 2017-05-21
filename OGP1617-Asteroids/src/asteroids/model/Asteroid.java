package asteroids.model;

import asteroids.helper.entity.EntityType;
import asteroids.helper.entity.MinorPlanet;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * A class of asteroids.
 * 
 * @invar	| this.getPosition().isValidPosition(this.position.getPositionX(), this.position.getPositionY())
 * @invar   | this.isValidSpeed(getVelocityX(), getVelocityY())
 * @invar   | this.canHaveAsRadius(this.getRadius())
 * @invar   | this.canHaveAsMass(this.getMass())
 * @invar	| this.canHaveAsWorld(this.getWorld())
 * @invar	| this.canHaveAsShip(this.getShip())
 *       
 * @author	Mathijs Hubrechtsen
 */
public class Asteroid extends MinorPlanet {

	/**
	 * Initialize this new asteroid with given position, velocities and radius. 
	 *
	 * @param  	positionX
	 *         	The X position for this new asteroid.
	 * @param  	positionY
	 * 			The Y position of this new asteroid.
	 * @param  	velocityX
	 *         	The X velocity for this new asteroid.
	 * @param	velocityY
	 * 			The Y velocity for this new asteroid.
	 * @param  	radius
	 *         	The radius for this new asteroid.
	 *         
	 * @see implementation
	 */
	public Asteroid(double positionX, double positionY, double velocityX, double velocityY, double radius)
			throws IllegalArgumentException {
		setDensity(2.65 * Math.pow(10, 12));
		
		try {	// Check if the position can be set.
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
	 * Resolves the collision between this entity and a given ship.
	 * 
	 * @param 	ship
	 * 			The ship to be used.
	 * 
	 * @see implementation
	 */
	@Override
	public void resolveCollisionShip(Ship ship) {
		ship.terminate();
	}
	
	
	
	/**
	 * Returns the type of the class of this Asteroid in enumeration format.
	 * 
	 * @see implementation
	 */
	@Raw @Override
	public EntityType getType() {
		return EntityType.ASTEROID;
	}
	
}
