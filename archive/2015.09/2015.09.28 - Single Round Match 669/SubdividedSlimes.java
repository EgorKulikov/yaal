package net.egork;

public class SubdividedSlimes {
    public int needCut(int S, int M) {
        for (int i = 1; i < S; i++) {
            int current = 0;
            int remaining = S;
            for (int j = 0; j < i; j++) {
                int take = remaining / (i - j + 1);
                current += take * (remaining - take);
                remaining -= take;
            }
            if (current >= M) {
                return i;
            }
        }
		return -1;
    }
}
