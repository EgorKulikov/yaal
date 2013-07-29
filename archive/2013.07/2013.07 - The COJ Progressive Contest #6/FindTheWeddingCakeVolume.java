package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class FindTheWeddingCakeVolume {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] radius = new int[count];
		int[] height = new int[count];
		IOUtils.readIntArrays(in, radius, height);
		int answer = 0;
		for (int i = 0; i < count; i++)
			answer += radius[i] * radius[i] * height[i];
		out.printFormat("%.2f\n", answer * Math.PI);
    }
}
