package net.egork;

import net.egork.geometry.GeometryUtils;
import net.egork.geometry.Point;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		Point point = Point.readPoint(in);
		out.printFormat("%.6f\n", GeometryUtils.positiveAngle(point.angle()));
    }
}
