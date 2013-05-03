package on2013_04.on2013_04_22_Croc_Champ_2013___Round_2.B___Distinct_Paths;



import net.egork.misc.ArrayUtils;
import net.egork.collections.set.EHashSet;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;
import java.util.Set;

public class TaskB {
	static final int MOD = (int) (1e9 + 7);

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int rowCount = in.readInt();
		int columnCount = in.readInt();
		int colorCount = in.readInt();
		int[][] setColors = IOUtils.readIntTable(in, rowCount, columnCount);
		MiscUtils.decreaseByOne(setColors);
		if (rowCount + columnCount - 1 > colorCount) {
			out.printLine(0);
			return;
		}
		if (rowCount > columnCount) {
			int temp = rowCount;
			rowCount = columnCount;
			columnCount = temp;
			int[][] colors = new int[rowCount][columnCount];
			for (int i = 0; i < rowCount; i++) {
				for (int j = 0; j < columnCount; j++) {
					colors[i][j] = setColors[j][i];
				}
			}
			setColors = colors;
		}
		if (rowCount == 1 && columnCount == 1) {
			out.printLine(setColors[0][0] == -1 ? colorCount : 1);
			return;
		}
		Set<Integer> allSet = new EHashSet<Integer>();
		for (int i = 0; i < rowCount; i++) {
			for (int j = (i == 0 ? 1 : 0); j < (i == rowCount - 1 ? columnCount - 1 : columnCount); j++) {
				if (setColors[i][j] == -1)
					continue;
				allSet.add(setColors[i][j]);
				if (setColors[i][j] == setColors[0][0] || setColors[i][j] == setColors[rowCount - 1][columnCount - 1]) {
					out.printLine(0);
					return;
				}
			}
		}
		if (allSet.size() >= colorCount - 1) {
			out.printLine(0);
			return;
		}
		int leftUpperWays;
		if (setColors[0][0] == -1)
			leftUpperWays = colorCount - allSet.size() - (setColors[rowCount - 1][columnCount - 1] == -1 ? 0 : 1);
		else
			leftUpperWays = 1;
		int rightBottomWays;
		if (setColors[rowCount - 1][columnCount - 1] == -1)
			rightBottomWays = colorCount - 1 - allSet.size();
		else if (setColors[0][0] == setColors[rowCount - 1][columnCount - 1]) {
			out.printLine(0);
			return;
		} else
			rightBottomWays = 1;
//		leftUpperWays -= allSet.size();
//		rightBottomWays -= allSet.size();
		int firstSkip = -1;
		int secondSkip = -1;
		if (setColors[0][0] != -1) {
			firstSkip = setColors[0][0];
			allSet.add(firstSkip);
		} else {
			for (int i = 0; i < colorCount; i++) {
				if (!allSet.contains(i)) {
					allSet.add(i);
					firstSkip = i;
					break;
				}
			}
		}
		if (setColors[rowCount - 1][columnCount - 1] != -1) {
			secondSkip = setColors[rowCount - 1][columnCount - 1];
			allSet.add(secondSkip);
		} else {
			for (int i = 0; i < colorCount; i++) {
				if (!allSet.contains(i)) {
					allSet.add(i);
					secondSkip = i;
					break;
				}
			}
		}
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				if (setColors[i][j] > firstSkip && setColors[i][j] > secondSkip)
					setColors[i][j] -= 2;
				else if (setColors[i][j] > firstSkip || setColors[i][j] > secondSkip)
					setColors[i][j]--;
			}
		}
		int stateCount = (int) IntegerUtils.power(rowCount + 1, colorCount - 2);
		int[] current = new int[stateCount];
		int[] next = new int[stateCount];
		current[stateCount - 1] = 1;
		int[] delta = new int[colorCount];
		for (int i = 0; i < colorCount; i++)
			delta[i] = (int) IntegerUtils.power(rowCount + 1, i);
		for (int i = 0; i < columnCount; i++) {
			for (int j = (i == 0 ? 1 : 0); j < (i == columnCount - 1 ? rowCount - 1 : rowCount); j++) {
				Arrays.fill(next, 0);
				for (int k = 0; k < stateCount; k++) {
					if (current[k] == 0)
						continue;
					int state = k;
					for (int l = 0; l < colorCount; l++) {
						int shift = state % (rowCount + 1);
						if (shift > j && (setColors[j][i] == l || setColors[j][i] == -1)) {
							int nextState = k - (shift - j) * delta[l];
							next[nextState] += current[k];
							if (next[nextState] >= MOD)
								next[nextState] -= MOD;
						}
						state /= rowCount + 1;
					}
				}
				int[] temp = current;
				current = next;
				next = temp;
			}
		}
		long answer = ArrayUtils.sumArray(current);
		answer %= MOD;
		answer *= leftUpperWays * rightBottomWays;
		answer %= MOD;
		out.printLine(answer);
    }
}
