package asteroids.helper;

public class Thruster {

	public Thruster() {}
	
	private boolean thrustEnabled = false;
	
	public void thrustOn() {
		thrustEnabled = true;
	}
	
	public void thrustOff() {
		thrustEnabled = false;
	}
	
	public boolean getThrustStatus() {
		return thrustEnabled;
	}
}
