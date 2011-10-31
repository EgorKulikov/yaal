import net.egork.collections.sequence.Array;
import net.egork.io.IOUtils;
import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;

public class TaskD implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int count = in.readInt();
		int[] numbers = IOUtils.readIntArray(in, count);
		Integer[] order = SequenceUtils.order(Array.wrap(numbers));
		for (int i = count - 1; i >= 0; i--) {
			if (order[i] == i)
				count--;
		}
/*		double[] factorial = new double[count + 1];
		factorial[0] = 1;
		for (int i = 1; i <= count; i++)
			factorial[i] = factorial[i - 1] * i;
		double[] derangementProbability = new double[count + 1];
		derangementProbability[0] = 1;
		for (int i = 1; i <= count; i++) {
			if (i % 2 == 0)
				derangementProbability[i] = derangementProbability[i - 1] + 1 / factorial[i];
			else
				derangementProbability[i] = derangementProbability[i - 1] - 1 / factorial[i];
		}
		double[] answer = new double[count + 1];
		for (int i = 2; i <= count; i++) {
			answer[i] = 1;
			for (int j = 1; j <= i; j++)
				answer[i] += answer[i - j] * derangementProbability[i - j] / factorial[j];
			answer[i] /= 1 - derangementProbability[i];
		}*/
		out.printf("Case #%d: %.12f\n", testNumber, (double) count);
	}
}

