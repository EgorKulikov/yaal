package on2014_08.on2014_08_21_SnarkNews_Summer_Series_2014_Round_3.A___Tramway_Burgers;



import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
	static final long MOD = (long) (1e9 + 7);

	long[][] c = IntegerUtils.generateBinomialCoefficients(1001, MOD);

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int money = in.readInt();
		int breadCount = in.readInt();
		int cheeseCount = in.readInt();
		int toppingCount = in.readInt();
		int sauceCount = in.readInt();
		long[] qty = new long[money + 1];
		qty[0] = 1;
		for (int i = money; i >= 0; i--) {
			for (int j = Math.min(money - i + 1, sauceCount); j > 0; j--) {
				qty[i + j - 1] += qty[i] * c[sauceCount][j];
				qty[i + j - 1] %= MOD;
			}
		}
		for (int i = money; i >= 0; i--) {
			for (int j = Math.min(money - i + 1, cheeseCount); j > 0; j--) {
				qty[i + j - 1] += qty[i] * c[cheeseCount][j];
				qty[i + j - 1] %= MOD;
			}
		}
		for (int i = money; i >= 0; i--) {
			for (int j = Math.min(money - i + 3, toppingCount); j > 3; j--) {
				qty[i + j - 3] += qty[i] * c[toppingCount][j];
				qty[i + j - 3] %= MOD;
			}
			qty[i] *= (c[toppingCount][3] + c[toppingCount][2] + c[toppingCount][1] + 1);
			qty[i] %= MOD;
		}
		long answer = 0;
		for (int i = money; i > 0; i--) {
			for (int j = 0; j < i; j++) {
				answer += qty[j];
			}
		}
		answer %= MOD;
		answer *= breadCount;
		answer %= MOD;
		out.printLine(answer);
	}
}
