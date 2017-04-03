package asteroids.helper;

import asteroids.model.World;

public abstract class Entity {
	
	public abstract Position getPosition();
	
	
	public abstract double getVelocityX();
	
	public abstract double getVelocityY();
	
	public abstract double getSpeed();
	
	
	public abstract double getRadius();
	
	
	public abstract double getMass();
	
	
	public abstract double getTimeToCollision(Entity entity);

	
	public abstract World getWorld();
	
	public abstract boolean canHaveAsWorld(World world);
	
	public abstract boolean isInWorld(World world);
	
	public abstract void setWorld(World world);
	
	public abstract void deSetWorld();
	

	public abstract String getType();
}
