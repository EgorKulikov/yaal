package net.egork;

import net.egork.geometry.Circle;
import net.egork.geometry.GeometryUtils;
import net.egork.geometry.Point;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Sight {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		double radius = in.readInt();
		double[] inAngle = new double[count];
		double[] outAngle = new double[count];
		Circle silo = new Circle(new Point(0, 0), radius);
		double rotate = 1;
		for (int i = 0; i < count; i++) {
			Point point = Point.readPoint(in);
			point = point.rotate(rotate);
			Point[] touching = silo.findTouchingPoints(point);
			inAngle[i] = touching[0].angle();
			outAngle[i] = touching[1].angle();
			double delta = GeometryUtils.canonicalAngle(outAngle[i] - inAngle[i]);
			if (delta < 0) {
				double temp = inAngle[i];
				inAngle[i] = outAngle[i];
				outAngle[i] = temp;
			}
		}
		int start = 0;
		for (int i = 0; i < count; i++) {
			if (inAngle[i] > 0 && outAngle[i] < 0)
				start++;
		}
		int[] inOrder = ArrayUtils.order(inAngle);
		int[] outOrder = ArrayUtils.order(outAngle);
		long answer = 0;
		int j = 0;
		for (int i : outOrder) {
			while (j < count && inAngle[inOrder[j]] < outAngle[i]) {
				start++;
				j++;
			}
			start--;
			answer += start;
		}
		out.printLine(answer);
    }
}
