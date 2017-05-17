package asteroids.model.programs.statements;

import asteroids.model.Program;
import asteroids.model.programs.MyExpression;
import asteroids.model.programs.MyStatement;
import asteroids.part3.programs.SourceLocation;

public class AssignmentStatement implements MyStatement {

	public AssignmentStatement(String variableName, MyExpression<Double> value, SourceLocation location) {
		setLocation(location);
		setVariableName(variableName);
		setValue(value);
	}
	
	
	private String variableName;
	private MyExpression<Double> value;
	private SourceLocation location;
	
	
	public String getVariableName() {
		return this.variableName;
	}
	
	public MyExpression<Double> getValue() {
		return this.value;
	}

	@Override
	public SourceLocation getLocation() {
		return this.location;
	}
	
	@Override
	public int getSize() {
		return 1;
	}
	
	
	private void setVariableName(String variableName) {
		this.variableName = variableName;
	}
	
	private void setValue(MyExpression<Double> value) {
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
		if (getProgram().getFlag(getLocation())) return;
		if (getSuperStatement() == null)
			getProgram().addGlobalVar(getVariableName(), getValue());
		else
			getProgram().addLocalVar(getVariableName(), getValue());
		getProgram().flagLine(getLocation());
	}

}
