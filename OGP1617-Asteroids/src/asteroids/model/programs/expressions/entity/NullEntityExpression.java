package asteroids.model.programs.expressions.entity;

import asteroids.model.programs.expressions.EntityExpression;
import asteroids.part3.programs.SourceLocation;

public class NullEntityExpression extends EntityExpression {

	public NullEntityExpression(SourceLocation location) {
		super(location);
	}

	
	
	@Override
	public void setEntity() {
		setEntity(null);
	}

}
