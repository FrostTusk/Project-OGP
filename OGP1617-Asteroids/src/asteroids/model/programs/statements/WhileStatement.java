package asteroids.model.programs.statements;

import asteroids.model.programs.MyExpression;
import asteroids.model.programs.MyStatement;
import asteroids.part3.programs.SourceLocation;

public class WhileStatement implements MyStatement {

	public WhileStatement(MyExpression condition, MyStatement body, SourceLocation location) {
		setLocation(location);
		setCondition(condition);
		setBody(body);
	}
	

	private MyExpression condition;
	private MyStatement body;
	private SourceLocation location;
	
	
	public MyExpression getCondition() {
		return this.condition;
	}
	
	public MyStatement getBody() {
		return this.body;
	}

	@Override
	public SourceLocation getLocation() {
		return this.location;
	}
	
	
	private void setCondition(MyExpression condition) {
		this.condition = condition;
	}
	
	private void setBody(MyStatement body) {
		this.body = body;
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
