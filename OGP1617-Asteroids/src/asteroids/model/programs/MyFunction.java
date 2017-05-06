package asteroids.model.programs;

import asteroids.part3.programs.SourceLocation;

public class MyFunction {
	
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
	
}
