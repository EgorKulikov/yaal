package on2012_0_19.taske;



import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskE {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
        int disciplineCount = in.readInt();
        int variantCount = in.readInt();
        int length = in.readInt();
        int[][] energy = new int[disciplineCount][variantCount];
        int[][] position = new int[disciplineCount][variantCount];
        for (int i = 0; i < disciplineCount; i++)
            IOUtils.readIntArrays(in, position[i], energy[i]);
        long[][] result = new long[disciplineCount][variantCount];
        for (int i = 0; i < variantCount; i++)
            result[0][i] = position[0][i] + energy[0][i];
        for (int i = 1; i < disciplineCount; i++) {
            for (int j = 0; j < variantCount; j++) {
                result[i][j] = Long.MAX_VALUE;
                for (int k = 0; k < variantCount; k++) {
                    result[i][j] = Math.min(result[i][j], result[i - 1][k] + Math.abs(position[i - 1][k] - position[i][j]));
                }
                result[i][j] += energy[i][j];
            }
        }
        long answer = Long.MAX_VALUE;
        for (int i = 0; i < variantCount; i++)
            answer = Math.min(answer, result[disciplineCount - 1][i] + length - position[disciplineCount - 1][i]);
        out.printLine(answer);
	}
}
