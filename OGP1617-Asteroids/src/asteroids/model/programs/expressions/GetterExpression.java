package asteroids.model.programs.expressions;

import asteroids.helper.ExitOutException;
import asteroids.helper.entity.Entity;
import asteroids.helper.program.GetterType;
import asteroids.model.programs.MyExpression;
import asteroids.model.programs.MyStatement;
import asteroids.part3.programs.SourceLocation;

public class GetterExpression implements MyExpression<Double> {
	
	public GetterExpression(MyExpression<Entity> expression, GetterType getterType, SourceLocation location) {
		setExpression(expression);
		setGetterType(getterType);
		setLocation(location);
	}
	
	
	private GetterType getterType;
	private MyExpression<Entity> expression;
	private SourceLocation location;
	
	
	public MyExpression<Entity> getExpression() {
		return this.expression;
	}
	
	public GetterType getGetterType() {
		return this.getterType;
	}
	
	@Override
	public SourceLocation getLocation() {
		return this.location;
	}
	
	
	private void setExpression(MyExpression<Entity> expression) {
		this.expression = expression;
	}

	private void setGetterType(GetterType getterType) {
		this.getterType = getterType;
	}
	
	private void setLocation(SourceLocation location) {
		this.location = location;
	}

	
	
	protected MyStatement statement;

	
	@Override
	public MyStatement getStatement() {
		return this.statement;
	}
	
	
	public void setStatement(MyStatement statement) throws NullPointerException {
		this.statement = statement;
		getExpression().setStatement(statement);		
	}

	
	
	@Override
	public Double evaluate() throws ExitOutException, IllegalArgumentException, IllegalStateException, NullPointerException {
		switch(getGetterType()) {
		case GETDIR:
			return getStatement().getExecuter().getOwner().getOrientation();
		case GETRADIUS:
			return getExpression().evaluate().getRadius();
		case GETVX:
			return getExpression().evaluate().getVelocityX();
		case GETVY:
			return getExpression().evaluate().getVelocityY();
		case GETX:
			return getExpression().evaluate().getPosition().getPositionX();
		case GETY:
			return getExpression().evaluate().getPosition().getPositionY();		
		default:
			throw new IllegalArgumentException();
		}
	}

}
