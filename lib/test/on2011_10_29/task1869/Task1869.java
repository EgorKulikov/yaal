package on2011_10_29.task1869;



import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Task1869 {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int length = in.readInt();
		int[][] count = IOUtils.readIntTable(in, length, length);
		int answer = 0;
		int current = 0;
		for (int i = 0; i < length; i++) {
			for (int j = i + 1; j < length; j++)
				current += count[i][j];
			for (int j = 0; j < i; j++)
				current -= count[j][i];
			answer = Math.max(answer, current);
		}
		for (int i = 0; i < length; i++) {
			for (int j = i + 1; j < length; j++)
				current += count[j][i];
			for (int j = 0; j < i; j++)
				current -= count[i][j];
			answer = Math.max(answer, current);
		}
		answer = (answer + 35) / 36;
		out.printLine(answer);
	}
}
