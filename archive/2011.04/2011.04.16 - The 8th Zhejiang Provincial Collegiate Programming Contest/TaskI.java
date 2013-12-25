package April2011.ZJU8thZhejiangProvincialCollegiateProgrammingContest;

import net.egork.geometry.Circle;
import net.egork.geometry.Point;
import net.egork.geometry.Segment;
import net.egork.utils.Solver;

import java.io.PrintWriter;

public class TaskI implements Solver {
	public void solve(int testNumber, net.egork.utils.old.io.old.InputReader in, PrintWriter out) {
		int baseCount = in.readInt();
		int brickCount = in.readInt();
		Circle[] bases = new Circle[baseCount];
		for (int i = 0; i < baseCount; i++) {
			int x = in.readInt();
			int y = in.readInt();
			int r = in.readInt();
			bases[i] = new Circle(new Point(x, y), r);
		}
		Segment[] bricks = new Segment[brickCount];
		for (int i = 0; i < brickCount; i++) {
			int x1 = in.readInt();
			int y1 = in.readInt();
			int x2 = in.readInt();
			int y2 = in.readInt();
			bricks[i] = new Segment(new Point(x1, y1), new Point(x2, y2));
		}
		boolean[] placed = new boolean[brickCount];
		@SuppressWarnings({"unchecked"})
		Segment[] intersections = new Segment[brickCount];
		for (int i = 0; i < brickCount; i++) {
			intersections[i] = null;
			for (Circle base : bases) {
				if (base.contains(bricks[i].a))
					intersections[i] = add(intersections[i], bricks[i].a);
				if (base.contains(bricks[i].b))
					intersections[i] = add(intersections[i], bricks[i].b);
				for (Point point : bricks[i].intersect(base))
					intersections[i] = add(intersections[i], point);
			}
		}
		while (true) {
			boolean updated = false;
			for (int i = 0; i < brickCount; i++) {
				if (!placed[i] && intersections[i] != null && intersections[i].contains(bricks[i].middle(), true)) {
					placed[i] = true;
					updated = true;
					for (int j = 0; j < brickCount; j++) {
						if (!placed[j]) {
							intersections[j] = add(intersections[j], bricks[j].intersect(bricks[i], true));
							if (bricks[i].contains(bricks[j].a, true))
								intersections[j] = add(intersections[j], bricks[j].a);
							if (bricks[i].contains(bricks[j].b, true))
								intersections[j] = add(intersections[j], bricks[j].b);
							if (bricks[j].contains(bricks[i].a, true))
								intersections[j] = add(intersections[j], bricks[i].a);
							if (bricks[j].contains(bricks[i].b, true))
								intersections[j] = add(intersections[j], bricks[i].b);
						}
					}
				}
			}
			if (!updated)
				break;
		}
		for (int i = 0; i < brickCount; i++) {
			if (!placed[i]) {
				out.println("NO");
				return;
			}
		}
		out.println("YES");
	}

	private Segment add(Segment segment, Point point) {
		if (point == null)
			return segment;
		if (segment == null)
			return new Segment(point, point);
		Segment first = new Segment(segment.a, point);
		Segment second = new Segment(segment.b, point);
		if (first.length() > segment.length())
			segment = first;
		if (second.length() > segment.length())
			segment = second;
		return segment;
	}
}

