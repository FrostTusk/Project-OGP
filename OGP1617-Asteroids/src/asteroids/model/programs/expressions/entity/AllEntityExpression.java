package asteroids.model.programs.expressions.entity;

import asteroids.helper.entity.Entity;
import asteroids.model.programs.expressions.EntityExpression;
import asteroids.part3.programs.SourceLocation;

public class AllEntityExpression extends  EntityExpression {

	public AllEntityExpression(SourceLocation location) {
		super(location);
		// TODO Auto-generated constructor stub
	}

	
	public Entity getClosestEntity() throws IndexOutOfBoundsException {
		Entity[] entities;
		try {
			entities = new Entity[getStatement().getExecuter().getOwner().getWorld().getAllEntities().size()];
		}
		catch(NullPointerException exc) {
			return null; // Default value if any element is null.
		}
		
		entities = getStatement().getExecuter().getOwner().getWorld().getAllEntities().toArray(entities);
		
		Entity entity = entities[0];	// Else index out of bounds exception is thrown.
		if (entities.length < 2)
			return entity;	// If there is only one entity.
		
		for (int i = 1; i < entities.length; i++)	// Find the closest entity
			if (entities[i].getTimeToCollision(getStatement().getExecuter().getOwner()) 
					< entity.getTimeToCollision(getStatement().getExecuter().getOwner()))
				entity = entities[i];
		
		return entity;
	}
	
	
	public Entity getDifferentEntity(Entity[] entities, Entity excludeEntity) throws IndexOutOfBoundsException {
		for (int i = 0; i < entities.length; i++)
			if (entities[i] != excludeEntity)
				return entities[i];
		return null;
	}
	
	
	@Override
	public void setEntity() {

	}

}
