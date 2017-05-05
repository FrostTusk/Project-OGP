package asteroids.model.programs.statements;

import asteroids.helper.program.StatementType;
import asteroids.model.programs.MyExpression;
import asteroids.model.programs.MyStatement;
import asteroids.part3.programs.SourceLocation;

public class WhileStatement extends MyStatement {

	public WhileStatement(MyExpression condition, MyStatement body, SourceLocation sourceLocation) {
		setSourceLocation(sourceLocation);
		setType(StatementType.WHILE);
		setCondition(condition);
		setBody(body);
	}
	

	private MyExpression condition;
	private MyStatement body;
	
	
	public MyExpression getCondition() {
		return this.condition;
	}
	
	public MyStatement getBody() {
		return this.body;
	}

	
	private void setCondition(MyExpression condition) {
		this.condition = condition;
	}
	
	private void setBody(MyStatement body) {
		this.body = body;
	}
	
}
