package asteroids.model.programs.statements;

import asteroids.model.Program;
import asteroids.model.programs.MyExpression;
import asteroids.model.programs.MyStatement;
import asteroids.part3.programs.SourceLocation;

public class AssignmentStatement <T> implements MyStatement {

	public AssignmentStatement(String variableName, MyExpression<T> value, SourceLocation location) {
		setLocation(location);
		setVariableName(variableName);
		setValue(value);
	}
	
	
	private String variableName;
	private MyExpression<T> value;
	private SourceLocation location;
	
	
	public String getVariableName() {
		return this.variableName;
	}
	
	public T getValue() {
		return this.value.evaluate();
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
	
	private void setValue(MyExpression<T> value) {
		this.value = value;
		value.setStatement(this);
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
		Object value = getValue();
		if (value.getClass().toString() == String.class.toString()) {
			value = getProgram().getLocalVar((String) getValue());
			if (value == null)
				value = getProgram().getGlobalVar((String) getValue());
			if (value == null)
				throw new IllegalArgumentException();
		}
		if (getSuperStatement() == null)
			getProgram().addGlobalVar(getVariableName(), value);
		else
			getProgram().addLocalVar(getVariableName(), value);
		getProgram().flagLine(getLocation());
	}

}
