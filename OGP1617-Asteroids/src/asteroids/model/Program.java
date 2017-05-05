package asteroids.model;

import java.util.List;
import java.util.stream.Stream;

import asteroids.model.programs.MyFunction;
import asteroids.model.programs.MyStatement;

public class Program {
	
	public Program(List<MyFunction> functions, MyStatement main) {
//		Stream<MyFunction> functionsStream = functions.stream().
//				filter(x -> canHaveAsFunction(x));
	}
	
	public void addFunction(MyFunction function) {
		
	}
	
	public boolean canHaveAsFunction(MyFunction function) {
		return false;
	}
	
	public Stream<MyFunction> getFunctions() {
		return null;
	}
}
