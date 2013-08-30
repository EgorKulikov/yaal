package net.egork;

import net.egork.collections.map.EHashMap;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.*;

public class TaskC {
	int rowCount, columnCount;
	char[][] map;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		rowCount = in.readInt();
		columnCount = in.readInt();
		map = IOUtils.readTable(in, rowCount, columnCount);
		Queue<State> queue = new ArrayDeque<State>();
		Map<State, Integer> answer = new EHashMap<State, Integer>();
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				if (map[i][j] == 'S') {
					State start = new State(j, i, 1, 0);
					queue.add(start);
					answer.put(start, 0);
				}
			}
		}
		while (!queue.isEmpty()) {
			State state = queue.poll();
			int result = answer.get(state);
			if (state.isFinal()) {
				out.printLine(result);
				return;
			}
			for (State next : state.moves()) {
				if (answer.containsKey(next))
					continue;
				answer.put(next, result + 1);
				queue.add(next);
			}
		}
		out.printLine("Impossible");
    }

	class State {
		int x;
		int y;
		int dir;
		int speed;

		State(int x, int y, int dir, int speed) {
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.speed = speed;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;

			State state = (State) o;

			if (dir != state.dir) return false;
			if (speed != state.speed) return false;
			if (x != state.x) return false;
			if (y != state.y) return false;

			return true;
		}

		@Override
		public int hashCode() {
			int result = x;
			result = 31 * result + y;
			result = 31 * result + dir;
			result = 31 * result + speed;
			return result;
		}

		public boolean isFinal() {
			return speed == 0 && map[y][x] == 'T';
		}

		public List<State> moves() {
			List<State> result = new ArrayList<State>();
			{
				State candidate = fly();
				if (candidate != null)
					result.add(candidate);
			}
			if (speed > 0 && speed < 5) {
				State candidate = more().fly();
				if (candidate != null)
					result.add(candidate);
			}
			if (speed > 0) {
				State candidate = less().fly();
				if (candidate != null)
					result.add(candidate);
			}
			if (speed == 0) {
				State candidate = forward().fly();
				if (candidate != null)
					result.add(candidate);
			}
			if (speed == 0) {
				State candidate = back().fly();
				if (candidate != null)
					result.add(candidate);
			}
			{
				State candidate = stop().fly();
				if (candidate != null)
					result.add(candidate);
			}
			if (speed == 0) {
				State candidate = left().fly();
				if (candidate != null)
					result.add(candidate);
			}
			if (speed == 0) {
				State candidate = right().fly();
				if (candidate != null)
					result.add(candidate);
			}
			return result;
		}

		private State left() {
			return new State(x, y, (dir + 1) & 3, speed);
		}

		private State right() {
			return new State(x, y, (dir + 3) & 3, speed);
		}

		private State stop() {
			return new State(x, y, dir, 0);
		}

		private State back() {
			return new State(x, y, dir, -1);
		}

		private State forward() {
			return new State(x, y, dir, 1);
		}

		private State less() {
			return new State(x, y, dir, speed - 1);
		}

		private State more() {
			return new State(x, y, dir, speed + 1);
		}

		private State fly() {
			for (int i = Math.min(speed, 1); i <= speed; i++) {
				if (i == 0)
					continue;
				int yy = y + MiscUtils.DY4[dir] * i;
				int xx = x + MiscUtils.DX4[dir] * i;
				if (!MiscUtils.isValidCell(yy, xx, rowCount, columnCount) || map[yy][xx] == 'M')
					return null;
			}
			return new State(x + MiscUtils.DX4[dir] * speed, y + MiscUtils.DY4[dir] * speed, dir, speed);
		}
	}
}
