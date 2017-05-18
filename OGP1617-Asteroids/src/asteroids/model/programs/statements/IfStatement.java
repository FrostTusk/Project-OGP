package asteroids.model.programs.statements;

import asteroids.model.Program;
import asteroids.model.programs.MyExpression;
import asteroids.model.programs.MyStatement;
import asteroids.part3.programs.SourceLocation;

public class IfStatement implements MyStatement {

	public IfStatement(MyExpression<Boolean> condition, MyStatement ifBody, MyStatement elseBody,
			SourceLocation location) {
		setLocation(location);
		setCondition(condition);
		setIfBody(ifBody);
		setElseBody(elseBody);
	}
	
	
	private MyExpression<Boolean> condition;
	private MyStatement ifBody;
	private MyStatement elseBody;
	private SourceLocation location;
	
	
	public Boolean getCondition() {
		return condition.evaluate();
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
	
	@Override
	public int getSize() {
		if (getElseBody() == null)
			return getIfBody().getSize() + 1;
		return ifBody.getSize() + elseBody.getSize() + 1;
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
		this.superStatement = statement;
	}
	
	

	@Override
	public void execute() throws IllegalStateException {
		if (getProgram().getFlag(getLocation())) return;
		if (getCondition()) {
			getIfBody().execute();	// FIXME how to flag else body.
		}
		else if (getElseBody() != null) {
			getElseBody().execute();
		}
		getProgram().flagLine(getLocation());
	}
	
}
