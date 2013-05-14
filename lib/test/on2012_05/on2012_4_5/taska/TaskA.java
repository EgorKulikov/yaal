package on2012_05.on2012_4_5.taska;



import net.egork.collections.CollectionUtils;
import net.egork.collections.sequence.Array;
import net.egork.geometry.Circle;
import net.egork.geometry.GeometryUtils;
import net.egork.geometry.Line;
import net.egork.geometry.Point;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int balloonCount = in.readInt();
		int[] x = new int[balloonCount];
		int[] y = new int[balloonCount];
		int[] r = new int[balloonCount];
		IOUtils.readIntArrays(in, x, y, r);
		Circle[] circles = new Circle[balloonCount];
		for (int i = 0; i < balloonCount; i++)
			circles[i] = new Circle(new Point(x[i], y[i]), r[i] / 2d);
		boolean[] popped = new boolean[balloonCount];
		for (int i = 0; i <= 9; i++) {
			double angle = Math.PI * i / 18;
			Line line = new Point(0, 0).line(new Point(Math.cos(angle), Math.sin(angle)));
			boolean shouldShoot = false;
			for (int j = 0; j < balloonCount; j++) {
				if (!popped[j] && line.distance(circles[j].center) < circles[j].radius + GeometryUtils.epsilon)
					shouldShoot = popped[j] = true;
			}
			if (shouldShoot)
				out.printLine("Fire laser at", i * 10, "degrees.");
		}
		int totalPopped = CollectionUtils.count(Array.wrap(popped), true);
		if (totalPopped == 0)
			out.printLine("No balloon burst.");
		else if (totalPopped == 1)
			out.printLine("1 burst balloon.");
		else
			out.printLine(totalPopped, "burst balloons.");
	}
}
