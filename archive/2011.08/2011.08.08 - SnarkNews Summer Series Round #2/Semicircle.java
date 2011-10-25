import net.egork.geometry.GeometryUtils;
import net.egork.io.IOUtils;
import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;

public class Semicircle implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int count = in.readInt();
		double[] angles = IOUtils.readDoubleArray(in, count);
		double l = in.readDouble();
		double lAngle = Math.asin(l / 2) / Math.PI * 180;
		double curAngle = 90;
		for (int i = 0; i < count; i++) {
			curAngle -= angles[i];
			if (curAngle < GeometryUtils.epsilon) {
				out.println("Out after " + (i + 1));
				return;
			}
			if (curAngle < lAngle + GeometryUtils.epsilon) {
				out.println("Reached after " + (i + 1));
				return;
			}
		}
	}
}

