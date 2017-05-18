package asteroids.model.programs.expressions;

import asteroids.helper.entity.Entity;
import asteroids.model.Ship;
import asteroids.model.programs.MyExpression;
import asteroids.model.programs.MyStatement;
import asteroids.part3.programs.SourceLocation;

public class EntityExpression implements MyExpression<Entity> {
	
	public EntityExpression(SourceLocation location, Entity specific) {
		setLocation(location);
		
	}
	
	
	public EntityExpression(Class<? extends Entity> entityType, SourceLocation location) {
		setLocation(location);
	}
	
	
	private Entity entity;
	private SourceLocation location;
	
	
	public Entity getEntity() {
		return this.entity;
	}
	
	@Override
	public SourceLocation getLocation() {
		return this.location;
	}
	
	
	protected void setEntity(Entity entity) {
		this.entity = entity;
	}

	private void setLocation(SourceLocation location) {
		this.location = location;
	}

	
	
	private MyStatement statement;

	
	@Override
	public MyStatement getStatement() {
		return this.statement;
	}
	
	
	public void setStatement(MyStatement statement) {
		this.statement = statement;
	}
	
	

	@Override
	public Entity evaluate() {
		return null;
	}
	
	
	
	public void setEntity(Class<? extends Entity> entityType) {
		this.entity = getClosestEntity(entityType);
	}
	
	private Entity getClosestEntity(Class<? extends Entity> entityType) {
		getStatement().getProgram().getOwner().getWorld().getAllEntitiesSpecific(Ship.class);
		return null;
	}
	
	
}
