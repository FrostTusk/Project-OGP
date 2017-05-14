package asteroids.model.programs.expressions;

import asteroids.helper.program.GetterType;
import asteroids.model.programs.MyExpression;
import asteroids.part3.programs.SourceLocation;

public class GetterExpression implements MyExpression {

	public GetterExpression(GetterType getterType, SourceLocation location) {
		setLocation(location);
		setExpression(null);
		setGetterType(getterType);
	}
	
	public GetterExpression(MyExpression expression, GetterType getterType, SourceLocation location) {
		setLocation(location);
		setExpression(expression);
		setGetterType(getterType);
	}
	
	
	private GetterType getterType;
	private MyExpression expression;
	private SourceLocation location;
	
	
	public MyExpression getExpression() {
		return this.expression;
	}
	
	public GetterType getGetterType() {
		return this.getterType;
	}
	
	@Override
	public SourceLocation getLocation() {
		return this.location;
	}
	
	
	private void setExpression(MyExpression expression) {
		this.expression = expression;
	}

	private void setGetterType(GetterType getterType) {
		this.getterType = getterType;
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
