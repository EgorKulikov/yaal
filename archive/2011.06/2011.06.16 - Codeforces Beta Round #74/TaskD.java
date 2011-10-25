import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;

public class TaskD implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int x = in.readInt();
		int y = in.readInt();
		int z = in.readInt();
		int dx = in.readInt();
		int dy = in.readInt();
		int dz = in.readInt();
		int R = in.readInt();
		int mineCount = in.readInt();
		double answer = Double.POSITIVE_INFINITY;
		for (int i = 0; i < mineCount; i++) {
			int cx = in.readInt();
			int cy = in.readInt();
			int cz = in.readInt();
			int cr = in.readInt();
			answer = Math.min(answer, check(x - cx, y - cy, z - cz, dx, dy, dz, R + cr));
			int rayCount = in.readInt();
			for (int j = 0; j < rayCount; j++) {
				int px = in.readInt();
				int py = in.readInt();
				int pz = in.readInt();
				answer = Math.min(answer, check(x - cx - px, y - cy - py, z - cz - pz, dx, dy, dz, R));
			}
		}
		if (answer == Double.POSITIVE_INFINITY)
			out.println("-1");
		else
			out.printf("%.10f\n", answer);
	}

	private double check(long x, long y, long z, long dx, long dy, long dz, long r) {
		long a = dx * dx + dy * dy + dz * dz;
		long b = x * dx + y * dy + z * dz;
		long c = x * x + y * y + z * z - r * r;
		if (c <= 0)
			throw new RuntimeException();
		if (b * b < a * c || b > 0)
			return Double.POSITIVE_INFINITY;
		return (-b - Math.sqrt(b * b - a * c)) / a;
	}
}

