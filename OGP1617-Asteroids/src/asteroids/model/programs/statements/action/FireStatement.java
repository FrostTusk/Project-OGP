package asteroids.model.programs.statements.action;

import asteroids.helper.program.ActionType;
import asteroids.model.programs.MyExpression;
import asteroids.model.programs.statements.ActionStatement;
import asteroids.part3.programs.SourceLocation;

public class FireStatement extends ActionStatement {

	public FireStatement(SourceLocation location) {
		setLocation(location);
		setActionType(ActionType.THRUSTON);
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
