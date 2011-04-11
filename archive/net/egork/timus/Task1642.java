package net.egork.timus;

import net.egork.utils.Solver;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;
import java.util.Arrays;

public class Task1642 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int obstacleCount = in.readInt();
		int exit = in.readInt();
		int[] obstacles = in.readIntArray(obstacleCount);
		Arrays.sort(obstacles);
		int index = -Arrays.binarySearch(obstacles, 0) - 1;
		int index2 = -Arrays.binarySearch(obstacles, exit) - 1;
		if (index != index2) {
			out.println("Impossible");
			return;
		}
		if (exit > 0)
			out.println(exit + " " + (-obstacles[index - 1] * 2 + exit));
		else
			out.println((2 * obstacles[index] - exit) + " " + (-exit));
	}
}

