package asteroids.helper.program;

public class SimpleDouble implements SimpleClass {
	
	public SimpleDouble(Double value) {
		setValue(value);
	}
	
	private Double value;
	
	private void setValue(Double value) {
		this.value = value;
	}

	@Override
	public Double getValue() {
		return Double.valueOf(this.value);
	}

	@Override
	public ClassType getClassType() {
		return ClassType.DOUBLE;
	}
	
}
