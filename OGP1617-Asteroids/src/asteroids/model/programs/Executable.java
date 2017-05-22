package asteroids.model.programs;

import java.util.List;

import asteroids.model.*;
import asteroids.part3.programs.SourceLocation;

public interface Executable {

	public double getTime();
	public Ship getOwner();
	public Program getProgram();
	public void setTime(double time);
	public boolean getFlag(SourceLocation location);
	public Object getLocalVar(String name);
	public Object getGlobalVar(String name);
	public boolean canHaveAsName(String name);
	public void addPrintValue(Object value);
	public void addVar(String name, Object value);
	public void flagLine(SourceLocation location);
	public void deFlagLine(SourceLocation location);
	public void setReturn(Object value);
	public Object execute(List<Object> actualArgs);
		
}
