package asteroids.model.programs.expressions;

import asteroids.model.programs.MyExpression;
import asteroids.part3.programs.SourceLocation;

public class NotExpression implements MyExpression {

	public NotExpression(MyExpression expression, SourceLocation location) {
		setLocation(location);
		setExpression(expression);
	}
	
	
	private MyExpression expression;
	private SourceLocation location;
	
	
	public MyExpression getExpression() {
		return this.expression;
	}
	
	@Override
	public SourceLocation getLocation() {
		return this.location;
	}
	
	
	private void setExpression(MyExpression expression) {
		this.expression = expression;
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
