package asteroids.model.programs.statements;

import asteroids.helper.program.StatementType;
import asteroids.model.programs.MyStatement;
import asteroids.part3.programs.SourceLocation;

public class BreakStatement extends MyStatement {

	public BreakStatement(SourceLocation location) {
		setLocation(location);
		setType(StatementType.BREAK);
	}
	
}
