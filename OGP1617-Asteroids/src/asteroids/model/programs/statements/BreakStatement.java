package asteroids.model.programs.statements;

import asteroids.helper.ExitOutException;
import asteroids.model.programs.*;
import asteroids.part3.programs.SourceLocation;

public class BreakStatement implements MyStatement {

	public BreakStatement(SourceLocation location) {
		setLocation(location);
	}

	
	private SourceLocation location;
	
	
	@Override
	public SourceLocation getLocation() {
		return this.location;
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
		MyStatement superStatement = getSuperStatement();
		while (!WhileStatement.class.isInstance(superStatement)) {
			superStatement = superStatement.getSuperStatement();
			if ( (superStatement == null) && (MyFunction.class.isInstance(getExecuter())) )
				throw new ExitOutException();
			if (superStatement == null)
				throw new IllegalArgumentException();
		}
		requestFlag();
		throw new ExitOutException();
	}

}
