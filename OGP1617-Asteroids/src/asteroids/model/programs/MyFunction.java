package asteroids.model.programs;

import java.util.List;
import java.util.Map;

import asteroids.model.Program;
import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;

@SuppressWarnings("rawtypes")
public class MyFunction implements Executable {
	
	public MyFunction(String functionName, MyStatement body, SourceLocation location) {
		setFunctionName(functionName);
		setBody(body);
		setLocation(location);		
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
	
	
	
	private boolean[] lineTracker;
	private Map<String, Object> localVars;
	private Map<String, Object> args;
	
	
	@Override
	public boolean getFlag(SourceLocation location) {
		return this.lineTracker[location.getLine() - 1];
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
		this.lineTracker[location.getLine() - 1] = true;
	}

	@Override
	public void deFlagLine(SourceLocation location) {
		this.lineTracker[location.getLine() - 1] = false;
	}
	
	@Override
	public void addVar(String name, Object value) {
		this.localVars.put(name, value);
	}

	public void addArg(String name, Object value) {
		this.args.put(name, value);
	}
	
	public Object execute(List<MyExpression> actualArgs) {
		return null;
	}
	
}
