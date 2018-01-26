package net.egork;

import net.egork.geometry.Point;
import net.egork.geometry.Vector;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;
import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

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
        p = go(p, vector, b);
        out.printLine(p.x, p.y);
        p = go(p, vector, -b);
        p = go(p, other, -a / 2d);
        out.printLine(p.x, p.y);
        p = go(p, other, -(c - a) / 2d);
        out.printLine(p.x, p.y);
        p = go(p, vector, -d);
        out.printLine(p.x, p.y);
        p = go(p, other, c);
        out.printLine(p.x, p.y);
        p = go(p, vector, d);
        out.printLine(p.x, p.y);
        p = go(p, other, -(c - a) / 2d);
        out.printLine(p.x, p.y);
    }

    private Point go(Point p, Vector vector, double times) {
        return new Point(p.x + vector.x * times, p.y + vector.y * times);
    }
}
