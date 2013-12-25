package on2013_02.on2013_02_01_Codeforces_Round__165.A___Magical_Boxes;



import net.egork.misc.ArrayUtils;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] size = new int[count];
		int[] qty = new int[count];
		IOUtils.readIntArrays(in, size, qty);
		int[] order = ArrayUtils.order(size);
		int lastSize = 0;
		int lastQty = 0;
		for (int i : order) {
			int delta = size[i] - lastSize;
			while (delta > 0 && lastQty > qty[i]) {
				lastQty = (lastQty + 3) >> 2;
				delta--;
			}
			lastQty = Math.max(lastQty, qty[i]);
			lastSize = size[i];
		}
		lastSize++;
		lastQty = (lastQty + 3) >> 2;
		while (lastQty > 1) {
			lastQty = (lastQty + 3) >> 2;
			lastSize++;
		}
		out.printLine(lastSize);
	}
}
