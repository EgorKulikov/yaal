package on2013_09.on2013_09_11_Codeforces_Trainings_Season_1_Episode_1.TaskG;



import net.egork.geometry.Line;
import net.egork.geometry.Point;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskG {
	Point tester = new Point(0, -1e50);

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		Point[] points = new Point[count];
		for (int i = 0; i < count; i++) {
			int qty = in.readInt();
			for (int j = 0; j < qty; j++)
				points[i] = Point.readPoint(in);
		}
		int lineCount = in.readInt();
		Line[] lines = new Line[lineCount];
		for (int i = 0; i < lineCount; i++) {
			Point first = Point.readPoint(in);
			Point second = Point.readPoint(in);
			lines[i] = first.line(second);
		}
		StringBuilder answer = new StringBuilder(count);
		process(points, lines, (1 << count) - 1, answer, 0);
		out.printLine(answer);
    }

	private void process(Point[] points, Line[] lines, int mask, StringBuilder answer, int step) {
		if (Integer.bitCount(mask) <= 1 || step == lines.length) {
			for (int i = 0; i < points.length; i++) {
				if ((mask >> i & 1) == 1)
					answer.append((char)('A' + i));
			}
			return;
		}
		int more = 0;
		for (int i = 0; i < points.length; i++) {
			if ((mask >> i & 1) == 1 && lines[step].value(points[i]) > 0)
				more += 1 << i;
		}
		if (lines[step].value(tester) > 0)
			more = mask - more;
		process(points, lines, more, answer, step + 1);
		process(points, lines, mask - more, answer, step + 1);
	}
}
