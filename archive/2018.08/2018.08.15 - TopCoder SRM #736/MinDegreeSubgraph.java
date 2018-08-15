package net.egork;

public class MinDegreeSubgraph {
    public String exists(int n, long m, int k) {
        if (m < (long)k * (k + 1) / 2) {
            return "NONE";
        }
        if (m > (long) k * (k - 1) / 2 + (long)(n - k) * (k - 1)) {
            return "ALL";
        }
        return "SOME";
    }
}
