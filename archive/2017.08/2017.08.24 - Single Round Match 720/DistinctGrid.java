package net.egork;

public class DistinctGrid {
    public int[] findGrid(int n, int k) {
        int[] answer = new int[n * n];
        int shift = 0;
        int current = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < k - 1; j++) {
                answer[i * n + shift] = current++;
                shift++;
                if (shift == n) {
                    shift = 0;
                }
            }
        }
        return answer;
    }
}
