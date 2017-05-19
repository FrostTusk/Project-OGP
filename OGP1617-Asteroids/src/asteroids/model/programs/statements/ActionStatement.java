package asteroids.model.programs.statements;

import asteroids.helper.program.ActionType;
import asteroids.model.programs.MyExpression;
import asteroids.model.programs.MyStatement;
import asteroids.model.programs.Executable;
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


	private Executable runner;
	private MyStatement superStatement;
	
	
	@Override
	public int getSize() {
		return 1;
	}

	@Override
	public Executable getExecuter() {
		return this.runner;
	}

	@Override
	public MyStatement getSuperStatement() {
		return this.superStatement;
	}
	

	@Override
	public void setExecuter(Executable runner) {
		this.runner = runner;
	}

	@Override
	public void setSuperStatement(MyStatement statement) {
		this.superStatement = statement;
	}
	
	

	@Override
	public void execute() throws IllegalStateException {
		if (getExecuter().getFlag(getLocation())) return;
		setExecuter(getExecuter());
		if (getExecuter().getTime() < 0.2)
			throw new IllegalStateException();
		switch (getActionType()) {
			case SHOOT:
				getExecuter().getOwner().fireBullet();
				break;
			case SKIP:
				break;
			case THRUSTOFF:
				getExecuter().getOwner().thrustOff();
				break;
			case THRUSTON:
				getExecuter().getOwner().thrustOn();
				break;
			case TURN:
				getExecuter().getOwner().turn(angle);
				break;
			default:
				throw new IllegalArgumentException();
		}
		getExecuter().setTime(getExecuter().getTime() - 0.2);
		getExecuter().flagLine(getLocation());
	}

}