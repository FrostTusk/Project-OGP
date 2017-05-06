package asteroids.model.programs.expressions;

import asteroids.helper.program.ExpressionType;
import asteroids.model.programs.MyExpression;
import asteroids.part3.programs.SourceLocation;

public class VariableExpression extends MyExpression {
	
	public VariableExpression(String variableName, SourceLocation sourceLocation) {
		setSourceLocation(sourceLocation);
		setType(ExpressionType.VARIABLE);
		setVariableName(variableName);
	}
	
	
	private String variableName;
	
	
	public String getVariableName() {
		return this.variableName;
	}
	
	
	public void setVariableName(String variableName) {
		this.variableName = variableName;
	}
	
}
