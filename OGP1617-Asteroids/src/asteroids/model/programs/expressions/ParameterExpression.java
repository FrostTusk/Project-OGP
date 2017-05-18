package asteroids.model.programs.expressions;

import asteroids.model.programs.MyExpression;
import asteroids.model.programs.MyStatement;
import asteroids.part3.programs.SourceLocation;

public class ParameterExpression implements MyExpression<String> {
	
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


	@Override
	public String evaluate() {
		return getParameterName();
	}

	@Override
	public MyStatement getStatement() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setStatement(MyStatement statement) {
		// TODO Auto-generated method stub
		
	}
	
}
