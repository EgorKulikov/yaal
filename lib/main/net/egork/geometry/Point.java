package net.egork.geometry;

import net.egork.utils.io.InputReader;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public class Point {
	public static final Point ORIGIN = new Point(0, 0);
	public final double x;
	public final double y;

	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}

	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public Line line(Point other) {
		if (equals(other))
			return null;
		double a = other.y - y;
		double b = x - other.x;
		double c = -a * x - b * y;
		return new Line(a, b, c);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Point point = (Point) o;

		return Math.abs(x - point.x) <= GeometryUtils.epsilon && Math.abs(y - point.y) <= GeometryUtils.epsilon;
	}

	@Override
	public int hashCode() {
		int result;
		long temp;
		temp = x != +0.0d ? Double.doubleToLongBits(x) : 0L;
		result = (int) (temp ^ (temp >>> 32));
		temp = y != +0.0d ? Double.doubleToLongBits(y) : 0L;
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	public double distance(Point other) {
		return GeometryUtils.fastHypot(x - other.x, y - other.y);
	}

	public double distance(Line line) {
		return Math.abs(line.a * x + line.b * y + line.c);
	}

	public double value() {
		return GeometryUtils.fastHypot(x, y);
	}

	public double angle() {
		return Math.atan2(y, x);
	}

	public static Point readPoint(InputReader in) {
		double x = in.readDouble();
		double y = in.readDouble();
		return new Point(x, y);
	}

	public Point rotate(double angle) {
		double nx = x * Math.cos(angle) - y * Math.sin(angle);
		double ny = y * Math.cos(angle) + x * Math.sin(angle);
		return new Point(nx, ny);
	}
}
