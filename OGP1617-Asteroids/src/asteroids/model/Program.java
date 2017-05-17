package asteroids.model;

import java.util.List;
import java.util.Map;

import asteroids.model.programs.MyFunction;
import asteroids.model.programs.MyStatement;
import asteroids.part3.programs.SourceLocation;

public class Program {
	
	public Program(List<MyFunction> functions, MyStatement main) {
		main.setProgram(this);
		setFunctions(functions);
		setMain(main);
	}
	
	
	private List<MyFunction> functions;
	private MyStatement main;
	private boolean[] lineTracker;
	private Map<String, Object> localVars;
	private Map<String, Object> globalVars;
	
	
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
	
	
	
	public Object getLocalVar(String name) {
		try {
			return localVars.get(name);
		}
		catch (NullPointerException exc) {
			return null;
		}
	}
	
	public Object getGlobalVar(String name) {
		try {
			return globalVars.get(name);
		}
		catch (NullPointerException exc) {
			return null;
		}
	}
	
	
	public void addLocalVar(String name, Object variable) {
		localVars.put(name, variable);
	}
	
	public void addGlobalVar(String name, Object variable) {
		globalVars.put(name, variable);
	}
	
	
	public void flagLine(SourceLocation location) {
		lineTracker[location.getLine()] = true;
	}
	
}
