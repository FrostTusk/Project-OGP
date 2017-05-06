package asteroids.model.programs.statements;

import asteroids.helper.program.StatementType;
import asteroids.model.programs.MyExpression;
import asteroids.model.programs.MyStatement;
import asteroids.part3.programs.SourceLocation;

public class IfStatement extends MyStatement {

	public IfStatement(MyExpression condition, MyStatement ifBody, MyStatement elseBody,
			SourceLocation location) {
		setLocation(location);
		setType(StatementType.IF);
		setCondition(condition);
		setIfBody(ifBody);
		setElseBody(elseBody);
	}
	
	
	private MyExpression condition;
	private MyStatement ifBody;
	private MyStatement elseBody;
	
	
	public MyExpression getCondition() {
		return this.condition;
	}
	
	public MyStatement getIfBody() {
		return this.ifBody;
	}
	
	public MyStatement getElseBody() {
		return this.elseBody;
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
	
}
