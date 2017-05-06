package asteroids.model.programs.expressions;

import asteroids.helper.program.ExpressionType;
import asteroids.model.programs.MyExpression;
import asteroids.part3.programs.SourceLocation;

public class NotExpression extends MyExpression {

	public NotExpression(MyExpression expression, SourceLocation location) {
		setLocation(location);
		setType(ExpressionType.NOT);
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
