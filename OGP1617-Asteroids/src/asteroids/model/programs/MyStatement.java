package asteroids.model.programs;

import asteroids.helper.ExitOutException;
import asteroids.part3.programs.SourceLocation;

public interface MyStatement {
	
	public SourceLocation getLocation();
	public Executable getExecuter();
	public MyStatement getSuperStatement();
	public void setExecuter(Executable executer) throws NullPointerException ;
	public void setSuperStatement(MyStatement statement);
	public void requestFlag() throws NullPointerException;
	public void requestDeFlag() throws NullPointerException;
	public void execute() throws ExitOutException, IllegalArgumentException, IllegalStateException, NullPointerException;
	
}
