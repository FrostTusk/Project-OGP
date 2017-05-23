package asteroids.helper.entity;

import be.kuleuven.cs.som.annotate.*;

/**
 * A class of positions. Each position has an X and Y position.
 * Each position also has a maximum and minimum value (both exclusive) for it's x and y positions. 
 * By default this value will be +/- Infinity for maximum and minimum respectively.
 * This is because a position cannot be equal to infinity.
 * 
 * Update: If the bounds are not given, they will also not be checked,
 * 		   the default values will NOT be used.
 * 
 * @author Mathijs Hubrechtsen, Ruben Dhuyvetter 
 * 
 * @invar  	Each position must be a valid position.
 *       	| this.isValidPosition(this.getPositionX(), this.getPositionY())
 */
@Value
public class Position {
	 
	/**
	 * Initialize this new position with given X and Y position and default values for
	 * the minimum and maximum position, this means the bounds will not be checked.
	 *
	 * @param  	positionX
	 *         	The X position for this new bullet.
	 * @param  	positionY
	 * 			The Y position of this new position
	 * 
	 * @post   	The X position of this new position is equal to
	 *         	the given X position.
	 *       	| new.getPositionX() == positionX
	 * @post   	The Y position of this new position is equal to
	 *         	the given Y position.
	 *       	| new.getPositionY() == positionY
	 *       
	 * @throws 	IllegalArgumentException
	 *         	The given positions cannot be a valid position for any
	 *         	position.
	 *       	| ! this.isValidPosition(positionX, positionY)
	 */
	public Position(double positionX, double positionY) throws IllegalArgumentException {
		this.boundsSet = false;
		this.minPositionX = Double.NEGATIVE_INFINITY;	// These value are never used
		this.maxPositionX = Double.POSITIVE_INFINITY;	// because boundsSet == false.
		this.minPositionY = Double.NEGATIVE_INFINITY;
		this.maxPositionY = Double.POSITIVE_INFINITY;

		if (!isValidPosition(positionX, positionY))
			throw new IllegalArgumentException();
		
		this.positionX = positionX;
		this.positionY = positionY;
	}
	
	/**
	 * Initialize this new position with given X and Y position, 
	 * a given X minimum and maximum value, and a given Y minimum and maximum value.
	 *
	 * @param  	positionX
	 *         	The X position of this new position.
	 * @param  	positionY
	 * 			The Y position of this new position.
	 * @param	minPositionX
	 * 			The minimum X position of this new position.
	 * @param	maxPositionX
	 * 			The maximum X position of this new position.
	 * @param	minPositionY
	 * 			The minimum Y position of this new position.
	 * @param	maxPositionY
	 * 			The maximum Y position of this new position.
	 * 
	 * @post   	The X position of this new position is equal to
	 *         	the given X position.
	 *       	| new.getPositionX() == positionX
	 * @post   	The Y position of this new position is equal to
	 *         	the given Y position.
	 *       	| new.getPositionY() == positionY
	 *       
	 * @throws 	IllegalArgumentException
	 *         	The given positions cannot be a valid position for any
	 *         	position.
	 *       	| ! this.isValidPosition(positionX, positionY)
	 */
	public Position(double positionX, double positionY,
			double minPositionX, double maxPositionX, double minPositionY, double maxPositionY) throws IllegalArgumentException {
		this.boundsSet = true;
		this.minPositionX = minPositionX;
		this.maxPositionX = maxPositionX;
		this.minPositionY = minPositionY;
		this.maxPositionY = maxPositionY;
			
		if (!isValidPosition(positionX, positionY))
			throw new IllegalArgumentException();
		
		this.positionX = positionX;
		this.positionY = positionY;
	}
	
	
	private final boolean boundsSet;
	/**
	 * Variable registering the X position of this position.
	 */
	private final double positionX;
	/**
	 * Variable registering the minimum X position of this position.
	 */
	private final double minPositionX;
	/**
	 * Variable registering the maximum X position of this position.
	 */
	private final double maxPositionX;
	/**
	 * Variable registering the Y position of this position.
	 */
	private final double positionY;
	/**
	 * Variable registering the minimum Y position of this position.
	 */
	private final  double minPositionY;
	/**
	 * Variable registering the maximum Y position of this position.
	 */
	private final double maxPositionY;
	
	
	/**
	 * Return the X position of this position.
	 */
	@Basic @Immutable @Raw
	public double getPositionX() {
		return this.positionX;
	}
	
	/**
	 * Return the minimum X position of this position.
	 */
	@Basic @Immutable @Raw
	public double getMinPositionX() {
		return this.minPositionX;
	}
	
	/**
	 * Return the max X position of this position.
	 */
	@Basic @Immutable @Raw
	public double getMaxPositionX() {
		return this.maxPositionX;
	}
	
	/**
	 * Return the Y position of this position.
	 */
	@Basic @Immutable @Raw
	public double getPositionY() {
		return this.positionY;
	}
	
	/**
	 * Return the minimum Y position of this position.
	 */
	@Basic @Immutable @Raw
	public double getMinPositionY() {
		return this.minPositionY;
	}
	
	/**
	 * Return the max Y position of this position.
	 */
	@Basic @Immutable @Raw
	public double getMaxPositionY() {
		return this.maxPositionY;
	}
	
	
	/**
	 * Check whether the given position is a valid position.
	 *  
	 * @param  	positionX
	 *         	The X position to check.
	 * @param	positionY
	 * 			The Y position to check.
	 * @return	Returns whether or not the given position is a valid position.
	 * 			true if it is, false if not.
	 *       	| result == (! ( (java.lang.Double.isNaN(positionX)) || (java.lang.Double.isNaN(positionY)) ) &&
	 *       	|			(this.getMinpositionX() < positionX) && (this.getMaxpositionX() > positionX) &&
	 *			|			(this.getMinpositionY() < positionY) && (this.getMaxpositionY() > positionY)      
	 */
	@Raw
	public boolean isValidPosition(double positionX, double positionY) {
		// A position has to be a number.
		if ( (java.lang.Double.isNaN(positionX)) || (java.lang.Double.isNaN(positionY)) )
			return false;
		if (!boundsSet) 
			return true;
		// A position cannot be smaller/bigger or equal to its lower and upper bounds.
		return (getMinPositionX() < positionX) && (getMaxPositionX() > positionX) &&
				(getMinPositionY() < positionY) && (getMaxPositionY() > positionY);
	}

}
