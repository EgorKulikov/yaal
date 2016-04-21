package net.egork.geometry;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public class Circle {
    public final Point center;
    public final double radius;

    public Circle(Point center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    public boolean contains(Point point) {
        return center.distance(point) < radius + GeometryUtils.epsilon;
    }

    public boolean strictContains(Point point) {
        return center.distance(point) < radius - GeometryUtils.epsilon;
    }

    public Point[] findTouchingPoints(Point point) {
        double distance = center.distance(point);
        if (distance < radius - GeometryUtils.epsilon) {
            return new Point[0];
        }
        if (distance < radius + GeometryUtils.epsilon) {
            return new Point[]{point};
        }
        Circle power = new Circle(point, Math.sqrt((distance - radius) * (distance + radius)));
        return intersect(power);
    }

    public Point[] intersect(Circle other) {
        double distance = center.distance(other.center);
        if (distance < GeometryUtils.epsilon) {
            return null;
        }
        Line line = new Line(2 * (other.center.x - center.x), 2 * (other.center.y - center.y), other.radius * other
                .radius - radius * radius + center.x * center.x - other.center.x * other.center.x +
                center.y * center.y - other.center.y * other.center.y);
        return line.intersect(this);
    }
}
