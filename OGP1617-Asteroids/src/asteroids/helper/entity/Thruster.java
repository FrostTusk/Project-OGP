package asteroids.helper.entity;

import be.kuleuven.cs.som.annotate.*;

/** 
 * A class of thrusters.
 */
public class Thruster {

	/**
	 * Initializes a new thruster.
	 */
	public Thruster() {}
	
	
	/**
	 * Variable registering whether or not this thruster is enabled.
	 */
	private boolean thrustEnabled = false;
	
	
	/**
	 * Return the status of this thruster.
	 * true => On, false => Off
	 */
	@Basic @Raw
	public boolean getThrustStatus() {
		return thrustEnabled;
	}
	
	
	/**
	 * Toggle this thruster on.
	 * @see implementation
	 */
	@Raw
	public void thrustOn() {
		thrustEnabled = true;
	}
	
	/**
	 * Toggle this thruster off.
	 * @see implementation
	 */
	@Raw
	public void thrustOff() {
		thrustEnabled = false;
	}
	
}
