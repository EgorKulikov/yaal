package on2015_05.on2015_05_27_Weekly_Challenges___Week_15.Haunted_House;



import net.egork.io.IOUtils;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class HauntedHouse {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int count = in.readInt();
        int[] low = new int[count];
        int[] high = new int[count];
        IOUtils.readIntArrays(in, low, high);
        int[] delta = new int[ArrayUtils.maxElement(high) + 2];
        for (int i : low) {
            delta[i]++;
        }
        for (int i : high) {
            delta[i + 1]--;
        }
        int[] qty = new int[delta.length];
        qty[0] = delta[0];
        for (int i = 1; i < qty.length; i++) {
            qty[i] = qty[i - 1] + delta[i];
        }
        int answer = 0;
        for (int i = 0; i < qty.length; i++) {
            if (qty[i] >= i + 1) {
                answer = i + 1;
            }
        }
        out.printLine(answer);
    }
}
