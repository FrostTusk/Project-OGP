package asteroids.model.programs;

import java.util.*;
import asteroids.helper.ExitOutException;
import asteroids.model.*;
import asteroids.part3.programs.SourceLocation;

@SuppressWarnings("rawtypes")
public class MyFunction implements Executable {
		
	public MyFunction(String functionName, MyStatement body, SourceLocation location) {
		setFunctionName(functionName);
		setBody(body);
		setLocation(location);	
				
		this.args = new ArrayList<Object>();
		this.flagMap = new HashMap<String, Boolean>();
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
	public void setTime(double time) {
		this.time = time;
	}
	
	private void setOwner(Ship owner) {
		this.owner = owner;
	}
	
	public void setProgram(Program program) {
		this.program = program;
		setOwner(getProgram().getOwner());
		setTime(getProgram().getTime());
	}
	
	
	
	private List<Object> args;
	private Map<String, Boolean> flagMap;
	private Map<String, Object> localVars;
	
	
	public Object getArgument(String name) {
		return null;
	}
	
	@Override
	public boolean getFlag(SourceLocation location) {
		try {
			return flagMap.get(getPositionString(location));
		}
		catch (NullPointerException exc) {
			return false;
		}
	}

	@Override
	public Object getLocalVar(String name) {
		try {
			return localVars.get(name);
		}
		catch (NullPointerException exc) {
			return null;
		}
	}

	@Override
	public Object getGlobalVar(String name) {
		return getProgram().getGlobalVar(name);
	}


	public boolean canHaveAsName(String name) {
		if (getProgram().getAllFunctions() == null)
			return true;
		for (MyFunction ownedFunction: getProgram().getAllFunctions())
			if (ownedFunction.getFunctionName().equals(name))
				return false;
		return true;
	}
	
	
	public void addArg(Object value) {
		this.args.add(value);
	}
	
	@Override
	public void addPrintValue(Object value) {
		getProgram().addPrintValue(value);
	}

	@Override
	public void flagLine(SourceLocation location) {
		flagMap.put(getPositionString(location), true);
	}

	@Override
	public void deFlagLine(SourceLocation location) {
		flagMap.put(getPositionString(location), false);
	}
	
	@Override
	public void addVar(String name, Object value) {
		this.localVars.put(name, value);
	}


	
	private MyExpression returnValue;
	private boolean returnSet;
	
	
	public boolean returnHasBeenSet() {
		return this.returnSet;
	}
	
	
	@Override
	public void setReturn(MyExpression<?> value) {
		this.returnValue = value;
		this.returnSet = true;
	}
	
	
	public MyExpression execute(List<MyExpression> actualArgs) {
		this.returnSet = false;
		getBody().setExecuter(this);
		getBody().requestDeFlag();
		
		for (MyExpression arg: actualArgs)
			addArg(arg.evaluate());
		
		try {
			getBody().execute();
		}
		catch(ExitOutException exc) {
			if (returnHasBeenSet())
				return this.returnValue;
			else
				throw new ExitOutException();
		}
		throw new IllegalArgumentException();
	}
	

	
	public String getPositionString(SourceLocation location) {
		return Integer.toString(location.getLine()) + "," + Integer.toString(location.getColumn());
	}

}
