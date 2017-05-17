package asteroids.model.programs.statements;

import java.util.concurrent.TimeoutException;

import asteroids.helper.program.ActionType;
import asteroids.model.Program;
import asteroids.model.programs.MyStatement;
import asteroids.part3.programs.SourceLocation;

//public abstract class ActionStatement implements MyStatement {
//		
//		private ActionType actionType;
//		
//		
//		protected void setActionType(ActionType actionType) {
//			this.actionType = actionType;
//		}
//		
//		
//		public ActionType getActionType() {
//			return this.actionType;
//		}
//	
//}
public class ActionStatement implements MyStatement {
	
	public ActionStatement(SourceLocation location, ActionType type) {
		setLocation(location);
		setActionType(type);
	}
	
	public ActionStatement(SourceLocation location, double angle, ActionType type) {
		if (type != ActionType.TURN)
			throw new IllegalArgumentException();
		setLocation(location);
		setActionType(type);
	}
	
	
	private SourceLocation location;

	
	private void setLocation(SourceLocation location) {
		this.location = location;
	}
	
	private ActionType actionType;
	
	
	protected void setActionType(ActionType actionType) {
		this.actionType = actionType;
	}
	
	
	public ActionType getActionType() {
		return this.actionType;
	}

	@Override
	public SourceLocation getLocation() {
		return this.location;
	}

	@Override
	public int getSize() {
		return 1;
	}


	
	private Program program;
	private MyStatement superStatement;
	
	
	@Override
	public Program getProgram() {
		return this.program;
	}

	@Override
	public MyStatement getSuperStatement() {
		return this.superStatement;
	}
	

	@Override
	public void setProgram(Program program) {
		this.program = program;
	}

	@Override
	public void setSuperStatement(MyStatement statement) {
		this.superStatement = statement;
	}
	
	

	@Override
	public void execute() throws TimeoutException {
		if (getProgram().getTime() < 0.2)
			throw new TimeoutException(); // FIXME
		switch (getActionType()) {
			case SHOOT:
				getProgram().getOwner().fireBullet();
			case SKIP: /* Do Nothing */ ;
			case THRUSTOFF:
				getProgram().getOwner().thrustOff();
			case THRUSTON:
				getProgram().getOwner().thrustOn();
			case TURN:
				
		}
		getProgram().setTime(getProgram().getTime() - 0.2);
		getProgram().flagLine(getLocation());
	}

}