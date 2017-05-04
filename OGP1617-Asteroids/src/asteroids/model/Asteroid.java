package asteroids.model;

import asteroids.helper.MinorPlanet;

public class Asteroid extends MinorPlanet {

	public Asteroid() {
		setDensity(2.65 * Math.pow(10, 12));
		setMass();
	}
	
	@Override
	public void resolveCollisionShip(Ship ship) {
		ship.terminate();
	}
	
}
