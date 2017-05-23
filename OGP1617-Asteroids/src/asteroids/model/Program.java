package asteroids.model;

import java.util.*;

import asteroids.helper.ExitOutException;
import asteroids.helper.Helper;
import asteroids.helper.entity.Terminateable;
import asteroids.model.programs.*;
import asteroids.part3.programs.SourceLocation;

public class Program implements Executable, Terminateable {
	
	private Helper helper = new Helper();
	
	public Program(List<MyFunction> functions, MyStatement main) {
		setFunctions(functions);
		setMain(main);
		main.setExecuter(this);
		
		terminated = false;
		setTime(0);
		this.printTracker = new ArrayList<Object>();
		this.flagTracker = new HashMap<String, Boolean>();
		this.globalVars = new HashMap<String, Object>();
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
	private Map<String, Object> globalVars;

	
	public List<Object> getPrintTracker() {
		if (!isTerminated())
			return null;
		return helper.deepCopyList(this.printTracker);
	}
	
	public boolean getFlag(SourceLocation location) throws NullPointerException {
		if (!flagTracker.containsKey(getPositionString(location)))
			return false;
		return flagTracker.get(getPositionString(location));
	}
	
	public Object getLocalVar(String name) throws NullPointerException {
		throw new NullPointerException();
	}
	
	public Object getGlobalVar(String name) throws NullPointerException {
		if (!globalVars.containsKey(name))
			throw new NullPointerException();
		return globalVars.get(name);
	}
	

	public void addPrintValue(Object value) {
		this.printTracker.add(value);
	}
	
	public void flagLine(SourceLocation location) throws NullPointerException {
		flagTracker.put(getPositionString(location), true);
	}

	@Override
	public void deFlagLine(SourceLocation location) throws NullPointerException {
		flagTracker.put(getPositionString(location), false);
	}
	
	@Override
	public void addVar(String name, Object value) {
		addGlobalVar(name, value);		
	}

	public void addLocalVar(String name, Object variable) throws IllegalArgumentException {
		throw new IllegalArgumentException(); // Only functions have local variables.
	}
	
	public void addGlobalVar(String name, Object variable) {
		globalVars.put(name, variable);
	}
	
	
	
	@Override
	public void setReturn(Object value) throws IllegalArgumentException {
		throw new IllegalArgumentException();
	}
	

	

	@Override
	public List<Object> execute(List<Object> actualArgs) 
			throws ExitOutException, IllegalStateException, IllegalArgumentException, NullPointerException {
		double time = ((Double) actualArgs.get(0));
		setTime(getTime() + time);
		main.execute();
		terminate();
		return getPrintTracker();
	}
	
	
	public String getPositionString(SourceLocation location) throws NullPointerException {
		return Integer.toString(location.getLine()) + "," + Integer.toString(location.getColumn());
	}
	
}
