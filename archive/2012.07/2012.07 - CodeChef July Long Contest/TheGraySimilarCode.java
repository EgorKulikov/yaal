package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TheGraySimilarCode {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        long[] array = IOUtils.readLongArray(in, count);
        if (count >= 130) {
            out.printLine("Yes");
            return;
        }
        for (int i = 0; i < count; i++) {
            for (int j = i + 1; j < count; j++) {
                for (int k = j + 1; k < count; k++) {
                    for (int l = k + 1; l < count; l++) {
                        if ((array[i] ^ array[j] ^ array[k] ^ array[l]) == 0) {
                            out.printLine("Yes");
                            return;
                        }
                    }
                }
            }
        }
        out.printLine("No");
	}
}
