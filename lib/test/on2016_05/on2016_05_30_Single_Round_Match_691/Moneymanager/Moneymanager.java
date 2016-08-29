package on2016_05.on2016_05_30_Single_Round_Match_691.Moneymanager;



import static java.lang.Integer.MIN_VALUE;
import static net.egork.misc.ArrayUtils.*;

public class Moneymanager {
    public int getbest(int[] a, int[] b, int X) {
        int n = a.length;
        int[] order = createOrder(n);
        sort(order, (x, y) -> b[y] * a[x] - b[x] * a[y]);
        orderBy(reversePermutation(order), a, b);
        int sum = (int) sumArray(b);
        int half = n / 2;
        int answer = 0;
        for (int firstHalfSum = half; firstHalfSum <= sum - half; firstHalfSum++) {
            int remaining = 0;
            int[][] current = new int[half + 1][firstHalfSum + 1];
            int[][] next = new int[half + 1][firstHalfSum + 1];
            fill(current, MIN_VALUE / 2);
            current[0][0] = 0;
            for (int i = 0; i < n; i++) {
                fill(next, MIN_VALUE / 2);
                for (int j = 0; j <= half; j++) {
                    for (int k = 0; k <= firstHalfSum; k++) {
                        if (current[j][k] >= 0) {
                            if (j + 1 <= half && k + b[i] <= firstHalfSum) {
                                next[j + 1][k + b[i]] = Math.max(next[j + 1][k + b[i]], current[j][k] + (k + b[i] +
                                        sum - firstHalfSum) * a[i]);
                            }
                            next[j][k] = Math.max(next[j][k], current[j][k] + (remaining - k + b[i]) * a[i]);
                        }
                    }
                }
                remaining += b[i];
                int[][] temp = current;
                current = next;
                next = temp;
            }
            answer = Math.max(answer, current[half][firstHalfSum] + (sum - firstHalfSum) * X);
        }
        return answer;
    }
}
