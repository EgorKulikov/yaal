package on2012_07.on2012_6_31.taska;



import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        int[] computer = IOUtils.readIntArray(in, count);
        int[][] required = new int[count][];
        for (int i = 0; i < count; i++) {
            int requiredCount = in.readInt();
            required[i] = IOUtils.readIntArray(in, requiredCount);
        }
        MiscUtils.decreaseByOne(required);
        int answer = Integer.MAX_VALUE;
        for (int i = 1; i <= 3; i++)
            answer = Math.min(answer, go(computer, required, i));
        out.printLine(answer);
	}

    private int go(int[] computer, int[][] required, int current) {
        int count = computer.length;
        boolean[] processed = new boolean[count];
        int[] requirementsRemaining = new int[count];
        for (int i = 0; i < count; i++)
            requirementsRemaining[i] = required[i].length;
        int result = 0;
        int notCompleted = count;
        while (notCompleted != 0) {
            while (true) {
                boolean updated = false;
                for (int i = 0; i < count; i++) {
                    if (!processed[i] && computer[i] == current && requirementsRemaining[i] == 0) {
                        processed[i] = true;
                        result++;
                        for (int j = 0; j < count; j++) {
                            for (int k : required[j]) {
                                if (k == i)
                                    requirementsRemaining[j]--;
                            }
                        }
                        updated = true;
                        notCompleted--;
                    }
                }
                if (!updated)
                    break;
            }
            current = current % 3 + 1;
            result++;
        }
        return result - 1;
    }
}
