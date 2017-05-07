package asteroids.helper.program;

public class SimpleBoolean implements SimpleClass {

	public SimpleBoolean(Boolean value) {
		setValue(value);
	}
	
	private Boolean value;
	
	private void setValue(Boolean value) {
		this.value = value;
	}

	@Override
	public Boolean getValue() {
		return Boolean.valueOf(this.value);
	}

	@Override
	public ClassType getClassType() {
		return ClassType.BOOLEAN;
	}

}
