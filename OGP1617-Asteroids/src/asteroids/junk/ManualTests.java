package asteroids.junk;

import java.util.*;
import asteroids.helper.*;
import asteroids.model.*;

/**
 * Do not use this test class as a test suite! Use JUnit 4 test suites 
 * for unit tests instead! Use this class only to test or try out
 * things in java syntax/semantics.
 * 
 * @author Mathijs Hubrechtsen
 */
@SuppressWarnings("unused")
public class ManualTests {

	/**
	 * Variable whether debug mode needs to be used or not.
	 */
	private static boolean debug = false;
	/**
	 * Variable whether debug mode needs to original tests need to be shown or not.
	 */
	private static boolean showOriginalTests = false;
	
	/**
	 * Variable registering a test map.
	 */
	private static Map<String, Entity> entities = new HashMap<String, Entity>();
	
	
	/**
	 * Main Method
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// ship parameters = positionX, positionY, velocityX, velocityY, orientation, shape

		boolean test;	// Test if 0 is properly compared with a double in java.
		if (0.1 > 0) test = true;
		else test = false;
		System.out.format("(0.1 > 0) == ");
		System.out.println(test);
		
		Ship ship1 = new Ship(0, 0, 100, 100, 0, 20, 10);	// Test if maps work as expected
		Ship ship2 = new Ship(10, 10, 100, 100, 0, 20, 10);	// with Strings in java
		entities.put("HELLO", ship1);
		System.out.println(entities.toString());
		entities.put("HELLO", ship2);
		System.out.println(entities.toString());
		System.out.format("This Ship should be the second Ship: ");
		System.out.println(entities.get("HELLO"));
		if (debug) summary((Ship)entities.get("HELLO"), false);
		
		// Original Tests that were written at the very start of the project:
		if (showOriginalTests) runOriginalTestHandler();	
	}
	
	
	/**
	 * Handler that prints out the very first tests that were written in this project.
	 * These tests are now included in the Test Suite for Ship.
	 */
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
		Ship ship6 = new Ship(100, -100, 50, 50, Math.PI, 10, 1);
		runTestSingle(ship6, debug);
		System.out.println("");
		System.out.println("");
	}
	
	/**
	 * Runs a single test.
	 * Helper Method for runOriginalTestHandler().
	 * 
	 * @param	ship
	 * 			The ship to be used.
	 * @param	debug
	 * 			Is debug mode active or not.
	 */
	public static void runTestSingle(Ship ship, boolean debug) {
		summary(ship, debug);
	}
	
		
	/**
	 * Prints out a few properties of a given ship.
	 * 
	 * @param	ship
	 * 			The ship to be used.
	 * @param	debug
	 * 			Is debug mode active or not.
	 */
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
