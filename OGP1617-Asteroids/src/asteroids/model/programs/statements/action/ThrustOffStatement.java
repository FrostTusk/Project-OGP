package asteroids.model.programs.statements.action;

import asteroids.helper.program.ActionType;
import asteroids.model.programs.statements.ActionStatement;
import asteroids.part3.programs.SourceLocation;

public class ThrustOffStatement extends ActionStatement {

	public ThrustOffStatement(SourceLocation location) {
		setLocation(location);
		setActionType(ActionType.THRUSTON);
	}
	
}
