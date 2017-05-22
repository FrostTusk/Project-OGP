package asteroids.model.programs.expressions;

import asteroids.helper.ExitOutException;
import asteroids.model.programs.*;
import asteroids.part3.programs.SourceLocation;

public class ChangeSignExpression implements MyExpression<Double> {

	public ChangeSignExpression(MyExpression<Double> expression, SourceLocation location) {
		setExpression(expression);
		setLocation(location);
	}
	
	
	private MyExpression<Double> expression;
	private SourceLocation location;
	
	
	public MyExpression<Double> getExpression() {
		return this.expression;
	}
	
	@Override
	public SourceLocation getLocation() {
		return this.location;
	}
	
	
	private void setExpression(MyExpression<Double> expression) {
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
	
	
	public void setStatement(MyStatement statement) throws NullPointerException {
		this.statement = statement;
		getExpression().setStatement(statement);
	}

	
	
	@Override
	public Double evaluate() throws ExitOutException, IllegalArgumentException, IllegalStateException, NullPointerException {
		return -1 * getExpression().evaluate();
	}
	
}
