package asteroids.model.programs.statements.action;

import asteroids.helper.program.ActionType;
import asteroids.model.programs.MyExpression;
import asteroids.model.programs.statements.ActionStatement;
import asteroids.part3.programs.SourceLocation;

public class TurnStatement extends ActionStatement {

	public TurnStatement(MyExpression angle, SourceLocation location) {
		setLocation(location);
		setActionType(ActionType.THRUSTON);
		setAngle(angle);
	}
	
	
	private MyExpression angle;
	
	
	public MyExpression getAngle() {
		return this.angle;
	}
	
	
	private void setAngle(MyExpression angle) {
		this.angle = angle;
	}
	
}
