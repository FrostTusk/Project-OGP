package asteroids.model.programs.expressions;

import asteroids.helper.entity.EntityType;
import asteroids.model.programs.MyExpression;
import asteroids.part3.programs.SourceLocation;

public class EntityExpression implements MyExpression {
	
	public EntityExpression(EntityType entityType, SourceLocation location) {
		setLocation(location);
		setEntityType(entityType);
	}
	
	
	private EntityType entityType;
	private SourceLocation location;
	
	
	public EntityType getEntityType() {
		return this.entityType;
	}
	
	@Override
	public SourceLocation getLocation() {
		return this.location;
	}
	
	
	protected void setEntityType(EntityType entityType) {
		this.entityType = entityType;
	}

	private void setLocation(SourceLocation location) {
		this.location = location;
	}


	@Override
	public MyExpression evaluate() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
