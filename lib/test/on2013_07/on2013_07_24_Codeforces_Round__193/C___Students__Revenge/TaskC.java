package on2013_07.on2013_07_24_Codeforces_Round__193.C___Students__Revenge;



import net.egork.collections.comparators.IntComparator;
import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntList;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int pass = in.readInt();
		int execute = in.readInt();
		final int[] hair = new int[count];
		final int[] wraith = new int[count];
		IOUtils.readIntArrays(in, hair, wraith);
		int[] order = ArrayUtils.createOrder(count);
		ArrayUtils.sort(order, new IntComparator() {
			public int compare(int first, int second) {
				if (wraith[first] != wraith[second])
					return wraith[first] - wraith[second];
				return hair[second] - hair[first];
			}
		});
		ArrayUtils.sort(order, pass - execute, count, new IntComparator() {
			public int compare(int first, int second) {
				if (hair[first] != hair[second])
					return hair[first] - hair[second];
				return wraith[first] - wraith[second];
			}
		});
		int maxWraith = Integer.MAX_VALUE;
		int minHair = 0;
		IntList answer = new IntArrayList(pass);
		for (int i = count - execute; i < count; i++) {
			int j = order[i];
			answer.add(j + 1);
			if (wraith[j] < maxWraith || wraith[j] == maxWraith && hair[j] > minHair) {
				maxWraith = wraith[j];
				minHair = hair[j];
			}
		}
		ArrayUtils.sort(order, 0, count - execute, new IntComparator() {
			public int compare(int first, int second) {
				if (wraith[first] != wraith[second])
					return wraith[second] - wraith[first];
				return hair[second] - hair[first];
			}
		});
		for (int i = 0; i < count - execute && answer.size() < pass; i++) {
			int j = order[i];
			if (wraith[j] < maxWraith || wraith[j] == maxWraith && hair[j] >= minHair)
				answer.add(j + 1);
		}
		out.printLine(answer);
    }
}
