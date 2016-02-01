package on2016_01.on2016_01_31_Grand_Prix_of_Asia_2016.I___Robots;



import net.egork.collections.Pair;
import net.egork.graph.Graph;
import net.egork.graph.GraphAlgorithms;
import net.egork.graph.ShortestDistance;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.*;

import static net.egork.io.IOUtils.*;
import static net.egork.misc.MiscUtils.*;
import static net.egork.misc.ArrayUtils.*;

public class TaskI {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		long t = in.readLong();

		Robot[] robots = new Robot[n];
		for (int i = 0; i < n; i++) {
			robots[i] = new Robot(in.readInt(), in.readInt(), "UDRL".indexOf(in.readString()), i);
		}

		Graph graph = new Graph(n);
		for (int cc = 0; cc < 2; cc++) {
			Map<Integer, List<Robot>> map = new HashMap<>();
			for (Robot robot : robots) {
				List<Robot> l = map.get(robot.c[cc]);
				if (l == null) {
					l = new ArrayList<>();
					map.put(robot.c[cc], l);
				}
				l.add(robot);
			}
			for (List<Robot> list : map.values()) {
				final int finalCc = cc;
				Collections.sort(list, new Comparator<Robot>() {
					@Override
					public int compare(Robot o1, Robot o2) {
						return Integer.compare(o1.c[1 - finalCc], o2.c[1 - finalCc]);
					}
				});
				Robot last = null;
				for (Robot robot : list) {
					if (last != null) {
						graph.addWeightedEdge(last.id, robot.id, Math.abs(robot.c[1 - cc] - last.c[1 - cc]));
					}
					if (robot.dir / 2 == cc && (robot.dir & 1) == 0) {
						last = robot;
					}
				}
				Collections.reverse(list);
				last = null;
				for (Robot robot : list) {
					if (last != null) {
						graph.addWeightedEdge(last.id, robot.id, Math.abs(robot.c[1 - cc] - last.c[1 - cc]));
					}
					if (robot.dir / 2 == cc && (robot.dir & 1) == 1) {
						last = robot;
					}
				}
			}
		}
		long[] d = ShortestDistance.dijkstraAlgorithm(graph, 0).first;
		for (int i = 0; i < n; i++) {
			long time;
			if (d[i] == Long.MAX_VALUE) {
				time = 0;
			} else {
				time = Math.max(t - d[i], 0);
			}
			long dx = new int[]{0, 0, 1, -1}[robots[i].dir];
			long dy = new int[]{1, -1, 0, 0}[robots[i].dir];
			out.printLine(robots[i].c[0] + dx * time, robots[i].c[1] + dy * time);
		}
	}

	class Robot {
		int[] c = new int[2]; int dir; int id;

		public Robot(int x, int y, int dir, int id) {
			this.c[0] = x;
			this.c[1] = y;
			this.dir = dir;
			this.id = id;
		}
	}
}
