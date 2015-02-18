package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class ExtraterrestrialRotations {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		String[] faces = IOUtils.readStringArray(in, 6);
		int count = in.readInt();
		for (int i = 0; i < count; i++) {
			char type = in.readCharacter();
			int times = in.readInt() % 4;
			for (int j = 0; j < times; j++) {
				if (type == 'X') {
					faces = new String[]{faces[2], faces[0], faces[5], faces[3], faces[4], faces[1]};
				} else if (type == 'Y') {
					faces = new String[]{faces[0], faces[3], faces[4], faces[2], faces[1], faces[5]};
				} else {
					faces = new String[]{faces[3], faces[1], faces[2], faces[5], faces[0], faces[4]};
				}
			}
		}
		out.printLine(faces);
    }
}
