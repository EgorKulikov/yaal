package net.egork.geometry;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public class Line {
	public final double a;
	public final double b;
	public final double c;

	public Line(double a, double b, double c) {
		double h = GeometryUtils.fastHypot(a, b);
		this.a = a / h;
		this.b = b / h;
		this.c = c / h;
	}

	public Point intersect(Line other) {
		if (parallel(other))
			return null;
		double determinant = a * other.b - b * other.a;
		double x = (c * other.b - b * other.c) / determinant;
		double y = (a * other.c - c * other.a) / determinant;
		return new Point(x, y);
	}

	public boolean parallel(Line other) {
		return Math.abs(a * other.b - b * other.a) < GeometryUtils.epsilon;
	}
}
