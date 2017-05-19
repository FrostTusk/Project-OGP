package asteroids.model.programs;

import asteroids.model.Program;
import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;

public interface Executable {

	public double getTime();
	public Ship getOwner();
	public void setTime(double time);
	public boolean getFlag(SourceLocation location);
	public Object getLocalVar(String name);
	public Object getGlobalVar(String name);
	public void addVar(String name, Object value);
	public void addPrintValue(Object value);
	public void flagLine(SourceLocation location);
	public void execute(double time);
	public Program getProgram();
		
}
