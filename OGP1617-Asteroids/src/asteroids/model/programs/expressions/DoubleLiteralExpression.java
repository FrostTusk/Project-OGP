package asteroids.model.programs.expressions;

import asteroids.helper.program.ExpressionType;
import asteroids.model.programs.MyExpression;
import asteroids.part3.programs.SourceLocation;

public class DoubleLiteralExpression extends MyExpression {

	public DoubleLiteralExpression(double value, SourceLocation location) {
		setLocation(location);
		setType(ExpressionType.DOUBLELITERAL);
		setValue(value);
	}
	
	
	private double value;
	
	
	public double getValue() {
		return this.value;
	}
	
	
	private void setValue(double value) {
		this.value = value;
	}
	
}
