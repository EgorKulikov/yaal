package on2012_01.on2012_0_7.taska;



import net.egork.geometry.GeometryUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int R = in.readInt();
		int r = in.readInt();
		if (R < r) {
			out.printLine("NO");
			return;
		}
		R -= r;
		if (count == 1) {
			out.printLine("YES");
			return;
		}
		if (r > R) {
			out.printLine("NO");
			return;
		}
		double angle = Math.asin((double)r / R);
		if (angle * count < Math.PI + GeometryUtils.epsilon)
			out.printLine("YES");
		else
			out.printLine("NO");
	}
}
