package asteroids.model.programs;

import java.util.*;
import asteroids.helper.program.*;
import asteroids.model.*;
import asteroids.model.programs.statements.*;

public class ProgramExecuter {
	
	public ProgramExecuter(Ship ship, Program program, double time) {
		setShip(ship);
		setProgram(program);
		setTime(time);
		executeProgram();
	}

	
	private Ship ship;
	private Program program;
	private double time;
	
	
	public Ship getShip() {
		return this.ship;
	}
	
	public Program getProgram() {
		return this.program;
	}
	
	public double getTime() {
		return this.time;
	}
	
	
	private void setShip(Ship ship) {
		this.ship = ship;
	}
	
	private void setProgram(Program program) {
		this.program = program;
	}
	
	private void setTime(double time) {
		this.time = time;
	}

	
	
	private Map<String, MyExpression> globalVariableMap;
	
	
	public MyExpression getGlobalVariableExpression(String variable) {
		try {
			return globalVariableMap.get(variable);
		}
		catch (NullPointerException exc) {
			return null;
		}
	}
	

	public boolean variableCanHaveAsValue(MyExpression oldValue, MyExpression newValue) {
		return (oldValue.getType() == newValue.getType()) ? true:false;
	}
	
	public boolean containsGlobalVariable(String variable) {
		try {
			globalVariableMap.get(variable);
		}
		catch (NullPointerException exc) {
			return false;
		}
		return true;
	}

	
	public void addGlobalVariable(String variable, MyExpression expression) {
		globalVariableMap.put(variable, expression);
	}
	
	
	
	
	private void executeProgram() {
		Stack<Object> stack = new Stack<Object>();
		statementHandler(getProgram().getMain(), null, null, stack);
	}	
	
	private boolean statementHandler(MyStatement statement, MyStatement enclosingStatement, 
			MyFunction enclosingFunction, Stack<Object> stack) {
		StatementType type = statement.getType();
		boolean getNext = true;
		switch (type) {
        	case ACTION: 
        		getNext = runActionStatement(statement);
        	case ASSIGNMENT: 
        		getNext = runAssignment((AssignmentStatement) statement);;
        	case BREAK: 
        		getNext = runBreak((BreakStatement) statement);
        	case IF: break;
        	case PRINT: 
        		getNext = runPrint((PrintStatement) statement);
        	case RETURN: 
        		getNext = runReturn((ReturnStatement) statement, stack);
        	case SEQUENCE: 
        		getNext = runSequence((SequenceStatement) statement, enclosingStatement, enclosingFunction, stack);
        	case WHILE: break;
        	default: break;
		}
		return getNext;
	}
	
	
	private boolean runActionStatement(MyStatement statement) {
		return true;
	}
	
	
	private boolean runAssignment(AssignmentStatement statement) throws IllegalArgumentException {
		String variable = statement.getVariableName();
		if ( containsGlobalVariable(variable) && 
				(!variableCanHaveAsValue(getGlobalVariableExpression(variable), statement.getValue())) )
			throw new IllegalArgumentException();
		
		addGlobalVariable(variable, statement.getValue());
		return true;
	}
	
	
	private boolean runBreak(BreakStatement statement) {
		return false;
	}
	
	
	private boolean runPrint(PrintStatement statement) {
		System.out.println(statement.getValue().toString());
		return true;
	}
	
	
	private boolean runReturn(ReturnStatement statement, Stack<Object> stack) {
		stack.add(statement.getValue());
		return false;
	}
	
	
	private boolean runSequence(SequenceStatement statement, MyStatement enclosingStatement, 
			MyFunction enclosingFunction, Stack<Object> stack) {
		for(MyStatement subStatement: statement.getStatements())
			if (!statementHandler(subStatement, enclosingStatement, enclosingFunction, stack))
				return false;
		return true;
	}
	
	
	
	private void expressionHandler(MyExpression expression) {
		ExpressionType type = expression.getType();
		switch (type) {
			case CHANGESIGN:;
			case DOUBLELITERAL:;
			case ENTITY:;
			case FUNCTIONCALL:;
			case GETTER:;
			case NOT:;
			case OPERATOR:;
			case PARAMETER:;
			case SQRT:;
			case VARIABLE:;
		}
	}
	
	
	private void extractValue(MyExpression expression) {
		
	}
	
}
