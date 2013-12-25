package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class DoomBakesCakes {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int colorCount = in.readInt();
		char[] mask = in.readString().toCharArray();
		int length = mask.length;
		for (int i = 1; i < length; i++) {
			if (mask[i] != '?' && mask[i] == mask[i - 1]) {
				out.printLine("NO");
				return;
			}
		}
		if (length != 1 && mask[0] != '?' && mask[0] == mask[length - 1]) {
			out.printLine("NO");
			return;
		}
		if (colorCount == 1) {
			if (length > 1)
				out.printLine("NO");
			else
				out.printLine("0");
			return;
		}
		if (length == 1) {
			if (mask[0] == '?')
				out.printLine(0);
			else
				out.printLine(mask);
			return;
		}
		if (colorCount == 2) {
			if (length % 2 == 1) {
				out.printLine("NO");
				return;
			}
			boolean good = true;
			for (int i = 0; i < length; i++) {
				if (mask[i] != '?' && mask[i] != '0' + i % 2) {
					good = false;
					break;
				}
			}
			if (good) {
				for (int i = 0; i < length; i++)
					out.print(i % 2);
				out.printLine();
				return;
			}
			good = true;
			for (int i = 0; i < length; i++) {
				if (mask[i] != '?' && mask[i] != '0' + 1 - i % 2) {
					good = false;
					break;
				}
			}
			if (good) {
				for (int i = 0; i < length; i++)
					out.print(1 - i % 2);
				out.printLine();
				return;
			}
			out.printLine("NO");
			return;
		}
		for (int i = 0; i < length; i++) {
			if (mask[i] != '?')
				continue;
			int left = mask[(i + length - 1) % length];
			int right = mask[(i + 1) % length];
			if (left != '0' && right != '0')
				mask[i] = '0';
			else if (left != '1' && right != '1')
				mask[i] = '1';
			else
				mask[i] = '2';
		}
		out.printLine(mask);
	}
}
