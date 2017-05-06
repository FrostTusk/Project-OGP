package asteroids.model.programs.expressions;

import asteroids.helper.entity.EntityType;
import asteroids.helper.program.ExpressionType;
import asteroids.model.programs.MyExpression;
import asteroids.part3.programs.SourceLocation;

public class EntityExpression extends MyExpression {
	
	public EntityExpression(EntityType entityType, SourceLocation location) {
		setLocation(location);
		setType(ExpressionType.ENTITY);
		setEntityType(entityType);
	}
	
	
	private EntityType entityType;
	
	
	public EntityType getEntityType() {
		return this.entityType;
	}
	
	
	protected void setEntityType(EntityType entityType) {
		this.entityType = entityType;
	}
	
}
