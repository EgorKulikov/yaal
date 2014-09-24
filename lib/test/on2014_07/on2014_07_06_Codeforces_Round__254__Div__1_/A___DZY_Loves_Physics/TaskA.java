package on2014_07.on2014_07_06_Codeforces_Round__254__Div__1_.A___DZY_Loves_Physics;



import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int edgeCount = in.readInt();
		int[] values = IOUtils.readIntArray(in, count);
		int[] from = new int[edgeCount];
		int[] to = new int[edgeCount];
		int[] edgeValue = new int[edgeCount];
		IOUtils.readIntArrays(in, from, to, edgeValue);
		MiscUtils.decreaseByOne(from, to);
		if (edgeCount == 0) {
			out.printLine(0);
			return;
		}
		double answer = 0;
		for (int i = 0; i < edgeCount; i++) {
			answer = Math.max(answer, (double)(values[from[i]] + values[to[i]]) / edgeValue[i]);
		}
		out.printLine(answer);
    }
}
