package asteroids.helper.program;

import asteroids.helper.entity.EntityType;

public class SimpleEntity implements SimpleClass{
	
	public SimpleEntity(EntityType value) {
		setValue(value);
	}
	
	private EntityType value;
	
	private void setValue(EntityType value) {
		this.value = value;
	}

	@Override
	public EntityType getValue() {
		return this.value;
	}

	@Override
	public ClassType getClassType() {
		return ClassType.ENTITY;
	}
	
}
