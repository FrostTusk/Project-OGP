package asteroids.model.programs.expressions;

import asteroids.helper.program.ExpressionType;
import asteroids.model.programs.MyExpression;
import asteroids.part3.programs.SourceLocation;

public class AnyExpression extends MyExpression {
	
	public AnyExpression(SourceLocation location) {
		setLocation(location);
		setType(ExpressionType.ANY);
	}
	
}
