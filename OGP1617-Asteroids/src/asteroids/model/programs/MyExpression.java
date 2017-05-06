package asteroids.model.programs;

import asteroids.helper.program.ExpressionType;
import asteroids.part3.programs.SourceLocation;

public abstract class MyExpression {
	
	private SourceLocation location;
	private ExpressionType type;
	

	protected void setLocation(SourceLocation location) {
		this.location = location;
	}
	
	protected void setType(ExpressionType type) {
		this.type = type;
	}
	
	
	public SourceLocation getLocation() {
		return this.location;
	}
	
	public ExpressionType getType() {
		return this.type;
	}
	
}
