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
		statementHandler(getProgram().getMain());
	}	
	
	private boolean statementHandler(MyStatement statement) {
		StatementType type = statement.getType();
		switch (type) {
        	case ACTION: break;
        	case ASSIGNMENT: runAssignment((AssignmentStatement) statement);;
        	case BREAK: runBreak((BreakStatement) statement);
        	case IF: break;
        	case PRINT: runPrint((PrintStatement) statement);
        	case RETURN: break;
        	case SEQUENCE: runSequence((SequenceStatement) statement);;
        	case WHILE: break;
        	default: break;
		}
		return true;
	}
	
	private void runActionStatement(MyStatement statement) {
		
	}
	
	
	private void runAssignment(AssignmentStatement statement) throws IllegalArgumentException {
		String variable = statement.getVariableName();
		if ( containsGlobalVariable(variable) && 
				(!variableCanHaveAsValue(getGlobalVariableExpression(variable), statement.getValue())) )
			throw new IllegalArgumentException();
		
		addGlobalVariable(variable, statement.getValue());
	}
	
	private void runBreak(BreakStatement statement) {
		
	}
	
	private void runPrint(PrintStatement statement) {
		System.out.println(statement.getValue().toString());
	}
	
	private void runSequence(SequenceStatement statement) {
		for(MyStatement subStatement: statement.getStatements())
			statementHandler(statement);
	}
	
}
