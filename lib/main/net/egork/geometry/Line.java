package net.egork.geometry;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public class Line {
	public final double a;
	public final double b;
	public final double c;

	public Line(Point p, double angle) {
		a = Math.sin(angle);
		b = -Math.cos(angle);
		c = -p.x * a - p.y * b;
	}

	public Line(double a, double b, double c) {
		double h = GeometryUtils.fastHypot(a, b);
		this.a = a / h;
		this.b = b / h;
		this.c = c / h;
	}

	public Point intersect(Line other) {
		if (parallel(other))
			return null;
		double determinant = b * other.a - a * other.b;
		double x = (c * other.b - b * other.c) / determinant;
		double y = (a * other.c - c * other.a) / determinant;
		return new Point(x, y);
	}

	public boolean parallel(Line other) {
		return Math.abs(a * other.b - b * other.a) < GeometryUtils.epsilon;
	}

	public boolean contains(Point point) {
		return Math.abs(value(point)) < GeometryUtils.epsilon;
	}

	public Line perpendicular(Point point) {
		return new Line(-b, a, b * point.x - a * point.y);
	}

	public double value(Point point) {
		return a * point.x + b * point.y + c;
	}

	public Point[] intersect(Circle circle) {
		double distance = distance(circle.center);
		if (distance > circle.radius + GeometryUtils.epsilon)
			return new Point[0];
		Point intersection = intersect(perpendicular(circle.center));
		if (Math.abs(distance - circle.radius) < GeometryUtils.epsilon)
			return new Point[]{intersection};
		double shift = Math.sqrt(circle.radius * circle.radius - distance * distance);
		return new Point[]{new Point(intersection.x + shift * b, intersection.y - shift * a),
			new Point(intersection.x - shift * b, intersection.y + shift * a)};
	}

	public double distance(Point center) {
		return Math.abs(value(center));
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Line line = (Line) o;

		if (!parallel(line)) return false;
		if (Math.abs(a * line.c - c * line.a) > GeometryUtils.epsilon || Math.abs(b * line.c - c * line.b) > GeometryUtils.epsilon) return false;

		return true;
	}
}
