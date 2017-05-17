package asteroids.model.programs.statements;

import java.util.concurrent.TimeoutException;

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
	
	@Override
	public int getSize() {
		return body.getSize() + 1;
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
		if (getProgram().getFlag(getLocation())) return;
		while (getCondition())
			body.execute();
		getProgram().flagLine(getLocation());
	}
	
}
