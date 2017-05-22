package asteroids.model.programs.statements;

import asteroids.model.Program;
import asteroids.model.programs.Executable;
import asteroids.model.programs.MyExpression;
import asteroids.model.programs.MyStatement;
import asteroids.part3.programs.SourceLocation;

public class AssignmentStatement <T> implements MyStatement {

	public AssignmentStatement(String variableName, MyExpression<T> value, SourceLocation location) 
		throws NullPointerException {
		setVariableName(variableName);
		setValue(value);
		setLocation(location);
		
		getValue().setStatement(this);
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
	
	
	private void setVariableName(String variableName) {
		this.variableName = variableName;
	}
	
	private void setValue(MyExpression<T> value) {
		this.value = value;
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
	}

	@Override
	public void setSuperStatement(MyStatement statement) {
		this.superStatement = statement;
	}
	

	
	@Override
	public void requestFlag() throws NullPointerException {
		getExecuter().flagLine(getLocation());
	}

	@Override
	public void requestDeFlag() throws NullPointerException {
		getExecuter().deFlagLine(getLocation());
	}
	
	
	@Override
	public void execute() throws IllegalArgumentException, NullPointerException {	
		if (getExecuter().getFlag(getLocation())) return;
		setExecuter(getExecuter());
		
		if ( (!getExecuter().canHaveAsName(getVariableName())) && (getExecuter() instanceof Program) )
			throw new IllegalArgumentException();
		
		Object localVar, globalVar;
		try {
			localVar = getExecuter().getLocalVar(getVariableName()); 
			if (!localVar.getClass().isInstance(getValue().evaluate()))
				throw new IllegalArgumentException();
		}
		catch (NullPointerException exc1) {
			try {
				globalVar = getExecuter().getGlobalVar(getVariableName());
				if (!globalVar.getClass().isInstance(getValue().evaluate()))
						throw new IllegalArgumentException();
			}
			catch (NullPointerException exc2) {}
		}
		
		getExecuter().addVar(getVariableName(), getValue().evaluate());
		requestFlag();
	}

}
