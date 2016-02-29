package on2016_02.on2016_02_28_Grand_Prix_of_Bashkortostan_2016.K___Krotek;



import net.egork.collections.iss.IndependentSetSystem;
import net.egork.collections.iss.RecursiveIndependentSetSystem;
import net.egork.generated.collections.pair.IntIntPair;
import net.egork.geometry.GeometryUtils;
import net.egork.geometry.Line;
import net.egork.geometry.Point;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.*;

import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;
import static java.lang.Math.*;
import static java.util.Arrays.*;

public class TaskK {
    Line shift;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int[] x1 = new int[n];
        int[] y1 = new int[n];
        int[] x2 = new int[n];
        int[] y2 = new int[n];
        readIntArrays(in, x1, y1, x2, y2);
        double[][] distance = new double[2 * n][2 * n];
        fill(distance, Double.POSITIVE_INFINITY);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 2; k++) {
                    distance[2 * i + j][2 * i + k] = 0;
                }
            }
        }
        Point[] points = new Point[2 * n];
        for (int i = 0; i < n; i++) {
            points[2 * i] = new Point(x1[i], y1[i]);
            points[2 * i + 1] = new Point(x2[i], y2[i]);
        }
        for (int i = 0; i < 2 * n; i++) {
            points[i].rotate(1);
        }
        Line[] lines = new Line[n];
        for (int i = 0; i < n; i++) {
            lines[i] = points[2 * i].line(points[2 * i + 1]);
        }
        double[] angle = new double[2 * n];
        int[] order = createOrder(2 * n);
        boolean[] isOpen = new boolean[2 * n];
        for (int i = 0; i < 2 * n; i++) {
            fill(isOpen, false);
            for (int j = 0; j < 2 * n; j++) {
                if (points[i].equals(points[j])) {
                    distance[i][j] = 0;
                    angle[j] = Double.POSITIVE_INFINITY;
                } else {
                    angle[j] = atan2(points[j].y - points[i].y, points[j].x - points[i].x);
                }
            }
            for (int j = 0; j < 2 * n; j++) {
                if (angle[j] == Double.POSITIVE_INFINITY) {
                    angle[j ^ 1] = Double.POSITIVE_INFINITY;
                }
            }
            Point start = points[i];
            shift = new Line(start, -PI);
            NavigableSet<Line> set = new TreeSet<>((a, b) -> Double.compare(getDistance(start, a), getDistance(start,
                    b)));
            for (int j = 0; j < n; j++) {
                if (angle[2 * j] == Double.POSITIVE_INFINITY) {
                    continue;
                }
                if (Math.abs(angle[2 * j] - angle[2 * j + 1]) < PI) {
                    if (angle[2 * j] < angle[2 * j + 1]) {
                        isOpen[2 * j] = true;
                    } else {
                        isOpen[2 * j + 1] = true;
                    }
                } else {
                    set.add(lines[j]);
                    if (angle[2 * j] > angle[2 * j + 1]) {
                        isOpen[2 * j] = true;
                    } else {
                        isOpen[2 * j + 1] = true;
                    }
                }
            }
            sort(order, (x, y) -> {
                if (abs(angle[x] - angle[y]) < GeometryUtils.epsilon) {
                    if (isOpen[x] == isOpen[y]) {
                        return 0;
                    }
                    if (isOpen[x]) {
                        return -1;
                    }
                    return 1;
                }
                return Double.compare(angle[x], angle[y]);
            });
            for (int j : order) {
                if (angle[j] == Double.POSITIVE_INFINITY) {
                    break;
                }
                if (!isOpen[j]) {
                    shift = new Line(start, angle[j] - GeometryUtils.epsilon);
                    if (lines[j >> 1] == set.first()) {
                        distance[i][j] = points[i].distance(points[j]);
                    }
                    set.remove(lines[j >> 1]);
                } else {
                    shift = new Line(start, angle[j] + GeometryUtils.epsilon);
                    set.add(lines[j >> 1]);
                    if (lines[j >> 1] == set.first()) {
                        distance[i][j] = points[i].distance(points[j]);
                    }
                }
            }
        }
        IndependentSetSystem setSystem = new RecursiveIndependentSetSystem(2 * n);
        double answer = 0;
        List<IntIntPair> edges = new ArrayList<>();
        for (int i = 0; i < 2 * n; i++) {
            for (int j = 0; j < i; j++) {
                if (distance[i][j] != Double.POSITIVE_INFINITY) {
                    edges.add(new IntIntPair(i, j));
                }
            }
        }
        Collections.sort(edges, (x, y) -> Double.compare(distance[x.first][x.second], distance[y.first][y.second]));
        for (IntIntPair edge : edges) {
            if (setSystem.join(edge.first, edge.second)) {
                answer += distance[edge.first][edge.second];
            }
        }
        out.printLine(answer);
    }

    private double getDistance(Point start, Line line) {
        return shift.intersect(line).distance(start);
    }
}
