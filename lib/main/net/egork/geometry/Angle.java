package net.egork.geometry;

/**
 * @author Egor Kulikov (egor@egork.net)
 */
public class Angle {
	public final Point o;
	public final Point a;
	public final Point b;

	public Angle(Point o, Point a, Point b) {
		this.o = o;
		this.a = a;
		this.b = b;
	}

	public double value() {
		double ab = a.distance(b);
		double ao = a.distance(o);
		double bo = b.distance(o);
		return Math.acos((ao * ao + bo * bo - ab * ab) / (2 * ao * bo));
	}

}
