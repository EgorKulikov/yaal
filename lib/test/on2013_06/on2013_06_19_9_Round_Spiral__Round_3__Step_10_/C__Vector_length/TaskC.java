package on2013_06.on2013_06_19_9_Round_Spiral__Round_3__Step_10_.C__Vector_length;



import net.egork.geometry.Point;
import net.egork.geometry.Vector;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		Point from = Point.readPoint(in);
		Point to = Point.readPoint(in);
		Vector vector = new Vector(from, to);
		out.printFormat("%.6f\n", vector.length());
    }
}
