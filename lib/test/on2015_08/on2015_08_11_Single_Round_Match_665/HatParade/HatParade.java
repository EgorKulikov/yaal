package on2015_08.on2015_08_11_Single_Round_Match_665.HatParade;



import net.egork.misc.ArrayUtils;

public class HatParade {
    private static final int MOD = (int) (1e9 + 7);

    public int getPermutation(int[] value, int[] sum) {
        long total = ArrayUtils.sumArray(value);
        for (int i = 0; i < sum.length; i++) {
            sum[i] = (int) Math.min(sum[i], total + value[i] - sum[i]);
            sum[i] -= value[i];
        }
        int[] order = ArrayUtils.order(sum);
        long left = 0;
        long right = 0;
        int answer = 1;
        for (int i : order) {
            if (left == right && left + right + value[i] != total) {
                answer *= 2;
                if (answer >= MOD) {
                    answer -= MOD;
                }
            }
            if (sum[i] != left) {
                return 0;
            }
            left += value[i];
            if (left > right) {
                long temp = left;
                left = right;
                right = temp;
            }
        }
		return answer;
    }
}
