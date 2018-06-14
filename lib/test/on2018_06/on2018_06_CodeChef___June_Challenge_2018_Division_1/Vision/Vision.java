package on2018_06.on2018_06_CodeChef___June_Challenge_2018_Division_1.Vision;



import net.egork.geometry.Circle;
import net.egork.geometry.Line;
import net.egork.geometry.Point;
import net.egork.geometry.Quaternion;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.lang.Math.sqrt;
import static net.egork.geometry.Point.ORIGIN;

public class Vision {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        double px = in.readDouble();
        double py = in.readDouble();
        double pz = in.readDouble();
        double qx = in.readDouble();
        double qy = in.readDouble();
        double qz = in.readDouble();
        double dx = in.readDouble();
        double dy = in.readDouble();
        double dz = in.readDouble();
        double cx = in.readDouble();
        double cy = in.readDouble();
        double cz = in.readDouble();
        double r = in.readDouble();
        px -= qx;
        py -= qy;
        pz -= qz;
        cx -= qx;
        cy -= qy;
        cz -= qz;
        Quaternion p = new Quaternion(px, py, pz);
        Quaternion d = new Quaternion(dx, dy, dz);
        Quaternion rotation = Quaternion.basisRotation(d, Quaternion.vectorProduct(p, d));
        p = p.conj(rotation);
        d = d.conj(rotation);
        Quaternion c = new Quaternion(cx, cy, cz);
        c = c.conj(rotation);
        r = sqrt(r * r - c.y * c.y);
        Point pp = new Point(p.x, p.z);
        Circle circle = new Circle(new Point(c.x, c.z), r);
        Point[] tangentPoints = circle.findTouchingPoints(pp);
        Line[] tangents = new Line[2];
        for (int i = 0; i < 2; i++) {
            tangents[i] = pp.line(tangentPoints[i]);
        }
        Line ox = ORIGIN.line(new Point(1, 0));
        for (int i = 0; i < 2; i++) {
            Point target = ox.intersect(tangents[i]);
            if (target != null && target.x > 0) {
                out.printLine(target.x / d.abs());
                return;
            }
        }
    }
}
