import net.egork.collections.Pair;
import net.egork.io.IOUtils;
import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;
import java.util.Arrays;

public class TaskJ implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int fruitCount = in.readInt();
		Pair<Integer, Integer>[] fruits = IOUtils.readIntPairArray(in, fruitCount);
		for (int i = 0; i < fruitCount; i++)
			fruits[i] = Pair.makePair(fruits[i].first * 2, fruits[i].second * 2);
		Arrays.sort(fruits);
		int[] points = new int[2 * fruitCount];
		for (int i = 0; i < fruitCount; i++) {
			points[2 * i] = fruits[i].first - 1;
			points[2 * i + 1] = fruits[i].second;
		}
		Arrays.sort(points);
		int[] answer = new int[points.length];
		for (int i = 0; i < points.length; i++) {
			int count = 0;
			for (Pair<Integer, Integer> fruit : fruits) {
				if (fruit.first > points[i])
					break;
				if (fruit.second >= points[i])
					count++;
			}
			if (count <= 2)
				count = 0;
			answer[i] = count;
			int index = 0;
			for (int j = 0; j < i; j++) {
				while (index < fruitCount && fruits[index].first <= points[j]) {
					if (fruits[index].second >= points[i])
						count--;
					index++;
				}
				if (count <= 2)
					count = 0;
				answer[i] = Math.max(answer[i], answer[j] + count);
			}
		}
		out.println("Case #" + testNumber + ": " + answer[answer.length - 1]);
	}
}

