package asteroids.model.programs.expressions;

import asteroids.helper.program.ExpressionType;
import asteroids.model.programs.MyExpression;
import asteroids.part3.programs.SourceLocation;

public class ChangeSignExpression extends MyExpression {

	public ChangeSignExpression(MyExpression expression, SourceLocation sourceLocation) {
		setSourceLocation(sourceLocation);
		setType(ExpressionType.CHANGESIGN);
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
