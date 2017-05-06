package asteroids.model.programs.expressions;

import asteroids.helper.program.ExpressionType;
import asteroids.model.programs.MyExpression;
import asteroids.part3.programs.SourceLocation;

public class NullExpression extends MyExpression {
	
	public NullExpression(SourceLocation location) {
		setLocation(location);
		setType(ExpressionType.NULL);
	}
	
}
