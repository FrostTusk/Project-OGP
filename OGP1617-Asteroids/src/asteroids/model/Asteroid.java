package asteroids.model;

import asteroids.helper.entity.EntityType;
import asteroids.helper.entity.MinorPlanet;

public class Asteroid extends MinorPlanet {

	public Asteroid() {
		setDensity(2.65 * Math.pow(10, 12));
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
