package asteroids.model.programs.statements;

import java.util.List;

import asteroids.model.Program;
import asteroids.model.programs.MyStatement;
import asteroids.part3.programs.SourceLocation;

public class SequenceStatement implements MyStatement {

	public SequenceStatement(List<MyStatement> statements, SourceLocation location) {
		setLocation(location);
		setStatements(statements);
	}
	
	
	private List<MyStatement> statements;
	private SourceLocation location;
	
	public List<MyStatement> getStatements() {
		return this.statements;
	}

	@Override
	public SourceLocation getLocation() {
		return this.location;
	}
	
	
	private void setStatements(List<MyStatement> statements) {
		this.statements = statements;
	}
	
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
