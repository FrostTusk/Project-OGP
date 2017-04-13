package asteroids.tests;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import asteroids.helper.Entity;
import asteroids.helper.Position;
import asteroids.model.Bullet;
import asteroids.model.Ship;
import asteroids.model.World;


public class TestWorld {
	
	private static final double EPSILON = 0.0001;

	

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
	
	
	// TODO when entities are finished, check this test.
	@Test
	public void testCreateWorldTerminateWorld() {
		World world = new World(-15, -10);
		assertNotNull(world);
		world.terminate();
	}
	
	@Test
	public void testTerminateWorld() {
		World world = new World(-15, -10);
		assertNotNull(world);
		world.terminate();
		assertTrue(world.isTerminated());
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
	
	
/*	The next test crashes the Test Suite, this is because it changes
 *	the upper bound (which is a static variable). All the other tests
 *	create worlds with sizes above 5, which will mean they all fail.
 */
//	@Test
//	public void testCreateWorldNewUpperBound() throws ModelException {
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
	public void testWorldCanContainEntityEntityInOtherWorld() {
		World world1 = new World(1000, 1000);
		World world2 = new World(1000, 1000);	
		Ship ship = new Ship(500, 500, 10, -10, Math.PI, 20, 10);
		Bullet bullet = new Bullet(20, 20, 1, 1, 1);
		world2.addEntity(ship);
		world2.addEntity(bullet);
		ship.setWorld(world2);
		bullet.setWorld(world2);
// TODO Bad usage of canHaveAsEntity => this is more of a test if the bullet can have the world
// Technically it's the entity that can't have 2 worlds at the same time, not the world.
//		assertFalse(world1.canHaveAsEntity(ship));
//		assertFalse(world1.canHaveAsEntity(bullet));
	}
	
	@Test
	public void testWorldCanContainEntityBulletOfShip() {
		World world = new World(1000, 1000);
		Ship ship = new Ship(500, 500, 10, -10, Math.PI, 20, 10);
		Bullet bullet = new Bullet(500, 500, 1, 1, 1);
		ship.loadBullet(bullet);
		assertTrue(world.canHaveAsEntity(ship));
// TODO See last test.
//		assertFalse(world.canHaveAsEntity(bullet));
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
		Set<Ship> ships = world.getAllShips();
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
		for (Entity entity : world.getAllEntities()) {
			if (world.getEntityAtPosition(position1) != bullet1 || world.getEntityAtPosition(position2) != bullet2 || world.getEntityAtPosition(position3) != bullet3) fail();
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
		world.evolve(2);
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
		world.evolve(2);
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
		world.evolve(2);
		assertTrue(ship2.isTerminated());
		assertTrue(bullet.isTerminated());
	}
	
	@Test
	public void testWorldEvolveNoCollisions() {
		World world = new World(1000, 1000);
		Ship ship = new Ship(100, 100, 10, -10, Math.PI, 20, 10);
		Bullet bullet = new Bullet(1, 1, 10, 10, 1);
		world.addEntity(ship);
		ship.setWorld(world);
		world.addEntity(bullet);
		bullet.setWorld(world);
		world.evolve(2);
		assertEquals(120, ship.getPosition().getPositionX(), EPSILON);
		assertEquals(80, ship.getPosition().getPositionY(), EPSILON);
		assertEquals(21, bullet.getPosition().getPositionX(), EPSILON);
		assertEquals(21, bullet.getPosition().getPositionY(), EPSILON);
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
		world.evolve(20);
		assertEquals(10, ship1.getVelocityX(), EPSILON);
		assertEquals(0, ship1.getVelocityY(), EPSILON);
		assertEquals(0, ship2.getVelocityX(), EPSILON);
		assertEquals(0, ship2.getVelocityY(), EPSILON);
		world.evolve(30);
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
		world.evolve(3);
		assertEquals(-1, bullet.getVelocityX(), EPSILON);
		assertEquals(10, bullet.getVelocityY(), EPSILON);
		world.evolve(3);
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
		world.evolve(10);
		assertEquals(-90, bullet.getVelocityX(), EPSILON);
		assertEquals(0, bullet.getVelocityY(), EPSILON);
		assertEquals(1, bullet.getBoundaryCollisionCounter(), EPSILON);
		world.evolve(10);
		assertEquals(90, bullet.getVelocityX(), EPSILON);
		assertEquals(0, bullet.getVelocityY(), EPSILON);
		assertEquals(2, bullet.getBoundaryCollisionCounter(), EPSILON);
		world.evolve(10);
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
		world.evolve(3);
		assertEquals(10, bullet.getVelocityX(), EPSILON);
		assertEquals(-1, bullet.getVelocityY(), EPSILON);
		world.evolve(3);
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
		world.evolve(7);
		assertEquals(10, bullet.getVelocityX(), EPSILON);
		assertEquals(1, bullet.getVelocityY(), EPSILON);
		world.evolve(10);
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
		world.evolve(3);
		assertEquals(-1, bullet.getVelocityX(), EPSILON);
		assertEquals(10, bullet.getVelocityY(), EPSILON);
		world.evolve(3);
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
		world.evolve(4);
		assertEquals(1, ship.getVelocityX(), EPSILON);
		assertEquals(10, ship.getVelocityY(), EPSILON);
		world.evolve(3);
		assertEquals(1, ship.getVelocityX(), EPSILON);
		assertEquals(10, ship.getVelocityY(), EPSILON);
		world.evolve(2);
		assertEquals(1, ship.getVelocityX(), EPSILON);
		assertEquals(-10, ship.getVelocityY(), EPSILON);
	}
	
	@Test
	public void testWorldEvolveCollisionShipStartsAgainstBoundaryMovesToBoundary() {
		World world = new World(1000, 1000);
		Ship ship = new Ship(800, 900, 0, 10, Math.PI, 100, 10);
		world.addEntity(ship);
		ship.setWorld(world);
		assertEquals(0, ship.getVelocityX(), EPSILON);
		assertEquals(10, ship.getVelocityY(), EPSILON);
		world.evolve(1);
		assertEquals(0, ship.getVelocityX(), EPSILON);
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
		world.evolve(1);
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
		world.evolve(2);
		assertEquals(1, ship.getBulletsCount(), EPSILON);
		assertTrue(bullet.getShip() == ship);
	}
	
	
}