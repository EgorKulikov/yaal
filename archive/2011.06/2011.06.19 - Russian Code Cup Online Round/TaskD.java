import net.egork.geometry.GeometryUtils;
import net.egork.geometry.Line;
import net.egork.geometry.Point;
import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;
import java.util.Arrays;

public class TaskD implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		GeometryUtils.epsilon = 1e-11;
		int vertexCount = in.readInt();
		Point[] points = new Point[vertexCount];
		double[] pointAngle = new double[2 * vertexCount];
		for (int i = 0; i < vertexCount; i++) {
			int x = in.readInt();
			int y = in.readInt();
			points[i] = new Point(x, y);
		}
		int x = in.readInt();
		int y = in.readInt();
		for (int i = 0, pointsLength = points.length; i < pointsLength; i++) {
			points[i] = new Point(points[i].x - x, points[i].y - y);
			pointAngle[i] = convert(Math.atan2(points[i].y, points[i].x));
		}
		for (int i = 0; i < vertexCount; i++) {
			int next = i + 1;
			if (next == vertexCount)
				next = 0;
			double a = points[i].value();
			double b = points[next].value();
			double c = points[i].distance(points[next]);
			if (b * b > a * a + c * c || a * a > b * b + c * c)
				continue;
			double delta = Math.asin((a * a + c * c - b * b) / (2 * a * c));
			pointAngle[vertexCount + i] = convert(pointAngle[i] + delta);
		}
		int rayCount = in.readInt();
		double[] rayAngle = new double[rayCount];
		for (int i = 0; i < rayCount; i++)
			rayAngle[i] = in.readInt() / 180. * Math.PI;
		double[] candidates = new double[2 * rayCount * vertexCount];
		for (int i = 0; i < 2 * vertexCount; i++) {
			for (int j = 0; j < rayCount; j++)
				candidates[i * rayCount + j] = convert(pointAngle[i] - rayAngle[j]);
		}
		Arrays.sort(candidates);
		Line[] lines = new Line[vertexCount];
		for (int i = 0; i < vertexCount; i++)
			lines[i] = points[i].line(points[(i + 1) % vertexCount]);
		double answer = Double.POSITIVE_INFINITY;
		for (int i = 1; i < candidates.length; i++)
			answer = Math.min(answer, count(candidates[i - 1], candidates[i], rayAngle, points, lines));
		answer = Math.min(answer, count(candidates[candidates.length - 1], candidates[0] + 2 * Math.PI, rayAngle, points, lines));
		out.printf("%.10f\n", answer);
	}

	private double count(double left, double right, double[] rayAngle, Point[] points, Line[] lines) {
		if (right - left < GeometryUtils.epsilon)
			return Double.POSITIVE_INFINITY;
		int[] index = new int[rayAngle.length];
		double delta = (left + right) / 2;
		for (int i = 0; i < rayAngle.length; i++) {
			double a = Math.sin(rayAngle[i] + delta);
			double b = -Math.cos(rayAngle[i] + delta);
			for (int j = 0; j < points.length; j++) {
				int next = j + 1;
				if (next == points.length)
					next = 0;
				if (a * points[j].x + b * points[j].y > 0 && a * points[next].x + b * points[next].y < 0) {
					index[i] = j;
					break;
				}
			}
		}
		while (right - left > GeometryUtils.epsilon) {
			double mLeft = (2 * left + right) / 3;
			double mRight = (2* right + left) / 3;
			double leftValue = value(rayAngle, index, lines, mLeft);
			double rightValue = value(rayAngle, index, lines, mRight);
			if (leftValue < rightValue)
				right = mRight;
			else
				left = mLeft;
		}
		return value(rayAngle, index, lines, (left + right) / 2);
	}

	private double value(double[] rayAngle, int[] index, Line[] lines, double delta) {
		double result = 0;
		for (int i = 0; i < rayAngle.length; i++) {
			Line ray = new Line(Math.sin(rayAngle[i] + delta), -Math.cos(rayAngle[i] + delta), 0);
			result += ray.intersect(lines[index[i]]).value();
		}
		return result;
	}

	private double convert(double angle) {
		while (angle < -GeometryUtils.epsilon)
			angle += 2 * Math.PI;
		while (angle > 2 * Math.PI - GeometryUtils.epsilon)
			angle -= 2 * Math.PI;
		return angle;
	}
}

