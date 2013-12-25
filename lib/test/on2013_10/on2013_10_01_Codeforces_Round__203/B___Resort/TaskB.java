package on2013_10.on2013_10_01_Codeforces_Round__203.B___Resort;



import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntList;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] type = IOUtils.readIntArray(in, count);
		int[] parent = IOUtils.readIntArray(in, count);
		MiscUtils.decreaseByOne(parent);
		int[] degree = new int[count];
		for (int i : parent) {
			if (i != -1)
				degree[i]++;
		}
		IntList answer = new IntArrayList();
		for (int i = 0; i < count; i++) {
			if (type[i] == 0)
				continue;
			IntList candidate = new IntArrayList();
			int current = i;
			do {
				candidate.add(current + 1);
				current = parent[current];
			} while (current != -1 && degree[current] == 1);
			if (candidate.size() > answer.size())
				answer = candidate;
		}
		answer.inPlaceReverse();
		out.printLine(answer.size());
		out.printLine(answer);
    }
}
