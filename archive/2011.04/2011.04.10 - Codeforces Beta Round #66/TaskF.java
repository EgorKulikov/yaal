package April2011.CodeforcesBetaRound66;

import net.egork.geometry.GeometryUtils;
import net.egork.utils.Solver;

import java.io.PrintWriter;

public class TaskF implements Solver {
	private double length;

	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		double startX = in.readDouble();
		double startY = in.readDouble();
		double finishX = in.readDouble();
		double finishY = in.readDouble();
		int tankCount = in.readInt();
		double[] x = new double[tankCount];
		double[] y = new double[tankCount];
		double[] angle = new double[tankCount];
		double[] speed = new double[tankCount];
		in.readDoubleArrays(x, y, angle, speed);
		int maxHits = in.readInt();
		if (maxHits == tankCount) {
			out.println(0);
			return;
		}
		double left = 0;
		length = GeometryUtils.fastHypot(startX - finishX, startY - finishY);
		double right = length * 10;
		for (int it = 0; it < 60; it++) {
			double currentSpeed = (left + right) / 2;
			int hitCount = 0;
			for (int i = 0; i < tankCount && hitCount <= maxHits && tankCount - i + hitCount > maxHits; i++) {
				if (canHit(startX, startY, finishX, finishY, currentSpeed, x[i], y[i], angle[i], speed[i]))
					hitCount++;
			}
			if (hitCount <= maxHits)
				right = currentSpeed;
			else
				left = currentSpeed;
		}
		out.printf("%.5f\n", (left + right) / 2);
	}

	private boolean canHit(double startX, double startY, double finishX, double finishY, double currentSpeed, double x, double y, double angle, double speed) {
		double left = 0;
		double right = length / currentSpeed;
		for (int it = 0; it < 100; it++) {
			double left1 = (2 * left + right) / 3;
			double right1 = (2 * right + left) / 3;
			double time1 = evaluate(left1, startX, startY, finishX, finishY, currentSpeed, x, y, angle, speed);
			double time2 = evaluate(left1, startX, startY, finishX, finishY, currentSpeed, x, y, angle, speed);
			if (time1 < 0 || time2 < 0)
				return true;
			if (time1 < time2)
				right = right1;
			else
				left = left1;
		}
		return false;
	}

	private double evaluate(double time, double startX, double startY, double finishX, double finishY, double currentSpeed, double x, double y, double angle, double speed) {
		double tankX = startX + (finishX - startX) / length * time * currentSpeed;
		double tankY = startY + (finishY - startY) / length * time * currentSpeed;
		double diffAngle = Math.abs(angle - Math.atan2(tankY - y, tankX - x));
		if (diffAngle > 2 * Math.PI)
			diffAngle -= 2 * Math.PI;
		diffAngle = Math.min(diffAngle, 2 * Math.PI - diffAngle);
		return diffAngle / speed - time;
	}

}

