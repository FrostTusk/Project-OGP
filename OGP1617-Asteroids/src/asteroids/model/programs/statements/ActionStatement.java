package asteroids.model.programs.statements;

import asteroids.helper.program.ActionType;
import asteroids.junk.TestException;
import asteroids.model.Program;
import asteroids.model.Ship;
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

	private Ship ship;
	
	
	protected void setShip(Ship ship) {
		this.ship = ship;
	}
	
	
	public Ship getShip() {
		return this.ship;
	}
	
	@Override
	public SourceLocation getLocation() {
		return this.location;
	}


	@Override
	public void execute() {
		if (getProgram().getTime() < 0.2)
			throw new TestException(); // FIXME
		switch (getActionType()) {
			case SHOOT:
				getShip().fireBullet();
			case SKIP:;
			case THRUSTOFF:
				getShip().thrustOff();
			case THRUSTON:
				getShip().thrustOn();
			case TURN:
				
		}
		getProgram().flagLine(getLocation());
	}


	private Program program;
	private MyStatement superStatement;
	
	@Override
	public Program getProgram() {
		return this.program;
	}

	@Override
	public void setProgram(Program program) {
		this.program = program;
	}

	@Override
	public MyStatement getSuperStatement() {
		return this.superStatement;
	}

	@Override
	public void setSuperStatement(MyStatement statement) {
		this.superStatement = statement;
	}

}