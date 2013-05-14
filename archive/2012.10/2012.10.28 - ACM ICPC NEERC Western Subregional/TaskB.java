package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		double length = in.readInt();
		double diameter = in.readInt();
		double radius = diameter / 2;
		double left = 0;
		double right = length;
		for (int i = 0; i < 10000; i++) {
			double midLeft = (2 * left + right) / 3;
			double midRight = (2 * right + left) / 3;
			if (get(length, radius, midLeft) > get(length, radius, midRight))
				right = midRight;
			else
				left = midLeft;
		}
		out.printLine(get(length, radius, (left + right) / 2));
	}

	private double get(double length, double radius, double height) {
		double bigRadius = radius + Math.sqrt(length * length - height * height);
		double bigHeight = height * bigRadius / (bigRadius - radius);
		return Math.PI / 3 * (bigRadius * bigRadius * bigHeight - radius * radius * (bigHeight - height));
	}
}
