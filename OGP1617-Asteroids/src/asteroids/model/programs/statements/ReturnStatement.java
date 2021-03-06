package asteroids.model.programs.statements;

import asteroids.helper.ExitOutException;
import asteroids.model.programs.*;
import asteroids.part3.programs.SourceLocation;

public class ReturnStatement <T> implements MyStatement{
	
	public ReturnStatement(MyExpression<T> value, SourceLocation location) throws NullPointerException {
		setValue(value);
		setLocation(location);
		
		getValue().setStatement(this);
	}
	
	
	private MyExpression<T> value;
	private SourceLocation location;
	
	
	public MyExpression<T> getValue() {
		return this.value;
	}
	
	@Override
	public SourceLocation getLocation() {
		return this.location;
	}
	
	
	private void setValue(MyExpression<T> value) {
		this.value = value;
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
	public void setExecuter(Executable executer) {
		this.executer = executer;
	}

	@Override
	public void setSuperStatement(MyStatement statement) {
		this.superStatement = statement;
	}

	
	
	@Override
	public void requestFlag() throws NullPointerException {
		getExecuter().flagLine(getLocation());
	}

	@Override
	public void requestDeFlag() throws NullPointerException {
		getExecuter().deFlagLine(getLocation());
	}
	
	
	@Override
	public void execute() throws ExitOutException, IllegalArgumentException, NullPointerException {
		if (getExecuter().getFlag(getLocation())) return;
		setExecuter(getExecuter());
		getExecuter().setReturn(getValue().evaluate());
		requestFlag();
		throw new ExitOutException();
	}
	
}
