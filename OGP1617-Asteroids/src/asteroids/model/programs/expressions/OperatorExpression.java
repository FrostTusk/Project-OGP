package asteroids.model.programs.expressions;

import asteroids.helper.program.ExpressionType;
import asteroids.helper.program.OperatorType;
import asteroids.model.programs.MyExpression;
import asteroids.part3.programs.SourceLocation;

public class OperatorExpression extends MyExpression {
	
	public OperatorExpression(MyExpression expression1, MyExpression expression2, OperatorType operatorType, SourceLocation location) {
		setLocation(location);
		setType(ExpressionType.OPERATOR);
		setExpression1(expression1);
		setExpression2(expression2);
		setOperatorType(operatorType);
	}
	
	
	private OperatorType operatorType;
	private MyExpression expression1;
	private MyExpression expression2;
	
	
	public MyExpression getExpression1() {
		return this.expression1;
	}
	
	public MyExpression getExpression2() {
		return this.expression2;
	}
	
	public OperatorType getOperatorType() {
		return this.operatorType;
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
	
}
