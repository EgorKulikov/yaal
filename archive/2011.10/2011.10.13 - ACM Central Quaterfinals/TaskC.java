import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;

public class TaskC implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		long max = in.readLong();
		long number = in.readLong();
		long[][] count = new long[13][12 * 9 + 1];
		count[0][0] = 1;
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < count[i].length; j++) {
				if (count[i][j] == 0)
					continue;
				for (int k = j; k <= j + 9; k++)
					count[i + 1][k] += count[i][j];
			}
		}
		int sumDigits = 0;
		long maxCopy = max;
		while (maxCopy != 0) {
			sumDigits += maxCopy % 10;
			maxCopy /= 10;
		}
		int numberSumDigits = 0;
		long numberCopy = number;
		while (numberCopy != 0) {
			numberSumDigits += numberCopy % 10;
			numberCopy /= 10;
		}
		long answer = 0;
		if (sumDigits < numberSumDigits)
			answer++;
		int index = 0;
		while (max != 0) {
			int digit = (int) (max % 10);
			max /= 10;
			sumDigits -= digit;
			for (int i = 0; i < digit; i++) {
				for (int j = 0; j < count[index].length && i + j + sumDigits < numberSumDigits; j++)
					answer += count[index][j];
			}
			index++;
		}
		sumDigits = numberSumDigits;
		index = 0;
		while (number != 0) {
			int digit = (int) (number % 10);
			number /= 10;
			sumDigits -= digit;
			for (int i = 0; i < digit; i++) {
				int sum = numberSumDigits - sumDigits - i;
				if (sum >= 0 && sum < count[index].length)
					answer += count[index][sum];
			}
			index++;
		}
		out.println(answer);
	}
}

