package asteroids.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw;

public class Bullet {

	/**
	 * Variable registering the X position of this bullet.
	 */
	private double positionX;
	/**
	 * Variable registering the minimum X position of this bullet.
	 */
	private final double minPositionX = Double.NEGATIVE_INFINITY;
	/**
	 * Variable registering the maximum X position of this bullet.
	 */
	private final double maxPositionX = Double.POSITIVE_INFINITY;
	/**
	 * Variable registering the Y position of this bullet.
	 */
	private double positionY;
	/**
	 * Variable registering the minimum Y position of this bullet.
	 */
	private final double minPositionY = Double.NEGATIVE_INFINITY;
	/**
	 * Variable registering the maximum Y position of this bullet.
	 */
	private final double maxPositionY = Double.POSITIVE_INFINITY;
	
	
	/**
	 * Return the X position of this bullet.
	 */
	@Basic @Raw
	public double getPositionX() {
		return this.positionX;
	}
	
	/**
	 * Return the minimum X position of this bullet.
	 */
	@Basic @Immutable @Raw
	public double getMinPositionX() {
		return this.minPositionX;
	}
	
	/**
	 * Return the max X position of this bullet.
	 */
	@Basic @Immutable @Raw 
	public double getMaxPositionX() {
		return this.maxPositionX;
	}
	
	/**
	 * Return the Y position of this bullet.
	 */
	@Basic @Raw
	public double getPositionY() {
		return this.positionY;
	}
	
	/**
	 * Return the minimum Y position of this bullet.
	 */
	@Basic @Immutable @Raw
	public double getMinPositionY() {
		return this.minPositionY;
	}
	
	/**
	 * Return the max Y position of this bullet.
	 */
	@Basic @Immutable @Raw 
	public double getMaxPositionY() {
		return this.maxPositionY;
	}
	
	
	/**
	 * Check whether the given position is a valid position for
	 * any bullet.
	 *  
	 * @param  	positionX
	 *         	The X position to check.
	 * @param	positionY
	 * 			The Y position to check.
	 * @return	Returns whether or not the given position is a valid position.
	 * 			true if it is, false if not.
	 *       	| result == (! ( (java.lang.Double.isNaN(positionX)) || (java.lang.Double.isNaN(positionY)) ) &&
	 *       	|			(this.getMinPositionX() < positionX) && (this.getMaxPositionX() > positionX) &&
	 *			|			(this.getMinPositionY() < positionY) && (this.getMaxPositionY() > positionY)      
	 */
	public boolean isValidPosition(double positionX, double positionY) {
		// A position has to be a number.
		if ( (java.lang.Double.isNaN(positionX)) || (java.lang.Double.isNaN(positionY)) )
			return false;
		// A position cannot be smaller/bigger or equal to negative/positive infinity.
		if ( (getMinPositionX() < positionX) && (getMaxPositionX() > positionX) &&
				(getMinPositionY() < positionY) && (getMaxPositionY() > positionY) )
			return true;
		else
			return false;	
	}
	
	
	/**
	 * Set the position of this bullet to the given position.
	 * 
	 * @param  	positionX
	 *         	The new X position for this bullet.
	 * @param  	positionY
	 * 		   	The new Y position for this bullet.
	 * 
	 * @post   	The X position of this new bullet is equal to
	 *         	the given X position.
	 *       	| new.getPositionX() == positionX
	 * @post   	The Y position of this new bullet is equal to
	 *         	the given Y position.
	 *       	| new.getPositionY() == positionY
	 *       
	 * @throws 	IndexOutOfBoundsException
	 *         	The given position is not a valid position for any
	 *         	bullet.
	 *       	| ! isValidPosition(getPositionX(), getPositionY())
	 */
	@Raw
	public void setPosition(double positionX, double positionY) 
			throws IndexOutOfBoundsException {
		if (! isValidPosition(positionX, positionY))
			throw new IndexOutOfBoundsException();
		this.positionX = positionX;
		this.positionY = positionY;
	}
	
	
}
