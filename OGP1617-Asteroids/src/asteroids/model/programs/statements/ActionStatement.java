package asteroids.model.programs.statements;

import asteroids.helper.program.ActionType;
import asteroids.model.programs.MyExpression;
import asteroids.model.programs.MyFunction;
import asteroids.model.programs.MyStatement;
import asteroids.model.programs.Executable;
import asteroids.part3.programs.SourceLocation;

public class ActionStatement implements MyStatement {
	
	public ActionStatement(ActionType type, SourceLocation location) throws IllegalArgumentException {
		if (type == ActionType.TURN)
			throw new IllegalArgumentException();
		setActionType(type);
		setLocation(location);
	}
	
	public ActionStatement(ActionType type, MyExpression<Double> angle, SourceLocation location) 
			throws IllegalArgumentException {
		if (type != ActionType.TURN)
			throw new IllegalArgumentException();
		setActionType(type);
		setLocation(location);
		
		angle.setStatement(this);
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


	
	private Executable executer;
	private MyStatement superStatement;
	

	@Override
	public Executable getExecuter() {
		return this.executer;
	}

	@Override
	public MyStatement getSuperStatement() {
		return this.superStatement;
	}
	

	@Override
	public void setExecuter(Executable runner) {
		this.executer = runner;
	}

	@Override
	public void setSuperStatement(MyStatement statement) {
		this.superStatement = statement;
	}
	
	
	
	@Override
	public void requestFlag() {
		getExecuter().flagLine(getLocation());
	}

	@Override
	public void requestDeFlag() {
		getExecuter().deFlagLine(getLocation());
	}

	
	@Override
	public void execute() throws IllegalArgumentException, IllegalStateException {
		if (getExecuter().getFlag(getLocation())) return;
		setExecuter(getExecuter());
		if (MyFunction.class.isInstance(getExecuter()))
			throw new IllegalArgumentException();
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
		requestFlag();
	}

}
