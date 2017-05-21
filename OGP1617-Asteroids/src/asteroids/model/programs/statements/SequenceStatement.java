package asteroids.model.programs.statements;

import java.util.List;
import asteroids.model.programs.*;
import asteroids.part3.programs.SourceLocation;

public class SequenceStatement implements MyStatement {

	public SequenceStatement(List<MyStatement> statements, SourceLocation location) {
		setStatements(statements);
		setLocation(location);
		
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
		this.executer = executer;
		for (MyStatement subStatement: getStatements())
			subStatement.setExecuter(executer);
	}

	@Override
	public void setSuperStatement(MyStatement statement) {
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
		setExecuter(getExecuter());
		for (MyStatement subStatement: getStatements())
			subStatement.execute();
		requestFlag();
	}
	
}
