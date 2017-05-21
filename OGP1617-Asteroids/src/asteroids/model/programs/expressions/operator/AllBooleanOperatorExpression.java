package asteroids.model.programs.expressions.operator;

import asteroids.helper.program.OperatorType;
import asteroids.model.programs.MyExpression;
import asteroids.model.programs.MyStatement;
import asteroids.model.programs.expressions.OperatorExpression;
import asteroids.part3.programs.SourceLocation;

public class AllBooleanOperatorExpression <T> implements MyExpression<Boolean>{

	
	public AllBooleanOperatorExpression(MyExpression<T> expression1, MyExpression<T> expression2, 
			OperatorType operatorType, SourceLocation location) {
		this.expression1 = expression1;
		this.expression2 = expression2;
		this.operatorType = operatorType;
		setLocation(location);
	}
	
	
	private OperatorExpression<T> operator = new OperatorExpression<T>() {

		@Override
		public MyExpression<T> getExpression1() {
			return expression1;
		}

		@Override
		public MyExpression<T> getExpression2() {
			return expression2;
		}

		@Override
		public OperatorType getOperatorType() {
			return operatorType;
		}
	};
	
	private final MyExpression<T> expression1;
	private final MyExpression<T> expression2;
	private final OperatorType operatorType;
	private SourceLocation location;
	
	
	@Override
	public SourceLocation getLocation() {
		return this.location;
	}
	
	
	private void setLocation(SourceLocation location) {
		this.location = location;
	}
	
	

	private MyStatement statement;
	
	
	@Override
	public MyStatement getStatement() {
		return this.statement;
	}
	

	@Override
	public void setStatement(MyStatement statement) {
		this.statement = statement;
		operator.getExpression1().setStatement(statement);
		operator.getExpression2().setStatement(statement);
	}

	
	
	@Override
	public Boolean evaluate() throws IllegalArgumentException {
		switch (operatorType) {
			case EQUALITY:
				return operator.getExpression1().evaluate() == operator.getExpression2().evaluate();
			default:
				throw new IllegalArgumentException();
		}
	}
	
}
