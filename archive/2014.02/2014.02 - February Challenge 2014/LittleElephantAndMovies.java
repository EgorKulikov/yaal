package net.egork;

import net.egork.io.IOUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class LittleElephantAndMovies {
	private static final long MOD = (long) (1e9 + 7);

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int raises = in.readInt();
		int[] movies = IOUtils.readIntArray(in, count);
		Arrays.sort(movies);
		long[] answer = new long[raises + 1];
		long[] next = new long[raises + 1];
//		long[][] c = IntegerUtils.generateBinomialCoefficients(count + 1, MOD);
		answer[0] = 1;
		int current = 1;
		int done = 0;
		for (int i = count - 2; i >= -1; i--) {
			if (i == -1 || movies[i] != movies[i + 1]) {
				Arrays.fill(next, 0);
				for (int j = 0; j <= raises; j++) {
					long ratio = 1;
					for (int k = 0; k < current; k++)
						ratio = (ratio * (done + k)) % MOD;
					next[j] = (next[j] + answer[j] * ratio) % MOD;
					if (j != raises) {
						ratio = current;
						for (int k = 1; k < current; k++)
							ratio = (ratio * (done + k)) % MOD;
						next[j + 1] = (next[j + 1] + answer[j] * ratio) % MOD;
					}
				}
				long[] temp = answer;
				answer = next;
				next = temp;
				done += current;
				current = 0;
			}
			current++;
		}
		long total = 0;
		for (long i : answer)
			total += i;
		total %= MOD;
		out.printLine(total);
    }
}
