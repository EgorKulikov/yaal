package net.egork;

import net.egork.collections.intcollection.IntHashMap;
import net.egork.collections.iss.IndependentSetSystem;
import net.egork.collections.iss.RecursiveIndependentSetSystem;
import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class ChefAndGraphQueries {
	int[] s;
	int[] color;
	int iteration;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int edgeCount = in.readInt();
		int queryCount = in.readInt();
		int[] from = new int[edgeCount];
		int[] to = new int[edgeCount];
		IOUtils.readIntArrays(in, from, to);
		int[] left = new int[queryCount];
		int[] right = new int[queryCount];
		IOUtils.readIntArrays(in, left, right);
		MiscUtils.decreaseByOne(from, to, left, right);
		int[] first = new int[edgeCount];
		int[] next = new int[queryCount];
		Arrays.fill(first, -1);
		for (int i = 0; i < queryCount; i++) {
			next[i] = first[right[i]];
			first[right[i]] = i;
		}
		s = new int[count];
		color = new int[count];
		int[] answer = new int[queryCount];
		Arrays.fill(answer, -1);
		int step = (int) Math.round(Math.sqrt(edgeCount));
		for (int i = step - 1; i < edgeCount; i += step) {
			IndependentSetSystem setSystem = new RecursiveIndependentSetSystem(count);
			for (int j = i; j < edgeCount; j++) {
				setSystem.join(from[j], to[j]);
				for (int k = first[j]; k != -1; k = next[k]) {
					if (left[k] <= i - step || left[k] > i)
						continue;
					answer[k] = setSystem.getSetCount();
					iteration++;
					for (int l = i - 1; l >= left[k]; l--) {
						int a = setSystem.get(from[l]);
						int b = setSystem.get(to[l]);
						if (a == b)
							continue;
						a = get(a);
						b = get(b);
						if (a == b)
							continue;
						s[a] = b;
						color[a] = iteration;
						answer[k]--;
					}
				}
			}
		}
		for (int i = 0; i < queryCount; i++) {
			if (answer[i] != -1)
				continue;
			answer[i] = count;
			iteration++;
			for (int j = left[i]; j <= right[i]; j++) {
				int a = get(from[j]);
				int b = get(to[j]);
				if (a != b) {
					answer[i]--;
					s[a] = b;
					color[a] = iteration;
				}
			}
		}
		for (int i : answer)
			out.printLine(i);
    }

	private int get(int at) {
		if (color[at] != iteration)
			return at;
		int result = get(s[at]);
		s[at] = result;
		color[at] = iteration;
		return result;
	}
}
