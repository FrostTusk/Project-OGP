package asteroids.model.programs;

import asteroids.helper.ExitOutException;
import asteroids.part3.programs.SourceLocation;

public interface MyExpression <T> {
	
	public SourceLocation getLocation();
	public MyStatement getStatement();
	public void setStatement(MyStatement statement) throws NullPointerException;
	public T evaluate() throws ExitOutException, IllegalArgumentException, IllegalStateException, NullPointerException;
	
}
