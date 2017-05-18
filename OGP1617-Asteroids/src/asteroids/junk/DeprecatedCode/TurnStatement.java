package asteroids.junk.DeprecatedCode;

import asteroids.helper.program.ActionType;
import asteroids.model.Program;
import asteroids.model.programs.MyExpression;
import asteroids.model.programs.MyStatement;
import asteroids.model.programs.statements.ActionStatement;
import asteroids.part3.programs.SourceLocation;

@SuppressWarnings("all")
public class TurnStatement extends ActionStatement {

	public TurnStatement(MyExpression angle, SourceLocation location) {
		super(location, ActionType.SHOOT);
//		setLocation(location);
		setActionType(ActionType.THRUSTON);
		setAngle(angle);
	}
	
	
	private MyExpression angle;
	private SourceLocation location;
	
	
	public MyExpression getAngle() {
		return this.angle;
	}
	
	@Override
	public SourceLocation getLocation() {
		return this.location;
	}
	
	
	private void setAngle(MyExpression angle) {
		this.angle = angle;
	}

	@SuppressWarnings("unused")
	private void setLocation(SourceLocation location) {
		this.location = location;
	}


	@Override
	public void execute() {
		// TODO Auto-generated method stub
	}

	@Override
	public Program getProgram() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setProgram(Program program) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public MyStatement getSuperStatement() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setSuperStatement(MyStatement statement) {
		// TODO Auto-generated method stub
		
	}
	
}
