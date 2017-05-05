package asteroids.model.programs;

import asteroids.helper.program.ExpressionType;

public class MyExpression {

	public MyExpression(ExpressionType type) {
		this.type = type;
	}
	
	private ExpressionType type;

	public ExpressionType getType() {
		return type;
	}
}
