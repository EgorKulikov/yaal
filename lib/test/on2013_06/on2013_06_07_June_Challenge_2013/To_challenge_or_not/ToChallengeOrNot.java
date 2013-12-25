package on2013_06.on2013_06_07_June_Challenge_2013.To_challenge_or_not;



import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntHashSet;
import net.egork.collections.intcollection.IntList;
import net.egork.collections.intcollection.IntSet;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;
import java.util.Random;

public class ToChallengeOrNot {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		Random random = new Random(239);
		int count = in.readInt();
		int[] numbers = IOUtils.readIntArray(in, count);
		Arrays.sort(numbers);
		IntList answer = trivial(numbers);
		{
			ArrayUtils.reverse(numbers);
			IntList candidate = trivial(numbers);
			if (candidate.size() > answer.size())
				answer = candidate;
			ArrayUtils.reverse(numbers);
		}
		{
			IntList candidate = new IntArrayList();
			go(numbers, 0, count - 1, candidate);
			IntSet bad = new IntHashSet();
			for (int i = 0; i < candidate.size(); i++) {
				int k = candidate.get(i);
				bad.add(k);
				for (int j = i + 1; j < candidate.size(); j++) {
					int l = candidate.get(j);
					bad.add(2 * k - l);
					bad.add(2 * l - k);
					if ((k & 1) == (l & 1))
						bad.add((k + l) >> 1);
				}
			}
			IntList candidateAlt = new IntArrayList(candidate);
			for (int i : numbers) {
				if (!bad.contains(i)) {
					for (int j = 0; j < candidate.size(); j++) {
						int l = candidate.get(j);
						bad.add(2 * i - l);
						bad.add(2 * l - i);
						if ((i & 1) == (l & 1))
							bad.add((i + l) >> 1);
					}
					candidate.add(i);
				}
			}
			if (candidate.size() > answer.size())
				answer = candidate;
			candidate = candidateAlt;
			bad = new IntHashSet();
			for (int i = 0; i < candidate.size(); i++) {
				int k = candidate.get(i);
				bad.add(k);
				for (int j = i + 1; j < candidate.size(); j++) {
					int l = candidate.get(j);
					bad.add(2 * k - l);
					bad.add(2 * l - k);
					if ((k & 1) == (l & 1))
						bad.add((k + l) >> 1);
				}
			}
			ArrayUtils.reverse(numbers);
			for (int i : numbers) {
				if (!bad.contains(i)) {
					for (int j = 0; j < candidate.size(); j++) {
						int l = candidate.get(j);
						bad.add(2 * i - l);
						bad.add(2 * l - i);
						if ((i & 1) == (l & 1))
							bad.add((i + l) >> 1);
					}
					candidate.add(i);
				}
			}
			if (candidate.size() > answer.size())
				answer = candidate;
			ArrayUtils.reverse(numbers);
		}
		out.printLine(answer.size());
		out.printLine(answer);
    }

	private IntList trivial(int[] numbers) {
		IntList answer = new IntArrayList();
		IntSet bad = new IntHashSet();
		IntList current = new IntArrayList();
		for (int i : numbers) {
			if (!bad.contains(i)) {
				current.add(i);
				if (current.size() == TRIVIAL_THRESHOLD) {
					process(answer, current, bad);
					while (current.size() != 0)
						current.popBack();
				}
			}
		}
		process(answer, current, bad);
		return answer;
	}

	private void process(IntList answer, IntList current, IntSet bad) {
		int size = current.size();
		int mask = 0;
		for (int i = 1; i < (1 << size); i++) {
			if (Integer.bitCount(i) <= Integer.bitCount(mask))
				continue;
			boolean good = true;
			for (int j = 0; j < size && good; j++) {
				if ((i >> j & 1) == 0)
					continue;
				for (int k = j + 1; k < size && good; k++) {
					if ((i >> k & 1) == 0)
						continue;
					for (int l = 0; l < answer.size() && good; l++) {
						if (answer.get(l) + current.get(k) == 2 * current.get(j))
							good = false;
					}
					for (int l = k + 1; l < size && good; l++) {
						if ((i >> l & 1) == 1 && current.get(l) + current.get(j) == 2 * current.get(k))
							good = false;
					}
				}
			}
			if (good)
				mask = i;
		}
		for (int i = 0; i < size; i++) {
			if ((mask >> i & 1) == 1) {
				int k = current.get(i);
				for (int j = 0; j < answer.size(); j++)
					bad.add(2 * k - answer.get(j));
				answer.add(k);
			}
		}
	}

	final static int TRIVIAL_THRESHOLD = 12;
	final static int THRESHOLD = 18;

	private void go(int[] numbers, int from, int to, IntList candidate) {
		if (to - from + 1 <= THRESHOLD) {
			int length = to - from + 1;
			int mask = 0;
			for (int i = 1; i < (1 << length); i++) {
				if (Integer.bitCount(i) <= Integer.bitCount(mask))
					continue;
				boolean good = true;
				for (int j = 0; j < length && good; j++) {
					if ((i >> j & 1) == 0)
						continue;
					for (int k = j + 1; k < length && good; k++) {
						if ((i >> k & 1) == 0)
							continue;
						for (int l = k + 1; l < length && good; l++) {
							if ((i >> l & 1) == 1 && numbers[from + l] + numbers[from + j] == 2 * numbers[from + k])
								good = false;
						}
					}
				}
				if (good)
					mask = i;
			}
			for (int i = 0; i < length; i++) {
				if ((mask >> i & 1) == 1)
					candidate.add(numbers[from + i]);
			}
			return;
		}
		int a = numbers[from];
		int b = numbers[to];
		int c = (2 * a + b - 1) / 3;
		int d = (a + 2 * b + 2) / 3;
		int middleTo = Arrays.binarySearch(numbers, from, to + 1, c);
		if (middleTo < 0)
			middleTo = -middleTo - 2;
		int middleFrom = Arrays.binarySearch(numbers, from, to + 1, d);
		if (middleFrom < 0)
			middleFrom = -middleFrom - 1;
		while (true) {
			int cc = numbers[middleTo + 1];
			int x = middleFrom;
			boolean good = true;
			while (cc + numbers[to] >= 2 * numbers[x]) {
				if (Arrays.binarySearch(numbers, x, to + 1, 2 * numbers[x] - cc) >= 0) {
					good = false;
					break;
				}
				x++;
			}
			if (!good)
				break;
			x = middleFrom;
			while (numbers[x] + numbers[from] <= 2 * cc) {
				if (Arrays.binarySearch(numbers, from, middleTo + 1, 2 * cc - numbers[x]) >= 0) {
					good = false;
					break;
				}
				x++;
			}
			if (!good)
				break;
			middleTo++;
		}
		while (numbers[middleFrom - 1] + numbers[from] > 2 * numbers[middleTo] && numbers[middleTo] + numbers[to] < 2 * numbers[middleFrom - 1])
			middleFrom--;
		go(numbers, from, middleTo, candidate);
		go(numbers, middleFrom, to, candidate);
	}
}
