package on2013_06.on2013_06_19_9_Round_Spiral__Round_3__Step_10_.A__Polar_angle_of_point;



import net.egork.geometry.GeometryUtils;
import net.egork.geometry.Point;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		Point point = Point.readPoint(in);
		out.printFormat("%.6f\n", GeometryUtils.positiveAngle(point.angle()));
    }
}
