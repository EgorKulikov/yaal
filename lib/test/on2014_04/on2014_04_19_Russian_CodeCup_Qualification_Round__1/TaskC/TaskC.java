package on2014_04.on2014_04_19_Russian_CodeCup_Qualification_Round__1.TaskC;



import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntList;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] at = IOUtils.readIntArray(in, count);
		MiscUtils.decreaseByOne(at);
		IntList[] lists = new IntList[count];
		for (int i = 0; i < count; i++) {
			if (lists[at[i]] == null)
				lists[at[i]] = new IntArrayList();
			lists[at[i]].add(i);
		}
		int current = 1;
		int[] answer = new int[count];
		for (IntList list : lists) {
			if (list == null)
				continue;
			int[] order = list.toArray();
			ArrayUtils.reverse(order);
			for (int i : order)
				answer[i] = current++;
		}
		out.printLine(answer);
    }
}
