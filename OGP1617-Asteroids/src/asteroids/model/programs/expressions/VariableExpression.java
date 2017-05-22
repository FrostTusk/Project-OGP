package asteroids.model.programs.expressions;

import asteroids.model.programs.*;
import asteroids.part3.programs.SourceLocation;

public class VariableExpression implements MyExpression<Object> {
	
	public VariableExpression(String variableName, SourceLocation location) {
		setVariableName(variableName);
		setLocation(location);
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

	
	
	private MyStatement statement;
	
	
	@Override
	public MyStatement getStatement() {
		return statement;
	}

	@Override
	public void setStatement(MyStatement statement) {
		this.statement = statement;
	}

	
	
	@Override
	public Object evaluate() throws NullPointerException {
		try {
			return getStatement().getExecuter().getLocalVar(getVariableName());
		}
		catch (NullPointerException exc) {
			return getStatement().getExecuter().getGlobalVar(getVariableName());
		}
	}
	
}
