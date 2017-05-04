package asteroids.helper;

import asteroids.model.Ship;
import be.kuleuven.cs.som.annotate.Raw;

public abstract class MinorPlanet extends Entity {

	public MinorPlanet() {
		this.minRadius = 5;
	}
	
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
		this.mass = (4/3) * Math.PI * Math.pow(getRadius(), 3) * getDensity();
	}
	
	@Override
	public void resolveCollisionShip(Ship ship) {
		// TODO Auto-generated method stub
	}
	
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

	@Override
	public EntityType getType() {
		return EntityType.MINORPLANET;
	}

}
