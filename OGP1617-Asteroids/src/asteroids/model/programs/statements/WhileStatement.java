package asteroids.model.programs.statements;

import asteroids.helper.ExitOutException;
import asteroids.model.programs.*;
import asteroids.part3.programs.SourceLocation;

public class WhileStatement implements MyStatement {

	public WhileStatement(MyExpression<Boolean> condition, MyStatement body, SourceLocation location) {
		setCondition(condition);
		setBody(body);
		setLocation(location);
		
		getCondition().setStatement(this);
		getBody().setSuperStatement(this);
	}
	

	private MyExpression<Boolean> condition;
	private MyStatement body;
	private SourceLocation location;
	
	
	public MyExpression<Boolean> getCondition() {
		return this.condition;
	}

	public MyStatement getBody() {
		return this.body;
	}

	@Override
	public SourceLocation getLocation() {
		return this.location;
	}
	
	
	private void setCondition(MyExpression<Boolean> condition) {
		this.condition = condition;
	}
	
	private void setBody(MyStatement body) {
		this.body = body;
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
		getBody().setExecuter(executer);
	}

	@Override
	public void setSuperStatement(MyStatement statement) {
		this.superStatement = statement;
	}

	
	
	@Override
	public void requestFlag() {
		getExecuter().flagLine(location);	
		getBody().requestFlag();
	}


	@Override
	public void requestDeFlag() {
		getExecuter().deFlagLine(location);
		getBody().requestDeFlag();
	}
	
	
	@Override
	public void execute() throws IllegalStateException {
		if (getExecuter().getFlag(getLocation())) return;
		setExecuter(getExecuter());
		while (getCondition().evaluate()) {
			try {
				getBody().execute();
			}
			catch (ExitOutException exc) {
				break;
			}
			getBody().requestDeFlag();
		}
		requestFlag();
	}

}
