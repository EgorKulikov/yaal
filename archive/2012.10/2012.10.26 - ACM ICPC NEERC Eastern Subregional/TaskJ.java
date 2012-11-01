package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskJ {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int width = in.readInt();
		int height = in.readInt();
		if (width > height) {
			int temp = width;
			width = height;
			height = temp;
		}
		double alpha = Math.atan((double)width / height);
		int queryCount = in.readInt();
		for (int i = 0; i < queryCount; i++) {
			int curHeight = in.readInt();
			int curWidth = in.readInt();
			int realHeight = curHeight;
			int realWidth = curWidth;
			if (curWidth > curHeight) {
				int temp = curWidth;
				curWidth = curHeight;
				curHeight = temp;
			}
			if (curWidth >= width && curHeight >= height) {
				out.printLine("Block the hole");
				continue;
			}
			if (curWidth < width) {
				out.printLine("Too small");
				continue;
			}
			double left = alpha;
			double right = Math.PI / 2;
			for (int j = 0; j < 100; j++) {
				double middle = (left + right) / 2;
				double current = height * Math.cos(middle) + width * Math.sin(middle);
				if (current < curHeight)
					right = middle;
				else
					left = middle;
			}
			if (Math.cos(left) * width + Math.sin(left) * height < curWidth + 1e-6)
				out.printLine("Block the hole");
			else {
				double sin = (double)width / realWidth;
				if (height * sin + width * Math.sqrt(1 - sin * sin) < realHeight + 1e-6)
					out.printLine("Block the hole");
				else
					out.printLine("Too small");
			}
		}
	}
}
