package asteroids.model.programs.statements;

import asteroids.helper.program.StatementType;
import asteroids.model.programs.MyExpression;
import asteroids.model.programs.MyStatement;
import asteroids.part3.programs.SourceLocation;

public class ReturnStatement extends MyStatement{
	
	public ReturnStatement(MyExpression value, SourceLocation location) {
		setLocation(location);
		setType(StatementType.RETURN);
		setValue(value);
	}
	
	
	private MyExpression value;
	
	
	public MyExpression getValue() {
		return this.value;
	}
	
	
	private void setValue(MyExpression value) {
		this.value = value;
	}
	
}
