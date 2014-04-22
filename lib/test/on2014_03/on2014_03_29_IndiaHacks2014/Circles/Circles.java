package on2014_03.on2014_03_29_IndiaHacks2014.Circles;



import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Circles {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] x = new int[count];
		int[] y = new int[count];
		IOUtils.readIntArrays(in, x, y);
		int minX = ArrayUtils.minElement(x);
		int maxX = ArrayUtils.maxElement(x);
		int minY = ArrayUtils.minElement(y);
		int maxY = ArrayUtils.maxElement(y);
		int cx = (maxX + minX) / 2;
		int cy = (minY + maxY) / 2;
		int mDist = 0;
		for (int i = 0; i < count; i++) {
			int dx = cx - x[i];
			int dy = cy - y[i];
			mDist = Math.max(mDist, dx * dx + dy * dy);
		}
		mDist = (int) Math.round(Math.ceil(Math.sqrt(mDist) - 1e-4));
		if (mDist > count) {
			out.printLine(count);
			for (int i = 0; i < count; i++) {
				out.printLine(x[i], y[i], 1);
			}
			return;
		}
		out.printLine(1);
		out.printLine(cx, cy, mDist);
    }
}
