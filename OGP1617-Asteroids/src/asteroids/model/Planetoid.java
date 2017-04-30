package asteroids.model;

import asteroids.helper.MinorPlanet;

public class Planetoid extends MinorPlanet {

	public Planetoid() {
		initialRadius = getRadius();
		distanceTravelled = 0;
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
		this.radius = getInitialRadius() - 0.000001*getDistanceTravelled();
	}
	
}
