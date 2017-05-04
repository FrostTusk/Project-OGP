package asteroids.model.programs;

import java.util.List;

import asteroids.model.Program;
import asteroids.part3.programs.IProgramFactory;
import asteroids.part3.programs.SourceLocation;

public class ProgramFactory implements IProgramFactory<MyExpression, MyStatement, MyFunction, Program> {

	@Override
	public Program createProgram(List<MyFunction> functions, MyStatement main) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MyFunction createFunctionDefinition(String functionName, MyStatement body, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MyStatement createAssignmentStatement(String variableName, MyExpression value,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MyStatement createWhileStatement(MyExpression condition, MyStatement body, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MyStatement createBreakStatement(SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MyStatement createReturnStatement(MyExpression value, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MyStatement createIfStatement(MyExpression condition, MyStatement ifBody, MyStatement elseBody,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MyStatement createPrintStatement(MyExpression value, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MyStatement createSequenceStatement(List<MyStatement> statements, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MyExpression createReadVariableExpression(String variableName, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MyExpression createReadParameterExpression(String parameterName, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MyExpression createFunctionCallExpression(String functionName, List<MyExpression> actualArgs,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MyExpression createChangeSignExpression(MyExpression expression, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MyExpression createNotExpression(MyExpression expression, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MyExpression createDoubleLiteralExpression(double value, SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MyExpression createNullExpression(SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MyExpression createSelfExpression(SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MyExpression createShipExpression(SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MyExpression createAsteroidExpression(SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MyExpression createPlanetoidExpression(SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MyExpression createBulletExpression(SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MyExpression createPlanetExpression(SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MyExpression createAnyExpression(SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MyExpression createGetXExpression(MyExpression e, SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MyExpression createGetYExpression(MyExpression e, SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MyExpression createGetVXExpression(MyExpression e, SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MyExpression createGetVYExpression(MyExpression e, SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MyExpression createGetRadiusExpression(MyExpression e, SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MyExpression createLessThanExpression(MyExpression e1, MyExpression e2, SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MyExpression createEqualityExpression(MyExpression e1, MyExpression e2, SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MyExpression createAdditionExpression(MyExpression e1, MyExpression e2, SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MyExpression createMultiplicationExpression(MyExpression e1, MyExpression e2, SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MyExpression createSqrtExpression(MyExpression e, SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MyExpression createGetDirectionExpression(SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MyStatement createThrustOnStatement(SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MyStatement createThrustOffStatement(SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MyStatement createFireStatement(SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MyStatement createTurnStatement(MyExpression angle, SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MyStatement createSkipStatement(SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}
	
}