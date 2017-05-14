package asteroids.facade;

import asteroids.part3.facade.IFacade;
import asteroids.part3.programs.IProgramFactory;
import asteroids.part2.CollisionListener;
import asteroids.util.ModelException;

import java.util.*;

import asteroids.helper.entity.*;
import asteroids.model.*;
import asteroids.model.programs.ProgramFactory;


public class Facade implements IFacade {

	@Override @Deprecated
	public Ship createShip() throws ModelException {
		// ship parameters = positionX, positionY, velocityX, velocityY, orientation, radius.
		try {
			return new Ship(0, 0, 0, 0, 0, 10, 1);
		}
		catch (AssertionError exc) {
			throw new ModelException(exc);
		}
		catch (IllegalArgumentException exc) {
			throw new ModelException(exc);
		}
	}

	@Override @Deprecated
	public Ship createShip(double x, double y, double xVelocity, double yVelocity, double radius, double orientation)
			throws ModelException {
		try {
			return new Ship(x, y, xVelocity, yVelocity, orientation, radius, 1);
		}
		catch (AssertionError exc) {
			throw new ModelException(exc);
		}
		catch (IllegalArgumentException exc) {
			throw new ModelException(exc);
		}
	}
	
	@Override
	public Bullet createBullet(double x, double y, double xVelocity, double yVelocity, double radius)
			throws ModelException {
		try {
			return new Bullet(x, y, xVelocity, yVelocity, radius);
		}
		catch (AssertionError exc) {
			throw new ModelException(exc);
		}
		catch (IllegalArgumentException exc) {
			throw new ModelException(exc);
		}
	}

	@Override
	public double[] getShipPosition(Ship ship) throws ModelException {
		try {
			double positionX = ship.getPosition().getPositionX();
			double positionY = ship.getPosition().getPositionY();
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

	@Override @Deprecated
	public void move(Ship ship, double dt) throws ModelException {
		try {
			ship.move(dt);
		}
		catch (IllegalArgumentException exc) {
			throw new ModelException(exc);
		}
		catch (NullPointerException exc) {
			throw new ModelException(exc);
		}
	}

	@Deprecated
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

	@Override
	public Ship createShip(double x, double y, double xVelocity, double yVelocity, double radius, double direction,
			double mass) throws ModelException {
		try {
			return new Ship(x, y, xVelocity, yVelocity, direction, radius, mass);
		}
		catch (AssertionError exc) {
			throw new ModelException(exc);
		}
		catch (IllegalArgumentException exc) {
			throw new ModelException(exc);
		}
	}

	@Override
	public void terminateShip(Ship ship) throws ModelException {
		try {
			ship.terminate();
		}
		catch (NullPointerException exc) {
			throw new ModelException(exc);
		}
	}

	@Override
	public boolean isTerminatedShip(Ship ship) throws ModelException {
		try {
			return ship.isTerminated();
		}
		catch (NullPointerException exc) {
			throw new ModelException(exc);
		}
	}

	@Override
	public double getShipMass(Ship ship) throws ModelException {
		try {
			return ship.getMass();
		}
		catch (NullPointerException exc) {
			throw new ModelException(exc);
		}
	}

	@Override
	public World getShipWorld(Ship ship) throws ModelException {
		try {
			return ship.getWorld();
		}
		catch (NullPointerException exc) {
			throw new ModelException(exc);
		}
	}

	@Override
	public boolean isShipThrusterActive(Ship ship) throws ModelException {
		try {
			return ship.getThrustStatus();
		}
		catch (NullPointerException exc) {
			throw new ModelException(exc);
		}
	}

	@Override
	public void setThrusterActive(Ship ship, boolean active) throws ModelException {
		try {
			if (active)
				ship.thrustOn();
			else
				ship.thrustOff();
		}
		catch (NullPointerException exc) {
			throw new ModelException(exc);
		}
	}

	@Override
	public double getShipAcceleration(Ship ship) throws ModelException {
		try {
			return ship.getAcceleration();
		}
		catch (NullPointerException exc) {
			throw new ModelException(exc);
		}
	}

	@Override
	public void terminateBullet(Bullet bullet) throws ModelException {
		try {
			bullet.terminate();
		}
		catch (NullPointerException exc) {
			throw new ModelException(exc);
		}
	}

	@Override
	public boolean isTerminatedBullet(Bullet bullet) throws ModelException {
		try {
			return bullet.isTerminated();
		}
		catch (NullPointerException exc) {
			throw new ModelException(exc);
		}
	}

	@Override
	public double[] getBulletPosition(Bullet bullet) throws ModelException {
		try {
			double[] position = {bullet.getPosition().getPositionX(), bullet.getPosition().getPositionY()};
			return position;
		}
		catch (NullPointerException exc) {
			throw new ModelException(exc);
		}
	}

	@Override
	public double[] getBulletVelocity(Bullet bullet) throws ModelException {
		try {
			double[] velocity = {bullet.getVelocityX(), bullet.getVelocityY()};
			return velocity;
		}
		catch (NullPointerException exc) {
			throw new ModelException(exc);
		}

	}

	@Override
	public double getBulletRadius(Bullet bullet) throws ModelException {
		try {
			return bullet.getRadius();
		}
		catch (NullPointerException exc) {
			throw new ModelException(exc);
		}
	}

	@Override
	public double getBulletMass(Bullet bullet) throws ModelException {
		try {
			return bullet.getMass();
		}
		catch (NullPointerException exc) {
			throw new ModelException(exc);
		}
	}

	@Override
	public World getBulletWorld(Bullet bullet) throws ModelException {
		try {
			return bullet.getWorld();
		}
		catch (NullPointerException exc) {
			throw new ModelException(exc);
		}
	}

	@Override
	public Ship getBulletShip(Bullet bullet) throws ModelException {
		try {
			return bullet.getShip();
		}
		catch (NullPointerException exc) {
			throw new ModelException(exc);
		}
	}

	@Override
	public Ship getBulletSource(Bullet bullet) throws ModelException {
		try {
			return bullet.getSource();
		}
		catch (NullPointerException exc) {
			throw new ModelException(exc);
		}
	}

	@Override
	public World createWorld(double width, double height) throws ModelException {
		try {
			return new World(width, height);
		}
		catch (NullPointerException exc) {
			throw new ModelException(exc);
		}
	}

	@Override
	public void terminateWorld(World world) throws ModelException {
		try {
			world.terminate();
		}
		catch (NullPointerException exc) {
			throw new ModelException(exc);
		}		
	}

	@Override
	public boolean isTerminatedWorld(World world) throws ModelException {
		try {
			return world.isTerminated();
		}
		catch (NullPointerException exc) {
			throw new ModelException(exc);
		}	
	}

	@Override
	public double[] getWorldSize(World world) throws ModelException {
		try {
			double[] size = {world.getWidth(), world.getHeight()};
			return size;
		}
		catch (NullPointerException exc) {
			throw new ModelException(exc);
		}	
	}

	@Override
	public Set<? extends Ship> getWorldShips(World world) throws ModelException {
		try {
//			return ((Set<Ship>)world.getAllEntitiesSpecific(EntityType.SHIP));
			return world.getAllEntitiesSpecific(Ship.class);
		}
		catch (NullPointerException exc) {
			throw new ModelException(exc);
		}
	}

	@Override
	public Set<? extends Bullet> getWorldBullets(World world) throws ModelException {
		try {
//			return ((Set<Bullet>)world.getAllEntitiesSpecific(EntityType.BULLET));
			return world.getAllEntitiesSpecific(Bullet.class);
		}
		catch (NullPointerException exc) {
			throw new ModelException(exc);
		}
		catch (ClassCastException exc) {
			throw new ModelException(exc);
		}
	}

	@Override
	public void addShipToWorld(World world, Ship ship) throws ModelException {
		try {
			world.addEntity(ship);
		}
		catch (IllegalArgumentException exc) {
			throw new ModelException(exc);
		}
		catch (NullPointerException exc) {
			throw new ModelException(exc);
		}	
	}

	@Override
	public void removeShipFromWorld(World world, Ship ship) throws ModelException {
		try {
			world.removeEntity(ship);
		}
		catch (IllegalArgumentException exc) {
			throw new ModelException(exc);
		}
		catch (NullPointerException exc) {
			throw new ModelException(exc);
		}
	}

	@Override
	public void addBulletToWorld(World world, Bullet bullet) throws ModelException {
		try {
			world.addEntity(bullet);
		}
		catch (IllegalArgumentException exc) {
			throw new ModelException(exc);
		}
		catch (NullPointerException exc) {
			throw new ModelException(exc);
		}
	}

	@Override
	public void removeBulletFromWorld(World world, Bullet bullet) throws ModelException {
		try {
			world.removeEntity(bullet);
		}
		catch (IllegalArgumentException exc) {
			throw new ModelException(exc);
		}
		catch (NullPointerException exc) {
			throw new ModelException(exc);
		}
	}

	@Override
	public Set<? extends Bullet> getBulletsOnShip(Ship ship) throws ModelException {
		try {
			return ship.getAllBullets();
		}
		catch (NullPointerException exc) {
			throw new ModelException(exc);
		}
	}

	@Override
	public int getNbBulletsOnShip(Ship ship) throws ModelException {
		try {
			return ship.getBulletsCount();
		}
		catch (NullPointerException exc) {
			throw new ModelException(exc);
		}
	}

	@Override
	public void loadBulletOnShip(Ship ship, Bullet bullet) throws ModelException {
		try {
			ship.loadBullet(bullet);
		}
		catch (IllegalArgumentException exc) {
			throw new ModelException(exc);
		}
		catch (NullPointerException exc) {
			throw new ModelException(exc);
		}
	}

	@Override
	public void loadBulletsOnShip(Ship ship, Collection<Bullet> bullets) throws ModelException {
		try {
			ship.loadBullets(bullets);
		}
		catch (IllegalArgumentException exc) {
			throw new ModelException(exc);
		}
		catch (NullPointerException exc) {
			throw new ModelException(exc);
		}
	}

	@Override
	public void removeBulletFromShip(Ship ship, Bullet bullet) throws ModelException {
		try {
			ship.removeBullet(bullet);
		}
		catch (IllegalArgumentException exc) {
			throw new ModelException(exc);
		}
		catch (NullPointerException exc) {
			throw new ModelException(exc);
		}		
	}

	@Override
	public void fireBullet(Ship ship) throws ModelException {
		try {
			ship.fireBullet();
		}
		catch (NullPointerException exc) {
			throw new ModelException(exc);
		}
	}

	@Override
	public double getTimeCollisionBoundary(Object object) throws ModelException {
		try {
			return ((Entity) object).getTimeToCollision(((Entity)object).getWorld());
		}
		catch (NullPointerException exc) {
			throw new ModelException(exc);
		}
	}

	@Override
	public double[] getPositionCollisionBoundary(Object object) throws ModelException {
		try {
			return ((Entity) object).getCollisionPosition(((Entity)object).getWorld());
		}
		catch (NullPointerException exc) {
			throw new ModelException(exc);
		}
	}

	@Override
	public double getTimeCollisionEntity(Object entity1, Object entity2) throws ModelException {
		try {
			return ((Entity) entity1).getTimeToCollision((Entity)entity2);
		}
		catch (IllegalArgumentException exc) {
			throw new ModelException(exc);
		}
		catch (NullPointerException exc) {
			throw new ModelException(exc);
		}
	}

	@Override
	public double[] getPositionCollisionEntity(Object entity1, Object entity2) throws ModelException {
		try {
			return ((Entity) entity1).getCollisionPosition((Entity)entity2);
		}
		catch (IllegalArgumentException exc) {
			throw new ModelException(exc);
		}
		catch (NullPointerException exc) {
			throw new ModelException(exc);
		}
	}

	@Override
	public double getTimeNextCollision(World world) throws ModelException {
		try {
			return world.getTimeToFirstCollision();
		}
		catch (IllegalArgumentException exc) {
			throw new ModelException(exc);
		}
		catch (NullPointerException exc) {
			throw new ModelException(exc);
		}
	}

	@Override
	public double[] getPositionNextCollision(World world) throws ModelException {
		try {
			return world.getFirstCollisionPosition();
		}
		catch (IllegalArgumentException exc) {
			throw new ModelException(exc);
		}
		catch (NullPointerException exc) {
			throw new ModelException(exc);
		}
	}

	@Override
	public void evolve(World world, double dt, CollisionListener collisionListener) throws ModelException {
		try {
			world.evolve(dt, null);
		}
		catch (IllegalArgumentException exc) {
			throw new ModelException(exc);
		}
		catch (NullPointerException exc) {
			throw new ModelException(exc);
		}
	}

	@Override
	public Object getEntityAt(World world, double x, double y) throws ModelException {
		try {
			return world.getEntityAtPosition(new Position(x, y));
		}
		catch (NullPointerException exc) {
			throw new ModelException(exc);
		}
	}

	@Override
	public Set<? extends Object> getEntities(World world) throws ModelException {
		try {
			return world.getAllEntities();
		}
		catch (NullPointerException exc) {
			throw new ModelException(exc);
		}
	}

	@Override
	public int getNbStudentsInTeam() {
		return 2;
	}

	@Override
	public Set<? extends Asteroid> getWorldAsteroids(World world) throws ModelException {
		try {
//			return ((Set<Asteroid>)world.getAllEntitiesSpecific(EntityType.ASTEROID));
			return world.getAllEntitiesSpecific(Asteroid.class);
		}
		catch (NullPointerException exc) {
			throw new ModelException(exc);
		}
	}

	@Override
	public void addAsteroidToWorld(World world, Asteroid asteroid) throws ModelException {
		try {
			world.addEntity(asteroid);
		}
		catch (IllegalArgumentException exc) {
			throw new ModelException(exc);
		}
		catch (NullPointerException exc) {
			throw new ModelException(exc);
		}	
	}

	@Override
	public void removeAsteroidFromWorld(World world, Asteroid asteroid) throws ModelException {
		try {
			world.removeEntity(asteroid);
		}
		catch (IllegalArgumentException exc) {
			throw new ModelException(exc);
		}
		catch (NullPointerException exc) {
			throw new ModelException(exc);
		}		
	}

	@Override
	public Set<? extends Planetoid> getWorldPlanetoids(World world) throws ModelException {
		try {
//			return ((Set<Planetoid>)world.getAllEntitiesSpecific(EntityType.ASTEROID));
			return world.getAllEntitiesSpecific(Planetoid.class);
		}
		catch (NullPointerException exc) {
			throw new ModelException(exc);
		}
	}

	@Override
	public void addPlanetoidToWorld(World world, Planetoid planetoid) throws ModelException {
		try {
			world.addEntity(planetoid);
		}
		catch (IllegalArgumentException exc) {
			throw new ModelException(exc);
		}
		catch (NullPointerException exc) {
			throw new ModelException(exc);
		}	
	}

	@Override
	public void removePlanetoidFromWorld(World world, Planetoid planetoid) throws ModelException {
		try {
			world.removeEntity(planetoid);
		}
		catch (IllegalArgumentException exc) {
			throw new ModelException(exc);
		}
		catch (NullPointerException exc) {
			throw new ModelException(exc);
		}	
	}

	@Override
	public Asteroid createAsteroid(double x, double y, double xVelocity, double yVelocity, double radius)
			throws ModelException {
		try {
			return new Asteroid(x, y, xVelocity, yVelocity, radius);
		}
		catch (IllegalArgumentException exc) {
			throw new ModelException(exc);
		}
	}

	@Override
	public void terminateAsteroid(Asteroid asteroid) throws ModelException {
		try {
			asteroid.terminate();
		}
		catch (NullPointerException exc) {
			throw new ModelException(exc);
		}		
	}

	@Override
	public boolean isTerminatedAsteroid(Asteroid asteroid) throws ModelException {
		try {
			return asteroid.isTerminated();
		}
		catch (NullPointerException exc) {
			throw new ModelException(exc);
		}
	}

	@Override
	public double[] getAsteroidPosition(Asteroid asteroid) throws ModelException {
		try {
			double positionX = asteroid.getPosition().getPositionX();
			double positionY = asteroid.getPosition().getPositionY();
			double[] position = {positionX, positionY};
			return position;
		}
		catch (NullPointerException exc) {
			throw new ModelException(exc);
		}
	}

	@Override
	public double[] getAsteroidVelocity(Asteroid asteroid) throws ModelException {
		try {
			double velocityX = asteroid.getVelocityX();
			double velocityY = asteroid.getVelocityY();
			double[] velocity = {velocityX, velocityY};
			return velocity;
		}
		catch (NullPointerException exc) {
			throw new ModelException(exc);
		}
	}

	@Override
	public double getAsteroidRadius(Asteroid asteroid) throws ModelException {
		try {
			return asteroid.getRadius();
		}
		catch (NullPointerException exc) {
			throw new ModelException(exc);
		}
	}

	@Override
	public double getAsteroidMass(Asteroid asteroid) throws ModelException {
		try {
			return asteroid.getMass();
		}
		catch (NullPointerException exc) {
			throw new ModelException(exc);
		}
	}

	@Override
	public World getAsteroidWorld(Asteroid asteroid) throws ModelException {
		try {
			return asteroid.getWorld();
		}
		catch (NullPointerException exc) {
			throw new ModelException(exc);
		}
	}

	@Override
	public Planetoid createPlanetoid(double x, double y, double xVelocity, double yVelocity, double radius,
			double totalTraveledDistance) throws ModelException {
		try {
			return new Planetoid(x, y, xVelocity, yVelocity, radius, totalTraveledDistance);
		}
		catch (IllegalArgumentException exc) {
			throw new ModelException(exc);
		}
	}

	@Override
	public void terminatePlanetoid(Planetoid planetoid) throws ModelException {
		try {
			planetoid.terminate();
		}
		catch (NullPointerException exc) {
			throw new ModelException(exc);
		}	
	}

	@Override
	public boolean isTerminatedPlanetoid(Planetoid planetoid) throws ModelException {
		try {
			return planetoid.isTerminated();
		}
		catch (NullPointerException exc) {
			throw new ModelException(exc);
		}
	}

	@Override
	public double[] getPlanetoidPosition(Planetoid planetoid) throws ModelException {
		try {
			double positionX = planetoid.getPosition().getPositionX();
			double positionY = planetoid.getPosition().getPositionY();
			double[] position = {positionX, positionY};
			return position;
		}
		catch (NullPointerException exc) {
			throw new ModelException(exc);
		}
	}

	@Override
	public double[] getPlanetoidVelocity(Planetoid planetoid) throws ModelException {
		try {
			double velocityX = planetoid.getVelocityX();
			double velocityY = planetoid.getVelocityY();
			double[] velocity = {velocityX, velocityY};
			return velocity;
		}
		catch (NullPointerException exc) {
			throw new ModelException(exc);
		}
	}

	@Override
	public double getPlanetoidRadius(Planetoid planetoid) throws ModelException {
		try {
			return planetoid.getRadius();
		}
		catch (NullPointerException exc) {
			throw new ModelException(exc);
		}
	}

	@Override
	public double getPlanetoidMass(Planetoid planetoid) throws ModelException {
		try {
			return planetoid.getMass();
		}
		catch (NullPointerException exc) {
			throw new ModelException(exc);
		}
	}

	@Override
	public double getPlanetoidTotalTraveledDistance(Planetoid planetoid) throws ModelException {
		try {
			return planetoid.getDistanceTravelled();
		}
		catch (NullPointerException exc) {
			throw new ModelException(exc);
		}
	}

	@Override
	public World getPlanetoidWorld(Planetoid planetoid) throws ModelException {
		try {
			return planetoid.getWorld();
		}
		catch (NullPointerException exc) {
			throw new ModelException(exc);
		}
	}

	@Override
	public Program getShipProgram(Ship ship) throws ModelException {
		try {
			return ship.getProgram();
		}
		catch (NullPointerException exc) {
			throw new ModelException(exc);
		}
	}

	@Override
	public void loadProgramOnShip(Ship ship, Program program) throws ModelException {
		try {
			ship.setProgram(program);
		}
		catch (NullPointerException exc) {
			throw new ModelException(exc);
		}
	}

	@Override
	public List<Object> executeProgram(Ship ship, double dt) throws ModelException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IProgramFactory<?, ?, ?, ? extends Program> createProgramFactory() throws ModelException {
		return new ProgramFactory();
	}

}
