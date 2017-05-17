package asteroids.model.programs.expressions;

import asteroids.model.programs.MyExpression;
import asteroids.part3.programs.SourceLocation;

public class NotExpression implements MyExpression<Boolean> {

	public NotExpression(MyExpression<Boolean> expression, SourceLocation location) {
		setLocation(location);
		setExpression(expression);
	}
	
	
	private MyExpression<Boolean> expression;
	private SourceLocation location;
	
	
	public MyExpression<Boolean> getExpression() {
		return this.expression;
	}
	
	@Override
	public SourceLocation getLocation() {
		return this.location;
	}
	
	
	private void setExpression(MyExpression<Boolean> expression) {
		this.expression = expression;
	}

	private void setLocation(SourceLocation location) {
		this.location = location;
	}


	@Override
	public Boolean evaluate() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
