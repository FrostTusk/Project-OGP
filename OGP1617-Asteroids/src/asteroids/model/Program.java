package asteroids.model;

import java.util.List;
import java.util.Map;

import asteroids.model.programs.MyFunction;
import asteroids.model.programs.MyStatement;
import asteroids.part3.programs.SourceLocation;

public class Program {
	
	public Program(List<MyFunction> functions, MyStatement main) {
		setFunctions(functions);
		setMain(main);
		main.setProgram(this);
		
		setTime(0);
		lineTracker = new boolean[main.getSize()];
	}
	
	
	private List<MyFunction> functions;
	private MyStatement main;

	
	public List<MyFunction> getAllFunctions() {
		return this.functions;
	}
	
	public MyFunction getFunction(String name) {
		for (MyFunction function: getAllFunctions())
			if (function.getFunctionName().equals(name))
				return function;
		throw new IllegalArgumentException();
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
	
	
	
	private double time;
	private Ship owner;
	
	public double getTime() {
		return time;
	}
	
	public Ship getOwner() {
		return this.owner;
	}
	
	
	public void setTime(double time) {
		this.time = time;
	}
	
	public void setOwner(Ship owner) {
		this.owner = owner;
	}
	
	
	
	private boolean[] lineTracker;
	private List<Object> printTracker;
	private Map<String, Object> localVars;
	private Map<String, Object> globalVars;

	
	public boolean getFlag(SourceLocation location) {
		return lineTracker[location.getLine()];
	}
	
	public List<Object> getPrintTracker() {
		return this.printTracker;
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
	

	public void flagLine(SourceLocation location) {
		lineTracker[location.getLine()] = true;
	}
	
	public void addPrintValue(Object value) {
		this.printTracker.add(value);
	}
	
	public void addLocalVar(String name, Object variable) {
		localVars.put(name, variable);
	}
	
	public void addGlobalVar(String name, Object variable) {
		globalVars.put(name, variable);
	}
	


	
	
	public void execute(double time) {
		setTime(getTime() + time);
	}
	
}
