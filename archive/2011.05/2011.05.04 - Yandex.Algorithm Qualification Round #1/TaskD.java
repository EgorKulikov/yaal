import net.egork.collections.ArrayUtils;
import net.egork.collections.CollectionUtils;
import net.egork.collections.sequence.Array;
import net.egork.io.IOUtils;
import net.egork.utils.Solver;

import java.io.PrintWriter;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class TaskD implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int photoCount = in.readInt();
		int albumCount = in.readInt();
		final int[] albums = IOUtils.readIntArray(in, albumCount);
		if (albumCount == 1 || photoCount % 2 == 1 && albumCount == 2) {
			out.println(-1);
			return;
		}
		if (ArrayUtils.sumArray(albums) < photoCount) {
			out.println(-1);
			return;
		}
		int maxPhoto = (int) (ArrayUtils.sumArray(albums) - CollectionUtils.maxElement(Array.wrap(albums)));
		if (maxPhoto * 2 < photoCount) {
			out.println(-1);
			return;
		}
		int[] result = new int[photoCount];
		final int maxIndex = SequenceUtils.maxIndex(Array.wrap(albums));
		result[0] = maxIndex + 1;
		albums[maxIndex]--;
		Queue<Integer> queue = new PriorityQueue<Integer>(albumCount, new Comparator<Integer>() {
			public int compare(Integer first, Integer second) {
				if (albums[first] != albums[second])
					return albums[second] - albums[first];
				if (first == maxIndex)
					return -1;
				if (second == maxIndex)
					return 1;
				return first - second;
			}
		});
		for (int i = 0; i < albumCount; i++)
			queue.add(i);
		for (int i = 1; i < photoCount - 1; i++) {
			int index1 = queue.poll();
			int index2 = queue.poll();
			if (result[i - 1] == index1 + 1) {
				albums[index2]--;
				result[i] = index2 + 1;
			} else {
				albums[index1]--;
				result[i] = index1 + 1;
			}
			queue.add(index1);
			queue.add(index2);
		}
		for (int i = 0; i < albumCount; i++) {
			if (albums[i] != 0 && i != maxIndex && i + 1 != result[photoCount - 2]) {
				result[photoCount - 1] = i + 1;
				break;
			}
		}
		IOUtils.printArray(result, out);
	}
}

