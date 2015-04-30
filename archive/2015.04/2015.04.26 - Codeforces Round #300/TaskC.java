package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int dayCount = in.readInt();
        int recordCount = in.readInt();
        int[] day = new int[recordCount];
        int[] height = new int[recordCount];
        IOUtils.readIntArrays(in, day, height);
        int answer = height[0] + day[0] - 1;
        for (int i = 1; i < recordCount; i++) {
            if (Math.abs(height[i] - height[i - 1]) > Math.abs(day[i] - day[i - 1])) {
                out.printLine("IMPOSSIBLE");
                return;
            }
            answer = Math.max(answer, Math.max(height[i], height[i - 1]) + (day[i] - day[i - 1] - Math.abs(height[i] - height[i - 1])) / 2);
        }
        answer = Math.max(answer, height[recordCount - 1] + dayCount - day[recordCount - 1]);
        out.printLine(answer);
    }
}
