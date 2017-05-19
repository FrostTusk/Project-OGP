package asteroids.model.programs.statements;

import asteroids.model.programs.Executable;
import asteroids.model.programs.MyStatement;
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
	
	@Override
	public int getSize() {
		return 1;
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
	public void execute() {
		if (getExecuter().getFlag(getLocation())) return;
		if (getSuperStatement().getClass() != WhileStatement.class)
			throw new RuntimeException(); 
		//TODO: inner class
	}
	
}
