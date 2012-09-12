package on2012_09.on2012_09_September_Challenge_2012.Queries_About_Numbers;



import net.egork.collections.Pair;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class QueriesAboutNumbers {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		long number = in.readLong();
		long copyNumber = number;
		List<Pair<Long, Integer>> list = new ArrayList<Pair<Long, Integer>>();
		for (long i = 2; i * i <= number; i++) {
			if (number % i == 0) {
				int power = 0;
				do {
					number /= i;
					power++;
				} while (number % i == 0);
				list.add(Pair.makePair(i, power));
			}
		}
		if (number != 1)
			list.add(Pair.makePair(number, 1));
		go(list, 1, 0, 1);
		number = copyNumber;
		int queryCount = in.readInt();
		for (int i = 0; i < queryCount; i++) {
			int type = in.readInt();
			long otherNumber = in.readLong();
			if (type == 1)
				out.printLine(get(IntegerUtils.gcd(number, otherNumber)));
			else {
				int answer;
				long d = number / otherNumber;
				if (otherNumber * d == number)
					answer = get(d);
				else
					answer = 0;
				if (type == 2)
					out.printLine(answer);
				else
					out.printLine(size - answer);
			}
		}
	}

	private void go(List<Pair<Long, Integer>> list, long number, int step, int value) {
		if (step == list.size()) {
			add(number, value);
			return;
		}
		int count = list.get(step).second;
		long delta = list.get(step).first;
		for (int i = 0; i <= count; i++) {
			go(list, number, step + 1, value * (i + 1));
			number *= delta;
		}
	}

	int SIZE = 100003;
	int SHIFT = 101;
	int size = 0;

	long[] key = new long[SIZE];
	int[] value = new int[SIZE];

	void add(long k, int v) {
		int curKey = (int) (k % SIZE);
		while (key[curKey] != 0 && key[curKey] != k) {
			curKey += SHIFT;
			if (curKey >= SIZE)
				curKey -= SIZE;
		}
		key[curKey] = k;
		value[curKey] = v;
		size++;
	}

	int get(long k) {
		int curKey = (int) (k % SIZE);
		while (key[curKey] != k) {
			curKey += SHIFT;
			if (curKey >= SIZE)
				curKey -= SIZE;
		}
		return value[curKey];
	}
}
