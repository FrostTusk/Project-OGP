package asteroids.model.programs;

import asteroids.part3.programs.SourceLocation;

public interface MyExpression <T>{
	
//	private SourceLocation location;
//	private ExpressionType type;
//	
//
//	protected void setLocation(SourceLocation location) {
//		this.location = location;
//	}
//	
//	protected void setType(ExpressionType type) {
//		this.type = type;
//	}
//	
//	
//	public SourceLocation getLocation() {
//		return this.location;
//	}
//	
//	public ExpressionType getType() {
//		return this.type;
//	}
	
	public SourceLocation getLocation();
	public T evaluate();
	
}
