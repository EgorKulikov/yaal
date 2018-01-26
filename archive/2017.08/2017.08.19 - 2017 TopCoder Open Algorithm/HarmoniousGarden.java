package net.egork;

public class HarmoniousGarden {
    public String isPossible(int n, int k, int L) {
        if (L + (k - 1) * (L - 1) <= n) {
            return "Possible";
        }
        if (L % 2 == 1) {
            return "Impossible";
        }
        int[] need = new int[k + 1];
        for (int i = 1; i <= k; i++) {
            for (int j = 2; j * (j - 1) / 2 <= i; j++) {
                need[i] = Math.min(need[i], j * (L / 2 - 1) + 1 + need[i - j * (j - 1) / 2]);
            }
        }
        if (need[k] + 1 <= n) {
            return "Possible";
        }
        return "Impossible";
    }
}
