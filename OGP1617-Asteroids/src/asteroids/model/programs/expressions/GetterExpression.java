package asteroids.model.programs.expressions;

import asteroids.helper.program.ExpressionType;
import asteroids.helper.program.GetterType;
import asteroids.model.programs.MyExpression;
import asteroids.part3.programs.SourceLocation;

public class GetterExpression extends MyExpression {

	public GetterExpression(GetterType getterType, SourceLocation location) {
		setLocation(location);
		setType(ExpressionType.GETTER);
		setExpression(null);
		setGetterType(getterType);
	}
	
	public GetterExpression(MyExpression expression, GetterType getterType, SourceLocation location) {
		setLocation(location);
		setType(ExpressionType.GETTER);
		setExpression(expression);
		setGetterType(getterType);
	}
	
	
	private GetterType getterType;
	private MyExpression expression;
	
	
	public MyExpression getExpression() {
		return this.expression;
	}
	
	public GetterType getGetterType() {
		return this.getterType;
	}
	
	
	private void setExpression(MyExpression expression) {
		this.expression = expression;
	}

	private void setGetterType(GetterType getterType) {
		this.getterType = getterType;
	}
	
}
