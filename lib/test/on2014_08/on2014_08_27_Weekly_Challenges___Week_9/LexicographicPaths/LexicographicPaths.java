package on2014_08.on2014_08_27_Weekly_Challenges___Week_9.LexicographicPaths;



import net.egork.io.IOUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class LexicographicPaths {
	long[][] c = IntegerUtils.generateBinomialCoefficients(21);

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int x = in.readInt();
		int y = in.readInt();
		int index = in.readInt();
		StringBuilder answer = new StringBuilder(x + y);
		while (x > 0 || y > 0) {
			if (x != 0 && index < c[x - 1 + y][y]) {
				x--;
				answer.append('H');
			} else {
				if (x != 0) {
					index -= c[x - 1 + y][y];
				}
				y--;
				answer.append('V');
			}
		}
		out.printLine(answer);
    }
}
