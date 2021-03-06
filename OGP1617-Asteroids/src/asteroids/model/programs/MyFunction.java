package asteroids.model.programs;

import java.util.*;
import asteroids.helper.ExitOutException;
import asteroids.model.*;
import asteroids.part3.programs.SourceLocation;

public class MyFunction implements Executable {
		
	public MyFunction(String functionName, MyStatement body, SourceLocation location) {
		setFunctionName(functionName);
		setBody(body);
		setLocation(location);	
				
		this.flagTracker = new HashMap<String, Boolean>();
		this.localVars = new HashMap<String, Object>();
	}
	
	
	private MyStatement body;
	private String functionName;
	private SourceLocation location;
	
	
	public String getFunctionName() {
		return this.functionName;
	}
	
	public MyStatement getBody() {
		return this.body;
	}
	
	public SourceLocation getLocation() {
		return this.location;
	}
	
	
	private void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
	
	private void setBody(MyStatement body) {
		this.body = body;
	}
	
	private void setLocation(SourceLocation location) {
		this.location = location;
	}
	
	
	
	private double time;
	private Ship owner;
	private Program program;
	
	
	@Override
	public double getTime() {
		return time;
	}

	@Override
	public Ship getOwner() {
		return this.owner;
	}

	@Override
	public Program getProgram() {
		return this.program;
	}
	
	
	@Override
	public void setTime(double time) throws NullPointerException {
		getProgram().setTime(time);
	}
	
	private void setOwner(Ship owner) {
		this.owner = owner;
	}
	
	public void setProgram(Program program) throws NullPointerException {
		this.program = program;
		setOwner(getProgram().getOwner());
		setTime(getProgram().getTime());
	}
	
	
	
	private Map<String, Object> args;
	private Map<String, Boolean> flagTracker;
	private Map<String, Object> localVars;
	
	
	public Object getArgument(String name) {
		return args.get(name);
	}
	
	@Override
	public boolean getFlag(SourceLocation location) {
		if (!flagTracker.containsKey(getPositionString(location)))
			return false;
		try {
			return flagTracker.get(getPositionString(location));
		}
		catch (NullPointerException exc) {
			return false;
		}
	}

	@Override
	public Object getLocalVar(String name) throws NullPointerException {
		if (!localVars.containsKey(name))
			throw new NullPointerException();
		return localVars.get(name);
	}

	@Override
	public Object getGlobalVar(String name) throws NullPointerException {
		return getProgram().getGlobalVar(name);
	}


	public boolean canHaveAsName(String name) throws NullPointerException {
		if (getProgram().getAllFunctions() == null)
			return true;
		for (MyFunction ownedFunction: getProgram().getAllFunctions())
			if (ownedFunction.getFunctionName().equals(name))
				return false;
		return true;
	}
	
	
	public void registerArguments(List<Object> argFeed) throws NullPointerException {
		this.args = new HashMap<String, Object>();
		if (argFeed == null) return;
			
		for (int i = 1; i < argFeed.size() + 1; i++)
			args.put("$" + Integer.toString(i), argFeed.get(i - 1));
	}
	
	@Override
	public void addPrintValue(Object value) throws NullPointerException {
		getProgram().addPrintValue(value);
	}

	@Override
	public void flagLine(SourceLocation location) throws NullPointerException {
		flagTracker.put(getPositionString(location), true);
	}

	@Override
	public void deFlagLine(SourceLocation location) throws NullPointerException {
		flagTracker.put(getPositionString(location), false);
	}
	
	@Override
	public void addVar(String name, Object value) {
		this.localVars.put(name, value);
	}


	
	private Object returnValue;
	private boolean returnSet;
	
	
	public boolean returnHasBeenSet() {
		return this.returnSet;
	}
	
	
	@Override
	public void setReturn(Object value) {
		this.returnValue = value;
		this.returnSet = true;
	}
	
	
	public Object execute(List<Object> actualArgs) 
			throws ExitOutException, IllegalArgumentException, IllegalStateException, NullPointerException {
		Executable lastExecuter = getBody().getExecuter();
		this.returnSet = false;
		getBody().setExecuter(this);
		getBody().requestDeFlag();
		registerArguments(actualArgs);
		
		try {
			getBody().execute();
		}
		catch (ExitOutException exc) {
			getBody().setExecuter(lastExecuter);
			if (returnHasBeenSet())
				return this.returnValue;
			else
				throw new ExitOutException();
		}
		throw new IllegalArgumentException();
	}
	

	
	public String getPositionString(SourceLocation location) throws NullPointerException {
		return Integer.toString(location.getLine()) + "," + Integer.toString(location.getColumn());
	}

}
