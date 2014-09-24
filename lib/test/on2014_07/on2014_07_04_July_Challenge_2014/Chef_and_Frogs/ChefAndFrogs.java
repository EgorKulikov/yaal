package on2014_07.on2014_07_04_July_Challenge_2014.Chef_and_Frogs;



import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class ChefAndFrogs {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int distance = in.readInt();
		int queryCount = in.readInt();
		int[] at = IOUtils.readIntArray(in, count);
		int[] order = ArrayUtils.order(at);
		int[] id = new int[count];
		for (int i = 1; i < count; i++) {
			if (at[order[i]] - at[order[i - 1]] > distance) {
				id[order[i]] = id[order[i - 1]] + 1;
			} else {
				id[order[i]] = id[order[i - 1]];
			}
		}
		for (int i = 0; i < queryCount; i++) {
			int first = in.readInt() - 1;
			int second = in.readInt() - 1;
			if (id[first] == id[second]) {
				out.printLine("Yes");
			} else {
				out.printLine("No");
			}
		}
    }
}
