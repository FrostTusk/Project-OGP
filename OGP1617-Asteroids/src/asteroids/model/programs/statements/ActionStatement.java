package asteroids.model.programs.statements;

import asteroids.helper.program.ActionType;
import asteroids.model.Program;
import asteroids.model.programs.MyExpression;
import asteroids.model.programs.MyStatement;
import asteroids.part3.programs.SourceLocation;

public class ActionStatement implements MyStatement {
	
	public ActionStatement(ActionType type, SourceLocation location) {
		if (type == ActionType.TURN)
			throw new IllegalArgumentException();
		setLocation(location);
		setActionType(type);
	}
	
	public ActionStatement(ActionType type, MyExpression<Double> angle, SourceLocation location) {
		if (type != ActionType.TURN)
			throw new IllegalArgumentException();
		setLocation(location);
		setActionType(type);
		this.angle = angle.evaluate();
	}
	

	private ActionType actionType;
	private double angle;
	private SourceLocation location;
	
	
	public ActionType getActionType() {
		return this.actionType;
	}
	

	@Override
	public SourceLocation getLocation() {
		return this.location;
	}

	protected void setActionType(ActionType actionType) {
		this.actionType = actionType;
	}


	
	private void setLocation(SourceLocation location) {
		this.location = location;
	}


	private Program program;
	private MyStatement superStatement;
	
	
	@Override
	public int getSize() {
		return 1;
	}

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
	public void execute() throws IllegalStateException {
		if (getProgram().getFlag(getLocation())) return;
		setProgram(getProgram());
		if (getProgram().getTime() < 0.2)
			throw new IllegalStateException();
		switch (getActionType()) {
			case SHOOT:
				getProgram().getOwner().fireBullet();
				break;
			case SKIP:
				break;
			case THRUSTOFF:
				getProgram().getOwner().thrustOff();
				break;
			case THRUSTON:
				getProgram().getOwner().thrustOn();
				break;
			case TURN:
				getProgram().getOwner().turn(angle);
				break;
			default:
				throw new IllegalArgumentException();
		}
		getProgram().setTime(getProgram().getTime() - 0.2);
		getProgram().flagLine(getLocation());
	}

}