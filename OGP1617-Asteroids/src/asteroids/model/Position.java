package asteroids.model;

// Helper class to store the data for positions
public class Position {
	 

	public Position(double positionX, double positionY) {
	    this.positionX = positionX;
	    this.positionY = positionY;
	}
	
	
	private double positionX;
	private double positionY;
	
	
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
	 * Set the position of this Position to the given position.
	 * 
	 * @param  	positionX
	 *         	The new X position.
	 * @param  	positionY
	 * 		   	The new Y position.
	 * 
	 * @post   	The X position is equal to
	 *         	the given X position.
	 *       	| new.getPositionX() == positionX
	 * @post   	The Y position is equal to
	 *         	the given Y position.
	 *       	| new.getPositionY() == positionY
	 *       
	 * @throws 	IndexOutOfBoundsException
	 *         	The given position is not a valid position.
	 *       	| ! isValidPosition(getPositionX(), getPositionY())
	 */
	public void setPosition(double positionX, double positionY) 
			throws IllegalArgumentException {
		if (! isValidPosition(positionX, positionY))
			throw new IllegalArgumentException();
		this.positionX = positionX;
		this.positionY = positionY;
	}
	
	
}

