package asteroids.model.programs;

import asteroids.helper.program.ExpressionType;
import asteroids.part3.programs.SourceLocation;

public abstract class MyExpression {
	
	private SourceLocation sourceLocation;
	private ExpressionType type;
	

	protected void setSourceLocation(SourceLocation sourceLocation) {
		this.sourceLocation = sourceLocation;
	}
	
	protected void setType(ExpressionType type) {
		this.type = type;
	}
	
	
	public SourceLocation getSourceLocation(SourceLocation sourceLocation) {
		return this.sourceLocation;
	}
	
	public ExpressionType getType() {
		return this.type;
	}
	
}
