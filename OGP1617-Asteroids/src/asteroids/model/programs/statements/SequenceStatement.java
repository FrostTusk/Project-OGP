package asteroids.model.programs.statements;

import java.util.List;

import asteroids.helper.program.StatementType;
import asteroids.model.programs.MyStatement;
import asteroids.part3.programs.SourceLocation;

public class SequenceStatement extends MyStatement {

	public SequenceStatement(List<MyStatement> statements, SourceLocation sourceLocation) {
		setSourceLocation(sourceLocation);
		setType(StatementType.SEQUENCE);
		setStatements(statements);
	}
	
	
	private List<MyStatement> statements;
	
	
	public List<MyStatement> getStatements() {
		return this.statements;
	}

	
	private void setStatements(List<MyStatement> statements) {
		this.statements = statements;
	}
	
}
