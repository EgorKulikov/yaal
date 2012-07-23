import net.egork.collections.iss.IndependentSetSystem;
import net.egork.collections.iss.RecursiveIndependentSetSystem;
import net.egork.collections.map.CPPMap;
import net.egork.io.IOUtils;
import net.egork.misc.Factory;
import net.egork.utils.Solver;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

public class TaskD implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int cityCount = in.readInt();
		Road[] roads = new Road[cityCount - 1];
		for (int i = 0; i < cityCount - 1; i++) {
			int from = in.readInt() - 1;
			int to = in.readInt() - 1;
			int length = in.readInt();
			roads[i] = new Road(from, to, length, i + 1);
		}
		Arrays.sort(roads, new Comparator<Road>() {
			public int compare(Road o1, Road o2) {
				return o1.length - o2.length;
			}
		});
		int start = 0;
		long answer = 0;
		final long[] component = new long[cityCount];
		Arrays.fill(component, 1);
		IndependentSetSystem setSystem = new RecursiveIndependentSetSystem(cityCount);
		setSystem.setListener(new IndependentSetSystem.Listener() {
			public void joined(int joinedRoot, int root) {
				component[root] += component[joinedRoot];
			}
		});
		List<Integer> indices = null;
		Factory<Set<Road>> defaultValueFactory = new Factory<Set<Road>>() {
			public Set<Road> create() {
				return new HashSet<Road>();
			}
		};
		for (int i = 1; i < cityCount; i++) {
			if (i == cityCount - 1 || roads[i].length != roads[i - 1].length) {
				final Map<Integer, Set<Road>> map = new CPPMap<Integer, Set<Road>>(defaultValueFactory);
				for (int j = start; j < i; j++) {
					int first = setSystem.get(roads[j].from);
					int second = setSystem.get(roads[j].to);
					roads[j].from = first;
					roads[j].to = second;
					map.get(first).add(roads[j]);
					map.get(second).add(roads[j]);
				}
				Comparator<Integer> comparator = new Comparator<Integer>() {
					public int compare(Integer o1, Integer o2) {
						int value = map.get(o1).size() - map.get(o2).size();
						if (value != 0)
							return value;
						return o1 - o2;
					}
				};
				NavigableSet<Integer> count = new TreeSet<Integer>(comparator);
				count.addAll(map.keySet());
				List<Road> processedRoads = new ArrayList<Road>();
				while (!count.isEmpty()) {
					int first = count.pollFirst();
					if (map.get(first).size() != 1)
						throw new RuntimeException();
					Road road = map.get(first).iterator().next();
					int second = road.from + road.to - first;
					map.remove(first);
					count.remove(second);
					Set<Road> secondRemaining = map.get(second);
					secondRemaining.remove(road);
					if (!secondRemaining.isEmpty()) {
						map.put(second, secondRemaining);
						count.add(second);
					} else
						map.remove(second);
					first = setSystem.get(first);
					second = setSystem.get(second);
					setSystem.join(second, first);
					road.from = first;
					road.to = second;
					processedRoads.add(road);
				}
				for (Road road : processedRoads) {
					long total = component[setSystem.get(road.to)];
					long current = (total - component[road.from]) * component[road.from];
					if (current > answer) {
						answer = current;
						indices = new ArrayList<Integer>();
						indices.add(road.index);
					} else if (current == answer)
						indices.add(road.index);
				}
				start = i;
			}
		}
		Collections.sort(indices);
		out.println(2 * answer + " " + indices.size());
		IOUtils.printCollection(indices, out);
	}

	private static class Road {
		private int from;
		private int to;
		private final int length;
		private final int index;

		private Road(int from, int to, int length, int index) {
			this.from = from;
			this.to = to;
			this.length = length;
			this.index = index;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (o == null || getClass() != o.getClass())
				return false;

			Road road = (Road) o;

			if (from != road.from)
				return false;
			if (index != road.index)
				return false;
			if (length != road.length)
				return false;
			if (to != road.to)
				return false;

			return true;
		}

		@Override
		public int hashCode() {
			int result = from;
			result = 31 * result + to;
			result = 31 * result + length;
			result = 31 * result + index;
			return result;
		}
	}
}

