package net.egork;

import net.egork.utils.io.InputReader;
import java.io.PrintWriter;

public class TaskB {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int doughCount = in.readInt();
		int filingCount = in.readInt();
		int iceCreamCount = in.readInt();
		int doughFillingForbiddenCount = in.readInt();
		boolean[][] doughFillingForbidden = new boolean[doughCount][filingCount];
		for (int i = 0; i < doughFillingForbiddenCount; i++) {
			int dough = in.readInt() - 1;
			int filling = in.readInt() - 1;
			doughFillingForbidden[dough][filling] = true;
		}
		int fillingIceCreamForbiddenCount = in.readInt();
		boolean[][] fillingIceCreamForbidden = new boolean[filingCount][iceCreamCount];
		for (int i = 0; i < fillingIceCreamForbiddenCount; i++) {
			int filling = in.readInt() - 1;
			int iceCream = in.readInt() - 1;
			fillingIceCreamForbidden[filling][iceCream] = true;
		}
		int iceCreamDoughForbiddenCount = in.readInt();
		boolean[][] iceCreamDoughForbidden = new boolean[iceCreamCount][doughCount];
		for (int i = 0; i < iceCreamDoughForbiddenCount; i++) {
			int dough = in.readInt() - 1;
			int iceCream = in.readInt() - 1;
			iceCreamDoughForbidden[iceCream][dough] = true;
		}
		int answer = 0;
		for (int i = 0; i < doughCount; i++) {
			for (int j = 0; j < filingCount; j++) {
				for (int k = 0; k < iceCreamCount; k++) {
					if (!doughFillingForbidden[i][j] && !fillingIceCreamForbidden[j][k] &&
						!iceCreamDoughForbidden[k][i])
					{
						answer++;
					}
				}
			}
		}
		out.println(answer);
	}
}
