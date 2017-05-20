package asteroids.model.programs.expressions.entity;

import java.util.Set;

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
	
	
	public void setEntityType(Class<? extends Entity> entityType) {
		this.entityType = entityType;
	}
	
	@Override
	public void setEntity() {
		try {
			setEntity(getClosestEntity(getEntityType()));	
		}
		catch (IndexOutOfBoundsException exc) {
			setEntity(null);
		}
		catch (NullPointerException exc) {
			setEntity(null);
		}
	}
	
	
	
	public Entity getClosestEntity(Class<? extends Entity> entityType) throws  IndexOutOfBoundsException {
		Entity[] entitiesArray = getEntitiesArray(entityType); // Get the array of entities of the given type.
		Entity startEntity = getDifferentEntity(entitiesArray, getStatement().getExecuter().getOwner());
		Entity tempEntity = startEntity; // Get an entity that's different from the owner.
		
		for (int i = 0; i < entitiesArray.length; i++) { // Find the closest entity that is not 
			Entity currentEntity = entitiesArray[i]; 	 // the owner or the start entity.
			if ( (currentEntity == startEntity) || (currentEntity == getStatement().getExecuter().getOwner()) )
					continue;
			if (currentEntity.getDistanceBetween(getStatement().getExecuter().getOwner()) 
					< tempEntity.getDistanceBetween(getStatement().getExecuter().getOwner()))
				tempEntity = currentEntity;
		}
		return tempEntity;
	}
	
	public Entity[] getEntitiesArray(Class<? extends Entity> entityType) {
		Entity[] entitiesArray;
		Set<? extends Entity> entitiesSet;
		try {
			entitiesSet = getStatement().getExecuter().getOwner().getWorld().getAllEntitiesSpecific(entityType);
			entitiesArray = new Entity[entitiesSet.size()];
		}
		catch(NullPointerException exc) {
			return null; // Default value if any element is null.
		}
		return entitiesSet.toArray(entitiesArray);
	}

	/**
	 * @throws 	IndexOutOfBoundsException
	 * 			Thrown if entities is null.
	 */
	public Entity getDifferentEntity(Entity[] entities, Entity excludeEntity) throws IndexOutOfBoundsException {
		for (int i = 0; i < entities.length; i++)
			if (entities[i] != excludeEntity)
				return entities[i];
		return null;
	}
	
}
