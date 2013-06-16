package on2013_06.on2013_06_15_Google_Code_Jam_Round_3_2013.B___Rural_Planning;



import net.egork.chelper.checkers.Checker;
import net.egork.chelper.tester.Verdict;
import net.egork.geometry.Point;
import net.egork.geometry.Polygon;
import net.egork.geometry.Segment;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;

import java.io.StringBufferInputStream;


public class TaskBChecker implements Checker {
    public TaskBChecker(String parameters) {
    }

    public Verdict check(String input, String expectedOutput, String actualOutput) {
        InputReader in = new InputReader(new StringBufferInputStream(input));
        InputReader expected;
        if (expectedOutput == null)
            expected = null;
        else
            expected = new InputReader(new StringBufferInputStream(expectedOutput));
        InputReader actual = new InputReader(new StringBufferInputStream(actualOutput));
        return check(in, expected, actual);
    }

    public Verdict check(InputReader in, InputReader expected, InputReader actual) {
		int testCount = in.readInt();
		for (int test = 0; test < testCount; test++) {
			int count = in.readInt();
			Point[] points = new Point[count];
			for (int i = 0; i < count; i++)
				points[i] = Point.readPoint(in);
			double maxSquare = Polygon.convexHull(points.clone()).square();
			actual.readString();
			actual.readString();
			int[] order = IOUtils.readIntArray(actual, count);
			Point[] answer = new Point[count];
			for (int i = 0; i < count; i++)
				answer[i] = points[order[i]];
			if (new Polygon(answer).square() * 2 < maxSquare - 1e-5)
				return new Verdict(Verdict.VerdictType.WA, "Test #" + test + " - too small");
			for (int i = 0; i < count; i++) {
				Segment first = new Segment(answer[i], answer[(i + 1) % count]);
				for (int j = i + 1; j < count; j++) {
					Segment second = new Segment(answer[j], answer[(j + 1) % count]);
					if (first.intersect(second, false) != null)
						return new Verdict(Verdict.VerdictType.WA, "Test #" + test + " - " + i + " and " + j + " intesects");
				}
			}
		}
        return Verdict.OK;
    }
}
