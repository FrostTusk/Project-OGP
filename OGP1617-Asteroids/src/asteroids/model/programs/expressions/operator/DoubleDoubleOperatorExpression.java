package asteroids.model.programs.expressions.operator;

import asteroids.helper.program.OperatorType;
import asteroids.model.programs.*;
import asteroids.model.programs.expressions.OperatorExpression;
import asteroids.part3.programs.SourceLocation;

public class DoubleDoubleOperatorExpression implements MyExpression<Double> {

	public DoubleDoubleOperatorExpression(MyExpression<Double> expression1, MyExpression<Double> expression2, 
			OperatorType operatorType, SourceLocation location) {
		this.expression1 = expression1;
		this.expression2 = expression2;
		this.operatorType = operatorType;
		setLocation(location);
	}
	
	
	private OperatorExpression<Double> operator = new OperatorExpression<Double>() {

		@Override
		public MyExpression<Double> getExpression1() {
			return expression1;
		}

		@Override
		public MyExpression<Double> getExpression2() {
			return expression2;
		}

		@Override
		public OperatorType getOperatorType() {
			return operatorType;
		}
	};
	
	private final MyExpression<Double> expression1;
	private final MyExpression<Double> expression2;
	private final OperatorType operatorType;
	private SourceLocation location;
	
	
	@Override
	public SourceLocation getLocation() {
		return this.location;
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
		operator.getExpression1().setStatement(statement);
		operator.getExpression2().setStatement(statement);
	}

	
	
	@Override
	public Double evaluate() throws IllegalArgumentException {
		switch (operatorType) {
			case ADDITION:
				return operator.getExpression1().evaluate() + operator.getExpression2().evaluate();
			case MULTIPLICATION:
				return operator.getExpression1().evaluate() * operator.getExpression2().evaluate();			
			default:
				throw new IllegalArgumentException();
		}
	}

}
