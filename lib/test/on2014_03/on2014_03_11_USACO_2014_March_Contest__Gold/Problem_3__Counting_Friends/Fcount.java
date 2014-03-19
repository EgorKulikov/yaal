package on2014_03.on2014_03_11_USACO_2014_March_Contest__Gold.Problem_3__Counting_Friends;



import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntList;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class Fcount {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] list = IOUtils.readIntArray(in, count + 1);
		int[] degrees = new int[count];
		long sum = ArrayUtils.sumArray(list);
		IntList answer = new IntArrayList();
		int[] result = new int[count];
		for (int i = 0; i <= count; i++) {
			if (sum % 2 != list[i] % 2)
				continue;
			if (list[i] >= count || list[i] < 0) {
				answer.add(i + 1);
				continue;
			}
			if (result[list[i]] != 0) {
				if (result[list[i]] == 1)
					answer.add(i + 1);
				continue;
			}
			System.arraycopy(list, 0, degrees, 0, i);
			System.arraycopy(list, i + 1, degrees, i, count - i);
			Arrays.sort(degrees);
			boolean good = true;
			for (int j = 0; j < count; j++) {
				if (count - degrees[j] <= j) {
					good = false;
					break;
				}
				for (int k = count - 1; k > j && k >= count - degrees[j]; k--)
					degrees[k]--;
				if (degrees[j] == 0)
					continue;
				int at = count - degrees[j];
				int up = at;
				while (up < count - 1 && degrees[up + 1] == degrees[at])
					up++;
				int down = at;
				while (down > j + 1 && degrees[down - 1] == degrees[at] + 1)
					down--;
				int length = Math.min(up - at + 1, at - down);
				for (int k = down; k < down + length; k++)
					degrees[k]--;
				for (int k = up - length + 1; k <= up; k++)
					degrees[k]++;
				degrees[j] = 0;
			}
			if (good) {
				result[list[i]] = 1;
				answer.add(i + 1);
			} else
				result[list[i]] = -1;
		}
		out.printLine(answer.size());
		for (int i : answer.toArray())
			out.printLine(i);
    }
}
