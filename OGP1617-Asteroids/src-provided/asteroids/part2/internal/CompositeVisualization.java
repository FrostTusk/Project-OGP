package asteroids.part2.internal;

public class CompositeVisualization<F, T> extends Visualization<F, T> {

	private final Visualization<F, T>[] vis;

	@SafeVarargs
	public CompositeVisualization(T object, Visualization<F, T>... vis) {
		super(null, object, null);
		this.vis = vis;
	}

	@Override
	public void draw(DrawContext<F> context) {
		for (Visualization<F, T> v : vis) {
			v.draw(context);
		}
	}

}
