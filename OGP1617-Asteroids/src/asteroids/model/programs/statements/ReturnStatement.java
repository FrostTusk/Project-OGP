package asteroids.model.programs.statements;

import asteroids.model.programs.Executable;
import asteroids.model.programs.MyExpression;
import asteroids.model.programs.MyStatement;
import asteroids.part3.programs.SourceLocation;

public class ReturnStatement <T> implements MyStatement{
	
	public ReturnStatement(MyExpression<T> value, SourceLocation location) {
		value.setStatement(this);
		setLocation(location);
		setValue(value);
	}
	
	
	private MyExpression<T> value;
	private SourceLocation location;
	
	
	public MyExpression<T> getValue() {
		return this.value;
	}
	
	@Override
	public SourceLocation getLocation() {
		return this.location;
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
	public int getSize() {
		return 1;
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
		setExecuter(getExecuter());
		getExecuter().flagLine(getLocation()); // TODO Return a value.
	}
	
}
