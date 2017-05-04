package asteroids.model;

import java.util.concurrent.ThreadLocalRandom;

import asteroids.helper.entity.Entity;
import asteroids.helper.entity.EntityType;
import asteroids.helper.entity.MinorPlanet;
import asteroids.helper.entity.Position;

public class Planetoid extends MinorPlanet {

	public Planetoid() {
		initialRadius = getRadius();
		distanceTravelled = 0;
		setDensity(0.917 * Math.pow(10, 12));
		setMass();
	}
	
	
	private double initialRadius;
	private double distanceTravelled;
	
	
	public double getInitialRadius() {
		return initialRadius;
	}
	
	public double getDistanceTravelled() {
		return distanceTravelled;
	}
	
	
	public void addDistance(double distance) {
		distanceTravelled = getDistanceTravelled() + distance;
	}
	

	@Override
	public void move(double time) {
		if (time < 0) throw new IllegalArgumentException();
		
		double newPositionX = helper.calculatePosition(this, time)[0];
		double newPositionY = helper.calculatePosition(this, time)[1];
		double oldPositionX = getPosition().getPositionX();
		double oldPositionY = getPosition().getPositionY();
		
		addDistance(Math.pow(Math.abs(newPositionX - oldPositionX), 2) 
				+ Math.pow(Math.abs(newPositionY - oldPositionY), 2));
		updateRadius();
		
		try {
			setPosition(newPositionX, newPositionY);
		}
		catch (IllegalArgumentException exc) {
			throw new IllegalArgumentException();
		}
	}
	
	
	private void updateRadius() {
		try {
			setRadius(getInitialRadius() - 0.000001 * getDistanceTravelled());
		}
		catch (IllegalArgumentException exc) {
			this.terminate();
		}
	}
	
	@Override
	public void resolveCollisionShip(Ship ship) {
		Position newPosition = getRandomPosition(ship.getWorld());
		ship.setPosition(newPosition.getPositionX(), newPosition.getPositionY());
		for (Entity entity : getWorld().getAllEntities()) 
			if (ship.overlap(entity)) {
				ship.terminate();
				break;
			}
	}
	
	
	private Position getRandomPosition(World world) {
		return new Position(getRandomNumber(0, world.getWidth()), getRandomNumber(0, world.getHeight()));
	}
	
	
	/**
	 * Returns a single random number between 2 given values.
	 *
	 * @param 	low
	 *			The lowest random double value. (Inclusive)
	 * @param	high
	 *			The highest random double value. (Exclusive)
	 */
	public double getRandomNumber(double low, double high) {
	 	return ThreadLocalRandom.current().nextDouble(low, high);
	}
	
	@Override
	public EntityType getType() {
		return EntityType.PLANETOID;
	}
	
}
