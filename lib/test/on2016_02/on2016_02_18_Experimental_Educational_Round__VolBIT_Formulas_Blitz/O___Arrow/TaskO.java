package on2016_02.on2016_02_18_Experimental_Educational_Round__VolBIT_Formulas_Blitz.O___Arrow;



import net.egork.geometry.Point;
import net.egork.geometry.Vector;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.lang.Math.hypot;

public class TaskO {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        Point p = Point.readPoint(in);
        double vx = in.readInt();
        double vy = in.readInt();
        double h = hypot(vx, vy);
        vx /= h;
        vy /= h;
        Vector vector = new Vector(vx, vy);
        Vector other = new Vector(vy, -vx);
        int a = in.readInt();
        int b = in.readInt();
        int c = in.readInt();
        int d = in.readInt();
        p = p.apply(vector, b);
        out.printLine(p.x, p.y);
        p = p.apply(vector, -b);
        p = p.apply(other, -a / 2d);
        out.printLine(p.x, p.y);
        p = p.apply(other, -(c - a) / 2d);
        out.printLine(p.x, p.y);
        p = p.apply(vector, -d);
        out.printLine(p.x, p.y);
        p = p.apply(other, c);
        out.printLine(p.x, p.y);
        p = p.apply(vector, d);
        out.printLine(p.x, p.y);
        p = p.apply(other, -(c - a) / 2d);
        out.printLine(p.x, p.y);
    }
}
