package asteroids.model;

import java.util.concurrent.ThreadLocalRandom;

import asteroids.helper.entity.Entity;
import asteroids.helper.entity.EntityType;
import asteroids.helper.entity.MinorPlanet;
import asteroids.helper.entity.Position;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;
import jdk.internal.dynalink.linker.LinkerServices.Implementation;

/**
 * A class of planetoids.
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
public class Planetoid extends MinorPlanet {

	/**
	 * Initialize this new asteroid with given position, velocities and radius. 
	 *
	 * @param  	positionX
	 *         	The X position for this new planetoid.
	 * @param  	positionY
	 * 			The Y position of this new planetoid.
	 * @param  	velocityX
	 *         	The X velocity for this new planetoid.
	 * @param	velocityY
	 * 			The Y velocity for this new planetoid.
	 * @param  	radius
	 *         	The radius for this new planetoid.
	 * @param	distanceTraveled
	 * 			The distance this new planetoid has traveled.
	 *         
	 * @see implementation
	 */
	public Planetoid(double positionX, double positionY, double velocityX, double velocityY, double radius, double distanceTraveled)
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
		setDistanceTraveled(distanceTraveled);
		setMass();
	}
	
	
	/**
	 * Terminates this minorPlanet. Breaks up any associations with entities and worlds.
	 * Prepares this object for the garbage collector.
	 * @see implementation
	 */
	@Override
	public void terminate() {
		if (isTerminated()) return;
		World world = getWorld();
		if (getWorld() != null) { 
			try {
				getWorld().removeEntity(this);
			}
			catch (IllegalArgumentException exc) {setWorld(null);}	// Near empty catch because if an IllegalArgument Exception
		}				 						// is thrown, it means that the association wasn't set to begin with.						
		// This means that the association already doesn't exist. We don't have to do anything.
		this.isTerminated = true;
		spawnAsteroids(world);
	}
	
	
	
	/**
	 * Variable registering the initial radius.
	 */
	private double initialRadius;
	/**
	 * Variable registering the distance traveled.
	 */
	private double distanceTraveled;
	
	
	/**
	 * Get the initial radius.
	 */
	@Basic
	public double getInitialRadius() {
		return initialRadius;
	}
	
	/**
	 * Get the distance traveled.
	 */
	@Basic
	public double getDistanceTraveled() {
		return distanceTraveled;
	}
	
	
	/**
	 * Set the distance traveled to the given distanceTraveled.
	 * 
	 * @param 	distanceTraveled
	 * 			The new distance traveled.
	 * 
	 * @see implementation
	 */
	private void setDistanceTraveled(double distanceTraveled) {
		this.distanceTraveled = distanceTraveled;
		updateRadius();
	}
	

	/**
	 * Change the position of this entity based on the current
	 * position, velocity and a given time duration time.
	 * 
	 * @param  	time
	 * 			the time duration in which this entity will move.
	 * 
	 * @post   	The X position of this new entity is equal to
	 *         	the current X position times the X velocity times the given time
	 *       	| new.getPositionX() == this.getpositionX() * this.getVelocityX() * time
	 * @post   	The Y position of this new entity is equal to
	 *         	the current X position times the Y velocity times the given time.   	
	 *       	| new.getPositionY() == this.positionY() * this.getVelocityY() * time
	 * @post	The distance traveled is equal to the last distance traveled plus
	 * 			the distance traveled during move.
	 * 			| new.getDistanceTraveled() = this.getDistanceTraveled() + 
	 * 			| Math.sqrt(Math.pow(Math.abs(newPositionX - oldPositionX), 2) +
				| Math.pow(Math.abs(newPositionY - oldPositionY), 2))
				 
	 * @throws 	IllegalArgumentException
	 *         	The time was less than 0.
	 *       	| time < 0     
	 * @throws 	IllegalArgumentException
	 *         	The new position for this entity is not a valid position for any entity.
	 *       	| ! isValidPosition(new.getPositionX(), new.getPositionY())
	 */
	@Override @Raw 
	public void move(double time) throws IllegalArgumentException {
		if (time < 0) throw new IllegalArgumentException();
		
		double newPositionX = helper.calculatePosition(this, time)[0];
		double newPositionY = helper.calculatePosition(this, time)[1];
		double oldPositionX = getPosition().getPositionX();
		double oldPositionY = getPosition().getPositionY();
		
		setDistanceTraveled(getDistanceTraveled() + Math.sqrt(Math.pow(Math.abs(newPositionX - oldPositionX), 2) 
							 + Math.pow(Math.abs(newPositionY - oldPositionY), 2)));
		
		setPosition(newPositionX, newPositionY);
	}
	
	
	/**
	 * Update the current radius.
	 * 
	 * @see implementation
	 */
	private void updateRadius() {
		try {
			setRadius(getInitialRadius() - 0.000001 * getDistanceTraveled());
		}
		catch (IllegalArgumentException exc) {
			this.terminate();
		}
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
	public void resolveCollisionShip(Ship ship) throws IllegalArgumentException {
//		Position newPosition = getRandomPosition(ship.getWorld());
		Position newPosition = getRandomPosition(ship.getWorld(), ship.getRadius());
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
	
	
	/**
	 * Get a random position in the given world.
	 * 
	 * @param 	world
	 * 			The world in which a random position needs to be found.
	 * 
	 * @see implementation
	 */
	@Raw
	private Position getRandomPosition(World world) throws IllegalArgumentException {
		return new Position(getRandomNumber(0, world.getWidth()), getRandomNumber(0, world.getHeight()));
	}
	
	/**
	 * Get a random position in the given world that fits the given radius.
	 * 
	 * @param 	world
	 * 			The world in which a random position needs to be found.
	 * @param	radius
	 * 			The radius to be used.
	 * 
	 * @see implementation
	 */
	@Raw
	private Position getRandomPosition(World world, double radius) throws IllegalArgumentException {
		return new Position(getRandomNumber(radius, world.getWidth() - radius), getRandomNumber(radius, world.getHeight() - radius));
	}
	
	
	/**
	 * Returns a single random number between 2 given values.
	 *
	 * @param 	low
	 *			The lowest random double value. (Inclusive)
	 * @param	high
	 *			The highest random double value. (Exclusive)
	 *
	 * @see implementation
	 */
	@Raw
	public double getRandomNumber(double low, double high) {
	 	return ThreadLocalRandom.current().nextDouble(low, high);
	}
	
	
	/**
	 * Returns a single random sign.
	 * @see implementation
	 */
	@Raw
	public int getRandomSign() {
		int[] numbers = {-1, 1};
		return numbers[ThreadLocalRandom.current().nextInt(2)];
	}
	
	
	
	/**
	 * Spawns 2 asteroids at the position of this planetoid.
	 * 
	 * @param 	world
	 * 			The former world of this planetoid.
	 * 
	 * @see Implementation
	 */
	private void spawnAsteroids(World world) {
		if ( (world == null) || (getRadius() < 30) )
			return;
		double s = getRandomSign();
		Asteroid asteroid1 = new Asteroid(getPosition().getPositionX() - (getRadius() / 2), 
									getPosition().getPositionY(), s * 1.5 * getVelocityX(),
									s * 1.5 * getVelocityY(), getRadius() / 2);
		Asteroid asteroid2 = new Asteroid(getPosition().getPositionX() + (getRadius() / 2), 
				getPosition().getPositionY(), -s * 1.5 * getVelocityX(),
				-s * 1.5 * getVelocityY(), getRadius() / 2);
		
		world.addEntity(asteroid1);
		world.addEntity(asteroid2);
	}
	
	
	
	/**
	 * Returns the type of the class of this Planetoid Class in enumeration format.
	 * 
	 * @see implementation
	 */
	@Raw @Override
	public EntityType getType() {
		return EntityType.PLANETOID;
	}
	
}
