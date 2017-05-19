package asteroids.model.programs;

import java.util.List;

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
	
	
	
	public Object execute(List<MyExpression> list) {
		return null;
	}

	@Override
	public double getTime() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getLocalVar(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getGlobalVar(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addPrintValue(Object value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void execute(double time) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void flagLine(SourceLocation location) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean getFlag(SourceLocation location) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Ship getOwner() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setTime(double time) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addVar(String name, Object value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Program getProgram() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
