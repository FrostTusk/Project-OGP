package asteroids.part3;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;

import asteroids.part3.internal.AsteroidsFrame3;
import asteroids.util.internal.ResourceUtils;
import asteroids.part3.facade.IFacade;

public class Part3 {
	public static void main(String[] args) throws FileNotFoundException {
		boolean tryFullscreen = false;
		boolean enableSound = true;
		URL aiProgramUrl = ResourceUtils.toURL("asteroids/resources/programs/program.txt");
		for (int i = 0; i < args.length; i++) {
			String arg = args[i];
			if (arg.equals("-fullscreen")) {
				tryFullscreen = true;
			} else if (arg.equals("-nosound")) {
				enableSound = false;
			} else if (arg.equals("-ai")) {
				if (i + 1 < args.length) {
					String aiProgramPath = args[++i];
					File file = new File(aiProgramPath);
					if (!file.exists()) {
						System.out.println("file " + aiProgramPath + " not found");
						return;
					} else {
						try {
							aiProgramUrl = file.toURI().toURL();
						} catch (MalformedURLException e) {
							System.out.println("malformed url");
							return;
						}
					}
					i++;
				} else {
					System.out.println("no path specified");
					return;
				}
			} else {
				System.out.println("unknown option: " + arg);
				return;
			}
		}
		IFacade facade = new asteroids.facade.Facade();
		AsteroidsFrame3.run(facade, tryFullscreen, enableSound, aiProgramUrl);
	}
}
