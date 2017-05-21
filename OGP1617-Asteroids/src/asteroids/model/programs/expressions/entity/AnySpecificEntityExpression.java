package asteroids.model.programs.expressions.entity;

import asteroids.helper.entity.Entity;
import asteroids.part3.programs.SourceLocation;

public class AnySpecificEntityExpression extends SpecificEntityExpression {

	public AnySpecificEntityExpression(Class<Entity> entityType, SourceLocation location) {
		super(entityType, location);
	}

	@Override
	public void setEntity() {
		try {
			if (getClosestEntity(getEntityType()) == null)
				throw new NullPointerException();
			setEntity(getClosestEntity(getEntityType()));	
		}
		catch (IndexOutOfBoundsException exc) {
			setEntity(getStatement().getExecuter().getOwner());
		}
		catch (NullPointerException exc) {
			setEntity(getStatement().getExecuter().getOwner());
		}
	}
	
}
