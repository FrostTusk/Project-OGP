package asteroids.model.programs.expressions;

import asteroids.model.programs.MyExpression;
import asteroids.model.programs.MyStatement;
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

	
	@Override
	public Double evaluate() {
		return -1 * getExpression().evaluate();
	}

	@Override
	public MyStatement getStatement() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
