package asteroids.junk;

import asteroids.model.Ship;

/**
 * Do not use this test class! This test class was
 * written at the very start of the assignment and is unnecessary.
 * Use JUnit 4 test suites instead. Use this class only to test
 * very little things.
 * This class should not be delivered in the final version
 * just as the other classes in asteroids.junk.
 * 
 * @author Mathijs Hubrechtsen
 */
public class ManualTests {
	private static double pi = Math.PI;
	private static boolean debug = false;
	private static boolean showOriginalTests = false;
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		// ship parameters = positionX, positionY, velocityX, velocityY, orientation, shape

		boolean test;
		if (0.1 > 0) test = true;
		else test = false;
		System.out.println(test);
		
		if (showOriginalTests) runOriginalTestHandler();
	}
	
	
	public static void runOriginalTestHandler() {
		System.out.println("Ship 1:");
		Ship ship1 = new Ship(0, 0, 100, 100, 0, 20, 1);
		runTestSingle(ship1, debug);
		System.out.println("Ship 2:");
		Ship ship2 = new Ship(100, 100, 50, 50, 0, 10, 1);
		runTestSingle(ship2, debug);
		System.out.println("");
		System.out.println("");
		
		System.out.println("Ship 3:");
		Ship ship3 = new Ship(0, 0, 100, 100, 0, 10, 1);
		runTestSingle(ship3, debug);
		System.out.println("Ship 4:");
		Ship ship4 = new Ship(100, 100, 50, 50, 0, 10, 1);
		runTestSingle(ship4, debug);
		System.out.println("");
		System.out.println("");
		
		System.out.println("Ship 5:");
		Ship ship5 = new Ship(0, 0, 100, 100, 0, 10, 1);
		runTestSingle(ship5, debug);
		System.out.println("Ship 6:");
		Ship ship6 = new Ship(100, -100, 50, 50, pi, 10, 1);
		runTestSingle(ship6, debug);
		System.out.println("");
		System.out.println("");
	}
	
	
	public static void runTestSingle(Ship ship, boolean debug) {
		summary(ship, debug);
	}
	
	
	public static void summary(Ship ship, boolean debug) {
		System.out.println("Basic:");
		System.out.format("PositionX = %f%n", ship.getPosition().getPositionX());
		System.out.format("PositionY = %f%n", ship.getPosition().getPositionY());
		System.out.format("VelocityX = %f%n", ship.getVelocityX());
		System.out.format("VelocityY = %f%n", ship.getVelocityY());
		System.out.format("Orientation = %f%n", ship.getOrientation());
		System.out.format("Shape = %f%n", ship.getRadius());
		System.out.format("Speed = %f%n", ship.getSpeed());

		if (debug) {
			System.out.println("Advanced:");
			System.out.format("MinPositionX = %f%n", ship.getPosition().getMinPositionX());
			System.out.format("MinPositionY = %f%n", ship.getPosition().getMinPositionY());
			System.out.format("MaxPositionX = %f%n", ship.getPosition().getMaxPositionX());
			System.out.format("MaxPositionY = %f%n", ship.getPosition().getMaxPositionY());
			System.out.format("ConstantMaxSpeed = %f%n", ship.getMaxSpeed());
		}
	}

}
