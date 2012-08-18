package net.egork;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class CircularBuffer {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int size = in.readInt();
		String[] buffer = new String[size + 1];
		int start = 0;
		int end = 0;
		while (true) {
			char command = in.readCharacter();
			if (command == 'A') {
				int count = in.readInt();
				for (int i = 0; i < count; i++) {
					String element = in.readString();
					if ((end + 1) % (size + 1) == start)
						start = (start + 1) % (size + 1);
					buffer[end] = element;
					end = (end + 1) % (size + 1);
				}
			} else if (command == 'R') {
				int count = in.readInt();
				int length = end - start;
				if (length < 0)
					length += size + 1;
				if (length <= count)
					start = end = 0;
				else
					start = (start + count) % (size + 1);
			} else if (command == 'Q')
				break;
			else {
				for (int i = start; i != end; i = (i + 1) % (size + 1))
					out.printLine(buffer[i]);
			}
		}
	}
}
