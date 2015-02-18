package on2015_01.on2015_01_21_SnarkNews_Winter_Series_2015__Round_3.TaskC;



import net.egork.geometry.Point;
import net.egork.geometry.Segment;
import net.egork.numbers.DoubleUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int required = in.readInt();
		Point center = Point.readPoint(in);
		int[] capacity = new int[count];
		double[] distance = new double[count];
		for (int i = 0; i < count; i++) {
			int size = in.readInt();
			capacity[i] = in.readInt();
			Point last = Point.readPoint(in);
			distance[i] = Double.POSITIVE_INFINITY;
			for (int j = 1; j < size; j++) {
				Point current = Point.readPoint(in);
				distance[i] = Math.min(distance[i], new Segment(last, current).distance(center));
				last = current;
			}
		}
		double[] answer = new double[required + 1];
		Arrays.fill(answer, Double.POSITIVE_INFINITY);
		answer[0] = 0;
		for (int i = 0; i < count; i++) {
			for (int j = required; j >= 0; j--) {
				answer[Math.min(j + capacity[i], required)] = Math.min(answer[Math.min(j + capacity[i], required)],
					answer[j] + distance[i]);
			}
		}
		if (Double.isInfinite(answer[required])) out.printLine(-1);
		else out.printLine(answer[required]);
	}
}
