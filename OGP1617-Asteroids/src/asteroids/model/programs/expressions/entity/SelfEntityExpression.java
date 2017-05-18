package asteroids.model.programs.expressions.entity;

import asteroids.model.programs.expressions.EntityExpression;
import asteroids.part3.programs.SourceLocation;

public class SelfEntityExpression extends EntityExpression {

	public SelfEntityExpression(SourceLocation location) {
		super(location);
	}

	
	@Override
	public void setEntity() {
		setEntity(getStatement().getProgram().getOwner());
	}

}
