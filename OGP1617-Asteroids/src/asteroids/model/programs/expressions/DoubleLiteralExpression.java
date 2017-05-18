package asteroids.model.programs.expressions;

import asteroids.model.programs.MyExpression;
import asteroids.model.programs.MyStatement;
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

	
	
	private MyStatement statement;

	
	@Override
	public MyStatement getStatement() {
		return this.statement;
	}
	
	
	public void setStatement(MyStatement statement) {
		this.statement = statement;
	}


	
	@Override
	public Double evaluate() {
		return getValue();
	}
	
}
