package asteroids.model.programs.expressions.entity;

import asteroids.model.programs.expressions.EntityExpression;
import asteroids.part3.programs.SourceLocation;

public class SelfEntityExpression extends EntityExpression {

	public SelfEntityExpression(SourceLocation location) {
		super(location);
	}

	
	@Override
	public void setEntity() {
		try {
			setEntity(getStatement().getExecuter().getOwner());
		}
		catch (NullPointerException exc) {
			setEntity(null);
		}
	}

}
