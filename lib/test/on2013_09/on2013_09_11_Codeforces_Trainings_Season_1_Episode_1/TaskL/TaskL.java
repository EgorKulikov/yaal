package on2013_09.on2013_09_11_Codeforces_Trainings_Season_1_Episode_1.TaskL;



import net.egork.geometry.Point;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskL {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		if (in.isExhausted())
			throw new UnknownError();
		double x = in.readDouble();
		double y = in.readDouble();
		double c = in.readDouble();
		double left = 0;
		double right = Math.min(x, y);
		for (int i = 0; i < 50; i++) {
			double middle = (left + right) / 2;
			Point firstLeft = new Point(0, 0);
			Point secondLeft = new Point(middle, Math.sqrt(y * y - middle * middle));
			Point firstRight = new Point(middle, 0);
			Point secondRight = new Point(0, Math.sqrt(x * x - middle * middle));
			if (firstLeft.line(secondLeft).intersect(firstRight.line(secondRight)).y > c)
				left = middle;
			else
				right = middle;
		}
		out.printFormat("%.3f\n", (left + right) / 2);
    }
}
