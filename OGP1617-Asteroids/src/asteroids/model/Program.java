package asteroids.model;

import java.util.*;
import asteroids.model.programs.*;
import asteroids.part3.programs.SourceLocation;

public class Program implements Executable {
	
	public Program(List<MyFunction> functions, MyStatement main) {
		setFunctions(functions);
		setMain(main);
		main.setExecuter(this);
		
		terminated = false;
		setTime(0);
		this.printTracker = new ArrayList<Object>();
		this.flagTracker = new HashMap<String, Boolean>();
		this.globalVars = new HashMap<String, Object>();
		this.localVars = new HashMap<String, Object>();
	}
	
	
	private boolean terminated;

	
	public boolean isTerminated() {
		return this.terminated;
	}
	
	public void terminate() {
		this.terminated = true;
	}
	
	
	
	private List<MyFunction> functions;
	private MyStatement main;

	
	public List<MyFunction> getAllFunctions() {
		return this.functions;
	}
	
	public MyFunction getFunction(String name) throws IllegalArgumentException {
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
	
	
	
	private List<Object> printTracker;
	private Map<String, Boolean> flagTracker;
	private Map<String, Object> localVars;
	private Map<String, Object> globalVars;

	
	public List<Object> getPrintTracker() {
		if (!isTerminated())
			return null;
		return (this.printTracker.isEmpty()) ? null: this.printTracker;
	}
	
	public boolean getFlag(SourceLocation location) {
		try {
			return flagTracker.get(getPositionString(location));
		}
		catch (NullPointerException exc) {
			return false;
		}
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
	

	public void addPrintValue(Object value) {
		this.printTracker.add(value);
	}
	
	public void flagLine(SourceLocation location) {
		flagTracker.put(getPositionString(location), true);
	}

	@Override
	public void deFlagLine(SourceLocation location) {
		flagTracker.put(getPositionString(location), false);
	}
	
	@Override
	public void addVar(String name, Object value) {
		addGlobalVar(name, value);		
	}

	public void addLocalVar(String name, Object variable) {
		throw new IllegalArgumentException(); // Only Functions have local variables.
//		localVars.put(name, variable);
	}
	
	public void addGlobalVar(String name, Object variable) {
		globalVars.put(name, variable);
	}
	
	
	
	@Override
	public void setReturn(MyExpression<?> value) {
		throw new IllegalArgumentException();
	}
	
	
	public void execute(double time) {
		setTime(getTime() + time);
		main.execute();
		terminate();
	}

	
	
	public String getPositionString(SourceLocation location) {
		return Integer.toString(location.getLine()) + "," + Integer.toString(location.getColumn());
	}
	
}
