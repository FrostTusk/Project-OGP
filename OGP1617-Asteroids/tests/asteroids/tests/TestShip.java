package asteroids.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asteroids.model.Bullet;
import asteroids.model.Ship;
import asteroids.model.World;
import asteroids.facade.Facade;
import asteroids.helper.Position;
import asteroids.part1.facade.IFacade;

/* 
 * // TODO Do NOT get rid of tests without telling me!
 * // Not even irrelevant ones. 
 * Tests Index:
 * 1. Tests for Initialization
 * 2. Tests for Position
 * 3. Tests for Speed
 * 4. Tests for Radius
 * 5. Tests for Orientation
 * 6. Tests for Mass
 * 7. Test for Bullets
 * 8. Tests for Move
 * 9. Tests for Thrust
 * 10. Tests for Turn
 * 11. Tests for Distance
 * 12. Tests for Overlap
 * 13. Tests for Collision Detection
 * 14. tests for interaction with worlds
 * 15.tests for interaction with entities
 */

public class TestShip {

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
	public void testCreateShip() {
		Ship ship = new Ship(100, 200, 10, -10, Math.PI, 20, 0);
		assertNotNull(ship);
		Position position = ship.getPosition();
		assertNotNull(position);
		assertEquals(100, position.getPositionX(), EPSILON);
		assertEquals(200, position.getPositionY(), EPSILON);
		assertEquals(20, ship.getRadius(), EPSILON);
	}

	
	
			/*
			 * |----------------------------------------|
			 * | 2. The next tests test the Position.	|
			 * |----------------------------------------| 
			 */	

	
	
//	The following test is unnecessary:
//	@Test(expected = NullPointerException.class)
//	public void testCreateShipPosShipIsNull() {
//		Ship ship = null;
//		ship.getPosition();
//	}
	
	
	@Test
	public void testCreatShipPosGeneric() {
		Ship ship1 = new Ship(100, 200, 10, -10, Math.PI, 20, 0);
		Position position1 = ship1.getPosition();
		assertEquals(100, position1.getPositionX(), EPSILON);
		assertEquals(200, position1.getPositionY(), EPSILON);
		
		Ship ship2 = new Ship(100, -200, 10, -10, Math.PI, 20, 0);
		Position position2 = ship2.getPosition();
		assertEquals(100, position2.getPositionX(), EPSILON);
		assertEquals(-200, position2.getPositionY(), EPSILON);
		
		Ship ship3 = new Ship(-100, 200, 10, -10, Math.PI, 20, 0);
		Position position3 = ship3.getPosition();
		assertEquals(-100, position3.getPositionX(), EPSILON);
		assertEquals(200, position3.getPositionY(), EPSILON);
		
		Ship ship4 = new Ship(-100, -200, 10, -10, Math.PI, 20, 0);
		Position position4 = ship4.getPosition();
		assertEquals(-100, position4.getPositionX(), EPSILON);
		assertEquals(-200, position4.getPositionY(), EPSILON);
		
		Ship ship5= new Ship(0, 0, 10, -10, Math.PI, 20, 0);
		Position position5 = ship5.getPosition();
		assertEquals(0, position5.getPositionX(), EPSILON);
		assertEquals(0, position5.getPositionY(), EPSILON);
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void testCreateShipXIsPosInfinity() {
		new Ship(Double.POSITIVE_INFINITY, 200, 10, -10, Math.PI, 20, 0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCreateShipXIsNegInfinity() {
		new Ship(Double.NEGATIVE_INFINITY, 200, 10, -10, Math.PI, 20, 0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCreateShipXIsNan() {
		new Ship(Double.NaN, 200, 10, -10, Math.PI, 20, 0);
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void testCreateShipYIsPosInfinity() {
		new Ship(200, Double.POSITIVE_INFINITY, 10, -10, Math.PI, 20, 0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCreateShipYIsNegInfinity() {
		new Ship(200, Double.NEGATIVE_INFINITY, 10, -10, Math.PI, 20, 0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCreateShipYIsNan() {
		new Ship(200, Double.NaN, 10, -10, Math.PI, 20, 0);
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void testCreateShipXYIsPosInfinity() {
		new Ship(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, 10, -10, Math.PI, 20, 0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCreateShipXYIsNegInfinity() {
		new Ship(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, 10, -10, Math.PI, 20, 0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCreateShipXYIsNaN() {
		new Ship(Double.NaN, Double.NaN, 10, -10, Math.PI, 20, 0);
	}
	
	
	
			/*
			 * |------------------------------------|
			 * | 3. The next tests test the Speed.	|
			 * |------------------------------------| 
			 */	
	
	
	
//	The following test is unnecessary:
//	@Test(expected = NullPointerException.class)
//	public void testCreateShipVelShipIsNull() {
//		Ship ship = null;
//		ship.getSpeed();
//	}
	
	
	@Test
	public void testCreatShipVelGeneric() {
		Ship ship1 = new Ship(100, 200, 100, 200, Math.PI, 20, 0);
		double velocity1X = ship1.getVelocityX();
		double velocity1Y = ship1.getVelocityY();
		assertEquals(100, velocity1X, EPSILON);
		assertEquals(200, velocity1Y, EPSILON);
		
		Ship ship2 = new Ship(100, -200, 100, -200, Math.PI, 20, 0);
		double velocity2X = ship2.getVelocityX();
		double velocity2Y = ship2.getVelocityY();
		assertEquals(100, velocity2X, EPSILON);
		assertEquals(-200, velocity2Y, EPSILON);
		
		Ship ship3 = new Ship(-100, 200, -100, 200, Math.PI, 20, 0);
		double velocity3X = ship3.getVelocityX();
		double velocity3Y = ship3.getVelocityY();
		assertEquals(-100, velocity3X, EPSILON);
		assertEquals(200, velocity3Y, EPSILON);
		
		Ship ship4 = new Ship(-100, -200, -100, -200, Math.PI, 20, 0);
		double velocity4X = ship4.getVelocityX();
		double velocity4Y = ship4.getVelocityY();
		assertEquals(-100, velocity4X, EPSILON);
		assertEquals(-200, velocity4Y, EPSILON);
		
		Ship ship5= new Ship(0, 0, 0, 0, Math.PI, 20, 0);
		double velocity5X = ship5.getVelocityX();
		double velocity5Y = ship5.getVelocityY();
		assertEquals(0, velocity5X, EPSILON);
		assertEquals(0, velocity5Y, EPSILON);
	}
	
	
	@Test
	public void testCreateShipVelOverflow() {
		Ship ship = new Ship(200, 200, 500000, 500000, Math.PI, 20, 0);
		assertEquals(0, ship.getVelocityX(), EPSILON);
		assertEquals(0, ship.getVelocityY(), EPSILON);
	}
	
	
	@Test
	public void testCreateShipVXIsPosInfinity() {
		Ship ship = new Ship(200, 200, Double.POSITIVE_INFINITY, -10, Math.PI, 20, 0);
		assertEquals(0, ship.getVelocityX(), EPSILON);
	}
	
	@Test
	public void testCreateShipVXIsNegInfinity() {
		Ship ship = new Ship(200, 200, Double.NEGATIVE_INFINITY, -10, Math.PI, 20, 0);
		assertEquals(0, ship.getVelocityX(), EPSILON);
	}
	
	@Test
	public void testCreateShipVXIsNan() {
		Ship ship = new Ship(-200, 200, Double.NaN, -10, Math.PI, 20, 0);
		assertEquals(0, ship.getVelocityX(), EPSILON);
	}
	
	
	@Test
	public void testCreateShipVYIsPosInfinity() {
		Ship ship = new Ship(200, 200, 10, Double.POSITIVE_INFINITY, Math.PI, 20, 0);
		assertEquals(0, ship.getVelocityY(), EPSILON);
	}
	
	@Test
	public void testCreateShipVYIsNegInfinity() {
		Ship ship = new Ship(200, 200, 10, Double.NEGATIVE_INFINITY, Math.PI, 20, 0);
		assertEquals(0, ship.getVelocityY(), EPSILON);
	}
	
	@Test
	public void testCreateShipVYIsNan() {
		Ship ship = new Ship(-200, 200, 10, Double.NaN, Math.PI, 20, 0);
		assertEquals(0, ship.getVelocityY(), EPSILON);
	}
	
	
	@Test
	public void testCreateShipVXYIsPosInfinity() {
		Ship ship = new Ship(200, 200, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Math.PI, 20, 0);
		assertEquals(0, ship.getVelocityX(), EPSILON);
		assertEquals(0, ship.getVelocityY(), EPSILON);
	}
	
	@Test
	public void testCreateShipVXYIsNegInfinity() {
		Ship ship = new Ship(-200, 200, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Math.PI, 20, 0);
		assertEquals(0, ship.getVelocityX(), EPSILON);
		assertEquals(0, ship.getVelocityY(), EPSILON);
	}
	
	@Test
	public void testCreateShipVXYIsNan() {
		Ship ship = new Ship(-200, 200, Double.NaN, Double.NaN,  Math.PI, 20, 0);
		assertEquals(0, ship.getVelocityX(), EPSILON);
		assertEquals(0, ship.getVelocityY(), EPSILON);
	}
	
	
	
			/*
			 * |------------------------------------|
			 * | 4. The next tests test the Radius.	|
			 * |------------------------------------| 
			 */	
	

	
//	The following test is unnecessary:
//	@Test(expected = NullPointerException.class)
//	public void testCreateShipRadiusShipIsNull() {
//		Ship ship = null;
//		ship.getRadius();
//	}
	
	
	@Test
	public void testCreateShipRadiusGeneric() {
		Ship ship = new Ship(100, 200, 10, -10, Math.PI, 50, 0);
		assertNotNull(ship.getRadius());
		assertEquals(50, ship.getRadius(), EPSILON);
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void testCreateShipRadiusUnderflow() {
		new Ship(100, 200, 10, -10, 1, Math.PI, 0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCreateShipRadiusNegative() {
		new Ship(100, 200, 10, 10, Math.PI, -10, 20);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCreateShipRadiusZero() {
		new Ship(100, 200, 10, -10, 0, Math.PI, 0);
	}
	

	@Test(expected = IllegalArgumentException.class)
	public void testCreateShipRadiusIsPosInfinity() {
		new Ship(100, 200, 10, -10, Math.PI, Double.POSITIVE_INFINITY, 0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCreateShipRadiusIsNegInfinity() {
		new Ship(100, 200, 10, -10, Math.PI, Double.NEGATIVE_INFINITY, 0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCreateShipRadiusIsNan() {
		new Ship(100, 200, 10, -10, Math.PI, Double.NaN, 0);
	}
	
	
	
			/*
			 * |--------------------------------------------|
			 * | 5. The next tests test the Orientation.	|
			 * |--------------------------------------------| 
			 */	
	

	
//	The following test is unnecessay:
//	@Test(expected = NullPointerException.class)
//	public void testCreateShipOShipIsNull() {
//		Ship ship = null;
//		ship.getOrientation();
//	}
	
	@Test
	public void testCreateShipOGeneric() {
		Ship ship1 = new Ship(100, 200, 100, 200, 0, 20, 0);
		assertNotNull(ship1.getOrientation());
		assertEquals(0, ship1.getOrientation(), EPSILON);
		Ship ship2 = new Ship(100, 200, 100, 200, Math.PI, 20, 0);
		assertNotNull(ship2.getOrientation());
		assertEquals(Math.PI, ship2.getOrientation(), EPSILON);
		Ship ship3 = new Ship(100, 200, 100, 200, 2*Math.PI, 20, 20);
		assertNotNull(ship3.getOrientation());
		assertEquals(2*Math.PI, ship3.getOrientation(), EPSILON);
	}
	
	@Test (expected = AssertionError.class)
	public void testCreateShipOrientationOverflow() {
		new Ship(100, 200, 10, -10, 10*Math.PI, 20, 20);
	}
	
	@Test(expected = AssertionError.class)
	public void testCreateShipOisNeg() {
		new Ship(100, 200, 10, -10, -Math.PI, 20, 20);
	}
	

	@Test(expected = AssertionError.class)
	public void testCreateShipOrientationIsPosInfinity() {
		new Ship(100, 200, 10, -10, Double.POSITIVE_INFINITY, 20, 20);
	}
	
	@Test(expected = AssertionError.class)
	public void testCreateShipOrientationIsNegInfinity() {
		new Ship(100, 200, 10, -10, Double.NEGATIVE_INFINITY, 20, 20);
	}
	
	@Test(expected = AssertionError.class)
	public void testCreateShipOisNaN() {
		new Ship(100, 200, 10, -10, Double.NaN, 20, 0);
	}
	
		
			/*
			 * |------------------------------------|
			 * | 6. The next tests test the Mass.	|
			 * |------------------------------------| 
			 */



	@Test
	public void testCreateShipMassGeneric(){
		Ship ship = new Ship(100, 200, 10, -10, Math.PI, 20, Math.pow(10, 20));
		assertEquals(Math.pow(10, 20), ship.getMass(), EPSILON);
	}
	
	
	@Test
	public void testCreateShipMassUnderZero() {
		Ship ship = new Ship(100, 200, 10, -10, Math.PI, 20, -10);
		assertEquals((4/3) * Math.PI * Math.pow(20, 3) * ship.getDensity(), ship.getMass(), EPSILON);
	}
	
	@Test
	public void testCreateShipMassZero() {
		Ship ship = new Ship(100, 200, 10, -10, Math.PI, 20, 0);
		assertEquals((4/3) * Math.PI * Math.pow(20, 3) * ship.getDensity(), ship.getMass(), EPSILON);
	}
	
	@Test
	public void testShipTotalMassGeneric() {
		Bullet bullet1 = new Bullet(100, 200, 0, 0, 20);	
		Bullet bullet2 = new Bullet(100, 200, 0, 0, 20);	
		Ship ship = new Ship(100, 200, 10, -10, Math.PI, 20, 1);
		ship.loadBullet(bullet1);
		assertEquals(ship.getTotalMass(), ship.getMass() + bullet1.getMass(), EPSILON);
		ship.loadBullet(bullet2);
		assertEquals(ship.getTotalMass(), ship.getMass() + bullet1.getMass() + bullet2.getMass(), EPSILON);
	}
	
	@Test
	public void testShipTotalMassNoCargo() {
		Ship ship = new Ship(100, 200, 10, -10, Math.PI, 20, 1);
		assertEquals(ship.getTotalMass(), ship.getMass(), EPSILON);
	}
	
	@Test
	public void testShipSetMassGeneric() {
		Ship ship = new Ship(100, 200, 10, -10, Math.PI, 20, 1);
		assertEquals((4/3) * Math.PI * Math.pow(ship.getRadius(), 3) * ship.getDensity(), ship.getMass(), EPSILON);
		ship.setMass(Math.pow(10, 20));
		assertEquals(Math.pow(10, 20), ship.getMass(), EPSILON);
	}
	
	@Test
	public void testShipSetMassNeg() {
		Ship ship = new Ship(100, 200, 10, -10, Math.PI, 20, 1);
		assertEquals((4/3) * Math.PI * Math.pow(ship.getRadius(), 3) * ship.getDensity(), ship.getMass(), EPSILON);
		ship.setMass(-1);
		assertEquals((4/3) * Math.PI * Math.pow(ship.getRadius(), 3) * ship.getDensity(), ship.getMass(), EPSILON);
	}
	
	@Test
	public void testShipSetMassNaN() {
		Ship ship = new Ship(100, 200, 10, -10, Math.PI, 20, 1);
		assertEquals((4/3) * Math.PI * Math.pow(ship.getRadius(), 3) * ship.getDensity(), ship.getMass(), EPSILON);
		ship.setMass(Double.NaN);
		assertEquals((4/3) * Math.PI * Math.pow(ship.getRadius(), 3) * ship.getDensity(), ship.getMass(), EPSILON);
	}
	
	@Test
	public void testShipSetMassNegInf() {
		Ship ship = new Ship(100, 200, 10, -10, Math.PI, 20, 1);
		assertEquals((4/3) * Math.PI * Math.pow(ship.getRadius(), 3) * ship.getDensity(), ship.getMass(), EPSILON);
		ship.setMass(Double.NEGATIVE_INFINITY);
		assertEquals((4/3) * Math.PI * Math.pow(ship.getRadius(), 3) * ship.getDensity(), ship.getMass(), EPSILON);
	}
	
	@Test
	public void testShipSetMassPosInf() {
		Ship ship = new Ship(100, 200, 10, -10, Math.PI, 20, 1);
		assertEquals((4/3) * Math.PI * Math.pow(ship.getRadius(), 3) * ship.getDensity(), ship.getMass(), EPSILON);
		ship.setMass(Double.POSITIVE_INFINITY);
		assertEquals(Double.POSITIVE_INFINITY, ship.getMass(), EPSILON);
	}

	

			/*
			 * |--------------------------------------------------------|
			 * | 7. The next tests test the interaction with bullets.	|
			 * |--------------------------------------------------------| 
			 */



	@Test // TODO Technically, this is tested in other methods and is thus not really necessary
	public void testShipCanHaveAsBulletT() {
		Bullet bullet = new Bullet(100, 200, 0, 0, 20);	
		Ship ship1 = new Ship(100, 200, 10, -10, Math.PI, 20, 1);
		Ship ship2 = new Ship(200, 200, 10, -10, Math.PI, 20, 1);
		bullet.setShip(ship2);
		assertTrue(ship1.canHaveAsBullet(bullet));
	}
	
	@Test
	public void testShipCanHaveAsBulletF() {
		Bullet bullet = new Bullet(100, 200, 0, 0, 20);
		Ship ship = new Ship(100, 200, 10, -10, Math.PI, 20, 1);
		ship.loadBullet(bullet);
		assertFalse(ship.canHaveAsBullet(bullet));
	}
	
	@Test
	public void testShipCanHaveAsBulletBulletIsNull() {
		Bullet bullet = null;
		Ship ship = new Ship(100, 200, 10, -10, Math.PI, 20, 1);
		assertFalse(ship.canHaveAsBullet(bullet));
	}
	
	// TODO More of these tests:
	@Test
	public void testShipBulletLoadOnShipSingleBullet() {
		Bullet bullet = new Bullet(100, 200, 0, 0, 20);	
		Ship ship = new Ship(100, 200, 10, -10, Math.PI, 20, 1);
		ship.loadBullet(bullet);
		assertTrue(ship.getAllBullets().contains(bullet));
	}
	
	@Test
	public void testShipBulletLoadOnShipMultipleBullets() {
		Bullet bullet1 = new Bullet(100, 200, 0, 0, 20);	
		Bullet bullet2= new Bullet(100, 200, 0, 0, 20);	
		Bullet bullet3 = new Bullet(100, 200, 0, 0, 20);	
		Ship ship = new Ship(100, 200, 10, -10, Math.PI, 20, 1);
		ship.loadBullet(bullet1);
		ship.loadBullet(bullet2);
		ship.loadBullet(bullet3);
		assertTrue(ship.getAllBullets().contains(bullet1));
		assertTrue(ship.getAllBullets().contains(bullet2));
		assertTrue(ship.getAllBullets().contains(bullet3));
	}
	
	@Test
	public void testShipGetBulletsSetSize() {
		Bullet bullet1 = new Bullet(100, 200, 0, 0, 20);	
		Bullet bullet2= new Bullet(100, 200, 0, 0, 20);	
		Bullet bullet3 = new Bullet(100, 200, 0, 0, 20);	
		Ship ship = new Ship(100, 200, 10, -10, Math.PI, 20, 1);
		ship.loadBullet(bullet1);
		ship.loadBullet(bullet2);
		ship.loadBullet(bullet3);
		assertTrue(ship.getBulletsCount() == 3);
	}
	
	@Test
	public void testShipGetBulletsSetSizeNoBullets() {
		Ship ship = new Ship(100, 200, 10, -10, Math.PI, 20, 1);
		assertTrue(ship.getBulletsCount() == 0);
	}
	
	@Test
	public void testShipRemoveBullet() {
		Bullet bullet1 = new Bullet(100, 200, 0, 0, 20);	
		Bullet bullet2= new Bullet(100, 200, 0, 0, 20);	
		Bullet bullet3 = new Bullet(100, 200, 0, 0, 20);	
		Ship ship = new Ship(100, 200, 10, -10, Math.PI, 20, 1);
		ship.loadBullet(bullet1);
		ship.loadBullet(bullet2);
		ship.loadBullet(bullet3);
		assertTrue(ship.getBulletsCount() == 3);
		ship.removeBullet(bullet1);
		assertTrue(ship.getBulletsCount() == 2);
		assertFalse(ship.getAllBullets().contains(bullet1));
		assertTrue(ship.getAllBullets().contains(bullet2));
		assertTrue(ship.getAllBullets().contains(bullet3));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testShipRemoveBulletNoBullets() {
		Bullet bullet = new Bullet(100, 200, 0, 0, 20);
		Ship ship = new Ship(100, 200, 10, -10, Math.PI, 20, 1);
		assertTrue(ship.getBulletsCount() == 0);
		assertFalse(ship.getAllBullets().contains(bullet));
		ship.removeBullet(bullet);
	}
	
	
	@Test
	public void testShipBulletRemovedWhenFired() {
		Bullet bullet = new Bullet(100, 200, 0, 0, 20);	
		Ship ship = new Ship(100, 200, 10, -10, Math.PI, 20, 1);
		World world = new World(1000, 1000);
		ship.setWorld(world);
		ship.loadBullet(bullet);
		ship.fireBullet(bullet);
		assertFalse(ship.getAllBullets().contains(bullet));
	}
	
	@Test
	public void testShipCanHaveAsBulletAlreadyAsBullet() {
		Ship ship = new Ship(90, 90, -10, -10, 0, 20, 0);
		Bullet bullet = new Bullet(100, 100, 0, 0, 1);
		ship.loadBullet(bullet);
		assertFalse(ship.canHaveAsBullet(bullet));
	}
	
	@Test
	public void testShipCanHaveAsBulletNotCompletelyInShipCircle() {
		Ship ship = new Ship(95, 95, -10, -10, 0, 10, 0);
		Bullet bullet = new Bullet(100, 100, 0, 0, 10);
		assertFalse(ship.canHaveAsBullet(bullet));
	}
	
	@Test
	public void testShipCanHaveAsBulletSamePositionBulletLarger() {
		Ship ship = new Ship(100, 100, -10, -10, 0, 10, 0);
		Bullet bullet = new Bullet(100, 100, 0, 0, 20);
		assertFalse(ship.canHaveAsBullet(bullet));
	}
	
	@Test
	public void testShipCanHaveAsBulletOtherPosition() {
		Ship ship = new Ship(90, 90, -10, -10, 0, 20, 0);
		Bullet bullet = new Bullet(1000, 1000, 0, 0, 1);
		assertFalse(ship.canHaveAsBullet(bullet));
	}
	
	@Test
	public void testShipCanHaveAsBulletHasWorld() {
		Ship ship = new Ship(100, 100, -10, -10, 0, 20, 0);
		Bullet bullet = new Bullet(100, 100, 0, 0, 1);
		World world = new World(1000, 1000);
		bullet.setWorld(world);
		assertTrue(ship.canHaveAsBullet(bullet));
	}
	
	@Test
	public void testShipBulletReload() {
		Bullet bullet = new Bullet(100, 200, 0, 0, 10);	
		Ship ship = new Ship(100, 200, 10, -10, Math.PI, 20, 1);
		bullet.setShip(ship);
		bullet.setSource(ship);
		bullet.setShip(null);
		assertFalse(ship.getAllBullets().contains(bullet));
		ship.reloadBullet(bullet);
		assertTrue(ship.getAllBullets().contains(bullet));
	}
	
	@Test
	public void testShipBulletReloadHasBounced() {
		Bullet bullet = new Bullet(100, 200, 0, 0, 10);	
		Ship ship = new Ship(100, 200, 0, 0, Math.PI, 20, 1);
		World world = new World(500, 500);
		world.addEntity(ship);
		ship.loadBullet(bullet);
		assertTrue(ship.getAllBullets().contains(bullet));
		ship.fireBullet(bullet);
		assertFalse(ship.getAllBullets().contains(bullet));
		world.evolve(2);
		assertEquals(1, bullet.getBoundaryCollisionCounter(), EPSILON);
//		ship.reloadBullet(bullet); TODO Already collided in evolve
		assertTrue(ship.getAllBullets().contains(bullet));
	}
	
	@Test
	public void testShipFireBulletHorizontal() {
		Bullet bullet = new Bullet(100, 200, 0, 0, 10);	
		Ship ship = new Ship(100, 200, 10, -10, Math.PI, 20, 1);
		World world = new World(1000, 1000);
		world.addEntity(ship);
		ship.setWorld(world);
		ship.loadBullet(bullet);
		ship.fireBullet(bullet);
		assertEquals(-250, bullet.getVelocityX(), EPSILON);
		assertEquals(0, bullet.getVelocityY(), EPSILON);
		assertTrue(bullet.hasBeenFired());
		assertTrue(bullet.getSource() == ship);
	}
	
	@Test
	public void testShipFireBulletGeneric() {
		Bullet bullet = new Bullet(100, 200, 0, 0, 10);	
		Ship ship = new Ship(100, 200, 10, -10, 1, 20, 1);
		World world = new World(1000, 1000);
		world.addEntity(ship);
		ship.setWorld(world);
		ship.loadBullet(bullet);
		ship.fireBullet(bullet);
		assertEquals(250*Math.cos(1), bullet.getVelocityX(), EPSILON);
		assertEquals(250*Math.sin(1), bullet.getVelocityY(), EPSILON);
		assertTrue(bullet.hasBeenFired());
		assertTrue(bullet.getSource() == ship);
	}

	
	
			/*
			 * |--------------------------------------------|
			 * | 8. The next tests test the Move method.	|
			 * |--------------------------------------------| 
			 */	

	
	
//	The next test is unnecessary:
//	@Test(expected = NullPointerException.class)
//	public void testMoveShipIsNull() {
//		Ship ship = null;
//		ship.move(1);
//	}
	
	
	@Test
	public void testMoveGeneric() {
		Ship ship = new Ship(100, 100, 30, -15, 0, 20, 20);
		ship.move(1);
		Position position = ship.getPosition();
		assertNotNull(position);
		assertEquals(130, position.getPositionX(), EPSILON);
		assertEquals(85, position.getPositionY(), EPSILON);
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void testMoveTisNeg() {
		Ship ship = new Ship(100, 100, 30, -15, 0, 20, 20);
		ship.move(-1);;
	}
	
	@Test
	public void testMoveTisZero() {
		Ship ship = new Ship(100, 100, 30, -15, 0, 20, 20);
		ship.move(0);
		Position position = ship.getPosition();
		assertNotNull(position);
		assertEquals(100, position.getPositionX(), EPSILON);
		assertEquals(100, position.getPositionY(), EPSILON);
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void testMoveTisPosInfinity() {
		Ship ship = new Ship(100, 100, 30, -15, 0, 20, 20);
		ship.move(Double.POSITIVE_INFINITY);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testMoveTisNegInfinity() {
		Ship ship = new Ship(100, 100, 30, -15, 0, 20, 20);
		ship.move(Double.NEGATIVE_INFINITY);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testMoveTisNaN() {
		Ship ship = new Ship(100, 100, 30, -15, 0, 20, 0);
		ship.move(Double.NaN);
	}
	
	
	
			/*
			 * |--------------------------------------------|
			 * | 9. The next tests test the Thrust method.	|
			 * |--------------------------------------------| 
			 */
	
	
	
	@Test
	public void testThrustX() {
		Ship ship = new Ship(100, 100, 0, 0, 0, 20, 0);
		ship.thrust(10);
		assertNotNull(ship.getSpeed());
		assertEquals(10, ship.getVelocityX(), EPSILON);
		assertEquals(0, ship.getVelocityY(), EPSILON);
	}
	
	@Test
	public void testThrustY() {
		Ship ship = new Ship(100, 100, 0, 0, (Math.PI)/2, 20, 0);
		ship.thrust(15);
		assertNotNull(ship.getSpeed());
		assertEquals(0, ship.getVelocityX(), EPSILON);
		assertEquals(15, ship.getVelocityY(), EPSILON);
	}
	
	
	@Test
	public void testThrustXWhithMovingStart() {
		Ship ship = new Ship(100, 100, 10, 10, 0, 20, 0);
		ship.thrust(5);
		assertNotNull(ship.getSpeed());
		assertEquals(15, ship.getVelocityX(), EPSILON);
		assertEquals(10, ship.getVelocityY(), EPSILON);
	}
	
	@Test
	public void testThrustYWhithMovingStart() {
		Ship ship = new Ship(100, 100, 10, 10, (Math.PI)/2, 20, 0);
		ship.thrust(7);
		assertNotNull(ship.getSpeed());
		assertEquals(10, ship.getVelocityX(), EPSILON);
		assertEquals(17, ship.getVelocityY(), EPSILON);
	}
		

	@Test
	public void testThrustOverflow() {
		Ship ship = new Ship(100, 100, 0, 0, 0, 20, 0);
		ship.thrust(500000);
		assertEquals(0, ship.getVelocityX(), EPSILON);
		assertEquals(0, ship.getVelocityY(), EPSILON);
	}
	
	@Test
	public void testThrustNeg() {
		Ship ship = new Ship(100, 100, 100, 100, 0, 20, 0);
		ship.thrust(-10);
		assertNotNull(ship.getSpeed());
		assertEquals(100, ship.getVelocityX(), EPSILON);
		assertEquals(100, ship.getVelocityY(), EPSILON);
	}
	
	
	@Test
	public void testThrustPosInfinity() {
		Ship ship = new Ship(100, 100, 0, 0, 0, 20, 0);
		ship.thrust(Double.POSITIVE_INFINITY);
		assertEquals(0, ship.getVelocityX(), EPSILON);
		assertEquals(0, ship.getVelocityY(), EPSILON);
	}
	
	@Test
	public void testThrustNegInfinity() {
		Ship ship = new Ship(100, 100, 0, 0, 0, 20, 0);
		ship.thrust(Double.NEGATIVE_INFINITY);
		assertEquals(0, ship.getVelocityX(), EPSILON);
		assertEquals(0, ship.getVelocityY(), EPSILON);
	}
	
	@Test
	public void testThrustNaN() {
		Ship ship = new Ship(100, 100, 0, 0, 0, 20, 0);
		ship.thrust(Double.NaN);
		assertEquals(0, ship.getVelocityX(), EPSILON);
		assertEquals(0, ship.getVelocityY(), EPSILON);
	}
	
	@Test
	public void testThrusterOn() {
		Ship ship = new Ship(100, 100, 10, 10, 0, 20, 0);
		assertTrue(ship.getThrustStatus() == false);
		ship.thrustOn();
		assertTrue(ship.getThrustStatus() == true);
	}
	
	@Test
	public void testThrusterOff() {
		Ship ship = new Ship(100, 100, 10, 10, 0, 20, 0);
		assertTrue(ship.getThrustStatus() == false);
		ship.thrustOn();
		assertTrue(ship.getThrustStatus() == true);
		ship.thrustOff();
		assertTrue(ship.getThrustStatus() == false);
	}
	

	
			/*
			 * |--------------------------------------------|
			 * | 10. The next tests test the Turn method.	|
			 * |--------------------------------------------| 
			 */	
	
	
	
	@Test
	public void testTurnGeneric() {
		Ship ship = new Ship(100, 100, 30, -15, 0, 20, 0);
		ship.turn(Math.PI);
		assertNotNull(ship.getOrientation());
		assertEquals(Math.PI, ship.getOrientation(), EPSILON);
	}
	
	
	@Test
	public void testTurnOisNeg() {
		Ship ship = new Ship(100, 100, 30, -15, Math.PI, 20, 20);
		ship.turn(-Math.PI);
		assertNotNull(ship.getOrientation());
		assertEquals(0, ship.getOrientation(), EPSILON);
	}
	
	@Test
	public void testTurnOisZero() {
		Ship ship = new Ship(100, 100, 30, -15, 0, 20, 0);
		ship.turn(0);
		assertNotNull(ship.getOrientation());
		assertEquals(0, ship.getOrientation(), EPSILON);
	}
	
	
	@Test
	public void testTurnOOverflow() {
		Ship ship = new Ship(100, 100, 30, -15, 0, 20, 2*Math.PI);
		ship.turn(Math.PI);
		assertNotNull(ship.getOrientation());
		assertEquals(Math.PI, ship.getOrientation(), EPSILON);
	}
	
	@Test
	public void testTurnOUnderflow() {
		Ship ship = new Ship(100, 100, 30, -15, 0, 20, 0);
		ship.turn(-2*Math.PI);
		assertNotNull(ship.getOrientation());
		assertEquals(0, ship.getOrientation(), EPSILON);
	}
	
	@Test
	public void testTurnAtHighSpeed() {
		Ship ship = new Ship(100, 100, 100000, 100000, 0, 20, 0);
		ship.turn(Math.PI);
		assertNotNull(ship.getOrientation());
		assertEquals(Math.PI, ship.getOrientation(), EPSILON);
	}
	
	@Test(expected = AssertionError.class)
	public void testTurnOisPosInfinity() {
		Ship ship = new Ship(100, 100, 30, -15, Math.PI, 20, 0);
		ship.turn(Double.POSITIVE_INFINITY);
	}
	
	@Test(expected = AssertionError.class)
	public void testTurnOisNegInfinity() {
		Ship ship = new Ship(100, 100, 30, -15, Math.PI, 20, 0);
		ship.turn(Double.NEGATIVE_INFINITY);
	}
	
	@Test(expected = AssertionError.class)
	public void testTurnOisNaN() {
		Ship ship = new Ship(100, 100, 30, -15, Math.PI, 20, 0);
		ship.turn(Double.NaN);
	}
	

	
			/*
			 * |------------------------------------------------|
			 * | 11. The next tests test the Distance method.	|
			 * |------------------------------------------------| 
			 */
	
	
	
//	@Test(expected = NullPointerException.class)
//	public void testDistanceShip1IsNull() {
//		Ship ship1 = null;
//		Ship ship2 = new Ship(100, 100, 30, -15, 0, 20, 0);
//		assertEquals(0, ship1.getDistanceBetween(ship2), EPSILON);
//	}
	
	@Test(expected = NullPointerException.class)
	public void testDistanceShip2IsNull() {
		Ship ship1 = new Ship(100, 100, 30, -15, 0, 20, 0);
		Ship ship2 = null;
		assertEquals(0, ship1.getDistanceBetween(ship2), EPSILON);
	}
	
	
	@Test
	public void testDistanceIsPositiveLinearX() {
		Ship ship1 = new Ship(130, 0, 0, 0, 0, 10, 0);
		Ship ship2 = new Ship(100, 0, 0, 0, 0, 10, 0);
		assertEquals(10, ship1.getDistanceBetween(ship2), EPSILON);
	}
	
	@Test
	public void testDistanceIsPositiveLinearY() {
		Ship ship1 = new Ship(0, 140, 0, 0, 0, 10, 0);;
		Ship ship2 = new Ship(0, 100, 0, 0, 0, 10, 0);
		assertEquals(20, ship1.getDistanceBetween(ship2), EPSILON);
	}
	
	@Test
	public void testDistanceIsNegative() {
		Ship ship2 = new Ship(105, 100, 0, 0, 0, 10, 0);;
		Ship ship1 = new Ship(100, 100, 0, 0, 0, 10, 0);
		assertEquals(-15, ship1.getDistanceBetween(ship2), EPSILON);
	}
	
	@Test
	public void testDistanceIsZero() {
		Ship ship1 = new Ship(130, 0, 0, 0, 0, 10, 0);;
		Ship ship2 = new Ship(100, 0, 0, 0, 0, 20, 0);
		assertEquals(0, ship1.getDistanceBetween(ship2), EPSILON);
	}
	
	
	@Test
	public void testDistanceSameShip() {
		Ship ship1 = new Ship(100, 100, 30, -15, 0, 20, 0);
		assertEquals(0, ship1.getDistanceBetween(ship1), EPSILON);
	}
	
	@Test
	public void testDistanceOver00() {
		Ship ship1 = new Ship(20, 20, 30, 15, 0, 10, 0);
		Ship ship2 = new Ship(-20, -20, 0, 0, 0, 10, 0);
		assertEquals(36.568542, ship1.getDistanceBetween(ship2), EPSILON);
	}
	

	
			/*
			 * |------------------------------------------------|
		     * | 12. The next tests test the Overlap method.	|
		     * |------------------------------------------------| 
		     */	
	
	
	
//	@Test(expected = NullPointerException.class)
//	public void testOverlapShip1IsNull() {
//		Ship ship1 = null;
//		Ship ship2 = new Ship(100, 100, 30, -15, 0, 20, 0);
//		assertFalse(ship1.overlap(ship2));
//	}
	
	@Test(expected = NullPointerException.class)
	public void testOverlapShip2IsNull() {
		Ship ship1 = new Ship(100, 100, 30, -15, 0, 20, 0);
		Ship ship2 = null;
		assertFalse(ship1.overlap(ship2));
	}
	
	
	@Test
	public void testOverlapTrue() {
		Ship ship1 = new Ship(0, 0, 30, -15, Math.PI, 20, 20);
		Ship ship2 = new Ship(5, 5, 30, -15, Math.PI, 20, 20);
		assertTrue(ship1.overlap(ship2));
	}
	
	@Test
	public void testOverlapFalse() {
		Ship ship1 = new Ship(0, 0, 30, -15, 0, 10, 0);
		Ship ship2 = new Ship(100, 100, 30, -15, 0, 20, 0);
		assertFalse(ship1.overlap(ship2));
	}
	
	@Test
	public void testOverlapTouching() {
		Ship ship1 = new Ship(0, 0, 30, -15, 0, 50, 0);
		Ship ship2 = new Ship(100, 0, 30, -15, 0, 50, 0);
		assertFalse(ship1.overlap(ship2));
		ship2 = new Ship(99, 0, 30, -15, 0, 50, 0);
		assertTrue(ship1.overlap(ship2));
	}
	
	@Test
	public void testOverlapSameShip() {
		Ship ship1 = new Ship(0, 0, 30, -15, 0, 10, 0);
		assertTrue(ship1.overlap(ship1));
	}
	
	
	
			/*
			 * |------------------------------------------------------------|
			 * | 13. The next tests test the Collision Detection methods.	|
			 * |------------------------------------------------------------| 
			 */
	
	
	
//	@Test(expected = NullPointerException.class)
//	public void testCollisionDetectionTimeShip1IsNull1() {
//		Ship ship1 = null;
//		Ship ship2 = new Ship(30, 0, 0, 0, 0, 10, 0);
//		ship1.getTimeToCollision(ship2);
//	}
	
	@Test(expected = NullPointerException.class)
	public void testCollisionDetectionTimeShip2IsNull1() {
		Ship ship1 = new Ship(0, 0, 10, 0, 0, 10, 0);
		Ship ship2 = null;
		ship1.getTimeToCollision(ship2);
	}
	
	
//	@Test(expected = NullPointerException.class)
//	public void testCollisionDetectionPositionShip1IsNull2() {
//		Ship ship1 = null;
//		Ship ship2 = new Ship(30, 0, 0, 0, 0, 10, 0);
//		ship1.getCollisionPosition(ship2);
//	}
	
	@Test(expected = NullPointerException.class)
	public void testCollisionDetectionPositionShip2IsNull2() {
		Ship ship1 = new Ship(0, 0, 10, 0, 0, 10, 0);
		Ship ship2 = null;
		ship1.getCollisionPosition(ship2);
	}
	
	
	@Test
	public void testCollisionDetectionLinear() {
		World world = new World(1000, 1000);
		Ship ship1 = new Ship(100, 100, 10, 0, 0, 10, 0);
		Ship ship2 = new Ship(130, 100, 0, 0, 0, 10, 0);
		world.addEntity(ship1);
		world.addEntity(ship2);
		double[] position = ship1.getCollisionPosition(ship2);
		assertEquals(120, position[0], EPSILON);
		assertEquals(100, position[1], EPSILON);
		assertEquals(1, ship1.getTimeToCollision(ship2), EPSILON);
	}
	
	@Test
	public void testCollisionDetectionXPOSYPOS() {
		World world = new World(1000, 1000);
		Ship ship1 = new Ship(100, 100, 10, 10, 0, 10, 0);
		Ship ship2 = new Ship(150, 150, 0, 0, 0, 10, 0);
		world.addEntity(ship1);
		world.addEntity(ship2);
		double[] position = ship1.getCollisionPosition(ship2);
//		System.out.println(position[0]);
//		System.out.println(position[1]);
//		System.out.println(facade.getTimeToCollision(ship1, ship2));
		// Position is approximately correct, I assume it's not exactly correct because of rounding.
		assertTrue( (position[0] > 135) && (position[0] < 145) );
		assertTrue( (position[1] > 135) && (position[1] < 145) );
		assertTrue(( ship1.getTimeToCollision(ship2) > 2) && (ship1.getTimeToCollision(ship2) < 4) );
	}
	@Test
	public void testCollisionDetectionXPOSYNEG() {
		World world = new World(1000, 1000);
		Ship ship1 = new Ship(100, 100, 10, -10, 0, 10, 0);
		Ship ship2 = new Ship(150, 50, 0, 0, 0, 10, 0);
		world.addEntity(ship1);
		world.addEntity(ship2);
		double[] position = ship1.getCollisionPosition(ship2);
//		System.out.println(position[0]);
//		System.out.println(position[1]);
//		System.out.println(facade.getTimeToCollision(ship1, ship2));
		// Position is as predicted here.
		assertTrue( (position[0] > 135) && (position[0] < 145) );
		assertTrue( (position[1] < 65) && (position[1] > 55) );
		assertTrue( (ship1.getTimeToCollision(ship2) > 2) && (ship1.getTimeToCollision(ship2) < 4) );
	}
	
	@Test
	public void testCollisionDetectionXNEGYPOS() {		
		World world = new World(1000, 1000);
		Ship ship1 = new Ship(100, 100, -10, 10, 0, 10, 0);
		Ship ship2 = new Ship(50, 150, 0, 0, 0, 10, 0);
		world.addEntity(ship1);
		world.addEntity(ship2);
		double[] position = ship1.getCollisionPosition(ship2);
//		System.out.println(position[0]);
//		System.out.println(position[1]);
//		System.out.println(facade.getTimeToCollision(ship1, ship2));
		// Position is as predicted here.
		assertTrue( (position[0] < 75) && (position[0] > 55));
		assertTrue( (position[1] > 135) && (position[1] < 145) );
		assertTrue( (ship1.getTimeToCollision(ship2) > 2) && (ship1.getTimeToCollision(ship2) < 4) );
	}
	
	@Test
	public void testCollisionDetectionXNEGYNEG() {
		World world = new World(1000, 1000);
		Ship ship1 = new Ship(100, 100, -10, -10, 0, 10, 0);
		Ship ship2 = new Ship(50, 50, 0, 0, 0, 10, 0);
		world.addEntity(ship1);
		world.addEntity(ship2);
		double[] position = ship1.getCollisionPosition(ship2);
//		System.out.println(position[0]);
//		System.out.println(position[1]);
//		System.out.println(facade.getTimeToCollision(ship1, ship2));
		// Position is as predicted here.
		assertTrue( (position[0] < 75) && (position[0] > 55) );
		assertTrue( (position[1] < 75) && (position[1] > 55) );
		assertTrue( (ship1.getTimeToCollision(ship2) > 2) && (ship1.getTimeToCollision(ship2) < 4) );
	}
	

	@Test(expected =IllegalArgumentException.class)
	public void testCollisionDetectionSameShip() {		
		Ship ship1 = new Ship(0, 0, 10, 0, 0, 10, 0);
		double[] position = ship1.getCollisionPosition(ship1);
		assertNull(position);
		assertEquals(Double.POSITIVE_INFINITY, ship1.getTimeToCollision(ship1), EPSILON);
	}
	
	
	
			/*
			 * |--------------------------------------------------------|
			 * | 14. The next tests test the interaction with worlds.	|
			 * |--------------------------------------------------------| 
			 */	
	
	
	
	@Test
	public void testCanHaveAsWorldT() {
		Ship ship = new Ship(100, 100, -10, -10, 0, 10, 0);
		World world = new World(1000, 1000);
		assertTrue(ship.canHaveAsWorld(world));
	}
	
	@Test
	public void testCanHaveAsWorldShipHasOtherWorld() {
		Ship ship = new Ship(100, 100, -10, -10, 0, 10, 0);
		World world1 = new World(1000, 1000);
		World world2 = new World(1000, 1000);
		ship.setWorld(world2);
		assertFalse(ship.canHaveAsWorld(world1));
	}
	
	@Test
	public void testCanHaveAsWorldShipOutsideBoundaries() {
		Ship ship = new Ship(0, 100, -10, -10, 0, 10, 0);
		World world = new World(1000, 1000);
		assertFalse(ship.canHaveAsWorld(world));
	}
	
	@Test
	public void testShipSetWorld() {
		Ship ship = new Ship(100, 100, -10, -10, 0, 10, 0);
		World world = new World(1000, 1000);
		assertTrue(ship.getWorld() == null);
		ship.setWorld(world);
		assertTrue(ship.getWorld() == world);
	}
	
	@Test
	public void testShipIsInWorldT() {
		Ship ship = new Ship(100, 100, -10, -10, 0, 10, 0);
		World world = new World(1000, 1000);
		assertTrue(ship.isInWorld(world));
	}
	
	@Test
	public void testShipIsInWorldF() {
		Ship ship = new Ship(10000, 10000, -10, -10, 0, 10, 0);
		World world = new World(1000, 1000);
		assertFalse(ship.isInWorld(world));
	}
	
	@Test
	public void testShipIsInWorldApparently() {
		Ship ship = new Ship(100, 100, -10, -10, 0, 100.1, 0);
		World world = new World(1000, 1000);
		assertTrue(ship.isInWorld(world));
	}
	
	@Test
	public void testCollideWithWorldVelocityXChanged() {
		Ship ship = new Ship(950, 100, 10, 10, 0, 50, 0);
		World world = new World(1000, 1000);
		world.addEntity(ship);
		ship.setWorld(world);
		ship.resolveCollision(world);
		assertTrue(ship.getVelocityX() == -10);
		assertTrue(ship.getVelocityY() == 10);
	}
	
	@Test
	public void testCollideWithWorldVelocityYChanged() {
		Ship ship = new Ship(100, 900, 10, 10, 0, 100, 0);
		World world = new World(1000, 1000);
		world.addEntity(ship);
		ship.setWorld(world);
		ship.resolveCollision(world);
		assertTrue(ship.getVelocityX() == -10);
		assertTrue(ship.getVelocityY() == -10);
	}
	
	@Test
	public void testCollideWithWorldVelocityXYChanged() {
		Ship ship = new Ship(950, 950, 10, 10, 0, 50, 0);
		World world = new World(1000, 1000);
		world.addEntity(ship);
		ship.setWorld(world);
		ship.resolveCollision(world);
		assertTrue(ship.getVelocityX() == -10);
		assertTrue(ship.getVelocityY() == -10);
	}
	
	@Test
	public void testCollideWithWorldHighSpeed() {
		Ship ship = new Ship(950, 950, 10, 10, 0, 50, 0);
		World world = new World(1000, 1000);
		world.addEntity(ship);
		ship.setWorld(world);
		ship.resolveCollision(world);
		assertTrue(ship.getVelocityX() == -10);
		assertTrue(ship.getVelocityY() == -10);
	}
	
	
	
			/*
 			 * |----------------------------------------------------------------|
 			 * | 15. The next tests test the interaction with other entities.	|
 			 * |----------------------------------------------------------------| 
			 */	
	
	
	
	@Test
	public void testCollideWithShip() {
		Ship ship1 = new Ship(100, 100, -10, -10, 0, 25, 0);
		Ship ship2 = new Ship(50, 50, 0, 0, 0, 25, 0);
		World world = new World(1000, 1000);
		world.addEntity(ship1);
		world.addEntity(ship2);
		assertTrue(ship1.getVelocityX() == -10);
		assertTrue(ship1.getVelocityY() == -10);
		assertTrue(ship2.getVelocityX() == 0);
		assertTrue(ship2.getVelocityY() == 0);
		ship1.resolveCollision(ship2);
		assertFalse(ship1.getVelocityX() == -10);
		assertFalse(ship1.getVelocityY() == -10);
		assertFalse(ship2.getVelocityX() == 0);
		assertFalse(ship2.getVelocityY() == 0);
	}
	
	@Test
	public void testCollideWithBulletSource() {
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
	public void testCollideWithBulletNotSource() {
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
		
}
	