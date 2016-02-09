package on2016_01.on2016_01_24_SNWS2016__R3.A___Access_Denied_;



import net.egork.geometry.Circle;
import net.egork.geometry.Point;
import net.egork.geometry.Polygon;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int c = in.readInt();
		int r = in.readInt();
		int k = in.readInt();
		Point[] vertices = new Point[n];
		for (int i = 0; i < n; i++) {
			vertices[i] = Point.readPoint(in);
		}
		Polygon hall = new Polygon(vertices);
		Point[] workplaces = new Point[c];
		for (int i = 0; i < c; i++) {
			workplaces[i] = Point.readPoint(in);
		}
		List<Circle> routers = new ArrayList<>();
		for (int i = 0; i < r; i++) {
			Point location = Point.readPoint(in);
			double radius = in.readDouble();
			if (!hall.contains(location)) {
				routers.add(new Circle(location, radius));
			}
		}
		Circle[] transmitters = new Circle[k];
		for (int i = 0; i < k; i++) {
			Point location = Point.readPoint(in);
			double radius = in.readDouble();
			transmitters[i] = new Circle(location, radius);
		}
		int answer = 0;
		for (int i = 0; i < c; i++) {
			boolean inside = false;
			for (Circle router : routers) {
				if (router.contains(workplaces[i])) {
					inside = true;
					break;
				}
			}
			if (!inside) {
				continue;
			}
			for (Circle transmitter : transmitters) {
				if (transmitter.contains(workplaces[i])) {
					inside = false;
					break;
				}
			}
			if (inside) {
				answer++;
			}
		}
		out.printLine(answer);
	}
}
