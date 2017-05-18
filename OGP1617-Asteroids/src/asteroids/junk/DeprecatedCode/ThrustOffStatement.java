package asteroids.junk.DeprecatedCode;

import asteroids.helper.program.ActionType;
import asteroids.model.Program;
import asteroids.model.programs.MyStatement;
import asteroids.model.programs.statements.ActionStatement;
import asteroids.part3.programs.SourceLocation;

public class ThrustOffStatement extends ActionStatement {

	public ThrustOffStatement(SourceLocation location) {
		super(ActionType.SHOOT, location);
//		setLocation(location);
		setActionType(ActionType.THRUSTON);
	}

	
	private SourceLocation location;
	
	
	@Override
	public SourceLocation getLocation() {
		return this.location;
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
