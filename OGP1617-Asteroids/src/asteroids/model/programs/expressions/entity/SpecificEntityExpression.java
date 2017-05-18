package asteroids.model.programs.expressions.entity;

import asteroids.helper.entity.Entity;
import asteroids.model.programs.expressions.EntityExpression;
import asteroids.part3.programs.SourceLocation;

public class SpecificEntityExpression extends EntityExpression {
	
	public SpecificEntityExpression(Class<? extends Entity> entityType, SourceLocation location) {
		super(location);
		setEntityType(entityType);
	}


	private Class<? extends Entity> entityType;
	
	
	public Class<? extends Entity> getEntityType() {
		return this.entityType;
	}
	
	public Entity getClosestEntity(Class<? extends Entity> entityType) {
		Entity[] entities = new Entity[getStatement().getProgram().getOwner().getWorld().getAllEntitiesSpecific(entityType).size()];
		entities = getStatement().getProgram().getOwner().getWorld().getAllEntitiesSpecific(entityType).toArray(entities);
		Entity entity = entities[0];	// Else nullpointer exception is thrown
		if (entities.length < 2)
			return entity;	// If there is only one such entity
		for (int i = 1; i < entities.length; i++)	// Find the closest entity
			if (entities[i].getTimeToCollision(getStatement().getProgram().getOwner()) 
					< entity.getTimeToCollision(getStatement().getProgram().getOwner()))
				entity = entities[i];
		return entity;
	}


	public void setEntityType(Class<? extends Entity> entityType) {
		this.entityType = entityType;
	}
	
	@Override
	public void setEntity() {
		setEntity(getClosestEntity(getEntityType()));	
	}
	
}
