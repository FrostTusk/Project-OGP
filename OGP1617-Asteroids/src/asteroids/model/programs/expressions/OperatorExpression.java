package asteroids.model.programs.expressions;

import asteroids.helper.program.OperatorType;
import asteroids.model.programs.MyExpression;

public interface OperatorExpression <T> {

	public MyExpression<T> getExpression1();
	public MyExpression<T> getExpression2();
	public OperatorType getOperatorType();

}
