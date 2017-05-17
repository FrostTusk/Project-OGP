package asteroids.model.programs.statements;

import java.util.concurrent.TimeoutException;

import asteroids.model.Program;
import asteroids.model.programs.MyExpression;
import asteroids.model.programs.MyStatement;
import asteroids.part3.programs.SourceLocation;

public class IfStatement implements MyStatement {

	public IfStatement(MyExpression condition, MyStatement ifBody, MyStatement elseBody,
			SourceLocation location) {
		setLocation(location);
		setCondition(condition);
		setIfBody(ifBody);
		setElseBody(elseBody);
	}
	
	
	@SuppressWarnings("unused")
	private MyExpression condition; // FIXME
	private MyStatement ifBody;
	private MyStatement elseBody;
	private SourceLocation location;
	
	
	public Boolean getCondition() {
//		return this.condition;	// FIXME
		return false;
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
		return 0;
	}
	

	private void setCondition(MyExpression condition) {
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
	public void execute() throws TimeoutException {
		if (getCondition()) {
			getIfBody().execute();
		}
		else if (getElseBody() != null) {
			getElseBody().execute();
		}
		getProgram().flagLine(getLocation());
	}
	
}
