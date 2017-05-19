package asteroids.junk.DeprecatedCode;

import asteroids.helper.program.OperatorType;
import asteroids.model.programs.MyExpression;
import asteroids.model.programs.MyStatement;
import asteroids.part3.programs.SourceLocation;

public class OperatorExpression <T> implements MyExpression<T> {
	
	public OperatorExpression(MyExpression<T> expression1, MyExpression<T> expression2, OperatorType operatorType, SourceLocation location) {
		setLocation(location);
		setExpression1(expression1);
		setExpression2(expression2);
		setOperatorType(operatorType);
//		if (!expression1.getClass().equals(expression2.getClass())) throw new RuntimeException();
	}
	
	
	private OperatorType operatorType;
	private MyExpression<T> expression1;
	private MyExpression<T> expression2;
	private SourceLocation location;
	
	
	public MyExpression<T> getExpression1() {
		return this.expression1;
	}
	
	public MyExpression<T> getExpression2() {
		return this.expression2;
	}
	
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


	@Override
	public T evaluate() {
		switch (getOperatorType()) {
		case EQUALITY:
			break;
		default:;
		}
		return null;
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
