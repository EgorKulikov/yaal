package net.egork;

import net.egork.collections.intcollection.IntArrayList;
import net.egork.collections.intcollection.IntList;
import net.egork.collections.set.TreapSet;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class DeletingNumbers {
	static class Array {
		int[] array;
		TreapSet<Integer> set = new TreapSet<Integer>();

		Array(int[] array) {
			this.array = array;
			for (int i = 0; i < array.length; i++)
				set.add(i);
		}

		int index(int index) {
			return set.get(index);
		}

		int get(int index) {
			return array[set.get(index)];
		}

		void remove(int value) {
			set.remove(value);
		}

		public int back(int value) {
			return set.headSet(value).size();
		}
	}

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] baseArray = IOUtils.readIntArray(in, count);
		MiscUtils.decreaseByOne(baseArray);
		Answer answer = new Answer(count, baseArray).invoke();
		int size = count;
		IntList first = new IntArrayList();
		IntList step = new IntArrayList();
		while (size > 0) {
			int bestFirst = size - 1;
			int bestStep = 1;
			int result = 1;
			for (int i = 1; i <= 70; i++) {
				for (int j = size - 1; j >= size - i; j--) {
					int start = j;
					int qty = 1;
					for (int k = j - i; k >= 0; k -= i) {
						if (baseArray[k] != baseArray[j])
							break;
						start = k;
						qty++;
					}
					if (qty > result) {
						bestFirst = start;
						bestStep = i;
						result = qty;
					}
				}
			}
			first.add(bestFirst + 1);
			step.add(bestStep);
			int k = bestFirst;
			int next = bestFirst + bestStep;
			for (int j = bestFirst + 1; j < size; j++) {
				if (j == next)
					next += bestStep;
				else
					baseArray[k++] = baseArray[j];
			}
			size = k;
		}
		if (answer.first.size() < first.size()) {
			first = answer.first;
			step = answer.step;
		}
		out.printLine(first.size());
		for (int i = 0; i < first.size(); i++)
			out.printLine(first.get(i), step.get(i));
    }

	private class Answer {
		private int count;
		private int[] baseArray;
		private IntList first;
		private IntList step;

		public Answer(int count, int[] baseArray) {
			this.count = count;
			this.baseArray = baseArray;
		}

		public Answer invoke() {
			int size = count;
			first = new IntArrayList();
			step = new IntArrayList();
			Array array = new Array(baseArray);
			int[] previous = new int[count];
			int[] next = new int[count];
			int[] last = new int[100000];
			Arrays.fill(last, -1);
			Arrays.fill(next, count);
			for (int i = 0; i < count; i++) {
				if (last[baseArray[i]] != -1)
					next[last[baseArray[i]]] = i;
				previous[i] = last[baseArray[i]];
				last[baseArray[i]] = i;
			}
			while (size > 0) {
				int bestFirst = size - 1;
				int bestStep = 1;
				int result = 1;
				for (int i = size - 1; i >= size - 1 && i >= 0; i--) {
					int prev = previous[array.index(i)];
					int cnt = 0;
					int maxCnt = 5;
					while (prev != -1 && cnt < maxCnt) {
						int start = i;
						int qty = 1;
						int shift = i - array.back(prev);
						if (i + shift < size)
							break;
						int value = array.get(i);
						for (int k = i - shift; k >= 0; k -= shift) {
							if (array.get(k) != value)
								break;
							start = k;
							qty++;
						}
						if (qty > result) {
							bestFirst = start;
							bestStep = shift;
							result = qty;
						}
						cnt++;
						prev = previous[prev];
					}
				}
				first.add(bestFirst + 1);
				step.add(bestStep);
				size -= result;
				int lst = bestFirst + bestStep * (result - 1);
				for (int j = lst; j >= bestFirst; j -= bestStep) {
					int idx = array.index(j);
					if (previous[idx] != -1)
						next[previous[idx]] = next[idx];
					if (next[idx] != count)
						previous[next[idx]] = previous[idx];
					array.remove(idx);
				}
			}
			return this;
		}
	}
}
