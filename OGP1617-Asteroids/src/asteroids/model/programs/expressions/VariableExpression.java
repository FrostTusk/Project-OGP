package asteroids.model.programs.expressions;

import asteroids.model.programs.MyExpression;
import asteroids.model.programs.MyStatement;
import asteroids.part3.programs.SourceLocation;

public class VariableExpression implements MyExpression<Object> {
	
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
	public Object evaluate() {
		Object local = getStatement().getProgram().getLocalVar(getVariableName());
		Object global = getStatement().getProgram().getGlobalVar(getVariableName());
		if ( (local == null) && (global == null) )
			throw new IllegalArgumentException();
		return (local == null) ? global: local;
	}
	
}
