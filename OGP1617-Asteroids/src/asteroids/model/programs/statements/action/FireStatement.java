package asteroids.model.programs.statements.action;

import asteroids.helper.program.ActionType;
import asteroids.model.programs.statements.ActionStatement;
import asteroids.part3.programs.SourceLocation;

public class FireStatement extends ActionStatement {

	public FireStatement(SourceLocation location) {
		setSourceLocation(location);
		setActionType(ActionType.THRUSTON);
	}
	
}
