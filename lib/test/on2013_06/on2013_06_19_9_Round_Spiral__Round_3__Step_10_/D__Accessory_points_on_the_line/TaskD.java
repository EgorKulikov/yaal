package on2013_06.on2013_06_19_9_Round_Spiral__Round_3__Step_10_.D__Accessory_points_on_the_line;



import net.egork.geometry.Line;
import net.egork.geometry.Point;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		Point point = Point.readPoint(in);
		double a = in.readDouble();
		double b = in.readDouble();
		double c = in.readDouble();
		Line line = new Line(a, b, c);
		out.printLine(line.contains(point) ? "YES" : "NO");
    }
}
