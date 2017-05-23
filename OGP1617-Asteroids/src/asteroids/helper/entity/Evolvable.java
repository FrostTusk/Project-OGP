package asteroids.helper.entity;

import asteroids.helper.ExitOutException;

public interface Evolvable {
	
	public void morph(double time) throws ExitOutException, IllegalArgumentException, NullPointerException ;
}
