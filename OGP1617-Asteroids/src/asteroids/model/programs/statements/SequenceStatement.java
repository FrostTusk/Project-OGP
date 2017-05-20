package asteroids.model.programs.statements;

import java.util.List;
import asteroids.model.programs.Executable;
import asteroids.model.programs.MyStatement;
import asteroids.part3.programs.SourceLocation;

public class SequenceStatement implements MyStatement {

	public SequenceStatement(List<MyStatement> statements, SourceLocation location) {
		setLocation(location);
		setStatements(statements);
		
		for (MyStatement statement: getStatements())
			statement.setSuperStatement(this);
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
		for (MyStatement subStatement: getStatements())
			subStatement.setExecuter(executer);
		this.executer = executer;
	}

	@Override
	public void setSuperStatement(MyStatement statement) {
		for (MyStatement subStatement: getStatements())
			subStatement.setSuperStatement(statement);
		this.superStatement = statement;
	}
	
	
	@Override
	public void requestFlag() {
		getExecuter().flagLine(getLocation());
		for (MyStatement statement: getStatements())
			statement.requestFlag();
	}

	@Override
	public void requestDeFlag() {
		getExecuter().deFlagLine(getLocation());
		for (MyStatement statement: getStatements())
			statement.requestDeFlag();
	}
	
	@Override
	public void execute() throws IllegalStateException {
//		if (getExecuter().getFlag(getLocation())) return;
		setExecuter(getExecuter());
		for (MyStatement subStatement: getStatements())
			subStatement.execute();
		requestFlag();
	}


	
}
