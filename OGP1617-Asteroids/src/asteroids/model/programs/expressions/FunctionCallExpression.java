package asteroids.model.programs.expressions;

import java.util.List;

import asteroids.model.programs.MyExpression;
import asteroids.part3.programs.SourceLocation;

public class FunctionCallExpression implements MyExpression {
	
	public FunctionCallExpression(String functionName, List<MyExpression> actualArgs,
			SourceLocation location) {
		setLocation(location);
		setFunctionName(functionName);
		setActualArgs(actualArgs);
	}
	
	
	private String functionName;
	private List<MyExpression> actualArgs;
	private SourceLocation location;
	
	
	public String getFunctionName() {
		return this.functionName;
	}
	
	public List<MyExpression> getActualArgs() {
		return this.actualArgs;
	}
	
	@Override
	public SourceLocation getLocation() {
		return this.location;
	}
	
	
	private void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
	
	private void setActualArgs(List<MyExpression> actualArgs) {
		this.actualArgs = actualArgs;
	}
	
	private void setLocation(SourceLocation location) {
		this.location = location;
	}
	
	
	@Override
	public MyExpression evaluate() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
