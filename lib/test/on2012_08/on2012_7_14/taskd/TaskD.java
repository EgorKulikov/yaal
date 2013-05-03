package on2012_08.on2012_7_14.taskd;



import net.egork.misc.ArrayUtils;
import net.egork.collections.comparators.IntComparator;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[][] positions = new int[count][];
		for (int i = 0; i < count; i++)
			positions[i] = IOUtils.readIntArray(in, in.readInt());
		for (int[] row : positions)
			ArrayUtils.sort(row, IntComparator.DEFAULT);
		int answer = 0;
		for (int i = 0; i < count; i++) {
			int last = (i + count - 1) % count;
			int next = (i + 1) % count;
			int lastIndex = 0;
			int nextIndex = 0;
			for (int j = 0; j < positions[i].length; j++) {
				int value = positions[i][j];
				int left = 0;
				int right = 0;
				while (lastIndex < positions[last].length && positions[last][lastIndex] < value) {
					lastIndex++;
					left++;
				}
				while (nextIndex < positions[next].length && positions[next][nextIndex] < value) {
					nextIndex++;
					right++;
				}
				if (j != 0 && left != right)
					answer++;
			}
		}
		out.printLine(answer);
	}
}
