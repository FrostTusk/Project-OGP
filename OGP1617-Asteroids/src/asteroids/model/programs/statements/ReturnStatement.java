package asteroids.model.programs.statements;

import asteroids.model.Program;
import asteroids.model.programs.MyExpression;
import asteroids.model.programs.MyStatement;
import asteroids.part3.programs.SourceLocation;

public class ReturnStatement implements MyStatement{
	
	public ReturnStatement(MyExpression value, SourceLocation location) {
		setLocation(location);
		setValue(value);
	}
	
	
	private MyExpression value;
	private SourceLocation location;
	
	
	public MyExpression getValue() {
		return this.value;
	}
	
	@Override
	public SourceLocation getLocation() {
		return this.location;
	}
	
	
	private void setValue(MyExpression value) {
		this.value = value;
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
	public void execute() {
		// TODO Auto-generated method stub
	}
	
}
