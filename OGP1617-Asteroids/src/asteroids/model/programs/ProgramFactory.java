package asteroids.model.programs;

import java.util.List;

import asteroids.part3.programs.IProgramFactory;
import asteroids.part3.programs.SourceLocation;

@SuppressWarnings("rawtypes")
public class ProgramFactory implements IProgramFactory {

	@Override
	public Object createProgram(List functions, Object main) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createFunctionDefinition(String functionName, Object body, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createAssignmentStatement(String variableName, Object value, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createWhileStatement(Object condition, Object body, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createBreakStatement(SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createReturnStatement(Object value, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createIfStatement(Object condition, Object ifBody, Object elseBody, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createPrintStatement(Object value, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createSequenceStatement(List statements, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createReadVariableExpression(String variableName, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createReadParameterExpression(String parameterName, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createFunctionCallExpression(String functionName, List actualArgs, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createChangeSignExpression(Object expression, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createNotExpression(Object expression, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createDoubleLiteralExpression(double value, SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createNullExpression(SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createSelfExpression(SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createShipExpression(SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createAsteroidExpression(SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createPlanetoidExpression(SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createBulletExpression(SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createPlanetExpression(SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createAnyExpression(SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createGetXExpression(Object e, SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createGetYExpression(Object e, SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createGetVXExpression(Object e, SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createGetVYExpression(Object e, SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createGetRadiusExpression(Object e, SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createLessThanExpression(Object e1, Object e2, SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createEqualityExpression(Object e1, Object e2, SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createAdditionExpression(Object e1, Object e2, SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createMultiplicationExpression(Object e1, Object e2, SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createSqrtExpression(Object e, SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createGetDirectionExpression(SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createThrustOnStatement(SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createThrustOffStatement(SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createFireStatement(SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createTurnStatement(Object angle, SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createSkipStatement(SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

}
