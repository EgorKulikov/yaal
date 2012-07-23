import net.egork.utils.Solver;

import java.io.PrintWriter;

public class TaskE implements Solver {
	private boolean[][] done = new boolean[100][100];
	private double[][] result = new double[100][100];
	private boolean[] serveDone = new boolean[100];
	private double[] serveResult = new double[100];

	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int first = in.readInt();
		int second = in.readInt();
		if (done[first][second]) {
			out.printf("Case #%d: %.4f", testNumber, result[first][second] * 100);
			out.println("%");
			return;
		}
		double gameServe = processGame(first);
		double gameReceive = processGame(second);
		double set = processSet(gameServe, gameReceive);
		double match = processMatch(set);
		out.printf("Case #%d: %.4f", testNumber, match * 100);
		out.println("%");
		done[first][second] = true;
		result[first][second] = match;
		done[second][first] = true;
		result[second][first] = match;
		done[100 - first][100 - second] = true;
		result[100 - first][100 - second] = 1 - match;
		done[100 - second][100 - first] = true;
		result[100 - second][100 - first] = 1 - match;
	}

	private double processGame(int percent) {
		if (serveDone[percent])
			return serveResult[percent];
		double serve = percent / 100.;
		double[][] probability = new double[5][5];
		probability[0][0] = 1;
		for (int i = 0; i <= 4; i++) {
			for (int j = 0; j <= 4; j++) {
				if (i != 0 && j != 4)
					probability[i][j] += probability[i - 1][j] * serve;
				if (j != 0 && i != 4)
					probability[i][j] += probability[i][j - 1] * (1 - serve);
			}
		}
		double result = probability[4][0] + probability[4][1] + probability[4][2] +
			probability[3][3] * serve * serve / (serve * serve + (1 - serve) * (1 - serve));
		serveDone[percent] = true;
		serveResult[percent] = result;
		serveDone[100 - percent] = true;
		serveResult[100 - percent] = 1 - result;
		return result;
	}

	private double processSet(double gameServe, double gameReceive) {
		double[][] probability = new double[7][7];
		probability[0][0] = 1;
		for (int i = 0; i <= 6; i++) {
			for (int j = 0; j <= 6; j++) {
				if (i != 0 && j != 6)
					probability[i][j] += probability[i - 1][j] * ((i + j) % 2 == 0 ? gameServe : gameReceive);
				if (j != 0 && i != 6)
					probability[i][j] += probability[i][j - 1] * (1 - ((i + j) % 2 == 0 ? gameServe : gameReceive));
			}
		}
		return probability[6][0] + probability[6][1] + probability[6][2] + probability[6][3] + probability[6][4] +
			probability[5][5] * gameServe * gameReceive / (gameServe * gameReceive + (1 - gameServe) * (1 - gameReceive));
	}

	private double processMatch(double set) {
		double[][] probability = new double[4][4];
		probability[0][0] = 1;
		for (int i = 0; i <= 3; i++) {
			for (int j = 0; j <= 3; j++) {
				if (i != 0 && j != 3)
					probability[i][j] += probability[i - 1][j] * set;
				if (j != 0 && i != 3)
					probability[i][j] += probability[i][j - 1] * (1 - set);
			}
		}
		return probability[3][0] + probability[3][1] + probability[3][2];
	}
}

