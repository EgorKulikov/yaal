package on2014_01.on2014_01_12_Codeforces_Round__223__Div__1_.A___Sereja_and_Prefixes;



import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntList;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] type = new int[count];
		int[] toAdd = new int[count];
		int[] prefix = new int[count];
		int[] repeats = new int[count];
		for (int i = 0; i < count; i++) {
			type[i] = in.readInt();
			if (type[i] == 1)
				toAdd[i] = in.readInt();
			else {
				prefix[i] = in.readInt();
				repeats[i] = in.readInt();
			}
		}
		long[] length = new long[count + 1];
		IntList sequence = new IntArrayList();
		for (int i = 0; i < count; i++) {
			if (sequence.size() < 100000) {
				if (type[i] == 1)
					sequence.add(toAdd[i]);
				else {
					for (int j = 0; j < repeats[i] && sequence.size() < 100000; j++) {
						for (int k = 0; k < prefix[i] && sequence.size() < 100000; k++)
							sequence.add(sequence.get(k));
					}
				}
			}
			if (type[i] == 1)
				length[i + 1] = length[i] + 1;
			else
				length[i + 1] = length[i] + repeats[i] * prefix[i];
		}
		for (int i = 0; i <= count; i++)
			length[i] *= 2;
		int queryCount = in.readInt();
		long[] queries = IOUtils.readLongArray(in, queryCount);
		int[] answer = new int[queryCount];
		int i = 0;
		for (long query : queries) {
			query--;
			int at = -Arrays.binarySearch(length, 2 * query + 1) - 2;
			if (type[at] == 1)
				answer[i++] = toAdd[at];
			else
				answer[i++] = sequence.get((int) ((query - (length[at] >> 1)) % prefix[at]));
		}
		out.printLine(answer);
    }
}
