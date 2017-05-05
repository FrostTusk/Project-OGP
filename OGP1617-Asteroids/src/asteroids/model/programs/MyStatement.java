package asteroids.model.programs;

import asteroids.helper.program.StatementType;
import asteroids.part3.programs.SourceLocation;

public abstract class MyStatement {
	
	private SourceLocation sourceLocation;
	private StatementType type;
	

	protected void setSourceLocation(SourceLocation sourceLocation) {
		this.sourceLocation = sourceLocation;
	}
	
	protected void setType(StatementType type) {
		this.type = type;
	}
	
	
	public SourceLocation getSourceLocation(SourceLocation sourceLocation) {
		return this.sourceLocation;
	}
	
	public StatementType getType() {
		return this.type;
	}
	
}
