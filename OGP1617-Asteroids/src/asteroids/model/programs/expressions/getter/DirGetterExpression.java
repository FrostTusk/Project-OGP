package asteroids.model.programs.expressions.getter;

import asteroids.helper.program.GetterType;
import asteroids.model.programs.MyStatement;
import asteroids.model.programs.expressions.GetterExpression;
import asteroids.part3.programs.SourceLocation;

public class DirGetterExpression extends GetterExpression {

	public DirGetterExpression(GetterType getterType, SourceLocation location) {
		super(null, getterType, location);
	}
	
	
	
	@Override
	public void setStatement(MyStatement statement) {
		this.statement = statement;	
	}

}
