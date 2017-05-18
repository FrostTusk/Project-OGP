package asteroids.model.programs;

import java.util.List;

import asteroids.helper.entity.*;
import asteroids.helper.program.*;
import asteroids.model.*;
import asteroids.model.programs.expressions.*;
import asteroids.model.programs.expressions.entity.*;
import asteroids.model.programs.statements.*;
import asteroids.part3.programs.*;

@SuppressWarnings("all") // FIXME
public class ProgramFactory implements IProgramFactory<MyExpression, MyStatement, MyFunction, Program> {

	@Override
	public Program createProgram(List<MyFunction> functions, MyStatement main) {
		return new Program(functions, main);
	}

	@Override
	public MyFunction createFunctionDefinition(String functionName, MyStatement body, SourceLocation sourceLocation) {
		return new MyFunction(functionName, body, sourceLocation);
	}

	@Override
	public MyStatement createAssignmentStatement(String variableName, MyExpression value,
			SourceLocation sourceLocation) {
		return new AssignmentStatement(variableName, value, sourceLocation);
	}

	@Override
	public MyStatement createWhileStatement(MyExpression condition, MyStatement body, SourceLocation sourceLocation) {
		return new WhileStatement(condition, body, sourceLocation);
	}

	@Override
	public MyStatement createBreakStatement(SourceLocation sourceLocation) {
		return new BreakStatement(sourceLocation);
	}

	@Override
	public MyStatement createReturnStatement(MyExpression value, SourceLocation sourceLocation) {
		return new ReturnStatement(value, sourceLocation);
	}

	@Override
	public MyStatement createIfStatement(MyExpression condition, MyStatement ifBody, MyStatement elseBody,
			SourceLocation sourceLocation) {
		return new IfStatement(condition, ifBody, elseBody, sourceLocation);
	}

	@Override
	public MyStatement createPrintStatement(MyExpression value, SourceLocation sourceLocation) {
		return new PrintStatement(value, sourceLocation);
	}

	@Override
	public MyStatement createSequenceStatement(List<MyStatement> statements, SourceLocation sourceLocation) {
		return new SequenceStatement(statements, sourceLocation);
	}

	@Override
	public MyExpression createReadVariableExpression(String variableName, SourceLocation sourceLocation) {
		return new VariableExpression(variableName, sourceLocation);
	}

	@Override
	public MyExpression createReadParameterExpression(String parameterName, SourceLocation sourceLocation) {
		return new ParameterExpression(parameterName, sourceLocation);
	}

	@Override
	public MyExpression createFunctionCallExpression(String functionName, List<MyExpression> actualArgs,
			SourceLocation sourceLocation) {
		return new FunctionCallExpression(functionName, actualArgs, sourceLocation);
	}

	@Override
	public MyExpression createChangeSignExpression(MyExpression expression, SourceLocation sourceLocation) {
		return new ChangeSignExpression(expression, sourceLocation);
	}

	@Override
	public MyExpression createNotExpression(MyExpression expression, SourceLocation sourceLocation) {
		return new NotExpression(expression, sourceLocation);
	}

	@Override
	public MyExpression createDoubleLiteralExpression(double value, SourceLocation location) {
		return new DoubleLiteralExpression(value, location);
	}

	@Override
	public MyExpression createNullExpression(SourceLocation location) {
		return new NullEntityExpression(location);
	}

	@Override
	public MyExpression createSelfExpression(SourceLocation location) {
		return new SelfEntityExpression(location);
	}

	@Override
	public MyExpression createShipExpression(SourceLocation location) {
		return new SpecificEntityExpression(Ship.class, location);
	}

	@Override
	public MyExpression createAsteroidExpression(SourceLocation location) {
		return new SpecificEntityExpression(Asteroid.class, location);
	}

	@Override
	public MyExpression createPlanetoidExpression(SourceLocation location) {
		return new SpecificEntityExpression(Planetoid.class, location);
	}

	@Override
	public MyExpression createBulletExpression(SourceLocation location) {
		return new SpecificEntityExpression(Bullet.class, location);
	}

	@Override
	public MyExpression createPlanetExpression(SourceLocation location) {
		return new SpecificEntityExpression(MinorPlanet.class, location);
	}

	@Override
	public MyExpression createAnyExpression(SourceLocation location) {
		return new SpecificEntityExpression(Entity.class, location);
	}

	@Override
	public MyExpression createGetXExpression(MyExpression e, SourceLocation location) {
		return new GetterExpression(e, GetterType.GETX, location);
	}

	@Override
	public MyExpression createGetYExpression(MyExpression e, SourceLocation location) {
		return new GetterExpression(e, GetterType.GETY, location);
	}

	@Override
	public MyExpression createGetVXExpression(MyExpression e, SourceLocation location) {
		return new GetterExpression(e, GetterType.GETVX, location);
	}

	@Override
	public MyExpression createGetVYExpression(MyExpression e, SourceLocation location) {
		return new GetterExpression(e, GetterType.GETVY, location);
	}

	@Override
	public MyExpression createGetRadiusExpression(MyExpression e, SourceLocation location) {
		return new GetterExpression(e, GetterType.GETRADIUS, location);
	}

	@Override
	public MyExpression createLessThanExpression(MyExpression e1, MyExpression e2, SourceLocation location) {
		return new OperatorExpression(e1, e2, OperatorType.LESSTHAN, location);
	}

	@Override
	public MyExpression createEqualityExpression(MyExpression e1, MyExpression e2, SourceLocation location) {
		return new OperatorExpression(e1, e2, OperatorType.EQUALITY, location);
	}

	@Override
	public MyExpression createAdditionExpression(MyExpression e1, MyExpression e2, SourceLocation location) {
		return new OperatorExpression(e1, e2, OperatorType.ADDITION, location);
	}

	@Override
	public MyExpression createMultiplicationExpression(MyExpression e1, MyExpression e2, SourceLocation location) {
		return new OperatorExpression(e1, e2, OperatorType.MULTIPLICATION, location);
	}

	@Override
	public MyExpression createSqrtExpression(MyExpression e, SourceLocation location) {
		return new SqrtExpression(e, location);
	}

	@Override
	public MyExpression createGetDirectionExpression(SourceLocation location) {
		return new GetterExpression(null, GetterType.GETDIR, location);
	}

	@Override
	public MyStatement createThrustOnStatement(SourceLocation location) {
		return new ActionStatement(ActionType.THRUSTON, location);
	}

	@Override
	public MyStatement createThrustOffStatement(SourceLocation location) {
		return new ActionStatement(ActionType.THRUSTOFF, location);
	}

	@Override
	public MyStatement createFireStatement(SourceLocation location) {
		return new ActionStatement(ActionType.SHOOT, location);
	}

	@Override
	public MyStatement createTurnStatement(MyExpression angle, SourceLocation location) {
		return new ActionStatement(ActionType.TURN, angle, location);
	}

	@Override
	public MyStatement createSkipStatement(SourceLocation location) {
		return new ActionStatement(ActionType.SKIP, location);
	}
	
}