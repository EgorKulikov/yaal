package Timus.Part2;

import net.egork.arrays.ArrayWrapper;
import net.egork.arrays.ArrayUtils;
import net.egork.utils.Solver;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;

public class Task1193 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int studentCount = in.readInt();
		int[] preparationTime = new int[studentCount];
		int[] answerTime = new int[studentCount];
		int[] goAwayTime = new int[studentCount];
		in.readIntArrays(preparationTime, answerTime, goAwayTime);
		Integer[] order = ArrayUtils.order(ArrayWrapper.wrap(preparationTime));
		int currentTime = 0;
		int answer = 0;
		for (int i : order) {
			int startTime = Math.max(preparationTime[i], currentTime);
			int endTime = startTime + answerTime[i];
			currentTime = endTime;
			answer = Math.max(answer, endTime - goAwayTime[i]);
		}
		out.println(answer);
	}
}

