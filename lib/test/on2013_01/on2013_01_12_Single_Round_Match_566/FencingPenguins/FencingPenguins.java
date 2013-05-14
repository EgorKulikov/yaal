package on2013_01.on2013_01_12_Single_Round_Match_566.FencingPenguins;



import net.egork.misc.ArrayUtils;
import net.egork.geometry.Point;
import net.egork.geometry.Segment;

public class FencingPenguins {
	private static final long MOD = (long) (1e5 + 7);
	boolean[][] isValid;
	long[][] right;
	long[][] answer;
	long[][] ongoingWithPenguin;
	long[][] ongoingWithoutPenguin;

	public int countWays(int count, int radius, int[] x, int[] y, String color) {
		Point[] posts = new Point[count];
		for (int i = 0; i < count; i++)
			posts[i] = new Point(radius * Math.cos(2 * Math.PI / count * i), radius * Math.sin(2 * Math.PI / count * i));
		Segment[][] segments = new Segment[count][count];
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < count; j++) {
				if (i != j)
					segments[i][j] = new Segment(posts[i], posts[j]);
			}
		}
		int size = x.length;
		Point[] points = new Point[size];
		for (int i = 0; i < size; i++)
			points[i] = new Point(x[i], y[i]);
		right = new long[count][count];
		isValid = new boolean[count][count];
		boolean[][][] values = new boolean[count][count][size];
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < count; j++) {
				if (i == j)
					continue;
				for (int k = 0; k < size; k++) {
					values[i][j][k] = segments[i][j].line().value(points[k]) > 0;
					if (values[i][j][k])
						right[i][j] += 1L << k;
				}
			}
		}
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < size; j++) {
				if (values[i][(i + 1) % count][j])
					return 0;
			}
		}
		int[] decoded = new int[size];
		for (int i = 0; i < size; i++) {
			if (Character.isUpperCase(color.charAt(i)))
				decoded[i] = color.charAt(i) - 'A';
			else
				decoded[i] = color.charAt(i) - 'a' + 26;
		}
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < count; j++) {
				if (i == j)
					continue;
				long leftColor = 0;
				long rightColor = 0;
				for (int k = 0; k < size; k++) {
					if (values[i][j][k])
						leftColor |= 1L << decoded[k];
					else
						rightColor |= 1L << decoded[k];
				}
				if ((leftColor & rightColor) != 0)
					continue;
				isValid[i][j] = true;
				if ((i + 1) % count != j) {
					if ((i + 2) % count == j && right[i][j] != 0) {
						isValid[i][j] = false;
					} else {
						int nextI = (i + 1) % count;
						int nextJ = (j + count - 1) % count;
						if (right[i][j] != right[nextI][nextJ])
							isValid[i][j] = false;
					}
				}
			}
		}
		answer = new long[count][count];
		ongoingWithPenguin = new long[count][count];
		ongoingWithoutPenguin = new long[count][count];
		ArrayUtils.fill(answer, -1);
		ArrayUtils.fill(ongoingWithPenguin, -1);
		ArrayUtils.fill(ongoingWithoutPenguin, -1);
		return (int) go(0, count - 1);
	}

	private long go(int from, int to) {
		if (from >= to)
			return 1;
		if (answer[from][to] != -1)
			return answer[from][to];
		if (right[from][to] == 0)
			return answer[from][to] = 1;
		answer[from][to] = 0;
		if (right[from + 1][to] == right[from][to])
			answer[from][to] = go(from + 1, to);
		if (isValid[to][from])
			answer[from][to] += goWithout(from, to);
		for (int i = to - 1; i > from; i--) {
			if (isValid[i][from] && (right[from][i] + right[i + 1][to]) == right[from][to])
				answer[from][to] += goWithout(from, i) * go(i + 1, to);
		}
		answer[from][to] %= MOD;
		return answer[from][to];
	}

	private long goWithout(int from, int to) {
		if (ongoingWithoutPenguin[from][to] != -1)
			return ongoingWithoutPenguin[from][to];
		if (right[from][to] == 0)
			return ongoingWithoutPenguin[from][to] = 0;
		ongoingWithoutPenguin[from][to] = 0;
		for (int i = to - 1; i > from; i--) {
			if (isValid[i][to]) {
				if ((right[from][i] + right[i][to]) == right[from][to])
					ongoingWithoutPenguin[from][to] += goWithout(from, i) * go(i + 1, to - 1);
				else
					ongoingWithoutPenguin[from][to] += goWith(from, i) * go(i + 1, to - 1);
			}
		}
		return ongoingWithoutPenguin[from][to] %= MOD;
	}

	private long goWith(int from, int to) {
		if (ongoingWithPenguin[from][to] != -1)
			return ongoingWithPenguin[from][to];
		ongoingWithPenguin[from][to] = 0;
		if (isValid[from][to])
			ongoingWithPenguin[from][to] += go(from + 1, to - 1);
		for (int i = to - 1; i > from; i--) {
			if (isValid[i][to])
				ongoingWithPenguin[from][to] += goWith(from, i) * go(i + 1, to - 1);
		}
		return ongoingWithPenguin[from][to] %= MOD;
	}
}
