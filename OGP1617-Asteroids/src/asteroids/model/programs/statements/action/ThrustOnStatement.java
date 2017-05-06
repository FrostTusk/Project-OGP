package asteroids.model.programs.statements.action;

import asteroids.helper.program.ActionType;
import asteroids.model.programs.statements.ActionStatement;
import asteroids.part3.programs.SourceLocation;

public class ThrustOnStatement extends ActionStatement {

	public ThrustOnStatement(SourceLocation location) {
		setSourceLocation(location);
		setActionType(ActionType.THRUSTON);
	}

}
