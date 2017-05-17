package asteroids.model.programs;

import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public interface MyStatement {
	
//	private SourceLocation location;
//	private StatementType type;
//	
//
//	protected void setLocation(SourceLocation location) {
//		this.location = location;
//	}
//	
//	protected void setType(StatementType type) {
//		this.type = type;
//	}
//	
//	
//	public SourceLocation getLocation(SourceLocation location) {
//		return this.location;
//	}
//	
//	public StatementType getType() {
//		return this.type;
//	}
	
	public SourceLocation getLocation();
	public Program getProgram();
	public MyStatement getSuperStatement();
	public int getSize();
	public void setProgram(Program program);
	public void setSuperStatement(MyStatement statement);
	public void execute() throws IllegalStateException, IllegalArgumentException;
}
