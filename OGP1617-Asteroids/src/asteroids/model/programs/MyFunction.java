package asteroids.model.programs;

import asteroids.part3.programs.SourceLocation;

public class MyFunction {
	public MyFunction(String functionName, MyStatement body, SourceLocation sourceLocation) {
		this.functionName = functionName;
		this.body = body;
		this.sourceLocation = sourceLocation;
		
	}
	
	private MyStatement body;
	private String functionName;
	private SourceLocation sourceLocation;
	
	public MyStatement getBody() {
		return this.body;
	}

	public String getFunctionName() {
		return this.functionName;
	}
	
	public SourceLocation getSourceLocation() {
		return this.sourceLocation;
	}
	
}
