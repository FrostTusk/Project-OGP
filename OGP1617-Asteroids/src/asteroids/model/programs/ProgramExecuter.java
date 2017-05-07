package asteroids.model.programs;

import java.util.Set;

import asteroids.helper.program.StatementType;
import asteroids.junk.TestException;
import asteroids.model.*;
import asteroids.model.programs.statements.AssignmentStatement;

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

	
	
	private Set<String> globalVariables;
	
	private void executeProgram() {
		statementHandler(getProgram().getMain());
	}
	
	
	private void statementHandler(MyStatement statement) {
		StatementType type = statement.getType();
		switch (type) {
        	case ACTION: break;
        	case ASSIGNMENT: runAssignment((AssignmentStatement) statement);;
        	case BREAK: break;
        	case IF: break;
        	case PRINT: break;
        	case RETURN: break;
        	case SEQUENCE: break;
        	case WHILE: break;
        	default: break;
		}
	}
	
	private void runAssignment(AssignmentStatement statement) {
		String name = statement.getVariableName();
		if (globalVariables.contains(name))
			throw new TestException();
		statement.getValue();
	}
	
}
