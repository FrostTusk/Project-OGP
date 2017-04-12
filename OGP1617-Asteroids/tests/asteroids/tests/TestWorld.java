package asteroids.tests;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import asteroids.helper.Entity;
import asteroids.model.Bullet;
import asteroids.model.Ship;
import asteroids.model.World;
import asteroids.util.ModelException;

public class TestWorld {
	
	private static final double EPSILON = 0.0001;

	

			/*
			 * |--------------------------------------------|
			 * | 1. The next test test the Initialization.	|
			 * |--------------------------------------------| 
			 */	
	
	
	
	@Test
	public void testCreateWorld() throws ModelException {
		World world = new World(10, 10);
		assertNotNull(world);
	}
	
	
	// TODO when entities are finished, check this test.
	@Test
	public void testCreateWorldTerminateWorld() throws ModelException {
		World world = new World(-15, -10);
		assertNotNull(world);
		world.terminate();
	}
	
	@Test
	public void testTerminateWorld() throws ModelException {
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
	public void testCreateWorldSizeGeneric() throws ModelException {
		World world = new World(15, 10);
		assertNotNull(world);
		assertEquals(15, world.getWidth(), EPSILON);
		assertEquals(10, world.getHeight(), EPSILON);
	}
	
	
	@Test
	public void testCreateWorldSizeWidthZero() throws ModelException {
		World world = new World(0, 0);
		assertNotNull(world);
		assertEquals(0, world.getWidth(), EPSILON);
		assertEquals(0, world.getHeight(), EPSILON);
	}
	
	@Test
	public void testCreateWorldSizeWidthEdgeOverflow() throws ModelException {
		World world = new World(Double.MAX_VALUE, 10);
		assertNotNull(world);
		assertEquals(Double.MAX_VALUE, world.getWidth(), EPSILON);
		assertEquals(10, world.getHeight(), EPSILON);
	}
	
	@Test
	public void testCreateWorldSizeHeightEdgeOverflow() throws ModelException {
		World world = new World(15, Double.MAX_VALUE);
		assertNotNull(world);
		assertEquals(15, world.getWidth(), EPSILON);
		assertEquals(Double.MAX_VALUE, world.getHeight(), EPSILON);
	}
	
	
	@Test
	public void testCreateWorldSizeWidthHeightEdgeOverflow() throws ModelException {
		World world = new World(Double.MAX_VALUE, Double.MAX_VALUE);
		assertNotNull(world);
		assertEquals(Double.MAX_VALUE, world.getWidth(), EPSILON);
		assertEquals(Double.MAX_VALUE, world.getHeight(), EPSILON);
	}
	
	
	@Test
	public void testCreateWorldSizeWidthOverflow() throws ModelException {
		World world = new World(Double.MAX_VALUE + 50, 10);
		assertNotNull(world);
		assertEquals(Double.MAX_VALUE, world.getWidth(), EPSILON);
		assertEquals(10, world.getHeight(), EPSILON);
	}
	
	@Test
	public void testCreateWorldSizeHeightOverflow() throws ModelException {
		World world = new World(15, Double.MAX_VALUE + 50);
		assertNotNull(world);
		assertEquals(15, world.getWidth(), EPSILON);
		assertEquals(Double.MAX_VALUE, world.getHeight(), EPSILON);
	}
	
	@Test
	public void testCreateWorldSizeWidthHeightOverflow() throws ModelException {
		World world = new World(Double.MAX_VALUE, Double.MAX_VALUE);
		assertNotNull(world);
		assertEquals(Double.MAX_VALUE, world.getWidth(), EPSILON);
		assertEquals(Double.MAX_VALUE, world.getHeight(), EPSILON);
	}
	
	@Test
	public void testCreateWorldSizeWidthNeg() throws ModelException {
		World world = new World(-15, 10);
		assertNotNull(world);
		assertEquals(0, world.getWidth(), EPSILON);
		assertEquals(10, world.getHeight(), EPSILON);
	}
	
	@Test
	public void testCreateWorldSizeHeightNeg() throws ModelException {
		World world = new World(15, -10);
		assertNotNull(world);
		assertEquals(15, world.getWidth(), EPSILON);
		assertEquals(0, world.getHeight(), EPSILON);
	}
	
	@Test
	public void testCreateWorldSizeWidthHeightNeg() throws ModelException {
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
	public void testWorldIsValidSizeT() throws ModelException {
		assertTrue(World.isValidSize(0, 0));
		assertTrue(World.isValidSize(100, 100));
		assertTrue(World.isValidSize(100, 50));
	}
	
	@Test
	public void testWorldIsValidSizeF() throws ModelException {
		assertFalse(World.isValidSize(-10, -10));
		assertFalse(World.isValidSize(10, -10));
		assertFalse(World.isValidSize(-10, 10));
		assertFalse(World.isValidSize(Double.NaN, 10));
		assertFalse(World.isValidSize(Double.NaN, Double.NaN));
		assertFalse(World.isValidSize(10, Double.NaN));
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
	public void testWorldCanContainEntityGeneric() throws ModelException {
		World world = new World(1000, 1000);
		Ship ship = new Ship(100, 100, 10, -10, Math.PI, 20, 10);
		Bullet bullet = new Bullet(1, 1, 1, 1, 1);
		assertTrue(world.canHaveAsEntity(ship));
		assertTrue(world.canHaveAsEntity(bullet));
	}
	
	@Test
	public void testWorldCanContainEntityBoundariesF() throws ModelException {
		World world = new World(1000, 1000);
		Ship ship = new Ship(1000, 1000, 10, -10, Math.PI, 20, 10);
		Bullet bullet = new Bullet(0, 0, 1, 1, 1);
		assertFalse(world.canHaveAsEntity(ship));
		assertFalse(world.canHaveAsEntity(bullet));
	}
	
	@Test
	public void testWorldCanContainEntityEntityInOtherWorld() throws ModelException {
		World world1 = new World(1000, 1000);
		World world2 = new World(1000, 1000);	
		Ship ship = new Ship(500, 500, 10, -10, Math.PI, 20, 10);
		Bullet bullet = new Bullet(20, 20, 1, 1, 1);
		world2.addEntity(ship);
		world2.addEntity(bullet);
		ship.setWorld(world2);
		bullet.setWorld(world2);
		assertFalse(world1.canHaveAsEntity(ship));
		assertFalse(world1.canHaveAsEntity(bullet));
	}
	
	@Test
	public void testWorldCanContainEntityBulletOfShip() throws ModelException {
		World world = new World(1000, 1000);
		Ship ship = new Ship(500, 500, 10, -10, Math.PI, 20, 10);
		Bullet bullet = new Bullet(20, 20, 1, 1, 1);
		ship.loadBullet(bullet);
		assertTrue(world.canHaveAsEntity(ship));
		assertFalse(world.canHaveAsEntity(bullet));
	}
	
	@Test
	public void testWorldContainsEntityT() throws ModelException {
		World world = new World(1000, 1000);
		Ship ship = new Ship(500, 500, 10, -10, Math.PI, 20, 10);
		Bullet bullet = new Bullet(20, 20, 1, 1, 1);
		world.addEntity(ship);
		world.addEntity(bullet);
		assertTrue(world.containsEntity(ship));
		assertTrue(world.containsEntity(bullet));
	}
	
	@Test
	public void testWorldContainsEntityF() throws ModelException {
		World world = new World(1000, 1000);
		Ship ship = new Ship(500, 500, 10, -10, Math.PI, 20, 10);
		Bullet bullet = new Bullet(20, 20, 1, 1, 1);
		assertFalse(world.containsEntity(ship));
		assertFalse(world.containsEntity(bullet));
	}
	
	@Test
	public void testWorldAddEntityAble() throws ModelException {
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
	public void testWorldRemoveEntityGeneric() throws ModelException {
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
	public void testWorldEvolveNoCollisions() throws IllegalArgumentException {
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
	public void testWorldGetFirstCollisionPosition() throws ModelException {
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
	public void testWorldGetFirstCollisionTime() throws ModelException {
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
	public void testWorldGetFirstCollisionTimeNoCollision() throws ModelException {
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
	public void testWorldGetFirstCollisionEntitiesNoCollision() throws ModelException {
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
	public void testWorldGetFirstCollisionEntitiesGeneric() throws ModelException {
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
	public void testWorldGetFirstCollisionEntitiesNoEntities() throws ModelException {
		World world = new World(1000, 1000);
		world.getFirstCollisionEntities();
	}
	
	@Test
	public void testWorldGetAllEntitiesGeneric() throws ModelException {
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
	public void testWorldGetAllEntitiesAllShips() throws ModelException {
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
	public void testWorldGetAllEntitiesAllBullets() throws ModelException {
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
	public void testWorldGetAllEntitiesNoEntities() throws ModelException {
		World world = new World(1000, 1000);
		Set<Entity> entities = world.getAllEntities();
		assertEquals(0, entities.size(), EPSILON);
	}
	
	
	
}
