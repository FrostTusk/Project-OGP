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
	
	@Override
	public int getSize() {
		return statements.size();
	}
	
	
	private void setStatements(List<MyStatement> statements) {
		this.statements = statements;
	}
	
	private void setLocation(SourceLocation location) {
		this.location = location;
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
		for (MyStatement subStatement: getStatements())
			subStatement.setSuperStatement(statement);
		this.superStatement = statement;
	}
	
	

	@Override
	public void execute() throws IllegalStateException {
		if (getProgram().getFlag(getLocation())) return;
		setProgram(getProgram());
		for (MyStatement subStatement: getStatements()) {
			subStatement.execute();
			if (subStatement.getClass() == BreakStatement.class) break;
		}
		getProgram().flagLine(getLocation());
	}
	
}
