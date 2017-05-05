package asteroids.model.programs.statements;

import asteroids.helper.program.StatementType;
import asteroids.model.programs.MyExpression;
import asteroids.model.programs.MyStatement;
import asteroids.part3.programs.SourceLocation;

public class AssignmentStatement extends MyStatement {

	public AssignmentStatement(String variableName, MyExpression value, SourceLocation sourceLocation) {
		setSourceLocation(sourceLocation);
		setType(StatementType.ASSIGNMENT);
		setVariableName(variableName);
		setCondition(value);
	}
	
	
	private String variableName;
	private MyExpression value;
	
	
	public String getVariableName() {
		return this.variableName;
	}
	
	public MyExpression getValue() {
		return this.value;
	}

	
	private void setVariableName(String variableName) {
		this.variableName = variableName;
	}
	
	private void setCondition(MyExpression value) {
		this.value = value;
	}
	
}
