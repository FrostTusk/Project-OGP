package asteroids.model;

import java.util.*;
import asteroids.model.programs.*;
import asteroids.part3.programs.SourceLocation;

public class Program implements Executable {
	
	public Program(List<MyFunction> functions, MyStatement main) {
		setFunctions(functions);
		setMain(main);
		main.setExecuter(this);
		
		setTime(0);
		this.printTracker = new ArrayList<Object>();
//		this.lineTracker = new boolean[main.getSize()];
		this.flagMap = new HashMap<String, Boolean>();
		
		this.globalVars = new HashMap<String, Object>();
		this.localVars = new HashMap<String, Object>();
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
	

	public boolean canHaveAsName(String name) {
		if (getAllFunctions() == null)
			return true;
		for (MyFunction ownedFunction: getAllFunctions())
			if (ownedFunction.getFunctionName().equals(name))
				return false;
		return true;
	}
	
	
	private void setFunctions(List<MyFunction> functions) throws IllegalArgumentException {
		if (functions == null)
			return;
		for (MyFunction function: functions)
			if (!canHaveAsName(function.getFunctionName()))
				throw new IllegalArgumentException();
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
	
	
	@Override
	public Program getProgram() {
		return this;
	}

	public void setTime(double time) {
		this.time = time;
	}
	
	public void setOwner(Ship owner) {
		this.owner = owner;
	}
	
	
	
//	private boolean[] lineTracker;
	private Map<String, Boolean> flagMap;
	private List<Object> printTracker;
	private Map<String, Object> localVars;
	private Map<String, Object> globalVars;

	
	public boolean getFlag(SourceLocation location) {
		try {
			return flagMap.get(getPositionString(location));
		}
		catch (NullPointerException exc) {
			return false;
		}
//		return lineTracker[location.getLine() - 1];
	}
	
	public List<Object> getPrintTracker() {
//		List<Object> result = new ArrayList<Object>();
//		result.add(null);
//		return (printTracker.isEmpty()) ? result:printTracker;
//		return this.printTracker;
//		try {
		if (!terminated)
			return null;
		return (this.printTracker.isEmpty()) ? null: this.printTracker;
//		}
//		catch (NullPointerException exc) {
//			throw new ArithmeticException();
//		}
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
//		lineTracker[location.getLine() - 1] = true;
		flagMap.put(getPositionString(location), true);
	}

	@Override
	public void deFlagLine(SourceLocation location) {
//		lineTracker[location.getLine() - 1] = false;
		flagMap.put(getPositionString(location), false);
	}
	
	public void addPrintValue(Object value) {
		this.printTracker.add(value);
	}
	
	@Override
	public void addVar(String name, Object value) {
		addGlobalVar(name, value);		
	}

	public void addLocalVar(String name, Object variable) {
		localVars.put(name, variable);
	}
	
	public void addGlobalVar(String name, Object variable) {
		globalVars.put(name, variable);
	}
	
	
	
	private boolean terminated;

	
	public void execute(double time) {
		setTime(getTime() + time);
		main.execute();
		terminated = true;
	}

	
	public String getPositionString(SourceLocation location) {
		return Integer.toString(location.getLine()) + "," + Integer.toString(location.getColumn());
	}

	@Override
	public void setReturn(MyExpression<?> value) {
		throw new IllegalArgumentException();
	}
	
}
