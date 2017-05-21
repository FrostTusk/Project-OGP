package asteroids.model.programs;

import asteroids.part3.programs.SourceLocation;

public interface MyExpression <T> {
	
	public SourceLocation getLocation();
	public MyStatement getStatement();
	public void setStatement(MyStatement statement);
	public T evaluate();
	
}
