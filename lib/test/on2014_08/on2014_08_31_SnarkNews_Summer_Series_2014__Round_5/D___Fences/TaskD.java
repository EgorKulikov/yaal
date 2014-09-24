package on2014_08.on2014_08_31_SnarkNews_Summer_Series_2014__Round_5.D___Fences;



import net.egork.geometry.Point;
import net.egork.geometry.Polygon;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int cx = in.readInt();
		int cy = in.readInt();
		int r = in.readInt();
		int answer = 0;
		if (cx * cx + cy * cy <= r * r) {
			answer++;
		}
		int sx = in.readInt();
		int sy = in.readInt();
		int tx = in.readInt();
		int ty = in.readInt();
		if (sx <= 0 && 0 <= tx && sy <= 0 && 0 <= ty) {
			answer++;
		}
		int[] x = new int[3];
		int[] y = new int[3];
		IOUtils.readIntArrays(in, x, y);
		Point[] points = new Point[3];
		for (int i = 0; i < 3; i++) {
			points[i] = new Point(x[i], y[i]);
		}
		if (new Polygon(points).contains(Point.ORIGIN)) {
			answer++;
		}
		out.printLine(answer);
    }
}
