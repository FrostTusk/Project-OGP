package asteroids.model.programs.expressions;

import asteroids.helper.entity.Entity;
import asteroids.model.programs.*;
import asteroids.part3.programs.SourceLocation;

public abstract class EntityExpression implements MyExpression<Entity> {
	
	public EntityExpression(SourceLocation location) {
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
	
	
	public abstract void setEntity();
	
	public void setEntity(Entity entity) {
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
		try {
			setEntity();
		}
		catch (IndexOutOfBoundsException exc) {
			setEntity(null);
		}
		catch (NullPointerException exc) {
			setEntity(null);
		}
	}	
	
	
	
	@Override
	public Entity evaluate() {
		try {
			setEntity();
			return getEntity();
		}
		catch (IndexOutOfBoundsException exc){
			return null;
		}
		catch (NullPointerException exc) {
			return null;
		}
	}
	
}
