import net.egork.geometry.GeometryUtils;
import net.egork.geometry.Line;
import net.egork.geometry.Point;
import net.egork.utils.Solver;
import net.egork.utils.old.io.old.InputReader;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;

public class TaskD implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		GeometryUtils.epsilon = 1e-11;
		int railCount = in.readInt() * 2;
		Line[] rails = new Line[railCount];
		for (int i = 0; i < railCount; i++) {
			int x = in.readInt();
			int y = in.readInt();
			Point first = new Point(x, y);
			x = in.readInt();
			y = in.readInt();
			rails[i] = first.line(new Point(x, y));
		}
		Arrays.sort(rails, new Comparator<Line>() {
			public int compare(Line o1, Line o2) {
				if (o1.parallel(o2))
					return Double.compare(o1.c, o2.c);
				if (Math.abs(o1.a - o2.a) < GeometryUtils.epsilon)
					return Double.compare(o1.b, o2.b);
				return Double.compare(o1.a, o2.a);
			}
		});
		boolean[] visited = new boolean[railCount];
		for (int i = 1; i < railCount; i++) {
			if (!rails[0].parallel(rails[i]))
				break;
			Arrays.fill(visited, false);
			int index = i;
			boolean good = true;
			double distance = rails[i].c - rails[0].c;
			for (int j = 0; j < railCount; j++) {
				if (visited[j])
					continue;
				if (j >= index)
					index = j + 1;
				while (index < railCount) {
					if (!visited[index]) {
						if (!rails[index].parallel(rails[j])) {
							good = false;
							break;
						}
						if (rails[index].c - rails[j].c > distance + GeometryUtils.epsilon) {
							good = false;
							break;
						}
						if (rails[index].c - rails[j].c > distance - GeometryUtils.epsilon)
							break;
					}
					index++;
				}
				if (index == railCount)
					good = false;
				if (!good)
					break;
				visited[j] = visited[index++] = true;
			}
			if (good) {
				out.printf("%.9f\n", distance);
				return;
			}
		}
		out.println(-1);
	}
}

