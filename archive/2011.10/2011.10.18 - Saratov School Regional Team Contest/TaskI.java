import net.egork.utils.Solver;

import java.io.PrintWriter;

public class TaskI implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		String[] digits = {"1110111", "0010010", "1011101", "1011011", "0111010", "1101011", "1101111", "1010010",
			"1111111", "1111011"};
		int[][] happiness = new int[10][10];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				for (int k = 0; k < 7; k++)
					happiness[i][j] += (digits[i].charAt(k) - '0') * (digits[j].charAt(k) - '0');
			}
		}
		char[] ticketInput = in.readString().toCharArray();
		int[] ticket = new int[ticketInput.length];
		for (int i = 0; i < ticket.length; i++)
			ticket[i] = ticketInput[i] - '0';
		int size = ticket.length / 2;
		int maxTailHappiness = 0;
		int tailHappiness = 0;
		for (int i = 2 * size - 1; i >= size; i--) {
			tailHappiness += happiness[ticket[i - size]][ticket[i]];
			for (int j = ticket[i] + 1; j < 10; j++) {
				if (happiness[j][ticket[i - size]] + maxTailHappiness > tailHappiness) {
					ticket[i] = j;
					int need = tailHappiness - happiness[j][ticket[i - size]];
					for (int k = i + 1; k < 2 * size; k++) {
						maxTailHappiness -= happiness[ticket[k - size]][ticket[k - size]];
						for (int l = 0; l < 10; l++) {
							if (maxTailHappiness + happiness[l][ticket[k - size]] > need) {
								ticket[k] = l;
								break;
							}
						}
						need -= happiness[ticket[k - size]][ticket[k]];
					}
					for (int digit : ticket)
						out.print(digit);
					out.println();
					return;
				}
			}
			maxTailHappiness += happiness[ticket[i - size]][ticket[i - size]];
		}
		int currentHappiness = 0;
		for (int i = 0; i < size; i++)
			currentHappiness += happiness[ticket[i]][ticket[i + size]];
		int maxHappiness = 0;
		for (int i = 0; i < size; i++)
			maxHappiness += happiness[ticket[i]][ticket[i]];
		for (int i = size - 1; i >= 0; i--) {
			maxHappiness -= happiness[ticket[i]][ticket[i]];
			for (int j = ticket[i] + 1; j < 10; j++) {
				if (maxHappiness + happiness[j][j] > currentHappiness) {
					ticket[i] = j;
					int need = currentHappiness;
					maxHappiness += happiness[j][j];
					for (int k = i + 1; k < size; k++) {
						maxHappiness -= 7;
						for (int l = 0; l < 10; l++) {
							if (maxHappiness + happiness[l][l] > need) {
								ticket[k] = l;
								maxHappiness += happiness[l][l];
								break;
							}
						}
					}
					maxTailHappiness = maxHappiness;
					for (int k = size; k < 2 * size; k++) {
						maxTailHappiness -= happiness[ticket[k - size]][ticket[k - size]];
						for (int l = 0; l < 10; l++) {
							if (maxTailHappiness + happiness[l][ticket[k - size]] > need) {
								ticket[k] = l;
								break;
							}
						}
						need -= happiness[ticket[k - size]][ticket[k]];
					}
					for (int digit : ticket)
						out.print(digit);
					out.println();
					return;
				}
			}
			maxHappiness += 7;
		}
		out.println(-1);
	}
}

