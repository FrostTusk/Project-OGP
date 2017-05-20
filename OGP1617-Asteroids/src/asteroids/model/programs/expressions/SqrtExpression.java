package asteroids.model.programs.expressions;

import asteroids.model.programs.MyExpression;
import asteroids.model.programs.MyStatement;
import asteroids.part3.programs.SourceLocation;

public class SqrtExpression implements MyExpression<Double> {

	public SqrtExpression(MyExpression<Double> expression, SourceLocation location) {
		setLocation(location);
		setExpression(expression);
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
		return Math.sqrt(expression.evaluate());
	}

	
	private MyStatement statement;
	
	
	@Override
	public MyStatement getStatement() {
		return this.statement;
	}

	@Override
	public void setStatement(MyStatement statement) {
		getExpression().setStatement(statement);
		this.statement = statement;
	}
	
}
