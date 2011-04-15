package Timus.Part7;

import net.egork.utils.Solver;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;

public class Task1645 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int participantCount = in.readInt();
		int[] order = in.readIntArray(participantCount);
		int[] minimum = new int[participantCount];
		int[] maximum = new int[participantCount];
		for (int i = 0; i < participantCount; i++) {
			minimum[order[i] - 1] = 1;
			maximum[order[i] - 1] = participantCount;
			for (int j = 0; j < i; j++) {
				if (order[j] > order[i])
					minimum[order[i] - 1]++;
			}
			for (int j = i + 1; j < participantCount; j++) {
				if (order[j] < order[i])
					maximum[order[i] - 1]--;
			}
		}
		for (int i = 0; i < participantCount; i++)
			out.println(minimum[i] + " " + maximum[i]);
	}
}

