package asteroids.model.programs;

import java.util.List;
import asteroids.part3.programs.SourceLocation;

@SuppressWarnings("rawtypes")
public class MyFunction implements Runnable {
	
	public MyFunction(String functionName, MyStatement body, SourceLocation location) {
		setFunctionName(functionName);
		setBody(body);
		setLocation(location);		
	}
	
	
	private MyStatement body;
	private String functionName;
	private SourceLocation location;
	
	
	public String getFunctionName() {
		return this.functionName;
	}
	
	public MyStatement getBody() {
		return this.body;
	}
	
	public SourceLocation getLocation() {
		return this.location;
	}
	
	
	private void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
	
	private void setBody(MyStatement body) {
		this.body = body;
	}
	
	private void setLocation(SourceLocation location) {
		this.location = location;
	}
	
	
	
	public Object execute(List<MyExpression> list) {
		return null;
	}

	@Override
	public double getTime() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getLocalVar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getGlobalVar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addPrintValue() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void execute(double time) {
		// TODO Auto-generated method stub
		
	}
	
}
