package asteroids.model;

import java.util.List;

import asteroids.model.programs.MyFunction;
import asteroids.model.programs.MyStatement;

public class Program {
	
	public Program(List<MyFunction> functions, MyStatement main) {
		setFunctions(functions);
		setMain(main);
	}
	
	
	private List<MyFunction> functions;
	private MyStatement main;
	
	public List<MyFunction> getFunctions() {
		return this.functions;
	}
	
	public MyStatement getMain() {
		return this.main;
	}
	
	
	private void setFunctions(List<MyFunction> functions) {
		this.functions = functions;
	}
	
	private void setMain(MyStatement main) {
		this.main = main;
	}
	
}
