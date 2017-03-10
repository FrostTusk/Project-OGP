package asteroids.part1;

import asteroids.part1.facade.IFacade;
import asteroids.part1.internal.AsteroidsFrame;

public class Part1 {
	public static void main(String[] args) {
		boolean tryFullscreen = false;
		for (String arg : args) {
			if (arg.equals("-window")) {
				tryFullscreen = false;
			} else {
				System.out.println("unknown option: " + arg);
				return;
			}
		}
		
		IFacade facade = new asteroids.facade.Facade();		
		AsteroidsFrame.run(facade, tryFullscreen);	    
	  }
}
