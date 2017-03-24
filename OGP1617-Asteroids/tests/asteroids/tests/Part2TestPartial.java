package asteroids.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import asteroids.model.Bullet;
import asteroids.model.Ship;
import asteroids.model.World;
import asteroids.facade.Facade;
import asteroids.part2.facade.IFacade;
import asteroids.util.ModelException;

public class Part2TestPartial {

	private static final double EPSILON = 0.0001;

	IFacade facade;

	@Before
	public void setUp() {
		facade = new Facade();
	}

	@Test
	public void testCreateWorld() throws ModelException {
		World world = facade.createWorld(1000, 800);
		assertEquals(1000, facade.getWorldSize(world)[0], EPSILON);
		assertEquals(800, facade.getWorldSize(world)[1], EPSILON);
		assertTrue(facade.getWorldShips(world).isEmpty());
		assertTrue(facade.getWorldBullets(world).isEmpty());
	}

	@Test
	public void testLoadBulletOnShipOverlappingBullets() throws ModelException {
		Ship ship = facade.createShip(100, 120, 10, 5, 500, 0, 1.0E20);
		Bullet bullet1 = facade.createBullet(100, 120, 10, 5, 50);
		Bullet bullet2 = facade.createBullet(130, 110, 10, 5, 30);
		facade.loadBulletOnShip(ship, bullet1);
		facade.loadBulletOnShip(ship, bullet2);
		assertEquals(2, facade.getNbBulletsOnShip(ship));
	}

	@Test
	public void testEvolveShipWithActiveThruster() throws ModelException {
		World world = facade.createWorld(5000, 5000);
		Ship ship = facade.createShip(100, 120, 10, 0, 50, Math.PI, 1.1E18);
		facade.addShipToWorld(world, ship);
		facade.setThrusterActive(ship, true);
		assertEquals(1000.0, facade.getShipAcceleration(ship), EPSILON);
		assertTrue(facade.isShipThrusterActive(ship));
		facade.evolve(world, 1, null);
		assertEquals(-990, facade.getShipVelocity(ship)[0], EPSILON);
		assertEquals(0, facade.getShipVelocity(ship)[1], EPSILON);
	}

}
