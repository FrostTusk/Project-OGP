package asteroids.model.programs.expressions.entity;

import java.util.Set;
import java.util.stream.Collectors;
import asteroids.helper.entity.Entity;
import asteroids.model.Bullet;
import asteroids.part3.programs.SourceLocation;

public class BulletSpecificEntityExpression extends SpecificEntityExpression {

	public BulletSpecificEntityExpression(Class<? extends Bullet> entityType, SourceLocation location) {
		super(entityType, location);
	}

	
	@Override
	public Entity[] getEntitiesArray(Class<? extends Entity> entityType) {
		Entity[] entitiesArray;
		Set<? extends Entity> entitiesSet;
		try {
			entitiesSet = getStatement().getExecuter().getOwner().getWorld().getAllEntitiesSpecific(entityType).
						  stream().filter(x -> ((Bullet) x).getSource() == getStatement().getExecuter().getOwner()).
						  collect(Collectors.toSet());
			entitiesArray = new Entity[entitiesSet.size()];
		}
		catch(NullPointerException exc) {
			return null; // Default value if any element is null.
		}
		return entitiesSet.toArray(entitiesArray);
	}
	
}
