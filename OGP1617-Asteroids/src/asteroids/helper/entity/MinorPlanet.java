package asteroids.helper.entity;

import asteroids.model.Ship;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * A class of minor planets.
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
public abstract class MinorPlanet extends Entity {

	/**
	 * Default constructor for minor planet, should never be used.
	 * @see implementation
	 */
	public MinorPlanet() {
		setMinRadius(5);
	}
	
	
	/**
	 * Terminates this minorPlanet. Breaks up any associations with entities and worlds.
	 * Prepares this object for the garbage collector.
	 * @see implementation
	 */
	@Override
	public void terminate() {
		if (isTerminated()) return;
		if (getWorld() != null) { 
			try {
				getWorld().removeEntity(this);
			}
			catch (IllegalArgumentException exc) {}	// Empty catch because if an IllegalArgument Exception
		}								// is thrown, it means that the association wasn't set to begin with.
		// This means that the association already doesn't exist. We don't have to do anything.
		this.isTerminated = true;
	}


	
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
	
	
	/**
	 * Resolves the collision between this entity and a given ship.
	 * 
	 * @param 	ship
	 * 			The ship to be used.
	 * 
	 * @see implementation
	 */
	@Override
	public abstract void resolveCollisionShip(Ship ship);
	
	/**
	 * Resolves the collision between this entity and a given minor planet.
	 * 
	 * @param 	minorPlanet
	 * 			The minorPlanet to be used.
	 * 
	 * @see implementation
	 */
	@Override
	public void resolveCollisionMinorPlanet(MinorPlanet minorPlanet) {
		if (minorPlanet == null) 
			throw new NullPointerException();	// This method is pretty much a copy of the formula in the assignment.
		double[] deltaV = {minorPlanet.getVelocityX() - getVelocityX(), minorPlanet.getVelocityY() - getVelocityY()};
		double[] deltaR = {minorPlanet.getPosition().getPositionX() - getPosition().getPositionX(), 
						   minorPlanet.getPosition().getPositionY() - getPosition().getPositionY()};
		
		double J = ( 2 * getMass() * minorPlanet.getMass() * helper.evaluateScalar(deltaV, deltaR) 
				/ ( (getRadius() + minorPlanet.getRadius()) * (getMass() + minorPlanet.getMass()) ) );
		
		double Jx = J * (minorPlanet.getPosition().getPositionX() - getPosition().getPositionX()) / (getRadius() + minorPlanet.getRadius());
		double Jy = J * (minorPlanet.getPosition().getPositionY() - getPosition().getPositionY()) / (getRadius() + minorPlanet.getRadius());
		
		this.setVelocity(getVelocityX() + Jx/getMass(), getVelocityY() + Jy/getMass());
		minorPlanet.setVelocity(minorPlanet.getVelocityX() - Jx/minorPlanet.getMass(), minorPlanet.getVelocityY() - Jy/minorPlanet.getMass());
	}

	
	
	/**
	 * Returns the type of the class of this MinorPlanet Class in enumeration format.
	 * 
	 * @see implementation
	 */
	@Override
	public EntityType getType() {
		return EntityType.MINORPLANET;
	}

}
