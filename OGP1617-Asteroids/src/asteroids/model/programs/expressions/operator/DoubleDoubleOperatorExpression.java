package asteroids.model.programs.expressions.operator;

import asteroids.helper.program.OperatorType;
import asteroids.model.programs.MyExpression;
import asteroids.model.programs.MyStatement;
import asteroids.part3.programs.SourceLocation;

public class DoubleDoubleOperatorExpression implements MyExpression<Double> {

	public DoubleDoubleOperatorExpression(MyExpression<Double> expression1, MyExpression<Double> expression2, 
			OperatorType operatorType, SourceLocation location) {
		setLocation(location);
		setExpression1(expression1);
		setExpression2(expression2);
		setOperatorType(operatorType);
//		if (expression1.getClass() != expression2.getClass()) throw new RuntimeException();
	}
	
	
	private OperatorType operatorType;
	private MyExpression<Double> expression1;
	private MyExpression<Double> expression2;
	private SourceLocation location;
	
	
	public MyExpression<Double> getExpression1() {
		return this.expression1;
	}
	
	public MyExpression<Double> getExpression2() {
		return this.expression2;
	}
	
	public OperatorType getOperatorType() {
		return this.operatorType;
	}
	
	@Override
	public SourceLocation getLocation() {
		return this.location;
	}
	
	
	private void setExpression1(MyExpression<Double> expression) {
		this.expression1 = expression;
	}

	private void setExpression2(MyExpression<Double> expression) {
		this.expression2 = expression;
	}
	
	private void setOperatorType(OperatorType operatorType) {
		this.operatorType = operatorType;
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
		getExpression1().setStatement(statement);
		getExpression2().setStatement(statement);
		this.statement = statement;
	}

	
	
	@Override
	public Double evaluate() {
		switch (operatorType) {
		case ADDITION:
			return getExpression1().evaluate() + getExpression2().evaluate();
		case MULTIPLICATION:
			return getExpression1().evaluate() * getExpression2().evaluate();			
		default:
			throw new IllegalArgumentException();
		}
	}

}
