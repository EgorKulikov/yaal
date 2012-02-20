package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Billboards {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
        int width = in.readInt();
        int height = in.readInt();
		String text = in.readLine();
		String[] words = text.split(" ");
		int[] lengths = new int[words.length];
		for (int i = 0; i < words.length; i++)
			lengths[i] = words[i].length();
		int left = 0;
		int right = Math.min(height, width);
		while (left != right) {
			int size = (left + right + 1) / 2;
			if (fit(width / size, height / size, lengths))
				left = size;
			else
				right = size - 1;
		}
		out.printLine("Case #" + testNumber + ":", left);
	}

	private boolean fit(int width, int height, int[] lengths) {
		int current = -1;
		height--;
		for (int i : lengths) {
			current += i + 1;
			if (current > width) {
				current = i;
				height--;
				if (current > width)
					return false;
			}
		}
		return height >= 0;
	}
}
