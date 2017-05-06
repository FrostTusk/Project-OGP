package asteroids.model.programs.expressions;

import asteroids.helper.program.ExpressionType;
import asteroids.model.programs.MyExpression;
import asteroids.part3.programs.SourceLocation;

public class SqrtExpression extends MyExpression {

	public SqrtExpression(MyExpression expression, SourceLocation location) {
		setLocation(location);
		setType(ExpressionType.SQRT);
		setExpression(expression);
	}
	
	
	private MyExpression expression;
	
	
	public MyExpression getExpression() {
		return this.expression;
	}
	
	
	private void setExpression(MyExpression expression) {
		this.expression = expression;
	}
	
}
