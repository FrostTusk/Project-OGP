package asteroids.facade;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import asteroids.facade.Facade;
import asteroids.helper.Entity;
import asteroids.helper.Position;
import asteroids.model.Bullet;
import asteroids.model.Ship;
import asteroids.model.World;
import asteroids.part2.CollisionListener;
import asteroids.part2.facade.IFacade;
import asteroids.util.ModelException;

@SuppressWarnings("deprecation")
public class TestFacade {

	private static final double EPSILON = 0.0001;

	IFacade facade;

	@Before
	public void setUp() {
		facade = new Facade();
	}

	
	
		/*
		 * |----------------------------|
		 * | #Header-1# Ship Methods.	|
		 * |----------------------------| 
		 */
	
	
			/*
			 * |--------------------------------------------|
			 * | 1. The next test test the Initialization.	|
			 * |--------------------------------------------| 
			 */	
	
	
	
	@Test @Deprecated
	public void testCreateShip() throws ModelException {
		Ship ship = facade.createShip(100, 200, 10, -10, 20, Math.PI);
		assertNotNull(ship);
		double[] position = facade.getShipPosition(ship);
		assertNotNull(position);
		assertEquals(100, position[0], EPSILON);
		assertEquals(200, position[1], EPSILON);
		assertEquals(20, ship.getRadius(), EPSILON);
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
			 * |--------------------------------------------|
			 * | 5. The next tests test the Orientation.	|
			 * |--------------------------------------------| 
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
			 * |--------------------------------------------|
			 * | 6. The next tests test the Move method.	|
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
			 * | 7. The next tests test the Thrust method.	|
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
	
	@Test(expected = ModelException.class)
	public void testCollisionDetectionSameShip() throws ModelException {		
		Ship ship1 = facade.createShip(0, 0, 10, 0, 10, 0);
		double[] position = facade.getCollisionPosition(ship1, ship1);
		assertNull(position);
		assertEquals(Double.POSITIVE_INFINITY, facade.getTimeToCollision(ship1, ship1), EPSILON);
	}
	
	
	
		/*
		 * |----------------------------|
		 * | #Header-2# Bullet Methods.	|
		 * |----------------------------| 
		 */
	
	/*
	 * |--------------------------------------------|
	 * | 1. The next test test the Initialization.	|
	 * |--------------------------------------------| 
	 */	



	@Test
	public void testCreateBullet() throws ModelException {
		Bullet bullet = facade.createBullet(1, 1, 1, 1, 1);
		assertNotNull(bullet);
	}

	@Test
	public void testCreateTerminateBullet() throws ModelException {
		Bullet bullet = facade.createBullet(1, 1, 1, 1, 1);
		assertNotNull(bullet);
		assertFalse(facade.isTerminatedBullet(bullet));
		facade.terminateBullet(bullet);
		assertTrue(facade.isTerminatedBullet(bullet));
	}


	/*
	 * |----------------------------------------|
	 * | 2. The next tests test the Position.	|
	 * |----------------------------------------| 
	 */	



	
	@Test(expected = ModelException.class)
	public void testCreateBulletPosBulletIsNull() throws ModelException {
		Bullet bullet = null;
		facade.getBulletPosition(bullet);
	}

	@Test
	public void testCreatBulletPosGeneric() throws ModelException {
		Bullet bullet1 = facade.createBullet(100, 200, 10, -10, 20);
		assertEquals(100, facade.getBulletPosition(bullet1)[0], EPSILON);
		assertEquals(200, facade.getBulletPosition(bullet1)[1], EPSILON);

		Bullet bullet2 = facade.createBullet(100, -200, 10, -10, 20);
		assertEquals(100, facade.getBulletPosition(bullet2)[0], EPSILON);
		assertEquals(-200, facade.getBulletPosition(bullet2)[1], EPSILON);

		Bullet bullet3 = facade.createBullet(-100, 200, 10, -10, 20);
		assertEquals(-100, facade.getBulletPosition(bullet3)[0], EPSILON);
		assertEquals(200, facade.getBulletPosition(bullet3)[1], EPSILON);

		Bullet bullet4 = facade.createBullet(-100, -200, 10, -10, 20);
		assertEquals(-100, facade.getBulletPosition(bullet4)[0], EPSILON);
		assertEquals(-200, facade.getBulletPosition(bullet4)[1], EPSILON);

		Bullet bullet5 = facade.createBullet(0, 0, 10, -10, 20);
		assertEquals(0, facade.getBulletPosition(bullet5)[0], EPSILON);
		assertEquals(0, facade.getBulletPosition(bullet5)[1], EPSILON);
	}


	@Test(expected = ModelException.class)
	public void testCreateBulletXIsPosInfinity() throws ModelException {
		facade.createBullet(Double.POSITIVE_INFINITY, 200, 10, -10, 20);
	}

	@Test(expected = ModelException.class)
	public void testCreateBulletXIsNegInfinity() throws ModelException {
		facade.createBullet(Double.NEGATIVE_INFINITY, 200, 10, -10, 20);
	}

	@Test(expected = ModelException.class)
	public void testCreateBulletXIsNan() throws ModelException {
		facade.createBullet(Double.NaN, 200, 10, -10, 20);
	}


	@Test(expected = ModelException.class)
	public void testCreateBulletYIsPosInfinity() throws ModelException {
		facade.createBullet(200, Double.POSITIVE_INFINITY, 10, -10, 20);
	}

	@Test(expected = ModelException.class)
	public void testCreateBulletYIsNegInfinity() throws ModelException {
		facade.createBullet(200, Double.NEGATIVE_INFINITY, 10, -10, 20);
	}
	
	@Test(expected = ModelException.class)
	public void testCreateBulletYIsNan() throws ModelException {
		facade.createBullet(200, Double.NaN, 10, -10, 20);
	}


	@Test(expected = ModelException.class)
	public void testCreateBulletXYIsPosInfinity() throws ModelException {
		facade.createBullet(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, 10, -10, 20);
	}

	@Test(expected = ModelException.class)
	public void testCreateBulletXYIsNegInfinity() throws ModelException {
		facade.createBullet(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, 10, -10, 20);
	}

	@Test(expected = ModelException.class)
	public void testCreateBulletXYIsNaN() throws ModelException {
		facade.createBullet(Double.NaN, Double.NaN, 10, -10, 20);
	}



	/*
	 * |------------------------------------|
	 * | 3. The next tests test the Speed.	|
	 * |------------------------------------| 
	 */	


	@Test
	public void testCreatBulletVelGeneric() throws ModelException {
		Bullet bullet1 = facade.createBullet(100, 200, 10, 20, 20);
		assertEquals(10, facade.getBulletVelocity(bullet1)[0], EPSILON);
		assertEquals(20, facade.getBulletVelocity(bullet1)[1], EPSILON);

		Bullet bullet2 = facade.createBullet(100, 200, -10, 20, 20);
		assertEquals(-10, facade.getBulletVelocity(bullet2)[0], EPSILON);
		assertEquals(20, facade.getBulletVelocity(bullet2)[1], EPSILON);

		Bullet bullet3 = facade.createBullet(100, 200, 10, -20, 20);
		assertEquals(10, facade.getBulletVelocity(bullet3)[0], EPSILON);
		assertEquals(-20, facade.getBulletVelocity(bullet3)[1], EPSILON);

		Bullet bullet4 = facade.createBullet(100, 200, -10, -20, 20);
		assertEquals(-10, facade.getBulletVelocity(bullet4)[0], EPSILON);
		assertEquals(-20, facade.getBulletVelocity(bullet4)[1], EPSILON);

		Bullet bullet5 = facade.createBullet(100, 200, 0, 0, 20);
		assertEquals(0, facade.getBulletVelocity(bullet5)[0], EPSILON);
		assertEquals(0, facade.getBulletVelocity(bullet5)[1], EPSILON);
	}


	@Test
	public void testCreateBulletVelOverflow() throws ModelException {
		Bullet bullet = facade.createBullet(200, 200, 500000, 500000, 20);
		assertEquals(0, facade.getBulletVelocity(bullet)[0], EPSILON);
		assertEquals(0, facade.getBulletVelocity(bullet)[1], EPSILON);
	}


	@Test
	public void testCreateBulletVXIsPosInfinity() throws ModelException {
		Bullet bullet = facade.createBullet(200, 200, Double.POSITIVE_INFINITY, 10, 20);
		assertEquals(0, facade.getBulletVelocity(bullet)[0], EPSILON);
		assertEquals(0, facade.getBulletVelocity(bullet)[1], EPSILON);
	}

	@Test
	public void testCreateBulletVXIsNegInfinity() throws ModelException {
		Bullet bullet = facade.createBullet(200, 200, Double.NEGATIVE_INFINITY, 10, 20);
		assertEquals(0, facade.getBulletVelocity(bullet)[0], EPSILON);
		assertEquals(0, facade.getBulletVelocity(bullet)[1], EPSILON);
	}

	@Test
	public void testCreateBulletVXIsNan() throws ModelException {
		Bullet bullet = facade.createBullet(200, 200, Double.NaN, 10, 20);
		assertEquals(0, facade.getBulletVelocity(bullet)[0], EPSILON);
		assertEquals(0, facade.getBulletVelocity(bullet)[1], EPSILON);
	}


	@Test
	public void testCreateBulletVYIsPosInfinity() throws ModelException {
		Bullet bullet = facade.createBullet(200, 200, 10, Double.POSITIVE_INFINITY, 20);
		assertEquals(0, facade.getBulletVelocity(bullet)[0], EPSILON);
		assertEquals(0, facade.getBulletVelocity(bullet)[1], EPSILON);
	}

	@Test
	public void testCreateBulletVYIsNegInfinity() throws ModelException {
		Bullet bullet = facade.createBullet(200, 200, 10, Double.NEGATIVE_INFINITY, 20);
		assertEquals(0, facade.getBulletVelocity(bullet)[0], EPSILON);
		assertEquals(0, facade.getBulletVelocity(bullet)[1], EPSILON);
	}

	@Test
	public void testCreateBulletVYIsNan() throws ModelException {
		Bullet bullet = facade.createBullet(200, 200, 10, Double.NaN, 20);
		assertEquals(0, facade.getBulletVelocity(bullet)[0], EPSILON);
		assertEquals(0, facade.getBulletVelocity(bullet)[1], EPSILON);
	}


	@Test
	public void testCreateBulletVXYIsPosInfinity() throws ModelException {
		Bullet bullet = facade.createBullet(200, 200, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, 20);
		assertEquals(0, facade.getBulletVelocity(bullet)[0], EPSILON);
		assertEquals(0, facade.getBulletVelocity(bullet)[1], EPSILON);
	}

	@Test
	public void testCreateBulletVXYIsNegInfinity() throws ModelException {
		Bullet bullet = facade.createBullet(200, 200, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, 20);
		assertEquals(0, facade.getBulletVelocity(bullet)[0], EPSILON);
		assertEquals(0, facade.getBulletVelocity(bullet)[1], EPSILON);
	}

	@Test
	public void testCreateBulletVXYIsNan() throws ModelException {
		Bullet bullet = facade.createBullet(200, 200, Double.NaN, Double.NaN, 20);
		assertEquals(0, facade.getBulletVelocity(bullet)[0], EPSILON);
		assertEquals(0, facade.getBulletVelocity(bullet)[1], EPSILON);
	}

	

	/*
	 * |------------------------------------|
	 * | 4. The next tests test the Radius.	|
	 * |------------------------------------| 
	 */	


	@Test
	public void testCreateBulletRadiusGeneric() throws ModelException {
		Bullet bullet = facade.createBullet(100, 200, 10, -10, 50);
		assertNotNull(bullet);
		assertEquals(50, facade.getBulletRadius(bullet), EPSILON);
	}


	@Test(expected = ModelException.class)
	public void testCreateBulletRadiusUnderflow() throws ModelException {
		facade.createBullet(100, 200, 10, -10, 0.5);
	}

	@Test(expected = ModelException.class)
	public void testCreateBulletRadiusNegative() throws ModelException {
		facade.createBullet(100, 200, 10, -10, -50);
	}

	@Test(expected = ModelException.class)
	public void testCreateBulletRadiusZero() throws ModelException {
		facade.createBullet(100, 200, 10, -10, 0);
	}


	@Test(expected = ModelException.class)
	public void testCreateBulletRadiusIsPosInfinity() throws ModelException {
		facade.createBullet(100, 200, 10, -10, Double.POSITIVE_INFINITY);
	}

	@Test(expected = ModelException.class)
	public void testCreateBulletRadiusIsNegInfinity() throws ModelException {
		facade.createBullet(100, 200, 10, -10, Double.NEGATIVE_INFINITY);
	}

	@Test(expected = ModelException.class)
	public void testCreateBulletRadiusIsNan() throws ModelException {
		facade.createBullet(100, 200, 10, -10, Double.NaN);
	}



	/*
	 * |------------------------------------|
	 * | 5. The next tests test the Mass.	|
	 * |------------------------------------| 
	 */	

	@Test
	public void testBulletGetMassGeneric() throws ModelException {
		Bullet bullet = facade.createBullet(100, 200, 10, -10, 50);
		assertNotNull(bullet);
		assertEquals((4/3) * Math.PI * Math.pow(50, 3) * (bullet.getDensity()), facade.getBulletMass(bullet), EPSILON);
	}

	@Test
	public void testBulletSetMassGeneric() throws ModelException {
		Bullet bullet = facade.createBullet(100, 200, 10, -10, 50);
		assertNotNull(bullet);
		bullet.setMass();
		assertEquals((4/3) * Math.PI * Math.pow(50, 3) * (bullet.getDensity()), facade.getBulletMass(bullet), EPSILON);
	}

	@Test
	public void testBulletGetMassWithoutSetMass() throws ModelException {
		Bullet bullet = facade.createBullet(100, 200, 10, -10, 50);
		assertNotNull(bullet);
		facade.getBulletMass(bullet);
		assertEquals((4/3) * Math.PI * Math.pow(facade.getBulletRadius(bullet), 3) * (bullet.getDensity()), facade.getBulletMass(bullet), EPSILON);
	}


	/*
	 * |--------------------------------------------------------|
	 * | 6. The next tests test the interaction with worlds.	|
	 * |--------------------------------------------------------| 
	 */	



	@Test
	public void testBulletGetTimeToCollisionWorldGeneric() throws ModelException {
		Bullet bullet = facade.createBullet(2, 2, 1, 0, 1);
		World world = facade.createWorld(100, 100);
		bullet.setWorld(world);
		assertEquals(97, facade.getTimeCollisionBoundary(bullet), EPSILON);
	} 

	@Test // TODO reasoning.
	public void testBulletGetTimeToCollisionWorldTouchingNoCollision() throws ModelException {
		Bullet bullet = facade.createBullet(2, 2, 0, 0, 2);
		World world = facade.createWorld(100, 100);
		bullet.setWorld(world);
		assertEquals(0, facade.getTimeCollisionBoundary(bullet), EPSILON);
	}

	@Test
	public void testBulletGetTimeToCollisionWorldTouchingWithCollision() throws ModelException {
		Bullet bullet = facade.createBullet(2, 2, 0, -10, 2);
		World world = facade.createWorld(100, 100);
		bullet.setWorld(world);
		assertEquals(0, facade.getTimeCollisionBoundary(bullet), EPSILON);
	}

	@Test
	public void testBulletGetCollisionPositionWorldGeneric() throws ModelException {
		Bullet bullet = facade.createBullet(2, 2, 1, 0, 1);
		World world = facade.createWorld(100, 100);
		facade.addBulletToWorld(world, bullet);
		bullet.setWorld(world); 
		double[] position = facade.getPositionCollisionBoundary(bullet);
		if (position[0] != 100) fail();
		if (position[1] != 2) fail();
	}

	@Test
	public void testBulletGetCollisionPositionWorldBulletInOtherWorld() throws ModelException {
		Bullet bullet = facade.createBullet(2, 2, 0, -10, 2);
		World world = facade.createWorld(100, 100);
		bullet.setWorld(world);
		assertEquals(0, facade.getTimeCollisionBoundary(bullet), EPSILON);
	}

	@Test
	public void testBulletGetCollisionPositionWorldTouchingNoCollision() throws ModelException {
		Bullet bullet = facade.createBullet(2, 2, 0, -10, 2);
		World world = facade.createWorld(100, 100);
		bullet.setWorld(world);
		assertEquals(0, facade.getTimeCollisionBoundary(bullet), EPSILON);
	}

	@Test
	public void testBulletGetCollisionPositionWorldTouchingWithCollision() throws ModelException {
		Bullet bullet = facade.createBullet(2, 2, 0, -10, 2);
		World world = facade.createWorld(100, 100);
		bullet.setWorld(world);
		assertEquals(0, facade.getTimeCollisionBoundary(bullet), EPSILON);
	}

	
	/*
	 * |--------------------------------------------------------|
	 * | 8. The next tests test the interaction with entities.	|
	 * |--------------------------------------------------------| 
	 */	


	@Test 
	public void testBulletGetTimeToCollisionGeneric() throws ModelException {
		Bullet bullet1 = facade.createBullet(100, 100, 0, 0, 20);
		Bullet bullet2 = facade.createBullet(10, 100, 10, 0, 20);
		assertEquals(5, facade.getTimeCollisionEntity(bullet1, bullet2), EPSILON);
	}

	@Test 
	public void testBulletGetTimeToCollisionNoCollision() throws ModelException {
		Bullet bullet1 = facade.createBullet(100, 100, 0, 0, 20);
		Bullet bullet2 = facade.createBullet(10, 100, 0, 0, 20);
		assertEquals(Double.POSITIVE_INFINITY, facade.getTimeCollisionEntity(bullet1, bullet2), EPSILON);
	}

	@Test(expected = ModelException.class)
	public void testBulletGetTimeToCollisionOverlap() throws ModelException {
		Bullet bullet1 = facade.createBullet(100, 100, 0, 0, 20);
		Bullet bullet2 = facade.createBullet(100, 100, 0, 0, 20);
		assertEquals(0, facade.getTimeCollisionEntity(bullet1, bullet2), EPSILON);
	}

	@Test 
	public void testBulletCollisionPositionGeneric() throws ModelException {
		Bullet bullet1 = facade.createBullet(100, 100, -10, 0, 5);
		Bullet bullet2 = facade.createBullet(10, 100, 0, 0, 5);
		World world = facade.createWorld(1000, 1000);
		bullet1.setWorld(world);
		bullet2.setWorld(world);
		double[] position = facade.getPositionCollisionEntity(bullet1, bullet2);
		assertEquals(15, position[0], EPSILON);
		assertEquals(100, position[1], EPSILON);
	}

	@Test 
	public void testBulletCollisionPositionNoCollision() throws ModelException {
		Bullet bullet1 = facade.createBullet(100, 100, 0, 0, 5);
		Bullet bullet2 = facade.createBullet(10, 100, 0, 0, 5);
		World world = facade.createWorld(1000, 1000);
		bullet1.setWorld(world);
		bullet2.setWorld(world);
		double[] position = facade.getPositionCollisionEntity(bullet1, bullet2);
		assertTrue(position == null);
	}

	@Test 
	public void testBulletCollisionPositionOtherWorlds() throws ModelException {
		Bullet bullet1 = facade.createBullet(100, 100, 0, 0, 5);
		Bullet bullet2 = facade.createBullet(10, 100, 0, 0, 5);
		World world1 = facade.createWorld(1000, 1000);
		World world2 = facade.createWorld(1000, 1000);
		bullet1.setWorld(world1);
		bullet2.setWorld(world2);
		double[] position = facade.getPositionCollisionEntity(bullet1, bullet2);
		assertTrue(position == null);
	}
	

		/*
		*|------------------------------|
		*| #Header-3# World Methods.	|
		*|------------------------------| 
		*/

	@Test
	public void testCreateWorld() throws ModelException {
		World world = facade.createWorld(10, 10);
		assertNotNull(world);
	}
	
	@Test
	public void testCreateWorldTerminateWorld() throws ModelException {
		World world = facade.createWorld(-15, -10);
		assertNotNull(world);
		facade.terminateWorld(world);
	}
	
	@Test
	public void testTerminateWorld() throws ModelException {
		World world = facade.createWorld(-15, -10);
		assertNotNull(world);
		facade.terminateWorld(world);
		assertTrue(facade.isTerminatedWorld(world));
	}
	
			/*
			 * |--------------------------------------------|
			 * | 2. The next test test the Size. (Basic)	|
			 * |--------------------------------------------| 
			 */	

	
	
	@Test
	public void testCreateWorldSizeGeneric() throws ModelException {
		World world = facade.createWorld(15, 10);
		assertNotNull(world);
		assertEquals(15, facade.getWorldSize(world)[0], EPSILON);
		assertEquals(10, facade.getWorldSize(world)[1], EPSILON);
	}
	
	
	@Test
	public void testCreateWorldSizeWidthZero() throws ModelException {
		World world = facade.createWorld(0, 0);
		assertNotNull(world);
		assertEquals(0, facade.getWorldSize(world)[0], EPSILON);
		assertEquals(0, facade.getWorldSize(world)[1], EPSILON);
	}
	
	@Test
	public void testCreateWorldSizeWidthEdgeOverflow() throws ModelException {
		World world = facade.createWorld(Double.MAX_VALUE, 10);
		assertNotNull(world);
		assertEquals(Double.MAX_VALUE, facade.getWorldSize(world)[0], EPSILON);
		assertEquals(10, facade.getWorldSize(world)[1], EPSILON);
	}
	
	@Test
	public void testCreateWorldSizeHeightEdgeOverflow() throws ModelException {
		World world = facade.createWorld(15, Double.MAX_VALUE);
		assertNotNull(world);
		assertEquals(15, facade.getWorldSize(world)[0], EPSILON);
		assertEquals(Double.MAX_VALUE, facade.getWorldSize(world)[1], EPSILON);
	}
	
	
	@Test
	public void testCreateWorldSizeWidthHeightEdgeOverflow() throws ModelException {
		World world = facade.createWorld(Double.MAX_VALUE, Double.MAX_VALUE);
		assertNotNull(world);
		assertEquals(Double.MAX_VALUE, facade.getWorldSize(world)[0], EPSILON);
		assertEquals(Double.MAX_VALUE, facade.getWorldSize(world)[1], EPSILON);
	}
	
	
	@Test
	public void testCreateWorldSizeWidthOverflow() throws ModelException {
		World world = facade.createWorld(Double.MAX_VALUE + 50, 10);
		assertNotNull(world);
		assertEquals(Double.MAX_VALUE, facade.getWorldSize(world)[0], EPSILON);
		assertEquals(10, facade.getWorldSize(world)[1], EPSILON);
	}
	
	@Test
	public void testCreateWorldSizeHeightOverflow() throws ModelException {
		World world = facade.createWorld(15, Double.MAX_VALUE + 50);
		assertNotNull(world);
		assertEquals(15, facade.getWorldSize(world)[0], EPSILON);
		assertEquals(Double.MAX_VALUE, facade.getWorldSize(world)[1], EPSILON);
	}
	
	@Test
	public void testCreateWorldSizeWidthHeightOverflow() throws ModelException {
		World world = facade.createWorld(Double.MAX_VALUE, Double.MAX_VALUE);
		assertNotNull(world);
		assertEquals(Double.MAX_VALUE, facade.getWorldSize(world)[0], EPSILON);
		assertEquals(Double.MAX_VALUE, facade.getWorldSize(world)[1], EPSILON);
	}
	
	@Test
	public void testCreateWorldSizeWidthNeg() throws ModelException {
		World world = facade.createWorld(-15, 10);
		assertNotNull(world);
		assertEquals(0, facade.getWorldSize(world)[0], EPSILON);
		assertEquals(10, facade.getWorldSize(world)[1], EPSILON);
	}
	
	@Test
	public void testCreateWorldSizeHeightNeg() throws ModelException {
		World world = facade.createWorld(15, -10);
		assertNotNull(world);
		assertEquals(15, facade.getWorldSize(world)[0], EPSILON);
		assertEquals(0, facade.getWorldSize(world)[1], EPSILON);
	}
	
	@Test
	public void testCreateWorldSizeWidthHeightNeg() throws ModelException {
		World world = facade.createWorld(-15, -10);
		assertNotNull(world);
		assertEquals(0, facade.getWorldSize(world)[0], EPSILON);
		assertEquals(0, facade.getWorldSize(world)[1], EPSILON);
	}
	
	
	/*
	 * |----------------------------------------------------|
	 * | 4. The next test test the relation with Entities	|
	 * |----------------------------------------------------| 
	 */	
	
	
	@Test
	public void testWorldAddEntityAble() throws ModelException {
		World world = facade.createWorld(1000, 1000);
		Ship ship = facade.createShip(100, 100, 10, -10, Math.PI, 20, 10);
		Bullet bullet = facade.createBullet(1, 1, 1, 1, 1);
		assertFalse(world.containsEntity(ship));
		assertFalse(world.containsEntity(bullet));
		facade.addShipToWorld(world, ship);
		facade.addBulletToWorld(world, bullet);
		assertTrue(world.containsEntity(ship));
		assertTrue(world.containsEntity(bullet));
	}
	
	@Test(expected = ModelException.class)
	public void testWorldAddEntityUnable() throws ModelException {
		World world = facade.createWorld(1000, 1000);
		Ship ship = facade.createShip(1000, 1000, 10, -10, Math.PI, 20, 10);
		Bullet bullet = facade.createBullet(1000, 1000, 1, 1, 1);
		facade.addShipToWorld(world, ship);
		facade.addBulletToWorld(world, bullet);
		assertFalse(world.containsEntity(ship));
		assertFalse(world.containsEntity(bullet));
	}
	
	@Test
	public void testWorldRemoveEntityGeneric() throws ModelException {
		World world = facade.createWorld(1000, 1000);
		Ship ship = facade.createShip(100, 100, 10, -10, Math.PI, 20, 10);
		Bullet bullet = facade.createBullet(1, 1, 1, 1, 1);
		facade.addShipToWorld(world, ship);
		facade.addBulletToWorld(world, bullet);
		assertTrue(world.containsEntity(ship));
		assertTrue(world.containsEntity(bullet));
		facade.removeBulletFromWorld(world, bullet);
		facade.removeShipFromWorld(world, ship);
		assertFalse(world.containsEntity(ship));
		assertFalse(world.containsEntity(bullet));
	}
	
	@Test(expected = ModelException.class)
	public void testWorldRemoveEntityNotInWorld() throws ModelException  {
		World world = facade.createWorld(1000, 1000);
		Ship ship = facade.createShip(100, 100, 10, -10, Math.PI, 20, 10);
		Bullet bullet = facade.createBullet(1, 1, 1, 1, 1);
		facade.removeBulletFromWorld(world, bullet);
		facade.removeShipFromWorld(world, ship);
		assertFalse(world.containsEntity(ship));
		assertFalse(world.containsEntity(bullet));
	}
	
	@Test
	public void testWorldGetFirstCollisionPosition() throws ModelException {
		World world = facade.createWorld(1000, 1000);
		Ship ship = facade.createShip(150, 100, 0, 10, Math.PI, 20, 10);
		Bullet bullet = facade.createBullet(150, 150, 0, 0, 1);
		facade.addShipToWorld(world, ship);
		facade.addBulletToWorld(world, bullet);
		facade.getPositionNextCollision(world);
		assertEquals(150, facade.getPositionNextCollision(world)[0], EPSILON);
		assertEquals(149, facade.getPositionNextCollision(world)[1], EPSILON);
	}
	
	@Test
	public void testWorldGetFirstCollisionPositionNoCollision() throws ModelException {
		World world = facade.createWorld(1000, 1000);
		Ship ship = facade.createShip(150, 100, 0, 0, Math.PI, 20, 10);
		Bullet bullet = facade.createBullet(150, 150, 0, 0, 1);
		facade.addShipToWorld(world, ship);
		facade.addBulletToWorld(world, bullet);
		if (facade.getPositionNextCollision(world) != null) {		
			assertEquals(Double.POSITIVE_INFINITY, facade.getPositionNextCollision(world)[0], EPSILON);
			assertEquals(Double.POSITIVE_INFINITY, facade.getPositionNextCollision(world)[1], EPSILON);
		}
		else fail();
	}
	
	@Test
	public void testWorldGetFirstCollisionTime() throws ModelException {
		World world = facade.createWorld(1000, 1000);
		Ship ship1 = facade.createShip(100, 100, 0, 10, Math.PI, 20, 10);
		Ship ship2 = facade.createShip(100, 150, 0, 0, Math.PI, 20, 10);
		facade.addShipToWorld(world, ship1);
		facade.addShipToWorld(world, ship2);
		assertEquals(1, facade.getTimeNextCollision(world), EPSILON);
	}
	
	@Test
	public void testWorldGetFirstCollisionTimeNoCollision() throws ModelException {
		World world = facade.createWorld(1000, 1000);
		Ship ship1 = facade.createShip(100, 100, 0, 0, Math.PI, 20, 10);
		Ship ship2 = facade.createShip(150, 150, 0, 0, Math.PI, 20, 10);
		facade.addShipToWorld(world, ship1);
		facade.addShipToWorld(world, ship2);
		assertEquals(Double.POSITIVE_INFINITY, facade.getTimeNextCollision(world), EPSILON);
	}
	
	@Test
	public void testWorldGetFirstCollisionTimeNoEntities() throws ModelException {
		World world = facade.createWorld(1000, 1000);
		assertEquals(-1, facade.getTimeNextCollision(world), EPSILON);
	}
	
	@Test
	public void testWorldGetAllEntitiesGeneric() throws ModelException {
		World world = facade.createWorld(1000, 1000);
		Ship ship1 = facade.createShip(100, 100, 10, 0, Math.PI, 20, 10);
		Ship ship2 = facade.createShip(150, 100, 0, 0, Math.PI, 20, 10);
		Bullet bullet = facade.createBullet(150, 150, 0, 0, 1);
		facade.addShipToWorld(world, ship1);
		facade.addShipToWorld(world, ship2);
		facade.addBulletToWorld(world, bullet);
		@SuppressWarnings("unchecked")
		Set<Entity> entitiesTest = (Set<Entity>) facade.getEntities(world);
		Set<Entity> entitiesReference = new HashSet<Entity>(Arrays.asList(ship1, ship2, bullet));
		double counter = 0;
		for (Entity entityTest : entitiesTest) for (Entity entityRef : entitiesReference) if (entityTest == entityRef) counter += 1;
		if (counter != 3) fail();
		assertEquals(3, entitiesTest.size(), EPSILON);
	}
	
	@Test
	public void testWorldGetAllEntitiesAllShips() throws ModelException {
		World world = facade.createWorld(1000, 1000);
		Ship ship1 = facade.createShip(100, 100, 10, 0, Math.PI, 20, 10);
		Ship ship2 = facade.createShip(150, 100, 0, 0, Math.PI, 20, 10);
		Ship ship3 = facade.createShip(200, 100, 0, 0, Math.PI, 20, 10);
		Ship ship4 = facade.createShip(250, 100, 0, 0, Math.PI, 20, 10);
		facade.addShipToWorld(world, ship1);
		facade.addShipToWorld(world, ship2);
		facade.addShipToWorld(world, ship3);
		facade.addShipToWorld(world, ship4);
		@SuppressWarnings("unchecked")
		Set<Entity> entities = (Set<Entity>) facade.getEntities(world);
		@SuppressWarnings("unchecked")
		Set<Ship> ships = (Set<Ship>) facade.getWorldShips(world);
		double counter = 0;
		for (Ship ship : ships) for (Entity entity : entities) if (ship == entity) counter += 1;
		if (counter != 4) fail();
		assertEquals(4, entities.size(), EPSILON);
	}
	
	@Test
	public void testWorldGetAllEntitiesAllBullets() throws ModelException {
		World world = facade.createWorld(1000, 1000);
		Bullet bullet1 = facade.createBullet(150, 100, 0, 0, 1);
		Bullet bullet2 = facade.createBullet(150, 150, 0, 0, 1);
		Bullet bullet3 = facade.createBullet(150, 200, 0, 0, 1);
		facade.addBulletToWorld(world, bullet1);
		facade.addBulletToWorld(world, bullet2);
		facade.addBulletToWorld(world, bullet3);
		@SuppressWarnings("unchecked")
		Set<Entity> entities = (Set<Entity>) facade.getEntities(world);
		Set<Bullet> bullets = new HashSet<Bullet>(Arrays.asList(bullet1, bullet2, bullet3));
		double counter = 0;
		for (Entity entityTest : entities) for (Bullet bullet : bullets) if (entityTest == bullet) counter += 1;
		if (counter != 3) fail();
		assertEquals(3, entities.size(), EPSILON);
	}
	
	@Test
	public void testWorldGetAllEntitiesNoEntities() throws ModelException {
		World world = facade.createWorld(1000, 1000);
		@SuppressWarnings("unchecked")
		Set<Entity> entities = (Set<Entity>) facade.getEntities(world);
		assertEquals(0, entities.size(), EPSILON);
	}
	
	@Test
	public void testWorldGetEntityAtPosition() throws ModelException {
		World world = facade.createWorld(1000, 1000);
		Bullet bullet1 = facade.createBullet(150, 100, 0, 0, 1);
		Bullet bullet2 = facade.createBullet(150, 150, 0, 0, 1);
		Bullet bullet3 = facade.createBullet(150, 200, 0, 0, 1);
		for (@SuppressWarnings("unused") Object entity : facade.getEntities(world)) {
			if (facade.getEntityAt(world, facade.getBulletPosition(bullet1)[0], facade.getBulletPosition(bullet1)[1]) != bullet1 || facade.getEntityAt(world, facade.getBulletPosition(bullet2)[0], facade.getBulletPosition(bullet2)[1]) != bullet2 
					||facade.getEntityAt(world, facade.getBulletPosition(bullet3)[0], facade.getBulletPosition(bullet3)[1]) != bullet3) fail();
		}
	}
	
	
			/*
			 * |--------------------------------------------|
			 * | 5. The next test test the evolve method	|
			 * |--------------------------------------------| 
			 */	
	
	@Test
	public void testWorldEvolveNoMovement() throws ModelException {
		World world = facade.createWorld(1000, 1000);
		Bullet bullet1 = facade.createBullet(150, 150, 0, 0, 1);
		Bullet bullet2 = facade.createBullet(250, 250, 0, 0, 1);
		Ship ship1 = facade.createShip(100, 100, 0, 0, Math.PI, 20, 10);
		Ship ship2 = facade.createShip(200, 200, 0, 0, Math.PI, 20, 10);
		facade.addBulletToWorld(world, bullet1);
		facade.addBulletToWorld(world, bullet2);
		facade.addShipToWorld(world, ship1);
		facade.addShipToWorld(world, ship2);
		world.evolve(2);
		assertEquals(150, facade.getBulletPosition(bullet1)[0], EPSILON);
		assertEquals(150, facade.getBulletPosition(bullet1)[1], EPSILON);
		assertEquals(250, facade.getBulletPosition(bullet2)[0], EPSILON);
		assertEquals(250, facade.getBulletPosition(bullet2)[1], EPSILON);
		assertEquals(100, facade.getShipPosition(ship1)[0], EPSILON);
		assertEquals(100, facade.getShipPosition(ship1)[1], EPSILON);
		assertEquals(200, facade.getShipPosition(ship1)[0], EPSILON);
		assertEquals(200, facade.getShipPosition(ship1)[1], EPSILON);
	}
	
	@Test
	public void testWorldEvolveNoEntities() throws ModelException {
		World world = facade.createWorld(1000, 1000);
		world.evolve(2);
	}
	
	@Test
	public void testWorldEvolveCollisionBetweenShipAndBullet() throws ModelException {
		World world = facade.createWorld(1000, 1000);
		Bullet bullet = facade.createBullet(150, 150, 60, 0, 1);
		Ship ship1 = facade.createShip(100, 100, 0, 0, Math.PI, 20, 10);
		Ship ship2 = facade.createShip(200, 150, 0, 0, Math.PI, 20, 10);
		facade.addBulletToWorld(world, bullet);
		facade.addShipToWorld(world, ship1);
		facade.addShipToWorld(world, ship2);
		bullet.setSource(ship1);
		world.evolve(2);
		assertTrue(facade.isTerminatedShip(ship2));
		assertTrue(facade.isTerminatedBullet(bullet));
	}
	
	@Test
	public void testWorldEvolveNoCollisions() throws ModelException {
		World world = facade.createWorld(1000, 1000);
		Ship ship = facade.createShip(100, 100, 10, -10, Math.PI, 20, 10);
		Bullet bullet = facade.createBullet(2, 2, 10, 10, 1);
		facade.addBulletToWorld(world, bullet);
		facade.addShipToWorld(world, ship);
		CollisionListener collisionListener = null;
		facade.evolve(world, 2, collisionListener);
		assertEquals(120, facade.getShipPosition(ship)[0], EPSILON);
		assertEquals(80, facade.getShipPosition(ship)[1], EPSILON);
		assertEquals(22, facade.getBulletPosition(bullet)[0], EPSILON);
		assertEquals(22, facade.getBulletPosition(bullet)[1], EPSILON);
	}
	
	@Test
	public void testWorldEvolveCollisionShip2InRest() throws ModelException {
		World world = facade.createWorld(1000, 1000);
		Ship ship1 = facade.createShip(100, 500, 10, 0, Math.PI, 20, 10);
		Ship ship2 = facade.createShip(500, 500, 0, 0, Math.PI, 20, 10);
		Bullet bullet = facade.createBullet(5, 5, 0, 0, 1);
		facade.addBulletToWorld(world, bullet);
		facade.addShipToWorld(world, ship1);
		facade.addShipToWorld(world, ship2);
		world.evolve(20);
		assertEquals(10, facade.getShipVelocity(ship1)[0], EPSILON);
		assertEquals(0, facade.getShipVelocity(ship1)[1], EPSILON);
		assertEquals(0, facade.getShipVelocity(ship2)[0], EPSILON);
		assertEquals(0, facade.getShipVelocity(ship2)[1], EPSILON);
		world.evolve(30);
		assertEquals(0, facade.getShipVelocity(ship1)[0], EPSILON);
		assertEquals(0, facade.getShipVelocity(ship1)[1], EPSILON);
		assertEquals(0, facade.getShipVelocity(ship2)[0], EPSILON);
		assertEquals(10, facade.getShipVelocity(ship2)[1], EPSILON);
	}
	
	@Test
	public void testWorldEvolveCollisionBulletHorizontalBoundary() throws ModelException {
		World world = facade.createWorld(1000, 1000);
		Ship ship1 = facade.createShip(100, 500, 0, 0, Math.PI, 20, 10);
		Ship ship2 = facade.createShip(500, 500, 0, 0, Math.PI, 20, 10);
		Bullet bullet = facade.createBullet(5, 5, -1, 10, 1);
		facade.addBulletToWorld(world, bullet);
		facade.addShipToWorld(world, ship1);
		facade.addShipToWorld(world, ship2);
		double counter = bullet.getBoundaryCollisionCounter();
		assertEquals(0, counter, EPSILON);
		world.evolve(3);
		assertEquals(-1, facade.getBulletVelocity(bullet)[0], EPSILON);
		assertEquals(10, facade.getBulletVelocity(bullet)[1], EPSILON);
		world.evolve(3);
		assertEquals(1, facade.getBulletVelocity(bullet)[0], EPSILON);
		assertEquals(10, facade.getBulletVelocity(bullet)[1], EPSILON);
		assertEquals(1, bullet.getBoundaryCollisionCounter(), EPSILON);
	}
	
	@Test
	public void testWorldEvolveCollisionBulletHorizontalBoundaryCounterFull() throws ModelException {
		World world = facade.createWorld(1000, 1000);
		Bullet bullet = facade.createBullet(900, 50, 90, 0, 10);
		facade.addBulletToWorld(world, bullet);
		double counter = bullet.getBoundaryCollisionCounter();
		assertEquals(0, counter, EPSILON);
		assertEquals(90, facade.getBulletVelocity(bullet)[0], EPSILON);
		assertEquals(0, facade.getBulletVelocity(bullet)[1], EPSILON);
		world.evolve(10);
		assertEquals(-90, facade.getBulletVelocity(bullet)[0], EPSILON);
		assertEquals(0, facade.getBulletVelocity(bullet)[1], EPSILON);
		assertEquals(1, bullet.getBoundaryCollisionCounter(), EPSILON);
		world.evolve(10);
		assertEquals(90, facade.getBulletVelocity(bullet)[0], EPSILON);
		assertEquals(0, facade.getBulletVelocity(bullet)[1], EPSILON);
		assertEquals(2, bullet.getBoundaryCollisionCounter(), EPSILON);
		world.evolve(10);
		assertTrue(facade.isTerminatedBullet(bullet));
	}
	
	@Test
	public void testWorldEvolveCollisionBulletVerticalBoundary0() throws ModelException {
		World world = facade.createWorld(1000, 1000);
		Ship ship1 = facade.createShip(100, 500, 0, 0, Math.PI, 20, 10);
		Ship ship2 = facade.createShip(500, 500, 0, 0, Math.PI, 20, 10);
		Bullet bullet = facade.createBullet(5, 5, 10, -1, 1);
		facade.addBulletToWorld(world, bullet);
		facade.addShipToWorld(world, ship1);
		facade.addShipToWorld(world, ship2);
		double counter = bullet.getBoundaryCollisionCounter();
		assertEquals(0, counter, EPSILON);
		world.evolve(3);
		assertEquals(10, facade.getBulletVelocity(bullet)[0], EPSILON);
		assertEquals(-1, facade.getBulletVelocity(bullet)[1], EPSILON);
		world.evolve(3);
		assertEquals(10, facade.getBulletVelocity(bullet)[0], EPSILON);
		assertEquals(1, facade.getBulletVelocity(bullet)[1], EPSILON);
		assertEquals(1, bullet.getBoundaryCollisionCounter(), EPSILON);
	}
	
	@Test
	public void testWorldEvolveCollisionBulletHorizontalBoundaryWidth() throws ModelException {
		World world = facade.createWorld(1000, 1000);
		Bullet bullet = facade.createBullet(900, 5, 10, 1, 1);
		facade.addBulletToWorld(world, bullet);
		double counter = bullet.getBoundaryCollisionCounter();
		assertEquals(0, counter, EPSILON);
		world.evolve(7);
		assertEquals(10, facade.getBulletVelocity(bullet)[0], EPSILON);
		assertEquals(1, facade.getBulletVelocity(bullet)[1], EPSILON);
		world.evolve(10);
		assertEquals(-10, facade.getBulletVelocity(bullet)[0], EPSILON);
		assertEquals(1, facade.getBulletVelocity(bullet)[1], EPSILON);
		assertEquals(1, bullet.getBoundaryCollisionCounter(), EPSILON);
	}
	
	@Test
	public void testWorldEvolveCollisionShipBoundaryX() throws ModelException {
		World world = facade.createWorld(1000, 1000);
		Ship ship1 = facade.createShip(900, 500, 0, 0, Math.PI, 20, 10);
		Ship ship2 = facade.createShip(500, 500, 0, 0, Math.PI, 20, 10);
		Bullet bullet = facade.createBullet(5, 5, -1, 10, 1);
		facade.addBulletToWorld(world, bullet);
		facade.addShipToWorld(world, ship1);
		facade.addShipToWorld(world, ship2);
		double counter = bullet.getBoundaryCollisionCounter();
		assertEquals(0, counter, EPSILON);
		world.evolve(3);
		assertEquals(-1, facade.getBulletVelocity(bullet)[0], EPSILON);
		assertEquals(10, facade.getBulletVelocity(bullet)[1], EPSILON);
		world.evolve(3);
		assertEquals(1, facade.getBulletVelocity(bullet)[0], EPSILON);
		assertEquals(10, facade.getBulletVelocity(bullet)[1], EPSILON);
		assertEquals(1, bullet.getBoundaryCollisionCounter(), EPSILON);
	}
	
	@Test
	public void testWorldEvolveCollisionShipBoundaryY() throws ModelException {
		World world = facade.createWorld(1000, 1000);
		Ship ship = facade.createShip(500, 900, 1, 10, Math.PI, 20, 10);
		facade.addShipToWorld(world, ship);
		world.evolve(4);
		assertEquals(1, facade.getShipVelocity(ship)[0], EPSILON);
		assertEquals(10, facade.getShipVelocity(ship)[1], EPSILON);
		world.evolve(3);
		assertEquals(1, facade.getShipVelocity(ship)[0], EPSILON);
		assertEquals(10, facade.getShipVelocity(ship)[1], EPSILON);
		world.evolve(2);
		assertEquals(1, facade.getShipVelocity(ship)[0], EPSILON);
		assertEquals(-10, facade.getShipVelocity(ship)[1], EPSILON);
	}
	
	@Test
	public void testWorldEvolveCollisionShipStartsAgainstBoundaryMovesFromBoundary() throws ModelException {
		World world = facade.createWorld(1000, 1000);
		Ship ship = facade.createShip(800, 900, 0, -10, Math.PI, 100, 10);
		facade.addShipToWorld(world, ship);
		assertEquals(0, facade.getShipVelocity(ship)[0], EPSILON);
		assertEquals(-10, facade.getShipVelocity(ship)[1], EPSILON);
		world.evolve(1);
		assertEquals(0, facade.getShipVelocity(ship)[0], EPSILON);
		assertEquals(-10, facade.getShipVelocity(ship)[1], EPSILON);
	}
	
	@Test
	public void testWorldEvolveCollisionShipOwnBullet() throws ModelException {
		World world = facade.createWorld(1000, 1000);
		Ship ship = facade.createShip(500, 500, 0, 0, 0, 20, 10);
		Bullet bullet = facade.createBullet(500, 500, 10, 10, 1);
		facade.addBulletToWorld(world, bullet);
		facade.addShipToWorld(world, ship);
		assertEquals(250, facade.getBulletVelocity(bullet)[0], EPSILON);
		assertEquals(0, facade.getBulletVelocity(bullet)[1], EPSILON);
		ship.setPosition(800, 500);
		assertEquals(0, facade.getNbBulletsOnShip(ship), EPSILON);
		world.evolve(2);
		assertEquals(1, facade.getNbBulletsOnShip(ship), EPSILON);
		assertTrue(facade.getBulletShip(bullet) == ship);
	}
	
	@Test
	public void testWorldEvolve2CollisionsSameTimeBulletShip() throws ModelException {
		World world = facade.createWorld(1000, 1000);
		Ship ship1 = facade.createShip(500, 500, 0, 0, 0, 20, 10);
		Ship ship2 = facade.createShip(700, 500, 0, 0, Math.PI/2, 20, 10);
		Bullet bullet1 = facade.createBullet(500, 500, 10, 10, 1);
		Bullet bullet2 = facade.createBullet(700, 500, 10, 10, 1);
		facade.addShipToWorld(world, ship1);
		facade.addShipToWorld(world, ship2);
		facade.loadBulletOnShip(ship1, bullet1);
		facade.fireBullet(ship1);
		facade.loadBulletOnShip(ship2, bullet2);
		facade.fireBullet(ship2);
		ship1.setPosition(700, 700);
		world.evolve(2);
		assertTrue(facade.isTerminatedBullet(bullet1));
		assertTrue(facade.isTerminatedBullet(bullet2));
		assertTrue(facade.isTerminatedShip(ship1));
		assertTrue(facade.isTerminatedShip(ship2));
	}
	
	@Test
	public void testWorldEvolve2CollisionsSameTimeBulletBullet() throws ModelException {
		World world = facade.createWorld(1000, 1000);
		Bullet bullet1 = facade.createBullet(500, 500, 250, 0, 1);
		Bullet bullet2 = facade.createBullet(700, 500, 0, 0, 1);
		Bullet bullet3 = facade.createBullet(500, 700, 250, 0, 1);
		Bullet bullet4 = facade.createBullet(700, 700, 0, 0, 1);
		facade.addBulletToWorld(world, bullet1);
		facade.addBulletToWorld(world, bullet2);
		facade.addBulletToWorld(world, bullet3);
		facade.addBulletToWorld(world, bullet4);
		world.evolve(2);
		assertTrue(facade.isTerminatedBullet(bullet1));
		assertTrue(facade.isTerminatedBullet(bullet2));
		assertTrue(facade.isTerminatedBullet(bullet3));
		assertTrue(facade.isTerminatedBullet(bullet4));
	}
	
	@Test
	public void testWorldEvolve2CollisionsSameTimeShipBoundary() throws ModelException {
		World world = facade.createWorld(1000, 1000);
		Ship ship1 = facade.createShip(950, 500, 20, 0, 0, 20, 10);
		Ship ship2 = facade.createShip(950, 400, 20, 0, 0, 20, 10);
		facade.addShipToWorld(world, ship1);
		facade.addShipToWorld(world, ship2);
		world.evolve(2);
		assertEquals(-20, facade.getShipVelocity(ship1)[0], EPSILON);
		assertEquals(-20, facade.getShipVelocity(ship2)[0], EPSILON);
	}
	
	@Test
	public void testWorldEvolveCollisionOnIntTime() throws ModelException {
		World world = facade.createWorld(1000, 1000);
		Ship ship = facade.createShip(950, 500, 30, 10, 0, 20, 10);
		facade.addShipToWorld(world, ship);	
		world.evolve(2);
		assertEquals(-30, facade.getShipVelocity(ship)[0], EPSILON);
		assertEquals(10, facade.getShipVelocity(ship)[1], EPSILON);
	}
	
}
