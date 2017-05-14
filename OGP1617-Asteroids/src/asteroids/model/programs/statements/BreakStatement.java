package asteroids.model.programs.statements;

import asteroids.model.programs.MyExpression;
import asteroids.model.programs.MyStatement;
import asteroids.part3.programs.SourceLocation;

public class BreakStatement implements MyStatement {

	public BreakStatement(SourceLocation location) {
		setLocation(location);
	}

	
	private SourceLocation location;
	
	
	@Override
	public SourceLocation getLocation() {
		return this.location;
	}
	
	
	private void setLocation(SourceLocation location) {
		this.location = location;
	}
	
	
	@Override
	public MyExpression execute() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
