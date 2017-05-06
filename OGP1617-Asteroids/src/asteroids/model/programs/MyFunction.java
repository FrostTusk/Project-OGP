package asteroids.model.programs;

import asteroids.part3.programs.SourceLocation;

public class MyFunction {
	public MyFunction(String functionName, MyStatement body, SourceLocation location) {
		this.functionName = functionName;
		this.body = body;
		this.location = location;
		
	}
	
	private MyStatement body;
	private String functionName;
	private SourceLocation location;
	
	public MyStatement getBody() {
		return this.body;
	}

	public String getFunctionName() {
		return this.functionName;
	}
	
	public SourceLocation getLocation() {
		return this.location;
	}
	
}
