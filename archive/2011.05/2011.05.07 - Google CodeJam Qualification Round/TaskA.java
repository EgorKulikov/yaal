import net.egork.utils.Solver;

import java.io.PrintWriter;

public class TaskA implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int moveCount = in.readInt();
		char[] color = new char[moveCount];
		int[] position = new int[moveCount];
		for (int i = 0; i < moveCount; i++) {
			color[i] = in.readCharacter();
			position[i] = in.readInt();
		}
		int orangePosition = 1;
		int bluePosition = 1;
		int nextOrange = -1;
		int nextBlue = -1;
		int answer = 0;
		for (int i = 0; i < moveCount; i++) {
			for (int j = i; j < moveCount; j++) {
				if (color[j] == 'O') {
					nextOrange = position[j];
					break;
				}
			}
			for (int j = i; j < moveCount; j++) {
				if (color[j] == 'B') {
					nextBlue = position[j];
					break;
				}
			}
			while (color[i] == 'B' ? bluePosition != nextBlue : orangePosition != nextOrange) {
				if (bluePosition < nextBlue)
					bluePosition++;
				else if (bluePosition > nextBlue)
					bluePosition--;
				if (orangePosition < nextOrange)
					orangePosition++;
				else if (orangePosition > nextOrange)
					orangePosition--;
				answer++;
			}
			if (bluePosition < nextBlue)
				bluePosition++;
			else if (bluePosition > nextBlue)
				bluePosition--;
			if (orangePosition < nextOrange)
				orangePosition++;
			else if (orangePosition > nextOrange)
				orangePosition--;
			answer++;
		}
		out.println("Case #" + testNumber + ": " + answer);
	}
}

