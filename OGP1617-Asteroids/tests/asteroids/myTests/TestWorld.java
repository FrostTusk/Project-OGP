package asteroids.myTests;

import static org.junit.Assert.*;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import asteroids.helper.entity.Entity;
import asteroids.helper.entity.MinorPlanet;
import asteroids.helper.entity.Position;
import asteroids.model.Asteroid;
import asteroids.model.Bullet;
import asteroids.model.Planetoid;
import asteroids.model.Program;
import asteroids.model.Ship;
import asteroids.model.World;
import asteroids.part3.facade.IFacade;
import asteroids.part3.programs.IProgramFactory;
import asteroids.util.ModelException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;



/* 
 * Tests Index:
 * 1. Tests for Initialization
 * 2. Tests for Size (basic)
 * 3. Tests for Size (advanced)
 * 4. Tests for Entities
 * 5. Tests for evolve
 */

public class TestWorld {
	
	private static final double EPSILON = 0.0001;
	IFacade facade;
	IProgramFactory<?, ?, ?, Program> programFactory;
	

			/*
			 * |--------------------------------------------|
			 * | 1. The next test test the Initialization.	|
			 * |--------------------------------------------| 
			 */	
	
	
	
	@Test
	public void testCreateWorld() {
		World world = new World(10, 10);
		assertNotNull(world);
	}
	
	
	@Test
	public void testTerminateWorld() {
		World world = new World(15, -10);
		assertNotNull(world);
		world.terminate();
		assertTrue(world.isTerminated());
	}
	
	@Test
	public void testTerminateWorldWithEntities() {
		World world = new World(100, 100);
		Ship ship = new Ship (20, 20, 0, 0, 0, 10, 10);
		Bullet bullet = new Bullet (70, 70, 0, 0, 1);
		world.addEntity(ship);
		world.addEntity(bullet);
		assertNotNull(world);
		world.terminate();
		assertTrue(world.isTerminated());
		assertTrue(ship.getWorld() != world);
		assertTrue(bullet.getWorld() != world);
	}
	
	
	
			/*
			 * |--------------------------------------------|
			 * | 2. The next test test the Size. (Basic)	|
			 * |--------------------------------------------| 
			 */	

	
	
	@Test
	public void testCreateWorldSizeGeneric() {
		World world = new World(15, 10);
		assertNotNull(world);
		assertEquals(15, world.getWidth(), EPSILON);
		assertEquals(10, world.getHeight(), EPSILON);
	}
	
	
	@Test
	public void testCreateWorldSizeWidthZero() {
		World world = new World(0, 0);
		assertNotNull(world);
		assertEquals(0, world.getWidth(), EPSILON);
		assertEquals(0, world.getHeight(), EPSILON);
	}
	
	@Test
	public void testCreateWorldSizeWidthEdgeOverflow() {
		World world = new World(Double.MAX_VALUE, 10);
		assertNotNull(world);
		assertEquals(Double.MAX_VALUE, world.getWidth(), EPSILON);
		assertEquals(10, world.getHeight(), EPSILON);
	}
	
	@Test
	public void testCreateWorldSizeHeightEdgeOverflow() {
		World world = new World(15, Double.MAX_VALUE);
		assertNotNull(world);
		assertEquals(15, world.getWidth(), EPSILON);
		assertEquals(Double.MAX_VALUE, world.getHeight(), EPSILON);
	}
	
	
	@Test
	public void testCreateWorldSizeWidthHeightEdgeOverflow() {
		World world = new World(Double.MAX_VALUE, Double.MAX_VALUE);
		assertNotNull(world);
		assertEquals(Double.MAX_VALUE, world.getWidth(), EPSILON);
		assertEquals(Double.MAX_VALUE, world.getHeight(), EPSILON);
	}
	
	
	@Test
	public void testCreateWorldSizeWidthOverflow() {
		World world = new World(Double.MAX_VALUE + 50, 10);
		assertNotNull(world);
		assertEquals(Double.MAX_VALUE, world.getWidth(), EPSILON);
		assertEquals(10, world.getHeight(), EPSILON);
	}
	
	@Test
	public void testCreateWorldSizeHeightOverflow() {
		World world = new World(15, Double.MAX_VALUE + 50);
		assertNotNull(world);
		assertEquals(15, world.getWidth(), EPSILON);
		assertEquals(Double.MAX_VALUE, world.getHeight(), EPSILON);
	}
	
	@Test
	public void testCreateWorldSizeWidthHeightOverflow() {
		World world = new World(Double.MAX_VALUE, Double.MAX_VALUE);
		assertNotNull(world);
		assertEquals(Double.MAX_VALUE, world.getWidth(), EPSILON);
		assertEquals(Double.MAX_VALUE, world.getHeight(), EPSILON);
	}
	
	
	@Test
	public void testCreateWorldSizeWidthNeg() {
		World world = new World(-15, 10);
		assertNotNull(world);
		assertEquals(0, world.getWidth(), EPSILON);
		assertEquals(10, world.getHeight(), EPSILON);
	}
	
	@Test
	public void testCreateWorldSizeHeightNeg() {
		World world = new World(15, -10);
		assertNotNull(world);
		assertEquals(15, world.getWidth(), EPSILON);
		assertEquals(0, world.getHeight(), EPSILON);
	}
	
	@Test
	public void testCreateWorldSizeWidthHeightNeg() {
		World world = new World(-15, -10);
		assertNotNull(world);
		assertEquals(0, world.getWidth(), EPSILON);
		assertEquals(0, world.getHeight(), EPSILON);
	}

	
	
			/*
			 * |--------------------------------------------|
			 * | 3. The next test test the Size. (Advanced)	|
			 * |--------------------------------------------| 
			 */	
	
	

	@Test
	public void testWorldIsValidSizeT() {
		assertTrue(World.isValidSize(0, 0));
		assertTrue(World.isValidSize(100, 100));
		assertTrue(World.isValidSize(100, 50));
	}
	
	@Test
	public void testWorldIsValidSizeF() {
		assertFalse(World.isValidSize(-10, -10));
		assertFalse(World.isValidSize(10, -10));
		assertFalse(World.isValidSize(-10, 10));
		assertFalse(World.isValidSize(Double.NaN, 10));
		assertFalse(World.isValidSize(Double.NaN, Double.NaN));
		assertFalse(World.isValidSize(10, Double.NaN));
		assertFalse(World.isValidSize(Double.POSITIVE_INFINITY, 10));
		assertFalse(World.isValidSize(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY));
		assertFalse(World.isValidSize(10, Double.POSITIVE_INFINITY));
	}
	
	
//	/*	The next test crashes the Test Suite, this is because it changes
//	 *	the upper bound (which is a static variable). Almost all the other tests
//	 *	create worlds with sizes above 5, which will mean they almost all fail.
//	 */
//	@Test
//	public void testCreateWorldNewUpperBound() {
//		World world1 = new World(15, 10);
//		assertNotNull(world1);
//		world1.setUpperBound(5);
//		assertNotNull(world1);
//		World world2 = new World(15, 10);
//		assertNotNull(world2);
//		assertEquals(5, world2.getWidth(), EPSILON);
//		assertEquals(5, world2.getHeight(), EPSILON);
//	}
	
	
		
			/*
			 * |----------------------------------------------------|
			 * | 4. The next test test the relation with Entities	|
			 * |----------------------------------------------------| 
			 */	
	
	
	
	@Test
	public void testWorldCanContainEntityGeneric() {
		World world = new World(1000, 1000);
		Ship ship = new Ship(100, 100, 10, -10, Math.PI, 20, 10);
		Bullet bullet = new Bullet(1, 1, 1, 1, 1);
		assertTrue(world.canHaveAsEntity(ship));
		assertTrue(world.canHaveAsEntity(bullet));
	}
	
	@Test
	public void testWorldCanContainEntityBoundariesF() {
		World world = new World(1000, 1000);
		Ship ship = new Ship(1000, 1000, 10, -10, Math.PI, 20, 10);
		Bullet bullet = new Bullet(0, 0, 1, 1, 1);
		assertFalse(world.canHaveAsEntity(ship));
		assertFalse(world.canHaveAsEntity(bullet));
	}
	
	@Test
	public void testWorldCanContainEntityBulletOfShip() {
		World world = new World(1000, 1000);
		Ship ship = new Ship(500, 500, 10, -10, Math.PI, 20, 10);
		Bullet bullet = new Bullet(500, 500, 1, 1, 1);
		ship.loadBullet(bullet);
		assertTrue(world.canHaveAsEntity(ship));
		assertFalse(bullet.canHaveAsWorld(world));
	}
	
	@Test
	public void testWorldCanContainEntityShip() {
		World world = new World(1000, 1000);
		Ship ship1 = new Ship(500, 500, 10, -10, Math.PI, 20, 10);
		Ship ship2 = new Ship(1000, 500, 10, -10, Math.PI, 20, 10);
		Ship ship3 = new Ship(900, 900, 10, -10, Math.PI, 200, 10);
		Ship ship4 = new Ship(500, 500, 10, -10, Math.PI, 20, 10);
		assertTrue(world.canHaveAsEntity(ship1));
		assertTrue(world.canHaveAsEntity(ship4));
		assertFalse(world.canHaveAsEntity(ship2));
		assertFalse(world.canHaveAsEntity(ship3));
	}
	
	@Test
	public void testWorldCanContainEntityNull() {
		World world = new World(1000, 1000);
		Ship ship1 = null;
		Ship ship2 = null;
		assertFalse(world.canHaveAsEntity(ship1));
		assertFalse(world.canHaveAsEntity(ship2));
	}
	
	
	@Test
	public void testWorldContainsEntityT() {
		World world = new World(1000, 1000);
		Ship ship = new Ship(500, 500, 10, -10, Math.PI, 20, 10);
		Bullet bullet = new Bullet(20, 20, 1, 1, 1);
		world.addEntity(ship);
		world.addEntity(bullet);
		assertTrue(world.containsEntity(ship));
		assertTrue(world.containsEntity(bullet));
	}
	
	@Test
	public void testWorldContainsEntityF() {
		World world = new World(1000, 1000);
		Ship ship = new Ship(500, 500, 10, -10, Math.PI, 20, 10);
		Bullet bullet = new Bullet(20, 20, 1, 1, 1);
		assertFalse(world.containsEntity(ship));
		assertFalse(world.containsEntity(bullet));
	}
	
	
	@Test
	public void testWorldAddEntityAble() {
		World world = new World(1000, 1000);
		Ship ship = new Ship(100, 100, 10, -10, Math.PI, 20, 10);
		Bullet bullet = new Bullet(1, 1, 1, 1, 1);
		assertFalse(world.containsEntity(ship));
		assertFalse(world.containsEntity(bullet));
		world.addEntity(ship);
		world.addEntity(bullet);
		assertTrue(world.containsEntity(ship));
		assertTrue(world.containsEntity(bullet));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testWorldAddEntityUnable() throws IllegalArgumentException {
		World world = new World(1000, 1000);
		Ship ship = new Ship(1000, 1000, 10, -10, Math.PI, 20, 10);
		Bullet bullet = new Bullet(1000, 1000, 1, 1, 1);
		world.addEntity(ship);
		world.addEntity(bullet);
		assertFalse(world.containsEntity(ship));
		assertFalse(world.containsEntity(bullet));
	}
	
	
	@Test
	public void testWorldRemoveEntityGeneric() {
		World world = new World(1000, 1000);
		Ship ship = new Ship(100, 100, 10, -10, Math.PI, 20, 10);
		Bullet bullet = new Bullet(1, 1, 1, 1, 1);
		world.addEntity(ship);
		ship.setWorld(world);
		world.addEntity(bullet);
		bullet.setWorld(world);
		assertTrue(world.containsEntity(ship));
		assertTrue(world.containsEntity(bullet));
		world.removeEntity(ship);
		ship.setWorld(null);
		world.removeEntity(bullet);
		bullet.setWorld(null);
		assertFalse(world.containsEntity(ship));
		assertFalse(world.containsEntity(bullet));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testWorldRemoveEntityNotInWorld() throws IllegalArgumentException {
		World world = new World(1000, 1000);
		Ship ship = new Ship(100, 100, 10, -10, Math.PI, 20, 10);
		Bullet bullet = new Bullet(1, 1, 1, 1, 1);
		world.removeEntity(ship);
		world.removeEntity(bullet);
		assertFalse(world.containsEntity(ship));
		assertFalse(world.containsEntity(bullet));
	}
	
	
	@Test
	public void testWorldGetFirstCollisionPosition() {
		World world = new World(1000, 1000);
		Ship ship = new Ship(150, 100, 0, 10, Math.PI, 20, 10);
		Bullet bullet = new Bullet(150, 150, 0, 0, 1);
		world.addEntity(ship);
		ship.setWorld(world);
		world.addEntity(bullet);
		bullet.setWorld(world);
		world.getFirstCollisionPosition();
		assertEquals(150, world.getFirstCollisionPosition()[0], EPSILON);
		assertEquals(149, world.getFirstCollisionPosition()[1], EPSILON);
	}
	
	@Test
	public void testWorldGetFirstCollisionPositionNoCollision() {
		World world = new World(1000, 1000);
		Ship ship = new Ship(150, 100, 0, 0, Math.PI, 20, 10);
		Bullet bullet = new Bullet(150, 150, 0, 0, 1);
		world.addEntity(ship);
		ship.setWorld(world);
		world.addEntity(bullet);
		bullet.setWorld(world);
		if (world.getFirstCollisionPosition() != null) {		
			assertEquals(Double.POSITIVE_INFINITY, world.getFirstCollisionPosition()[0], EPSILON);
			assertEquals(Double.POSITIVE_INFINITY, world.getFirstCollisionPosition()[1], EPSILON);
		}
	}
	
	
	@Test
	public void testWorldGetFirstCollisionTime() {
		World world = new World(1000, 1000);
		Ship ship1 = new Ship(100, 100, 0, 10, Math.PI, 20, 10);
		Ship ship2 = new Ship(100, 150, 0, 0, Math.PI, 20, 10);
		world.addEntity(ship1);
		ship1.setWorld(world);
		world.addEntity(ship2);
		ship2.setWorld(world);
		assertEquals(1, world.getTimeToFirstCollision(), EPSILON);
	}
	
	@Test
	public void testWorldGetFirstCollisionTimeNoCollision() {
		World world = new World(1000, 1000);
		Ship ship1 = new Ship(100, 100, 0, 0, Math.PI, 20, 10);
		Ship ship2 = new Ship(150, 150, 0, 0, Math.PI, 20, 10);
		world.addEntity(ship1);
		ship1.setWorld(world);
		world.addEntity(ship2);
		ship2.setWorld(world);
		assertEquals(Double.POSITIVE_INFINITY, world.getTimeToFirstCollision(), EPSILON);
	}
	
	@Test
	public void testWorldGetFirstCollisionTimeNoEntities() {
		World world = new World(1000, 1000);
		assertEquals(-1, world.getTimeToFirstCollision(), EPSILON);
	}
	
	@Test
	public void testWorldGetFirstCollisionEntitiesNoCollision() {
		World world = new World(1000, 1000);
		Ship ship1 = new Ship(100, 100, 0, 0, Math.PI, 20, 10);
		Ship ship2 = new Ship(150, 150, 0, 0, Math.PI, 20, 10);
		world.addEntity(ship1);
		ship1.setWorld(world);
		world.addEntity(ship2);
		ship2.setWorld(world);
		Entity[] ships1 = {ship1, ship2};
		Entity[] ships2 = {ship2, ship1};
		if ( (world.getFirstCollisionEntities()[0] == ships1[0] && world.getFirstCollisionEntities()[1] == ships1[1]) ||
				(world.getFirstCollisionEntities()[0] == ships2[0] && world.getFirstCollisionEntities()[1] == ships2[1]) ) return; 
		else fail();
	}
	
	
	@Test
	public void testWorldGetFirstCollisionEntitiesGeneric() {
		World world = new World(1000, 1000);
		Ship ship1 = new Ship(100, 100, 10, 0, Math.PI, 20, 10);
		Ship ship2 = new Ship(150, 100, 0, 0, Math.PI, 20, 10);
		Ship ship3 = new Ship(200, 100, 0, 0, Math.PI, 20, 10);
		Ship ship4 = new Ship(250, 100, 0, 0, Math.PI, 20, 10);
		world.addEntity(ship1);
		ship1.setWorld(world);
		world.addEntity(ship2);
		ship2.setWorld(world);
		world.addEntity(ship3);
		ship3.setWorld(world);
		world.addEntity(ship4);
		ship4.setWorld(world);
		Entity[] ships1 = {ship1, ship2};
		Entity[] ships2 = {ship2, ship1};
		if ( (world.getFirstCollisionEntities()[0] == ships1[0] && world.getFirstCollisionEntities()[1] == ships1[1]) ||
				(world.getFirstCollisionEntities()[0] == ships2[0] && world.getFirstCollisionEntities()[1] == ships2[1]) ) return; 
		else fail();
	}
	
	@Test
	public void testWorldGetFirstCollisionEntitiesNoEntities() {
		World world = new World(1000, 1000);
		world.getFirstCollisionEntities();
	}
	
	
	@Test
	public void testWorldGetAllEntitiesGeneric() {
		World world = new World(1000, 1000);
		Ship ship1 = new Ship(100, 100, 10, 0, Math.PI, 20, 10);
		Ship ship2 = new Ship(150, 100, 0, 0, Math.PI, 20, 10);
		Bullet bullet = new Bullet(150, 150, 0, 0, 1);
		world.addEntity(ship1);
		ship1.setWorld(world);
		world.addEntity(ship2);
		ship2.setWorld(world);
		world.addEntity(bullet);
		bullet.setWorld(world);
		Set<Entity> entitiesTest = world.getAllEntities();
		Set<Entity> entitiesReference = new HashSet<Entity>(Arrays.asList(ship1, ship2, bullet));
		double counter = 0;
		for (Entity entityTest : entitiesTest) for (Entity entityRef : entitiesReference) if (entityTest == entityRef) counter += 1;
		if (counter != 3) fail();
		assertEquals(3, entitiesTest.size(), EPSILON);
	}
	
	@Test
	public void testWorldGetAllEntitiesAllShips() {
		World world = new World(1000, 1000);
		Ship ship1 = new Ship(100, 100, 10, 0, Math.PI, 20, 10);
		Ship ship2 = new Ship(150, 100, 0, 0, Math.PI, 20, 10);
		Ship ship3 = new Ship(200, 100, 0, 0, Math.PI, 20, 10);
		Ship ship4 = new Ship(250, 100, 0, 0, Math.PI, 20, 10);
		world.addEntity(ship1);
		ship1.setWorld(world);
		world.addEntity(ship2);
		ship2.setWorld(world);
		world.addEntity(ship3);
		ship3.setWorld(world);
		world.addEntity(ship4);
		ship4.setWorld(world);
		Set<Entity> entities = world.getAllEntities();
//		Set<Ship> ships = ((Set<Ship>) world.getAllEntitiesSpecific(EntityType.SHIP));
		Set<Ship> ships = world.getAllEntitiesSpecific(Ship.class);
		double counter = 0;
		for (Ship ship : ships) for (Entity entity : entities) if (ship == entity) counter += 1;
		if (counter != 4) fail();
		assertEquals(4, entities.size(), EPSILON);
	}
	
	@Test
	public void testWorldGetAllEntitiesAllBullets() {
		World world = new World(1000, 1000);
		Bullet bullet1 = new Bullet(150, 100, 0, 0, 1);
		Bullet bullet2 = new Bullet(150, 150, 0, 0, 1);
		Bullet bullet3 = new Bullet(150, 200, 0, 0, 1);
		world.addEntity(bullet1);
		bullet1.setWorld(world);
		world.addEntity(bullet2);
		bullet2.setWorld(world);
		world.addEntity(bullet3);
		bullet3.setWorld(world);
		Set<Entity> entities = world.getAllEntities();
		Set<Bullet> bullets = new HashSet<Bullet>(Arrays.asList(bullet1, bullet2, bullet3));
		double counter = 0;
		for (Entity entityTest : entities) for (Bullet bullet : bullets) if (entityTest == bullet) counter += 1;
		if (counter != 3) fail();
		assertEquals(3, entities.size(), EPSILON);
	}
	
	@Test
	public void testWorldGetAllEntitiesNoEntities() {
		World world = new World(1000, 1000);
		Set<Entity> entities = world.getAllEntities();
		assertEquals(0, entities.size(), EPSILON);
	}
	
	@Test
	public void testWorldGetEntityAtPosition() {
		World world = new World(1000, 1000);
		Bullet bullet1 = new Bullet(150, 100, 0, 0, 1);
		Bullet bullet2 = new Bullet(150, 150, 0, 0, 1);
		Bullet bullet3 = new Bullet(150, 200, 0, 0, 1);
		Position position1 = bullet1.getPosition();
		Position position2 = bullet2.getPosition();
		Position position3 = bullet3.getPosition();
		for (@SuppressWarnings("unused") Entity entity : world.getAllEntities()) {
			if (world.getEntityAtPosition(position1) != bullet1 || world.getEntityAtPosition(position2) != bullet2 
					|| world.getEntityAtPosition(position3) != bullet3) fail();
		}
	}
	
	
	
			/*
			 * |--------------------------------------------|
			 * | 5. The next test test the evolve method	|
			 * |--------------------------------------------| 
			 */	
	
	
	
	@Test
	public void testWorldEvolveNoMovement() {
		World world = new World(1000, 1000);
		Bullet bullet1 = new Bullet(150, 150, 0, 0, 1);
		Bullet bullet2 = new Bullet(250, 250, 0, 0, 1);
		Ship ship1 = new Ship(100, 100, 0, 0, Math.PI, 20, 10);
		Ship ship2 = new Ship(200, 200, 0, 0, Math.PI, 20, 10);
		world.addEntity(ship1);
		world.addEntity(ship2);
		world.addEntity(bullet1);
		world.addEntity(bullet2);
		world.evolve(2, null);
		assertEquals(150, bullet1.getPosition().getPositionX(), EPSILON);
		assertEquals(150, bullet1.getPosition().getPositionY(), EPSILON);
		assertEquals(250, bullet2.getPosition().getPositionX(), EPSILON);
		assertEquals(250, bullet2.getPosition().getPositionY(), EPSILON);
		assertEquals(100, ship1.getPosition().getPositionX(), EPSILON);
		assertEquals(100, ship1.getPosition().getPositionY(), EPSILON);
		assertEquals(200, ship2.getPosition().getPositionX(), EPSILON);
		assertEquals(200, ship2.getPosition().getPositionY(), EPSILON);
	}
	
	@Test
	public void testWorldEvolveNoEntities() {
		World world = new World(1000, 1000);
		world.evolve(2, null);
	}
	
	@Test
	public void testWorldEvolveCollisionBetweenShipAndBullet() {
		World world = new World(1000, 1000);
		Bullet bullet = new Bullet(150, 150, 60, 0, 1);
		Ship ship1 = new Ship(100, 100, 0, 0, Math.PI, 20, 10);
		Ship ship2 = new Ship(200, 150, 0, 0, Math.PI, 20, 10);
		world.addEntity(ship1);
		world.addEntity(ship2);
		world.addEntity(bullet);
		bullet.setSource(ship1);
		world.evolve(2, null);
		assertTrue(ship2.isTerminated());
		assertTrue(bullet.isTerminated());
	}
	
	@Test
	public void testWorldEvolveNoCollisions() {
		World world = new World(1000, 1000);
		Ship ship = new Ship(100, 100, 10, -10, Math.PI, 20, 10);
		Bullet bullet = new Bullet(2, 2, 10, 10, 1);
		world.addEntity(ship);
		ship.setWorld(world);
		world.addEntity(bullet);
		bullet.setWorld(world);
		world.evolve(2, null);
		assertEquals(120, ship.getPosition().getPositionX(), EPSILON);
		assertEquals(80, ship.getPosition().getPositionY(), EPSILON);
		assertEquals(22, bullet.getPosition().getPositionX(), EPSILON);
		assertEquals(22, bullet.getPosition().getPositionY(), EPSILON);
	}
	
	@Test
	public void testWorldEvolveCollisionShip2InRest() {
		World world = new World(1000, 1000);
		Ship ship1 = new Ship(100, 500, 10, 0, Math.PI, 20, 10);
		Ship ship2 = new Ship(500, 500, 0, 0, Math.PI, 20, 10);
		Bullet bullet = new Bullet(5, 5, 0, 0, 1);
		world.addEntity(ship1);
		ship1.setWorld(world);
		world.addEntity(ship2);
		ship2.setWorld(world);
		world.addEntity(bullet);
		bullet.setWorld(world);
		world.evolve(20, null);
		assertEquals(10, ship1.getVelocityX(), EPSILON);
		assertEquals(0, ship1.getVelocityY(), EPSILON);
		assertEquals(0, ship2.getVelocityX(), EPSILON);
		assertEquals(0, ship2.getVelocityY(), EPSILON);
		world.evolve(30, null);
		assertEquals(0, ship1.getVelocityY(), EPSILON);
		assertEquals(0, ship2.getVelocityY(), EPSILON);
		assertEquals(0, ship1.getVelocityX(), EPSILON);
		assertEquals(10, ship2.getVelocityX(), EPSILON);
	}
	
	@Test
	public void testWorldEvolveCollisionBulletHorizontalBoundary() {
		World world = new World(1000, 1000);
		Ship ship1 = new Ship(100, 500, 0, 0, Math.PI, 20, 10);
		Ship ship2 = new Ship(500, 500, 0, 0, Math.PI, 20, 10);
		Bullet bullet = new Bullet(5, 5, -1, 10, 1);
		world.addEntity(ship1);
		ship1.setWorld(world);
		world.addEntity(ship2);
		ship2.setWorld(world);
		world.addEntity(bullet);
		bullet.setWorld(world);
		double counter = bullet.getBoundaryCollisionCounter();
		assertEquals(0, counter, EPSILON);
		world.evolve(3, null);
		assertEquals(-1, bullet.getVelocityX(), EPSILON);
		assertEquals(10, bullet.getVelocityY(), EPSILON);
		world.evolve(3, null);
		assertEquals(1, bullet.getVelocityX(), EPSILON);
		assertEquals(10, bullet.getVelocityY(), EPSILON);
		assertEquals(1, bullet.getBoundaryCollisionCounter(), EPSILON);
	}
	
	@Test
	public void testWorldEvolveCollisionBulletHorizontalBoundaryCounterFull() {
		World world = new World(1000, 1000);
		Bullet bullet = new Bullet(900, 50, 90, 0, 10);
		world.addEntity(bullet);
		bullet.setWorld(world);
		double counter = bullet.getBoundaryCollisionCounter();
		assertEquals(0, counter, EPSILON);
		assertEquals(90, bullet.getVelocityX(), EPSILON);
		assertEquals(0, bullet.getVelocityY(), EPSILON);
		world.evolve(10, null);
		assertEquals(-90, bullet.getVelocityX(), EPSILON);
		assertEquals(0, bullet.getVelocityY(), EPSILON);
		assertEquals(1, bullet.getBoundaryCollisionCounter(), EPSILON);
		world.evolve(10, null);
		assertEquals(90, bullet.getVelocityX(), EPSILON);
		assertEquals(0, bullet.getVelocityY(), EPSILON);
		assertEquals(2, bullet.getBoundaryCollisionCounter(), EPSILON);
		world.evolve(10, null);
		assertTrue(bullet.isTerminated());
	}
	
	@Test
	public void testWorldEvolveCollisionBulletVerticalBoundary0() {
		World world = new World(1000, 1000);
		Ship ship1 = new Ship(100, 500, 0, 0, Math.PI, 20, 10);
		Ship ship2 = new Ship(500, 500, 0, 0, Math.PI, 20, 10);
		Bullet bullet = new Bullet(5, 5, 10, -1, 1);
		world.addEntity(ship1);
		ship1.setWorld(world);
		world.addEntity(ship2);
		ship2.setWorld(world);
		world.addEntity(bullet);
		bullet.setWorld(world);
		double counter = bullet.getBoundaryCollisionCounter();
		assertEquals(0, counter, EPSILON);
		world.evolve(3, null);
		assertEquals(10, bullet.getVelocityX(), EPSILON);
		assertEquals(-1, bullet.getVelocityY(), EPSILON);
		world.evolve(3, null);
		assertEquals(10, bullet.getVelocityX(), EPSILON);
		assertEquals(1, bullet.getVelocityY(), EPSILON);
		assertEquals(1, bullet.getBoundaryCollisionCounter(), EPSILON);
	}
	
	@Test
	public void testWorldEvolveCollisionBulletHorizontalBoundaryWidth() {
		World world = new World(1000, 1000);
		Bullet bullet = new Bullet(900, 5, 10, 1, 1);
		world.addEntity(bullet);
		bullet.setWorld(world);
		double counter = bullet.getBoundaryCollisionCounter();
		assertEquals(0, counter, EPSILON);
		world.evolve(7, null);
		assertEquals(10, bullet.getVelocityX(), EPSILON);
		assertEquals(1, bullet.getVelocityY(), EPSILON);
		world.evolve(10, null);
		assertEquals(-10, bullet.getVelocityX(), EPSILON);
		assertEquals(1, bullet.getVelocityY(), EPSILON);
		assertEquals(1, bullet.getBoundaryCollisionCounter(), EPSILON);
	}
	
	@Test
	public void testWorldEvolveCollisionShipBoundaryX() {
		World world = new World(1000, 1000);
		Ship ship1 = new Ship(900, 500, 0, 0, Math.PI, 20, 10);
		Ship ship2 = new Ship(500, 500, 0, 0, Math.PI, 20, 10);
		Bullet bullet = new Bullet(5, 5, -1, 10, 1);
		world.addEntity(ship1);
		ship1.setWorld(world);
		world.addEntity(ship2);
		ship2.setWorld(world);
		world.addEntity(bullet);
		bullet.setWorld(world);
		double counter = bullet.getBoundaryCollisionCounter();
		assertEquals(0, counter, EPSILON);
		world.evolve(3, null);
		assertEquals(-1, bullet.getVelocityX(), EPSILON);
		assertEquals(10, bullet.getVelocityY(), EPSILON);
		world.evolve(3, null);
		assertEquals(1, bullet.getVelocityX(), EPSILON);
		assertEquals(10, bullet.getVelocityY(), EPSILON);
		assertEquals(1, bullet.getBoundaryCollisionCounter(), EPSILON);
	}
	
	@Test
	public void testWorldEvolveCollisionShipBoundaryY() {
		World world = new World(1000, 1000);
		Ship ship = new Ship(500, 900, 1, 10, Math.PI, 20, 10);
		world.addEntity(ship);
		ship.setWorld(world);
		world.evolve(4, null);
		assertEquals(1, ship.getVelocityX(), EPSILON);
		assertEquals(10, ship.getVelocityY(), EPSILON);
		world.evolve(3, null);
		assertEquals(1, ship.getVelocityX(), EPSILON);
		assertEquals(10, ship.getVelocityY(), EPSILON);
		world.evolve(2, null);
		assertEquals(1, ship.getVelocityX(), EPSILON);
		assertEquals(-10, ship.getVelocityY(), EPSILON);
	}
	
	@Test
	public void testWorldEvolveCollisionShipStartsAgainstBoundaryMovesFromBoundary() {
		World world = new World(1000, 1000);
		Ship ship = new Ship(800, 900, 0, -10, Math.PI, 100, 10);
		world.addEntity(ship);
		ship.setWorld(world);
		assertEquals(0, ship.getVelocityX(), EPSILON);
		assertEquals(-10, ship.getVelocityY(), EPSILON);
		world.evolve(1, null);
		assertEquals(0, ship.getVelocityX(), EPSILON);
		assertEquals(-10, ship.getVelocityY(), EPSILON);
	}
	
	@Test
	public void testWorldEvolveCollisionShipOwnBullet() {
		World world = new World(1000, 1000);
		Ship ship = new Ship(500, 500, 0, 0, 0, 20, 10);
		Bullet bullet = new Bullet(500, 500, 10, 10, 1);
		world.addEntity(ship);	
		ship.setWorld(world);
		ship.loadBullet(bullet);
		ship.fireBullet(bullet);
		assertEquals(250, bullet.getVelocityX(), EPSILON);
		assertEquals(0, bullet.getVelocityY(), EPSILON);
		ship.setPosition(800, 500);
		assertEquals(0, ship.getBulletsCount(), EPSILON);
		world.evolve(2, null);
		assertEquals(1, ship.getBulletsCount(), EPSILON);
		assertTrue(bullet.getShip() == ship);
	}
	
	@Test
	public void testWorldEvolve2CollisionsSameTimeBulletShip() {
		World world = new World(1000, 1000);
		Ship ship1 = new Ship(500, 500, 0, 0, 0, 20, 10);
		Ship ship2 = new Ship(700, 500, 0, 0, Math.PI/2, 20, 10);
		Bullet bullet1 = new Bullet(500, 500, 10, 10, 1);
		Bullet bullet2 = new Bullet(700, 500, 10, 10, 1);
		world.addEntity(ship1);	
		world.addEntity(ship2);
		ship1.loadBullet(bullet1);
		ship1.fireBullet(bullet1);
		ship2.loadBullet(bullet2);
		ship2.fireBullet(bullet2);
		ship1.setPosition(700, 700);
		world.evolve(2, null);
		assertTrue(bullet1.isTerminated());
		assertTrue(bullet2.isTerminated());
		assertTrue(ship1.isTerminated());
		assertTrue(ship2.isTerminated());
	}
	
	@Test
	public void testWorldEvolve2CollisionsSameTimeBulletBullet() {
		World world = new World(1000, 1000);
		Bullet bullet1 = new Bullet(500, 500, 250, 0, 1);
		Bullet bullet2 = new Bullet(700, 500, 0, 0, 1);
		Bullet bullet3 = new Bullet(500, 700, 250, 0, 1);
		Bullet bullet4 = new Bullet(700, 700, 0, 0, 1);
		world.addEntity(bullet1);
		world.addEntity(bullet2);
		world.addEntity(bullet3);
		world.addEntity(bullet4);
		world.evolve(2, null);
		assertTrue(bullet1.isTerminated());
		assertTrue(bullet2.isTerminated());
		assertTrue(bullet3.isTerminated());
		assertTrue(bullet4.isTerminated());
	}
	
	@Test
	public void testWorldEvolve2CollisionsSameTimeBulletBoundary() {
		World world = new World(1000, 1000);
		Bullet bullet1 = new Bullet(900, 100, 250, 0, 1);
		Bullet bullet2 = new Bullet(100, 100, -250, 0, 1);
		world.addEntity(bullet1);
		world.addEntity(bullet2);
		assertEquals(0, bullet1.getBoundaryCollisionCounter(), EPSILON);
		assertEquals(0, bullet2.getBoundaryCollisionCounter(), EPSILON);
		world.evolve(2, null);
		assertEquals(1, bullet1.getBoundaryCollisionCounter(), EPSILON);
		assertEquals(1, bullet2.getBoundaryCollisionCounter(), EPSILON);}
	
	@Test
	public void testWorldEvolve2CollisionsSameTimeShipBoundary() {
		World world = new World(1000, 1000);
		Ship ship1 = new Ship(950, 500, 20, 0, 0, 20, 10);
		Ship ship2 = new Ship(950, 400, 20, 0, 0, 20, 10);
		world.addEntity(ship1);	
		world.addEntity(ship2);
		world.evolve(2, null);
		assertEquals(-20, ship1.getVelocityX(), EPSILON);
		assertEquals(-20, ship2.getVelocityX(), EPSILON);
	}
	
	@Test
	public void testWorldEvolveCollisionOnIntTime() {
		World world = new World(1000, 1000);
		Ship ship = new Ship(950, 500, 30, 10, 0, 20, 10);
		world.addEntity(ship);	
		world.evolve(1, null);
		world.evolve(1, null);
		assertEquals(-30, ship.getVelocityX(), EPSILON);
		assertEquals(10, ship.getVelocityY(), EPSILON);
	}
	
	@Test
	public void testWorldEvolveRicochetShipSalamanderBug() {
		World world1 = new World(1000, 1000);
		Ship ship1 = new Ship(100, 100, 100, 50, 0, 10, 10);
		World world2 = new World(1000, 1000);
		Ship ship2 = new Ship(100, 100, 100, 50, 0, 10, 10);
		world1.addEntity(ship1);
		world2.addEntity(ship2);
		for (int i = 0; i < 100; i++) {
			world1.evolve(10, null);
			if (ship1.isTerminated()) {
				for (int j = 0; j < (i - 1); j++) {
					;	// debug here for salamander => fixed
					world2.evolve(10, null);
				}
				world2.evolve(10, null);
			}
		}
		assertFalse(ship1.isTerminated());
	}
	
	@Test
	  public void testGetAllEntitiesMinorPlanet() throws ModelException {
	    
	    World world = new World(2000, 2000);
	    Ship ship1 = new Ship(100, 100, 0, 0, 0, 20, 1.0E20);
	    for (int i = 1; i < 10; i++) {
	      Bullet bulletToLoad = new Bullet(100, 100, 0, 0, 10);
	      ship1.loadBullet(bulletToLoad);
	    }
	    world.addEntity(ship1);
	    Asteroid asteroid1 = new Asteroid(200, 200, 0, 0, 20);
	    world.addEntity(asteroid1);
	    Planetoid planetoid1 = new Planetoid(250, 250, 0, 0, 20, 0);
	    world.addEntity(planetoid1);
	    
	    Set<? extends Object> allEntities = world.getAllEntitiesSpecific(MinorPlanet.class);
	    assertEquals(allEntities.size(), 2);
	  }
	
	@Test
	  public void testGetAllEntitiesMinorPlanetNoPlanets() throws ModelException {
	    
	    World world = new World(2000, 2000);
	    Ship ship1 = new Ship(100, 100, 0, 0, 0, 20, 1.0E20);
	    for (int i = 1; i < 10; i++) {
	      Bullet bulletToLoad = new Bullet(100, 100, 0, 0, 10);
	      ship1.loadBullet(bulletToLoad);
	    }
	    world.addEntity(ship1);
	    Set<? extends Object> allEntities = world.getAllEntitiesSpecific(MinorPlanet.class);
	    assertEquals(allEntities.size(), 0);
	  }
	
	@Test
	  public void testGetAllEntitiesMinorPlanetAllPlanets() throws ModelException {
	    
	    World world = new World(2000, 2000);
	    Asteroid asteroid1 = new Asteroid(200, 200, 0, 0, 20);
	    world.addEntity(asteroid1);
	    Planetoid planetoid1 = new Planetoid(250, 250, 0, 0, 20, 0);
	    world.addEntity(planetoid1);
	    Planetoid planetoid2 = new Planetoid(500, 250, 0, 0, 20, 0);
	    world.addEntity(planetoid2);
	    Set<? extends Entity> allEntities = world.getAllEntitiesSpecific(MinorPlanet.class);
	    assertEquals(allEntities.size(), 3);
	    assertEquals(allEntities, world.getAllEntities());
	  }
	
	
}
