package asteroids.helper;

import asteroids.model.World;

public abstract class Entity {
	
	public abstract Position getPosition();
	
	
	public abstract double getVelocityX();
	
	public abstract double getVelocityY();
	
	public abstract double getSpeed();
	
	
	public abstract double getRadius();
	
	
	public abstract double getMass();
	
	
	
	
	public abstract World getWorld();
	
	public abstract boolean canHaveAsWorld(World world);
	
	public abstract boolean isInWorld(World world);
	
	public abstract void setWorld(World world);
	
	public abstract void deSetWorld();
	
	
	
	
	public abstract void move(double time);
	
	public abstract double[] getDistanceBetween(World world);
	
	
//	public abstract double getTimeToCollision(World world);
	// TODO Can we do this?
	public double getTimeToCollision(World world) {
		if (! world.containsEntity(this)) return Double.POSITIVE_INFINITY;
		
		double[] distance = getDistanceBetween(world);
		if ( (distance[0] == Double.POSITIVE_INFINITY) || (distance[1] == Double.POSITIVE_INFINITY) )
			return Double.POSITIVE_INFINITY;
		
		double time1 = distance[0] / this.getVelocityX();
		if (distance[1] + this.getVelocityY() * time1 > world.getHeight()) return Double.POSITIVE_INFINITY;
		if (distance[1] + this.getVelocityY() * time1 < 0) return Double.POSITIVE_INFINITY;
			
		double time2 = distance[1] / this.getVelocityY();
		if (distance[0] + this.getVelocityX() * time2 > world.getWidth()) return Double.POSITIVE_INFINITY;
		if (distance[0] + this.getVelocityX() * time2 < 0) return Double.POSITIVE_INFINITY;

		if (time1 > time2) return time2;		
		return time1;
	}
	
	public abstract double getTimeToCollision(Entity entity);

	public abstract double[] getCollisionPosition(World world);
	
	public abstract double[] getCollisionPosition(Entity entity);
	
	
	public abstract String getType();

}
