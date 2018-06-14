package net.egork.geometry;

/**
 * @author egor@egork.net
 */
public class Quaternion {
    public final double s;
    public final double x;
    public final double y;
    public final double z;

    public static final Quaternion EX = new Quaternion(1, 0, 0);
    public static final Quaternion EY = new Quaternion(0, 1, 0);
    public static final Quaternion EZ = new Quaternion(0, 0, 1);

    public Quaternion(double s, double x, double y, double z) {
        this.s = s;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Quaternion(double x, double y, double z) {
        this(0, x, y, z);
    }

    public static Quaternion add(Quaternion a, Quaternion b) {
        return new Quaternion(a.s + b.s, a.x + b.x, a.y + b.y, a.z + b.z);
    }

    public static Quaternion subtract(Quaternion a, Quaternion b) {
        return new Quaternion(a.s - b.s, a.x - b.x, a.y - b.y, a.z - b.z);
    }

    public static Quaternion multiply(Quaternion a, Quaternion b) {
        return new Quaternion(a.s * b.s - a.x * b.x - a.y * b.y - a.z * b.z,
                a.s * b.x + a.x * b.s + a.y * b.z - a.z * b.y,
                a.s * b.y + a.y * b.s + a.z * b.x - a.x * b.z,
                a.s * b.z + a.z * b.s + a.x * b.y - a.y * b.x);
    }

    public static Quaternion multiply(Quaternion a, double b) {
        return new Quaternion(a.s * b, a.x * b, a.y * b, a.z * b);
    }

    public static Quaternion divide(Quaternion a, Quaternion b) {
        return divide(multiply(a, b.conj()), b.norm());
    }

    public static Quaternion divide(Quaternion a, double b) {
        return new Quaternion(a.s / b, a.x  / b, a.y / b, a.z / b);
    }

    public static double scalarProduct(Quaternion a, Quaternion b) {
        return -multiply(a, b).s;
    }

    public static Quaternion vectorProduct(Quaternion a, Quaternion b) {
        return multiply(a, b).vector();
    }

    public static double mixProduct(Quaternion a, Quaternion b, Quaternion c) {
        return scalarProduct(a, vectorProduct(b, c));
    }

    public static Quaternion minRotation(Quaternion a, Quaternion b) {
        a = a.normalize();
        b = b.normalize();
        if (isOpposite(a, b)) {
            return b.orthogonal().rotation(Math.PI);
        } else {
            return multiply(a, add(a, b)).conj();
        }
    }

    public static Quaternion basisRotation(Quaternion nx, Quaternion ny) {
        nx = nx.normalize();
        ny = ny.normalize();
        Quaternion a = minRotation(nx, EX);
        ny = ny.conj(a);
        Quaternion b = minRotation(ny, EY);
        if (isOpposite(ny, EY)) {
            b = EX.rotation(Math.PI);
        }
        return multiply(b, a);
    }

    public Quaternion normalize() {
        return divide(this, abs());
    }

    public Quaternion rotation(double phi) {
        double sin = Math.sin(phi / 2);
        return new Quaternion(Math.cos(phi / 2), x * sin, y * sin, z * sin);
    }

    public Quaternion conj(Quaternion g) {
        return divide(multiply(g, this), g);
    }

    public Quaternion rotate(Quaternion i, double phi) {
        return conj(i.rotation(phi));
    }

    public Quaternion conj() {
        return new Quaternion(s, -x, -y, -z);
    }

    public double norm() {
        return s * s + x * x + y * y + z * z;
    }

    public Quaternion orthogonal() {
        if (Math.abs(y) > GeometryUtils.epsilon) {
            return new Quaternion(y, -x, 0);
        } else {
            return new Quaternion(z, 0, -x);
        }
    }

    public double abs() {
        return Math.sqrt(norm());
    }

    public Quaternion vector() {
        return new Quaternion(x, y, z);
    }

    public static boolean isOpposite(Quaternion a, Quaternion b) {
        return Math.abs(a.s + b.s) < GeometryUtils.epsilon && Math.abs(a.x + b.x) < GeometryUtils.epsilon &&
                Math.abs(a.y + b.y) < GeometryUtils.epsilon && Math.abs(a.z + b.z) < GeometryUtils.epsilon;
    }

    @Override
    public String toString() {
        return "(" + s + ", " + x + ", " + y + ", " + z + ")";
    }
}
