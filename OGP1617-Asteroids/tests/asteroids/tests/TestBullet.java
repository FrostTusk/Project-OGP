package asteroids.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asteroids.facade.Facade;
import asteroids.helper.Position;
import asteroids.model.Bullet;
import asteroids.model.Ship;
import asteroids.model.World;
import asteroids.part2.facade.IFacade;
import asteroids.util.ModelException;

/* 
 * // TODO Do NOT get rid of tests without telling me!
 * // Not even irrelevant ones. 
 * Tests Index:
 * 1. Tests for Initialization
 * 2. Tests for Position
 * 3. Tests for Speed
 * 4. Tests for Radius
 * 5. Tests for Mass
 * 6. Test for Worlds
 * 7. Tests for Resolving Collisions
 */

public class TestBullet {

	private static final double EPSILON = 0.0001;

	IFacade facade;

	@Before
	public void setUp() {
		facade = new Facade();
	}
	
	
	
			/*
			 * |--------------------------------------------|
			 * | 1. The next test test the Initialization.	|
			 * |--------------------------------------------| 
			 */	
	
	
	
	@Test
	public void testCreateBullet() {
		Bullet bullet = new Bullet(1, 1, 1, 1, 1);
		assertNotNull(bullet);
	}
	
	
	
			/*
			 * |----------------------------------------|
			 * | 2. The next tests test the Position.	|
			 * |----------------------------------------| 
			 */	

	
	
// TODO Check this test.	
//	@Test(expected = ModelException.class)
//	public void testCreateBulletPosBulletIsNull() throws ModelException {
//		Bullet bullet = null;
//		bullet.getPosition();
//	}
	
	
	@Test
	public void testCreatBulletPosGeneric() {
		Bullet bullet1 = new Bullet(100, 200, 10, -10, 20);
		Position position1 = bullet1.getPosition();
		assertEquals(100, position1.getPositionX(), EPSILON);
		assertEquals(200, position1.getPositionY(), EPSILON);
		
		Bullet bullet2 = new Bullet(100, -200, 10, -10, 20);
		Position position2 = bullet2.getPosition();
		assertEquals(100, position2.getPositionX(), EPSILON);
		assertEquals(-200, position2.getPositionY(), EPSILON);
		
		Bullet bullet3 = new Bullet(-100, 200, 10, -10, 20);
		Position position3 = bullet3.getPosition();
		assertEquals(-100, position3.getPositionX(), EPSILON);
		assertEquals(200, position3.getPositionY(), EPSILON);
		
		Bullet bullet4 = new Bullet(-100, -200, 10, -10, 20);
		Position position4 = bullet4.getPosition();
		assertEquals(-100, position4.getPositionX(), EPSILON);
		assertEquals(-200, position4.getPositionY(), EPSILON);
		
		Bullet bullet5 = new Bullet(0, 0, 10, -10, 20);
		Position position5 = bullet5.getPosition();
		assertEquals(0, position5.getPositionX(), EPSILON);
		assertEquals(0, position5.getPositionY(), EPSILON);
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void testCreateBulletXIsPosInfinity() {
		new Bullet(Double.POSITIVE_INFINITY, 200, 10, -10, 20);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCreateBulletXIsNegInfinity() {
		new Bullet(Double.NEGATIVE_INFINITY, 200, 10, -10, 20);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCreateBulletXIsNan() {
		new Bullet(Double.NaN, 200, 10, -10, 20);
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void testCreateBulletYIsPosInfinity() {
		new Bullet(200, Double.POSITIVE_INFINITY, 10, -10, 20);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCreateBulletYIsNegInfinity() {
		new Bullet(200, Double.NEGATIVE_INFINITY, 10, -10, 20);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCreateBulletYIsNan() {
		new Bullet(200, Double.NaN, 10, -10, 20);
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void testCreateBulletXYIsPosInfinity() {
		new Bullet(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, 10, -10, 20);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCreateBulletXYIsNegInfinity() {
		new Bullet(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, 10, -10, 20);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCreateBulletXYIsNaN() {
		new Bullet(Double.NaN, Double.NaN, 10, -10, 20);
	}

	
	
			/*
			 * |------------------------------------|
			 * | 3. The next tests test the Speed.	|
			 * |------------------------------------| 
		  	 */	
	
	

// TODO Check this test.
//	@Test(expected = ModelException.class)
//	public void testCreateBulletSpeedBulletIsNull() throws ModelException {
//		Bullet bullet = null;
//		bullet.getSpeed();
//	}
	
	
	@Test
	public void testCreatBulletVelGeneric() {
		Bullet bullet1 = new Bullet(100, 200, 10, 20, 20);
		assertEquals(10, bullet1.getVelocityX(), EPSILON);
		assertEquals(20, bullet1.getVelocityY(), EPSILON);
		
		Bullet bullet2 = new Bullet(100, 200, -10, 20, 20);
		assertEquals(-10, bullet2.getVelocityX(), EPSILON);
		assertEquals(20, bullet2.getVelocityY(), EPSILON);
		
		Bullet bullet3 = new Bullet(100, 200, 10, -20, 20);
		assertEquals(10, bullet3.getVelocityX(), EPSILON);
		assertEquals(-20, bullet3.getVelocityY(), EPSILON);
		
		Bullet bullet4 = new Bullet(100, 200, -10, -20, 20);
		assertEquals(-10, bullet4.getVelocityX(), EPSILON);
		assertEquals(-20, bullet4.getVelocityY(), EPSILON);
		
		Bullet bullet5 = new Bullet(100, 200, 0, 0, 20);
		assertEquals(0, bullet5.getVelocityX(), EPSILON);
		assertEquals(0, bullet5.getVelocityY(), EPSILON);
	}
	
	
	@Test
	public void testCreateBulletVelOverflow() {
		Bullet bullet = new Bullet(200, 200, 500000, 500000, 20);
		assertEquals(0, bullet.getVelocityX(), EPSILON);
		assertEquals(0, bullet.getVelocityY(), EPSILON);
	}
	
	
	@Test
	public void testCreateBulletVXIsPosInfinity() {
		Bullet bullet = new Bullet(200, 200, Double.POSITIVE_INFINITY, 10, 20);
		assertEquals(0, bullet.getVelocityX(), EPSILON);
		assertEquals(0, bullet.getVelocityY(), EPSILON);
	}
	
	@Test
	public void testCreateBulletVXIsNegInfinity() {
		Bullet bullet = new Bullet(200, 200, Double.NEGATIVE_INFINITY, 10, 20);
		assertEquals(0, bullet.getVelocityX(), EPSILON);
		assertEquals(0, bullet.getVelocityY(), EPSILON);
	}
	
	@Test
	public void testCreateBulletVXIsNan() {
		Bullet bullet = new Bullet(200, 200, Double.NaN, 10, 20);
		assertEquals(0, bullet.getVelocityX(), EPSILON);
		assertEquals(0, bullet.getVelocityY(), EPSILON);
	}
	
	
	@Test
	public void testCreateBulletVYIsPosInfinity() {
		Bullet bullet = new Bullet(200, 200, 10, Double.POSITIVE_INFINITY, 20);
		assertEquals(0, bullet.getVelocityX(), EPSILON);
		assertEquals(0, bullet.getVelocityY(), EPSILON);
	}
	
	@Test
	public void testCreateBulletVYIsNegInfinity() {
		Bullet bullet = new Bullet(200, 200, 10, Double.NEGATIVE_INFINITY, 20);
		assertEquals(0, bullet.getVelocityX(), EPSILON);
		assertEquals(0, bullet.getVelocityY(), EPSILON);
	}
	
	@Test
	public void testCreateBulletVYIsNan() {
		Bullet bullet = new Bullet(200, 200, 10, Double.NaN, 20);
		assertEquals(0, bullet.getVelocityX(), EPSILON);
		assertEquals(0, bullet.getVelocityY(), EPSILON);
	}
	
	
	@Test
	public void testCreateBulletVXYIsPosInfinity() {
		Bullet bullet = new Bullet(200, 200, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, 20);
		assertEquals(0, bullet.getVelocityX(), EPSILON);
		assertEquals(0, bullet.getVelocityY(), EPSILON);
	}
	
	@Test
	public void testCreateBulletVXYIsNegInfinity() {
		Bullet bullet = new Bullet(200, 200, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, 20);
		assertEquals(0, bullet.getVelocityX(), EPSILON);
		assertEquals(0, bullet.getVelocityY(), EPSILON);
	}
	
	@Test
	public void testCreateBulletVXYIsNan() {
		Bullet bullet = new Bullet(200, 200, Double.NaN, Double.NaN, 20);
		assertEquals(0, bullet.getVelocityX(), EPSILON);
		assertEquals(0, bullet.getVelocityY(), EPSILON);
	}
	
	
	
			/*
			 * |------------------------------------|
			 * | 4. The next tests test the Radius.	|
			 * |------------------------------------| 
			 */	
		
	
		
// TODO Check this test.		
//	@Test(expected = ModelException.class)
//	public void testCreateBulletRadiusBulletIsNull() throws ModelException {
//		Bullet bullet = null;
//		bullet.getRadius();
//	}
	
	
	@Test
	public void testCreateBulletRadiusGeneric() {
		Bullet bullet = new Bullet(100, 200, 10, -10, 50);
		assertNotNull(bullet);
		assertEquals(50, bullet.getRadius(), EPSILON);
		assertEquals(1, bullet.getMinRadius(), EPSILON);
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void testCreateBulletRadiusUnderflow() {
		new Bullet(100, 200, 10, -10, 0.5);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCreateBulletRadiusNegative() {
		new Bullet(100, 200, 10, -10, -50);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCreateBulletRadiusZero() {
		new Bullet(100, 200, 10, -10, 0);
	}
	

	@Test(expected = IllegalArgumentException.class)
	public void testCreateBulletRadiusIsPosInfinity() {
		new Bullet(100, 200, 10, -10, Double.POSITIVE_INFINITY);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCreateBulletRadiusIsNegInfinity() {
		new Bullet(100, 200, 10, -10, Double.NEGATIVE_INFINITY);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCreateBulletRadiusIsNan() {
		new Bullet(100, 200, 10, -10, Double.NaN);
	}
	
	
	
			/*
			 * |------------------------------------|
			 * | 5. The next tests test the Mass.	|
			 * |------------------------------------| 
			 */	
	
	
	
	@Test
	public void testBulletSetMassGeneric() {
		Bullet bullet = new Bullet(100, 200, 10, -10, 50);
		assertNotNull(bullet);
		bullet.setMass();
		assertEquals((4/3) * Math.PI * Math.pow(50, 3) * (bullet.getDensity()), bullet.getMass(), EPSILON);
	}
	
	@Test
	public void testBulletGetMassWithoutSetMass() {
		Bullet bullet = new Bullet(100, 200, 10, -10, 50);
		assertNotNull(bullet);
		bullet.getMass();
		assertEquals((4/3) * Math.PI * Math.pow(50, 3) * (bullet.getDensity()), bullet.getMass(), EPSILON);
	}
	
	@Test (expected = NullPointerException.class)
	public void testBulletSetMassBulletNull() {
		Bullet bullet = null;
		bullet.setMass();
		assertEquals((4/3) * Math.PI * Math.pow(50, 3) * bullet.getDensity(), bullet.getMass(), EPSILON);
	}
	
	@Test (expected = NullPointerException.class)
	public void testBulletGetMassBulletNull() {
		Bullet bullet = null;
		bullet.getMass();
		assertEquals((4/3) * Math.PI * Math.pow(50, 3) * bullet.getDensity(), bullet.getMass(), EPSILON);
	}
	
	
			/*
			 * |--------------------------------------------------------|
			 * | 6. The next tests test the interaction with worlds.	|
		 	 * |--------------------------------------------------------| 
		 	 */	

	
	@Test // TODO This is actually irrelevant
	public void testCanHaveAsWorldTrue() {
		Bullet bullet1 = new Bullet(1, 1, 1, 1, 1);
		Bullet bullet2 = new Bullet(10, 10, 1, 1, 1);
		World world = new World(100, 100);
		assertTrue(bullet1.canHaveAsWorld(world));
		assertTrue(bullet2.canHaveAsWorld(world));
	}
	
	@Test // TODO This is actually irrelevant
	public void testCanHaveAsWorldFalse() {
		Bullet bullet1 = new Bullet(100, 1, 1, 1, 1);
		Bullet bullet2 = new Bullet(0, 1, 1, 1, 1);
		Bullet bullet3 = new Bullet(1, 100, 1, 1, 1);
		Bullet bullet4 = new Bullet(1, 0, 1, 1, 1);
		World world = new World(100, 100);
		assertFalse(bullet1.canHaveAsWorld(world));
		assertFalse(bullet2.canHaveAsWorld(world));
		assertFalse(bullet3.canHaveAsWorld(world));
		assertFalse(bullet4.canHaveAsWorld(world));
	}
	
	
	@Test
	public void testSetWorld() {
		Bullet bullet = new Bullet(1, 1, 1, 1, 1);
		World world = new World(100, 100);
		bullet.setWorld(world);
		assertNotNull(bullet.getWorld());
	}
	
	@Test
	public void testDeSetWorld() {
		Bullet bullet = new Bullet(1, 1, 1, 1, 1);
		World world = new World(100, 100);
		bullet.setWorld(world);
		bullet.setWorld(null);
		assertNull(bullet.getWorld());
	}
	

			/*
			 * |----------------------------------------------------|
			 * | 7. The next tests test the resolving collisions.	|
		 	 * |----------------------------------------------------| 
		 	 */	
	
	
	
	@Test
	public void testCollideWithWorldBulletCanBounce() {
		Bullet bullet = new Bullet(50, 50, 0, 0, 20);
		World world = new World(100, 100);
		double counter = bullet.getBoundaryCollisionCounter();
		bullet.setWorld(world);
		bullet.resolveCollision(world);
		assertTrue(counter + 1 == bullet.getBoundaryCollisionCounter());
	}
	
// TODO Fix this test Mathijs.
//	@Test
//	public void testCollideWithWorldBulletCanNotBounce() throws ModelException {
//		Bullet bullet = new Bullet(50, 50, 0, 0, 20);
//		World world = new World(100, 100);
//		double counter = bullet.getBoundaryCollisionCounter();
//		bullet.setWorld(world);
//		while (counter < bullet.getBoundaryCollisionMax()) {
//			bullet.resolveCollision(world);
//			counter += 1;
//		}
//		assertTrue(bullet.isTerminated());
//	}
	
	
	@Test
	public void testCollideWithWorldVelocityXChanged() {
		Bullet bullet = new Bullet(80, 50, 10, 10, 20);
		World world = new World(100, 100);
		double counter = bullet.getBoundaryCollisionCounter();
		world.addEntity(bullet);
		bullet.setWorld(world);
		bullet.resolveCollision(world);
		assertTrue(counter + 1 == bullet.getBoundaryCollisionCounter());
		assertTrue(bullet.getVelocityX() == -10);
		assertTrue(bullet.getVelocityY() == 10);
	}
	
	//TODO
	@Test
	public void testCollideWithWorldVelocityYChanged() {
		Bullet bullet = new Bullet(50, 80, 10, 10, 20);
		World world = new World(100, 100);
		double counter = bullet.getBoundaryCollisionCounter();
		world.addEntity(bullet);
		bullet.setWorld(world);
		bullet.resolveCollision(world);
		assertTrue(counter + 1 == bullet.getBoundaryCollisionCounter());
		assertTrue(bullet.getVelocityX() == 10);
		assertTrue(bullet.getVelocityY() == -10);
	}
	
	
	// TODO
	@Test
	public void testCollideWithWorldCorner() {
		Bullet bullet = new Bullet(80, 80, 10, 10, 20);
		World world = new World(100, 100);
		double counter = bullet.getBoundaryCollisionCounter();
		world.addEntity(bullet);
		bullet.setWorld(world);
		bullet.resolveCollision(world);
		assertTrue(counter + 1 == bullet.getBoundaryCollisionCounter());
		// TODO Separate case for corners.
		assertTrue(bullet.getVelocityX() == -10);
		assertTrue(bullet.getVelocityY() == -10);
	}
	
	
	
	@Test
	public void testCollideWithShipSource() {
		Bullet bullet = new Bullet(100, 100, 0, 0, 20);	
		Ship ship = new Ship(100, 100, 10, -10, Math.PI, 20, 10);
		World world = new World(1000, 1000);
		ship.setWorld(world);
		bullet.setShip(ship);
		bullet.setSource(ship);
		bullet.setShip(null);
		double counter = ship.getBulletsCount();
		bullet.resolveCollisionShip(ship);	
		assertTrue(counter + 1 == ship.getBulletsCount());
	}
	
	@Test
	public void testCollideWithShipNotSource() {
		Bullet bullet = new Bullet(200, 200, 0, 0, 20);	
		Ship ship1 = new Ship(100, 100, 10, -10, Math.PI, 20, 10);
		Ship ship2 = new Ship(200, 200, 10, -10, Math.PI, 20, 10);
		World world = new World(1000, 1000);
		world.addEntity(ship1);
		world.addEntity(ship2);
		bullet.setShip(ship1);
		bullet.setSource(ship1);
		bullet.setShip(null);
		bullet.resolveCollisionShip(ship2);	
		assertTrue(ship2.isTerminated());
		assertTrue(bullet.isTerminated());
	}
	
	@Test
	public void testCollideWithBullet() {
		Bullet bullet1 = new Bullet(100, 200, 0, 0, 20);
		Bullet bullet2 = new Bullet(100, 200, 10, 20, 20);
		bullet1.resolveCollisionBullet(bullet2);
		assertTrue(bullet1.isTerminated());
		assertTrue(bullet2.isTerminated());
	}
	
	/*
	 * |----------------------------------------------------|
	 * | 8. The next tests test the interaction with ships.	|
 	 * |----------------------------------------------------| 
 	 */	
	
	@Test
	public void testCanHaveAsShipT() {
		Bullet bullet = new Bullet(100, 200, 0, 0, 20);
		Ship ship = new Ship(100, 100, 10, -10, Math.PI, 20, 10);
		assertTrue(bullet.canHaveAsShip(ship));
	}
	
	@Test
	public void testCanHaveAsShipF() {
		Bullet bullet = new Bullet(100, 200, 0, 0, 20);
		Ship ship1 = new Ship(100, 100, 10, -10, Math.PI, 20, 10);
		Ship ship2 = new Ship(200, 200, 10, -10, Math.PI, 20, 10);
		bullet.setShip(ship1);
		assertFalse(bullet.canHaveAsShip(ship2));
	}
	
	@Test
	public void testCanHaveAsShipBulletInWorld() {
		World world = new World(1000, 1000);
		Bullet bullet = new Bullet(100, 200, 0, 0, 20);
		Ship ship = new Ship(100, 100, 10, -10, Math.PI, 20, 10);
		bullet.setWorld(world);
		assertFalse(bullet.canHaveAsShip(ship));
	}
	
	@Test
	public void testCanHaveAsSourceT() {
		Bullet bullet = new Bullet(100, 200, 0, 0, 20);
		Ship ship = new Ship(100, 100, 10, -10, Math.PI, 20, 10);
		bullet.setShip(ship);
		assertTrue(bullet.canHaveAsSource(ship));
	}
	
	@Test
	public void testCanHaveAsSourceBulletAlreadyFired() {
		World world = new World(1000, 1000);
		Bullet bullet = new Bullet(100, 200, 0, 0, 20);
		Ship ship1 = new Ship(100, 100, 10, -10, Math.PI, 20, 10);
		Ship ship2 = new Ship(100, 100, 10, -10, Math.PI, 20, 10);
		world.addEntity(ship1);
		ship1.fireBullet(bullet);
		assertFalse(bullet.canHaveAsSource(ship2));
	}
	
	@Test
	public void testCanHaveAsSourceBulletHasOtherShip() {
		World world = new World(1000, 1000);
		Bullet bullet = new Bullet(100, 200, 0, 0, 20);
		Ship ship1 = new Ship(100, 100, 10, -10, Math.PI, 20, 10);
		Ship ship2 = new Ship(100, 100, 10, -10, Math.PI, 20, 10);
		world.addEntity(ship1);
		bullet.setShip(ship1);
		assertFalse(bullet.canHaveAsSource(ship2));
	}
	
	@Test
	public void testResetSourceGeneric() {
		World world = new World(1000, 1000);
		Bullet bullet = new Bullet(100, 100, 0, 0, 20);
		Ship ship = new Ship(100, 100, 10, -10, Math.PI, 20, 10);
		world.addEntity(ship);
		world.addEntity(bullet);
		ship.setWorld(world);
		bullet.setWorld(world);
		bullet.setSource(ship);
		bullet.resetSource(ship);
		if (bullet.getSource() != ship) fail();
	}
	
	@Test 
	public void testSetSourceToNull() {
		Bullet bullet = new Bullet(100, 200, 0, 0, 20);
		Ship ship1 = null;
		bullet.setSource(ship1);
		if (bullet.getSource() != ship1) fail();
	}

	
}
