package on2013_06.on2013_06_16_Russian_CodeCup_2013_Online_Round.E_________;



import net.egork.geometry.GeometryUtils;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE {
	boolean[][] can;
	int[] x, y;
	double[][] angles;
	double middle;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		x = new int[count];
		y = new int[count];
		IOUtils.readIntArrays(in, x, y);
		double left = 0;
		double right = Math.PI;
		can = new boolean[count][count];
		angles = new double[count][count];
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < count; j++) {
				if (i != j)
					angles[i][j] = Math.atan2(y[j] - y[i], x[j] - x[i]);
			}
		}
		for (int i = 0; i < 40; i++) {
			ArrayUtils.fill(can, false);
			middle = (left + right) / 2;
			boolean success = false;
			for (int j = 1; j < count; j++) {
				if (good(j, 0)) {
					success = true;
					break;
				}
			}
			if (success)
				right = middle;
			else
				left = middle;
		}
		out.printLine((left + right) / 2 / Math.PI * 180);
    }

	private boolean good(int current, int last) {
		if (current == 0)
			return true;
		if (can[current][last])
			return false;
		can[current][last] = true;
		for (int i = 0; i < x.length; i++) {
			if (i != current) {
				double angle = Math.abs(GeometryUtils.canonicalAngle(angles[i][current] - angles[current][last]));
				if (angle < middle && good(i, current))
					return true;
			}
		}
		return false;
	}
}
