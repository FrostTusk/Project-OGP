package asteroids.model;

import be.kuleuven.cs.som.annotate.*;

// Helper class to store the data for positions
@Value
public class Position {
	 

	public Position(double positionX, double positionY) throws IllegalArgumentException {
		this.minPositionX = Double.NEGATIVE_INFINITY;
		this.maxPositionX = Double.POSITIVE_INFINITY;
		this.minPositionY = Double.NEGATIVE_INFINITY;
		this.maxPositionY = Double.POSITIVE_INFINITY;
		
		try {
			this.setPosition(positionX, positionY);
		}
		catch (IllegalArgumentException exc) {
			throw new IllegalArgumentException();
		}
	}
	
	public Position(double positionX, double positionY,
			double minPositionX, double maxPositionX, double minPositionY, double maxPositionY) throws IllegalArgumentException {
		this.minPositionX = minPositionX;
		this.maxPositionX = maxPositionX;
		this.minPositionY = minPositionY;
		this.maxPositionY = maxPositionY;
		
		try {
			this.setPosition(positionX, positionY);
		}
		catch (IllegalArgumentException exc) {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * Variable registering the X position of this ship.
	 */
	private double positionX;
	/**
	 * Variable registering the minimum X position of this ship.
	 */
	private final double minPositionX;
	/**
	 * Variable registering the maximum X position of this ship.
	 */
	private final double maxPositionX;
	/**
	 * Variable registering the Y position of this ship.
	 */
	private double positionY;
	/**
	 * Variable registering the minimum Y position of this ship.
	 */
	private final double minPositionY;
	/**
	 * Variable registering the maximum Y position of this ship.
	 */
	private final double maxPositionY;
	
	
	/**
	 * Return the X position of this ship.
	 */
	@Basic 
	public double getPositionX() {
		return this.positionX;
	}
	
	/**
	 * Return the minimum X position of this ship.
	 */
	@Basic @Immutable @Raw
	public double getMinPositionX() {
		return this.minPositionX;
	}
	
	/**
	 * Return the max X position of this ship.
	 */
	@Basic @Immutable @Raw 
	public double getMaxPositionX() {
		return this.maxPositionX;
	}
	
	/**
	 * Return the Y position of this ship.
	 */
	@Basic @Raw
	public double getPositionY() {
		return this.positionY;
	}
	
	/**
	 * Return the minimum Y position of this ship.
	 */
	@Basic @Immutable @Raw
	public double getMinPositionY() {
		return this.minPositionY;
	}
	
	/**
	 * Return the max Y position of this ship.
	 */
	@Basic @Immutable @Raw 
	public double getMaxPositionY() {
		return this.maxPositionY;
	}
	
	
	/**
	 * Check whether the given position is a valid position for
	 * any ship.
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
	private boolean isValidPosition(double positionX, double positionY) {
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
	 * Set the position of this ship to the given position.
	 * 
	 * @param  	positionX
	 *         	The new X position for this ship.
	 * @param  	positionY
	 * 		   	The new Y position for this ship.
	 * 
	 * @post   	The X position of this new ship is equal to
	 *         	the given X position.
	 *       	| new.getPositionX() == positionX
	 * @post   	The Y position of this new ship is equal to
	 *         	the given Y position.
	 *       	| new.getPositionY() == positionY
	 *       
	 * @throws 	IndexOutOfBoundsException
	 *         	The given position is not a valid position for any
	 *         	ship.
	 *       	| ! isValidPosition(getPositionX(), getPositionY())
	 */
	private void setPosition(double positionX, double positionY) 
			throws IllegalArgumentException {
		if (! isValidPosition(positionX, positionY))
			throw new IllegalArgumentException();
		this.positionX = positionX;
		this.positionY = positionY;
	}

	
}

