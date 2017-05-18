package asteroids.model.programs.expressions;

import asteroids.model.programs.MyExpression;
import asteroids.model.programs.MyStatement;
import asteroids.part3.programs.SourceLocation;

public class VariableExpression implements MyExpression<String> {
	
	public VariableExpression(String variableName, SourceLocation location) {
		setLocation(location);
		setVariableName(variableName);
	}
	
	
	private String variableName;
	private SourceLocation location;
	
	
	public String getVariableName() {
		return this.variableName;
	}
	
	@Override
	public SourceLocation getLocation() {
		return this.location;
	}
	
	
	private void setVariableName(String variableName) {
		this.variableName = variableName;
	}
	
	private void setLocation(SourceLocation location) {
		this.location = location;
	}

	
	@Override
	public String evaluate() {
		return getVariableName();
	}


	@Override
	public MyStatement getStatement() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
