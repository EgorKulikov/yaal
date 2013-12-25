package on2012_02.on2012_1_19.dailytrain;



import net.egork.io.IOUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class DailyTrain {
	static final long[][] c = IntegerUtils.generateBinomialCoefficients(7);

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int toBuy = in.readInt();
		int carCount = in.readInt();
		char[][] cars = IOUtils.readTable(in, carCount, 54);
		long answer = 0;
		for (char[] car : cars) {
			for (int i = 0; i < 9; i++) {
				int currentFree = 0;
				for (int j = 4 * i; j < 4 * i + 4; j++) {
					if (car[j] == '0')
						currentFree++;
				}
				for (int j = 52 - 2 * i; j < 54 - 2 * i; j++) {
					if (car[j] == '0')
						currentFree++;
				}
				answer += c[currentFree][toBuy];
			}
		}
		out.printLine(answer);
	}
}
