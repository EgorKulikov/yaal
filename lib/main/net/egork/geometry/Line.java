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
		/*if (a < -GeometryUtils.epsilon) {
			a = -a;
			b = -b;
			c = -c;
		} else if (a < GeometryUtils.epsilon && b < -GeometryUtils.epsilon) {
			b = -b;
			c = -c;
		}*/
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
}
