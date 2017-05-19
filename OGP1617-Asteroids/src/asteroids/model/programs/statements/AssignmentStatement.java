package asteroids.model.programs.statements;

import asteroids.model.programs.Executable;
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
	
	public MyExpression<T> getValue() {
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
	
	private void setValue(MyExpression<T> value) {
		this.value = value;
		value.setStatement(this);
	}

	private void setLocation(SourceLocation location) {
		this.location = location;
	}

	
	
	private Executable executer;
	private MyStatement superStatement;


	@Override
	public Executable getExecuter() {
		return this.executer;
	}

	@Override
	public MyStatement getSuperStatement() {
		return this.superStatement;
	}
	

	@Override
	public void setExecuter(Executable executer) {
		this.executer = executer;
		getValue().setStatement(this);
	}

	@Override
	public void setSuperStatement(MyStatement statement) {
		this.superStatement = statement;
	}
	

	
	@Override
	public void execute() {
		if (getExecuter().getFlag(getLocation())) return;
//		if (getSuperStatement() == null)
//			getExecuter().addGlobalVar(getVariableName(), getValue().evaluate());
//		else
//			getExecuter().addLocalVar(getVariableName(), getValue().evaluate());
		getExecuter().addVar(getVariableName(), getValue().evaluate());
		getExecuter().flagLine(getLocation());
	}

}
