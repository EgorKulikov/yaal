import net.egork.utils.Solver;

import java.io.PrintWriter;
import java.util.Arrays;

public class TaskB implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		char[] number = in.readString().toCharArray();
		if (number.length % 2 == 1) {
			char[] temp = new char[number.length + 1];
			System.arraycopy(number, 0, temp, 1, number.length);
			temp[0] = '0';
			number = temp;
		}
		char[] result = new char[number.length];
		Arrays.fill(result, '4');
		for (int i = 0; i < number.length; i++) {
			if (number[i] > '7') {
				i--;
				while (i >= 0) {
					if (result[i] == '7')
						result[i] = '4';
					else {
						result[i] = '7';
						break;
					}
					i--;
				}
				if (i < 0) {
					for (int j = 0; j <= number.length / 2; j++)
						out.print('4');
					for (int j = 0; j <= number.length / 2; j++)
						out.print('7');
					out.println();
					return;
				}
				break;
			} else if (number[i] == '7')
				result[i] = '7';
			else if (number[i] > '4') {
				result[i] = '7';
				break;
			} else if (number[i] < '4')
				break;
		}
		int foursCount = 0;
		int sevenCount = 0;
		for (char digit : result) {
			if (digit == '4')
				foursCount++;
			else
				sevenCount++;
		}
		if (foursCount > sevenCount) {
			int replace = (foursCount - sevenCount) / 2;
			for (int i = result.length - 1; i >= 0 && replace != 0; i--) {
				if (result[i] == '4') {
					result[i] = '7';
					replace--;
				}
			}
		} else if (sevenCount > foursCount) {
			boolean fixed = false;
			for (int i = result.length - 1; i >= 0; i--) {
				if (result[i] == '7') {
					sevenCount--;
					result[i] = '4';
				} else if ((sevenCount + 1) * 2 <= result.length) {
					int sevens = -sevenCount - 1 + result.length / 2;
					result[i] = '7';
					Arrays.fill(result, result.length - sevens, result.length, '7');
					fixed = true;
					break;
				}
			}
			if (!fixed) {
				for (int j = 0; j <= number.length / 2; j++)
					out.print('4');
				for (int j = 0; j <= number.length / 2; j++)
					out.print('7');
				out.println();
				return;
			}
		}
		out.println(result);
	}
}

