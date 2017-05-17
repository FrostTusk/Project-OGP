package asteroids.model.programs.statements;

import asteroids.model.Program;
import asteroids.model.programs.MyExpression;
import asteroids.model.programs.MyStatement;
import asteroids.part3.programs.SourceLocation;

public class WhileStatement implements MyStatement {

	public WhileStatement(MyExpression condition, MyStatement body, SourceLocation location) {
		setLocation(location);
		setCondition(condition);
		setBody(body);
	}
	

	@SuppressWarnings("unused") // FIXME
	private MyExpression condition;
	private MyStatement body;
	private SourceLocation location;
	
	
	public Boolean getCondition() {
//		return this.condition;
		return false;	// FIXME: obviously wrong.
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
	public void execute() {
		while (getCondition()) {
			body.execute();
		}
		// Flag all the lines.
	}

	
	private Program program;
	private MyStatement superStatement;
	
	@Override
	public Program getProgram() {
		return this.program;
	}

	@Override
	public void setProgram(Program program) {
		this.program = program;
	}

	@Override
	public MyStatement getSuperStatement() {
		return this.superStatement;
	}

	@Override
	public void setSuperStatement(MyStatement statement) {
		this.superStatement = statement;
	}
	
	
}
