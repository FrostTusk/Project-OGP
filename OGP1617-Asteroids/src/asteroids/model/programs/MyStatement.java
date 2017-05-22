package asteroids.model.programs;

import asteroids.helper.ExitOutException;
import asteroids.part3.programs.SourceLocation;

public interface MyStatement {
	
	public SourceLocation getLocation();
	public Executable getExecuter();
	public MyStatement getSuperStatement();
	public void setExecuter(Executable executer);
	public void setSuperStatement(MyStatement statement);
	public void requestFlag();
	public void requestDeFlag();
	public void execute();
	
}
