package on2014_06.on2014_06_28_Addepar_Hackathon.ComputingAnalytics;



import net.egork.collections.intcollection.IntHashSet;
import net.egork.collections.intcollection.IntSet;
import net.egork.collections.map.EHashMap;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Map;

public class ComputingAnalytics {
	private static final int BUBEN = 200;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int sampleCount = in.readInt();
		int queryCount = in.readInt();
		int[] sampleFrom = new int[sampleCount];
		int[] sampleTo = new int[sampleCount];
		IOUtils.readIntArrays(in, sampleFrom, sampleTo);
		int[] queryFrom = new int[queryCount];
		int[] queryTo = new int[queryCount];
		IOUtils.readIntArrays(in, queryFrom, queryTo);
		for (int i = 0; i < sampleCount; i++) {
			if (sampleFrom[i] > sampleTo[i]) {
				int temp = sampleFrom[i];
				sampleFrom[i] = sampleTo[i];
				sampleTo[i] = temp;
			}
		}
		for (int i = 0; i < queryCount; i++) {
			if (queryFrom[i] > queryTo[i]) {
				int temp = queryFrom[i];
				queryFrom[i] = queryTo[i];
				queryTo[i] = temp;
			}
		}
		MiscUtils.decreaseByOne(sampleFrom, queryFrom);
		IntSet[] sample = new IntSet[count + 1];
		for (int i = 0; i <= count; i++) {
			sample[i] = new IntHashSet();
		}
		for (int i = 0; i < sampleCount; i++) {
			sample[sampleFrom[i]].add(sampleTo[i]);
			sample[sampleTo[i]].add(sampleFrom[i]);
		}
		Map<IntPair, Integer> queries = new EHashMap<>();
		boolean[] answer = new boolean[queryCount];
		for (int i = 0; i < queryCount; i++) {
			if (sample[queryFrom[i]].contains(queryTo[i])) {
				answer[i] = true;
			} else {
				queries.put(new IntPair(queryFrom[i], queryTo[i]), i);
				queries.put(new IntPair(queryTo[i], queryFrom[i]), i);
			}
		}
		IntPair key = new IntPair(0, 0);
		for (int i = 0; i <= count; i++) {
			if (sample[i].size() > BUBEN) {
				for (int j = 0; j < queryCount; j++) {
					if (!answer[j] && sample[i].contains(queryFrom[j]) && sample[i].contains(queryTo[j])) {
						answer[j] = true;
					}
				}
			} else {
				int[] points = sample[i].toArray();
				for (int j = 0; j < points.length; j++) {
					key.second = points[j];
					for (int k = 0; k < j; k++) {
						key.first = points[k];
						if (queries.containsKey(key)) {
							answer[queries.get(key)] = true;
						}
					}
				}
			}
		}
		for (int i = 0; i < queryCount; i++) {
			IntPair curKey = new IntPair(queryFrom[i], queryTo[i]);
			if (answer[i] && queries.containsKey(curKey)) {
				answer[queries.get(curKey)] = true;
			}
		}
		for (int i = 0; i < queryCount; i++) {
			if (!answer[i] && answer[queries.get(new IntPair(queryFrom[i], queryTo[i]))]) {
				answer[i] = true;
			}
		}
		for (int i = 0; i < queryCount; i++) {
			if (answer[i]) {
				out.printLine("YES");
			} else {
				out.printLine("NO");
			}
		}
	}

	static class IntPair {
		int first;
		int second;

		IntPair(int first, int second) {
			this.first = first;
			this.second = second;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;

			IntPair intPair = (IntPair) o;

			if (first != intPair.first) return false;
			if (second != intPair.second) return false;

			return true;
		}

		@Override
		public int hashCode() {
			int result = first;
			result = 31 * result + second;
			return result;
		}
	}
}
