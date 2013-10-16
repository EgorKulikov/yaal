package on2013_10.on2013_10_15_Codeforces_Round__207.B___Xenia_and_Hamming;



import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		long firstRepeat = in.readLong();
		long secondRepeat = in.readLong();
		char[] first = in.readString().toCharArray();
		char[] second = in.readString().toCharArray();
		int g = IntegerUtils.gcd(first.length, second.length);
		int[][] qty = new int[g][26];
		for (int i = 0; i < second.length; i += g) {
			for (int j = 0; j < g; j++)
				qty[j][second[i + j] - 'a']++;
		}
		long single = 0;
		for (int i = 0; i < first.length; i += g) {
			for (int j = 0; j < g; j++)
				single += qty[j][first[i + j] - 'a'];
		}
		long totalLength = firstRepeat * first.length;
		long partLength = IntegerUtils.lcm(first.length, second.length);
		long answer = totalLength - (totalLength / partLength) * single;
		out.printLine(answer);
    }
}
