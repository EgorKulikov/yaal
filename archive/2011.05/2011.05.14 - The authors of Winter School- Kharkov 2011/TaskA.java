import net.egork.geometry.GeometryUtils;
import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;

public class TaskA implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		GeometryUtils.epsilon = 1e-18;
		int cityCount = in.readInt();
		int startIndex = in.readInt() - 1;
		int endIndex = in.readInt() - 1;
		double r = in.readDouble();
		double d = in.readDouble() / r;
		double[] x = new double[cityCount];
		double[] y = new double[cityCount];
		double[] z = new double[cityCount];
		for (int i = 0; i < cityCount; i++) {
			double latitude = in.readDouble();
			char latitudeDirection = in.readCharacter();
			double longitude = in.readDouble();
			char longitudeDirection = in.readCharacter();
			latitude *= Math.PI / 180;
			longitude *= Math.PI / 180;
			if (latitudeDirection == 'S')
				latitude = -latitude;
			if (longitudeDirection == 'W')
				longitude = -longitude;
			x[i] = Math.cos(longitude) * Math.cos(latitude);
			y[i] = Math.sin(longitude) * Math.cos(latitude);
			z[i] = Math.sin(latitude);
		}
		double minCos = d < Math.PI ? Math.cos(d) : -2;
		boolean[][] reachable = new boolean[cityCount][cityCount];
		for (int i = 0; i < cityCount; i++) {
			for (int j = i + 1; j < cityCount; j++) {
				reachable[j][i] = reachable[i][j] = x[i] * x[j] + y[i] * y[j] + z[i] * z[j] >= minCos;
			}
		}
		boolean[] visited = new boolean[cityCount];
		int[] distance = new int[cityCount];
		visited[startIndex] = true;
		int[] queue = new int[cityCount];
		queue[0] = startIndex;
		int queueSize = 1;
		for (int i = 0; i < queueSize; i++) {
			for (int j = 0; j < cityCount; j++) {
				if (!visited[j] && reachable[queue[i]][j]) {
					visited[j] = true;
					distance[j] = distance[queue[i]] + 1;
					queue[queueSize++] = j;
				}
			}
		}
		if (!visited[endIndex])
			out.println(-1);
		else
			out.println(distance[endIndex]);
	}
}

