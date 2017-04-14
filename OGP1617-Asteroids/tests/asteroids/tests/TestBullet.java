package asteroids.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import asteroids.helper.Position;
import asteroids.model.Bullet;
import asteroids.model.Ship;
import asteroids.model.World;


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
	
	@Test
	public void testCreateTerminateBullet() {
		Bullet bullet = new Bullet(1, 1, 1, 1, 1);
		assertNotNull(bullet);
		assertFalse(bullet.isTerminated());
		bullet.terminate();
		assertTrue(bullet.isTerminated());
	}
	
//	The following test is unnecessary:	
//	@Test (expected = NullPointerException.class)
//	public void testTerminateBulletNull() {
//		Bullet bullet = null;
//		assertFalse(bullet.isTerminated());
//		bullet.terminate();
//		assertTrue(bullet.isTerminated());
//	}
	
	
	
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
	
	@Test
	public void testBulletSetPosGeneric() {
		Bullet bullet = new Bullet(100, 200, 10, -10, 20);
		Position position = bullet.getPosition();
		assertEquals(100, position.getPositionX(), EPSILON);
		assertEquals(200, position.getPositionY(), EPSILON);
		bullet.setPosition(200, 100);
		position = bullet.getPosition();
		assertEquals(200, position.getPositionX(), EPSILON);
		assertEquals(100, position.getPositionY(), EPSILON);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testBulletSetPosNaN() {
		Bullet bullet = new Bullet(100, 200, 10, -10, 20);
		Position position = bullet.getPosition();
		assertEquals(100, position.getPositionX(), EPSILON);
		assertEquals(200, position.getPositionY(), EPSILON);
		bullet.setPosition(Double.NaN, 100);
		position = bullet.getPosition();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testBulletSetPosPosInf() {
		Bullet bullet = new Bullet(100, 200, 10, -10, 20);
		Position position = bullet.getPosition();
		assertEquals(100, position.getPositionX(), EPSILON);
		assertEquals(200, position.getPositionY(), EPSILON);
		bullet.setPosition(Double.POSITIVE_INFINITY, 100);
		position = bullet.getPosition();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testBulletSetPosNegInf() {
		Bullet bullet = new Bullet(100, 200, 10, -10, 20);
		Position position = bullet.getPosition();
		assertEquals(100, position.getPositionX(), EPSILON);
		assertEquals(200, position.getPositionY(), EPSILON);
		bullet.setPosition(Double.NEGATIVE_INFINITY, 100);
		position = bullet.getPosition();
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
	
	@Test
	public void testBulletSetVelGeneric() {
		Bullet bullet = new Bullet(100, 200, 10, 20, 20);
		assertEquals(10, bullet.getVelocityX(), EPSILON);
		assertEquals(20, bullet.getVelocityY(), EPSILON);
		bullet.setVelocity(50, 55);
		assertEquals(50, bullet.getVelocityX(), EPSILON);
		assertEquals(55, bullet.getVelocityY(), EPSILON);
	}
	
	@Test
	public void testBulletSetVelNaN() {
		Bullet bullet = new Bullet(100, 200, 10, 20, 20);
		assertEquals(10, bullet.getVelocityX(), EPSILON);
		assertEquals(20, bullet.getVelocityY(), EPSILON);
		bullet.setVelocity(Double.NaN, 55);
		assertEquals(0, bullet.getVelocityX(), EPSILON);
		assertEquals(0, bullet.getVelocityY(), EPSILON);
	}
	
	@Test
	public void testBulletSetVelPosInf() {
		Bullet bullet = new Bullet(100, 200, 10, 20, 20);
		assertEquals(10, bullet.getVelocityX(), EPSILON);
		assertEquals(20, bullet.getVelocityY(), EPSILON);
		bullet.setVelocity(Double.POSITIVE_INFINITY, 55);
		assertEquals(0, bullet.getVelocityX(), EPSILON);
		assertEquals(0, bullet.getVelocityY(), EPSILON);
	}
	
	@Test
	public void testBulletSetVelNegInf() {
		Bullet bullet = new Bullet(100, 200, 10, 20, 20);
		assertEquals(10, bullet.getVelocityX(), EPSILON);
		assertEquals(20, bullet.getVelocityY(), EPSILON);
		bullet.setVelocity(50, Double.NEGATIVE_INFINITY);
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
	
// The following tests are unnecessary:	
//	@Test (expected = NullPointerException.class)
//	public void testBulletSetMassBulletNull() {
//		Bullet bullet = null;
//		bullet.setMass();
//		assertEquals((4/3) * Math.PI * Math.pow(50, 3) * bullet.getDensity(), bullet.getMass(), EPSILON);
//	}
	
//	@Test (expected = NullPointerException.class)
//	public void testBulletGetMassBulletNull() {
//		Bullet bullet = null;
//		bullet.getMass();
//		assertEquals((4/3) * Math.PI * Math.pow(50, 3) * bullet.getDensity(), bullet.getMass(), EPSILON);
//	}
	
	
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
	
	@Test
	public void testIsInWorldT() {
		Bullet bullet = new Bullet(0.99, 0.99, 1, 1, 1);
		World world = new World(100, 100);
		bullet.isInWorld(world);
		assertTrue(bullet.isInWorld(world));
	}
	
	@Test
	public void testIsInWorldF() {
		Bullet bullet = new Bullet(0.98, 0.99, 1, 1, 1);
		World world = new World(100, 100);
		bullet.isInWorld(world);
		assertFalse(bullet.isInWorld(world));
	}
	
	@Test
	public void testBulletDistanceBetweenWorldGeneric() {
		Bullet bullet = new Bullet(10, 10, 1, 1, 1);
		World world = new World(100, 100);
		bullet.setWorld(world);
		assertEquals(9, bullet.getDistanceBetween(world)[0], EPSILON);
		assertEquals(89, bullet.getDistanceBetween(world)[1], EPSILON);
		assertEquals(9, bullet.getDistanceBetween(world)[2], EPSILON);
		assertEquals(89, bullet.getDistanceBetween(world)[3], EPSILON);
	}
	
	@Test
	public void testBulletDistanceBetweenWorldAgainstBoundary() {
		Bullet bullet = new Bullet(1, 1, 1, 1, 1);
		World world = new World(100, 100);
		bullet.setWorld(world);
		assertEquals(0, bullet.getDistanceBetween(world)[0], EPSILON);
		assertEquals(98, bullet.getDistanceBetween(world)[1], EPSILON);
		assertEquals(0, bullet.getDistanceBetween(world)[2], EPSILON);
		assertEquals(98, bullet.getDistanceBetween(world)[3], EPSILON);
	}
	
	@Test
	public void testBulletGetTimeToCollisionWorldGeneric() {
		Bullet bullet = new Bullet(2, 2, 1, 0, 1);
		World world = new World(100, 100);
		bullet.setWorld(world);
		assertEquals(97, bullet.getTimeToCollision(world), EPSILON);
	}
	
	@Test // TODO reasoning.
	public void testBulletGetTimeToCollisionWorldTouchingNoCollision() {
		Bullet bullet = new Bullet(2, 2, 0, 0, 2);
		World world = new World(100, 100);
		bullet.setWorld(world);
		assertEquals(0, bullet.getTimeToCollision(world), EPSILON);
	}
	
	@Test
	public void testBulletGetTimeToCollisionWorldTouchingWithCollision() {
		Bullet bullet = new Bullet(2, 2, 0, -10, 2);
		World world = new World(100, 100);
		bullet.setWorld(world);
		assertEquals(0, bullet.getTimeToCollision(world), EPSILON);
	}
	
	@Test
	public void testBulletGetCollisionPositionWorldGeneric() {
		Bullet bullet = new Bullet(2, 2, 1, 0, 1);
		World world = new World(100, 100);
		world.addEntity(bullet);
		bullet.setWorld(world); // TODO For Mathijs: is this a good fix?
		double[] position = bullet.getCollisionPosition(world);
		if (position[0] != 100) fail();
		if (position[1] != 2) fail();
	}
	
	@Test
	public void testBulletGetCollisionPositionWorldBulletInOtherWorld() {
		Bullet bullet = new Bullet(2, 2, 0, -10, 2);
		World world = new World(100, 100);
		bullet.setWorld(world);
		assertEquals(0, bullet.getTimeToCollision(world), EPSILON);
	}
	
	@Test
	public void testBulletGetCollisionPositionWorldTouchingNoCollision() {
		Bullet bullet = new Bullet(2, 2, 0, -10, 2);
		World world = new World(100, 100);
		bullet.setWorld(world);
		assertEquals(0, bullet.getTimeToCollision(world), EPSILON);
	}
	
	@Test
	public void testBulletGetCollisionPositionWorldTouchingWithCollision() {
		Bullet bullet = new Bullet(2, 2, 0, -10, 2);
		World world = new World(100, 100);
		bullet.setWorld(world);
		assertEquals(0, bullet.getTimeToCollision(world), EPSILON);
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
		 * |--------------------------------------------------------|
		 * | 8. The next tests test the interaction with entities.	|
		 * |--------------------------------------------------------| 
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
		ship.setWorld(world);
		bullet.setShip(ship);
		bullet.setSource(ship);
		bullet.setShip(null);
		bullet.setWorld(world);
		bullet.setWorld(null);	// TODO good fix?
		bullet.resetSource(ship);
		if (bullet.hasBeenFired() == true) fail();
		if (bullet.getSource() != null) fail();
	}
	
	@Test 
	public void testSetSourceToNull() {
		Bullet bullet = new Bullet(100, 200, 0, 0, 20);
		Ship ship = null;
		bullet.setSource(ship);
		if (bullet.getSource() != ship) fail();
	}
	
	@Test 
	public void testOverlapShipT() {
		Bullet bullet = new Bullet(100, 100, 0, 0, 20);
		Ship ship = new Ship(100, 100, 10, -10, Math.PI, 20, 10);
		assertTrue(bullet.overlap(ship));
	
	}
	
	@Test 
	public void testOverlapShipTouching() {
		Bullet bullet = new Bullet(100, 100, 0, 0, 20);
		Ship ship = new Ship(100, 200, 10, -10, Math.PI, 80, 10);
		assertFalse(bullet.overlap(ship));
	}
	
	@Test 
	public void testBulletGetDistanceBetweenShipGeneric() {
		Bullet bullet = new Bullet(100, 100, 0, 0, 20);
		Ship ship = new Ship(100, 200, 10, -10, Math.PI, 20, 10);
		assertEquals(60, bullet.getDistanceBetween(ship), EPSILON);
	}
	
	@Test 
	public void testBulletGetDistanceBetweenShipTouch() {
		Bullet bullet = new Bullet(100, 100, 0, 0, 20);
		Ship ship = new Ship(100, 200, 10, -10, Math.PI, 80, 10);
		assertEquals(0, bullet.getDistanceBetween(ship), EPSILON);
	}
	
	@Test 
	public void testBulletGetDistanceBetweenShipOverlap() {
		Bullet bullet = new Bullet(100, 100, 0, 0, 20);
		Ship ship = new Ship(100, 190, 10, -10, Math.PI, 80, 10);
		assertEquals(-10, bullet.getDistanceBetween(ship), EPSILON);
	}
	
	@Test 
	public void testBulletGetDistanceBetweenSelf() {
		Bullet bullet = new Bullet(100, 100, 0, 0, 20);
		assertEquals(0, bullet.getDistanceBetween(bullet), EPSILON);
	}
	
	@Test 
	public void testBulletGetTimeToCollisionGeneric() {
		Bullet bullet1 = new Bullet(100, 100, 0, 0, 20);
		Bullet bullet2 = new Bullet(10, 100, 10, 0, 20);
		assertEquals(5, bullet1.getTimeToCollision(bullet2), EPSILON);
	}
	
	@Test 
	public void testBulletGetTimeToCollisionNoCollision() {
		Bullet bullet1 = new Bullet(100, 100, 0, 0, 20);
		Bullet bullet2 = new Bullet(10, 100, 0, 0, 20);
		assertEquals(Double.POSITIVE_INFINITY, bullet1.getTimeToCollision(bullet2), EPSILON);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testBulletGetTimeToCollisionOverlap() {
		Bullet bullet1 = new Bullet(100, 100, 0, 0, 20);
		Bullet bullet2 = new Bullet(100, 100, 0, 0, 20);
		assertEquals(Double.POSITIVE_INFINITY, bullet1.getTimeToCollision(bullet2), EPSILON);
	}
	
	@Test 
	public void testBulletCollisionPositionGeneric() {
		Bullet bullet1 = new Bullet(100, 100, -10, 0, 5);
		Bullet bullet2 = new Bullet(10, 100, 0, 0, 5);
		World world = new World(1000, 1000);
		bullet1.setWorld(world);
		bullet2.setWorld(world);
		double[] position = bullet1.getCollisionPosition(bullet2);
		assertEquals(15, position[0], EPSILON);
		assertEquals(100, position[1], EPSILON);
	}
	
	@Test 
	public void testBulletCollisionPositionNoCollision() {
		Bullet bullet1 = new Bullet(100, 100, 0, 0, 5);
		Bullet bullet2 = new Bullet(10, 100, 0, 0, 5);
		World world = new World(1000, 1000);
		bullet1.setWorld(world);
		bullet2.setWorld(world);
		double[] position = bullet1.getCollisionPosition(bullet2);
		assertTrue(position == null);
	}
	
	@Test 
	public void testBulletCollisionPositionOtherWorlds() {
		Bullet bullet1 = new Bullet(100, 100, 0, 0, 5);
		Bullet bullet2 = new Bullet(10, 100, 0, 0, 5);
		World world1 = new World(1000, 1000);
		World world2 = new World(1000, 1000);
		bullet1.setWorld(world1);
		bullet2.setWorld(world2);
		double[] position = bullet1.getCollisionPosition(bullet2);
		assertTrue(position == null);
	}

	
			/*
			 * |----------------------------------------------------|
			 * | 9. The next tests test the move method.			|
			 * |----------------------------------------------------| 
			 */	

	@Test
	public void testBulletMoveGeneric() {
		World world = new World(1000, 1000);
		Bullet bullet = new Bullet(100, 100, 10, 5, 20);
		world.addEntity(bullet);
		bullet.setWorld(world);
		bullet.move(5);
		assertEquals(150, bullet.getPosition().getPositionX(), EPSILON);
		assertEquals(125, bullet.getPosition().getPositionY(), EPSILON);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testBulletMoveTimeNeg() {
		World world = new World(1000, 1000);
		Bullet bullet = new Bullet(100, 100, 10, 5, 20);
		world.addEntity(bullet);
		bullet.setWorld(world);
		bullet.move(-5);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testBulletMoveTimeNaN() {
		World world = new World(1000, 1000);
		Bullet bullet = new Bullet(100, 100, 10, 5, 20);
		world.addEntity(bullet);
		bullet.setWorld(world);
		bullet.move(Double.NaN);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testBulletMoveTimePosInf() {
		World world = new World(1000, 1000);
		Bullet bullet = new Bullet(100, 100, 10, 5, 20);
		world.addEntity(bullet);
		bullet.setWorld(world);
		bullet.move(Double.POSITIVE_INFINITY);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testBulletMoveTimeNegInf() {
		World world = new World(1000, 1000);
		Bullet bullet = new Bullet(100, 100, 10, 5, 20);
		world.addEntity(bullet);
		bullet.setWorld(world);
		bullet.move(Double.NEGATIVE_INFINITY);
	}
	
}
