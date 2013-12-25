package on2013_12.on2013_12_13_Codeforces_Round__219.C___Watching_Fireworks_is_Fun;



import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int length = in.readInt();
		int count = in.readInt();
		long speed = in.readInt();
		int[] at = new int[count];
		int[] base = new int[count];
		int[] time = new int[count];
		IOUtils.readIntArrays(in, at, base, time);
		MiscUtils.decreaseByOne(at, time);
		long[] answer = new long[length];
		long[] nextAnswer = new long[length];
		int[] order = new int[length];
		int last = 0;
		for (int i = 0; i < count; i++) {
			int delta = (int) Math.min(speed * (time[i] - last), length);
			int head = 0;
			int size = 0;
			for (int j = 0; j < delta; j++) {
				order[size++] = j;
				while (size > 1 && answer[order[size - 1]] <= answer[order[size - 2]]) {
					order[size - 2] = order[size - 1];
					size--;
				}
			}
			for (int j = 0; j < length; j++) {
				if (j + delta < length) {
					order[size++] = j + delta;
					while (size > 1 && answer[order[size - 1]] <= answer[order[size - 2]]) {
						order[size - 2] = order[size - 1];
						size--;
					}
				}
				if (j - order[head] > delta)
					head++;
				nextAnswer[j] = answer[order[head]] + Math.abs(j - at[i]);
			}
			long[] temp = answer;
			answer = nextAnswer;
			nextAnswer = temp;
			last = time[i];
		}
		out.printLine(ArrayUtils.sumArray(base) - ArrayUtils.minElement(answer));
/*
		long[] answer = new long[2 * count];
		long[] position = new long[2 * count];
		long[] nextAnswer = new long[2 * count];
		long[] nextPosition = new long[2 * count];
		for (int i = 0; i < count; i++) {
			position[2 * i] = normalize(at[i] - time[i] * speed, length);
			position[2 * i + 1] = normalize(at[i] + time[i] * speed, length);
		}
		int[] order = ArrayUtils.order(position);
		ArrayUtils.order(order, position);
		int last = 0;
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < count; j++) {
				nextPosition[2 * j] = normalize(at[j] - (time[i] - time[j]) * speed, length);
				nextPosition[2 * j + 1] = normalize(at[j] + (time[i] - time[j]) * speed, length);
			}
			final long[] finalNextPosition = nextPosition;
			ArrayUtils.sort(order, new IntComparator() {
				public int compare(int first, int second) {
					return Long.compare(finalNextPosition[first], finalNextPosition[second]);
				}
			});
			ArrayUtils.order(order, nextPosition);
			final long[] finalAnswer = answer;
			Heap heap = new Heap(new IntComparator() {
				public int compare(int first, int second) {
					return Long.compare(finalAnswer[first], finalAnswer[second]);
				}
			}, 2 * count);
			int k = 0;
			for (int j = 0; j < 2 * count; j++) {
				while (k < 2 * count && position[k] - nextPosition[j] <= speed * (time[i] - last))
					heap.add(k++);
				int result;
				while (true) {
					result = heap.peek();
					if (nextPosition[j] - position[result] > speed * (time[i] - last))
						heap.poll();
					else
						break;
				}
				nextAnswer[j] = answer[result] + Math.abs(at[i] - nextPosition[j]);
			}
			long[] temp = answer;
			answer = nextAnswer;
			nextAnswer = temp;
			temp = position;
			position = nextPosition;
			nextPosition = temp;
			last = time[i];
		}
		out.printLine(ArrayUtils.sumArray(base) - ArrayUtils.minElement(answer));*/
    }

	private long normalize(long position, int length) {
		return Math.max(1, Math.min(length, position));
	}
}
