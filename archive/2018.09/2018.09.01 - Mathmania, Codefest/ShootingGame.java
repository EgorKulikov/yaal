package net.egork;

public class ShootingGame {
    public double findProbability(int p) {
        if (p > 500000) {
            return -1;
        }
        double pp = p / 1000000d;
        double answer = 1 - (1 - 2 * pp) / (1 - pp);
        return answer;
    }
}
