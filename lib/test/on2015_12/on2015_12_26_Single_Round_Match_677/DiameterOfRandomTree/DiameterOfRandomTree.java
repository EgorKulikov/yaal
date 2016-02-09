package on2015_12.on2015_12_26_Single_Round_Match_677.DiameterOfRandomTree;



import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Graph;

public class DiameterOfRandomTree {
	Graph graph;
	double[][][] p;
	int n;

	public double getExpectation(int[] a, int[] b) {
		n = a.length + 1;
		graph = BidirectionalGraph.createGraph(n, a, b);
		p = new double[n][2 * n - 1][2 * n - 1];
		calculate(0, -1);
		double answer = 0;
		for (int i = 0; i < p[0].length; i++) {
			for (int j = 0; j < p[0][i].length; j++) {
				answer += i * p[0][i][j];
			}
		}
		return answer;
	}

	private void calculate(int vertex, int last) {
		p[vertex][0][0] = 1;
		for (int i = graph.firstOutbound(vertex); i != -1; i = graph.nextOutbound(i)) {
			int next = graph.destination(i);
			if (next == last) {
				continue;
			}
			calculate(next, vertex);
			double[][] q = enlarge(p[next]);
			double[][] r = new double[2 * n - 1][2 * n - 1];
			double[][] sp = new double[2 * n - 1][2 * n - 1];
			double[][] sq = new double[2 * n - 1][2 * n - 1];
			for (int j = 0; j < 2 * n - 1; j++) {
				for (int k = 0; k < 2 * n - 1; k++) {
					for (int m = 0; k + m < 2 * n - 1; m++) {
						r[Math.max(j, k + m)][Math.max(k, m)] += p[vertex][j][k] * q[j][m] + p[vertex][j][k] * sq[j][m] + sp[j][k] * q[j][m];
					}
				}
				if (j + 1 < 2 * n - 1) {
					for (int k = 0; k < 2 * n - 1; k++) {
						sp[j + 1][k] = sp[j][k] + p[vertex][j][k];
						sq[j + 1][k] = sq[j][k] + q[j][k];
					}
				}
			}
			p[vertex] = r;
		}
	}

	private double[][] enlarge(double[][] p) {
		double[][] q = new double[2 * n - 1][2 * n - 1];
		for (int i = 0; i < 2 * n - 1; i++) {
			for (int j = 0; j < 2 * n - 1; j++) {
				if (j + 1 < 2 * n - 1) {
					q[Math.max(i, j + 1)][j + 1] += p[i][j] / 2;
				}
				if (j + 2 < 2 * n - 1) {
					q[Math.max(i, j + 2)][j + 2] += p[i][j] / 2;
				}
			}
		}
		return q;
	}
}
