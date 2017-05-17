package asteroids.model.programs.expressions;

import asteroids.helper.program.OperatorType;
import asteroids.model.programs.MyExpression;
import asteroids.part3.programs.SourceLocation;

public class OperatorExpression implements MyExpression<Object> {
	
	public OperatorExpression(MyExpression<Boolean> expression1, MyExpression<Boolean> expression2, OperatorType operatorType, SourceLocation location) {
		setLocation(location);
		setExpression1(expression1);
		setExpression2(expression2);
		setOperatorType(operatorType);
	}
	
	
	private OperatorType operatorType;
	private MyExpression<Boolean> expression1;
	private MyExpression<Boolean> expression2;
	private SourceLocation location;
	
	
	public MyExpression<Boolean> getExpression1() {
		return this.expression1;
	}
	
	public MyExpression<Boolean> getExpression2() {
		return this.expression2;
	}
	
	public OperatorType getOperatorType() {
		return this.operatorType;
	}
	
	@Override
	public SourceLocation getLocation() {
		return this.location;
	}
	
	
	private void setExpression1(MyExpression<Boolean> expression) {
		this.expression1 = expression;
	}

	private void setExpression2(MyExpression<Boolean> expression) {
		this.expression2 = expression;
	}
	
	private void setOperatorType(OperatorType operatorType) {
		this.operatorType = operatorType;
	}
	
	private void setLocation(SourceLocation location) {
		this.location = location;
	}


	@Override
	public MyExpression<Boolean> evaluate() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
