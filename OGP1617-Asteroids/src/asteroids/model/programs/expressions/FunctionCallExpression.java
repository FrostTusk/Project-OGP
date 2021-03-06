package asteroids.model.programs.expressions;

import java.util.ArrayList;
import java.util.List;

import asteroids.helper.ExitOutException;
import asteroids.model.programs.*;
import asteroids.part3.programs.SourceLocation;

@SuppressWarnings("rawtypes")
public class FunctionCallExpression implements MyExpression<Object> {
	
	public FunctionCallExpression(String functionName, List<MyExpression> actualArgs,
			SourceLocation location) {
		setFunctionName(functionName);
		setActualArgs(actualArgs);
		setLocation(location);
	}
	
	
	private String functionName;
	private List<MyExpression> actualArgs;
	private SourceLocation location;
	
	
	public String getFunctionName() {
		return this.functionName;
	}
	
	public List<MyExpression> getActualArgs() {
		return this.actualArgs;
	}
	
	public List<Object> getEvaluatedArgs() throws ExitOutException, IllegalArgumentException, IllegalStateException, NullPointerException {
		List<Object> result = new ArrayList<Object>();
		for (MyExpression expression: getActualArgs())
			result.add(expression.evaluate());
		return result;
	}
	
	@Override
	public SourceLocation getLocation() {
		return this.location;
	}
	
	
	private void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
	
	private void setActualArgs(List<MyExpression> actualArgs) {
		this.actualArgs = actualArgs;
	}
	
	private void setLocation(SourceLocation location) {
		this.location = location;
	}

	
	
	private MyStatement statement;

	
	@Override
	public MyStatement getStatement() {
		return this.statement;
	}
	
	
	public void setStatement(MyStatement statement) {
		this.statement = statement;
		for (MyExpression expression: getActualArgs())
			expression.setStatement(statement);
	}
	
	
	
	@Override
	public Object evaluate() throws ExitOutException, IllegalArgumentException, IllegalStateException, NullPointerException {
		MyFunction template = getStatement().getExecuter().getProgram().getFunction(getFunctionName());
		MyFunction function = new MyFunction(template.getFunctionName(), template.getBody(), template.getLocation());
		function.setProgram(getStatement().getExecuter().getProgram());
		return function.execute(getEvaluatedArgs());
	}

}
