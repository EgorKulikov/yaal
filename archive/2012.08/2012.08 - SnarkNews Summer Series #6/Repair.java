package net.egork;

import net.egork.collections.map.Indexer;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class Repair {
	private static final long MOD = (1L << 31) - 1;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int operationCount = in.readInt();
		Indexer<String> instruments = new Indexer<String>();
		for (int i = 0; i < count; i++)
			instruments.get(in.readString());
		int[] color = new int[count];
		int[] quantity = new int[count];
		quantity[0] = count;
		int nextColor = 1;
		int[] perColor = new int[count];
		long[][] c = IntegerUtils.generateBinomialCoefficients(count + 1, MOD);
		long answer = 0;
		boolean[] marked = new boolean[count];
		int[] newColor = new int[count];
		for (int i = 0; i < operationCount; i++) {
			Arrays.fill(perColor, 0, nextColor, 0);
			Arrays.fill(marked, false);
			Arrays.fill(newColor, -1);
			int currentCount = in.readInt();
			for (int j = 0; j < currentCount; j++) {
				int index = instruments.get(in.readString());
				perColor[color[index]]++;
				marked[index] = true;
			}
			long current = 1;
			for (int j = nextColor - 1; j >= 0; j--) {
				if (perColor[j] != quantity[j] && perColor[j] != 0) {
					current = (current * c[quantity[j]][perColor[j]]) % MOD;
					newColor[j] = nextColor++;
				}
			}
			for (int j = 0; j < count; j++) {
				if (marked[j] && newColor[color[j]] != -1) {
					quantity[color[j]]--;
					color[j] = newColor[color[j]];
					quantity[color[j]]++;
				}
			}
			answer += current;
		}
		answer %= MOD;
		out.printLine(answer);
	}
}
