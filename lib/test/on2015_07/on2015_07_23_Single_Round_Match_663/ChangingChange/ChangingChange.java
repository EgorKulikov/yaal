package on2015_07.on2015_07_23_Single_Round_Match_663.ChangingChange;



import net.egork.numbers.IntegerUtils;

public class ChangingChange {
    private static final long MOD = (long) (1e9 + 7);

    public int[] countWays(int[] ways, int[] valueRemoved, int[] numRemoved) {
        int count = ways.length - 1;
        int queries = valueRemoved.length;
        long[] newWays = new long[count + 1];
        long[] cc = new long[count + 1];
        long[] reverse = IntegerUtils.generateReverse(count + 1, MOD);
        int[] answer = new int[queries];
        for (int i = 0; i < queries; i++) {
            cc[0] = 1;
            for (int j = 1; j <= count; j++) {
                cc[j] = cc[j - 1] * ((MOD - numRemoved[i]) - j + 1) % MOD * reverse[j] % MOD;
            }
            long result = 0;
            for (int j = 0, k = 0; k <= count; j++, k += valueRemoved[i]) {
                result += ways[count - k] * cc[j] % MOD;
            }
            result %= MOD;
            answer[i] = (int) result;
//            for (int j = 0; j <= count; j++) {
//                newWays[j] = ways[j];
//                for (int k = 1, l = valueRemoved[i]; k <= numRemoved[i] && l <= j; k++, l += valueRemoved[i]) {
//                    newWays[j] -= newWays[j - l] * cc[k];
//                }
//                newWays[j] %= MOD;
//                if (newWays[j] < 0) {
//                    newWays[j] += MOD;
//                }
//            }
//            answer[i] = (int) newWays[count];
        }
        return answer;
    }
}
