package net.egork.geometry;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public class Segment {
	public final Point a;
	public final Point b;

	public Segment(Point a, Point b) {
		this.a = a;
		this.b = b;
	}

	public double length() {
		return a.distance(b);
	}

	public double distance(Point point) {
		double length = length();
		double left = point.distance(a);
		if (length < GeometryUtils.epsilon)
			return left;
		double right = point.distance(b);
		if (left * left > right * right + length * length)
			return right;
		if (right * right > left * left + length * length)
			return left;
		return point.distance(a.line(b));
	}

	public Point intersect(Segment other, boolean includeEnds) {
		Line line = line();
		Line otherLine = other.a.line(other.b);
		if (line.parallel(otherLine))
			return null;
		Point intersection = line.intersect(otherLine);
		if (contains(intersection, includeEnds) && other.contains(intersection, includeEnds))
			return intersection;
		else
			return null;
	}

	public boolean contains(Point point, boolean includeEnds) {
		Line line = line();
		if (a.equals(point) || b.equals(point))
			return includeEnds;
		if (!line.contains(point))
			return false;
		Line perpendicular = line.perpendicular(a);
		double aValue = perpendicular.value(a);
		double bValue = perpendicular.value(b);
		double pointValue = perpendicular.value(point);
		return aValue < pointValue && pointValue < bValue || bValue < pointValue && pointValue < aValue;
	}

	public Line line() {
		return a.line(b);
	}
}
