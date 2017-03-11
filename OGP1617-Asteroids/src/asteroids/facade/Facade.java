package asteroids.facade;

import asteroids.part1.facade.IFacade;
import asteroids.util.ModelException;
import asteroids.model.Ship;

public class Facade implements IFacade {

	@Override
	public Ship createShip() throws ModelException {
		// ship parameters = positionX, positionY, velocityX, velocityY, orientation, radius.
		return new Ship(0, 0, 0, 0, 0, 10);
	}

	@Override
	public Ship createShip(double x, double y, double xVelocity, double yVelocity, double radius, double orientation)
			throws ModelException {
		try {
			return new Ship(x, y, xVelocity, yVelocity, orientation, radius);
		}
		catch (IllegalArgumentException exc) {
			throw new ModelException(exc);
		}
		catch (IndexOutOfBoundsException exc) {
			throw new ModelException(exc);
		}
		catch (AssertionError exc) {
			throw new ModelException(exc);
		}
	}

	@Override
	public double[] getShipPosition(Ship ship) throws ModelException {
		try {
			double positionX = ship.getPositionX();
			double positionY = ship.getPositionY();
			double[] position = {positionX, positionY};
			return position;
		}
		catch (NullPointerException exc) {
			throw new ModelException(exc);
		}
	}

	@Override
	public double[] getShipVelocity(Ship ship) throws ModelException {
		try {
		double velocityX = ship.getVelocityX();
		double velocityY = ship.getVelocityY();
		double[] velocity = {velocityX, velocityY};
		return velocity;
		}
		catch (NullPointerException exc) {
			throw new ModelException(exc);
		}
	}

	@Override
	public double getShipRadius(Ship ship) throws ModelException {
		try {
			return ship.getRadius();
		}
		catch (NullPointerException exc) {
			throw new ModelException(exc);
		}
	}

	@Override
	public double getShipOrientation(Ship ship) throws ModelException {
		try {
			return ship.getOrientation();
		}
		catch (NullPointerException exc) {
			throw new ModelException(exc);
		}
	}

	@Override
	public void move(Ship ship, double dt) throws ModelException {
		try {
			ship.move(dt);
		}
		catch (IllegalArgumentException exc) {
			throw new ModelException(exc);
		}
		catch (IndexOutOfBoundsException exc) {
			throw new ModelException(exc);
		}
		catch (NullPointerException exc) {
			throw new ModelException(exc);
		}
	}

	@Override
	public void thrust(Ship ship, double amount) throws ModelException {
		try {
			ship.thrust(amount);
		}
		catch (NullPointerException exc) {
			throw new ModelException(exc);
		}
	}

	@Override
	public void turn(Ship ship, double angle) throws ModelException {
		try {
			ship.turn(angle);
		}
		catch (AssertionError exc) {
			throw new ModelException(exc);
		}
		catch (NullPointerException exc) {
			throw new ModelException(exc);
		}
			
	}

	@Override
	public double getDistanceBetween(Ship ship1, Ship ship2) throws ModelException {
		try {
			return ship1.getDistanceBetween(ship2);
		}
		catch (IllegalArgumentException exc) {
			throw new ModelException(exc);
		}
		catch (NullPointerException exc) {
			throw new ModelException(exc);
		}
	}

	@Override
	public boolean overlap(Ship ship1, Ship ship2) throws ModelException {
		try {
			return ship1.overlap(ship2);
		}
		catch (IllegalArgumentException exc) {
			throw new ModelException(exc);
		}
		catch (NullPointerException exc) {
			throw new ModelException(exc);
		}
	}

	@Override
	public double getTimeToCollision(Ship ship1, Ship ship2) throws ModelException {
		try {
			return ship1.getTimeToCollision(ship2);
		}
		catch (IllegalArgumentException exc) {
			throw new ModelException(exc);
		}
		catch (NullPointerException exc) {
			throw new ModelException(exc);
		}
	}

	@Override
	public double[] getCollisionPosition(Ship ship1, Ship ship2) throws ModelException {
		try {
			return ship1.getCollisionPosition(ship2);
		}
		catch (IllegalArgumentException exc) {
			throw new ModelException(exc);
		}
		catch (NullPointerException exc) {
			throw new ModelException(exc);
		}
	}

}
