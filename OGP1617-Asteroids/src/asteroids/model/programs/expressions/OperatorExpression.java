package asteroids.model.programs.expressions;

import asteroids.helper.program.OperatorType;
import asteroids.model.programs.MyExpression;
import asteroids.model.programs.MyStatement;
import asteroids.part3.programs.SourceLocation;

public class OperatorExpression implements MyExpression<Object> {
	
	public OperatorExpression(MyExpression<Object> expression1, MyExpression<Object> expression2, OperatorType operatorType, SourceLocation location) {
		setLocation(location);
		setExpression1(expression1);
		setExpression2(expression2);
		setOperatorType(operatorType);
		if (expression1.getClass() != expression2.getClass()) throw new RuntimeException();
	}
	
	
	private OperatorType operatorType;
	private MyExpression<Object> expression1;
	private MyExpression<Object> expression2;
	private SourceLocation location;
	
	
	public MyExpression<Object> getExpression1() {
		return this.expression1;
	}
	
	public MyExpression<Object> getExpression2() {
		return this.expression2;
	}
	
	public OperatorType getOperatorType() {
		return this.operatorType;
	}
	
	@Override
	public SourceLocation getLocation() {
		return this.location;
	}
	
	
	private void setExpression1(MyExpression<Object> expression) {
		this.expression1 = expression;
	}

	private void setExpression2(MyExpression<Object> expression) {
		this.expression2 = expression;
	}
	
	private void setOperatorType(OperatorType operatorType) {
		this.operatorType = operatorType;
	}
	
	private void setLocation(SourceLocation location) {
		this.location = location;
	}


	@Override
	public Object evaluate() {
		switch (getOperatorType()) {
		case ADDITION:
			break;
		case EQUALITY:
			break;
		case LESSTHAN:
			break;
		case MULTIPLICATION:
			break;
		default:
			break;			
		}
		return true;
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
