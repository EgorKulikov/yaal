package on2013_08.on2013_08_August_Challenge_2013.Sereja_and_Ballons;



import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;
import java.util.Random;

public class SerejaAndBallons {
	Random random = new Random(239);
	int[] remaining;
	int[] last;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int queryCount = in.readInt();
		int[] qty = IOUtils.readIntArray(in, count);
		int[] from = new int[queryCount];
		int[] to = new int[queryCount];
		IOUtils.readIntArrays(in, from, to);
		int changeCount = in.readInt();
		int[] x = IOUtils.readIntArray(in, changeCount);
		MiscUtils.decreaseByOne(from, x);
		int answer = 0;
		int[] first = new int[count];
		int[] next = new int[queryCount];
		Arrays.fill(first, -1);
		remaining = new int[4 * count];
		last = new int[4 * count];
		init(0, 0, count);
		for (int i = 0; i < queryCount; i++) {
			int at = random.nextInt(to[i] - from[i]) + from[i];
			next[i] = first[at];
			first[at] = i;
		}
		for (int i : x) {
			i += answer;
			remove(0, 0, count, i);
			if (--qty[i] == 0) {
				for (int j = first[i]; j != -1;) {
					int k = next[j];
					if (query(0, 0, count, from[j], to[j]) == 0)
						answer++;
					else {
						int at = random(0, 0, count, from[j], to[j]);
						next[j] = first[at];
						first[at] = j;
					}
					j = k;
				}
			}
			out.printLine(answer);
		}
    }

	private void init(int root, int left, int right) {
		remaining[root] = right - left;
		if (right - left > 1) {
			int middle = (left + right) >> 1;
			init(2 * root + 1, left, middle);
			init(2 * root + 2, middle, right);
		}
	}

	private int query(int root, int left, int right, int from, int to) {
		if (left >= to || right <= from)
			return last[root] = 0;
		if (left >= from && right <= to)
			return remaining[root];
		int middle = (left + right) >> 1;
		return last[root] = query(2 * root + 1, left, middle, from, to) + query(2 * root + 2, middle, right, from, to);
	}

	private void remove(int root, int left, int right, int position) {
		if (left > position || right <= position)
			return;
		remaining[root]--;
		if (right - left > 1) {
			int middle = (left + right) >> 1;
			remove(2 * root + 1, left, middle, position);
			remove(2 * root + 2, middle, right, position);
		}
	}

	private int random(int root, int left, int right, int from, int to) {
		if (right - left == 1)
			return left;
		int middle = (left + right) >> 1;
		int inLeft = specialQuery(2 * root + 1, left, middle, from, to);
		int inRight = specialQuery(2 * root + 2, middle, right, from, to);
		if (inRight == 0 || inLeft != 0 && random.nextInt(inLeft + inRight) < inLeft)
			return random(2 * root + 1, left, middle, from, to);
		else
			return random(2 * root + 2, middle, right, from, to);
	}

	private int specialQuery(int root, int left, int right, int from, int to) {
		if (left >= from && right <= to)
			return remaining[root];
		return last[root];
	}
}
