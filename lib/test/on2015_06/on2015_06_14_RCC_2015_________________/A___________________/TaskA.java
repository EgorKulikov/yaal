package on2015_06.on2015_06_14_RCC_2015_________________.A___________________;



import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    private static final long MOD = (long) (1e9 + 7);
    long[][] c = IntegerUtils.generateBinomialCoefficients(1001, MOD);
    
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int length = in.readInt();
        int target = in.readInt();
        int ones = 0;
        int zeroes = 0;
        for (int i = 0; i < length; i++) {
            if (in.readCharacter() == '1') {
                ones++;
            } else {
                zeroes++;
            }
        }
        long answer = 0;
        for (int i = 0; i <= length - target && i <= ones; i += 3) {
            int remainingOnes = ones - i;
            int remainingZeroes = target - remainingOnes;
            if (remainingZeroes < 0 || remainingZeroes % 2 != zeroes % 2) {
                continue;
            }
            answer += c[target][remainingOnes];
        }
        answer %= MOD;
        out.printLine(answer);
    }
}
