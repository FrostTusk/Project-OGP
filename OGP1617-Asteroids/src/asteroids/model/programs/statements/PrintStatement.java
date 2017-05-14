package asteroids.model.programs.statements;

import asteroids.model.programs.MyExpression;
import asteroids.model.programs.MyStatement;
import asteroids.part3.programs.SourceLocation;

public class PrintStatement implements MyStatement {

	public PrintStatement(MyExpression value, SourceLocation location) {
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


	@Override
	public MyExpression execute() {
		// TODO Auto-generated method stub
		return null;
	}


}
