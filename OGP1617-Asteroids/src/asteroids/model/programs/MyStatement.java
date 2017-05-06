package asteroids.model.programs;

import asteroids.helper.program.StatementType;
import asteroids.part3.programs.SourceLocation;

public abstract class MyStatement {
	
	private SourceLocation location;
	private StatementType type;
	

	protected void setLocation(SourceLocation location) {
		this.location = location;
	}
	
	protected void setType(StatementType type) {
		this.type = type;
	}
	
	
	public SourceLocation getLocation(SourceLocation location) {
		return this.location;
	}
	
	public StatementType getType() {
		return this.type;
	}
	
}
