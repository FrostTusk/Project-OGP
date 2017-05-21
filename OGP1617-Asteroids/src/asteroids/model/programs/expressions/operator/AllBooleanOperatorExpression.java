package asteroids.model.programs.expressions.operator;

import asteroids.helper.program.OperatorType;
import asteroids.model.programs.*;
import asteroids.model.programs.expressions.OperatorExpression;
import asteroids.part3.programs.SourceLocation;

public class AllBooleanOperatorExpression <T> implements MyExpression<Boolean>, OperatorExpression<T> {
	
	public AllBooleanOperatorExpression(MyExpression<T> expression1, MyExpression<T> expression2, 
			OperatorType operatorType, SourceLocation location) {
		setExpression1(expression1);
		setExpression2(expression2);
		setOperatorType(operatorType);
		setLocation(location);
	}
	
	
	private OperatorType operatorType;
	private MyExpression<T> expression1;
	private MyExpression<T> expression2;
	private SourceLocation location;
	
	
	@Override
	public MyExpression<T> getExpression1() {
		return this.expression1;
	}

	@Override
	public MyExpression<T> getExpression2() {
		return this.expression2;
	}

	@Override
	public OperatorType getOperatorType() {
		return this.operatorType;
	}
	
	@Override
	public SourceLocation getLocation() {
		return this.location;
	}
	

	private void setExpression1(MyExpression<T> expression) {
		this.expression1 = expression;
	}

	private void setExpression2(MyExpression<T> expression) {
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
		return this.statement;
	}
	

	@Override
	public void setStatement(MyStatement statement) {
		this.statement = statement;
		getExpression1().setStatement(statement);
		getExpression2().setStatement(statement);
	}

	
	
	@Override
	public Boolean evaluate() throws IllegalArgumentException {
		switch (operatorType) {
			case EQUALITY:
				return getExpression1().evaluate() == getExpression2().evaluate();
			default:
				throw new IllegalArgumentException();
		}
	}

}
