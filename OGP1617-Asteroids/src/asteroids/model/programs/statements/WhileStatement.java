package asteroids.model.programs.statements;

import asteroids.model.programs.Executable;
import asteroids.model.programs.MyExpression;
import asteroids.model.programs.MyStatement;
import asteroids.part3.programs.SourceLocation;

public class WhileStatement implements MyStatement {

	public WhileStatement(MyExpression<Boolean> condition, MyStatement body, SourceLocation location) {
		setLocation(location);
		setCondition(condition);
		setBody(body);
		body.setSuperStatement(this);
	}
	

	private MyExpression<Boolean> condition;
	private SourceLocation location;
	private MyStatement superStatement;
	private MyStatement body;
	private Boolean breakWhile = false;
	
	
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
	
	
	
	private Executable executer;
	
	
	@Override
	public Executable getExecuter() {
		return this.executer;
	}

	@Override
	public MyStatement getSuperStatement() {
		return this.superStatement;
	}

	
	@Override
	public void setExecuter(Executable executer) {
		this.executer = executer;
		getCondition().setStatement(this);
	}

	@Override
	public void setSuperStatement(MyStatement statement) {
		this.superStatement = statement;
	}

	
	
	@Override
	public void execute() throws IllegalStateException {
		if (getExecuter().getFlag(getLocation())) return;
		while (getCondition().evaluate() && !breakWhile)
				body.execute();
		getExecuter().flagLine(getLocation());
	}
	
	
//	private void breakThisWhile() {
//		breakWhile = true;
//		
////		SourceLocation returnLocation = getWhile().getLocation();
////		getProgram().flagLine(returnLocation);
//		
//		
//	}
	
	
//	public class BreakStatement implements MyStatement {
//
//		public BreakStatement(SourceLocation location) {
//			setLocation(location);
//		}
//
//		
//		public WhileStatement getWhile() {
//		    return WhileStatement.this;
//		}
//		
//		
//		private SourceLocation location;
//		private Executable executer;
//		private MyStatement superStatement;
//		
//		
//		@Override
//		public SourceLocation getLocation() {
//			return this.location;
//		}
//		
//		@Override
//		public int getSize() {
//			return 1;
//		}
//
//
//		private void setLocation(SourceLocation location) {
//			this.location = location;
//		}
//		
//
//		@Override
//		public Executable getExecuter() {
//			return this.executer;
//		}
//
//		@Override
//		public MyStatement getSuperStatement() {
//			return this.superStatement;
//		}
//		
//
//		@Override
//		public void setExecuter(Executable executer) {
//			this.executer = executer;
//		}
//
//
//		@Override
//		public void setSuperStatement(MyStatement statement) {
//			this.superStatement = statement;
//		}
//
//
//		@Override
//		public void execute() {
//			if (getExecuter().getFlag(getLocation())) return;
//			if (getSuperStatement().getClass() != WhileStatement.class)
//				throw new RuntimeException(); 
//			
//			WhileStatement superWhile = ((WhileStatement)this.getSuperStatement());
//			superWhile.breakThisWhile();
//			
//		}
//		
//	}
//	
}
