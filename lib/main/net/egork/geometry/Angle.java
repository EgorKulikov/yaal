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
        double x1 = a.x - o.x;
        double y1 = a.y - o.y;
        double x2 = b.x - o.x;
        double y2 = b.y - o.y;
        double scal = x1 * x2 + y1 * y2;
        double vect = x1 * y2 - x2 * y1;
        return Math.abs(Math.atan2(vect, scal));
    }
}
