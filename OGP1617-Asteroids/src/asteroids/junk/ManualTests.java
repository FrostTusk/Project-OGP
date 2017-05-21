package asteroids.junk;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.*;
import asteroids.helper.*;
import asteroids.helper.entity.Entity;
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

	private static PrintStream sysOut = System.out;
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
		sysOut.format("(0.1 > 0) == ");
		sysOut.println(test);
		
		Ship ship1 = new Ship(0, 0, 100, 100, 0, 20, 10);	// Test if maps work as expected
		Ship ship2 = new Ship(10, 10, 100, 100, 0, 20, 10);	// with Strings in java
		entities.put("HELLO", ship1);
		sysOut.println(entities.toString());
		entities.put("HELLO", ship2);
		sysOut.println(entities.toString());
		sysOut.format("This Ship should be the second Ship: ");
		sysOut.println(entities.get("HELLO"));
		if (debug) summary((Ship)entities.get("HELLO"), false);
		
		Entity entity = new Ship(10, 10, 10, 10, 0, 10, 10);
		sysOut.format("Max Speed = %f%n", entity.getMaxSpeed());
		
		Planetoid planetoid = new Planetoid(10, 10, 10, 10, 10, 10);
		sysOut.format("Max Speed = %f%n", planetoid.getMaxSpeed());
		sysOut.format("Min Radius = %f%n", planetoid.getMinRadius());
		
		Entity entity1 = new Ship(10, 10, 10, 10, 0, 10, 10);
		sysOut.println("New Ship entity to string = " + entity1.getClass().toString());
		sysOut.println("Ship class to string = " + Ship.class.toString());
		if (entity1.getClass().toString() == Ship.class.toString())
			sysOut.println("Strings are equal");
		else
			sysOut.println("Strings are NOT equal");
		
		
		// Original Tests that were written at the very start of the project:
		if (showOriginalTests) runOriginalTestHandler();	
	}
	
	
	/**
	 * Handler that prints out the very first tests that were written in this project.
	 * These tests are now included in the Test Suite for Ship.
	 */
	public static void runOriginalTestHandler() {
		sysOut.println("Ship 1:");
		Ship ship1 = new Ship(0, 0, 100, 100, 0, 20, 1);
		runTestSingle(ship1, debug);
		sysOut.println("Ship 2:");
		Ship ship2 = new Ship(100, 100, 50, 50, 0, 10, 1);
		runTestSingle(ship2, debug);
		sysOut.println("");
		sysOut.println("");
		
		sysOut.println("Ship 3:");
		Ship ship3 = new Ship(0, 0, 100, 100, 0, 10, 1);
		runTestSingle(ship3, debug);
		sysOut.println("Ship 4:");
		Ship ship4 = new Ship(100, 100, 50, 50, 0, 10, 1);
		runTestSingle(ship4, debug);
		sysOut.println("");
		sysOut.println("");
		
		sysOut.println("Ship 5:");
		Ship ship5 = new Ship(0, 0, 100, 100, 0, 10, 1);
		runTestSingle(ship5, debug);
		sysOut.println("Ship 6:");
		Ship ship6 = new Ship(100, -100, 50, 50, Math.PI, 10, 1);
		runTestSingle(ship6, debug);
		sysOut.println("");
		sysOut.println("");
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
		sysOut.println("Basic:");
		sysOut.format("PositionX = %f%n", ship.getPosition().getPositionX());
		sysOut.format("PositionY = %f%n", ship.getPosition().getPositionY());
		sysOut.format("VelocityX = %f%n", ship.getVelocityX());
		sysOut.format("VelocityY = %f%n", ship.getVelocityY());
		sysOut.format("Orientation = %f%n", ship.getOrientation());
		sysOut.format("Shape = %f%n", ship.getRadius());
		sysOut.format("Speed = %f%n", ship.getSpeed());

		if (debug) {
			sysOut.println("Advanced:");
			sysOut.format("MinPositionX = %f%n", ship.getPosition().getMinPositionX());
			sysOut.format("MinPositionY = %f%n", ship.getPosition().getMinPositionY());
			sysOut.format("MaxPositionX = %f%n", ship.getPosition().getMaxPositionX());
			sysOut.format("MaxPositionY = %f%n", ship.getPosition().getMaxPositionY());
			sysOut.format("ConstantMaxSpeed = %f%n", ship.getMaxSpeed());
		}
	}

}
