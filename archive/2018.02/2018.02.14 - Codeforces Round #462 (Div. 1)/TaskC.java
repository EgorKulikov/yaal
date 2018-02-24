package net.egork;

import net.egork.collections.iss.IndependentSetSystem;
import net.egork.collections.iss.RecursiveIndependentSetSystem;
import net.egork.generated.collections.list.IntArrayList;
import net.egork.generated.collections.list.IntList;
import net.egork.geometry.Circle;
import net.egork.geometry.Point;
import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;
import static java.lang.Math.max;
import static java.util.Arrays.asList;
import static net.egork.geometry.GeometryUtils.epsilon;
import static net.egork.misc.ArrayUtils.count;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] x = new int[n];
        int[] y = new int[n];
        int[] r = new int[n];
        in.readIntArrays(x, y, r);
        Circle[] circles = new Circle[n];
        for (int i = 0; i < n; i++) {
            circles[i] = new Circle(new Point(x[i], y[i]), r[i]);
        }
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                Point[] intersect = circles[i].intersect(circles[j]);
                if (intersect != null) {
                    points.addAll(asList(intersect));
                }
            }
        }
        boolean[] bad = new boolean[points.size()];
        for (int i = 0; i < points.size(); i++) {
            for (int j = 0; j < i; j++) {
                if (points.get(i).equals(points.get(j))) {
                    bad[i] = true;
                    break;
                }
            }
        }
        int nVertices = count(bad, false);
        int nEdges = 0;
        IndependentSetSystem setSystem = new RecursiveIndependentSetSystem(points.size());
        for (int i = 0; i < n; i++) {
            int current = 0;
            IntList onThis = new IntArrayList();
            for (int j = 0; j < points.size(); j++) {
                if (!bad[j] && abs(circles[i].center.distance(points.get(j)) - r[i]) < epsilon) {
                    current++;
                    onThis.add(j);
                }
            }
            for (int j = 1; j < onThis.size(); j++) {
                setSystem.join(onThis.get(j), onThis.get(0));
            }
            nEdges += max(current, 1);
        }
        int nComponents = setSystem.getSetCount() - count(bad, true);
        out.printLine(1 + nEdges - nVertices + nComponents);
    }
}
