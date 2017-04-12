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
import asteroids.util.ModelException;

/* // TODO Rewrite facade ships to Ships
 * // TODO get rid of throwsModelException
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
		Ship ship3 = new Ship(100, 200, 100, 200, 2*Math.PI, 20, 0);
		assertNotNull(ship3.getOrientation());
		assertEquals(2*Math.PI, ship3.getOrientation(), EPSILON);
	}
	
	@Test (expected = AssertionError.class)
	public void testCreateShipOrientationOverflow() {
		new Ship(100, 200, 10, -10, 10*Math.PI, 20, 0);
	}
	
	@Test(expected = ModelException.class)
	public void testCreateShipOisNeg() throws ModelException {
		facade.createShip(100, 200, 10, -10, 0, -Math.PI);
	}
	

	@Test(expected = ModelException.class)
	public void testCreateShipOrientationIsPosInfinity() throws ModelException {
		facade.createShip(100, 200, 10, -10, 0, Double.POSITIVE_INFINITY);
	}
	
	@Test(expected = ModelException.class)
	public void testCreateShipOrientationIsNegInfinity() throws ModelException {
		facade.createShip(100, 200, 10, -10, 0, Double.NEGATIVE_INFINITY);
	}
	
	@Test(expected = ModelException.class)
	public void testCreateShipOisNaN() throws ModelException {
		facade.createShip(100, 200, 10, -10, 0, Double.NaN);
	}
	
	
		
			/*
			 * |------------------------------------|
			 * | 6. The next tests test the Mass.	|
			 * |------------------------------------| 
			 */



	@Test
	public void testCreateShipMassGeneric() throws ModelException {
		Ship ship = new Ship(100, 200, 10, -10, Math.PI, 20, Math.pow(10, 20));
		assertEquals(Math.pow(10, 20), ship.getMass(), EPSILON);
	}
	
	
	@Test
	public void testCreateShipMassUnderZero() throws ModelException {
		Ship ship = new Ship(100, 200, 10, -10, Math.PI, 20, -10);
		assertEquals((4/3) * Math.PI * Math.pow(20, 3) * ship.getDensity(), ship.getMass(), EPSILON);
	}
	
	@Test
	public void testCreateShipMassZero() throws ModelException {
		Ship ship = new Ship(100, 200, 10, -10, Math.PI, 20, 0);
		assertEquals((4/3) * Math.PI * Math.pow(20, 3) * ship.getDensity(), ship.getMass(), EPSILON);
	}

	

			/*
			 * |--------------------------------------------------------|
			 * | 7. The next tests test the interaction with bullets.	|
			 * |--------------------------------------------------------| 
			 */



	@Test // TODO Technically, this is tested in other methods and is thus not really necessary
	public void testShipCanHaveAsBulletTrue() throws ModelException {
		Bullet bullet = new Bullet(100, 200, 0, 0, 20);	
		Ship ship1 = new Ship(100, 200, 10, -10, Math.PI, 20, 1);
		Ship ship2 = new Ship(200, 200, 10, -10, Math.PI, 20, 1);
		bullet.setShip(ship2);
		assertTrue(ship1.canHaveAsBullet(bullet));
	}
	
	@Test
	public void testShipCanHaveAsBulletFalse() throws ModelException {
		Bullet bullet = new Bullet(100, 200, 0, 0, 20);
		Ship ship = new Ship(100, 200, 10, -10, Math.PI, 20, 1);
		ship.loadBullet(bullet);
		assertFalse(ship.canHaveAsBullet(bullet));
	}
	
	@Test
	public void testShipCanHaveAsBulletBulletIsNull() throws ModelException {
		Bullet bullet = null;
		Ship ship = new Ship(100, 200, 10, -10, Math.PI, 20, 1);
		assertFalse(ship.canHaveAsBullet(bullet));
	}
	
	// TODO More of these tests:
	@Test
	public void testShipBulletLoadOnShip() throws ModelException {
		Bullet bullet = new Bullet(100, 200, 0, 0, 20);	
		Ship ship = new Ship(100, 200, 10, -10, Math.PI, 20, 1);
		ship.loadBullet(bullet);
		assertTrue(ship.getAllBullets().contains(bullet));
	}
	
	
	@Test
	public void testShipBulletRemovedWhenFired() throws ModelException {
		Bullet bullet = new Bullet(100, 200, 0, 0, 20);	
		Ship ship = new Ship(100, 200, 10, -10, Math.PI, 20, 1);
		World world = new World(1000, 1000);
		ship.setWorld(world);
		ship.loadBullet(bullet);
		ship.fireBullet(bullet);
		assertFalse(ship.getAllBullets().contains(bullet));
	}
	

	
			/*
			 * |--------------------------------------------|
			 * | 8. The next tests test the Move method.	|
			 * |--------------------------------------------| 
			 */	

	
	
	@Test(expected = ModelException.class)
	public void testMoveShipIsNull() throws ModelException {
		Ship ship = null;
		facade.move(ship, 1);
	}
	
	
	@Test
	public void testMoveGeneric() throws ModelException {
		Ship ship = facade.createShip(100, 100, 30, -15, 20, 0);
		facade.move(ship, 1);
		double[] position = facade.getShipPosition(ship);
		assertNotNull(position);
		assertEquals(130, position[0], EPSILON);
		assertEquals(85, position[1], EPSILON);
	}
	
	
	@Test(expected = ModelException.class)
	public void testMoveTisNeg() throws ModelException {
		Ship ship = facade.createShip(100, 100, 30, -15, 20, 0);
		facade.move(ship, -1);
	}
	
	@Test
	public void testMoveTisZero() throws ModelException {
		Ship ship = facade.createShip(100, 100, 30, -15, 20, 0);
		facade.move(ship, 0);
		double[] position = facade.getShipPosition(ship);
		assertNotNull(position);
		assertEquals(100, position[0], EPSILON);
		assertEquals(100, position[1], EPSILON);
	}
	
	
	@Test(expected = ModelException.class)
	public void testMoveTisPosInfinity() throws ModelException {
		Ship ship = facade.createShip(100, 100, 30, -15, 20, 0);
		facade.move(ship, Double.POSITIVE_INFINITY);
	}
	
	@Test(expected = ModelException.class)
	public void testMoveTisNegInfinity() throws ModelException {
		Ship ship = facade.createShip(100, 100, 30, -15, 20, 0);
		facade.move(ship, Double.NEGATIVE_INFINITY);
	}
	
	@Test(expected = ModelException.class)
	public void testMoveTisNaN() throws ModelException {
		Ship ship = facade.createShip(100, 100, 30, -15, 20, 0);
		facade.move(ship, Double.NaN);
	}
	
	
	
			/*
			 * |--------------------------------------------|
			 * | 9. The next tests test the Thrust method.	|
			 * |--------------------------------------------| 
			 */
	
	
	
	@Test
	public void testThrustX() throws ModelException {
		Ship ship = facade.createShip(100, 100, 0, 0, 20, 0);
		facade.thrust(ship, 10);
		assertNotNull(facade.getShipVelocity(ship));
		assertEquals(10, facade.getShipVelocity(ship)[0], EPSILON);
		assertEquals(0, facade.getShipVelocity(ship)[1], EPSILON);
	}
	
	@Test
	public void testThrustY() throws ModelException {
		Ship ship = facade.createShip(100, 100, 0, 0, 20, (Math.PI)/2);
		facade.thrust(ship, 15);
		assertNotNull(facade.getShipVelocity(ship));
		assertEquals(0, facade.getShipVelocity(ship)[0], EPSILON);
		assertEquals(15, facade.getShipVelocity(ship)[1], EPSILON);
	}
	
	
	@Test
	public void testThrustXWhithMovingStart() throws ModelException {
		Ship ship = facade.createShip(100, 100, 10, 10, 20, 0);
		facade.thrust(ship, 5);
		assertNotNull(facade.getShipVelocity(ship));
		assertEquals(15, facade.getShipVelocity(ship)[0], EPSILON);
		assertEquals(10, facade.getShipVelocity(ship)[1], EPSILON);
	}
	
	@Test
	public void testThrustYWhithMovingStart() throws ModelException {
		Ship ship = facade.createShip(100, 100, 10, 10, 20, (Math.PI)/2);
		facade.thrust(ship, 7);
		assertNotNull(facade.getShipVelocity(ship));
		assertEquals(10, facade.getShipVelocity(ship)[0], EPSILON);
		assertEquals(17, facade.getShipVelocity(ship)[1], EPSILON);
	}
		

	@Test
	public void testThrustOverflow() throws ModelException {
		Ship ship = facade.createShip(100, 100, 0, 0, 20, 0);
		facade.thrust(ship, 500000);
		assertEquals(0, facade.getShipVelocity(ship)[0], EPSILON);
		assertEquals(0, facade.getShipVelocity(ship)[1], EPSILON);
	}
	
	@Test
	public void testThrustNeg() throws ModelException {
		Ship ship = facade.createShip(100, 100, 100, 100, 20, 0);
		facade.thrust(ship, -10);
		assertNotNull(facade.getShipVelocity(ship));
		assertEquals(100, facade.getShipVelocity(ship)[0], EPSILON);
		assertEquals(100, facade.getShipVelocity(ship)[1], EPSILON);
	}
	
	
	@Test
	public void testThrustPosInfinity() throws ModelException {
		Ship ship = facade.createShip(100, 100, 0, 0, 20, 0);
		facade.thrust(ship, Double.POSITIVE_INFINITY);
		assertEquals(0, facade.getShipVelocity(ship)[0], EPSILON);
		assertEquals(0, facade.getShipVelocity(ship)[1], EPSILON);
	}
	
	@Test
	public void testThrustNegInfinity() throws ModelException {
		Ship ship = facade.createShip(100, 100, 0, 0, 20, 0);
		facade.thrust(ship, Double.NEGATIVE_INFINITY);
		assertEquals(0, facade.getShipVelocity(ship)[0], EPSILON);
		assertEquals(0, facade.getShipVelocity(ship)[1], EPSILON);
	}
	
	@Test
	public void testThrustNaN() throws ModelException {
		Ship ship = facade.createShip(100, 100, 0, 0, 20, 0);
		facade.thrust(ship, Double.NaN);
		assertEquals(0, facade.getShipVelocity(ship)[0], EPSILON);
		assertEquals(0, facade.getShipVelocity(ship)[1], EPSILON);
	}
	

	
			 /*
			  * |-------------------------------------------|
			  * | 10. The next tests test the Turn method.	|
			  * |-------------------------------------------| 
			  */	
	
	
	
	@Test
	public void testTurnGeneric() throws ModelException {
		Ship ship = facade.createShip(100, 100, 30, -15, 20, 0);
		facade.turn(ship, Math.PI);
		assertNotNull(facade.getShipOrientation(ship));
		assertEquals(Math.PI, facade.getShipOrientation(ship), EPSILON);
	}
	
	
	@Test
	public void testTurnOisNeg() throws ModelException {
		Ship ship = new Ship(100, 100, 30, -15, Math.PI, 20, 20);
		facade.turn(ship, -Math.PI);
		assertNotNull(ship.getOrientation());
		assertEquals(0, ship.getOrientation(), EPSILON);
	}
	
	@Test
	public void testTurnOisZero() throws ModelException {
		Ship ship = facade.createShip(100, 100, 30, -15, 20, 0);
		facade.turn(ship, 0);
		assertNotNull(facade.getShipOrientation(ship));
		assertEquals(0, facade.getShipOrientation(ship), EPSILON);
	}
	
	
	@Test
	public void testTurnOOverflow() throws ModelException {
		Ship ship = facade.createShip(100, 100, 30, -15, 20, 2*Math.PI);
		facade.turn(ship, Math.PI);
		assertNotNull(facade.getShipOrientation(ship));
		assertEquals(Math.PI, facade.getShipOrientation(ship), EPSILON);
	}
	
	@Test
	public void testTurnOUnderflow() throws ModelException {
		Ship ship = facade.createShip(100, 100, 30, -15, 20, 0);
		facade.turn(ship, -2*Math.PI);
		assertNotNull(facade.getShipOrientation(ship));
		assertEquals(0, facade.getShipOrientation(ship), EPSILON);
	}
	
	
	@Test(expected = ModelException.class)
	public void testTurnOisPosInfinity() throws ModelException {
		Ship ship = facade.createShip(100, 100, 30, -15, Math.PI, 20);
		facade.turn(ship, Double.POSITIVE_INFINITY);
	}
	
	@Test(expected = ModelException.class)
	public void testTurnOisNegInfinity() throws ModelException {
		Ship ship = facade.createShip(100, 100, 30, -15, Math.PI, 20);
		facade.turn(ship, Double.NEGATIVE_INFINITY);
	}
	
	@Test(expected = ModelException.class)
	public void testTurnOisNaN() throws ModelException {
		Ship ship = facade.createShip(100, 100, 30, -15, Math.PI, 20);
		facade.turn(ship, Double.NaN);
	}
	

	
			 /*
			  * |-----------------------------------------------|
			  * | 11. The next tests test the Distance method.	|
			  * |-----------------------------------------------| 
			  */
	
	
	
	@Test(expected = ModelException.class)
	public void testDistanceShip1IsNull() throws ModelException {
		Ship ship1 = null;
		Ship ship2 = facade.createShip(100, 100, 30, -15, 20, 0);
		assertEquals(0, facade.getDistanceBetween(ship1, ship2), EPSILON);
	}
	
	@Test(expected = ModelException.class)
	public void testDistanceShip2IsNull() throws ModelException {
		Ship ship1 = facade.createShip(100, 100, 30, -15, 20, 0);
		Ship ship2 = null;
		assertEquals(0, facade.getDistanceBetween(ship1, ship2), EPSILON);
	}
	
	
	@Test
	public void testDistanceIsPositiveLinearX() throws ModelException {
		Ship ship1 = facade.createShip(130, 0, 0, 0, 10, 0);;
		Ship ship2 = facade.createShip(100, 0, 0, 0, 10, 0);
		assertEquals(10, facade.getDistanceBetween(ship1, ship2), EPSILON);
	}
	
	@Test
	public void testDistanceIsPositiveLinearY() throws ModelException {
		Ship ship1 = facade.createShip(0, 140, 0, 0, 10, 0);;
		Ship ship2 = facade.createShip(0, 100, 0, 0, 10, 0);
		assertEquals(20, facade.getDistanceBetween(ship1, ship2), EPSILON);
	}
	
	@Test
	public void testDistanceIsNegative() throws ModelException {
		Ship ship2 = facade.createShip(105, 100, 0, 0, 10, 0);;
		Ship ship1 = facade.createShip(100, 100, 0, 0, 10, 0);
		assertEquals(-15, facade.getDistanceBetween(ship1, ship2), EPSILON);
	}
	
	@Test
	public void testDistanceIsZero() throws ModelException {
		Ship ship1 = facade.createShip(130, 0, 0, 0, 10, 0);;
		Ship ship2 = facade.createShip(100, 0, 0, 0, 20, 0);
		assertEquals(0, facade.getDistanceBetween(ship1, ship2), EPSILON);
	}
	
	
	@Test
	public void testDistanceSameShip() throws ModelException {
		Ship ship1 = facade.createShip(100, 100, 30, -15, 20, 0);
		assertEquals(0, facade.getDistanceBetween(ship1, ship1), EPSILON);
	}
	
	@Test
	public void testDistanceOver00() throws ModelException {
		Ship ship1 = facade.createShip(20, 20, 30, 15, 10, 0);
		Ship ship2 = facade.createShip(-20, -20, 0, 0, 10, 0);
		assertEquals(36.568542, facade.getDistanceBetween(ship1, ship2), EPSILON);
	}
	

	
			 /*
			  * |-----------------------------------------------|
			  * | 12. The next tests test the Overlap method.	|
			  * |-----------------------------------------------| 
			  */	
	
	
	
	@Test(expected = ModelException.class)
	public void testOverlapShip1IsNull() throws ModelException {
		Ship ship1 = null;
		Ship ship2 = facade.createShip(100, 100, 30, -15, 20, 0);
		assertFalse(facade.overlap(ship1, ship2));
	}
	
	@Test(expected = ModelException.class)
	public void testOverlapShip2IsNull() throws ModelException {
		Ship ship1 = facade.createShip(100, 100, 30, -15, 20, 0);
		Ship ship2 = null;
		assertFalse(facade.overlap(ship1, ship2));
	}
	
	
	@Test
	public void testOverlapTrue() throws ModelException {
		Ship ship1 = new Ship(0, 0, 30, -15, Math.PI, 20, 20);
		Ship ship2 = new Ship(5, 5, 30, -15, Math.PI, 20, 20);
		assertTrue(ship1.overlap(ship2));
	}
	
	@Test
	public void testOverlapFalse() throws ModelException {
		Ship ship1 = facade.createShip(0, 0, 30, -15, 10, 0);
		Ship ship2 = facade.createShip(100, 100, 30, -15, 20, 0);
		assertFalse(facade.overlap(ship1, ship2));
	}
	
	
	@Test
	public void testSameShip() throws ModelException {
		Ship ship1 = facade.createShip(0, 0, 30, -15, 10, 0);
		assertTrue(facade.overlap(ship1, ship1));
	}
	
	
	
			/*
			 * |------------------------------------------------------------|
			 * | 13. The next tests test the Collision Detection methods.	|
			 * |------------------------------------------------------------| 
			 */
	
	
	
	@Test(expected = ModelException.class)
	public void testCollisionDetectionTimeShip1IsNull1() throws ModelException {
		Ship ship1 = null;
		Ship ship2 = facade.createShip(30, 0, 0, 0, 10, 0);
		facade.getTimeToCollision(ship1, ship2);
	}
	
	@Test(expected = ModelException.class)
	public void testCollisionDetectionTimeShip2IsNull1() throws ModelException {
		Ship ship1 = facade.createShip(0, 0, 10, 0, 10, 0);
		Ship ship2 = null;
		facade.getTimeToCollision(ship1, ship2);
	}
	
	
	@Test(expected = ModelException.class)
	public void testCollisionDetectionPositionShip1IsNull2() throws ModelException {
		Ship ship1 = null;
		Ship ship2 = facade.createShip(30, 0, 0, 0, 10, 0);
		facade.getCollisionPosition(ship1, ship2);
	}
	
	@Test(expected = ModelException.class)
	public void testCollisionDetectionPositionShip2IsNull2() throws ModelException {
		Ship ship1 = facade.createShip(0, 0, 10, 0, 10, 0);
		Ship ship2 = null;
		facade.getCollisionPosition(ship1, ship2);
	}
	
	
	@Test
	public void testCollisionDetectionLinear() throws ModelException {
		Ship ship1 = facade.createShip(0, 0, 10, 0, 10, 0);
		Ship ship2 = facade.createShip(30, 0, 0, 0, 10, 0);
		double[] position = facade.getCollisionPosition(ship1, ship2);
		assertEquals(20, position[0], EPSILON);
		assertEquals(0, position[1], EPSILON);
		assertEquals(1, facade.getTimeToCollision(ship1, ship2), EPSILON);
	}
	
	@Test
	public void testCollisionDetectionXPOSYPOS() throws ModelException {
		Ship ship1 = facade.createShip(0, 0, 10, 10, 10, 0);
		Ship ship2 = facade.createShip(50, 50, 0, 0, 10, 0);
		double[] position = facade.getCollisionPosition(ship1, ship2);
//		System.out.println(position[0]);
//		System.out.println(position[1]);
//		System.out.println(facade.getTimeToCollision(ship1, ship2));
		// Position is approximately correct, I assume it's not exactly correct because of rounding.
		assertTrue( (position[0] > 35) && (position[0] < 45) );
		assertTrue( (position[1] > 35) && (position[1] < 45) );
		assertTrue( (facade.getTimeToCollision(ship1, ship2) > 2) && (facade.getTimeToCollision(ship1, ship2) < 4) );
	}
	// TODO testCollisionDetectionXPOSYNEG()
	@Test
	public void testCollisionDetectionXNEGYPOS() throws ModelException {		
		Ship ship1 = facade.createShip(0, 0, -10, 10, 10, 0);
		Ship ship2 = facade.createShip(-50, 50, 0, 0, 10, 0);
		double[] position = facade.getCollisionPosition(ship1, ship2);
//		System.out.println(position[0]);
//		System.out.println(position[1]);
//		System.out.println(facade.getTimeToCollision(ship1, ship2));
		// Position is as predicted here.
		assertTrue( (position[0] < -35) && (position[0] > -45));
		assertTrue( (position[1] > 35) && (position[1] < 45) );
		assertTrue( (facade.getTimeToCollision(ship1, ship2) > 2) && (facade.getTimeToCollision(ship1, ship2) < 4) );
	}
	
	@Test
	public void testCollisionDetectionXNEGYNEG() throws ModelException {
		Ship ship1 = facade.createShip(0, 0, -10, -10, 10, 0);
		Ship ship2 = facade.createShip(-50, -50, 0, 0, 10, 0);
		double[] position = facade.getCollisionPosition(ship1, ship2);
//		System.out.println(position[0]);
//		System.out.println(position[1]);
//		System.out.println(facade.getTimeToCollision(ship1, ship2));
		// Position is as predicted here.
		assertTrue( (position[0] < -35) && (position[0] > -45) );
		assertTrue( (position[1] < -35) && (position[1] > -45) );
		assertTrue( (facade.getTimeToCollision(ship1, ship2) > 2) && (facade.getTimeToCollision(ship1, ship2) < 4) );
	}
	

	@Test(expected = ModelException.class)
	public void testCollisionDetectionSameShip() throws ModelException {		
		Ship ship1 = facade.createShip(0, 0, 10, 0, 10, 0);
		double[] position = facade.getCollisionPosition(ship1, ship1);
		assertNull(position);
		assertEquals(Double.POSITIVE_INFINITY, facade.getTimeToCollision(ship1, ship1), EPSILON);
	}
	
}
	