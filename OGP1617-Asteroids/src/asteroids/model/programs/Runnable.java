package asteroids.model.programs;

public interface Runnable {

	public double getTime();
	public Object getLocalVar();
	public Object getGlobalVar();
	public void addPrintValue();
	public void execute(double time);
	
	
}
