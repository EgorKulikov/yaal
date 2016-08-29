package on2016_06.on2016_06_08_June_Challenge_2016.Chef_And_Binary_Operation;



import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import static java.lang.Math.max;
import static net.egork.misc.ArrayUtils.count;

public class ChefAndBinaryOperation {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        String a = in.readString();
        String b = in.readString();
        if (a.equals(b)) {
            out.printLine("Lucky Chef");
            out.printLine(0);
            return;
        }
        int zeroA = count(a.toCharArray(), '0');
        if (a.length() != b.length() || zeroA == 0 || zeroA == a.length()) {
            out.printLine("Unlucky Chef");
            return;
        }
        int zeroDiff = 0;
        int oneDiff = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) == '1' && b.charAt(i) == '0') {
                zeroDiff++;
            } else if (a.charAt(i) == '0' && b.charAt(i) == '1') {
                oneDiff++;
            }
        }
        out.printLine("Lucky Chef");
        out.printLine(max(zeroDiff, oneDiff));
    }
}
