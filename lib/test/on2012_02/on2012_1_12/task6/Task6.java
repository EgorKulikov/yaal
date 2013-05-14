package on2012_02.on2012_1_12.task6;



import net.egork.io.IOUtils;
import net.egork.string.StringUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Task6 {
	static final long MOD = 10000007;

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int length = in.readInt();
		int count = in.readInt();
		String[] spells = IOUtils.readStringArray(in, count);
		int[][] z = new int[count][];
		for (int i = 0; i < count; i++)
			z[i] = StringUtils.zAlgorithm(spells[i]);
		for (int i = 0; i < count; i++)
			z[i][0] = count;
		StringBuilder best = new StringBuilder();
		long total = 1;
		for (int i = 0; i < length; i++) {
			int max = -1;
			int mask = 0;
			for (int j = 0; j < count; j++) {
				if (max < z[j][i]) {
					max = z[j][i];
					mask = 1 << (spells[j].charAt(i) - 'a');
				} else if (max == z[j][i])
					mask |= 1 << (spells[j].charAt(i) - 'a');
			}
			total *= Integer.bitCount(mask);
			total %= MOD;
			best.append((char)('a' + Integer.bitCount(Integer.lowestOneBit(mask) - 1)));
		}
		out.printLine(total, best);
	}
}
