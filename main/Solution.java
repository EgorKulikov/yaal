import static java.util.Arrays.fill;

class Solution {
    public static final int MOD7 = (int) (1e9 + 7);

    public int numPermsDISequence(String S) {
        int n = S.length() + 1;
        long[] current = new long[n];
        long[] next = new long[n];
        fill(current, 1);
        for (int i = 0; i < S.length(); i++) {
            if (S.charAt(i) == 'D') {
                long total = 0;
                for (int j = n - 1; j >= 0; j--) {
                    next[j] = total % MOD7;
                    total += current[j];
                }
            } else {
                long total = 0;
                for (int j = 0; j < n; j++) {
                    total += current[j];
                    next[j] = total % MOD7;
                }
            }
            n--;
            long[] temp = current;
            current = next;
            next = temp;
        }
        return (int)current[0];
    }
}