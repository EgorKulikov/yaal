package net.egork;

import net.egork.geometry.Point;
import net.egork.geometry.Polygon;
import net.egork.geometry.Vector;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import static java.lang.Math.PI;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        Point[] p = new Point[n];
        for (int i = 0; i < n; i++) {
            p[i] = Point.readPoint(in);
        }
        double[] answer = new double[n];
        Polygon pol = Polygon.convexHull(p.clone());
        for (int i = 0; i < pol.vertices.length; i++) {
            int prev = i - 1;
            if (prev < 0) {
                prev += pol.vertices.length;
            }
            int next = i + 1;
            if (next >= pol.vertices.length) {
                next -= pol.vertices.length;
            }
            double ang1 = new Vector(pol.vertices[prev], pol.vertices[i]).angle();
            double ang2 = new Vector(pol.vertices[i], pol.vertices[next]).angle();
            double ang = ang1 - ang2;
            if (ang < 0) {
                ang += 2 * PI;
            }
            for (int j = 0; j < n; j++) {
                if (p[j].equals(pol.vertices[i])) {
                    answer[j] = ang / (2 * PI);
                }
            }
        }
        for (double d : answer) {
            out.printLine(d);
        }
    }
}
