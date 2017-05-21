package asteroids.model;

import java.util.concurrent.ThreadLocalRandom;

import asteroids.helper.entity.Entity;
import asteroids.helper.entity.EntityType;
import asteroids.helper.entity.MinorPlanet;
import asteroids.helper.entity.Position;

public class Planetoid extends MinorPlanet {

	public Planetoid(double positionX, double positionY, double velocityX, double velocityY, double radius, double distanceTravelled)
			throws IllegalArgumentException {
		setDensity(0.917 * Math.pow(10, 12));
		
		try {	// Check if the position can be set.
			setPosition(positionX, positionY);
		}
		catch (IllegalArgumentException exc) {
			throw new IllegalArgumentException();
		}
		
		setVelocity(velocityX, velocityY);
		setRadius(radius);
		initialRadius = getRadius();
		setDistanceTravelled(distanceTravelled);
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
	
	
	public void setDistanceTravelled(double distanceTravelled) {
		this.distanceTravelled = distanceTravelled;
		updateRadius();
	}
	

	@Override
	public void move(double time) {
		if (time < 0) throw new IllegalArgumentException();
		
		double newPositionX = helper.calculatePosition(this, time)[0];
		double newPositionY = helper.calculatePosition(this, time)[1];
		double oldPositionX = getPosition().getPositionX();
		double oldPositionY = getPosition().getPositionY();
		
		setDistanceTravelled(getDistanceTravelled() + Math.sqrt(Math.pow(Math.abs(newPositionX - oldPositionX), 2) 
							 + Math.pow(Math.abs(newPositionY - oldPositionY), 2)));
		
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
		for (Entity entity: getWorld().getAllEntities()) {
			if (entity == ship)
				continue;
			if (ship.overlap(entity)) {
				ship.terminate();
//				System.out.println(ship);
//				System.out.println(entity);
				break;
			}
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
