package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskG1 {
	double numWays = 0;
	double total = 0;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int repeats = in.readInt();
		int[] permutation = IOUtils.readIntArray(in, count);
		go(permutation, repeats);
		out.printLine(total / numWays);
    }

	private void go(int[] permutation, int repeats) {
		if (repeats == 0) {
			numWays++;
			for (int i = 0; i < permutation.length; i++) {
				for (int j = 0; j < i; j++) {
					if (permutation[j] > permutation[i]) {
						total++;
					}
				}
			}
			return;
		}
		for (int i = 0; i < permutation.length; i++) {
			for (int j = 0; j <= i; j++) {
				int k = j;
				int l = i;
				while (k < l) {
					int temp = permutation[k];
					permutation[k] = permutation[l];
					permutation[l] = temp;
					k++;
					l--;
				}
				go(permutation, repeats - 1);
				k = j;
				l = i;
				while (k < l) {
					int temp = permutation[k];
					permutation[k] = permutation[l];
					permutation[l] = temp;
					k++;
					l--;
				}
			}
		}
	}
}
