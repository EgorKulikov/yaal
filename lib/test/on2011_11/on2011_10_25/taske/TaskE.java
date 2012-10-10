package on2011_11.on2011_10_25.taske;



import net.egork.collections.Pair;
import net.egork.collections.sequence.Array;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskE {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int size = in.readInt();
		int count = in.readInt();
		Pair<Integer, Integer>[] figures = IOUtils.readIntPairArray(in, count);
		Arrays.sort(figures);
		int[] canEat = new int[count];
		int[] row = new int[size + 1];
		int[] column = new int[size + 1];
		int[] firstDiagonal = new int[2 * size + 1];
		int[] secondDiagonal = new int[2 * size + 1];
		Arrays.fill(row, -1);
		Arrays.fill(column, -1);
		Arrays.fill(firstDiagonal, -1);
		Arrays.fill(secondDiagonal, -1);
		for (int i = 0, figuresLength = figures.length; i < figuresLength; i++) {
			Pair<Integer, Integer> figure = figures[i];
			if (row[figure.first] != -1) {
				canEat[i]++;
				canEat[row[figure.first]]++;
			}
			row[figure.first] = i;
			if (column[figure.second] != -1) {
				canEat[i]++;
				canEat[column[figure.second]]++;
			}
			column[figure.second] = i;
			if (firstDiagonal[figure.first + figure.second] != -1) {
				canEat[i]++;
				canEat[firstDiagonal[figure.first + figure.second]]++;
			}
			firstDiagonal[figure.first + figure.second] = i;
			if (secondDiagonal[figure.first - figure.second + size] != -1) {
				canEat[i]++;
				canEat[secondDiagonal[figure.first - figure.second + size]]++;
			}
			secondDiagonal[figure.first - figure.second + size] = i;
		}
		int[] answer = new int[9];
		for (int i : canEat)
			answer[i]++;
		out.printLine(Array.wrap(answer).toArray());
	}
}
