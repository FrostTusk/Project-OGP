package asteroids.model.programs.expressions;

import asteroids.model.programs.MyExpression;
import asteroids.model.programs.MyStatement;
import asteroids.part3.programs.SourceLocation;

public class NotExpression implements MyExpression<Boolean> {

	public NotExpression(MyExpression<Boolean> expression, SourceLocation location) {
		setLocation(location);
		setExpression(expression);
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
		getExpression().setStatement(statement);
	}

	private void setLocation(SourceLocation location) {
		this.location = location;
	}


	@Override
	public Boolean evaluate() {
		return (getExpression().evaluate() == true) ? false:true;
	}


	private MyStatement statement;
	
	
	@Override
	public MyStatement getStatement() {
		return this.statement;
	}

	@Override
	public void setStatement(MyStatement statement) {
		this.statement = statement;
		getExpression().setStatement(statement);
	}
	
}
