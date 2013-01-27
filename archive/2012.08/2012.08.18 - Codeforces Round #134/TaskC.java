package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		in.readInt();
		if ((parseNode(in) & 4) != 0)
			out.printLine("YES");
		else
			out.printLine("NO");
	}

	private int parseNode(InputReader in) {
		int[] stack = new int[1000000];
		int stackSize = 0;
		while (!in.isExhausted()) {
			char c = in.readCharacter();
			if (c == ')') {
				int left = stack[--stackSize];
				int operation = stack[--stackSize];
				int right = stack[--stackSize];
				int result = 0;
				if (operation == '&') {
					if ((left & 1) != 0 || (right & 1) != 0 || (left & 4) != 0 && (right & 4) != 0)
						result++;
					result += (left & 2) & (right & 2);
					if ((left & 4) != 0 && (right & 6) != 0 || (right & 4) != 0 && (left & 6) != 0)
						result += 4;
				} else if (operation == '|') {
					result += (left & 1) & (right & 1);
					if ((left & 2) != 0 || (right & 2) != 0 || (left & 4) != 0 && (right & 4) != 0)
						result += 2;
					if ((left & 4) != 0 && (right & 5) != 0 || (right & 4) != 0 && (left & 5) != 0)
						result += 4;
				} else {
					if ((left & 1) != 0 && (right & 1) != 0 || (left & 2) != 0 && (right & 2) != 0 || (left & 4) != 0 && (right & 4) != 0)
						result++;
					if ((left & 1) != 0 && (right & 2) != 0 || (left & 2) != 0 && (right & 1) != 0 || (left & 4) != 0 && (right & 4) != 0)
						result += 2;
					if ((left & 3) != 0 && (right & 4) != 0 || (right & 3) != 0 && (left & 4) != 0)
						result += 4;
				}
				stack[stackSize++] = result;
			} else if (c == '0')
				stack[stackSize++] = 1;
			else if (c == '1')
				stack[stackSize++] = 2;
			else if (c == '?')
				stack[stackSize++] = 4;
			else if (c != '(')
				stack[stackSize++] = c;
		}
		return stack[0];
	}
}
