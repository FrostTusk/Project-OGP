package asteroids.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asteroids.facade.Facade;
import asteroids.helper.Position;
import asteroids.model.Bullet;
import asteroids.model.Ship;
import asteroids.model.World;
import asteroids.part1.facade.IFacade;
import asteroids.util.ModelException;

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
	public void testCreateBullet() throws ModelException {
		Bullet bullet = new Bullet(1, 1, 1, 1, 1);
		assertNotNull(bullet);
	}
	
	
			/*
			 * |---------------------------------------|
			 * | 2. The next tests test the Position.	|
			 * |---------------------------------------| 
			 */	

	
//	@Test(expected = ModelException)
//	public void testCreateBulletPosBulletIsNull() throws ModelException {
//		Bullet bullet = null;
//		bullet.getPosition();
//	}
	
	
	@Test
	public void testCreatBulletPosGeneric() throws ModelException {
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
	public void testCreateBulletXIsPosInfinity() throws ModelException {
		new Bullet(Double.POSITIVE_INFINITY, 200, 10, -10, 20);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCreateBulletXIsNegInfinity() throws ModelException {
		new Bullet(Double.NEGATIVE_INFINITY, 200, 10, -10, 20);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCreateBulletXIsNan() throws ModelException {
		new Bullet(Double.NaN, 200, 10, -10, 20);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCreateBulletYIsPosInfinity() throws ModelException {
		new Bullet(200, Double.POSITIVE_INFINITY, 10, -10, 20);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCreateBulletYIsNegInfinity() throws ModelException {
		new Bullet(200, Double.NEGATIVE_INFINITY, 10, -10, 20);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCreateBulletYIsNan() throws ModelException {
		new Bullet(200, Double.NaN, 10, -10, 20);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCreateBulletXYIsPosInfinity() throws ModelException {
		new Bullet(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, 10, -10, 20);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCreateBulletXYIsNegInfinity() throws ModelException {
		new Bullet(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, 10, -10, 20);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCreateBulletXYIsNaN() throws ModelException {
		new Bullet(Double.NaN, Double.NaN, 10, -10, 20);
	}

	
	
			/*
			 * |---------------------------------------|
			 * | 3. The next tests test the Speed.		|
			 * |---------------------------------------| 
		  	 */	
	
	
//	@Test(expected = ModelException)
//	public void testCreateBulletPosBulletIsNull() throws ModelException {
//		Bullet bullet = null;
//		bullet.getSpeed();
//	}
	
	
	@Test
	public void testCreatBulletVelGeneric() throws ModelException {
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
	public void testCreateBulletVelOverflow() throws ModelException {
		Bullet bullet = new Bullet(200, 200, 500000, 500000, 20);
		assertEquals(0, bullet.getVelocityX(), EPSILON);
		assertEquals(0, bullet.getVelocityY(), EPSILON);
	}
	
	@Test
	public void testCreateBulletVXIsPosInfinity() throws ModelException {
		Bullet bullet = new Bullet(200, 200, Double.POSITIVE_INFINITY, 10, 20);
		assertEquals(0, bullet.getVelocityX(), EPSILON);
		assertEquals(0, bullet.getVelocityY(), EPSILON);
	}
	
	@Test
	public void testCreateBulletVXIsNegInfinity() throws ModelException {
		Bullet bullet = new Bullet(200, 200, Double.NEGATIVE_INFINITY, 10, 20);
		assertEquals(0, bullet.getVelocityX(), EPSILON);
		assertEquals(0, bullet.getVelocityY(), EPSILON);
	}
	
	@Test
	public void testCreateBulletVXIsNan() throws ModelException {
		Bullet bullet = new Bullet(200, 200, Double.NaN, 10, 20);
		assertEquals(0, bullet.getVelocityX(), EPSILON);
		assertEquals(0, bullet.getVelocityY(), EPSILON);
	}
	
	@Test
	public void testCreateBulletVYIsPosInfinity() throws ModelException {
		Bullet bullet = new Bullet(200, 200, 10, Double.POSITIVE_INFINITY, 20);
		assertEquals(0, bullet.getVelocityX(), EPSILON);
		assertEquals(0, bullet.getVelocityY(), EPSILON);
	}
	
	@Test
	public void testCreateBulletVYIsNegInfinity() throws ModelException {
		Bullet bullet = new Bullet(200, 200, 10, Double.NEGATIVE_INFINITY, 20);
		assertEquals(0, bullet.getVelocityX(), EPSILON);
		assertEquals(0, bullet.getVelocityY(), EPSILON);
	}
	
	@Test
	public void testCreateBulletVYIsNan() throws ModelException {
		Bullet bullet = new Bullet(200, 200, 10, Double.NaN, 20);
		assertEquals(0, bullet.getVelocityX(), EPSILON);
		assertEquals(0, bullet.getVelocityY(), EPSILON);
	}
	
	@Test
	public void testCreateBulletVXYIsPosInfinity() throws ModelException {
		Bullet bullet = new Bullet(200, 200, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, 20);
		assertEquals(0, bullet.getVelocityX(), EPSILON);
		assertEquals(0, bullet.getVelocityY(), EPSILON);
	}
	
	@Test
	public void testCreateBulletVXYIsNegInfinity() throws ModelException {
		Bullet bullet = new Bullet(200, 200, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, 20);
		assertEquals(0, bullet.getVelocityX(), EPSILON);
		assertEquals(0, bullet.getVelocityY(), EPSILON);
	}
	
	@Test
	public void testCreateBulletVXYIsNan() throws ModelException {
		Bullet bullet = new Bullet(200, 200, Double.NaN, Double.NaN, 20);
		assertEquals(0, bullet.getVelocityX(), EPSILON);
		assertEquals(0, bullet.getVelocityY(), EPSILON);
	}
	
	
	
			/*
			 * |------------------------------------|
			 * | 4. The next tests test the Radius.	|
			 * |------------------------------------| 
			 */	
		
		
		
//	@Test(expected = ModelException.class)
//	public void testCreateBulletRadiusBulletIsNull() throws ModelException {
//		Bullet bullet = null;
//		bullet.getRadius();
//	}
	
	
	@Test
	public void testCreateBulletRadiusGeneric() throws ModelException {
		Bullet bullet = new Bullet(100, 200, 10, -10, 50);
		assertNotNull(bullet);
		assertEquals(50, bullet.getRadius(), EPSILON);
		assertEquals(1, bullet.getMinRadius(), EPSILON);
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void testCreateBulletRadiusNegative() throws ModelException {
		new Bullet(100, 200, 10, -10, -50);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCreateBulletRadiusZero() throws ModelException {
		new Bullet(100, 200, 10, -10, 0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCreateBulletRadiusUnderflow() throws ModelException {
		new Bullet(100, 200, 10, -10, 0.5);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreateBulletRadiusIsPosInfinity() throws ModelException {
		new Bullet(100, 200, 10, -10, Double.POSITIVE_INFINITY);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCreateBulletRadiusIsNegInfinity() throws ModelException {
		new Bullet(100, 200, 10, -10, Double.NEGATIVE_INFINITY);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCreateBulletRadiusIsNan() throws ModelException {
		new Bullet(100, 200, 10, -10, Double.NaN);
	}
	
	
			/*
			* |----------------------------------------------------------------|
			* | 5. The next tests test the interaction with other entities.	|
		 	* |----------------------------------------------------------------| 
		 	*/	

	
	@Test
	public void testSetWorld() throws ModelException {
		Bullet bullet = new Bullet(1, 1, 1, 1, 1);
		World world = new World(100, 100);
		bullet.setWorld(world);
		assertNotNull(bullet.getWorld());
	}
	
	@Test
	public void testDeSetWorld() throws ModelException {
		Bullet bullet = new Bullet(1, 1, 1, 1, 1);
		World world = new World(100, 100);
		bullet.setWorld(world);
		bullet.deSetWorld();
		assertNull(bullet.getWorld());
	}
	
	@Test
	public void testCollideWithBullet() throws ModelException {
		Bullet bullet1 = new Bullet(100, 200, 0, 0, 20);
		Bullet bullet2 = new Bullet(100, 200, 10, 20, 20);
		bullet1.resolveCollisionBullet(bullet2);
		assertTrue(bullet1.isTerminated());
		assertTrue(bullet2.isTerminated());
	}
	
	@Test
	public void testCollideWithShipSource() throws ModelException {
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
	public void testCollideWithShipNotSource() throws ModelException {
		Bullet bullet = new Bullet(100, 200, 0, 0, 20);	
		Ship ship = facade.createShip(100, 200, 10, -10, 20, Math.PI);
		bullet.resolveCollision(ship);
		assertTrue(bullet.isTerminated());
		assertTrue(ship.isTerminated());
	}
	
	@Test
	public void testCollideWithWorldBulletCanBounce() throws ModelException {
		Bullet bullet = new Bullet(50, 50, 0, 0, 20);
		World world = new World(100, 100);
		double counter = bullet.getBoundaryCollisionCounter();
		bullet.setWorld(world);
		bullet.resolveCollision(world);
		assertTrue(counter + 1 == bullet.getBoundaryCollisionCounter());
	}
	
	// Werkt nog niet: world !contains bullet -> illArgExc
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
	public void testBulletAddToShip() throws ModelException {
		Bullet bullet = new Bullet(100, 200, 0, 0, 20);	
		Ship ship = facade.createShip(100, 200, 10, -10, 20, Math.PI);
		ship.loadBullet(bullet);
		assertTrue(ship.getAllBullets().contains(bullet));
	}
	
	@Test
	public void testBulletRemovedWhenFired() throws ModelException {
		Bullet bullet = new Bullet(100, 200, 0, 0, 20);	
		Ship ship = new Ship(100, 200, 10, -10, Math.PI, 20, 1);
		World world = new World(1000, 1000);
		ship.setWorld(world);
		ship.loadBullet(bullet);
		ship.fireBullet(bullet);
		assertFalse(ship.getAllBullets().contains(bullet));
	}
	
}
