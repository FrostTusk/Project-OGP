package asteroids.model.programs.statements;

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
	
	
	private MyExpression condition;
	private MyStatement ifBody;
	private MyStatement elseBody;
	private SourceLocation location;
	
	
	public MyExpression getCondition() {
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
