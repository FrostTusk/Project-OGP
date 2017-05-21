package asteroids.model.programs.statements;

import asteroids.model.programs.*;
import asteroids.part3.programs.SourceLocation;

public class IfStatement implements MyStatement {

	public IfStatement(MyExpression<Boolean> condition, MyStatement ifBody, MyStatement elseBody,
			SourceLocation location) {
		setCondition(condition);
		setIfBody(ifBody);
		setElseBody(elseBody);
		setLocation(location);
		
		getCondition().setStatement(this);
		getIfBody().setSuperStatement(this);
		if (getElseBody() != null)
			getElseBody().setSuperStatement(this);
	}
	
	
	private MyExpression<Boolean> condition;
	private MyStatement ifBody;
	private MyStatement elseBody;
	private SourceLocation location;
	
	
	public MyExpression<Boolean> getCondition() {
		return this.condition;
	}
	
	public MyStatement getIfBody() {
		return this.ifBody;
	}
	
	public MyStatement getElseBody() {
		return this.elseBody;
	}
	
	@Override
	public SourceLocation getLocation() {
		return this.location;
	}
	

	private void setCondition(MyExpression<Boolean> condition) {
		this.condition = condition;
	}
	
	private void setIfBody(MyStatement ifBody) {
		this.ifBody = ifBody;
	}
	
	private void setElseBody(MyStatement elseBody) {
		this.elseBody = elseBody;
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
		getIfBody().setExecuter(executer);
		if (getElseBody() != null)
			getElseBody().setExecuter(executer);
	}

	@Override
	public void setSuperStatement(MyStatement statement) {
		this.superStatement = statement;
	}
	
	
	
	@Override
	public void requestFlag() {
		getExecuter().flagLine(getLocation());
		getIfBody().requestFlag();
		if (getElseBody() != null)
			getElseBody().requestFlag();
	}

	@Override
	public void requestDeFlag() {
		getExecuter().deFlagLine(getLocation());
		getIfBody().requestDeFlag();
		if (getElseBody() != null)
			getElseBody().requestDeFlag();
	}
	
	
	@Override
	public void execute() throws IllegalStateException {
		if (getExecuter().getFlag(getLocation())) return;
		setExecuter(getExecuter());
		if (getCondition().evaluate())
			getIfBody().execute();
		else if (getElseBody() != null)
			getElseBody().execute();
		requestFlag();
	}
	
}
