package on2015_11.on2015_11_22_Grand_Prix_of_Europe___2015.B___Book_Borders;



import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskB {
	int[] first;
	int[] next = new int[30_000_000];
	int[] val = new int[30_000_000];
	int qty;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		String[] words = in.readLine().split(" ");
		int a = in.readInt() + 1;
		int b = in.readInt() + 1;
		int[] lengths = new int[words.length];
		for (int i = 0; i < words.length; i++) {
			lengths[i] = words[i].length() + 1;
		}
		long[] sums = ArrayUtils.partialSums(lengths);
		first = ArrayUtils.createArray((int) sums[lengths.length], -1);
		for (int i = a; i <= b; i++) {
			add(0, i);
		}
		int[] answer = new int[b + 1];
		Arrays.fill(answer, -1);
		for (int i = 0; i < lengths.length; i++) {
			for (int j = (int) sums[i]; j < sums[i + 1]; j++) {
				for (int l = first[j]; l != -1; l = next[l]) {
					int k = val[l];
					answer[k] += lengths[i];
					int pos = (int) (sums[i] + k);
					if (pos < sums[lengths.length]) {
						add(pos, k);
					}
				}
			}
		}
		for (int i = a; i <= b; i++) {
			out.printLine(answer[i]);
		}
	}

	private void add(int at, int value) {
		next[qty] = first[at];
		val[qty] = value;
		first[at] = qty++;
	}
}
