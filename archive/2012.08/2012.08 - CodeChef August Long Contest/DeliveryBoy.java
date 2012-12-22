package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class DeliveryBoy {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        int[][] distances = IOUtils.readIntTable(in, count, count);
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < count; j++) {
                for (int k = 0; k < count; k++)
                    distances[j][k] = Math.min(distances[j][k], distances[j][i] + distances[i][k]);
            }
        }
        int queryCount = in.readInt();
        for (int i = 0; i < queryCount; i++) {
            int start = in.readInt();
            int gas = in.readInt();
            int destination = in.readInt();
            out.printLine(distances[start][gas] + distances[gas][destination], -distances[start][destination] + distances[start][gas] + distances[gas][destination]);
        }
	}
}
