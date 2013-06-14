import net.egork.collections.ArrayUtils;
import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;

public class TaskE implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		String number = in.readString();
		int dotPosition = number.indexOf('.');
		if (dotPosition != -1) {
			if ((number.length() - dotPosition) % 2 == 0)
				number += "0";
			number = number.substring(0, dotPosition) + number.substring(dotPosition + 1);
			dotPosition = (dotPosition + 1) / 2;
		}
		if (number.length() % 2 == 1)
			number = "0" + number;
		int[] pairs = new int[number.length() / 2];
		for (int i = 0; i < pairs.length; i++)
			pairs[i] = Integer.parseInt(number.substring(2 * i, 2 * i + 2));
		long answer = 0;
		long[] left = new long[pairs.length];
		long[] right = new long[pairs.length];
		long remaining = 0;
		for (int i = 0; i < pairs.length; i++) {
			remaining *= 100;
			remaining += pairs[i];
			left[i] = 20 * answer;
			for (int j = 1; j <= 10; j++) {
				if ((left[i] + j) * j > remaining) {
					left[i] += j - 1;
					right[i] = j - 1;
					remaining -= left[i] * right[i];
					answer *= 10;
					answer += j - 1;
					break;
				}
			}
		}
		int startPair = -1;
		int nonZero = 0;
		for (int i = 0; i < pairs.length; i++) {
			if (right[i] != 0) {
				startPair = i;
				nonZero = pairs.length - i;
				break;
			}
		}
		if (startPair == -1)
			startPair = pairs.length;
		int height = nonZero * 3 + 3;
		int leftWidth = 5 + pairs.length * 3;
		int shift = 0;
		if (pairs[0] < 10) {
			leftWidth--;
			shift = 1;
		}
		int rightWidth = 0;
		for (int i = 0; i < pairs.length; i++) {
			if (i < startPair)
				continue;
			int currentWidth = 6 + Long.toString(left[i]).length() + Long.toString(right[i]).length() + Long.toString(left[i] * right[i]).length();
			rightWidth = Math.max(rightWidth, currentWidth);
		}
		char[][] result = new char[height][leftWidth + rightWidth];
		ArrayUtils.fill(result, '.');
		for (int i = pairs.length - 1; i >= 0; i--) {
			result[0][5 + 3 * i - shift] = (char)(answer % 10 + '0');
			answer /= 10;
		}
		for (int i = 0; i < pairs.length; i++) {
			if (i != 0 || pairs[i] >= 10)
				result[2][4 + 3 * i - shift] = (char) (pairs[i] / 10 + '0');
			result[2][5 + 3 * i - shift] = (char) (pairs[i] % 10 + '0');
		}
		result[1][0] = '_';
		result[2][1] = '\\';
		result[2][2] = '/';
		for (int i = 3; i < leftWidth - 1; i++)
			result[1][i] = '-';
		result[2][leftWidth - 1] = '`';
		remaining = 0;
		int index = 0;
		for (int i = 0; i < pairs.length; i++) {
			remaining *= 100;
			remaining += pairs[i];
			if (i < startPair)
				continue;
			long remainingCopy = remaining;
			int column = 5 + 3 * i - shift;
			boolean added = false;
			if (index != 0 && remainingCopy < 100) {
				remainingCopy += 100;
				added = true;
			}
			while (remainingCopy != 0) {
				result[2 + 3 * index][column] = (char) (remainingCopy % 10 + '0');
				column--;
				remainingCopy /= 10;
				if (remainingCopy != 0) {
					result[2 + 3 * index][column] = (char) (remainingCopy % 10 + '0');
					column -= 2;
					remainingCopy /= 10;
				}
			}
			if (added)
				result[2 + 3 * index][5 + 3 * (i - 1) - shift] = '0';
			long multiply = left[i] * right[i];
			remaining -= multiply;
			column = 5 + 3 * i - shift;
			if (multiply == 0) {
				result[3 + 3 * index][column--] = '0';
			}
			while (multiply != 0) {
				result[3 + 3 * index][column] = (char) (multiply % 10 + '0');
				column--;
				multiply /= 10;
				if (multiply != 0) {
					result[3 + 3 * index][column] = (char) (multiply % 10 + '0');
					multiply /= 10;
					if (multiply == 0)
						column--;
					else
						column -= 2;
				}
			}
			result[3 + 3 * index][column] = '-';
			index++;
		}
		long remainingCopy = remaining;
		int column = 5 + 3 * (pairs.length - 1) - shift;
		if (remainingCopy == 0)
			result[2 + 3 * index][column] = '0';
		while (remainingCopy != 0) {
			result[2 + 3 * index][column] = (char) (remainingCopy % 10 + '0');
			column--;
			remainingCopy /= 10;
			if (remainingCopy != 0) {
				result[2 + 3 * index][column] = (char) (remainingCopy % 10 + '0');
				column -= 2;
				remainingCopy /= 10;
			}
		}
		for (int i = 4; i < height; i += 3) {
			int first = 0;
			int last = 0;
			for (int j = 0; j < leftWidth; j++) {
				if (result[i - 1][j] != '.') {
					if (first == 0)
						first = j;
					last = j;
				}
			}
			if (i != 4) {
				for (int j = 0; j < leftWidth; j++) {
					if (result[i - 2][j] != '.' && result[i - 2][j] != '0') {
						if (first > j)
							first = j;
//						last = j;
					}
				}
			}
			for (int j = first; j <= last; j++)
				result[i][j] = '-';
		}
		if (dotPosition != -1) {
			result[0][3 - shift + 3 * dotPosition] = ',';
			result[2][3 - shift + 3 * dotPosition] = ',';
		}
		index = 0;
		for (int i = 0; i < pairs.length; i++) {
			if (i < startPair)
				continue;
			String first = Long.toString(left[i]);
			for (column = leftWidth; column < leftWidth + first.length(); column++)
				result[3 * index + 3][column] = first.charAt(column - leftWidth);
			column++;
			result[3 * index + 3][column++] = 'x';
			column++;
			int start = column;
			first = Long.toString(right[i]);
			for (; column < start + first.length(); column++)
				result[3 * index + 3][column] = first.charAt(column - start);
			column++;
			result[3 * index + 3][column] = '=';
			column += 2;
			first = Long.toString(left[i] * right[i]);
			start = column;
			for (; column < start + first.length(); column++)
				result[3 * index + 3][column] = first.charAt(column - start);
			index++;
		}
		for (char[] row : result)
			out.println(row);
		out.println();
	}
}

