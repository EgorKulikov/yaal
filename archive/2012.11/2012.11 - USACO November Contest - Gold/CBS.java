package net.egork;

import net.egork.collections.Pair;
import net.egork.collections.map.EHashMap;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;
import java.util.Map;

public class CBS {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int length = in.readInt();
		char[][] sequences = IOUtils.readTable(in, count, length);
		Result answer = new Result(sequences[0]);
		for (int i = 1; i < count; i++)
			answer.join(new Result(sequences[i]));
		int[] result = new int[length + 1];
		long total = 0;
		for (int i = 0; i <= length; i++) {
			if (answer.last[i] != -1)
				result[i] = result[answer.last[i]] + 1;
			total += result[i];
		}
		out.printLine(total);
	}

	class Result {
		int[] first, last, level;

		Result(char[] sequence) {
			int length = sequence.length;
			first = new int[length + 1];
			last = new int[length + 1];
			level = new int[length + 1];
			int[] firstByLevel = new int[2 * length + 1];
			int[] lastByLevel = new int[2 * length + 1];
			Arrays.fill(firstByLevel, -1);
			Arrays.fill(lastByLevel, -1);
			int level = length;
			for (int i = 0; i < length; i++) {
				first[i] = firstByLevel[level];
				last[i] = lastByLevel[level];
				this.level[i] = level;
				if (sequence[i] == '(') {
					if (firstByLevel[level] == -1)
						firstByLevel[level] = i;
					lastByLevel[level] = i;
					level++;
				} else {
					firstByLevel[level] = -1;
					lastByLevel[level] = -1;
					level--;
				}
			}
			first[length] = firstByLevel[level];
			last[length] = lastByLevel[level];
			this.level[length] = level;
		}

		Result(int[] first, int[] last, int[] level) {
			this.first = first;
			this.last = last;
			this.level = level;
		}

		void join(Result other) {
			Map <Pair<Integer, Integer>, Integer> lastPair = new EHashMap<Pair<Integer, Integer>, Integer>();
			for (int i = 0; i < first.length; i++) {
				Pair<Integer, Integer> key = Pair.makePair(level[i], other.level[i]);
				if (first[i] == -1 || other.first[i] == -1) {
					first[i] = -1;
					last[i] = -1;
				} else if (lastPair.containsKey(key)) {
					int position = lastPair.get(key);
					if (position >= first[i] && position >= other.first[i]) {
						first[i] = first[position] == -1 ? position : first[position];
						last[i] = position;
					} else {
						first[i] = -1;
						last[i] = -1;
					}
				} else {
					first[i] = -1;
					last[i] = -1;
				}
				lastPair.put(key, i);
			}
		}
	}
}
