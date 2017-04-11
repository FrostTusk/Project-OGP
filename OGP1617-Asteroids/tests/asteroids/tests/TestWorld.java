package asteroids.tests;

import static org.junit.Assert.*;

import org.junit.Test;

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
	public void testWorldCanContainEntityT() throws ModelException {
		World world = new World(1000, 1000);
		Ship ship = new Ship(100, 100, 10, -10, Math.PI, 20, 10);
		Bullet bullet = new Bullet(1, 1, 1, 1, 1);
		assertTrue(world.canHaveAsEntity(ship));
		assertTrue(world.canHaveAsEntity(bullet));
	}
	
	@Test
	public void testWorldCanContainEntityF() throws ModelException {
		World world = new World(1000, 1000);
		Ship ship = new Ship(1000, 1000, 10, -10, Math.PI, 20, 10);
		Bullet bullet = new Bullet(0, 0, 1, 1, 1);
		assertFalse(world.canHaveAsEntity(ship));
		assertFalse(world.canHaveAsEntity(bullet));
	}
	
	@Test
	public void testWorldAddEntityAble() throws ModelException {
		World world = new World(1000, 1000);
		Ship ship = new Ship(100, 100, 10, -10, Math.PI, 20, 10);
		Bullet bullet = new Bullet(1, 1, 1, 1, 1);
		world.addEntity(ship);
		world.addEntity(bullet);
		assertTrue(world.containsEntity(ship));
		assertTrue(world.containsEntity(bullet));
	}
	
	@Test
	public void testWorldAddEntityUnable() throws ModelException {
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
	public void testWorldRemoveEntityNotInWorld() throws ModelException {
		World world = new World(1000, 1000);
		Ship ship = new Ship(100, 100, 10, -10, Math.PI, 20, 10);
		Bullet bullet = new Bullet(1, 1, 1, 1, 1);
		world.removeEntity(ship);
		world.removeEntity(bullet);
		assertFalse(world.containsEntity(ship));
		assertFalse(world.containsEntity(bullet));
	}
	
	@Test
	public void testWorldEvolveNoCollisions() throws ModelException {
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
	public void testWorldEvolveCollisions() throws ModelException {
		World world = new World(1000, 1000);
		Ship ship1 = new Ship(100, 100, 0, 0, Math.PI, 20, 10);
		Ship ship2 = new Ship(130, 130, -10, -10, Math.PI, 20, 10);
		world.addEntity(ship1);
		ship1.setWorld(world);
		world.addEntity(ship2);
		ship2.setWorld(world);
		world.evolve(2);
	}
	
	
	@Test
	public void testWorldRemoveEntityGeneric1() throws ModelException {
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
	public void testWorldRemoveEntityNotInWorld1() throws ModelException {
		World world = new World(1000, 1000);
		Ship ship = new Ship(100, 100, 10, -10, Math.PI, 20, 10);
		Bullet bullet = new Bullet(1, 1, 1, 1, 1);
		world.removeEntity(ship);
		world.removeEntity(bullet);
		assertFalse(world.containsEntity(ship));
		assertFalse(world.containsEntity(bullet));
	}
	
	@Test
	public void testWorldEvolveNoCollisions1() throws ModelException {
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
	public void testWorldEvolveCollisions1() throws ModelException {
		World world = new World(1000, 1000);
		Ship ship1 = new Ship(100, 100, 0, 0, Math.PI, 20, 10);
		Ship ship2 = new Ship(130, 130, -10, -10, Math.PI, 20, 10);
		world.addEntity(ship1);
		ship1.setWorld(world);
		world.addEntity(ship2);
		ship2.setWorld(world);
		world.evolve(2);
	}
	
}
