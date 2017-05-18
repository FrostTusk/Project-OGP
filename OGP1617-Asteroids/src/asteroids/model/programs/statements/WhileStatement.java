package asteroids.model.programs.statements;

import asteroids.model.Program;
import asteroids.model.programs.MyExpression;
import asteroids.model.programs.MyStatement;
import asteroids.part3.programs.SourceLocation;

public class WhileStatement implements MyStatement {

	public WhileStatement(MyExpression<Boolean> condition, MyStatement body, SourceLocation location) {
		setLocation(location);
		setCondition(condition);
		setBody(body);
	}
	

	private MyExpression<Boolean> condition;
	private SourceLocation location;
	private Program program;
	private MyStatement superStatement;
	private MyStatement body;
	
	
	public MyExpression<Boolean> getCondition() {
		return this.condition;
	}

	
	public MyStatement getBody() {
		return this.body;
	}

	@Override
	public SourceLocation getLocation() {
		return this.location;
	}
	
	@Override
	public int getSize() {
		return body.getSize() + 1;
	}
	

	private void setCondition(MyExpression<Boolean> condition) {
		this.condition = condition;
	}
	
	private void setBody(MyStatement body) {
		this.body = body;
	}


	private void setLocation(SourceLocation location) {
		this.location = location;
	}
	
	
	@Override
	public Program getProgram() {
		return this.program;
	}

	@Override
	public MyStatement getSuperStatement() {
		return this.superStatement;
	}

	
	@Override
	public void setProgram(Program program) {
		this.program = program;
		getCondition().setStatement(this);
	}

	@Override
	public void setSuperStatement(MyStatement statement) {
		this.superStatement = statement;
	}

	
	
	@Override
	public void execute() throws IllegalStateException {
		if (getProgram().getFlag(getLocation())) return;
		while (getCondition().evaluate())
			body.execute();
		getProgram().flagLine(getLocation());
	}
	
	
	public class BreakStatement implements MyStatement {

		public BreakStatement(SourceLocation location) {
			setLocation(location);
		}

		
		public WhileStatement getWhile() {
		    return WhileStatement.this;
		}
		
		
		private SourceLocation location;
		private Program program;
		private MyStatement superStatement;
		
		
		@Override
		public SourceLocation getLocation() {
			return this.location;
		}
		
		@Override
		public int getSize() {
			return 1;
		}


		private void setLocation(SourceLocation location) {
			this.location = location;
		}
		

		@Override
		public Program getProgram() {
			return this.program;
		}

		@Override
		public MyStatement getSuperStatement() {
			return this.superStatement;
		}
		

		@Override
		public void setProgram(Program program) {
			this.program = program;
		}


		@Override
		public void setSuperStatement(MyStatement statement) {
			this.superStatement = statement;
		}


		@Override
		public void execute() {
			if (getProgram().getFlag(getLocation())) return;
			if (getSuperStatement().getClass() != WhileStatement.class)
				throw new RuntimeException(); 
			
			SourceLocation returnLocation = getWhile().getLocation();
			getProgram().flagLine(returnLocation);
			
			
		}
		
	}
	
}
