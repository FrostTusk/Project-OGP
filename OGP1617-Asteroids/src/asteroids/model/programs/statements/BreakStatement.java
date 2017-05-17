package asteroids.model.programs.statements;

import asteroids.junk.TestException;
import asteroids.model.Program;
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
	
	
	private void setLocation(SourceLocation location) {
		this.location = location;
	}
	
	
	@Override
	public void execute() {
		if (getSuperStatement().getClass() != WhileStatement.class)
			throw new TestException(); // FIXME: Better exception
		// FIXME: InnerClass.
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
