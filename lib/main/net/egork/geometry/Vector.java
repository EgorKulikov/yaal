package net.egork.geometry;

/**
 * @author egorku@yandex-team.ru
 */
public class Vector {
    public final double x;
    public final double y;
    public final Point point;

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
        point = new Point(x, y);
    }

    public Vector(Point point) {
        this.x = point.x;
        this.y = point.y;
        this.point = point;
    }

    public Vector(Point from, Point to) {
        this(to.x - from.x, to.y - from.y);
    }

    public double angleTo(Vector other) {
        return GeometryUtils.canonicalAngle(other.point.angle() - point.angle());
    }

    public double length() {
        return point.value();
    }

    public double angle() {
        return point.angle();
    }
}
