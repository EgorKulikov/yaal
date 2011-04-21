package Timus.Part3;

import net.egork.collections.sequence.ArrayWrapper;
import net.egork.collections.Pair;
import net.egork.collections.sequence.SequenceUtils;
import net.egork.utils.Solver;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;

public class Task1207 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int pointCount = in.readInt();
		@SuppressWarnings({"unchecked"})
		Pair<Long, Long>[] points = Pair.readArray(pointCount, in);
		int index = SequenceUtils.minIndex(ArrayWrapper.wrap(points), new Pair.Comparator<Long, Long>());
		double[] angle = new double[pointCount];
		angle[index] = Double.NEGATIVE_INFINITY;
		for (int i = 0; i < pointCount; i++) {
			if (index == i)
				continue;
			angle[i] = Math.atan2(points[i].second() - points[index].second(), points[i].first() - points[index].first());
		}
		Integer[] order = SequenceUtils.order(ArrayWrapper.wrap(angle));
		out.println((index + 1) + " " + (order[pointCount / 2] + 1));
	}
}

