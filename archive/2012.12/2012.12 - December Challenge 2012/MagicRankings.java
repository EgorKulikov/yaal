package net.egork;

import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class MagicRankings {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        int[][] score = IOUtils.readIntTable(in, count, count);
        int[][] answer = new int[count][count];
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < count; j++) {
                if (i == 0 && j == 0)
                    continue;
                answer[i][j] = Integer.MIN_VALUE;
                if (i > 0)
                    answer[i][j] = Math.max(answer[i][j], answer[i - 1][j] + score[i][j]);
                if (j > 0)
                    answer[i][j] = Math.max(answer[i][j], answer[i][j - 1] + score[i][j]);
            }
        }
        if (answer[count - 1][count - 1] < 0)
            out.printLine("Bad Judges");
        else
            out.printLine((double)answer[count - 1][count - 1] / (2 * count - 3));
    }
}
