import net.egork.utils.Solver;

import java.io.PrintWriter;

public class TaskB implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		char[][] combinations = new char[256][256];
		int transmutationCount = in.readInt();
		for (int i = 0; i < transmutationCount; i++) {
			char base1 = in.readCharacter();
			char base2 = in.readCharacter();
			combinations[base1][base2] = combinations[base2][base1] = in.readCharacter();
		}
		boolean[][] opposite = new boolean[256][256];
		int oppositeCount = in.readInt();
		for (int i = 0; i < oppositeCount; i++) {
			char base1 = in.readCharacter();
			char base2 = in.readCharacter();
			opposite[base1][base2] = opposite[base2][base1] = true;
		}
		int elementCount = in.readInt();
		char[] stack = new char[elementCount];
		int size = 0;
		for (int i = 0; i < elementCount; i++) {
			stack[size++] = in.readCharacter();
			while (size >= 2) {
				if (combinations[stack[size - 1]][stack[size - 2]] != 0) {
					stack[size - 2] = combinations[stack[size - 1]][stack[size - 2]];
					size--;
					continue;
				}
				for (int j = 0; j < size - 1; j++) {
					if (opposite[stack[j]][stack[size - 1]])
						size = 0;
				}
				break;
			}
		}
		out.print("Case #" + testNumber + ": [");
		for (int i = 0; i < size; i++) {
			if (i != 0)
				out.print(", ");
			out.print(stack[i]);
		}
		out.println("]");
	}
}

