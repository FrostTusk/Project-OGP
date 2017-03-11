package asteroids.junk;
import asteroids.model.Ship;

public class ManualTests {
	private static double pi = Math.PI;
	
	public static void main(String[] args) {
		// ship parameters = positionX, positionY, velocityX, velocityY, orientation, shape
		boolean debug = false;
		
		System.out.println("Ship 1:");
		Ship ship1 = new Ship(0, 0, 100, 100, 0, 20);
		runTestSingle(ship1, debug);
		System.out.println("Ship 2:");
		Ship ship2 = new Ship(100, 100, 50, 50, 0, 10);
		runTestSingle(ship2, debug);
		System.out.println("");
		runTestDouble(ship1, ship2, debug);
		System.out.println("");
		
		System.out.println("Ship 3:");
		Ship ship3 = new Ship(0, 0, 100, 100, 0, 1);
		runTestSingle(ship3, debug);
		System.out.println("Ship 4:");
		Ship ship4 = new Ship(100, 100, 50, 50, 0, 1);
		runTestSingle(ship4, debug);
		System.out.println("");
		runTestDouble(ship3, ship4, debug);
		System.out.println("");
		
		System.out.println("Ship 5:");
		Ship ship5 = new Ship(0, 0, 100, 100, 0, 1);
		runTestSingle(ship5, debug);
		System.out.println("Ship 6:");
		Ship ship6 = new Ship(100, -100, 50, 50, pi, 1);
		runTestSingle(ship6, debug);
		System.out.println("");
		runTestDouble(ship5, ship6, debug);
		System.out.println("");
	}
	
	
	public static void runTestSingle(Ship ship, boolean debug) {
		summary(ship, debug);
	}
	
	
	public static void runTestDouble(Ship mainShip, Ship sideShip, boolean debug) {
		double time = mainShip.getTimeToCollision(sideShip);
		System.out.format("Time to Collision: %f%n", time);
		try {
			double[] position = mainShip.getCollisionPosition(sideShip);
			System.out.format("PositionX of Collision: %f%n", position[0]);
			System.out.format("PositionY of Collision: %f%n", position[1]);
		}
		catch (IllegalArgumentException exc) {
			System.out.format("");
		}
			
	}
	
	
	public static void summary(Ship ship, boolean debug) {
		if (debug)
			System.out.println("Basic:");
		
		System.out.format("PositionX = %f%n", ship.getPositionX());
		System.out.format("PositionY = %f%n", ship.getPositionY());
		System.out.format("VelocityX = %f%n", ship.getVelocityX());
		System.out.format("VelocityY = %f%n", ship.getVelocityY());
		System.out.format("Orientation = %f%n", ship.getOrientation());
		System.out.format("Shape = %f%n", ship.getRadius());
		System.out.format("Speed = %f%n", ship.getSpeed());
		
		if (debug) {
			System.out.println("Advanced:");
			System.out.format("MinPositionX = %f%n", ship.getMinPositionX());
			System.out.format("MinPositionY = %f%n", ship.getMinPositionY());
			System.out.format("MaxPositionX = %f%n", ship.getMaxPositionX());
			System.out.format("MaxPositionY = %f%n", ship.getMaxPositionY());
			System.out.format("ConstantMaxSpeed = %f%n", ship.getConstantMaxSpeed());
		}
	}

}
