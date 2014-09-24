package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class FunWithRotation {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int size = in.readInt();
		int count = in.readInt();
		int[] array = IOUtils.readIntArray(in, size);
		int shift = 0;
		for (int i = 0; i < count; i++) {
			char type = in.readCharacter();
			if (type == 'R') {
				int at = in.readInt() - 1;
				at += shift;
				if (at >= size) {
					at -= size;
				}
				out.printLine(array[at]);
			} else {
				int by = in.readInt();
				if (type == 'A') {
					by = -by;
				}
				shift += by;
				if (shift < 0) {
					shift += size;
				} else if (shift >= size) {
					shift -= size;
				}
			}
		}
	}
}
