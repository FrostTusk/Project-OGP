package asteroids.junk.DeprecatedCode;

import java.util.Set;

import asteroids.helper.entity.Entity;
import asteroids.model.programs.expressions.EntityExpression;
import asteroids.part3.programs.SourceLocation;

public class AllEntityExpression extends  EntityExpression {

	public AllEntityExpression(SourceLocation location) {
		super(location);
		// TODO Auto-generated constructor stub
	}

	
	public Entity getClosestEntity() throws IndexOutOfBoundsException {
		Entity[] entitiesArray;
		Set<Entity> entitiesSet;
		try {
			entitiesSet = getStatement().getExecuter().getOwner().getWorld().getAllEntities();
			entitiesArray = new Entity[getStatement().getExecuter().getOwner().getWorld().getAllEntities().size()];
		}
		catch(NullPointerException exc) {
			return null; // Default value if any element is null.
		}
		
		entitiesArray = entitiesSet.toArray(entitiesArray);
		Entity startEntity = getDifferentEntity(entitiesArray, getStatement().getExecuter().getOwner());
		Entity tempEntity = startEntity;
		
		for (int i = 0; i < entitiesArray.length; i++) {// Find the closest entity
			Entity currentEntity = entitiesArray[i]; 
			if ( (currentEntity == startEntity) || (currentEntity == getStatement().getExecuter().getOwner()) )
					continue;
			if (currentEntity.getTimeToCollision(getStatement().getExecuter().getOwner()) 
					< tempEntity.getTimeToCollision(getStatement().getExecuter().getOwner()))
				tempEntity = currentEntity;
		}
		return tempEntity;
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
