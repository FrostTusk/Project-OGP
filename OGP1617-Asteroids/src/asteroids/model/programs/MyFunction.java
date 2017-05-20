package asteroids.model.programs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import asteroids.junk.TestException;
import asteroids.model.Program;
import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;

@SuppressWarnings("rawtypes")
public class MyFunction implements Executable {
		
	public MyFunction(String functionName, MyStatement body, SourceLocation location) {
		setFunctionName(functionName);
		setBody(body);
		setLocation(location);	
		
		this.flagMap = new HashMap<String, Boolean>();
		this.localVars = new HashMap<String, Object>();
		this.args = new ArrayList<Object>();
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
		setOwner(program.getOwner());
		setTime(program.getTime());
	}
	
	
	
//	private boolean[] lineTracker;
	private Map<String, Boolean> flagMap;
	private Map<String, Object> localVars;
	private List<Object> args;
	
	
	@Override
	public boolean getFlag(SourceLocation location) {
//		return this.lineTracker[location.getLine() - 1];
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

	public Object getArgument(String name) {
		return null;
	}
	
	
	@Override
	public void addPrintValue(Object value) {
		getProgram().addPrintValue(value);
	}

	@Override
	public void flagLine(SourceLocation location) {
//		this.lineTracker[location.getLine() - 1] = true;
		flagMap.put(getPositionString(location), true);
	}

	@Override
	public void deFlagLine(SourceLocation location) {
//		this.lineTracker[location.getLine() - 1] = false;
		flagMap.put(getPositionString(location), false);
	}
	
	@Override
	public void addVar(String name, Object value) {
		this.localVars.put(name, value);
	}

	public void addArg(Object value) {
		this.args.add(value);
	}
	
	
	
	private MyExpression returnValue;
	private boolean returnSet;
	
	
	@Override
	public void setReturn(MyExpression<?> value) {
		this.returnValue = value;
		this.returnSet = true;
	}
	
	
	public Object execute(List<MyExpression> actualArgs) {
		this.returnSet = false;
		body.requestDeFlag();
		for (MyExpression arg: actualArgs)
			addArg(arg.evaluate());
		try {
			body.execute();
		}
		catch(TestException exc) {
			if (returnSet)
				return this.returnValue;
		}
		throw new IllegalArgumentException();
	}
	


	public String getPositionString(SourceLocation location) {
		return Integer.toString(location.getLine()) + "," + Integer.toString(location.getColumn());
	}

}
