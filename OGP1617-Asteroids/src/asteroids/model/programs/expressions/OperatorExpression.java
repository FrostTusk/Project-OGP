package asteroids.model.programs.expressions;

import asteroids.helper.program.OperatorType;
import asteroids.model.programs.MyExpression;
import asteroids.part3.programs.SourceLocation;

public class OperatorExpression implements MyExpression {
	
	public OperatorExpression(MyExpression expression1, MyExpression expression2, OperatorType operatorType, SourceLocation location) {
		setLocation(location);
		setExpression1(expression1);
		setExpression2(expression2);
		setOperatorType(operatorType);
	}
	
	
	private OperatorType operatorType;
	private MyExpression expression1;
	private MyExpression expression2;
	private SourceLocation location;
	
	
	public MyExpression getExpression1() {
		return this.expression1;
	}
	
	public MyExpression getExpression2() {
		return this.expression2;
	}
	
	public OperatorType getOperatorType() {
		return this.operatorType;
	}
	
	@Override
	public SourceLocation getLocation() {
		return this.location;
	}
	
	
	private void setExpression1(MyExpression expression) {
		this.expression1 = expression;
	}

	private void setExpression2(MyExpression expression) {
		this.expression2 = expression;
	}
	
	private void setOperatorType(OperatorType operatorType) {
		this.operatorType = operatorType;
	}
	
	private void setLocation(SourceLocation location) {
		this.location = location;
	}


	@Override
	public MyExpression execute() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
