package asteroids.model.programs.expressions;

import asteroids.model.programs.*;
import asteroids.part3.programs.SourceLocation;

public class NotExpression implements MyExpression<Boolean> {

	public NotExpression(MyExpression<Boolean> expression, SourceLocation location) {
		setExpression(expression);
		setLocation(location);
	}
	
	
	private MyExpression<Boolean> expression;
	private SourceLocation location;
	
	
	public MyExpression<Boolean> getExpression() {
		return this.expression;
	}
	
	@Override
	public SourceLocation getLocation() {
		return this.location;
	}
	
	
	private void setExpression(MyExpression<Boolean> expression) {
		this.expression = expression;
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
	public void setStatement(MyStatement statement) throws NullPointerException {
		this.statement = statement;
		getExpression().setStatement(statement);
	}

	
	
	@Override
	public Boolean evaluate() throws IllegalArgumentException, NullPointerException {
		return (getExpression().evaluate() == true) ? false: true;
	}
	
}
