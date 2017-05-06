package asteroids.model.programs.expressions;

import java.util.List;

import asteroids.helper.program.ExpressionType;
import asteroids.model.programs.MyExpression;
import asteroids.part3.programs.SourceLocation;

public class FunctionCallExpression extends MyExpression {
	
	public FunctionCallExpression(String functionName, List<MyExpression> actualArgs,
			SourceLocation sourceLocation) {
		setSourceLocation(sourceLocation);
		setType(ExpressionType.FUNCTIONCALL);
		setFunctionName(functionName);
		setActualArgs(actualArgs);
	}
	
	
	private String functionName;
	private List<MyExpression> actualArgs;
	
	
	public String getFunctionName() {
		return this.functionName;
	}
	
	public List<MyExpression> getActualArgs() {
		return this.actualArgs;
	}
	
	
	private void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
	
	private void setActualArgs(List<MyExpression> actualArgs) {
		this.actualArgs = actualArgs;
	}
	
}
