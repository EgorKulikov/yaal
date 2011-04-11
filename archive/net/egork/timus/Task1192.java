package net.egork.timus;

import net.egork.geometry.GeometryUtils;
import net.egork.numbers.DoubleUtils;
import net.egork.utils.Solver;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;

public class Task1192 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int v = in.readInt();
		int angle = in.readInt();
		double k = in.readDouble();
		out.printf("%.2f\n", DoubleUtils.sumGeometricProgression(
			GeometryUtils.missileTrajectoryLength(v, angle * 3.1415926535 / 180, 10), 1 / k));
	}
}

