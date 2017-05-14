package asteroids.model.programs.statements;

import java.util.List;
import asteroids.model.programs.MyExpression;
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
	public MyExpression execute() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
