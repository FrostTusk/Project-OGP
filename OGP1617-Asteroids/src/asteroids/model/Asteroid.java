package asteroids.model;

import asteroids.helper.entity.EntityType;
import asteroids.helper.entity.MinorPlanet;

public class Asteroid extends MinorPlanet {

	public Asteroid(double positionX, double positionY, double velocityX, double velocityY, double radius)
			throws IllegalArgumentException {
		setDensity(2.65 * Math.pow(10, 12));
		
		try {	// Check if the position can be set.
			setPosition(positionX, positionY);
		}
		catch (IllegalArgumentException exc) {
			throw new IllegalArgumentException();
		}
		
		setPosition(positionX, positionY);
		setVelocity(velocityX, velocityY);
		setRadius(radius);
		setMass();
	}
	
	@Override
	public void resolveCollisionShip(Ship ship) {
		ship.terminate();
	}
	
	@Override
	public EntityType getType() {
		return EntityType.ASTEROID;
	}
	
}
