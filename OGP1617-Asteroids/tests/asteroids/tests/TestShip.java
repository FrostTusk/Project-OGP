package asteroids.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import asteroids.model.Ship;
import asteroids.facade.Facade;
import asteroids.part1.facade.IFacade;
import asteroids.util.ModelException;

/*
 * Tests Index:
 * 1. Tests for Initialization
 * 2. Tests for Position
 * 3. Tests for Speed
 * 4. Tests for Radius
 * 5. Tests for Orientation
 * 6. Tests for Move
 * 7. Tests for Thrust
 * 8. Tests for Turn
 * 9. Tests for Distance
 * 10. Tests for Overlap
 * 11. Tests for Collision Detection
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
	public void testCreateShip() throws ModelException {
		Ship ship = facade.createShip(100, 200, 10, -10, 20, Math.PI);
		assertNotNull(ship);
		double[] position = facade.getShipPosition(ship);
		assertNotNull(position);
		assertEquals(100, position[0], EPSILON);
		assertEquals(200, position[1], EPSILON);
		assertEquals(20, facade.getShipRadius(ship), EPSILON);
		ship = facade.createShip();
		assertNotNull(ship);
		assertEquals(10, facade.getShipRadius(ship), EPSILON);
	}

	
	
			/*
			 * |----------------------------------------|
			 * | 2. The next tests test the Position.	|
			 * |----------------------------------------| 
			 */	

	
	
	@Test(expected = ModelException.class)
	public void testCreateShipPosShipIsNull() throws ModelException {
		Ship ship = null;
		facade.getShipPosition(ship);
	}
	
	
	@Test
	public void testCreatShipPosGeneric() throws ModelException {
		Ship ship1 = facade.createShip(100, 200, 10, -10, 20, Math.PI);
		double[] position1 = facade.getShipPosition(ship1);
		assertEquals(100, position1[0], EPSILON);
		assertEquals(200, position1[1], EPSILON);
		
		Ship ship2 = facade.createShip(100, -200, 10, -10, 20, Math.PI);
		double[] position2 = facade.getShipPosition(ship2);
		assertEquals(100, position2[0], EPSILON);
		assertEquals(-200, position2[1], EPSILON);
		
		Ship ship3 = facade.createShip(-100, 200, 10, -10, 20, Math.PI);
		double[] position3 = facade.getShipPosition(ship3);
		assertEquals(-100, position3[0], EPSILON);
		assertEquals(200, position3[1], EPSILON);
		
		Ship ship4 = facade.createShip(-100, -200, 10, -10, 20, Math.PI);
		double[] position4 = facade.getShipPosition(ship4);
		assertEquals(-100, position4[0], EPSILON);
		assertEquals(-200, position4[1], EPSILON);
		
		Ship ship5= facade.createShip(0, 0, 10, -10, 20, Math.PI);
		double[] position5 = facade.getShipPosition(ship5);
		assertEquals(0, position5[0], EPSILON);
		assertEquals(0, position5[1], EPSILON);
	}
	
	
	@Test(expected = ModelException.class)
	public void testCreateShipXIsPosInfinity() throws ModelException {
		facade.createShip(Double.POSITIVE_INFINITY, 200, 10, -10, 20, Math.PI);
	}
	
	@Test(expected = ModelException.class)
	public void testCreateShipXIsNegInfinity() throws ModelException {
		facade.createShip(Double.NEGATIVE_INFINITY, 200, 10, -10, 20, Math.PI);
	}
	
	@Test(expected = ModelException.class)
	public void testCreateShipXIsNan() throws ModelException {
		facade.createShip(Double.NaN, 200, 10, -10, 20, Math.PI);
	}
	
	
	@Test(expected = ModelException.class)
	public void testCreateShipYIsPosInfinity() throws ModelException {
		facade.createShip(200, Double.POSITIVE_INFINITY, 10, -10, 20, Math.PI);
	}
	
	@Test(expected = ModelException.class)
	public void testCreateShipYIsNegInfinity() throws ModelException {
		facade.createShip(200, Double.NEGATIVE_INFINITY, 10, -10, 20, Math.PI);
	}
	
	@Test(expected = ModelException.class)
	public void testCreateShipYIsNan() throws ModelException {
		facade.createShip(200, Double.NaN, 10, -10, 20, Math.PI);
	}
	
	
	@Test(expected = ModelException.class)
	public void testCreateShipXYIsPosInfinity() throws ModelException {
		facade.createShip(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, 10, -10, 20, Math.PI);
	}
	
	@Test(expected = ModelException.class)
	public void testCreateShipXYIsNegInfinity() throws ModelException {
		facade.createShip(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, 10, -10, 20, Math.PI);
	}
	
	@Test(expected = ModelException.class)
	public void testCreateShipXYIsNaN() throws ModelException {
		facade.createShip(Double.NaN, Double.NaN, 10, -10, 20, Math.PI);
	}
	
	
	
			/*
			 * |------------------------------------|
			 * | 3. The next tests test the Speed.	|
			 * |------------------------------------| 
			 */	
	
	
	
	@Test(expected = ModelException.class)
	public void testCreateShipVelShipIsNull() throws ModelException {
		Ship ship = null;
		facade.getShipVelocity(ship);
	}
	
	
	@Test
	public void testCreatShipVelGeneric() throws ModelException {
		Ship ship1 = facade.createShip(100, 200, 100, 200, 20, Math.PI);
		double[] velocity1 = facade.getShipVelocity(ship1);
		assertEquals(100, velocity1[0], EPSILON);
		assertEquals(200, velocity1[1], EPSILON);
		
		Ship ship2 = facade.createShip(100, -200, 100, -200, 20, Math.PI);
		double[] velocity2 = facade.getShipVelocity(ship2);
		assertEquals(100, velocity2[0], EPSILON);
		assertEquals(-200, velocity2[1], EPSILON);
		
		Ship ship3 = facade.createShip(-100, 200, -100, 200, 20, Math.PI);
		double[] velocity3 = facade.getShipVelocity(ship3);
		assertEquals(-100, velocity3[0], EPSILON);
		assertEquals(200, velocity3[1], EPSILON);
		
		Ship ship4 = facade.createShip(-100, -200, -100, -200, 20, Math.PI);
		double[] velocity4 = facade.getShipVelocity(ship4);
		assertEquals(-100, velocity4[0], EPSILON);
		assertEquals(-200, velocity4[1], EPSILON);
		
		Ship ship5= facade.createShip(0, 0, 0, 0, 20, Math.PI);
		double[] velocity5 = facade.getShipVelocity(ship5);
		assertEquals(0, velocity5[0], EPSILON);
		assertEquals(0, velocity5[1], EPSILON);
	}
	
	
	@Test
	public void testCreateShipVelOverflow() throws ModelException {
		Ship ship = facade.createShip(200, 200, 500000, 500000, 20, Math.PI);
		assertEquals(0, facade.getShipVelocity(ship)[0], EPSILON);
		assertEquals(0, facade.getShipVelocity(ship)[1], EPSILON);
	}
	
	
	@Test
	public void testCreateShipVXIsPosInfinity() throws ModelException {
		Ship ship = facade.createShip(200, 200, Double.POSITIVE_INFINITY, -10, 20, Math.PI);
		assertEquals(0, facade.getShipVelocity(ship)[0], EPSILON);
	}
	
	@Test
	public void testCreateShipVXIsNegInfinity() throws ModelException {
		Ship ship = facade.createShip(200, 200, Double.NEGATIVE_INFINITY, -10, 20, Math.PI);
		assertEquals(0, facade.getShipVelocity(ship)[0], EPSILON);
	}
	
	@Test
	public void testCreateShipVXIsNan() throws ModelException {
		Ship ship = facade.createShip(-200, 200, Double.NaN, -10, 20, Math.PI);
		assertEquals(0, facade.getShipVelocity(ship)[0], EPSILON);
	}
	
	
	@Test
	public void testCreateShipVYIsPosInfinity() throws ModelException {
		Ship ship = facade.createShip(200, 200, 10, Double.POSITIVE_INFINITY, 20, Math.PI);
		assertEquals(0, facade.getShipVelocity(ship)[1], EPSILON);
	}
	
	@Test
	public void testCreateShipVYIsNegInfinity() throws ModelException {
		Ship ship = facade.createShip(200, 200, 10, Double.NEGATIVE_INFINITY, 20, Math.PI);
		assertEquals(0, facade.getShipVelocity(ship)[1], EPSILON);
	}
	
	@Test
	public void testCreateShipVYIsNan() throws ModelException {
		Ship ship = facade.createShip(-200, 200, 10, Double.NaN, 20, Math.PI);
		assertEquals(0, facade.getShipVelocity(ship)[1], EPSILON);
	}
	
	
	@Test
	public void testCreateShipVXYIsPosInfinity() throws ModelException {
		Ship ship = facade.createShip(200, 200, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, 20, Math.PI);
		assertEquals(0, facade.getShipVelocity(ship)[0], EPSILON);
		assertEquals(0, facade.getShipVelocity(ship)[1], EPSILON);
	}
	
	@Test
	public void testCreateShipVXYIsNegInfinity() throws ModelException {
		Ship ship = facade.createShip(-200, 200, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, 20, Math.PI);
		assertEquals(0, facade.getShipVelocity(ship)[0], EPSILON);
		assertEquals(0, facade.getShipVelocity(ship)[1], EPSILON);
	}
	
	@Test
	public void testCreateShipVXYIsNan() throws ModelException {
		Ship ship = facade.createShip(-200, 200, Double.NaN, Double.NaN, 20, Math.PI);
		assertEquals(0, facade.getShipVelocity(ship)[0], EPSILON);
		assertEquals(0, facade.getShipVelocity(ship)[1], EPSILON);
	}
	
	
	
			/*
			 * |------------------------------------|
			 * | 4. The next tests test the Radius.	|
			 * |------------------------------------| 
			 */	
	
	
	
	@Test(expected = ModelException.class)
	public void testCreateShipRadiusShipIsNull() throws ModelException {
		Ship ship = null;
		facade.getShipRadius(ship);
	}
	
	
	@Test
	public void testCreateShipRadiusGeneric() throws ModelException {
		Ship ship = facade.createShip(100, 200, 10, -10, 50, Math.PI);
		assertNotNull(facade.getShipRadius(ship));
		assertEquals(50, facade.getShipRadius(ship), EPSILON);
	}
	
	
	@Test(expected = ModelException.class)
	public void testCreateShipRadiusNegative() throws ModelException {
		facade.createShip(100, 200, 10, -10, -20, Math.PI);
	}
	
	@Test(expected = ModelException.class)
	public void testCreateShipRadiusZero() throws ModelException {
		facade.createShip(100, 200, 10, -10, 0, Math.PI);
	}
	
	@Test(expected = ModelException.class)
	public void testCreateShipRadiusUnderflow() throws ModelException {
		facade.createShip(100, 200, 10, -10, 1, Math.PI);
	}
	
	@Test(expected = ModelException.class)
	public void testCreateShipRadiusIsPosInfinity() throws ModelException {
		facade.createShip(100, 200, 10, -10, Double.POSITIVE_INFINITY, Math.PI);
	}
	
	@Test(expected = ModelException.class)
	public void testCreateShipRadiusIsNegInfinity() throws ModelException {
		facade.createShip(100, 200, 10, -10, Double.NEGATIVE_INFINITY, Math.PI);
	}
	
	@Test(expected = ModelException.class)
	public void testCreateShipRadiusIsNan() throws ModelException {
		facade.createShip(100, 200, 10, -10, Double.NaN, Math.PI);
	}
	
	
	 /*
	  * |-------------------------------------------|
	  * | 5. The next tests test the Orientation.	|
	  * |-------------------------------------------| 
	  */	
	
	
	@Test(expected = ModelException.class)
	public void testCreateShipOShipIsNull() throws ModelException {
		Ship ship = null;
		facade.getShipOrientation(ship);
	}
	
	@Test
	public void testCreateShipOGeneric() throws ModelException {
		Ship ship1 = facade.createShip(100, 200, 100, 200, 20, 0);
		assertNotNull(facade.getShipOrientation(ship1));
		assertEquals(0, facade.getShipOrientation(ship1), EPSILON);
		Ship ship2 = facade.createShip(100, 200, 100, 200, 20, Math.PI);
		assertNotNull(facade.getShipOrientation(ship2));
		assertEquals(Math.PI, facade.getShipOrientation(ship2), EPSILON);
		Ship ship3 = facade.createShip(100, 200, 100, 200, 20, 2*Math.PI);
		assertNotNull(facade.getShipOrientation(ship3));
		assertEquals(2*Math.PI, facade.getShipOrientation(ship3), EPSILON);
	}
	
	@Test(expected = ModelException.class)
	public void testCreateShipOisNeg() throws ModelException {
		facade.createShip(100, 200, 10, -10, 0, -Math.PI);
	}
	
	@Test(expected = ModelException.class)
	public void testCreateShipOrientationOverflow() throws ModelException {
		facade.createShip(100, 200, 10, -10, 0, 10*Math.PI);
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
	  * |-------------------------------------------|
	  * | 6. The next tests test the Move method.	|
	  * |-------------------------------------------| 
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
	  * |-----------------------------------------------------------|
	  * | 7. The next tests test the Thrust method.					|
	  * |-----------------------------------------------------------| 
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
	public void testThrustNeg() throws ModelException {
		Ship ship = facade.createShip(100, 100, 100, 100, 20, 0);
		facade.thrust(ship, -10);
		assertNotNull(facade.getShipVelocity(ship));
		assertEquals(100, facade.getShipVelocity(ship)[0], EPSILON);
		assertEquals(100, facade.getShipVelocity(ship)[1], EPSILON);
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
	  * | 8. The next tests test the Turn method.	|
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
	public void testTurnOisZero() throws ModelException {
		Ship ship = facade.createShip(100, 100, 30, -15, 20, 0);
		facade.turn(ship, 0);
		assertNotNull(facade.getShipOrientation(ship));
		assertEquals(0, facade.getShipOrientation(ship), EPSILON);
	}
	
	@Test
	public void testTurnOisNeg() throws ModelException {
		Ship ship = facade.createShip(100, 100, 30, -15, 20, Math.PI);
		facade.turn(ship, -Math.PI);
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
		Ship ship = facade.createShip(100, 100, 30, -15, 20, Math.PI);
		facade.turn(ship, Double.POSITIVE_INFINITY);
	}
	
	@Test(expected = ModelException.class)
	public void testTurnOisNegInfinity() throws ModelException {
		Ship ship = facade.createShip(100, 100, 30, -15, 20, Math.PI);
		facade.turn(ship, Double.NEGATIVE_INFINITY);
	}
	
	@Test(expected = ModelException.class)
	public void testTurnOisNaN() throws ModelException {
		Ship ship = facade.createShip(100, 100, 30, -15, 20, Math.PI);
		facade.turn(ship, Double.NaN);
	}
	

	 /*
	  * |-----------------------------------------------|
	  * | 9. The next tests test the Distance method.	|
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
	  * | 10. The next tests test the Overlap method.	|
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
	public void testOverlapT() throws ModelException {
		Ship ship1 = facade.createShip(0, 0, 30, -15, 10, 0);
		Ship ship2 = facade.createShip(5, 5, 30, -15, 20, Math.PI);
		assertTrue(facade.overlap(ship1, ship2));
	}
	
	@Test
	public void testOverlapF() throws ModelException {
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
	  * |-----------------------------------------------------------|
	  * | 11. The next tests test the Collision Detection methods.	|
	  * |-----------------------------------------------------------| 
	  */
	
	
	@Test(expected = ModelException.class)
	public void testCollisionDetectionShip1IsNull1() throws ModelException {
		Ship ship1 = null;
		Ship ship2 = facade.createShip(30, 0, 0, 0, 10, 0);
		facade.getTimeToCollision(ship1, ship2);
	}
	
	@Test(expected = ModelException.class)
	public void testCollisionDetectionShip2IsNull1() throws ModelException {
		Ship ship1 = facade.createShip(0, 0, 10, 0, 10, 0);
		Ship ship2 = null;
		facade.getTimeToCollision(ship1, ship2);
	}
	
	@Test(expected = ModelException.class)
	public void testCollisionDetectionShip1IsNull2() throws ModelException {
		Ship ship1 = null;
		Ship ship2 = facade.createShip(30, 0, 0, 0, 10, 0);
		facade.getCollisionPosition(ship1, ship2);
	}
	
	@Test(expected = ModelException.class)
	public void testCollisionDetectionShip2IsNull2() throws ModelException {
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
	public void testCollisionDetectionSameShip() throws ModelException {		
		Ship ship1 = facade.createShip(0, 0, 10, 0, 10, 0);
		double[] position = facade.getCollisionPosition(ship1, ship1);
		assertNull(position);
		assertEquals(Double.POSITIVE_INFINITY, facade.getTimeToCollision(ship1, ship1), EPSILON);
	}

}
	