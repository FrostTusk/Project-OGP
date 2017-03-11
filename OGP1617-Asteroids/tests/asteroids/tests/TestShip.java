package asteroids.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import asteroids.model.Ship;
import asteroids.facade.Facade;
import asteroids.part1.facade.IFacade;
import asteroids.util.ModelException;

public class TestShip {

	private static final double EPSILON = 0.0001;

	IFacade facade;

	@Before
	public void setUp() {
		facade = new Facade();
	}

	
	 /*
	  * |-------------------------------------------|
	  * | 2. The next test test the Initialization.	|
	  * |-------------------------------------------| 
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
	}

	
	 /*
	  * |---------------------------------------|
	  * | 2. The next tests test the Position.	|
	  * |---------------------------------------| 
	  */	

	
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
	public void testCreateShipXIsNan() throws ModelException {
		facade.createShip(Double.NaN, 200, 10, -10, 20, Math.PI);
	}
	
	@Test(expected = ModelException.class)
	public void testCreateShipXIsNegInfinity() throws ModelException {
		facade.createShip(Double.NEGATIVE_INFINITY, 200, 10, -10, 20, Math.PI);
	}
	
	@Test(expected = ModelException.class)
	public void testCreateShipXYIsNegInfinity() throws ModelException {
		facade.createShip(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, 10, -10, 20, Math.PI);
	}
	
	@Test(expected = ModelException.class)
	public void testCreateShipXYIsPosInfinity() throws ModelException {
		facade.createShip(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, 10, -10, 20, Math.PI);
	}

	
	 /*
	  * |---------------------------------------|
	  * | 3. The next tests test the Speed.		|
	  * |---------------------------------------| 
	  */	
	
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
	public void testCreateShipVXIsNan() throws ModelException {
		Ship ship = facade.createShip(-200, 200, Double.NaN, -10, 20, Math.PI);
		assertEquals(0, facade.getShipVelocity(ship)[0], EPSILON);
	}
	
	@Test
	public void testCreateShipVXYIsNan() throws ModelException {
		Ship ship = facade.createShip(-200, 200, Double.NaN, Double.NaN, 20, Math.PI);
		assertEquals(0, facade.getShipVelocity(ship)[0], EPSILON);
		assertEquals(0, facade.getShipVelocity(ship)[1], EPSILON);
	}
	
	@Test
	public void testCreateShipVXIsNegInfinity() throws ModelException {
		Ship ship = facade.createShip(200, 200, Double.NEGATIVE_INFINITY, -10, 20, Math.PI);
		assertEquals(0, facade.getShipVelocity(ship)[0], EPSILON);
	}
	
	@Test
	public void testCreateShipVXYIsNegInfinity() throws ModelException {
		Ship ship = facade.createShip(-200, 200, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, 20, Math.PI);
		assertEquals(0, facade.getShipVelocity(ship)[0], EPSILON);
		assertEquals(0, facade.getShipVelocity(ship)[1], EPSILON);
	}
	
	@Test
	public void testCreateShipVXYIsPosInfinity() throws ModelException {
		Ship ship = facade.createShip(200, 200, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, 20, Math.PI);
		assertEquals(0, facade.getShipVelocity(ship)[0], EPSILON);
		assertEquals(0, facade.getShipVelocity(ship)[1], EPSILON);
	}

	
	 /*
	  * |---------------------------------------|
	  * | 4. The next tests test the Radius.	|
	  * |---------------------------------------| 
	  */	
	
	
	@Test(expected = ModelException.class)
	public void testCreateShipRadiusZero() throws ModelException {
		facade.createShip(100, 200, 10, -10, 0, Math.PI);
	}
	
	@Test(expected = ModelException.class)
	public void testCreateShipRadiusNegative() throws ModelException {
		facade.createShip(100, 200, 10, -10, -20, Math.PI);
	}
	
	@Test(expected = ModelException.class)
	public void testCreateShipRadiusIsNan() throws ModelException {
		facade.createShip(100, 200, 10, -10, Double.NaN, Math.PI);
	}
	
	@Test(expected = ModelException.class)
	public void testCreateShipRadiusIsPosInfinity() throws ModelException {
		facade.createShip(100, 200, 10, -10, Double.POSITIVE_INFINITY, Math.PI);
	}
	
	
	 /*
	  * |-------------------------------------------|
	  * | 5. The next tests test the Orientation.	|
	  * |-------------------------------------------| 
	  */	
	
	@Test
	public void testCreateShipOGeneric() throws ModelException {
		Ship ship1 = facade.createShip(100, 200, 100, 200, 20, 2*Math.PI);
		assertEquals(2*Math.PI, facade.getShipOrientation(ship1), EPSILON);
		
		Ship ship2 = facade.createShip(100, 200, 100, 200, 20, 0);
		assertEquals(0, facade.getShipOrientation(ship2), EPSILON);
		
		Ship ship3 = facade.createShip(100, 200, 100, 200, 20, Math.PI);
		assertEquals(Math.PI, facade.getShipOrientation(ship3), EPSILON);
	}
	
	@Test(expected = ModelException.class)
	public void testCreateShipOisNeg() throws ModelException {
		facade.createShip(100, 200, 10, -10, 0, -Math.PI);
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

	
	@Test
	public void testMove() throws ModelException {
		Ship ship = facade.createShip(100, 100, 30, -15, 20, 0);
		facade.move(ship, 1);
		double[] position = facade.getShipPosition(ship);
		assertNotNull(position);
		assertEquals(130, position[0], EPSILON);
		assertEquals(85, position[1], EPSILON);
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
	public void testMoveTisNeg() throws ModelException {
		Ship ship = facade.createShip(100, 100, 30, -15, 20, 0);
		facade.move(ship, -1);
	}
	
	@Test(expected = ModelException.class)
	public void testMoveTisNaN() throws ModelException {
		Ship ship = facade.createShip(100, 100, 30, -15, 20, 0);
		facade.move(ship, Double.NaN);
	}
	
	@Test(expected = ModelException.class)
	public void testMoveTisNegInfinity() throws ModelException {
		Ship ship = facade.createShip(100, 100, 30, -15, 20, 0);
		facade.move(ship, Double.NEGATIVE_INFINITY);
	}
	
	@Test(expected = ModelException.class)
	public void testMoveTisPosInfinity() throws ModelException {
		Ship ship = facade.createShip(100, 100, 30, -15, 20, 0);
		facade.move(ship, Double.POSITIVE_INFINITY);
	}

	 /*
	  * |-------------------------------------------|
	  * | 6. The next tests test the Turn method.	|
	  * |-------------------------------------------| 
	  */	
	
	@Test
	public void testTurn() throws ModelException {
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
	
	@Test(expected = ModelException.class)
	public void testTurnOisNaN() throws ModelException {
		Ship ship = facade.createShip(100, 100, 30, -15, 20, Math.PI);
		facade.turn(ship, Double.NaN);
	}
	
	@Test(expected = ModelException.class)
	public void testTurnOisNegInfinity() throws ModelException {
		Ship ship = facade.createShip(100, 100, 30, -15, 20, Math.PI);
		facade.turn(ship, Double.NEGATIVE_INFINITY);
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
}
