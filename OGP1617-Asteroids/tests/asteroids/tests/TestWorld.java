package asteroids.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import asteroids.model.World;
import asteroids.util.ModelException;

public class TestWorld {
	
	private static final double EPSILON = 0.0001;

	

			/*
			 * |--------------------------------------------|
			 * | 1. The next test test the Initialization.	|
			 * |--------------------------------------------| 
			 */	
	
	
	
	@Test
	public void testCreateWorld() throws ModelException {
		World world = new World(10, 10);
		assertNotNull(world);
	}
	
	
	// TODO when entitites are finished, check this test.
	@Test
	public void testCreateWorldTerminateWorld() throws ModelException {
		World world = new World(-15, -10);
		assertNotNull(world);
		world.terminate();
	}
	
			/*
			 * |--------------------------------------------|
			 * | 2. The next test test the Size. (Basic)	|
			 * |--------------------------------------------| 
			 */	

	
	
	@Test
	public void testCreateWorldSizeGeneric() throws ModelException {
		World world = new World(15, 10);
		assertNotNull(world);
		assertEquals(15, world.getWidth(), EPSILON);
		assertEquals(10, world.getHeight(), EPSILON);
	}
	
	
	@Test
	public void testCreateWorldSizeWidthZero() throws ModelException {
		World world = new World(0, 0);
		assertNotNull(world);
		assertEquals(0, world.getWidth(), EPSILON);
		assertEquals(0, world.getHeight(), EPSILON);
	}
	
	@Test
	public void testCreateWorldSizeWidthEdgeOverflow() throws ModelException {
		World world = new World(Double.MAX_VALUE, 10);
		assertNotNull(world);
		assertEquals(Double.MAX_VALUE, world.getWidth(), EPSILON);
		assertEquals(10, world.getHeight(), EPSILON);
	}
	
	@Test
	public void testCreateWorldSizeHeightEdgeOverflow() throws ModelException {
		World world = new World(15, Double.MAX_VALUE);
		assertNotNull(world);
		assertEquals(15, world.getWidth(), EPSILON);
		assertEquals(Double.MAX_VALUE, world.getHeight(), EPSILON);
	}
	
	
	@Test
	public void testCreateWorldSizeWidthHeightEdgeOverflow() throws ModelException {
		World world = new World(Double.MAX_VALUE, Double.MAX_VALUE);
		assertNotNull(world);
		assertEquals(Double.MAX_VALUE, world.getWidth(), EPSILON);
		assertEquals(Double.MAX_VALUE, world.getHeight(), EPSILON);
	}
	
	
	@Test
	public void testCreateWorldSizeWidthOverflow() throws ModelException {
		World world = new World(Double.MAX_VALUE + 50, 10);
		assertNotNull(world);
		assertEquals(Double.MAX_VALUE, world.getWidth(), EPSILON);
		assertEquals(10, world.getHeight(), EPSILON);
	}
	
	@Test
	public void testCreateWorldSizeHeightOverflow() throws ModelException {
		World world = new World(15, Double.MAX_VALUE + 50);
		assertNotNull(world);
		assertEquals(15, world.getWidth(), EPSILON);
		assertEquals(Double.MAX_VALUE, world.getHeight(), EPSILON);
	}
	
	@Test
	public void testCreateWorldSizeWidthHeightOverflow() throws ModelException {
		World world = new World(Double.MAX_VALUE, Double.MAX_VALUE);
		assertNotNull(world);
		assertEquals(Double.MAX_VALUE, world.getWidth(), EPSILON);
		assertEquals(Double.MAX_VALUE, world.getHeight(), EPSILON);
	}
	
	@Test
	public void testCreateWorldSizeWidthNeg() throws ModelException {
		World world = new World(-15, 10);
		assertNotNull(world);
		assertEquals(0, world.getWidth(), EPSILON);
		assertEquals(10, world.getHeight(), EPSILON);
	}
	
	@Test
	public void testCreateWorldSizeHeightNeg() throws ModelException {
		World world = new World(15, -10);
		assertNotNull(world);
		assertEquals(15, world.getWidth(), EPSILON);
		assertEquals(0, world.getHeight(), EPSILON);
	}
	
	@Test
	public void testCreateWorldSizeWidthHeightNeg() throws ModelException {
		World world = new World(-15, -10);
		assertNotNull(world);
		assertEquals(0, world.getWidth(), EPSILON);
		assertEquals(0, world.getHeight(), EPSILON);
	}

	
	
			/*
			 * |--------------------------------------------|
			 * | 3. The next test test the Size. (Advanced)	|
			 * |--------------------------------------------| 
			 */	

	
/*	The next test crashes the Test Suite, this is because it changes
 *	the upper bound (which is a static variable). All the other tests
 *	create worlds with sizes above 5, which will mean they all fail.
 */
//	@Test
//	public void testCreateWorldNewUpperBound() throws ModelException {
//		World world1 = new World(15, 10);
//		assertNotNull(world1);
//		world1.setUpperBound(5);
//		assertNotNull(world1);
//		World world2 = new World(15, 10);
//		assertNotNull(world2);
//		assertEquals(5, world2.getWidth(), EPSILON);
//		assertEquals(5, world2.getHeight(), EPSILON);
//	}
	

	
}
