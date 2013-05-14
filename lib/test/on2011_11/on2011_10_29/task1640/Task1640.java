package on2011_11.on2011_10_29.task1640;



import net.egork.geometry.GeometryUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Task1640 {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int x = 0;
		int y = 1001;
		double r = 0;
		int count = in.readInt();
		for (int i = 0; i < count; i++) {
			int curX = in.readInt();
			int curY = in.readInt();
			r = Math.max(r, GeometryUtils.fastHypot(curX - x, curY - y));
		}
		out.printLine(x, y, r);
	}
}
