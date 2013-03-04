package on2012_09.on2012_09_23_September_Cook_Off_2012.Prime_Permutations;



import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class PrimePermutations {
	long[] answer = new long[5000001];
	static final long MOD = (long) (1e9 + 7);

	{
		boolean[] isPrime = IntegerUtils.generatePrimalityTable(5000001);
		answer[1] = 1;
		int count = 1;
		for (int i = 2; i <= 5000000; i++) {
			if (isPrime[i])
				count++;
			answer[i] = answer[i - 1] * count % MOD;
		}
	}


	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		out.printLine(answer[count]);
	}
}
