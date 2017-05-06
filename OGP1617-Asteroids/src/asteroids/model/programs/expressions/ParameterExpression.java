package asteroids.model.programs.expressions;

import asteroids.helper.program.ExpressionType;
import asteroids.model.programs.MyExpression;
import asteroids.part3.programs.SourceLocation;

public class ParameterExpression extends MyExpression {
	
	public ParameterExpression(String parameterName, SourceLocation sourceLocation) {
		setSourceLocation(sourceLocation);
		setType(ExpressionType.PARAMETER);
		setParameterName(parameterName);
	}
	
	
	private String parameterName;
	
	
	public String getParameterName() {
		return this.parameterName;
	}
	
	
	private void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}
	
}
