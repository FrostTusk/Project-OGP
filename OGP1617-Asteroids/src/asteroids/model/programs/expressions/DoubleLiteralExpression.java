package asteroids.model.programs.expressions;

import asteroids.model.programs.MyExpression;
import asteroids.part3.programs.SourceLocation;

public class DoubleLiteralExpression implements MyExpression<Double> {

	public DoubleLiteralExpression(double value, SourceLocation location) {
		setLocation(location);
		setValue(value);
	}
	
	
	private double value;
	private SourceLocation location;
	
	
	public double getValue() {
		return this.value;
	}
	
	@Override
	public SourceLocation getLocation() {
		return this.location;
	}
	
	
	private void setValue(double value) {
		this.value = value;
	}
	
	private void setLocation(SourceLocation location) {
		this.location = location;
	}


	@Override
	public Double evaluate() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
