package asteroids.model.programs.expressions;

import asteroids.model.programs.MyExpression;
import asteroids.model.programs.MyFunction;
import asteroids.model.programs.MyStatement;
import asteroids.part3.programs.SourceLocation;

public class ParameterExpression implements MyExpression<Object> {
	
	public ParameterExpression(String parameterName, SourceLocation location) {
		setLocation(location);
		setParameterName(parameterName);
	}
	
	
	private String parameterName;
	private SourceLocation location;
	
	
	public String getParameterName() {
		return this.parameterName;
	}
	
	@Override
	public SourceLocation getLocation() {
		return this.location;
	}
	
	
	private void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}

	private void setLocation(SourceLocation location) {
		this.location = location;
	}


	
	private MyStatement statement;
	
	
	@Override
	public MyStatement getStatement() {
		return this.statement;
	}

	@Override
	public void setStatement(MyStatement statement) {
		this.statement = statement;
	}

	@Override
	public Object evaluate() {
		if (!(getStatement().getExecuter() instanceof MyFunction))
			throw new IllegalArgumentException();
		return ((MyFunction) getStatement().getExecuter()).getArgument(getParameterName());
	}
	
}
