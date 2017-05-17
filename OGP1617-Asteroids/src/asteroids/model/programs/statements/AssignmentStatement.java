package asteroids.model.programs.statements;

import asteroids.model.Program;
import asteroids.model.programs.MyExpression;
import asteroids.model.programs.MyStatement;
import asteroids.part3.programs.SourceLocation;

public class AssignmentStatement implements MyStatement {

	public AssignmentStatement(String variableName, MyExpression value, SourceLocation location) {
		setLocation(location);
		setVariableName(variableName);
		setCondition(value);
	}
	
	
	private String variableName;
	private MyExpression value;
	private SourceLocation location;
	
	
	public String getVariableName() {
		return this.variableName;
	}
	
	public MyExpression getValue() {
		return this.value;
	}

	@Override
	public SourceLocation getLocation() {
		return this.location;
	}
	
	
	private void setVariableName(String variableName) {
		this.variableName = variableName;
	}
	
	private void setCondition(MyExpression value) {
		this.value = value;
	}

	private void setLocation(SourceLocation location) {
		this.location = location;
	}

	
	@Override
	public void execute() {
		if (getSuperStatement() == null)
			getProgram().addGlobalVar(getVariableName(), getValue());
		else
			getProgram().addLocalVar(getVariableName(), getValue());
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
