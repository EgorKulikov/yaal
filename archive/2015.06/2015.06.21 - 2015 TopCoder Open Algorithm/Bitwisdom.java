package net.egork;

public class Bitwisdom {
    public double expectedActions(int[] p) {
//        if (p.length == 1) {
//            return p[0] / 100d;
//        }
        double answer = 0;
        for (int i = 1; i < p.length; i++) {
            answer += (p[i] * (100 - p[i - 1]) + p[i - 1] * (100 - p[i])) / 10000d;
        }
//        answer += p[0] * p[p.length - 1] / 10000d;
        double add = 1;
        for (int i : p) {
            add *= i / 100d;
        }
		return answer + add;
    }
}
