package asteroids.facade;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import asteroids.facade.Facade;
import asteroids.helper.entity.Entity;
import asteroids.model.Bullet;
import asteroids.model.Program;
import asteroids.model.Ship;
import asteroids.model.World;
import asteroids.part3.facade.IFacade;
import asteroids.part3.programs.IProgramFactory;
import asteroids.part3.programs.internal.ProgramParser;
import asteroids.util.ModelException;

/* 
 * Tests Index:
 * #1# Ship Tests
 * 		1. Tests for Initialization
 * 		2. Tests for Position
 * 		3. Tests for Speed
 * 		4. Tests for Radius
 * 		5. Tests for Orientation
 * 		6. Tests for Move
 * 		7. Tests for Thrust
 * 		8. Tests for Turn
 * 		9. Tests for Distance
 * 		10. Tests for Overlap
 * 		11. Tests for Collision Detection
 * #2# Bullet Tests
 * 		12. Tests for Initialization
 * 		13. Tests for Position
 * 		14. Tests for Speed
 * 		15. Tests for Radius
 * 		16. Tests for Mass
 * 		17. Tests for Worlds
 * 		18. Tests for Ships
 * #3# World Tests
 * 		19. Tests for Initialization
 * 		20. Tests for Size
 * 		21. Tests for Entities
 * 		22. Tests for Evolve
 * #4# Hook-Up Tests
 */


@SuppressWarnings("deprecation")
public class TestFacade {

	private static final double EPSILON = 0.0001;

	IFacade facade;
	IProgramFactory<?, ?, ?, Program> programFactory; 

	
	@Before @SuppressWarnings("unchecked")
	public void setUp() throws ModelException {
		facade = new Facade();
		programFactory = (IProgramFactory<?, ?, ?, Program>) facade.createProgramFactory();
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
	
	
	@Test
	public void testCreateShipXIsPosInfinity() throws ModelException {
		facade.createShip(Double.POSITIVE_INFINITY, 200, 10, -10, 20, Math.PI);
	}
	
	@Test
	public void testCreateShipXIsNegInfinity() throws ModelException {
		facade.createShip(Double.NEGATIVE_INFINITY, 200, 10, -10, 20, Math.PI);
	}
	
	@Test(expected = ModelException.class)
	public void testCreateShipXIsNan() throws ModelException {
		facade.createShip(Double.NaN, 200, 10, -10, 20, Math.PI);
	}
	
	
	@Test
	public void testCreateShipYIsPosInfinity() throws ModelException {
		facade.createShip(200, Double.POSITIVE_INFINITY, 10, -10, 20, Math.PI);
	}
	
	@Test
	public void testCreateShipYIsNegInfinity() throws ModelException {
		facade.createShip(200, Double.NEGATIVE_INFINITY, 10, -10, 20, Math.PI);
	}
	
	@Test(expected = ModelException.class)
	public void testCreateShipYIsNan() throws ModelException {
		facade.createShip(200, Double.NaN, 10, -10, 20, Math.PI);
	}
	
	
	@Test
	public void testCreateShipXYIsPosInfinity() throws ModelException {
		facade.createShip(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, 10, -10, 20, Math.PI);
	}
	
	@Test
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
	
	
	@Test
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
			 * |------------------------------------------------------------|
			 * | 11. The next tests test the Collision Detection methods.	|
			 * |------------------------------------------------------------| 
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
	
	@Test(expected = ModelException.class)
	public void testCollisionDetectionSameShip() throws ModelException {		
		Ship ship1 = facade.createShip(0, 0, 10, 0, 10, 0);
		double[] position = facade.getCollisionPosition(ship1, ship1);
		assertNull(position);
		assertEquals(Double.POSITIVE_INFINITY, facade.getTimeToCollision(ship1, ship1), EPSILON);
	}
	
	
	
		/*
		 * |----------------------------|
		 * | #Header-2# Bullet Tests.	|
		 * |----------------------------| 
		 */
	
	
			/*
			 * |--------------------------------------------|
			 * | 12. The next test test the Initialization.	|
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
			 * | 13. The next tests test the Position.	|
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


	@Test
	public void testCreateBulletXIsPosInfinity() throws ModelException {
		facade.createBullet(Double.POSITIVE_INFINITY, 200, 10, -10, 20);
	}

	@Test
	public void testCreateBulletXIsNegInfinity() throws ModelException {
		facade.createBullet(Double.NEGATIVE_INFINITY, 200, 10, -10, 20);
	}

	@Test(expected = ModelException.class)
	public void testCreateBulletXIsNan() throws ModelException {
		facade.createBullet(Double.NaN, 200, 10, -10, 20);
	}


	@Test
	public void testCreateBulletYIsPosInfinity() throws ModelException {
		facade.createBullet(200, Double.POSITIVE_INFINITY, 10, -10, 20);
	}

	@Test
	public void testCreateBulletYIsNegInfinity() throws ModelException {
		facade.createBullet(200, Double.NEGATIVE_INFINITY, 10, -10, 20);
	}
	
	@Test(expected = ModelException.class)
	public void testCreateBulletYIsNan() throws ModelException {
		facade.createBullet(200, Double.NaN, 10, -10, 20);
	}


	@Test
	public void testCreateBulletXYIsPosInfinity() throws ModelException {
		facade.createBullet(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, 10, -10, 20);
	}

	@Test
	public void testCreateBulletXYIsNegInfinity() throws ModelException {
		facade.createBullet(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, 10, -10, 20);
	}

	@Test(expected = ModelException.class)
	public void testCreateBulletXYIsNaN() throws ModelException {
		facade.createBullet(Double.NaN, Double.NaN, 10, -10, 20);
	}


	
			/*
			 * |------------------------------------|
			 * | 14. The next tests test the Speed.	|
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
			 * |----------------------------------------|
			 * | 15. The next tests test the Radius.	|
			 * |----------------------------------------| 
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
			 * | 16. The next tests test the Mass.	|
			 * |------------------------------------| 
			 */	
	
	

	@Test
	public void testBulletGetMassGeneric() throws ModelException {
		Bullet bullet = facade.createBullet(100, 200, 10, -10, 50);
		assertNotNull(bullet);
		assertEquals(((double)4/(double)3) * Math.PI * Math.pow(50, 3) * (bullet.getDensity()), facade.getBulletMass(bullet), EPSILON);
	}

	@Test
	public void testBulletSetMassGeneric() throws ModelException {
		Bullet bullet = facade.createBullet(100, 200, 10, -10, 50);
		assertNotNull(bullet);
		bullet.setMass();
		assertEquals(((double)4/(double)3) * Math.PI * Math.pow(50, 3) * (bullet.getDensity()), facade.getBulletMass(bullet), EPSILON);
	}

	@Test
	public void testBulletGetMassWithoutSetMass() throws ModelException {
		Bullet bullet = facade.createBullet(100, 200, 10, -10, 50);
		assertNotNull(bullet);
		facade.getBulletMass(bullet);
		assertEquals(((double)4/(double)3) * Math.PI * Math.pow(facade.getBulletRadius(bullet), 3) * (bullet.getDensity()), facade.getBulletMass(bullet), EPSILON);
	}


	
			/*
			 * |--------------------------------------------------------|
			 * | 17. The next tests test the interaction with worlds.	|
			 * |--------------------------------------------------------| 
			 */	



	@Test
	public void testBulletGetTimeToCollisionWorldGeneric() throws ModelException {
		Bullet bullet = facade.createBullet(2, 2, 1, 0, 1);
		World world = facade.createWorld(100, 100);
		bullet.setWorld(world);
		assertEquals(97, facade.getTimeCollisionBoundary(bullet), EPSILON);
	} 

	@Test
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
			 * | 18. The next tests test the interaction with entities.	|
			 * |--------------------------------------------------------| 
			 */	


	
	@Test(expected = ModelException.class)
	public void testBulletGetTimeToCollisionOverlap() throws ModelException {
		Bullet bullet1 = facade.createBullet(100, 100, 0, 0, 20);
		Bullet bullet2 = facade.createBullet(100, 100, 0, 0, 20);
		assertEquals(0, facade.getTimeCollisionEntity(bullet1, bullet2), EPSILON);
	}

	
	
		/*
		 *|-----------------------------|
		 *| #Header-3# World Methods.	|
		 *|-----------------------------| 
		 */
	
	
			/*
			 * |--------------------------------------------|
			 * | 19. The next test test the Initialization.	|
			 * |--------------------------------------------| 
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
			 * |----------------------------------|
			 * | 20. The next test test the Size. |
			 * |----------------------------------| 
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
			 * | 21. The next test test the relation with Entities.	|
			 * |----------------------------------------------------| 
			 */	
			

	
	@Test
	public void testWorldGetFirstCollisionTimeNoEntities() throws ModelException {
		World world = facade.createWorld(1000, 1000);
		assertEquals(-1, facade.getTimeNextCollision(world), EPSILON);
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
			 * | 22. The next test test the evolve method.	|
			 * |--------------------------------------------| 
			 */	
	
	
	
	@Test
	public void testWorldEvolveNoEntities() throws ModelException {
		World world = facade.createWorld(1000, 1000);
		world.evolve(2, null);
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
		world.evolve(10, null);
		assertEquals(-90, facade.getBulletVelocity(bullet)[0], EPSILON);
		assertEquals(0, facade.getBulletVelocity(bullet)[1], EPSILON);
		assertEquals(1, bullet.getBoundaryCollisionCounter(), EPSILON);
		world.evolve(10, null);
		assertEquals(90, facade.getBulletVelocity(bullet)[0], EPSILON);
		assertEquals(0, facade.getBulletVelocity(bullet)[1], EPSILON);
		assertEquals(2, bullet.getBoundaryCollisionCounter(), EPSILON);
		world.evolve(10, null);
		assertTrue(facade.isTerminatedBullet(bullet));
	}
	
	@Test
	public void testWorldEvolveCollisionBulletHorizontalBoundaryWidth() throws ModelException {
		World world = facade.createWorld(1000, 1000);
		Bullet bullet = facade.createBullet(900, 5, 10, 1, 1);
		facade.addBulletToWorld(world, bullet);
		double counter = bullet.getBoundaryCollisionCounter();
		assertEquals(0, counter, EPSILON);
		world.evolve(7, null);
		assertEquals(10, facade.getBulletVelocity(bullet)[0], EPSILON);
		assertEquals(1, facade.getBulletVelocity(bullet)[1], EPSILON);
		world.evolve(10, null);
		assertEquals(-10, facade.getBulletVelocity(bullet)[0], EPSILON);
		assertEquals(1, facade.getBulletVelocity(bullet)[1], EPSILON);
		assertEquals(1, bullet.getBoundaryCollisionCounter(), EPSILON);
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
		world.evolve(2, null);
		assertTrue(facade.isTerminatedBullet(bullet1));
		assertTrue(facade.isTerminatedBullet(bullet2));
		assertTrue(facade.isTerminatedBullet(bullet3));
		assertTrue(facade.isTerminatedBullet(bullet4));
	}
	
	
	
		/*
		 *|-----------------------------|
		 *| #Header-4# Hook-Up Tests.	|
		 *|-----------------------------| 
		 */
	
	
	
	@Test
	public void testHookupCreateBullet() throws ModelException {
		Bullet bullet = facade.createBullet(500, 500, 250, 0, 1);
		assertNotNull(bullet);
	}
	
	@Test
	public void testHookupGetShipPosition() throws ModelException {
		Ship ship = facade.createShip(10, 10, 0, 0, 10, 0, 0);
		assertEquals(10, facade.getShipPosition(ship)[0], EPSILON);
		assertEquals(10, facade.getShipPosition(ship)[1], EPSILON);
	}
	
	@Test
	public void testHookupGetShipVelocity() throws ModelException {
		Ship ship = facade.createShip(10, 10, 50, 20, 10, 0, 0);
		assertEquals(50, facade.getShipVelocity(ship)[0], EPSILON);
		assertEquals(20, facade.getShipVelocity(ship)[1], EPSILON);
	}
	
	@Test
	public void testHookupGetShipRadius() throws ModelException {
		Ship ship = facade.createShip(20, 20, 50, 20, 10, 0, 0);
		assertEquals(10, facade.getShipRadius(ship), EPSILON);
	}
	
	@Test
	public void testHookupGetShipOrientation() throws ModelException {
		Ship ship = facade.createShip(10, 10, 50, 20, 10, 0, 5);
		assertEquals(0, facade.getShipOrientation(ship), EPSILON);
	}
	
	@Test
	public void testHookupShipTurn() throws ModelException {
		Ship ship = facade.createShip(10, 10, 50, 20, 10, 0, 5);
		assertEquals(0, facade.getShipOrientation(ship), EPSILON);
		facade.turn(ship, Math.PI);
		assertEquals(Math.PI, facade.getShipOrientation(ship), EPSILON);
	}
	
	@Test
	public void testHookupGetDistanceBetweenShipShip() throws ModelException {
		Ship ship1 = facade.createShip(10, 10, 50, 0, 10, Math.PI, 0);
		Ship ship2 = facade.createShip(530, 10, -50, 0, 10, Math.PI, 0);
		World world = facade.createWorld(800, 800);
		facade.addShipToWorld(world, ship1);
		facade.addShipToWorld(world, ship2);
		assertEquals(500, facade.getDistanceBetween(ship1, ship2), EPSILON);
	}
	
	@Test
	public void testHookupOverlapShipShip() throws ModelException {
		Ship ship1 = facade.createShip(10, 10, 50, 0, 10, Math.PI, 0);
		Ship ship2 = facade.createShip(530, 10, -50, 0, 10, Math.PI, 0);
		Ship ship3 = facade.createShip(540, 10, -50, 0, 20, Math.PI, 0);
		assertFalse(facade.overlap(ship1, ship2));
		assertTrue(facade.overlap(ship3, ship2));
	}
	
	@Test
	public void testHookupGetTimeToCollisionShipShip() throws ModelException {
		Ship ship1 = facade.createShip(10, 10, 50, 0, 10, Math.PI, 0);
		Ship ship2 = facade.createShip(530, 10, -50, 0, 10, Math.PI, 0);
		World world = facade.createWorld(800, 800);
		facade.addShipToWorld(world, ship1);
		facade.addShipToWorld(world, ship2);
		assertEquals(5, facade.getTimeToCollision(ship1, ship2), EPSILON);
	}
	
	@Test
	public void testHookupGetCollisionPositionShipShip() throws ModelException {
		Ship ship1 = facade.createShip(10, 10, 50, 0, 10, Math.PI, 0);
		Ship ship2 = facade.createShip(530, 10, -50, 0, 10, Math.PI, 0);
		World world = facade.createWorld(800, 800);
		facade.addShipToWorld(world, ship1);
		facade.addShipToWorld(world, ship2);
		assertEquals(270, facade.getPositionCollisionEntity(ship1, ship2)[0], EPSILON);
		assertEquals(10, facade.getPositionCollisionEntity(ship1, ship2)[1], EPSILON);
	}
	
	@Test
	public void testHookupCreateShip() throws ModelException {
		Ship ship = facade.createShip(10, 10, 50, 0, 10, Math.PI, 0);
		assertNotNull(ship);
	}
	
	@Test
	public void testHookupTerminateShip() throws ModelException {
		Ship ship = facade.createShip(10, 10, 50, 0, 10, Math.PI, 0);
		assertNotNull(ship);
		facade.terminateShip(ship);
		assertTrue(facade.isTerminatedShip(ship));
	}
	
	@Test
	public void testHookupIsTerminatedShip() throws ModelException {
		Ship ship = facade.createShip(10, 10, 50, 0, 10, Math.PI, 0);
		assertNotNull(ship);
		assertFalse(facade.isTerminatedShip(ship));
		facade.terminateShip(ship);
		assertTrue(facade.isTerminatedShip(ship));
	}
	
	@Test
	public void testHookupGetShipMass() throws ModelException {
		Ship ship = facade.createShip(10, 10, 50, 0, 10, Math.PI, Math.pow(10, 30));
		assertEquals(Math.pow(10, 30), facade.getShipMass(ship), EPSILON);
	}
	
	@Test
	public void testHookupGetShipWorld() throws ModelException {
		Ship ship = facade.createShip(10, 10, 50, 0, 10, Math.PI, 0);
		World world = facade.createWorld(800, 800);
		ship.setWorld(world);
		assertTrue(facade.getShipWorld(ship) == world);
	}
	
	@Test
	public void testHookupIsThrusterActive() throws ModelException {
		Ship ship = facade.createShip(10, 10, 50, 0, 10, Math.PI, 0);
		assertFalse(facade.isShipThrusterActive(ship));
	}
	
	@Test
	public void testHookupSetThrusterActive() throws ModelException {
		Ship ship = facade.createShip(10, 10, 50, 0, 10, Math.PI, 0);
		assertFalse(facade.isShipThrusterActive(ship));
		facade.setThrusterActive(ship, true);
		assertTrue(facade.isShipThrusterActive(ship));
		facade.setThrusterActive(ship, false);
		assertFalse(facade.isShipThrusterActive(ship));
	}
	
	@Test
	public void testHookupGetShipAcceleration() throws ModelException {
		Ship ship = facade.createShip(10, 10, 50, 0, 10, Math.PI, 0);
		assertEquals(0, facade.getShipAcceleration(ship), EPSILON);
	}
	
	@Test
	public void testHookupTerminateBullet() throws ModelException {
		Bullet bullet = facade.createBullet(500, 500, 250, 0, 1);
		assertNotNull(bullet);
		assertFalse(facade.isTerminatedBullet(bullet));
	}
	
	@Test
	public void testHookupIsTerminatedBullet() throws ModelException {
		Bullet bullet = facade.createBullet(500, 500, 250, 0, 1);
		assertNotNull(bullet);
		assertFalse(facade.isTerminatedBullet(bullet));
		facade.terminateBullet(bullet);
		assertTrue(facade.isTerminatedBullet(bullet));
	}
	
	@Test
	public void testHookupGetBulletPosition() throws ModelException {
		Bullet bullet = facade.createBullet(500, 500, 250, 0, 1);
		assertNotNull(bullet);
		assertEquals(500, facade.getBulletPosition(bullet)[0], EPSILON);
		assertEquals(500, facade.getBulletPosition(bullet)[1], EPSILON);
	}
	
	@Test
	public void testHookupGetBulletVelocity() throws ModelException {
		Bullet bullet = facade.createBullet(500, 500, 250, 0, 1);
		assertNotNull(bullet);
		assertEquals(250, facade.getBulletVelocity(bullet)[0], EPSILON);
		assertEquals(0, facade.getBulletVelocity(bullet)[1], EPSILON);
	}
	
	@Test
	public void testHookupGetBulletRadius() throws ModelException {
		Bullet bullet = facade.createBullet(500, 500, 250, 0, 1);
		assertNotNull(bullet);
		assertEquals(1, facade.getBulletRadius(bullet), EPSILON);
	}
	
	@Test
	public void testHookupGetBulletMass() throws ModelException {
		Bullet bullet = facade.createBullet(500, 500, 250, 0, 1);
		assertNotNull(bullet);
		assertEquals(((double)4/(double)3) * Math.PI * Math.pow(1, 3) * 7.8 * Math.pow(10, 12), facade.getBulletMass(bullet), EPSILON);
	}
	
	@Test
	public void testHookupGetBulletWorld() throws ModelException {
		Bullet bullet = facade.createBullet(500, 500, 250, 0, 1);
		World world = facade.createWorld(1000, 1000);
		facade.addBulletToWorld(world, bullet);
		assertEquals(world, facade.getBulletWorld(bullet));
	}
	
	@Test
	public void testHookupGetBulletShip() throws ModelException {
		Bullet bullet = facade.createBullet(500, 500, 250, 0, 1);
		Ship ship = facade.createShip(500, 500, 50, 0, 10, Math.PI, 0);
		bullet.setShip(ship);
		assertEquals(ship, facade.getBulletShip(bullet));
	}
	
	@Test
	public void testHookupGetBulletSource() throws ModelException {
		Bullet bullet = facade.createBullet(500, 500, 250, 0, 1);
		Ship ship = facade.createShip(500, 500, 50, 0, 10, Math.PI, 0);
		World world = facade.createWorld(1000, 1000);
		assertEquals(null, facade.getBulletSource(bullet));
		facade.addShipToWorld(world, ship);
		facade.loadBulletOnShip(ship, bullet);
		facade.fireBullet(ship);
		assertEquals(ship, facade.getBulletSource(bullet));
	}
	
	@Test
	public void testHookupCreateWorld() throws ModelException {
		World world = facade.createWorld(1000, 1000);
		assertNotNull(world);
	}
	
	@Test
	public void testHookupTerminateWorld() throws ModelException {
		World world = facade.createWorld(1000, 1000);
		facade.terminateWorld(world);
		assertTrue(facade.isTerminatedWorld(world));
	}
	
	@Test
	public void testHookupIsTerminatedWorld() throws ModelException {
		World world = facade.createWorld(1000, 1000);
		assertNotNull(world);
		assertFalse(facade.isTerminatedWorld(world));
		facade.terminateWorld(world);
		assertTrue(facade.isTerminatedWorld(world));
	}
	
	@Test
	public void testHookupGetWorldSize() throws ModelException {
		World world = facade.createWorld(1000, 1200);
		assertEquals(1000, facade.getWorldSize(world)[0], EPSILON);
		assertEquals(1200, facade.getWorldSize(world)[1], EPSILON);
	}
	
	@Test
	public void testHookupGetWorldShips() throws ModelException {
		World world = facade.createWorld(1000, 1200);
		Ship ship = facade.createShip(10, 10, 50, 0, 10, Math.PI, 0);		
		facade.addShipToWorld(world, ship);
		Set<Ship> ships = world.getAllEntitiesSpecific(Ship.class);
		assertEquals(ships, facade.getWorldShips(world));
	}
	
	@Test
	public void testHookupGetWorldBullets() throws ModelException {
		World world = facade.createWorld(1000, 1200);
		Bullet bullet = facade.createBullet(500, 500, 250, 0, 1);		
		facade.addBulletToWorld(world, bullet);
		Set<Bullet> bullets = world.getAllEntitiesSpecific(Bullet.class);
		assertEquals(bullets, facade.getWorldBullets(world));
	}
	
	@Test
	public void testHookupAddShipToWorld() throws ModelException {
		World world = facade.createWorld(1000, 1200);
		Ship ship = facade.createShip(10, 10, 50, 0, 10, Math.PI, 0);		
		facade.addShipToWorld(world, ship);
		assertTrue(facade.getWorldShips(world).contains(ship));
	}
	
	@Test
	public void testHookupRemoveShipFromWorld() throws ModelException {
		World world = facade.createWorld(1000, 1200);
		Ship ship = facade.createShip(10, 10, 50, 0, 10, Math.PI, 0);		
		facade.addShipToWorld(world, ship);
		assertTrue(facade.getWorldShips(world).contains(ship));
		facade.removeShipFromWorld(world, ship);
		assertFalse(facade.getWorldShips(world).contains(ship));
	}
	
	@Test
	public void testHookupAddBulletToWorld() throws ModelException {
		World world = facade.createWorld(1000, 1200);
		Bullet bullet = facade.createBullet(500, 500, 250, 0, 1);	
		facade.addBulletToWorld(world, bullet);
		assertTrue(facade.getWorldBullets(world).contains(bullet));
	}
	
	@Test
	public void testHookupRemoveBulletFromWorld() throws ModelException {
		World world = facade.createWorld(1000, 1200);
		Bullet bullet = facade.createBullet(500, 500, 250, 0, 1);	
		facade.addBulletToWorld(world, bullet);
		assertTrue(facade.getWorldBullets(world).contains(bullet));
		facade.removeBulletFromWorld(world, bullet);
		assertFalse(facade.getWorldBullets(world).contains(bullet));
	}
	
	@Test
	public void testHookupGetBulletsOnShip() throws ModelException {
		Ship ship = facade.createShip(500, 500, 50, 0, 10, Math.PI, 0);	
		Bullet bullet = facade.createBullet(500, 500, 250, 0, 1);	
		facade.loadBulletOnShip(ship, bullet);
		Set<Bullet> bullets = ship.getAllBullets();
		assertEquals(bullets, facade.getBulletsOnShip(ship));
	}
	
	@Test
	public void testHookupGetNbBulletsOnShip() throws ModelException {
		Ship ship = facade.createShip(500, 500, 50, 0, 10, Math.PI, 0);	
		Bullet bullet = facade.createBullet(500, 500, 250, 0, 1);	
		facade.loadBulletOnShip(ship, bullet);
		assertEquals(1, facade.getNbBulletsOnShip(ship));
	}
	
	@Test
	public void testHookupLoadBulletOnShip() throws ModelException {
		Ship ship = facade.createShip(500, 500, 50, 0, 10, Math.PI, 0);	
		Bullet bullet = facade.createBullet(500, 500, 250, 0, 1);	
		assertEquals(0, facade.getNbBulletsOnShip(ship));
		facade.loadBulletOnShip(ship, bullet);
		assertEquals(1, facade.getNbBulletsOnShip(ship));
	}
	
	@Test
	public void testHookupLoadBulletsOnShip() throws ModelException {
		Ship ship = facade.createShip(500, 500, 50, 0, 10, Math.PI, 0);	
		Bullet bullet1 = facade.createBullet(500, 500, 250, 0, 1);
		Bullet bullet2 = facade.createBullet(500, 500, 250, 0, 1);
		List<Bullet> bullets = Arrays.asList(bullet1, bullet2);
		assertEquals(0, facade.getNbBulletsOnShip(ship));
		facade.loadBulletsOnShip(ship, bullets);
		assertEquals(2, facade.getNbBulletsOnShip(ship));
	}
	
	@Test
	public void testHookupRemoveBulletFromShip() throws ModelException {
		Ship ship = facade.createShip(500, 500, 50, 0, 10, Math.PI, 0);	
		Bullet bullet1 = facade.createBullet(500, 500, 250, 0, 1);
		Bullet bullet2 = facade.createBullet(500, 500, 250, 0, 1);
		facade.loadBulletOnShip(ship, bullet1);
		facade.loadBulletOnShip(ship, bullet2);
		assertTrue(ship.getAllBullets().contains(bullet1));
		assertTrue(ship.getAllBullets().contains(bullet2));
		facade.removeBulletFromShip(ship, bullet2);
		assertTrue(ship.getAllBullets().contains(bullet1));
		assertFalse(ship.getAllBullets().contains(bullet2));
	}
	
	@Test
	public void testHookupFireBullet() throws ModelException {
		World world = facade.createWorld(1000, 1200);
		Ship ship = facade.createShip(500, 500, 50, 0, 10, Math.PI, 0);	
		Bullet bullet = facade.createBullet(500, 500, 250, 0, 1);
		facade.addShipToWorld(world, ship);
		facade.loadBulletOnShip(ship, bullet);
		facade.fireBullet(ship);
		assertTrue(bullet.hasBeenFired());
	}
	
	@Test
	public void testHookupGetTimeCollisionBoundary() throws ModelException {
		World world = facade.createWorld(1000, 1200);
		Ship ship = facade.createShip(490, 500, 50, 0, 10, Math.PI, 0);	
		facade.addShipToWorld(world, ship);
		assertEquals(10, facade.getTimeCollisionBoundary(ship), EPSILON);
	}

	@Test
	public void testHookupGetPositionCollisionBoundary() throws ModelException {
		World world = facade.createWorld(1000, 1200);
		Ship ship = facade.createShip(500, 500, 50, 0, 10, Math.PI, 0);	
		facade.addShipToWorld(world, ship);
		assertEquals(1000, facade.getPositionCollisionBoundary(ship)[0], EPSILON);
		assertEquals(500, facade.getPositionCollisionBoundary(ship)[1], EPSILON);
	}
	
	@Test
	public void testHookupGetTimeCollisionEntity() throws ModelException {
		World world = facade.createWorld(1000, 1200);
		Ship ship1 = facade.createShip(490, 500, 50, 0, 10, Math.PI, 0);	
		Ship ship2 = facade.createShip(610, 500, -50, 0, 10, Math.PI, 0);	
		facade.addShipToWorld(world, ship1);
		facade.addShipToWorld(world, ship2);
		assertEquals(1, facade.getTimeCollisionEntity(ship1, ship2), EPSILON);
	}

	@Test
	public void testHookupGetPositionCollisionEntity() throws ModelException {
		World world = facade.createWorld(1000, 1200);
		Ship ship1 = facade.createShip(490, 500, 50, 0, 10, Math.PI, 0);	
		Ship ship2 = facade.createShip(610, 500, -50, 0, 10, Math.PI, 0);	
		facade.addShipToWorld(world, ship1);
		facade.addShipToWorld(world, ship2);
		assertEquals(550, facade.getPositionCollisionEntity(ship1, ship2)[0], EPSILON);
		assertEquals(500, facade.getPositionCollisionEntity(ship1, ship2)[1], EPSILON);
	}
	
	@Test
	public void testHookupGetTimeNextCollision() throws ModelException {
		World world = facade.createWorld(1000, 1200);
		Ship ship1 = facade.createShip(490, 500, 50, 0, 10, Math.PI, 0);	
		Ship ship2 = facade.createShip(610, 500, -50, 0, 10, Math.PI, 0);	
		facade.addShipToWorld(world, ship1);
		facade.addShipToWorld(world, ship2);
		assertEquals(1, facade.getTimeNextCollision(world), EPSILON);
	}

	@Test
	public void testHookupGetPositionNextCollision() throws ModelException {
		World world = facade.createWorld(1000, 1200);
		Ship ship1 = facade.createShip(490, 500, 50, 0, 10, Math.PI, 0);	
		Ship ship2 = facade.createShip(610, 500, -50, 0, 10, Math.PI, 0);	
		facade.addShipToWorld(world, ship1);
		facade.addShipToWorld(world, ship2);
		assertEquals(550, facade.getPositionNextCollision(world)[0], EPSILON);
		assertEquals(500, facade.getPositionNextCollision(world)[1], EPSILON);
	}
	
	@Test
	public void testHookupEvolve() throws ModelException {
		World world = facade.createWorld(1000, 1200);
		Ship ship1 = facade.createShip(600, 500, 50, 0, 10, Math.PI, 0);	
		Ship ship2 = facade.createShip(600, 600, -50, 0, 10, Math.PI, 0);	
		facade.addShipToWorld(world, ship1);
		facade.addShipToWorld(world, ship2);
		facade.evolve(world, 2, null);
		assertEquals(700, facade.getShipPosition(ship1)[0], EPSILON);
		assertEquals(500, facade.getShipPosition(ship1)[1], EPSILON);
		assertEquals(500, facade.getShipPosition(ship2)[0], EPSILON);
		assertEquals(600, facade.getShipPosition(ship2)[1], EPSILON);
	}
	
	@Test
	public void testHookupGetEntityAt() throws ModelException {
		World world = facade.createWorld(1000, 1200);
		Ship ship1 = facade.createShip(600, 500, 50, 0, 10, Math.PI, 0);	
		Ship ship2 = facade.createShip(600, 600, -50, 0, 10, Math.PI, 0);	
		facade.addShipToWorld(world, ship1);
		facade.addShipToWorld(world, ship2);
		assertEquals(ship1, facade.getEntityAt(world, 600, 500));
		assertEquals(ship2, facade.getEntityAt(world, 600, 600));
	}
	
	@Test
	public void testHookupGetWorldEntities() throws ModelException {
		World world = facade.createWorld(1000, 1200);
		Bullet bullet = facade.createBullet(500, 500, 250, 0, 1);
		Ship ship = facade.createShip(10, 10, 50, 0, 10, Math.PI, 0);		
		facade.addShipToWorld(world, ship);
		facade.addBulletToWorld(world, bullet);
		Set<Entity> entities = world.getAllEntities();
		assertEquals(entities, facade.getEntities(world));
	}
	

	
	public void nullProgramTest() throws ModelException {
		Ship ship = facade.createShip(10, 10, 50, 0, 10, Math.PI, 0);
		String code = "a := null;" + "print a;";
		Program program = ProgramParser.parseProgramFromString(code, programFactory);
		facade.loadProgramOnShip(ship, program);
		facade.executeProgram(ship, 1.0);
	}
  
}
