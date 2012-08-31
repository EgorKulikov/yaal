package on2012_07.on2012_6_21.taskb;



import net.egork.collections.map.TreeCounter;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] frontColor = new int[count];
		int[] backColor = new int[count];
		IOUtils.readIntArrays(in, frontColor, backColor);
		TreeCounter<Integer> counter = new TreeCounter<Integer>();
		for (int i : frontColor)
			counter.add(i);
		for (int i : backColor)
			counter.add(i);
		int threshold = (count + 1) >> 1;
		int answer = Integer.MAX_VALUE;
		for (int i : frontColor) {
			if (counter.get(i) >= threshold) {
				int front = 0;
				int back = 0;
				for (int j = 0; j < count; j++) {
					if (frontColor[j] == i)
						front++;
					else if (backColor[j] == i)
						back++;
				}
				if (front + back >= threshold)
					answer = Math.min(answer, Math.max(threshold - front, 0));
			}
			counter.remove(i);
		}
		for (int i : backColor) {
			if (counter.get(i) >= threshold) {
				int front = 0;
				int back = 0;
				for (int j = 0; j < count; j++) {
					if (frontColor[j] == i)
						front++;
					else if (backColor[j] == i)
						back++;
				}
				if (front + back >= threshold)
					answer = Math.min(answer, Math.max(threshold - front, 0));
			}
			counter.remove(i);
		}
		if (answer == Integer.MAX_VALUE)
			answer = -1;
		out.printLine(answer);
	}
}
