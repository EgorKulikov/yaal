package Timus.Part4;

import net.egork.geometry.Circle;
import net.egork.geometry.Point;
import net.egork.utils.Solver;
import net.egork.utils.io.InputReader;

import java.io.PrintWriter;

public class Task1333 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int circleCount = in.readInt();
		Circle[] circles = new Circle[circleCount];
		for (int i = 0; i < circleCount; i++) {
			double x = in.readDouble();
			double y = in.readDouble();
			double radius = in.readDouble();
			circles[i] = new Circle(new Point(x, y), radius);
		}
		double res = 0;
		for (int i = 0; i <= 250; i++) {
			for (int j = 0; j <= 250; j++) {
				Point point = new Point(i / 250., j / 250.);
				for (Circle circle : circles) {
					if (circle.contains(point)) {
						boolean isVerticalEdge = i == 0 || i == 250;
						boolean isHorizontalEdge = j == 0 || j == 250;
						if (isVerticalEdge && isHorizontalEdge)
							res++;
						else if (isVerticalEdge || isHorizontalEdge)
							res += 2;
						else
							res += 4;
						break;
					}
				}
			}
		}
		res /= 2500;
		out.printf("%.6f\n", res);
	}
}

